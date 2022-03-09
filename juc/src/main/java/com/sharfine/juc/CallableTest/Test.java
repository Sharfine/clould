package com.sharfine.juc.CallableTest;

/**
 * @author: Sharfine
 * @createTime: 2022/2/17 17:03
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        AsynResult<String> taskCallable = new TaskHandler();
        TaskExecutor taskExecutor = new TaskExecutor(taskCallable, "测试回调任务");
        Thread thread = new Thread(taskExecutor);
        thread.start();
        thread.join();
        String result = taskCallable.getResult();
        System.out.println(result);

    }

}
