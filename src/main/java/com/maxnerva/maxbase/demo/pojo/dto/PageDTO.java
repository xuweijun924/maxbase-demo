package com.maxnerva.maxbase.demo.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页请求对象
 *
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@Data
public class PageDTO {

    public static final int DEFAULT_PAGE_NUM = 1;
    public static final int DEFAULT_PAGE_SIZE = 20;

    @ApiModelProperty(value = "第几页（默认为1）", example = DEFAULT_PAGE_NUM + "")
    private Integer pageNum;

    @ApiModelProperty(value = "每页显示记录数（默认为20）", example = DEFAULT_PAGE_SIZE + "")
    private Integer pageSize;

    public Integer getPageNum() {
        if (this.pageNum == null || this.pageNum < 1) {
            this.pageNum = DEFAULT_PAGE_NUM;
        }
        return this.pageNum;
    }

    public Integer getPageSize() {
        if (this.pageSize == null || this.pageSize < 1) {
            this.pageSize = DEFAULT_PAGE_SIZE;
        }
        return this.pageSize;
    }

}
