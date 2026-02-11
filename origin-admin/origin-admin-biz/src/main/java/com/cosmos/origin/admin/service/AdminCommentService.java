package com.cosmos.origin.admin.service;

import com.cosmos.origin.admin.model.vo.comment.FindCommentPageListReqVO;
import com.cosmos.origin.common.utils.Response;

public interface AdminCommentService {

    /**
     * 查询评论分页数据
     *
     * @param findCommentPageListReqVO 查询评论分页数据请求参数
     * @return 查询评论分页数据响应结果
     */
    Response<?> findCommentPageList(FindCommentPageListReqVO findCommentPageListReqVO);
}
