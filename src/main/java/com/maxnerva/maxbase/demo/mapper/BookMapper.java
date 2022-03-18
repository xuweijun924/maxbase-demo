package com.maxnerva.maxbase.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maxnerva.maxbase.demo.pojo.entity.BookEntity;
import org.apache.ibatis.annotations.Param;

/**
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
public interface BookMapper extends BaseMapper<BookEntity> {

    IPage<BookEntity> list(@Param("iPage") Page<Object> iPage, @Param("bookName") String bookName);

}
