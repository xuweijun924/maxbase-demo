package com.maxnerva.maxbase.demo.common.context;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.map.MapUtil;
import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * 上下文数据管理抽象类
 *
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@Slf4j
public abstract class AbstractContextHolder {

    /**
     * 以TransmittableThreadLocal里放Map形式存放上下文信息
     * <p>
     * 注：如果需要保证线程池中传递值，需要修饰Runnable和Callable，
     * 详见：https://github.com/alibaba/transmittable-thread-local
     */
    private static final TransmittableThreadLocal<Map<String, Object>> THREAD_LOCAL = new TransmittableThreadLocal<>();

    /**
     * 放值
     *
     * @param key
     * @param value
     */
    protected static void set(String key, Object value) {
        validateKey(key);
        Validator.validateNotNull(value, "value不能为空");

        Map<String, Object> map = THREAD_LOCAL.get();
        if (map == null) {
            map = MapUtil.newHashMap();
            THREAD_LOCAL.set(map);
        }

        log.info("ThreadLocal.Map Put, Key: {}, Value: {}", key, value);
        map.put(key, value);
    }

    /**
     * 取值
     *
     * @param key
     * @return
     */
    protected static Object get(String key) {
        validateKey(key);
        Map<String, Object> map = THREAD_LOCAL.get();
        if (map == null) {
            throw new NullPointerException("No key exists，Key：" + key);
        }

        return map.get(key);
    }

    /**
     * 取所有值（即整个Map）
     *
     * @return
     */
    protected static Map<String, Object> getAll() {
        Map<String, Object> map = THREAD_LOCAL.get();
        if (map == null) {
            return Collections.emptyMap();
        }

        return map;
    }

    /**
     * 删值
     *
     * @param key
     */
    protected static void remove(String key) {
        validateKey(key);
        Map<String, Object> map = THREAD_LOCAL.get();
        if (map != null) {
            map.remove(key);
        }
    }

    /**
     * 删所有值
     */
    protected static void removeAll() {
        THREAD_LOCAL.remove();
    }

    private static void validateKey(String key) {
        Validator.validateNotEmpty(key, "key不能为空");
    }

}
