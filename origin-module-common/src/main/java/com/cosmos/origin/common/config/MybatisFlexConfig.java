package com.cosmos.origin.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis Flex 配置文件
 *
 * @author 一陌千尘
 * @date 2025/11/03
 */
@Configuration
@MapperScan("com.cosmos.origin.common.domain.mapper")
public class MybatisFlexConfig {

}
