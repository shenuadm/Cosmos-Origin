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
@Schema(name = "聊天室消息")
public class ChatMessageVO {

    @Schema(description = "消息 ID")
    private Long id;

    @Schema(description = "消息类型：0-系统消息，1-聊天消息，2-在线用户列表消息")
    private Integer type;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "消息内容")
    private String content;

    @Schema(description = "发送时间")
    private String time;

    @Schema(description = "在线人数")
    private Integer onlineCount;
}
