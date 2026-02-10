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
@Schema(name = "在线用户列表消息")
public class OnlineUsersMessageVO {

    @Schema(description = "消息类型")
    private Integer type;

    @Schema(description = "在线用户列表")
    private List<OnlineUserVO> users;

    @Schema(description = "在线人数")
    private Integer onlineCount;
}
