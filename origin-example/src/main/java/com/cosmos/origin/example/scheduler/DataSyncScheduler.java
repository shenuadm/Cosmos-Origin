package com.cosmos.origin.example.scheduler;

import com.cosmos.origin.event.publisher.EventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 数据同步定时任务示例
 * 演示更复杂的集成场景：定时任务 + 直接使用事件发布器
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DataSyncScheduler {

    private final EventPublisher eventPublisher;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 每 5 分钟同步一次数据
     * fixedRate: 固定频率执行，单位毫秒
     * initialDelay: 初始延迟，单位毫秒
     */
    @Scheduled(fixedRate = 300000, initialDelay = 10000)
    public void syncUserData() {
        String currentTime = LocalDateTime.now().format(FORMATTER);
        log.info("========== 开始同步用户数据，时间: {} ==========", currentTime);

        try {
            // 模拟数据同步逻辑
            int syncCount = performDataSync();

            // 发布数据同步完成事件
            DataSyncCompletedEvent event = new DataSyncCompletedEvent(
                    "USER_DATA",
                    syncCount,
                    LocalDateTime.now()
            );
            eventPublisher.publishEvent(event);

            log.info("用户数据同步完成，同步记录数: {}", syncCount);
        } catch (Exception e) {
            log.error("用户数据同步失败: {}", e.getMessage(), e);
        }
    }

    /**
     * 每小时清理一次过期缓存
     * cron 表达式：每小时的第 30 分钟执行
     */
    @Scheduled(cron = "0 30 * * * ?")
    public void cleanExpiredCache() {
        String currentTime = LocalDateTime.now().format(FORMATTER);
        log.info("========== 开始清理过期缓存，时间: {} ==========", currentTime);

        try {
            // 模拟缓存清理逻辑
            int cleanedCount = performCacheClean();

            log.info("过期缓存清理完成，清理条目数: {}", cleanedCount);

            // 发布缓存清理完成事件
            CacheCleanedEvent event = new CacheCleanedEvent(cleanedCount, LocalDateTime.now());
            eventPublisher.publishEvent(event);

        } catch (Exception e) {
            log.error("缓存清理失败: {}", e.getMessage(), e);
        }
    }

    /**
     * 每天凌晨 3 点备份数据库
     */
    @Scheduled(cron = "0 0 3 * * ?")
    public void backupDatabase() {
        String currentTime = LocalDateTime.now().format(FORMATTER);
        log.info("========== 开始备份数据库，时间: {} ==========", currentTime);

        try {
            // 模拟数据库备份逻辑
            String backupFile = performDatabaseBackup();

            log.info("数据库备份完成，备份文件: {}", backupFile);

            // 发布数据库备份完成事件
            DatabaseBackupEvent event = new DatabaseBackupEvent(backupFile, LocalDateTime.now());
            eventPublisher.publishEvent(event);

        } catch (Exception e) {
            log.error("数据库备份失败: {}", e.getMessage(), e);
        }
    }

    // ==================== 私有方法：模拟业务逻辑 ====================

    private int performDataSync() throws InterruptedException {
        log.info("正在从远程服务器拉取数据...");
        Thread.sleep(1000);
        log.info("正在写入本地数据库...");
        Thread.sleep(500);
        return (int) (Math.random() * 100) + 1;
    }

    private int performCacheClean() throws InterruptedException {
        log.info("正在扫描过期缓存项...");
        Thread.sleep(800);
        log.info("正在删除过期缓存...");
        Thread.sleep(300);
        return (int) (Math.random() * 50);
    }

    private String performDatabaseBackup() throws InterruptedException {
        log.info("正在导出数据库数据...");
        Thread.sleep(2000);
        log.info("正在压缩备份文件...");
        Thread.sleep(1000);
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        return "/backups/db_backup_" + timestamp + ".sql.gz";
    }

    // ==================== 内部事件类 ====================

    /**
     * 数据同步完成事件
     */
    public record DataSyncCompletedEvent(
            String dataType,
            int syncCount,
            LocalDateTime completedTime
    ) {
    }

    /**
     * 缓存清理完成事件
     */
    public record CacheCleanedEvent(
            int cleanedCount,
            LocalDateTime cleanedTime
    ) {
    }

    /**
     * 数据库备份完成事件
     */
    public record DatabaseBackupEvent(
            String backupFile,
            LocalDateTime backupTime
    ) {
    }
}
