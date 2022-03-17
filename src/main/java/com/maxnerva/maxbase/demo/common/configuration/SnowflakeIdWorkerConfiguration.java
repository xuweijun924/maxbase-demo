package com.maxnerva.maxbase.demo.common.configuration;

import com.maxnerva.maxbase.demo.common.properties.MaxbaseDemoProperties;
import com.maxnerva.maxbase.demo.common.properties.model.Snowflake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 雪花算法配置
 *
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@Slf4j
@Configuration
public class SnowflakeIdWorkerConfiguration {

    @Autowired
    private MaxbaseDemoProperties maxbaseDemoProperties;

    @Bean
    public cn.hutool.core.lang.Snowflake snowflakeIdWorker() {
        Snowflake snowflake = maxbaseDemoProperties.getSnowflake();
        Integer workerId = snowflake.getWorkerId();
        Integer dataCenterId = snowflake.getDataCenterId();
        log.info("Snowflake workerId: {}, dataCenterId: {}", workerId, dataCenterId);
        return new cn.hutool.core.lang.Snowflake(workerId, dataCenterId);
    }

}
