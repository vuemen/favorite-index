package com.shun.favoriteindex.datasource.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.shun.favoriteindex.datasource.FiDataSource;
import com.shun.favoriteindex.util.encrypt.FiEncrypt;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据源相关配置
 */
@Configuration
public class DataSourceConfig {

    private static String USERNAME_DRUID = "admin";
    private static String PASSWORD_DRUID = "3P97uUVDU9g=";

    /**
     * 配置数据源
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public FiDataSource dataSource() {
        FiDataSource dataSource = new FiDataSource();
        return dataSource;
    }

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        Map<String, String> initParameters = new HashMap<String, String>();
        // 用户名
        initParameters.put("loginUsername", USERNAME_DRUID);
        // 密码
        initParameters.put("loginPassword", FiEncrypt.decrypt(PASSWORD_DRUID));
        // 禁用HTML页面上的“Reset All”功能
        initParameters.put("resetEnable", "false");
        // IP白名单 (没有配置或者为空，则允许所有访问)
        initParameters.put("allow", "");
        // IP黑名单 (存在共同时，deny优先于allow)
        initParameters.put("deny", "");
        servletRegistrationBean.setInitParameters(initParameters);
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        // 排除不需要监控的资源
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

}
