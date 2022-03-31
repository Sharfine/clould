package com.sharfine.juc.aqs.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author: Sharfine
 * @createTime: 2022/3/31 14:08
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);


        new Thread(()->{
            try {
                semaphore.acquire();
                Thread.sleep(10000L);
                System.out.println("release");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();


        new Thread(()->{
            try {
                semaphore.acquire();
                Thread.sleep(10000000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();


        new Thread(()->{
            try {
                semaphore.acquire();
                Thread.sleep(10000000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"C").start();
    }
}
