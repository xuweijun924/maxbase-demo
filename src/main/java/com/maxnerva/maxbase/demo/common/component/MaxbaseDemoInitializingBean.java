package com.maxnerva.maxbase.demo.common.component;

import com.maxnerva.maxbase.demo.common.exception.initializing.ExceptionParameterInitializingBean;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * 应用初始化逻辑
 *
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@Component
@RequiredArgsConstructor
public class MaxbaseDemoInitializingBean implements InitializingBean {

    private final ExceptionParameterInitializingBean exceptionParameterInitializingBean;

    @Override
    public void afterPropertiesSet() throws Exception {
        exceptionParameterInitializingBean.setFromParameter();
        // ...
    }

}
