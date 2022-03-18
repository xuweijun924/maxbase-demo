package com.maxnerva.maxbase.demo.pojo.converter;

import com.maxnerva.maxbase.demo.dao.entity.BookEntity;
import com.maxnerva.maxbase.demo.pojo.dto.BookCreateDTO;
import com.maxnerva.maxbase.demo.pojo.dto.BookUpdateDTO;
import org.mapstruct.Mapper;

/**
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@Mapper(componentModel = "spring")
public interface BookConverter {

    BookEntity toBookEntity(BookCreateDTO bookCreateDTO);

    BookEntity toBookEntity(BookUpdateDTO bookUpdateDTO);

}