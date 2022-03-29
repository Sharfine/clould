package com.sharfine.juc.aqs.reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Sharfine
 * @createTime: 2022/3/28 15:59
 */
public class ReentrantLockTest {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();

        Thread a = new Thread(() -> {
            reentrantLock.lock();
            try {
                Thread.sleep(100000000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A");
        a.start();



        Thread b = new Thread(() -> {
            //调试看源码
            reentrantLock.lock();
        }, "B");
        b.start();

    }
}
