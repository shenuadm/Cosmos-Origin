package com.cosmos.origin.scheduler.publisher;

import com.cosmos.origin.event.publisher.EventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

/**
 * 定时任务事件发布工具类
 * 封装事件发布功能，用于在定时任务中发布事件
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SchedulerEventPublisher {

    private final EventPublisher eventPublisher;

    /**
     * 在定时任务中发布事件
     *
     * @param event 事件对象
     */
    public void publishEvent(ApplicationEvent event) {
        try {
            log.debug("定时任务发布事件: {}", event.getClass().getSimpleName());
            eventPublisher.publishEvent(event);
        } catch (Exception e) {
            log.error("定时任务发布事件失败: {}, 错误信息: {}", event.getClass().getSimpleName(), e.getMessage(), e);
        }
    }

    /**
     * 在定时任务中发布事件（支持任意对象作为事件）
     *
     * @param eventObject 事件对象（可以不继承 ApplicationEvent）
     */
    public void publishEvent(Object eventObject) {
        try {
            log.debug("定时任务发布事件对象: {}", eventObject.getClass().getSimpleName());
            eventPublisher.publishEvent(eventObject);
        } catch (Exception e) {
            log.error("定时任务发布事件对象失败: {}, 错误信息: {}", eventObject.getClass().getSimpleName(), e.getMessage(), e);
        }
    }
}
