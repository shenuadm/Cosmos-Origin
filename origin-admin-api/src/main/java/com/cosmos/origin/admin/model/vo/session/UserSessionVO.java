package com.cosmos.origin.admin.model.vo.session;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户会话信息 VO
 *
 * @author 一陌千尘
 * @date 2026/02/10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSessionVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户 ID
     */
    private Long userId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 角色列表
     */
    private List<String> roles;

    /**
     * Token
     */
    private String token;

    /**
     * 登录时间
     */
    private LocalDateTime loginTime;

    /**
     * 登录 IP
     */
    private String loginIp;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 是否记住我
     */
    private Boolean rememberMe;

    /**
     * Token 过期时间
     */
    private LocalDateTime expireTime;
}
