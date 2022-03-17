package com.maxnerva.maxbase.demo.common.context;

/**
 * 上下文管理实现类
 *
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
public class MaxbaseDemoContextHolder extends AbstractContextHolder {

    private static final String USER_ID = "USER_ID";

    public static void removeAll() {
        AbstractContextHolder.removeAll();
    }

    public static void setUserId(Long id) {
        set(USER_ID, id);
    }

    public static Long getUserId() {
        Object operator = get(USER_ID);
        return (Long) operator;
    }

}
