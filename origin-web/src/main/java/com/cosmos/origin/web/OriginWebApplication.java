package com.cosmos.origin.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;

@Slf4j
@ComponentScan({"com.cosmos.origin.*"}) // 多模块项目中，必需手动指定扫描 com.cosmos.origin 包下面的所有类
@SpringBootApplication
public class OriginWebApplication {

    public static void main(String[] args) {
        ConfigurableEnvironment env = SpringApplication.run(OriginWebApplication.class, args).getEnvironment();
        log.info("""
                        
                        ----------------------------------------------------------
                        \t\
                        Application: {} 运行成功！\s
                        \t\
                        Local URL: \thttp://localhost:{}
                        \t\
                        Document:\thttp://localhost:{}/doc.html
                        ----------------------------------------------------------""",
                env.getProperty("spring.application.name"),
                env.getProperty("server.port"),
                env.getProperty("server.port"));
    }

}
