package com.cosmos.origin.comment.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "查询评论列表响应参数")
public class FindCommentItemRspVO {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "评论内容")
    private String content;

    @Schema(description = "发布时间")
    private LocalDateTime createTime;

    @Schema(description = "回复用户的昵称")
    private String replyNickname;

    @Schema(description = "子评论集合")
    private List<FindCommentItemRspVO> childComments;

    @Schema(description = "是否展示回复表单（默认 false）")
    private Boolean isShowReplyForm;
}
