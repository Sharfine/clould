package com.sharfine.validate.testcode.list;

import com.sharfine.validate.testcode.test.model.Dog;
import com.sharfine.validate.testcode.test.model.Wang;

import java.util.LinkedHashSet;

/**
 * @author: Sharfine
 * @createTime: 2020/8/24 10:25
 */
public class LinkedHsahSetTest {
    public static void main(String[] args) {
        LinkedHashSet<String> hashSet = new LinkedHashSet<>();

        hashSet.add("aa");
        hashSet.add("bb");
        hashSet.add("cc");
        hashSet.add("dd");

        hashSet.remove("bb");
    }
}
