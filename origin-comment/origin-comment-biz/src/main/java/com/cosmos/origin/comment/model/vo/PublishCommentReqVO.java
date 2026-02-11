package com.cosmos.origin.comment.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "发布评论请求参数")
public class PublishCommentReqVO {

    @Schema(description = "头像")
    private String avatar;

    @NotBlank(message = "昵称不能为空")
    @Schema(description = "昵称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nickname;

    @NotBlank(message = "路由地址不能为空")
    private String routerUrl;

    @NotBlank(message = "评论内容不能为空")
    @Length(min = 1, max = 120, message = "评论内容需大于 1 小于 120 字符")
    private String content;

    /**
     * 回复的评论 ID
     */
    private Long replyCommentId;

    /**
     * 父评论 ID
     */
    private Long parentCommentId;
}
