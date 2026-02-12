package com.cosmos.origin.jwt.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录响应工具类
 * <p>
 * 提供创建登录相关响应数据的工具方法，避免重复代码
 *
 * @author 一陌千尘
 * @date 2025/02/09
 */
public class LoginResponseUtil {

    /**
     * 默认最大尝试次数
     */
    private static final int DEFAULT_MAX_ATTEMPTS = 5;

    /**
     * 创建账号锁定响应数据
     *
     * @param lockRemainingMinutes 剩余锁定时间（分钟）
     * @return 锁定响应数据 Map
     */
    public static Map<String, Object> createLockedData(long lockRemainingMinutes) {
        Map<String, Object> data = new HashMap<>();
        data.put("currentAttempts", 0);
        data.put("maxAttempts", DEFAULT_MAX_ATTEMPTS);
        data.put("remainingAttempts", 0);
        data.put("locked", true);
        data.put("lockRemainingMinutes", lockRemainingMinutes);
        return data;
    }

    /**
     * 创建登录尝试信息响应数据
     *
     * @param currentAttempts   当前尝试次数
     * @param remainingAttempts 剩余尝试次数
     * @return 登录尝试信息 Map
     */
    public static Map<String, Object> createAttemptData(int currentAttempts, int remainingAttempts) {
        Map<String, Object> data = new HashMap<>();
        data.put("currentAttempts", currentAttempts);
        data.put("maxAttempts", DEFAULT_MAX_ATTEMPTS);
        data.put("remainingAttempts", remainingAttempts);
        data.put("locked", false);
        data.put("lockRemainingMinutes", 0);
        return data;
    }
}
