package com.maxnerva.maxbase.demo.common.log;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.json.JSONUtil;
import com.maxnerva.maxbase.demo.common.util.IpUtil;
import com.maxnerva.maxbase.demo.common.util.RequestUtil;
import lombok.Builder;
import lombok.Data;
import org.aspectj.lang.JoinPoint;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 从 Aspectj 的 JoinPoint 对象中获取请求信息
 *
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
public class RequestInfoUtil {

    /**
     * 过滤不检查的 Class 集合。
     * 1、ServletRequest不能序列化，从入参里排除，否则报异常：java.lang.IllegalStateException: It is illegal to call this method if the current request is not in asynchronous mode (i.e. isAsyncStarted() returns false)
     * 2、ServletResponse不能序列化 从入参里排除，否则报异常：java.lang.IllegalStateException: getOutputStream() has already been called for this response
     * 3、MultipartFile不能序列化，它虽说不会报异常，但都会转成null
     * 因此，也可以说明我们在Controller时尽量不要使用以上3个参数，MultipartFile在上传时必须，但ServletRequest、ServletResponse完全可以避免！
     */
    private static final List<Class> DEFAULT_EXCLUDED_CLASS_LIST = Arrays.asList(ServletRequest.class, ServletResponse.class, MultipartFile.class);

    private RequestInfoUtil() {
        throw new UnsupportedOperationException();
    }

    public static RequestInfo getRequestInfo(JoinPoint joinPoint) {
        return getRequestInfo(joinPoint, DEFAULT_EXCLUDED_CLASS_LIST);
    }

    public static RequestInfo getRequestInfo(JoinPoint joinPoint, List<Class> excludedClassList) {
        Validator.validateNotNull(joinPoint, "JoinPoint对象参数不能为空");

        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        String requestInterface = String.format("%s.%s()", className, methodName);
        String requestParam = setRequestParam(joinPoint, excludedClassList);
        Date requestDate = new Date();

        HttpServletRequest request = RequestUtil.getRequest();
        String requestMethod = null;
        String requestUri = null;
        String requestUserIp = null;
        if (request != null) {
            requestMethod = request.getMethod();
            requestUri = request.getRequestURI();
            requestUserIp = IpUtil.getVisitorIp(request);
        }

        return RequestInfo
                .builder()
                .className(className)
                .methodName(methodName)
                .requestInterface(requestInterface)
                .requestParam(requestParam)
                .requestDate(requestDate)

                .requestMethod(requestMethod)
                .requestUri(requestUri)
                .requestUserIp(requestUserIp)
                .build();
    }

    private static String setRequestParam(JoinPoint joinPoint, List<Class> excludedClassList) {
        // 将不能进行序列化的入参过滤掉，只要留下我们需要记录的入参参数记录到日志中即可。
        Object[] args = joinPoint.getArgs();
        Object[] arguments = new Object[args.length];
        label:
        for (int i = 0; i < args.length; i++) {
            if (CollectionUtil.isNotEmpty(excludedClassList)) {
                for (Class clazz : excludedClassList) {
                    if (clazz.isInstance(args[i])) {
                        continue label;
                    }
                }
            }
            arguments[i] = args[i];
        }
        String requestParam = "";
        if (arguments != null) {
            try {
                requestParam = JSONUtil.toJsonStr(arguments);
            } catch (Exception e) {
                requestParam = arguments.toString();
            }
        }
        return requestParam;
    }

    @Data
    @Builder
    public static class RequestInfo {

        /**
         * 目标执行类
         */
        private String className;
        /**
         * 目标执行方法
         */
        private String methodName;
        /**
         * 请求接口 = className + methodName
         */
        private String requestInterface;
        /**
         * 请求参数
         */
        private String requestParam;
        /**
         * 请求时间
         */
        private Date requestDate;

        /**
         * 请求方式：GET/POST/...
         */
        private String requestMethod;
        /**
         * 请求地址
         */
        private String requestUri;
        /**
         * 请求人IP
         */
        private String requestUserIp;

    }

}
