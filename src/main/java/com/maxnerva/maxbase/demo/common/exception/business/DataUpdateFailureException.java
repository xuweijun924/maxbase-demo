package com.maxnerva.maxbase.demo.common.exception.business;

import com.maxnerva.maxbase.common.exception.base.BusinessException;

/**
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
public class DataUpdateFailureException extends BusinessException {

    public DataUpdateFailureException(Long id) {
        super(ErrorConstant.DATA_UPDATE_FAILURE,
                "Data update failure, id: {0}",
                id);
    }

}