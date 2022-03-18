package com.maxnerva.maxbase.demo.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 数据表映射实体
 *
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@Data
@TableName("book")
public class BookEntity extends FiveCommonColumns {

    private String bookName;
    private String author;
    private String bookType;
    private String publishingHouse;
    private Date publishingDate;
    private BigDecimal sellPrice;

}