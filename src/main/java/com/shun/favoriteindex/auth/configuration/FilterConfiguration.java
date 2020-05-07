package com.shun.favoriteindex.auth.configuration;

import com.shun.favoriteindex.auth.filter.AuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器配置
 */
@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean authFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new AuthFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
