package com.cosmos.origin.websocket.model.vo.chatroom;

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
@Schema(name = "获取历史消息响应参数")
public class FindChatMessagePageListRspVO {

    @Schema(description = "聊天消息列表")
    private List<ChatMessageVO> messages;

    @Schema(description = "是否还有下一页")
    private Boolean hasMore;
}
