package com.maxnerva.maxbase.demo.common.exception.handle;

import com.maxnerva.maxbase.common.base.response.ExceptionResponse;
import com.maxnerva.maxbase.common.exception.ExceptionDetail;
import com.maxnerva.maxbase.common.exception.base.BaseException;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

/**
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@Slf4j
public class ExceptionResponseBuilder {

    private ExceptionResponseBuilder() {
        throw new UnsupportedOperationException();
    }

    public static ExceptionResponse build(BaseException exception) {
        ExceptionDetail exceptionDetail = ExceptionDetail
                .builder()
                .errorCode(exception.getErrorCode())
                .message(exception.getMessage())
                .messageKey(exception.getMessageKey())
                .arguments(exception.getArguments())
                .exceptionTraceId(UUID.randomUUID().toString())
                .build();

        log.warn(getMessageSummary(exceptionDetail), exception);

        return ExceptionResponse
                .builder()
                .exception(exceptionDetail);
    }

    public static ExceptionResponse build(String message, Exception exception) {
        String exceptionTraceId = UUID.randomUUID().toString();
        ExceptionDetail exceptionDetail = ExceptionDetail
                .builder()
                .message(message)
                .exceptionTraceId(exceptionTraceId)
                .build();

        log.error(getMessageSummary(exceptionDetail), exception);

        return ExceptionResponse
                .builder()
                .exception(exceptionDetail);
    }

    private static String getMessageSummary(ExceptionDetail exceptionDetail) {
        return String.format("Exception trace id -> %s", exceptionDetail.getExceptionTraceId());
    }

}
