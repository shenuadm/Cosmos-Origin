package com.cosmos.origin.common.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 分页查询参数，继承该类即可
 *
 * @author 一陌千尘
 * @date 2026/02/06
 */
@Data
public class BasePageQuery {

    @NotNull(message = "当前页码不能为空")
    @Schema(description = "当前页码, 默认第 1 页", requiredMode = Schema.RequiredMode.REQUIRED)
    @Min(value = 1, message = "当前页码不能小于 1")
    private Long current = 1L;

    @NotNull(message = "每页展示的数据数量不能为空")
    @Schema(description = "每页展示的数据数量，默认每页展示 10 条数据", requiredMode = Schema.RequiredMode.REQUIRED)
    @Min(value = 1, message = "每页展示的数据数量不能小于 1")
    private Long size = 10L;
}
