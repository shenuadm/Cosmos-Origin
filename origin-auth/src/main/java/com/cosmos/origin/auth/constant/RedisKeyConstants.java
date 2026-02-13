package com.cosmos.origin.auth.constant;

public class RedisKeyConstants {

    /**
     * 验证码 KEY 前缀
     */
    private static final String VERIFICATION_CODE_KEY_PREFIX = "verification_code:";

    /**
     * 构建验证码 KEY
     *
     * @param phone 手机号
     * @return 验证码 KEY
     */
    public static String buildVerificationCodeKey(String phone) {
        return VERIFICATION_CODE_KEY_PREFIX + phone;
    }
}
