package com.maxnerva.maxbase.demo.common.exception.initializing;

import cn.hutool.core.util.StrUtil;
import com.maxnerva.maxbase.common.exception.base.SystemParameterProvider;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@Component
public class ExceptionParameterInitializingBean implements InitializingBean {

    @Value("${spring.application.name}")
    private String serviceName;

    @Override
    public void afterPropertiesSet() {
        if (StrUtil.isBlank(serviceName)) {
            throw new IllegalArgumentException("${spring.application.name} must not be blank");
        }
        // 用于配合 Maxbase-mini 脚手架显示异常信息（from，用于显示异常抛出的位置）
        SystemParameterProvider.getInstance().setServiceName(serviceName);
    }

}