package com.cosmos.origin.comment.service.impl;

import com.cosmos.origin.comment.domain.dos.CommentDO;
import com.cosmos.origin.comment.domain.mapper.CommentMapper;
import com.cosmos.origin.comment.enums.CommentStatusEnum;
import com.cosmos.origin.comment.model.vo.PublishCommentReqVO;
import com.cosmos.origin.comment.service.CommentService;
import com.cosmos.origin.common.utils.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;

    @Value("${comment.examine.open}")
    private Boolean isCommentExamineOpen; // 是否开启评论审核
    @Value("${comment.sensi.word.open}")
    private Boolean isCommentSensiWordOpen; // 是否开启敏感词过滤

    /**
     * 发布评论
     *
     * @param publishCommentReqVO 发布评论请求参数
     * @return 发布评论响应结果
     */
    @Override
    public Response<?> publishComment(PublishCommentReqVO publishCommentReqVO) {
        // 获取存储在 ThreadLocal 中的用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 拿到用户名
        String username = authentication.getName();

        // 回复的评论 ID
        Long replyCommentId = publishCommentReqVO.getReplyCommentId();
        // 评论内容
        String content = publishCommentReqVO.getContent();
        // 昵称
        String nickname = publishCommentReqVO.getNickname();

        // 设置默认状态（正常）
        Integer status = CommentStatusEnum.NORMAL.getCode();
        // 审核不通过原因
        String reason = "";

        // 如果开启了审核, 设置状态为待审核，等待博主后台审核通过
        if (isCommentExamineOpen) {
            status = CommentStatusEnum.WAIT_EXAMINE.getCode();
        }

        // 是否开启了敏感词过滤
        if (isCommentSensiWordOpen) {
            // todo 敏感词过滤，先空着
        }

        // 构建 DO 对象
        CommentDO commentDO = CommentDO.builder()
                .avatar(publishCommentReqVO.getAvatar())
                .content(content)
                .nickname(nickname)
                .username(username)
                .routerUrl(publishCommentReqVO.getRouterUrl())
                .replyCommentId(replyCommentId)
                .parentCommentId(publishCommentReqVO.getParentCommentId())
                .status(status)
                .reason(reason)
                .build();

        // 新增评论
        commentMapper.insert(commentDO);

        return Response.success();
    }
}
