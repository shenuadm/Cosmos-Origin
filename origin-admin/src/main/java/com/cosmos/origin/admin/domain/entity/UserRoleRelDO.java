package com.cosmos.origin.admin.domain.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户角色关联DO
 *
 * @author 一陌千尘
 * @date 2025/11/05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("t_user_role_rel")
public class UserRoleRelDO {

    @Id(keyType = KeyType.Auto)
    private Long id;

    private Long userId;

    private Long roleId;
}
