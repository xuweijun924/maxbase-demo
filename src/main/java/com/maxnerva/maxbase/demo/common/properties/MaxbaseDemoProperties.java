package com.maxnerva.maxbase.demo.common.properties;

import cn.hutool.json.JSONUtil;
import com.maxnerva.maxbase.demo.common.properties.model.Snowflake;
import com.maxnerva.maxbase.demo.common.properties.model.Swagger;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@Data
@Slf4j
@Validated
@Component
@RefreshScope
@ConfigurationProperties(prefix = "maxbase.demo")
public class MaxbaseDemoProperties {

    @NotNull
    private Snowflake snowflake;
    @NotNull
    private Swagger swagger;

    @EventListener
    public void onRefreshScopeRefreshed(final RefreshScopeRefreshedEvent event) {
        log.info("The project configuration is changed: {}", JSONUtil.toJsonStr(this));
    }

}