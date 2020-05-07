package com.shun.favoriteindex.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * 上下文 即容器
 */
public class FiApplicationContext implements ApplicationContextAware, ServletContextAware {

    private static ApplicationContext applicationContext;

    private static ServletContext servletContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        FiApplicationContext.applicationContext = applicationContext;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        FiApplicationContext.servletContext = servletContext;
    }

    public static <T> T getBean(String beanId) {
        return FiApplicationContext.getBean(beanId);
    }

    public static <T> T getBeanByType(Class<T> classType) {
        return FiApplicationContext.getBeanByType(classType);
    }
}
