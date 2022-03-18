package com.maxnerva.maxbase.demo.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.maxnerva.maxbase.common.util.OAuth2ContextHolder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体公共属性
 *
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@Data
@EqualsAndHashCode(of = "id")
public abstract class AbstractEntity<ID extends Serializable> {

    @TableId
    private ID id;
    private Long createdBy;
    private Date createdDate;
    private Long lastModifiedBy;
    private Date lastModifiedDate;

    public void beforeInsertAction() {
        Long creator = OAuth2ContextHolder.getUserId();
        if (creator == null) {
            throw new IllegalArgumentException("creator 不能为空");
        }
        createdBy = creator;
        lastModifiedBy = creator;

        Date now = new Date();
        createdDate = now;
        lastModifiedDate = now;
    }

    public void beforeUpdateAction() {
        Long modifier = OAuth2ContextHolder.getUserId();
        if (modifier == null) {
            throw new IllegalArgumentException("modifier 不能为空");
        }
        lastModifiedBy = modifier;
        lastModifiedDate = new Date();
    }

}
