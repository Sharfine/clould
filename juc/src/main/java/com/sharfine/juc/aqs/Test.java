package com.sharfine.juc.aqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author: Sharfine
 * @createTime: 2022/3/18 10:17
 */
public class Test {
    public static void main(String[] args) {
        int N = 5;
        CyclicBarrier barrier  = new CyclicBarrier(N);
        Thread thread = new Thread();
        thread.start();
        for(int i=0;i<N-1;i++)
            new Writer(barrier).start();
    }
    static class Writer extends Thread{
        private CyclicBarrier cyclicBarrier;
        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程"+Thread.currentThread().getName()+"正在写入数据...");
            try {
                Thread.sleep(5000);      //以睡眠来模拟写入数据操作
                System.out.println("线程"+Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入完毕");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch(BrokenBarrierException e){
                e.printStackTrace();
            }
            System.out.println("所有线程写入完毕，继续处理其他任务...");
        }
    }
    /*
    * \
    *
● 小密圈（100%）
    ● 小密圈codeReview会议及代码修改（15%）
    ● 圈子聚合根增删改接口编写（20%）
    ● 圈子成员聚合根代码生成，代码补充调整（30%）
    ● 添加测试接口和国际化配置(10%)
    ● 测试接口是否调通(15%)
    ● 学习多线程相关知识（10%）
    *
    *
    * */
}
