package com.maxnerva.maxbase.demo.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.maxnerva.maxbase.common.base.response.RestResponse;
import com.maxnerva.maxbase.demo.pojo.dto.UserLoginDTO;
import com.maxnerva.maxbase.demo.pojo.dto.UserLogoutDTO;
import com.maxnerva.maxbase.demo.pojo.vo.UserAccessTokenVO;
import com.maxnerva.maxbase.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@Api(tags = "用户 Token 接口")
@ApiSupport(order = 20)
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("data/user-token")
public class UserTokenController {

    private final UserService userService;

    @ApiOperation(value = "登录", notes = "登录成功后，获取 Access Token & Refresh Token")
    @ApiOperationSupport(order = 10)
    @PostMapping("login")
    public RestResponse<UserAccessTokenVO> login(@Validated @RequestBody UserLoginDTO userLoginDTO) {
        UserAccessTokenVO userAccessTokenVO = userService.login(userLoginDTO);
        return RestResponse.<UserAccessTokenVO>builder().data(userAccessTokenVO);
    }

    @ApiOperation(
            value = "注销",
            notes = "移除服务端的 Access Token（客户端也应清除，如不清除，在 Access Token 未过期时仍可调用接口）")
    @ApiOperationSupport(order = 20)
    @PostMapping("logout")
    public RestResponse<Void> logout(@Validated @RequestBody UserLogoutDTO userLogoutDTO) {
        userService.logout(userLogoutDTO);
        return RestResponse.builder();
    }

}
