package com.haiyu.manager.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableCaching
public class MyWebMvcConfig implements WebMvcConfigurer {


    /**
     * 添加对用户是否登录的拦截器,并添加过滤项,排除项
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    }
}
