package com.sharfine.validate;

/**
 * @author: Sharfine
 * @createTime: 2020/8/6 11:36
 */
public class test implements Runnable {

    private static int conut = 10;

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            test t = new test();
            new Thread(t, "THREAD" + i).start();
        }
    }

    @Override
    public void run() {
        conut--;
        System.out.println(Thread.currentThread().getName() + "count:" + conut);
    }
}
