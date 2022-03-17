package com.maxnerva.maxbase.demo.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.maxnerva.maxbase.common.base.response.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@Api(tags = "测试接口")
@ApiSupport(order = 10)
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("data/test")
public class TestController {

    @ApiOperation(value = "服务连通性测试", notes = "无任何请求参数，直接执行即可")
    @GetMapping("index")
    public RestResponse<Date> index() {
        return RestResponse.<Date>builder().data(new Date());
    }

}
