package com.sharfine.juc.simpledateformat线程安全问题.解决方法;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 此种方式同样会影响高并发场景下的性能，不太建议在高并发的生产环境使用。
 * @author: Sharfine
 * @createTime: 2022/2/22 10:51
 */
public class Lock锁方式 {
    //执行总次数
    private static final int EXECUTE_COUNT = 1000;
    //同时运行的线程数量
    private static final int THREAD_COUNT = 20;
    //SimpleDateFormat对象
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    //Lock对象
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(THREAD_COUNT);
        final CountDownLatch countDownLatch = new CountDownLatch(EXECUTE_COUNT);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < EXECUTE_COUNT; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    lock.lock();
                    try {

                        simpleDateFormat.parse("2020-01-01");
                    } catch (ParseException | NumberFormatException e) {
                        System.out.println("线程：" + Thread.currentThread().getName() + " 格式化日期失 败");
                                e.printStackTrace();
                        System.exit(1);
                    } finally {
                        lock.unlock();
                    }
                    semaphore.release();
                } catch (InterruptedException e) {
                    System.out.println("信号量发生错误");
                    e.printStackTrace();
                    System.exit(1);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("所有线程格式化日期成功");
    }
}
