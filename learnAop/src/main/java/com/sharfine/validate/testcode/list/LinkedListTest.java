package com.sharfine.validate.testcode.list;

import com.sharfine.validate.testcode.test.model.Dog;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: Sharfine
 * @createTime: 2020/8/18 19:55
 */
public class LinkedListTest {
    public static void main(String[] args) {

        List<Integer> list = new LinkedList<>();

        list.add(1);
        list.add(1);
        System.out.println(list.get(0));

        list.remove(1);

        boolean b = list.addAll(list);
    }
}
