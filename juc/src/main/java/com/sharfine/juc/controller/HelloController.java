package com.sharfine.juc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Sharfine
 * @createTime: 2022/1/13 10:52
 */
@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello(String param) throws InterruptedException {
        System.out.println("开始-----" + param);
        Thread.sleep(5000);
        System.out.println("结束-------");
        return "ok";
    }
}
