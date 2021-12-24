package com.sharfine.validate.testcode.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Sharfine
 * @createTime: 2020/8/11 11:48
 */
public class T04_VolatileNotSync {
    volatile int count = 0;

    public static void main(String[] args) {
        T04_VolatileNotSync t = new T04_VolatileNotSync();

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::m, "thread-" + i));
        }

        threads.forEach((o) -> o.start());

        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(t.count);


    }

    synchronized void m() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

}
