package com.example.config;

import com.example.common.interceptor.RedisSessionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置拦截器
 * @author wangqin
 * @version 1.0
 * @date 2019/11/27 11:18
 */
@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {

    @Bean
    public RedisSessionInterceptor getSessionInterceptor(){
        return new RedisSessionInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getSessionInterceptor()).addPathPatterns("/api/**").excludePathPatterns("/api/user/login");
    }

}
