package com.tythac.webapierp.utils.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author CXY
 * @version Create Time: 2023/4/6
 * @Description 設定攔截器(攔截器的加載是在 springcontext 創建之前完成的。在這裡讓 bean 提前加載就可以注入成功-No OK)
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    // 加上攔截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login")
                .excludePathPatterns("/favicon.ico");   // icon、避免請求2次驗證
//        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
