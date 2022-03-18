package com.maxnerva.maxbase.demo.pojo.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页响应对象
 *
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@Data
@NoArgsConstructor
public class PageResult<T> {

    @ApiModelProperty(value = "总记录数")
    private Long total;
    @ApiModelProperty(value = "总页数")
    private Long pages;
    @ApiModelProperty(value = "数据列表")
    private List<T> list;

    public PageResult(IPage<T> page) {
        this.total = page.getTotal();
        this.pages = page.getPages();
        this.list = page.getRecords();
    }

}
