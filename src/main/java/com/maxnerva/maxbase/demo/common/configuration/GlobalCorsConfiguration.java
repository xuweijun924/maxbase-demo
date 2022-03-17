package com.maxnerva.maxbase.demo.common.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域配置
 *
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@Configuration
public class GlobalCorsConfiguration {

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        // 是否允许发送Cookie
        config.setAllowCredentials(false);
        // 允许访问服务端的客户端域名
        config.addAllowedOrigin("*");
        // 允许访问服务端的客户端请求头
        config.addAllowedHeader("*");
        // 允许访问服务端的客户端请求方式
        config.addAllowedMethod("*");
        // 基于URL的CORS配置
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }

}