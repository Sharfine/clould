package com.sharfine.juc.aqs;

/**
 * @author: Sharfine
 * @createTime: 2022/3/29 16:13
 */
public class Test1 {
    public static void main(String[] args) {
        int a = -2;
        System.out.println(a >> 1);
        System.out.println(a >>> 1);

        int count = 10;
        --count;
        System.out.println(count);
    }
}
