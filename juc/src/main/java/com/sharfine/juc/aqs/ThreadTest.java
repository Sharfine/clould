package com.sharfine.juc.aqs;

/**
 * @author: Sharfine
 * @createTime: 2022/3/30 11:47
 */
public class ThreadTest {

    public Thread getThread(ThreadTest threadTest){
        return new Thread(()->{
            synchronized (threadTest){
                try {
                    Thread.sleep(10000000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void hello(){
        System.out.println("hello");
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadTest threadTest = new ThreadTest();
        Thread thread = threadTest.getThread(threadTest);
        thread.start();
        Thread.sleep(1000L);
        System.out.println(thread.hashCode());

    }
}
