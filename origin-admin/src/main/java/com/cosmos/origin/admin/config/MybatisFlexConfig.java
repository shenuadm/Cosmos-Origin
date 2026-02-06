package com.cosmos.origin.admin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis Flex 配置
 * <p>
 * 扫描admin模块中的Mapper接口
 *
 * @author 一陌千尘
 * @date 2025/11/03
 */
@Configuration("adminMybatisFlexConfig")
@MapperScan("com.cosmos.origin.admin.domain.mapper")
public class MybatisFlexConfig {
}
