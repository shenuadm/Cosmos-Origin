package com.cosmos.origin.websocket.model.vo.chatroom;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "获取历史消息请求参数")
public class FindChatMessagePageListReqVO {

    @Schema(description = "游标 ID，首次查询不传，翻页时传入最后一条消息的 ID")
    private Long lastId;
}
