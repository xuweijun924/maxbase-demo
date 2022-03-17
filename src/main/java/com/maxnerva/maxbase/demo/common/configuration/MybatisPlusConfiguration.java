package com.maxnerva.maxbase.demo.common.configuration;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.maxnerva.maxbase.demo.common.properties.MaxbaseDemoProperties;
import com.maxnerva.maxbase.demo.common.properties.model.Snowflake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis-Plus 配置
 *
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@Slf4j
@Configuration
public class MybatisPlusConfiguration {

    @Autowired
    private MaxbaseDemoProperties maxbaseDemoProperties;

    /**
     * 内置分页插件配置
     *
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    /**
     * 内置雪花算法配置，以辅助数据的ID生成
     *
     * @return
     */
    @Bean
    public IdentifierGenerator idGenerator() {
        Snowflake snowflake = maxbaseDemoProperties.getSnowflake();
        return new DefaultIdentifierGenerator(snowflake.getWorkerId(), snowflake.getDataCenterId());
    }

}