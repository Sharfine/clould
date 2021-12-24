package com.sharfine.validate.testcode.list;

import java.util.TreeSet;

/**
 * @author: Sharfine
 * @createTime: 2020/8/24 11:18
 */
public class TreeSetTest {
    public static void main(String[] args) {

        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("aa");
        treeSet.add("bb");
        treeSet.add("cc");
        treeSet.add("dd");
        treeSet.remove("bb");

        //new TreeSet<>(treeSet)


    }
}
