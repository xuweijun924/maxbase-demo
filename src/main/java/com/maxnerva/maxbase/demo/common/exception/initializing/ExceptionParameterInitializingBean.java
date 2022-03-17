package com.maxnerva.maxbase.demo.common.exception.initializing;

import cn.hutool.core.util.StrUtil;
import com.maxnerva.maxbase.common.exception.base.SystemParameterProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@Component
public class ExceptionParameterInitializingBean {

    @Value("${spring.application.name}")
    private String serviceName;

    public void setFromParameter() {
        if (StrUtil.isBlank(serviceName)) {
            throw new IllegalArgumentException("${spring.application.name} must not be blank");
        }
        SystemParameterProvider.getInstance().setServiceName(serviceName);
    }

}