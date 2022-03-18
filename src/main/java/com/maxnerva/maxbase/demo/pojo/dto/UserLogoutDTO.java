package com.maxnerva.maxbase.demo.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@Data
@ApiModel(description = "注销参数")
public class UserLogoutDTO {

    @ApiModelProperty(value = "Access Token", example = "eyJhbGc.iOiJSUzI.1NiJ9", required = true)
    @NotBlank
    private String accessToken;

}
