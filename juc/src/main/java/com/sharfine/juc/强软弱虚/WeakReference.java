package com.sharfine.juc.强软弱虚;

import java.util.ArrayList;

/**
 * @author: Sharfine
 * @createTime: 2022/3/7 14:14
 */
public class WeakReference {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> arraylist = list;
        list.add("0");
        list.add("1");
        list.add("2");
        list = null;
        System.out.println(arraylist);

    }
}
