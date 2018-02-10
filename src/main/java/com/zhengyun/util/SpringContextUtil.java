package com.zhengyun.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by 听风 on 2018/2/10.
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    // Spring应用上下文环境
    private static ApplicationContext context;


    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    /**
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return context;
    }

    public static Object getBean(String name) throws BeansException {
        return context.getBean(name);
    }

    public static <T>T getBean(Class<T> clazz) throws BeansException {
        return context.getBean(clazz);
    }
}
