package com.sharfine.juc.CallableTest;

/**
 * @author: Sharfine
 * @createTime: 2022/2/17 17:01
 */
public class TaskExecutor implements Runnable {
    private AsynResult<String> asynResult;
    private String taskParameter;

    public TaskExecutor(AsynResult<String> asynResult, String taskParameter) {
        this.asynResult = asynResult;
        this.taskParameter = taskParameter;
    }

    @Override
    public void run() {
        //TODO 一系列业务逻辑,将结果数据封装成TaskResult对象并返回
        asynResult.callable(taskParameter);
    }
}
