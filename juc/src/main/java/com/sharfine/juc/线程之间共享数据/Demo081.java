package com.sharfine.juc.线程之间共享数据;

/**
 * @author: Sharfine
 * @createTime: 2022/3/9 11:40
 */
public class Demo081 {
    public static void main(String[] args) {
        //创建线程任务对象

        Ticket ticket = new Ticket();
        //创建三个窗口对象
        Thread t1 = new Thread(ticket, "窗口1");
        Thread t2 = new Thread(ticket, "窗口2");
        Thread t3 = new Thread(ticket, "窗口3");
        //卖票
        t1.start();
        t2.start();
        t3.start();
    }
    static class Ticket implements Runnable {
        private int ticket = 10;
        public void run() {
            String name = Thread.currentThread().getName();
            while (true) {
                sell(name);
                if (ticket <= 0) {
                    break;
                }
            }
        }
        private synchronized void sell(String name) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }if (ticket > 0) {
                System.out.println(name + "卖票：" + ticket);
                ticket--;
            }
        }
    }
}
