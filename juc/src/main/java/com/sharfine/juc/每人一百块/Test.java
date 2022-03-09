package com.sharfine.juc.每人一百块;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author: Sharfine
 * @createTime: 2022/3/1 21:55
 */
public class Test {
    public static void main(String[] args) {
        ArrayList<Money> numbers = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            numbers.add(new Money(new BigDecimal(100)));
        }

        for (int i = 0; i < 200000; i++) {
            Random random = new Random();

            Money money2 = numbers.get(random.nextInt(numbers.size()));
            if (money2.getDecimal().compareTo(BigDecimal.ZERO) <= 0){
                numbers.remove(money2);
                continue;
            }
            money2.setDecimal(money2.getDecimal().subtract(BigDecimal.ONE));
            Money money = numbers.get(random.nextInt(numbers.size()));
            money.setDecimal(money.getDecimal().add(BigDecimal.ONE));


        }
        Thread a = new Thread(new MyRunable());
        a.start();

        System.out.println(numbers);
    }
}
