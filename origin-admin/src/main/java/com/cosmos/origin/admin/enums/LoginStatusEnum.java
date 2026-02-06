package com.cosmos.origin.admin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 登录状态枚举
 *
 * @author 一陌千尘
 * @date 2025/02/06
 */
@Getter
@AllArgsConstructor
public enum LoginStatusEnum {

    /**
     * 登录成功
     */
    SUCCESS(1, "登录成功"),

    /**
     * 登录失败
     */
    FAILED(0, "登录失败"),

    /**
     * 账号被锁定
     */
    LOCKED(-1, "账号被锁定");

    private final Integer code;
    private final String description;
}
