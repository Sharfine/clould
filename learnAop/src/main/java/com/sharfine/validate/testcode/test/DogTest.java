package com.sharfine.validate.testcode.test;

import com.sharfine.validate.testcode.test.model.Dog;
import lombok.Data;

/**
 * @author: Sharfine
 * @createTime: 2020/8/17 11:45
 */
public class DogTest {

    public static void main(String[] args) {
       /* Dog myDog = new Dog();
        myDog.setName("旺旺");

        rename(myDog);
//在一个方法里直接把引用给别的对象
//        myDog =  dogTest.new Dog();
//        myDog.setName("haha");
        System.out.println(myDog);
*/
    }

    private static void rename(Dog myDog) {
        //myDog =  new Dog();
        myDog.setName("haha");
        System.out.println(myDog);
    }

}
