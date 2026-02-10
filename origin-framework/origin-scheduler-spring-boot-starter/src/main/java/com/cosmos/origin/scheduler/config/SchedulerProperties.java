package com.cosmos.origin.scheduler.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 定时任务线程池配置属性类
 */
@Data
@ConfigurationProperties(prefix = "origin.scheduler.thread-pool")
public class SchedulerProperties {

    /**
     * 线程池大小，默认为 CPU 核心数
     */
    private Integer poolSize = Runtime.getRuntime().availableProcessors();

    /**
     * 线程名前缀，默认为 "scheduler-"
     */
    private String threadNamePrefix = "scheduler-";

    /**
     * 是否等待任务完成后再关闭线程池，默认为 true
     */
    private Boolean waitForTasksToCompleteOnShutdown = true;

    /**
     * 等待任务完成的超时时间（秒），默认为 60 秒
     */
    private Integer awaitTerminationSeconds = 60;
}
