package com.maxnerva.maxbase.demo.service.impl;

import com.maxnerva.maxbase.demo.mapper.BookMapper;
import com.maxnerva.maxbase.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;

}
