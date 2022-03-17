package com.maxnerva.maxbase.demo.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
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

    // TODO
    // private final UserService userService;

}
