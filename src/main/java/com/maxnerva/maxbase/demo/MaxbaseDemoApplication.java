package com.maxnerva.maxbase.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@SpringBootApplication
@MapperScan("com.maxnerva.maxbase.demo.mapper")
public class MaxbaseDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaxbaseDemoApplication.class, args);
    }

}
