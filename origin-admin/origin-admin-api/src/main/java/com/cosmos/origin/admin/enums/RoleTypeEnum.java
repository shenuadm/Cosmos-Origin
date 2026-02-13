package com.cosmos.origin.admin.enums;

import lombok.Getter;

/**
 * 角色类型枚举
 *
 * @author 一陌千尘
 * @date 2025/11/05
 */
@Getter
public enum RoleTypeEnum {

    SYSTEM_ADMIN("ROLE_SYSTEM_ADMIN", "系统管理员"),
    ;

    private final String roleKey;
    private final String roleName;

    RoleTypeEnum(String roleKey, String roleName) {
        this.roleKey = roleKey;
        this.roleName = roleName;
    }
}
