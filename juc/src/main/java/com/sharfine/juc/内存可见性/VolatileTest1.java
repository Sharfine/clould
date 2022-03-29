package com.sharfine.juc.内存可见性;

/**
 * @author: Sharfine
 * @createTime: 2022/3/23 11:38
 */

public class VolatileTest1 {
    static volatile boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (flag) {
//
            }
        });

        t1.start();
        Thread.sleep(100);
        flag = false;
        System.out.println("main end");
    }

}
