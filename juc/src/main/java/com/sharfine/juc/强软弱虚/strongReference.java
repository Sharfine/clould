package com.sharfine.juc.强软弱虚;

/**
 * @author: Sharfine
 * @createTime: 2022/3/7 14:11
 */
public class strongReference {
    public static void main(String[] args) {
        Object referent = new Object();

        /**
         * 通过赋值创建 StrongReference
         */
        Object strongReference = referent;

        System.out.println(referent == strongReference);

        referent = null;
        System.gc();

        /**
         * StrongReference 在 GC 后不会被回收
         */
        System.out.println(strongReference == null);
    }
}
