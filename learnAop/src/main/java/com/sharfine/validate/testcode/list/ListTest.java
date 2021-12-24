package com.sharfine.validate.testcode.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: Sharfine
 * @createTime: 2020/8/14 10:22
 */
public class ListTest {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("a");

        list.add("b");

        list.add("c");
        list.add("d");
        list.add("e");
        list.get(2);

        list.remove(2);


    }
}
