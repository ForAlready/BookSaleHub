package com.booksalehub.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 */
@Configuration
@MapperScan("com.booksalehub.mapper")
public class MyBatisConfig {
    // MyBatis的配置主要通过注解和application.yml完成
}