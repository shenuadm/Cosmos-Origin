package com.cosmos.origin.scheduler.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * 定时任务线程池配置类
 * 用于执行 @Scheduled 定时任务，提供专用线程池
 */
@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties(SchedulerProperties.class)
public class SchedulerExecutorConfig {

    private final SchedulerProperties properties;

    /**
     * 创建定时任务专用线程池
     *
     * @return 线程池调度器
     */
    @Bean(name = "schedulerTaskExecutor")
    public ThreadPoolTaskScheduler schedulerTaskExecutor() {
        log.info("========== 开始初始化定时任务线程池 ==========");

        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();

        // 线程池大小
        scheduler.setPoolSize(properties.getPoolSize());
        log.info("定时任务线程池大小: {}", properties.getPoolSize());

        // 线程名前缀
        scheduler.setThreadNamePrefix(properties.getThreadNamePrefix());

        // 等待所有任务完成后再关闭线程池
        scheduler.setWaitForTasksToCompleteOnShutdown(properties.getWaitForTasksToCompleteOnShutdown());

        // 设置等待终止时间
        scheduler.setAwaitTerminationSeconds(properties.getAwaitTerminationSeconds());

        // 初始化线程池
        scheduler.initialize();

        log.info("========== 定时任务线程池初始化完成 ==========");
        return scheduler;
    }
}
