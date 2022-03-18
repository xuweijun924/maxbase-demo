package com.maxnerva.maxbase.demo.common.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * RequestUtil：用于 Spring 环境下获取原生的请求对象 HttpServletRequest
 *
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
public class RequestUtil {

    private RequestUtil() {
        throw new UnsupportedOperationException();
    }

    /**
     * 从Spring 容器中获取原生的Request对象
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        try {
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        } catch (Exception e) {
            // 非Web请求，如：RPC请求
            return null;
        }
    }

    /**
     * 获取Request Body，该方法需要HttpServletRequestWrapper的支持
     *
     * @return
     */
    public static String getBody() {
        try {
            return getRequest()
                    .getReader()
                    .lines()
                    .collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            throw new RuntimeException("获取Request Body出错", e);
        }
    }

    /**
     * 获取访问者IP
     *
     * @return
     */
    public static String getIp() {
        return IpUtil.getVisitorIp(getRequest());
    }

}