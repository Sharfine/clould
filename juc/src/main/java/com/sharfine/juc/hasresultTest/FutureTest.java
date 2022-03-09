package com.sharfine.juc.hasresultTest;

import java.util.concurrent.*;

/**
 * @author: Sharfine
 * @createTime: 2022/2/21 9:42
 */
public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "测试Future获取异步结果";
            }
        });
        System.out.println(future.get());
        executorService.shutdown();
    }

}
