package com.cosmos.origin.admin.domain.dos;

import com.cosmos.origin.common.model.BaseEntity;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.*;

/**
 * 用户实体类
 *
 * @author 一陌千尘
 * @date 2025/11/03
 */
@EqualsAndHashCode(callSuper = true) // 告诉 JVM 不需要生成 equals() 和 hashCode() 方法，直接使用父类的实现
@Data
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
