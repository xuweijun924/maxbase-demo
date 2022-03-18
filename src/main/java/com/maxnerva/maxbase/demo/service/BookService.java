package com.maxnerva.maxbase.demo.service;

import com.maxnerva.maxbase.demo.pojo.dto.BookCreateDTO;
import com.maxnerva.maxbase.demo.pojo.dto.BookListDTO;
import com.maxnerva.maxbase.demo.pojo.dto.BookUpdateDTO;
import com.maxnerva.maxbase.demo.pojo.entity.BookEntity;
import com.maxnerva.maxbase.demo.pojo.vo.PageResult;

import java.util.List;

/**
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
public interface BookService {

    Long create(BookCreateDTO bookCreateDTO);

    void delete(Long id);

    void update(Long id, BookUpdateDTO bookUpdateDTO);

    BookEntity get(Long id);

    List<BookEntity> listAll();

    PageResult<BookEntity> list(BookListDTO bookListDTO);

}
