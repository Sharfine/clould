package com.sharfine.juc;

/**
 * @author: Sharfine
 * @createTime: 2022/1/13 11:27
 */
public class TSynchronized implements Runnable{

    static int i = 0;

    public void increase(){
        i++;
    }


    @Override
    public void run() {
        for(int i = 0;i < 10000;i++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        TSynchronized tSynchronized = new TSynchronized();
        Thread aThread = new Thread(tSynchronized);
        Thread bThread = new Thread(tSynchronized);
        aThread.start();
        bThread.start();

        Thread.sleep(5000);
        System.out.println("i = " + i);
    }
}

