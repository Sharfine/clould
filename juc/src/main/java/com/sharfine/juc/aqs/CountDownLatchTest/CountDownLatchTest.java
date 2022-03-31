package com.sharfine.juc.aqs.CountDownLatchTest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * @author: Sharfine
 * @createTime: 2022/3/2 11:42
 */
@Slf4j
public class CountDownLatchTest {
    private static final int threadCount = 200;
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int i = 0; i<5 ;i++){
            new Thread(()->{
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();


            }).start();
        }

        new Thread(()->{
            try {
                countDownLatch.await();
                System.out.println("----1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "A").start();
        Thread.sleep(10);
        new Thread(()->{
            try {
                countDownLatch.await();
                System.out.println("----2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "B").start();
        Thread.sleep(1000000);
        System.out.println("---");
    }

}
