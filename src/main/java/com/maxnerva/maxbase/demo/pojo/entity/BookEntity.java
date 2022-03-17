package com.maxnerva.maxbase.demo.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@TableName("book")
public class BookEntity extends AbstractEntity<Long> {

    private String bookName;
    private String author;
    private String bookType;
    private String publishingHouse;
    private Date publishingDate;
    private BigDecimal sellPrice;

}