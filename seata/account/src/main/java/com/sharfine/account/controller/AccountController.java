package com.sharfine.account.controller;

import com.sharfine.account.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Sharfine
 * @createTime: 2021/8/21 15:45
 */
@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;
    @PostMapping("/account/save")
    public String save(){
        accountService.save();
        return "ok";
    }
}
