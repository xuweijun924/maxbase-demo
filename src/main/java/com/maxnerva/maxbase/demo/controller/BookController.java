package com.maxnerva.maxbase.demo.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.maxnerva.authorization.client.web.annotation.SecurityIgnore;
import com.maxnerva.maxbase.common.base.response.RestResponse;
import com.maxnerva.maxbase.demo.common.swagger.ExampleData;
import com.maxnerva.maxbase.demo.pojo.dto.BookCreateDTO;
import com.maxnerva.maxbase.demo.pojo.dto.BookListDTO;
import com.maxnerva.maxbase.demo.pojo.dto.BookUpdateDTO;
import com.maxnerva.maxbase.demo.pojo.entity.BookEntity;
import com.maxnerva.maxbase.demo.pojo.vo.PageResult;
import com.maxnerva.maxbase.demo.service.BookService;
import com.maxnerva.resource.client.authentication.web.annotation.MustCarryToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@Api(tags = "书籍管理接口")
@ApiImplicitParams({
        @ApiImplicitParam(
                name = "Authorization",
                value = "Access Token",
                example = ExampleData.AUTHORIZATION,
                paramType = "header",
                dataType = "string",
                required = true),
        @ApiImplicitParam(
                name = "Application-Code",
                value = "Application Code",
                example = ExampleData.APPLICATION_CODE,
                paramType = "header",
                dataType = "string",
                required = true),
})
@ApiSupport(order = 30)
@MustCarryToken
@SecurityIgnore
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("data/book")
public class BookController {

    private final BookService bookService;

    @ApiOperation(value = "添加书籍")
    @ApiOperationSupport(order = 10)
    // @Security("BOOK:INSERT")
    @PostMapping("create")
    public RestResponse<Long> create(@Validated @RequestBody BookCreateDTO bookCreateDTO) {
        Long bookId = bookService.create(bookCreateDTO);
        return RestResponse.<Long>builder().data(bookId);
    }

    @ApiOperation(value = "删除书籍")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "id",
                    value = "书籍 id",
                    example = ExampleData.BOOK_ID,
                    paramType = "path",
                    dataType = "Long",
                    required = true),
    })
    @ApiOperationSupport(order = 20)
    // @Security("BOOK:DELETE")
    @DeleteMapping("{id}/delete")
    public RestResponse<Void> delete(@NotNull @PathVariable Long id) {
        bookService.delete(id);
        return RestResponse.builder();
    }

    @ApiOperation(value = "修改书籍")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "id",
                    value = "书籍 id",
                    example = ExampleData.BOOK_ID,
                    paramType = "path",
                    dataType = "Long",
                    required = true),
    })
    @ApiOperationSupport(order = 30)
    // @Security("BOOK:UPDATE")
    @PutMapping("{id}/update")
    public RestResponse<Void> update(@NotNull @PathVariable Long id,
                                     @Validated @RequestBody BookUpdateDTO bookUpdateDTO) {
        bookService.update(id, bookUpdateDTO);
        return RestResponse.builder();
    }

    @ApiOperation(value = "查询书籍")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "id",
                    value = "书籍 id",
                    example = ExampleData.BOOK_ID,
                    paramType = "path",
                    dataType = "Long",
                    required = true),
    })
    @ApiOperationSupport(order = 40)
    // @Security("BOOK:GET")
    @GetMapping("{id}")
    public RestResponse<BookEntity> get(@NotNull @PathVariable Long id) {
        BookEntity bookEntity = bookService.get(id);
        return RestResponse.<BookEntity>builder().data(bookEntity);
    }


    @ApiOperation(value = "查询所有书籍")
    @ApiOperationSupport(order = 50)
    // @Security("BOOK:LIST_ALL")
    @GetMapping("list-all")
    public RestResponse<List<BookEntity>> listAll() {
        List<BookEntity> bookEntityList = bookService.listAll();
        return RestResponse.<List<BookEntity>>builder().data(bookEntityList);
    }

    @ApiOperation(value = "分页查询书籍")
    @ApiOperationSupport(order = 60)
    // @Security("BOOK:LIST")
    @GetMapping("list")
    public RestResponse<PageResult<BookEntity>> list(@Validated BookListDTO bookListDTO) {
        PageResult<BookEntity> pageResult = bookService.list(bookListDTO);
        return RestResponse.<PageResult<BookEntity>>builder().data(pageResult);
    }

}
