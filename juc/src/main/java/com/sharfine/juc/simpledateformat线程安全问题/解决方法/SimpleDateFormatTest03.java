package com.sharfine.juc.simpledateformat线程安全问题.解决方法;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author: Sharfine
 * @createTime: 2022/2/22 10:47
 */
//加锁
//需要注意的是，虽然这种方式能够解决SimpleDateFormat类的线程安全问题，但是由于在程序的执行过程中，为
//SimpleDateFormat类对象加上了synchronized锁，导致同一时刻只能有一个线程执行parse(String)方法。此时，会影响程序的执行
//性能，在要求高并发的生产环境下，此种方式也是不太推荐使用的。
public class SimpleDateFormatTest03 {
    private static final int EXECUTE_COUNT = 1000;
    //同时运行的线程数量
    private static final int THREAD_COUNT = 20;
    //SimpleDateFormat对象
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(THREAD_COUNT);
        final CountDownLatch countDownLatch = new CountDownLatch(EXECUTE_COUNT);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < EXECUTE_COUNT; i++){
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    try {
                        synchronized (simpleDateFormat){
                            simpleDateFormat.parse("2020-01-01");
                        }
                    } catch (ParseException e) {
                        System.out.println("线程：" + Thread.currentThread().getName() + " 格式化日期失败");
                                e.printStackTrace();
                        System.exit(1);
                    }catch (NumberFormatException e){
                        System.out.println("线程：" + Thread.currentThread().getName() + " 格式化日期失败");
                                e.printStackTrace();
                        System.exit(1);
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
