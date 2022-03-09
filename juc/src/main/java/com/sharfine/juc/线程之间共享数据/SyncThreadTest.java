package com.sharfine.juc.线程之间共享数据;

/**
 * @author: Sharfine
 * @createTime: 2022/3/9 11:45
 */
public class SyncThreadTest {

    public static void main(String args[]){
        final Bank bank=new Bank();

        Thread tadd=new Thread(() -> {

            while(true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
                bank.addMoney(100);
                bank.lookMoney();
                System.out.println("\n");

            }
        });

        Thread tsub = new Thread(() -> {

            while(true){
                bank.subMoney(100);
                bank.lookMoney();
                System.out.println("\n");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
            }
        });
        tsub.start();
        tadd.start();
    }
}
