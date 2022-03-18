package com.maxnerva.maxbase.demo.common.interceptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Maxbase Demo 的拦截器
 *
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MaxbaseDemoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            log.info("The MaxbaseDemoInterceptor starts execution, url: {}", request.getRequestURL());
            return true;
        } catch (Exception exception) {
            log.error("The MaxbaseDemoInterceptor execution error!");
            throw exception;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler, Exception exception) {
        // do something...
    }

}