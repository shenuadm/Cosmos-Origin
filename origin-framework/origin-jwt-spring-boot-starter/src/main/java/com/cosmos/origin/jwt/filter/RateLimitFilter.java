package com.cosmos.origin.jwt.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 限流过滤器（固定窗口计数器实现）
 *
 * @author 一陌千尘
 * @date 2026/02/06
 */
public class RateLimitFilter extends OncePerRequestFilter {

    // 白名单路径（支持 Ant 风格通配符）
    private static final List<String> WHITE_LIST = Arrays.asList(
            "/login",
            "/logout",
            "/doc.html",
            "/v3/api-docs/**",
            "/favicon.ico",
            "/webjars/**",
            "/.well-known/**",
            "/test"
    );

    private static final int MAX_REQUESTS_PER_SECOND = 10; // 每秒最大请求数

    private final AtomicInteger requestCount = new AtomicInteger(0);
    private volatile long windowStart = System.currentTimeMillis();
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain chain) throws IOException, ServletException {
        String requestUri = request.getRequestURI();

        // 检查是否在白名单中
        if (isWhiteListed(requestUri)) {
            chain.doFilter(request, response);
            return;
        }

        long now = System.currentTimeMillis();

        // 检查是否需要重置窗口（每秒重置）
        if (now - windowStart >= 1000) {
            synchronized (this) {
                if (now - windowStart >= 1000) {
                    requestCount.set(0);
                    windowStart = now;
                }
            }
        }

        // 检查是否超过限流阈值
        if (requestCount.incrementAndGet() > MAX_REQUESTS_PER_SECOND) {
            response.setStatus(429);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":429,\"message\":\"请求过于频繁，请稍后再试\"}");
            return;
        }

        chain.doFilter(request, response);
    }

    /**
     * 检查请求路径是否在白名单中
     */
    private boolean isWhiteListed(String requestUri) {
        return WHITE_LIST.stream().anyMatch(pattern -> pathMatcher.match(pattern, requestUri));
    }
}
