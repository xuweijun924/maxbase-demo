package com.maxnerva.maxbase.demo.service;

import com.maxnerva.maxbase.common.dao.page.PageResult;
import com.maxnerva.maxbase.demo.dao.entity.BookEntity;
import com.maxnerva.maxbase.demo.pojo.dto.BookCreateDTO;
import com.maxnerva.maxbase.demo.pojo.dto.BookListDTO;
import com.maxnerva.maxbase.demo.pojo.dto.BookUpdateDTO;

import java.util.List;

/**
 * 书籍服务
 *
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
public interface BookService extends DataUpdateChecker {

    Long create(BookCreateDTO bookCreateDTO);

    void delete(Long id);

    void deleteMultiple(List<Long> idList);

    void update(Long id, BookUpdateDTO bookUpdateDTO);

    BookEntity get(Long id);

    List<BookEntity> listAll();

    PageResult<BookEntity> list(BookListDTO bookListDTO);

}
