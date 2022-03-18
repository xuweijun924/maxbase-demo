package com.maxnerva.maxbase.demo.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * @author Shengxiang Xu
 * @date 3/18/2022
 */
@Data
@ApiModel(description = "登录参数")
public class UserLoginDTO {

    @ApiModelProperty(value = "帐号", example = "maxbase-demo_admin", required = true)
    @NotBlank
    private String username;

    @ApiModelProperty(value = "密码", example = "Foxconn123!", required = true)
    @NotBlank
    private String password;

    @ApiModelProperty(value = "授权类型", example = "password", required = true)
    @NotBlank
    private String grantType;

    @ApiModelProperty(value = "权限范围", example = "all", required = true)
    @NotBlank
    private String scope;

    @ApiModelProperty(value = "自定义参数：Map 结构，Key-Value 均为字符串")
    private Map<String, String> additional;

}
