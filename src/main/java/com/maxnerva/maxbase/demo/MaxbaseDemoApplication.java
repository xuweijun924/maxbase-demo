package com.maxnerva.maxbase.demo;

import com.maxnerva.maxbase.common.dao.core.annotation.ProxiedDelegateScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 应用启动类（应用入口）
 *
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@SpringBootApplication
@MapperScan("com.maxnerva.maxbase.demo.dao.mapper")
@ProxiedDelegateScan(basePackages = "com.maxnerva.maxbase.demo.delegate")
public class MaxbaseDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaxbaseDemoApplication.class, args);
    }

}
