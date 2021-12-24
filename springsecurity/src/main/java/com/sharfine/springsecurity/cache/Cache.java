package com.sharfine.springsecurity.cache;


import com.sharfine.springsecurity.constant.CacheName;

/**
 * <p>
 * Cache接口
 * </p>
 *
 * @author sharfine
 * @since 2020-06-30
 */
public interface Cache {

    <T> T get(CacheName cacheName, String key, Class<T> clazz);

    void put(CacheName cacheName, String key, Object value);

    void remove(CacheName cacheName, String key);
}
