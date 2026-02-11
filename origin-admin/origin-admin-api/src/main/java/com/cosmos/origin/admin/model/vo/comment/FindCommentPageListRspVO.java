package com.cosmos.origin.admin.model.vo.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "查询评论分页数据响应参数")
public class FindCommentPageListRspVO {

    @Schema(description = "评论 ID")
    private Long id;

    @Schema(description = "路由地址")
    private String routerUrl;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "登录账号")
    private String username;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "评论内容")
    private String content;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "原因")
    private String reason;
}
