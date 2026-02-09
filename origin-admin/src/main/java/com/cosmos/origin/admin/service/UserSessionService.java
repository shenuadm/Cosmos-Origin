package com.cosmos.origin.admin.service;

import com.cosmos.origin.admin.model.vo.session.UserSessionVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 用户会话服务
 * <p>
 * 负责管理用户登录会话，将会话信息保存到 Redis
 *
 * @author 一陌千尘
 * @date 2026/02/10
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserSessionService {

    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    // Redis key 前缀
    private static final String USER_SESSION_KEY_PREFIX = "user:session:";
    private static final String USER_TOKEN_KEY_PREFIX = "user:token:";

    /**
     * 保存用户会话到 Redis
     *
     * @param userSessionVO 用户会话信息
     * @param expireMinutes 过期时间（分钟）
     */
    public void saveSession(UserSessionVO userSessionVO, Long expireMinutes) {
        try {
            String username = userSessionVO.getUsername();
            String token = userSessionVO.getToken();

            String sessionJson = objectMapper.writeValueAsString(userSessionVO);

            // 1. 保存 username -> session 映射（用于查询用户的会话信息）
            String userSessionKey = USER_SESSION_KEY_PREFIX + username;
            redisTemplate.opsForValue().set(userSessionKey, sessionJson, expireMinutes, TimeUnit.MINUTES);

            // 2. 保存 token -> username 映射（用于通过 token 快速查找用户）
            String tokenKey = USER_TOKEN_KEY_PREFIX + token;
            redisTemplate.opsForValue().set(tokenKey, username, expireMinutes, TimeUnit.MINUTES);

            log.info("用户 [{}] 登录会话已保存到 Redis，过期时间：{} 分钟", username, expireMinutes);
        } catch (JsonProcessingException e) {
            log.error("保存用户会话失败，序列化异常", e);
        }
    }

    /**
     * 根据用户名获取会话信息
     *
     * @param username 用户名
     * @return 会话信息，不存在则返回 null
     */
    public UserSessionVO getSessionByUsername(String username) {
        try {
            String userSessionKey = USER_SESSION_KEY_PREFIX + username;
            String sessionJson = redisTemplate.opsForValue().get(userSessionKey);

            if (sessionJson == null) {
                return null;
            }

            return objectMapper.readValue(sessionJson, UserSessionVO.class);
        } catch (JsonProcessingException e) {
            log.error("获取用户会话失败，反序列化异常", e);
            return null;
        }
    }

    /**
     * 根据 Token 获取会话信息
     *
     * @param token Token
     * @return 会话信息，不存在则返回 null
     */
    public UserSessionVO getSessionByToken(String token) {
        String tokenKey = USER_TOKEN_KEY_PREFIX + token;
        String username = redisTemplate.opsForValue().get(tokenKey);

        if (username == null) {
            return null;
        }

        return getSessionByUsername(username);
    }

    /**
     * 删除用户会话（用于退出登录）
     *
     * @param username 用户名
     */
    public void removeSession(String username) {
        // 先获取会话信息，以便删除 token 映射
        UserSessionVO session = getSessionByUsername(username);
        if (session != null && session.getToken() != null) {
            String tokenKey = USER_TOKEN_KEY_PREFIX + session.getToken();
            redisTemplate.delete(tokenKey);
        }

        // 删除用户会话
        String userSessionKey = USER_SESSION_KEY_PREFIX + username;
        redisTemplate.delete(userSessionKey);

        log.info("用户 [{}] 的会话已删除", username);
    }

    /**
     * 删除指定 Token 的会话
     *
     * @param token Token
     */
    public void removeSessionByToken(String token) {
        String tokenKey = USER_TOKEN_KEY_PREFIX + token;
        String username = redisTemplate.opsForValue().get(tokenKey);

        if (username != null) {
            removeSession(username);
        }
    }

    /**
     * 获取所有在线用户会话
     *
     * @return 在线用户会话列表
     */
    public List<UserSessionVO> getAllOnlineSessions() {
        List<UserSessionVO> sessions = new ArrayList<>();
        Set<String> keys = redisTemplate.keys(USER_SESSION_KEY_PREFIX + "*");

        if (!keys.isEmpty()) {
            for (String key : keys) {
                String sessionJson = redisTemplate.opsForValue().get(key);
                if (sessionJson != null) {
                    try {
                        UserSessionVO session = objectMapper.readValue(sessionJson, UserSessionVO.class);
                        sessions.add(session);
                    } catch (JsonProcessingException e) {
                        log.error("反序列化会话信息失败：{}", key, e);
                    }
                }
            }
        }

        return sessions;
    }

    /**
     * 强制下线指定用户
     *
     * @param username 用户名
     */
    public void forceLogout(String username) {
        removeSession(username);
        log.info("用户 [{}] 已被强制下线", username);
    }

    /**
     * 刷新会话过期时间
     *
     * @param username 用户名
     * @param expireMinutes 过期时间（分钟）
     */
    public void refreshSessionExpire(String username, Long expireMinutes) {
        String userSessionKey = USER_SESSION_KEY_PREFIX + username;
        UserSessionVO session = getSessionByUsername(username);

        if (session != null) {
            redisTemplate.expire(userSessionKey, expireMinutes, TimeUnit.MINUTES);
            String tokenKey = USER_TOKEN_KEY_PREFIX + session.getToken();
            redisTemplate.expire(tokenKey, expireMinutes, TimeUnit.MINUTES);
            log.debug("用户 [{}] 的会话过期时间已刷新", username);
        }
    }

    /**
     * 检查用户是否在线
     *
     * @param username 用户名
     * @return true-在线，false-离线
     */
    public boolean isOnline(String username) {
        String userSessionKey = USER_SESSION_KEY_PREFIX + username;
        return redisTemplate.hasKey(userSessionKey);
    }
}
