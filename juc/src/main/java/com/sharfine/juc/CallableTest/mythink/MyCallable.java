package com.sharfine.juc.CallableTest.mythink;

/**
 * @author: Sharfine
 * @createTime: 2022/2/21 10:23
 */
public class MyCallable implements Runnable {

    public StringResult result;

    public StringResult getOutCome() {
        while (result == null){

        }
        return result;
    }

    public void setOutCome(StringResult result) {
        this.result = result;
    }

    @Override
    public void run() {
        String result = "结果";
        setOutCome(new StringResult(result));
    }

}
