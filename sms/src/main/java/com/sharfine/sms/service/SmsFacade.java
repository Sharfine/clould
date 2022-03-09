package com.sharfine.sms.service;


import com.sharfine.sms.dp.Phone;
import com.sharfine.sms.dp.TimeOut;

import java.util.function.Supplier;

/**
 * @author: Sharfine
 * @createTime: 2022/1/4 21:21
 */
public interface SmsFacade {

    void sendPhoneAuthCode(Phone phone, TimeOut timeOut, Supplier<String> strategy);

    /**
     * @param key 缓存key
     * @param authCode 验证码
     */
    void verifyAuthCode(String key, String authCode);

}
