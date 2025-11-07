package com.cosmos.origin.web.controller;

import com.cosmos.origin.common.aspect.ApiOperationLog;
import com.cosmos.origin.common.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Tag(name = "测试模块")
public class TestController {

    @PostMapping("/admin/update")
    @ApiOperationLog(description = "测试更新接口")
    @Operation(summary = "测试更新接口")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Response<?> testUpdate() {
        log.info("更新成功...");
        return Response.success();
    }
}
