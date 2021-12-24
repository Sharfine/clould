package com.sharfine.validate.testcode.test.model;

import lombok.Data;

/**
 * @author: Sharfine
 * @createTime: 2020/8/17 11:56
 */
@Data
public class Dog {
    Dog(String name) {
        this.name = name;
    }

    private String name;

}
