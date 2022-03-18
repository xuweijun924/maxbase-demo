package com.maxnerva.maxbase.demo.common.exception.business;

import com.maxnerva.maxbase.common.exception.base.BusinessException;

/**
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
public class BossLoginException extends BusinessException {

    public BossLoginException() {
        super(ErrorConstant.BOSS_LOGIN,
                "Failed to get the BOSS token",
                null);
    }

}