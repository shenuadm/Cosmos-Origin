package com.cosmos.origin.auth;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class ThreadPoolTaskExecutorTests {

    /**
     * 测试线程池基本功能
     */
    @Test
    void testThreadPoolTaskExecutor() throws InterruptedException {
        // 创建线程池
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(10);
        executor.setThreadNamePrefix("test-executor-");
        executor.initialize();

        // 用于等待任务完成的计数器
        CountDownLatch latch = new CountDownLatch(1);

        // 提交任务
        executor.submit(() -> {
            log.info("异步线程中说: 技术底座");
            latch.countDown();
        });

        // 等待任务完成（最多5秒）
        boolean completed = latch.await(5, TimeUnit.SECONDS);
        assertTrue(completed, "任务应该在5秒内完成");

        // 关闭线程池
        executor.shutdown();
        log.info("线程池测试完成");
    }
}
