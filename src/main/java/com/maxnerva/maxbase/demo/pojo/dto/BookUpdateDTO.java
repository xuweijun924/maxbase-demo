package com.maxnerva.maxbase.demo.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@Data
@ApiModel(description = "修改书籍参数")
public class BookUpdateDTO {

    @ApiModelProperty(value = "书名", example = "三体", required = true)
    @NotBlank
    @Length(min = 1, max = 200)
    private String bookName;

    @ApiModelProperty(value = "作者", example = "刘慈欣", required = true)
    @NotBlank
    @Length(min = 1, max = 100)
    private String author;

    @ApiModelProperty(value = "分类", example = "科幻", required = true)
    @NotBlank
    @Length(min = 1, max = 50)
    private String bookType;

    @ApiModelProperty(value = "出版社", example = "重庆出版社", required = true)
    @NotBlank
    @Length(min = 1, max = 200)
    private String publishingHouse;

    @ApiModelProperty(value = "出版时间", example = "2010-11-01", required = true)
    @NotNull
    private Date publishingDate;

    @ApiModelProperty(value = "售价（元）", example = "93", required = true)
    @NotNull
    @Range(min = 0, max = 999999)
    private BigDecimal sellPrice;

}
