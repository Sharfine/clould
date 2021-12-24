package com.sharfine.validate.testcode.thread;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Sharfine
 * @createTime: 2020/8/13 16:08
 */
public class MyContiner<T> {

    private final LinkedList<T> list = new LinkedList<>();

    private int maxSize = 10;

    private int munSize = 0;

    private Lock lock = new ReentrantLock();

    private Condition product = lock.newCondition();

    private Condition consumer = lock.newCondition();

    public static void main(String[] args) {

        MyContiner<String> myContiner = new MyContiner<>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int m = 0; m < 2; m++) {
                    myContiner.put("哈哈");
                }
            }).start();

        }

        for (int j = 0; j < 2; j++) {
            new Thread(() -> {

                for (int n = 0; n < 10; n++) {
                    myContiner.get();
                }
            }).start();
        }

    }

    void put(T t) {
        lock.lock();
        try {
            while (list.size() == maxSize) {
                System.out.println("库存已满");
                product.await();
            }
            list.add(t);
            System.out.println(Thread.currentThread().getName() + "产出一个哈哈");
            consumer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void get() {
        lock.lock();
        try {
            while (list.size() == munSize) {
                System.out.println("消费完了");
                consumer.await();
            }
            list.removeFirst();
            System.out.println(Thread.currentThread().getName() + "消费一个哈哈");
            product.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
