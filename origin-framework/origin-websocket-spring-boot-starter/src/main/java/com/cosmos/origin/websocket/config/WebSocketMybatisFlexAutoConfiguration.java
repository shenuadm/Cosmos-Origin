package com.cosmos.origin.websocket.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfiguration;

/**
 * WebSocket Mybatis Flex 配置
 * 采用 AutoConfiguration 方式，支持单体自动装配
 *
 * @author 一陌千尘
 * @date 2026/02/10
 */
@AutoConfiguration
@MapperScan("com.cosmos.origin.websocket.domain.mapper")
public class WebSocketMybatisFlexAutoConfiguration {
}
