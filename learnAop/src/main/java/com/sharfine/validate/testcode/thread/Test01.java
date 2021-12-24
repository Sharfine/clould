package com.sharfine.validate.testcode.thread;

import java.util.ArrayList;

/**
 * @author: Sharfine
 * @createTime: 2020/8/11 11:18
 * 注意要用一个对象，之前那种方法用了两个不同的对象导致结果为0
 */
public class Test01 {
    private static final Test01 test01 = new Test01();
    //可以不用volidte因为已经保证原子性
    int num = 0;

    @SuppressWarnings("a")
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Thread> list = new ArrayList<>();

        //Test01 test01 = new Test01();

        //Test01.TestThread testThread = test01.new TestThread();
        for (int i = 1; i <= 10; i++) {

            Thread thread = new Thread(test01::m);

            list.add(thread);
        }
        list.forEach(Thread::start);
        //main线程可能会先执行完，导致值不对所以必须要最后结束

        /*list.forEach(o-> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });*/
        System.out.println(test01.num);

        Thread.sleep(1000);

        System.out.println(test01.num);
    }

    synchronized void m() {
        for (int i = 0; i < 100000; i++) {
            num++;
        }

    }

}
