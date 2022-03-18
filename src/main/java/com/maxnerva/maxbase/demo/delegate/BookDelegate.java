package com.maxnerva.maxbase.demo.delegate;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maxnerva.maxbase.common.base.service.BaseService;
import com.maxnerva.maxbase.common.dao.AbstractDelegate;
import com.maxnerva.maxbase.common.dao.core.annotation.ProxiedDelegateBean;
import com.maxnerva.maxbase.common.dao.delegate.BaseDelegate;
import com.maxnerva.maxbase.common.dao.page.PageResult;
import com.maxnerva.maxbase.demo.dao.entity.BookEntity;
import com.maxnerva.maxbase.demo.dao.mapper.BookMapper;
import com.maxnerva.maxbase.demo.pojo.dto.BookListDTO;

/**
 * Delegate 主要用于实现单表的业务操作（除了 {@link BaseService} 的公共操作方法）
 *
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@ProxiedDelegateBean
public abstract class BookDelegate
        extends AbstractDelegate<BookMapper>
        implements BaseDelegate<BookEntity, Long> {

    @Override
    public boolean add(BookEntity bookEntity) {
        bookEntity.beforeInsertAction();
        return getDao().insert(bookEntity) > 0;
    }

    public boolean edit(BookEntity bookEntity) {
        bookEntity.beforeUpdateAction();
        return getDao().updateById(bookEntity) > 0;
    }

    public PageResult<BookEntity> list(BookListDTO bookListDTO) {
        Page<Object> page = new Page<>(bookListDTO.getPageNum(), bookListDTO.getPageSize());
        IPage<BookEntity> iPage = getDao().list(page, bookListDTO.getBookName());
        return new PageResult<>(iPage.getTotal(), iPage.getRecords());
    }

}
