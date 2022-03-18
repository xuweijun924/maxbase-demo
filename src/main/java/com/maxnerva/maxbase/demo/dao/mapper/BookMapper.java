package com.maxnerva.maxbase.demo.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maxnerva.maxbase.common.dao.mybatis.core.BaseAdvanceMapper;
import com.maxnerva.maxbase.demo.dao.entity.BookEntity;
import org.apache.ibatis.annotations.Param;

/**
 * MyBatis Mapper 接口，需要注意的是，为了配合 Delegate 的使用，该接口必须要继承 {@link BaseAdvanceMapper}！
 *
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
public interface BookMapper extends BaseAdvanceMapper<BookEntity> {

    IPage<BookEntity> list(@Param("iPage") Page<Object> iPage,
                           @Param("bookName") String bookName);

}
