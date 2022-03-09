package com.sharfine.juc.CallableTest;

/**
 * @author: Sharfine
 * @createTime: 2022/2/17 16:58
 */
public interface AsynResult<T> {
    T callable(T t);

    String getResult();

}
