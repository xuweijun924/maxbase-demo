package com.maxnerva.maxbase.demo.common.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    /**
     * 定义切点
     */
    @Pointcut("execution(* com.maxnerva.maxbase.demo.controller.*.*(..))")
    public void controllerMethods() {
    }

    /**
     * 记录“请求-响应”日志
     *
     * @param pjp
     * @return
     */
    @Around("controllerMethods()")
    public Object writeRequestLog(ProceedingJoinPoint pjp) throws Throwable {
        RequestInfoUtil.RequestInfo requestInfo = RequestInfoUtil.getRequestInfo(pjp);
        String requestUri = requestInfo.getRequestUri();
        long start = System.currentTimeMillis();

        // 接口请求时记录日志
        log.info("Preparing request, uri: {}, ip: {}, method: {}, param: {}",
                requestUri,
                requestInfo.getRequestUserIp(),
                requestInfo.getRequestMethod(),
                requestInfo.getRequestParam());

        // 执行Controller方法
        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Throwable throwable) {
            log.warn("Request failure, uri: " + requestUri);
            throw throwable;
        }

        // 正常响应时记录日志
        log.info("Request success, uri: {}, time: {}ms",
                requestUri,
                System.currentTimeMillis() - start);

        return result;
    }

}
