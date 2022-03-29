package com.sharfine.juc.aqs.LockSupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author: Sharfine
 * @createTime: 2022/3/28 15:59
 */
public class LockSupportTest1 implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " \t ======= 进入锁");
        LockSupport.park(this);
        System.out.println(Thread.currentThread().getName() + "\t ======== 被唤醒");
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable a = new LockSupportTest1();
        Thread thread = new Thread(a);
        thread.start();

        Thread b = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LockSupport.unpark(thread);
            System.out.println(a);
            System.out.println(Thread.currentThread().getName() + "\t ======== 通知了");
        }, "A");
        b.start();
        TimeUnit.SECONDS.sleep(10);

    }


}
