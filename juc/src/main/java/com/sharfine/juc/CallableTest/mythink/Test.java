package com.sharfine.juc.CallableTest.mythink;

/**
 * @author: Sharfine
 * @createTime: 2022/2/21 11:59
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        MyCallable myCallable = new MyCallable();

        Thread thread = new Thread(myCallable);

        thread.start();
        StringResult outCome = myCallable.getOutCome();
        System.out.println(outCome);
    }
}
