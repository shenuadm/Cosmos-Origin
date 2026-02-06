package com.cosmos.origin.jwt.filter;

import com.google.common.util.concurrent.RateLimiter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * 限流过滤器
 *
 * @author 一陌千尘
 * @date 2026/02/06
 */
public class RateLimitFilter extends OncePerRequestFilter {
    private final RateLimiter rateLimiter = RateLimiter.create(10.0); // 每秒10个请求

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        if (!rateLimiter.tryAcquire()) {
            response.setStatus(429);
            response.getWriter().write("请求过于频繁");
            return;
        }
        chain.doFilter(request, response);
    }
}
