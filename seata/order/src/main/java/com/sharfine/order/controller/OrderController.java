package com.sharfine.order.controller;

import com.sharfine.order.service.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Sharfine
 * @createTime: 2021/9/13 16:22
 */
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/order/create")
    public String save(){
        orderService.create();
        return "ok";
    }
}
