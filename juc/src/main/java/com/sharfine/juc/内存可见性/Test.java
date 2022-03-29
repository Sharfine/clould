package com.sharfine.juc.内存可见性;

/**
 * @author: Sharfine
 * @createTime: 2022/3/10 18:02
 */
public class Test {

    private long count = 0;

    private void addCount(){
        count ++;
    }
    public long execute() throws InterruptedException {
        Thread threadA = new Thread(() -> {
            for(int i = 0; i < 100000; i++){
                synchronized (this) {
                    addCount();
                }
            }
        });
        Thread threadB = new Thread(() -> {
            for(int i = 0; i < 100000; i++){
                synchronized (this) {
                    addCount();
                }
            }
        });
//启动线程
        threadA.start();
        threadB.start();
//等待线程执行完成
        threadA.join();
        threadB.join();
        return count;
    }
    public static void main(String[] args) throws InterruptedException {
        Test threadTest = new Test();
        long count = threadTest.execute();
        System.out.println(count);
    }

}
