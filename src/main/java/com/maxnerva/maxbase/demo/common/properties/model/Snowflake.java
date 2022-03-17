package com.maxnerva.maxbase.demo.common.properties.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@Data
public class Snowflake {

    @NotNull
    private Integer workerId;
    @NotNull
    private Integer dataCenterId;

}
