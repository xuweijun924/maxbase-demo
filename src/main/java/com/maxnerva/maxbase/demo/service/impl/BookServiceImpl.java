package com.maxnerva.maxbase.demo.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.maxnerva.maxbase.common.base.service.BaseService;
import com.maxnerva.maxbase.common.dao.page.PageResult;
import com.maxnerva.maxbase.demo.dao.entity.BookEntity;
import com.maxnerva.maxbase.demo.delegate.BookDelegate;
import com.maxnerva.maxbase.demo.pojo.converter.BookConverter;
import com.maxnerva.maxbase.demo.pojo.dto.BookCreateDTO;
import com.maxnerva.maxbase.demo.pojo.dto.BookListDTO;
import com.maxnerva.maxbase.demo.pojo.dto.BookUpdateDTO;
import com.maxnerva.maxbase.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 书籍服务实现类。<br>
 * 注：该类会对应一个数据库表操作，因此，需要继承 {@lin BaseService}！
 *
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl extends BaseService<BookDelegate, BookEntity, Long> implements BookService {

    private final Snowflake snowflake;
    private final BookConverter bookConverter;

    @Override
    public Long create(BookCreateDTO bookCreateDTO) {
        BookEntity bookEntity = bookConverter.toBookEntity(bookCreateDTO);
        long bookId = snowflake.nextId();
        bookEntity.setId(bookId);
        checkDataUpdate(delegate.add(bookEntity), bookId);
        return bookId;
    }

    @Override
    public void delete(Long id) {
        checkDataUpdate(delegate.removeById(id), id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMultiple(List<Long> idList) {
        checkDataUpdate(delegate.removeByIds(idList), idList);
    }

    @Override
    public void update(Long id, BookUpdateDTO bookUpdateDTO) {
        BookEntity bookEntity = bookConverter.toBookEntity(bookUpdateDTO);
        bookEntity.setId(id);
        checkDataUpdate(delegate.edit(bookEntity), id);
    }

    @Override
    public BookEntity get(Long id) {
        return delegate.findOneById(id);
    }

    @Override
    public List<BookEntity> listAll() {
        return delegate.list();
    }

    @Override
    public PageResult<BookEntity> list(BookListDTO bookListDTO) {
        return delegate.list(bookListDTO);
    }

}
