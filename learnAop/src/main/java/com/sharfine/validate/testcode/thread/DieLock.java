package com.sharfine.validate.testcode.thread;

/**
 * @author: Sharfine
 * @createTime: 2020/8/11 16:14
 */
public class DieLock {

    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (DieLock.class) {
                        System.out.println("t1获得DieLock锁");
                        synchronized (Object.class) {
                            System.out.println("t1获得Object锁");
                        }
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (Object.class) {
                        System.out.println("t2获得Object锁");
                        synchronized (DieLock.class) {
                            System.out.println("t2获得DieLock锁");
                        }
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }
}
