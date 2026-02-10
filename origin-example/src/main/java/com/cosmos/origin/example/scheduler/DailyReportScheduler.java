package com.cosmos.origin.example.scheduler;

import com.cosmos.origin.example.event.ReportGenerateEvent;
import com.cosmos.origin.scheduler.publisher.SchedulerEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日报生成定时任务示例
 * 演示如何使用 @Scheduled 定时任务 + 事件发布订阅
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DailyReportScheduler {

    private final SchedulerEventPublisher schedulerEventPublisher;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 每天凌晨 2 点生成日报
     * cron表达式：秒 分 时 日 月 周
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void generateDailyReport() {
        String currentTime = LocalDateTime.now().format(FORMATTER);
        log.info("========== 开始生成日报，时间: {} ==========", currentTime);

        try {
            // 发布报告生成事件，由事件监听器异步处理
            ReportGenerateEvent event = new ReportGenerateEvent(
                    this,
                    "daily",
                    "生成系统日报，统计昨日数据"
            );
            schedulerEventPublisher.publishEvent(event);

            log.info("日报生成事件已发布，等待异步处理");
        } catch (Exception e) {
            log.error("日报生成失败: {}", e.getMessage(), e);
        }
    }

    /**
     * 每周一上午 9 点生成周报
     */
    @Scheduled(cron = "0 0 9 ? * MON")
    public void generateWeeklyReport() {
        String currentTime = LocalDateTime.now().format(FORMATTER);
        log.info("========== 开始生成周报，时间: {} ==========", currentTime);

        try {
            ReportGenerateEvent event = new ReportGenerateEvent(
                    this,
                    "weekly",
                    "生成系统周报，统计上周数据"
            );
            schedulerEventPublisher.publishEvent(event);

            log.info("周报生成事件已发布，等待异步处理");
        } catch (Exception e) {
            log.error("周报生成失败: {}", e.getMessage(), e);
        }
    }

    /**
     * 每月 1 日上午 10 点生成月报
     */
    @Scheduled(cron = "0 0 10 1 * ?")
    public void generateMonthlyReport() {
        String currentTime = LocalDateTime.now().format(FORMATTER);
        log.info("========== 开始生成月报，时间: {} ==========", currentTime);

        try {
            ReportGenerateEvent event = new ReportGenerateEvent(
                    this,
                    "monthly",
                    "生成系统月报，统计上月数据"
            );
            schedulerEventPublisher.publishEvent(event);

            log.info("月报生成事件已发布，等待异步处理");
        } catch (Exception e) {
            log.error("月报生成失败: {}", e.getMessage(), e);
        }
    }

    /**
     * 测试用：每 30 秒执行一次（方便测试）
     * 生产环境请注释掉或删除此方法
     */
    @Scheduled(fixedRate = 30000, initialDelay = 5000)
    public void testScheduler() {
        String currentTime = LocalDateTime.now().format(FORMATTER);
        log.info("========== 测试定时任务执行，时间: {} ==========", currentTime);

        try {
            ReportGenerateEvent event = new ReportGenerateEvent(
                    this,
                    "test",
                    "测试定时任务和事件发布订阅功能"
            );
            schedulerEventPublisher.publishEvent(event);

            log.info("测试事件已发布");
        } catch (Exception e) {
            log.error("测试任务执行失败: {}", e.getMessage(), e);
        }
    }
}
