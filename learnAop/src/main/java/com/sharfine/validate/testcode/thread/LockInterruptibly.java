package com.sharfine.validate.testcode.thread;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Sharfine
 * @createTime: 2020/8/13 10:47
 */
public class LockInterruptibly {

    private static ReentrantLock lock = new ReentrantLock();
    private List<String> list = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();
        list.add(10000, "haha");


        list.add(5, "haha");
        Thread a = new Thread(() -> {
            try {
                lock.lockInterruptibly();
                System.out.println(Thread.currentThread().getName() + "获得锁");
                System.out.println(Thread.currentThread().getName() + " 开工干活");
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " : " + i);
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " 被中断");
                System.out.println(Thread.currentThread().getName() + " 做些别的事情");
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " 释放锁");
            }
        }, "线程1");


        Thread b = new Thread(() -> {

            try {
                lock.lockInterruptibly();
                System.out.println("获得锁");
                System.out.println(Thread.currentThread().getName() + " 开工干活");
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " : " + i);
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " 被中断");
                System.out.println(Thread.currentThread().getName() + " 做些别的事情");
            } finally {
                try {
                    lock.unlock();
                    System.out.println(Thread.currentThread().getName() + " 释放锁");
                } catch (Exception e) {

                    System.out.println(Thread.currentThread().getName() + " 结束线程");
                }
            }
        }, "线程2");

        //线程在sleep所以a.interrupt();和b.interrupt();直接叫醒这些线程，并到catch里面，最后结束线程
        //Thread.sleep(2000);
        a.start();

        b.start();
        Thread.sleep(2000);
        //此线程的sleep方法也会被打断从而结束线程
        //a.interrupt();
        //线程b在阻塞等待锁，直接唤醒b线程并抛出异常
        b.interrupt();
    }
}