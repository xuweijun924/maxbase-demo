package com.maxnerva.maxbase.demo.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.maxnerva.maxbase.demo.service.BookService;
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
// TODO
@Api(tags = "书籍管理接口")
@ApiSupport(order = 30)
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("data/book")
public class BookController {

    private final BookService bookService;

}
