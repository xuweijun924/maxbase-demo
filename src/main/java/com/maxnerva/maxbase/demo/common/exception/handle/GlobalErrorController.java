package com.maxnerva.maxbase.demo.common.exception.handle;

import com.maxnerva.maxbase.common.base.response.ExceptionResponse;
import com.maxnerva.maxbase.demo.common.exception.system.UnknownRuntimeException;
import com.maxnerva.maxbase.demo.common.util.RequestUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * 自定义错误页
 *
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class GlobalErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    /**
     * 出异常后进入该方法，交由下面的方法处理
     * <p>
     * 注：从 Spring Boot 2.3方法起，该方法已废弃，且已不再执行了。目前暂未删除原因是：有可能使用低版本的 Spring Boot。
     *
     * @return
     */
    @Override
    public String getErrorPath() {
        log.warn("Exception URI: {}", RequestUtil.getRequest().getRequestURI());
        return "/error";
    }

    /**
     * 出异常后异常响应
     *
     * @param webRequest
     * @return
     */
    @RequestMapping("/error")
    public ExceptionResponse handleError(WebRequest webRequest) {
        Map<String, Object> errorMap = this.errorAttributes.getErrorAttributes(webRequest, true);
        int status = (int) errorMap.get("status");
        String error = (String) errorMap.get("error");
        String trace = (String) errorMap.get("trace");

        String responseMessage = "An unexpected exception occurred. Reminder: Check the HTTP status code, please!";
        String exceptionMessage = String.format("异常状态码：%s，异常信息：%s，异常细节：%s", status, error, trace);
        return ExceptionResponseBuilder.build(responseMessage, new UnknownRuntimeException(exceptionMessage));
    }

}