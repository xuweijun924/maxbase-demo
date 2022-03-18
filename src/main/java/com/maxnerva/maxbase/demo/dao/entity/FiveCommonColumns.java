package com.maxnerva.maxbase.demo.dao.entity;

import com.maxnerva.maxbase.common.dao.mybatis.entity.MybatisEntityForLong;
import com.maxnerva.maxbase.common.util.OAuth2ContextHolder;

import java.util.Date;

/**
 * 维护 5CC 的属性和常用操作方法
 *
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
public abstract class FiveCommonColumns extends MybatisEntityForLong {

    public void beforeInsertAction() {
        Long creator = OAuth2ContextHolder.getUserId();
        if (creator == null) {
            throw new IllegalArgumentException("creator 不能为空");
        }

        setCreatedBy(creator);
        setLastModifiedBy(creator);

        Date now = new Date();
        setCreatedDate(now);
        setLastModifiedDate(now);
    }

    public void beforeUpdateAction() {
        Long modifier = OAuth2ContextHolder.getUserId();
        if (modifier == null) {
            throw new IllegalArgumentException("modifier 不能为空");
        }

        setLastModifiedBy(modifier);
        setLastModifiedDate(new Date());
    }

}
