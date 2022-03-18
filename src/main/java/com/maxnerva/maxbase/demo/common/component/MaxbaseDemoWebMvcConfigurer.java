package com.maxnerva.maxbase.demo.common.component;

import com.maxnerva.maxbase.demo.common.interceptor.MaxbaseDemoInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 应用配置，如：Interceptor，ViewResolver，MessageConverter 等。
 *
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@Component
@RequiredArgsConstructor
public class MaxbaseDemoWebMvcConfigurer implements WebMvcConfigurer {

    private final MaxbaseDemoInterceptor maxbaseDemoInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(maxbaseDemoInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/v2/**",
                        "/swagger-ui.html/**",
                        "/favicon.ico",
                        "/",
                        "/csrf",
                        "/error",
                        "/doc.html");
    }

}
