package com.cosmos.origin.web.filter;

import com.cosmos.origin.admin.service.LoginAttemptService;
import com.cosmos.origin.common.enums.ResponseCodeEnum;
import com.cosmos.origin.common.utils.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录尝试检查过滤器
 * <p>
 * 在登录请求到达认证过滤器之前，检查账号是否被锁定，
 * 如果被锁定则直接返回错误信息，避免进行密码验证
 *
 * @author 一陌千尘
 * @date 2025/02/06
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class LoginAttemptCheckFilter extends OncePerRequestFilter {

    private final LoginAttemptService loginAttemptService;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        // 只处理登录请求
        if (isLoginRequest(request)) {
            String username = extractUsername(request);

            if (username != null && !username.isEmpty()) {
                // 检查账号是否被锁定
                if (loginAttemptService.isLocked(username)) {
                    long lockRemainingMinutes = loginAttemptService.getLockRemainingMinutes(username);

                    log.warn("用户 [{}] 尝试登录，但账号已被锁定，剩余锁定时间: {} 分钟", username, lockRemainingMinutes);

                    // 直接返回锁定响应
                    writeLockedResponse(response, lockRemainingMinutes);
                    return;
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    /**
     * 写入锁定响应
     */
    private void writeLockedResponse(HttpServletResponse response, long lockRemainingMinutes) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        String message = String.format("登录失败次数过多，账号已被锁定，请 %d 分钟后重试", lockRemainingMinutes);

        Map<String, Object> data = new HashMap<>();
        data.put("currentAttempts", 0);
        data.put("maxAttempts", 5);
        data.put("remainingAttempts", 0);
        data.put("locked", true);
        data.put("lockRemainingMinutes", lockRemainingMinutes);
        data.put("message", message);

        Response<Map<String, Object>> resp = Response.fail(
                ResponseCodeEnum.ACCOUNT_LOCKED.getErrorCode(),
                message
        );
        resp.setData(data);

        response.getWriter().write(objectMapper.writeValueAsString(resp));
    }

    /**
     * 判断是否为登录请求
     */
    private boolean isLoginRequest(HttpServletRequest request) {
        return "POST".equalsIgnoreCase(request.getMethod()) &&
                ("/login".equals(request.getServletPath()) ||
                        "/api/auth/login".equals(request.getServletPath()) ||
                        "/api/v1/login".equals(request.getServletPath()));
    }

    /**
     * 从请求中提取用户名
     */
    private String extractUsername(HttpServletRequest request) {
        // 优先从请求属性中获取（如果前面的过滤器已经设置）
        String username = (String) request.getAttribute("LOGIN_USERNAME");
        if (username != null) {
            return username;
        }

        // 否则从请求参数中获取
        return request.getParameter("username");
    }
}
