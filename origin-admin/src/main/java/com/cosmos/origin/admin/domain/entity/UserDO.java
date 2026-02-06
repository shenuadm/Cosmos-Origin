package com.cosmos.origin.admin.domain.entity;

import com.cosmos.origin.common.model.BaseEntity;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 用户DO
 *
 * @author 一陌千尘
 * @date 2025/11/03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("t_user")
public class UserDO extends BaseEntity {

    @Id(keyType = KeyType.Auto)
    private Long id;

    private String username;

    private String password;

    private String nickname;

    private String avatar;

    private String phone;

    private String email;
}
