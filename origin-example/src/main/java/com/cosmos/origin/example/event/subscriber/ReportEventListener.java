package com.cosmos.origin.example.event.subscriber;

import com.cosmos.origin.example.event.ReportGenerateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * 报告生成事件监听器
 * 异步处理报告生成任务
 */
@Slf4j
@Component
public class ReportEventListener {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 监听报告生成事件
     * 使用 @Async 注解实现异步处理，使用事件模块的线程池
     *
     * @param event 报告生成事件
     */
    @Async("eventTaskExecutor")
    @EventListener
    public void handleReportGenerateEvent(ReportGenerateEvent event) {
        String threadName = Thread.currentThread().getName();
        String generateTime = event.getGenerateTime().format(FORMATTER);
        
        log.info("========== 接收到报告生成事件 ==========");
        log.info("线程: {}", threadName);
        log.info("报告类型: {}", event.getReportType());
        log.info("报告描述: {}", event.getDescription());
        log.info("生成时间: {}", generateTime);

        try {
            // 模拟报告生成的耗时操作
            log.info("开始处理报告生成任务...");
            processReportGeneration(event.getReportType());
            log.info("报告生成完成！");
            
        } catch (Exception e) {
            log.error("报告生成失败: {}", e.getMessage(), e);
        }
    }

    /**
     * 模拟报告生成的业务逻辑
     *
     * @param reportType 报告类型
     */
    private void processReportGeneration(String reportType) throws InterruptedException {
        switch (reportType) {
            case "daily":
                log.info("正在查询昨日数据...");
                TimeUnit.SECONDS.sleep(2);
                log.info("正在生成日报 PDF...");
                TimeUnit.SECONDS.sleep(1);
                log.info("日报已保存至: /reports/daily/report_daily.pdf");
                break;
                
            case "weekly":
                log.info("正在查询上周数据...");
                TimeUnit.SECONDS.sleep(3);
                log.info("正在生成周报 PDF...");
                TimeUnit.SECONDS.sleep(2);
                log.info("周报已保存至: /reports/weekly/report_weekly.pdf");
                break;
                
            case "monthly":
                log.info("正在查询上月数据...");
                TimeUnit.SECONDS.sleep(5);
                log.info("正在生成月报 PDF...");
                TimeUnit.SECONDS.sleep(3);
                log.info("月报已保存至: /reports/monthly/report_monthly.pdf");
                break;
                
            case "test":
                log.info("执行测试报告生成逻辑...");
                TimeUnit.SECONDS.sleep(1);
                log.info("测试报告生成完成");
                break;
                
            default:
                log.warn("未知的报告类型: {}", reportType);
        }
    }

    /**
     * 报告生成后的后续处理
     * 可以在这里添加多个监听器，处理不同的业务逻辑
     */
    @Async("eventTaskExecutor")
    @EventListener
    public void afterReportGenerated(ReportGenerateEvent event) {
        try {
            // 模拟报告生成后的通知操作
            TimeUnit.MILLISECONDS.sleep(500);
            log.info("报告生成后处理: 发送邮件通知相关人员，报告类型: {}", event.getReportType());
        } catch (Exception e) {
            log.error("报告后处理失败: {}", e.getMessage(), e);
        }
    }
}
