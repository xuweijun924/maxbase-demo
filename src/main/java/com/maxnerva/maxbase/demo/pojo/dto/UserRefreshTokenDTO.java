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
@ApiModel(description = "刷新 Token 参数")
public class UserRefreshTokenDTO {

    @ApiModelProperty(value = "Refresh Token", example = "c8d2c2d1-ee6c-4c94-8175-f1ba7c1c8542", required = true)
    @NotBlank
    private String refreshToken;

    @ApiModelProperty(value = "授权类型", example = "refresh_token", required = true)
    @NotBlank
    private String grantType;

    @ApiModelProperty(value = "权限范围", example = "all", required = true)
    @NotBlank
    private String scope;

}
