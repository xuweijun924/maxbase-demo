package com.maxnerva.maxbase.demo.common.configuration;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Jackson 配置
 *
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@Configuration
public class JacksonConfiguration {

    /**
     * 解决在Spring Boot中Jackson导致Long型数据精度丢失问题：
     * https://orchidflower.oschina.io/2018/06/22/Handling-Bigint-using-Jackson-in-Springboot/
     *
     * @return
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder
                .serializerByType(Long.class, ToStringSerializer.instance)
                .serializerByType(Long.TYPE, ToStringSerializer.instance);
    }

}