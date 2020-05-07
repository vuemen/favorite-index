package com.shun.favoriteindex.context.configuration;

import com.shun.favoriteindex.context.FiApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 上下文配置
 */
@Configuration
public class FiApplicationContextConfiguration {

    @Bean
    public FiApplicationContext fiApplicationContext() {
        return new FiApplicationContext();
    }
}
