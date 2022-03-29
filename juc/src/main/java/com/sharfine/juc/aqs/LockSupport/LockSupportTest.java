package com.sharfine.juc.aqs.LockSupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author: Sharfine
 * @createTime: 2022/3/28 15:59
 */
public class LockSupportTest {
    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " \t ======= 进入锁");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t ======== 被唤醒");
        }, "A");
        a.start();



        Thread b = new Thread(() -> {
            LockSupport.unpark(a);
            System.out.println(Thread.currentThread().getName() + "\t ======== 通知了");
        }, "A");
        b.start();

    }
}
