package com.maxnerva.maxbase.demo.common.component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * 应用初始化操作，在此实现一般有 2 种情况：<br>
 * 1. 属于应用全局的初始化处理（如：加载 DB 数据到内存）；<br>
 * 2. 多个初始化操作分散处理并执行时，无法保证初始化顺序，而集中在此处理则可解决。
 *
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@Component
@RequiredArgsConstructor
public class MaxbaseDemoInitializingBean implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        // ...
    }

}
