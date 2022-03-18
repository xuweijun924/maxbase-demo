package com.maxnerva.maxbase.demo.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@Data
public class UserAccessTokenVO {

    @ApiModelProperty(value = "Access Token", example = "eyJhbGc.iOiJSUzI.1NiJ9")
    private String accessToken;

    @ApiModelProperty(value = "Refresh Token", example = "c8d2c2d1-ee6c-4c94-8175-f1ba7c1c8542")
    private String refreshToken;

    @ApiModelProperty(value = "Access Token 过期时间", example = "373646")
    private Integer expiresIn;

    @ApiModelProperty(value = "Token 类型", example = "bearer")
    private String tokenType;

    @ApiModelProperty(value = "权限范围", example = "all")
    private String scope;

    @ApiModelProperty(value = "过期提醒时间，有则显示，无则返null", example = "1642694400000", required = false)
    private Long expirationWarning;

}
