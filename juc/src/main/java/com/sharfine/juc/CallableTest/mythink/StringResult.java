package com.sharfine.juc.CallableTest.mythink;

import lombok.Data;

/**
 * @author: Sharfine
 * @createTime: 2022/2/21 11:21
 */
@Data
public class StringResult {
    private String result;

    public StringResult(String result) {
        this.result = result;
    }
}

