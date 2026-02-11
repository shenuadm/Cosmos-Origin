package com.cosmos.origin.comment.model.vo;

import com.cosmos.origin.common.model.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "查询评论分页数据请求参数")
public class FindCommentPageListReqVO extends BasePageQuery {

    @Schema(description = "路由地址")
    private String routerUrl;

    @Schema(description = "发布的起始日期")
    private LocalDate startDate;

    @Schema(description = "发布的结束日期")
    private LocalDate endDate;

    @Schema(description = "状态")
    private Integer status;
}
