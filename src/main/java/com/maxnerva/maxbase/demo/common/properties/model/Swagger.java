package com.maxnerva.maxbase.demo.common.properties.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@Data
public class Swagger {

    @NotNull
    private Boolean enable;
    @NotBlank
    private String groupName;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String author;
    @NotBlank
    private String authorUrl;
    @NotBlank
    private String authorEmail;
    @NotBlank
    private String version;

}
