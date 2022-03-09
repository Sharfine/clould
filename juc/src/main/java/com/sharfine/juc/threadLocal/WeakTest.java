package com.sharfine.juc.threadLocal;

import java.lang.reflect.Field;

/**
 * @author: Sharfine
 * @createTime: 2022/3/7 15:32
 */
public class WeakTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        //有强引用
        ThreadLocal<Object> threadLocal = new ThreadLocal<>();
        threadLocal.set("aa");
        print("gc前");
        System.gc();
        print("gc后");

    }

    private static void print(String info) throws NoSuchFieldException, IllegalAccessException {
        Thread thread = Thread.currentThread();
        Class<? extends Thread> clz = thread.getClass();
        Field field = clz.getDeclaredField("threadLocals");
        field.setAccessible(true);
        Object threadLocalMap = field.get(thread);

        Class<?> tlmClass = threadLocalMap.getClass();
        Field tableField = tlmClass.getDeclaredField("table");
        tableField.setAccessible(true);
        Object[] arr = (Object[]) tableField.get(threadLocalMap);
        for (Object o : arr) {
            if (o != null) {
                Class<?> entryClass = o.getClass();
                Field valueField = entryClass.getDeclaredField("value");
                Field referenceField = entryClass.getSuperclass().getSuperclass().getDeclaredField("referent");
                valueField.setAccessible(true);
                referenceField.setAccessible(true);
                System.out.println(String.format(info + " - 弱引用key:%s,值:%s", referenceField.get(o), valueField.get(o)));
            }
        }
    }
}
