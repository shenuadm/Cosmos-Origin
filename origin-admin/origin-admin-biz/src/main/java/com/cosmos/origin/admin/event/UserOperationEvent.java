package com.cosmos.origin.admin.event;

import com.cosmos.origin.event.event.BaseEvent;
import lombok.Getter;

/**
 * 用户操作事件
 * 用于监听用户相关操作，实现业务解耦
 */
@Getter
public class UserOperationEvent extends BaseEvent<UserOperationEvent.UserOperationData> {

    public UserOperationEvent(Object source, UserOperationData data) {
        super(source, data, "UserOperation");
    }

    /**
     * 用户操作数据
     *
     * @param userId        用户 ID
     * @param username      用户名
     * @param operationType 操作类型（如：CREATE, UPDATE, DELETE）
     * @param description   操作描述
     */
    public record UserOperationData(Long userId, String username, String operationType, String description) {
    }
}
