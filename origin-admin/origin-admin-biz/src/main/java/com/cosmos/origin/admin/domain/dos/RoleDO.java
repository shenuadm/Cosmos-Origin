package com.cosmos.origin.admin.domain.dos;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色实体类
 *
 * @author 一陌千尘
 * @date 2025/11/05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("t_role")
public class RoleDO {

    @Id(keyType = KeyType.Auto)
    private Long id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色唯一标识
     */
    private String roleKey;
}
