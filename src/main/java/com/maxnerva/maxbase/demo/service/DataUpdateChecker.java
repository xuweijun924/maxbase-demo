package com.maxnerva.maxbase.demo.service;

import com.maxnerva.maxbase.demo.common.exception.business.DataUpdateFailureException;

/**
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
public interface DataUpdateChecker {

    /**
     * 该方法用于 增删改 的最终检查，如果为 false 会抛出异常
     *
     * @param result 数据库操作结果
     * @param id     数据库操作关联 id
     * @throws DataUpdateFailureException 当 result 为 false 时，会抛出该异常
     */
    default void checkDataUpdate(boolean result, Long id) {
        if (!result) {
            // 主观异常
            throw new DataUpdateFailureException(id);
        }
    }

}
