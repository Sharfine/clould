package com.sharfine.juc.threadLocal;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Sharfine
 * @createTime: 2022/3/8 11:48
 */

public class Rehash {



    public static void main(String[] args) {
         final int HASH_INCREMENT = 0x61c88647;
         AtomicInteger nextHashCode =
                new AtomicInteger(1);
         final int threadLocalHashCode = nextHashCode.getAndAdd(HASH_INCREMENT);
        System.out.println(threadLocalHashCode);
        System.out.println(threadLocalHashCode);

    }

}
