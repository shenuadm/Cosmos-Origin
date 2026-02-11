package com.cosmos.origin.comment.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "查询评论列表请求参数")
public class FindCommentListReqVO {

    @Schema(description = "路由地址", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "路由地址不能为空")
    private String routerUrl;

}
