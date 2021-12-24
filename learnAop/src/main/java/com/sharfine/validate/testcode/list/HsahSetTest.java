package com.sharfine.validate.testcode.list;

import java.util.HashSet;

/**
 * @author: Sharfine
 * @createTime: 2020/8/24 9:38
 */
public class HsahSetTest {
    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();

        set.add("aaa");
        set.add("bbb");
        set.add("ccc");
        set.remove("aaa");
        set.forEach(System.out::println);
    }
}
