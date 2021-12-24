package com.sharfine.validate.testcode.thread;

import lombok.SneakyThrows;

/**
 * @author: Sharfine
 * @createTime: 2020/8/11 10:12
 */
public class ThreadTest {

    private final int maxSize = 10;
    private int size = 0;

    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest();
        ThreadTest.Producter producter = threadTest.new Producter();
        ThreadTest.Consumer consumer = threadTest.new Consumer();
        producter.start();

        consumer.start();
    }


    class Producter extends Thread {
        @SneakyThrows
        @Override
        public void run() {
            producer();
        }

        private void producer() throws InterruptedException {
            while (true) {

                while (size < maxSize) {
                    synchronized (ThreadTest.class) {
                        size++;
                        Thread.sleep(500);
                        System.out.println("当前为生产" + size);
                    }
                }
                System.out.println("队列满，等待消费");

                Thread.yield();
            }

        }
    }

    class Consumer extends Thread {

        @SneakyThrows
        @Override
        public void run() {
            consumer();
        }

        private synchronized void consumer() throws InterruptedException {
            while (true) {
                while (size > 0) {
                    synchronized (ThreadTest.class) {
                        size--;
                        Thread.sleep(500);
                        System.out.println("当前为消费" + size);
                    }
                }
                System.out.println("队列空，等待数据");

                Thread.yield();
            }

        }
    }

}
