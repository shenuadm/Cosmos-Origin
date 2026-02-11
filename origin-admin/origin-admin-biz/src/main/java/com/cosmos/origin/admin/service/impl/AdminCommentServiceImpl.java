package com.cosmos.origin.admin.service.impl;

import com.cosmos.origin.admin.model.vo.comment.FindCommentPageListReqVO;
import com.cosmos.origin.admin.service.AdminCommentService;
import com.cosmos.origin.common.utils.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class AdminCommentServiceImpl implements AdminCommentService {


    /**
     * 查询评论分页数据
     *
     * @param findCommentPageListReqVO 查询评论分页数据请求参数
     * @return 查询评论分页数据响应结果
     */
    @Override
    public Response<?> findCommentPageList(FindCommentPageListReqVO findCommentPageListReqVO) {
        // 获取当前页、以及每页需要展示的数据数量
        return Response.success();
    }

}
