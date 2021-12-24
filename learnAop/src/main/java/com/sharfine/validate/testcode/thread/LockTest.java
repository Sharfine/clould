package com.sharfine.validate.testcode.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Sharfine
 * @createTime: 2020/8/12 11:19
 */
public class LockTest {
    private static ReentrantLock lock = new ReentrantLock();

    private static Condition condition = lock.newCondition();


    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获取到锁");
                System.out.println(Thread.currentThread().getName() + "等待并释放锁");
                condition.await();
                System.out.println(Thread.currentThread().getName() + "再次获得锁");
                System.out.println(Thread.currentThread().getName() + "结束");
            } catch (Exception e) {
                System.out.println("出现异常");
            } finally {

                lock.unlock();
            }

        }, "线程一").start();

        Thread.sleep(1000);

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获取到锁");
                condition.signal();
                System.out.println(Thread.currentThread().getName() + "唤醒在等待此条件的其他线程");
                System.out.println(Thread.currentThread().getName() + "结束");
            } catch (Exception e) {
                System.out.println("出现异常");
            } finally {
                lock.unlock();
            }

        }, "线程二").start();
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获取到锁");
                condition.signal();
                System.out.println(Thread.currentThread().getName() + "唤醒在等待此条件的其他线程");
                System.out.println(Thread.currentThread().getName() + "结束");
            } catch (Exception e) {
                System.out.println("出现异常");
            } finally {
                lock.unlock();
            }

        }, "线程三").start();
    }


}
