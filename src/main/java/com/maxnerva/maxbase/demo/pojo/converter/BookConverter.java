package com.maxnerva.maxbase.demo.pojo.converter;

import com.maxnerva.maxbase.demo.pojo.dto.BookCreateDTO;
import com.maxnerva.maxbase.demo.pojo.dto.BookUpdateDTO;
import com.maxnerva.maxbase.demo.pojo.entity.BookEntity;
import org.mapstruct.Mapper;

/**
 * @author Shengxiang Xu
 * @date 3/18/2022
 */
@Mapper(componentModel = "spring")
public interface BookConverter {

    BookEntity toBookEntity(BookCreateDTO bookCreateDTO);

    BookEntity toBookEntity(BookUpdateDTO bookUpdateDTO);

}