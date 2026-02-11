package com.cosmos.origin.comment.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "查询评论列表响应参数")
public class FindCommentListRspVO {

    @Schema(description = "总评论数")
    private Integer total;

    @Schema(description = "评论集合")
    private List<FindCommentItemRspVO> comments;

}
