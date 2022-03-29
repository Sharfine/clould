package com.sharfine.juc.内存可见性;

/**
 * @author: Sharfine
 * @createTime: 2022/3/23 11:38
 */
public class VolatileTest3 {
    /*
     目标：演示可见性问题
       1. 创建一个共享变量
       2. 创建一条线程不断读取共享变量
       3. 创建一条线程修改共享变量
   */
    // 1. 创建一个共享变量；静态的成员变量；boolean类型名为flag；
    private static volatile boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
    /* 2. 创建一条线程不断读取共享变量
          采用lambda表达式的方式进行创建线程
    */
        new Thread(() -> {
            while (flag) {
            }
        }).start();
        /**
         沉睡两秒钟；
         让效果更加明显；
         这样则更加明显的来分析问题
         */
        Thread.sleep(2000);
        new Thread(() -> {
            // 将flag改为false；并且输出打印；
            flag = false;
            System.out.println("线程修改了变量的值为false");
        }).start();
    }

}
