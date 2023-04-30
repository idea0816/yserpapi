package com.tythac.webapierp.utils.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author CXY
 * @version Create Time: 2023/4/10
 * @Description 注入 CorsFilter 过滤器、使用过滤器就可以避免拦截器与全局跨域配置冲突问题
 */
@Configuration
public class CorsFilterConfiguration {

    @Bean
    public CorsFilter corsFilter() {
        // 创建 CorsConfiguration 对象后添加配置
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 设置放行哪些原始域
        corsConfiguration.addAllowedOrigin("*");
        // 放行哪些原始请求头部信息
        corsConfiguration.addAllowedHeader("*");
        // 放行哪些请求方法
        corsConfiguration.addAllowedMethod("*");

        // 添加映射路径
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(source);
    }

}
