package com.cosmos.origin.example.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

/**
 * 报告生成事件
 * 当定时任务触发报告生成时发布此事件
 */
@Getter
public class ReportGenerateEvent extends ApplicationEvent {

    /**
     * 报告类型（daily-日报、weekly-周报、monthly-月报）
     */
    private final String reportType;

    /**
     * 报告生成时间
     */
    private final LocalDateTime generateTime;

    /**
     * 报告描述
     */
    private final String description;

    public ReportGenerateEvent(Object source, String reportType, String description) {
        super(source);
        this.reportType = reportType;
        this.description = description;
        this.generateTime = LocalDateTime.now();
    }
}
