package com.maxnerva.maxbase.demo.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.maxnerva.maxbase.demo.mapper.BookMapper;
import com.maxnerva.maxbase.demo.pojo.converter.BookConverter;
import com.maxnerva.maxbase.demo.pojo.dto.BookCreateDTO;
import com.maxnerva.maxbase.demo.pojo.dto.BookListDTO;
import com.maxnerva.maxbase.demo.pojo.dto.BookUpdateDTO;
import com.maxnerva.maxbase.demo.pojo.entity.BookEntity;
import com.maxnerva.maxbase.demo.pojo.vo.PageResult;
import com.maxnerva.maxbase.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final Snowflake snowflake;
    private final BookConverter bookConverter;
    private final BookMapper bookMapper;

    @Override
    public Long create(BookCreateDTO bookCreateDTO) {
        BookEntity bookEntity = bookConverter.toBookEntity(bookCreateDTO);
        long bookId = snowflake.nextId();
        bookEntity.setId(bookId);
        bookEntity.beforeInsertAction();
        bookMapper.insert(bookEntity);
        return bookId;
    }

    @Override
    public void delete(Long id) {
        bookMapper.deleteById(id);
    }

    @Override
    public void update(Long id, BookUpdateDTO bookUpdateDTO) {
        BookEntity bookEntity = bookConverter.toBookEntity(bookUpdateDTO);
        bookEntity.setId(id);
        bookEntity.beforeUpdateAction();
        bookMapper.updateById(bookEntity);
    }

    @Override
    public BookEntity get(Long id) {
        return bookMapper.selectById(id);
    }

    @Override
    public List<BookEntity> listAll() {
        return bookMapper.selectList(null);
    }

    @Override
    public PageResult<BookEntity> list(BookListDTO bookListDTO) {
        Page<Object> page = new Page<>(bookListDTO.getPageNum(), bookListDTO.getPageSize());
        IPage<BookEntity> iPage = bookMapper.list(page, bookListDTO.getBookName());
        return new PageResult<>(iPage);
    }

}
