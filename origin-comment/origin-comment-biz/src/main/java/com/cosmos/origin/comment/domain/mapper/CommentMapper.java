package com.cosmos.origin.comment.domain.mapper;

import com.cosmos.origin.comment.domain.dos.CommentDO;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.query.QueryWrapper;

import java.util.List;

public interface CommentMapper extends BaseMapper<CommentDO> {

    /**
     * 根据路由地址、状态查询对应的评论
     *
     * @param routerUrl 路由地址
     * @return 评论列表
     */
    default List<CommentDO> selectByRouterUrlAndStatus(String routerUrl, Integer status) {
        return selectListByQuery(QueryWrapper.create()
                .eq(CommentDO::getRouterUrl, routerUrl) // 按路由地址查询
                .eq(CommentDO::getStatus, status) // 按状态查询
                .orderBy(CommentDO::getCreateTime, false) // 按创建时间倒序
        );
    }
}
