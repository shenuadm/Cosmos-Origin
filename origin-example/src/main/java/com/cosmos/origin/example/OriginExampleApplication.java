package com.cosmos.origin.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Origin Example 应用启动类
 * 用于演示和测试各个基础组件的集成使用
 */
@Slf4j
@SpringBootApplication
public class OriginExampleApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(OriginExampleApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path", "");

        log.info("""
                        
                        ----------------------------------------------------------
                        \tApplication '{}' is running! Access URLs:
                        \tLocal: \t\thttp://localhost:{}{}
                        \tExternal: \thttp://{}:{}{}
                        \tProfile(s): \t{}
                        ----------------------------------------------------------
                        """,
                env.getProperty("spring.application.name"),
                port,
                path,
                ip,
                port,
                path,
                env.getActiveProfiles().length == 0 ? "default" : String.join(",", env.getActiveProfiles())
        );

        log.info("========================================");
        log.info("origin-example 模块已启动");
        log.info("定时任务功能已启用，请查看日志输出");
        log.info("事件发布订阅功能已启用");
        log.info("========================================");
    }
}
