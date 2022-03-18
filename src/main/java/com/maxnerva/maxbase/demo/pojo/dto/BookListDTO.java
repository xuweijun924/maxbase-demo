package com.maxnerva.maxbase.demo.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@Data
@ApiModel(description = "分页查询书籍参数")
public class BookListDTO extends PageDTO {

    @ApiModelProperty(value = "书名", example = "三体")
    private String bookName;

}