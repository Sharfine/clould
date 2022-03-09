package com.sharfine.juc.线程之间共享数据;

/**
 * @author: Sharfine
 * @createTime: 2022/3/9 11:45
 */
public class Bank {
    private volatile int count =0;//账户余额

    //存钱
    public  void addMoney(int money){
        count +=money;
        System.out.println(System.currentTimeMillis()+"存进："+money);
    }

    //取钱
    public  void subMoney(int money){
        if(count-money < 0){
            System.out.println("余额不足");
            return;
        }
        count -=money;
        System.out.println(+System.currentTimeMillis()+"取出："+money);
    }

    //查询
    public void lookMoney(){
        System.out.println("账户余额："+count);
    }
}
