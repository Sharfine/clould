package com.sharfine.juc.CallableTest;

/**
 * @author: Sharfine
 * @createTime: 2022/2/17 16:59
 */
public class TaskHandler implements AsynResult<String>{

    public String result;

    @Override
    public String getResult() {
        return result;
    }

    @Override
    public String callable(String s) {
        this.result = s;
        return s;
    }
}
