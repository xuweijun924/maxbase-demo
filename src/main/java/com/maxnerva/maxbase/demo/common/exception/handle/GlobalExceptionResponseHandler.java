package com.maxnerva.maxbase.demo.common.exception.handle;

import cn.hutool.core.exceptions.ValidateException;
import com.maxnerva.maxbase.common.base.response.ExceptionResponse;
import com.maxnerva.maxbase.common.exception.base.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;

/**
 * 全局异常处理，该异常处理类为底层依赖 GlobalExceptionHandler 类的扩展。
 * <p>
 * 注：为避免冲突，该类不能再取名为 GlobalExceptionHandler！

 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionResponseHandler {

    /**
     * 请求参数校验失败的情况：
     * <p>
     * 一、MissingServletRequestParameterException
     * 捕捉参数缺失异常：用于@RequestParam(required = true)的较验
     * <p>
     * 二、ConstraintViolationException
     * 捕捉参数错误异常：Hibernate Validator对单个参数的较验失败
     * <p>
     * 三、BindException
     * 1、Hibernate Validator对对象参数的较验失败；
     * 2、对象属性为Date类型时，没有使用@DateTimeFormat声明传入的时间格式。
     * 注：对象参数校验优先于AOP，当校验失败发生BindException异常时，
     * 是看不到AOP输出的前置通知信息（"Preparing request，uri：xxx" ）的，
     * 只有在接口响应中能看到"参数校验失败"信息，因此，在开发时要特别注意。
     * <p>
     * 四、MethodArgumentNotValidException
     * org.springframework.web.bind.MethodArgumentNotValidException
     *
     * @param exception
     * @return
     */
    @ExceptionHandler({
            MissingServletRequestParameterException.class,
            ConstraintViolationException.class,
            BindException.class,
            MethodArgumentNotValidException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ExceptionResponse multiException1(Exception exception) {
        return ExceptionResponseBuilder.build(new BusinessException("Request parameter verification failed"));
    }

    /**
     * 参数类型转换异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler({
            MethodArgumentTypeMismatchException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ExceptionResponse multiException2(MethodArgumentTypeMismatchException exception) {
        return ExceptionResponseBuilder.build(new BusinessException("Parameter type conversion failed"));
    }

    // ---

    /**
     * 我们为 cn.hutool.core.exceptions.ValidateException 提供了异常判断，
     * 因此在内部参数校验时，尽量使用 cn.hutool.core.lang.Validator 工具类。
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(ValidateException.class)
    @ResponseStatus
    public ExceptionResponse exception(ValidateException exception) {
        return ExceptionResponseBuilder.build("Internal parameter verification failed", exception);
    }

    /**
     * 捕捉数据库的唯一约束异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus
    public ExceptionResponse exception(DuplicateKeyException exception) {
        String message = "The requested data already exists";
        String exceptionCauseMessage = exception.getCause().getMessage();
        exceptionCauseMessage = exceptionCauseMessage.substring("Duplicate entry '".length());
        exceptionCauseMessage = exceptionCauseMessage.substring(0, exceptionCauseMessage.indexOf("' for key '"));
        return ExceptionResponseBuilder.build(message + "：" + exceptionCauseMessage, exception);
    }

    /**
     * 空指针异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus
    public ExceptionResponse exception(NullPointerException exception) {
        return ExceptionResponseBuilder.build("A NullPointerException occurred", exception);
    }

}