package com.sharfine.sms.service.impl;


import com.sharfine.sms.dp.TimeOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.function.Supplier;

/**
 * @author: Sharfine
 * @createTime: 2022/1/5 12:03
 */
public abstract class AuthCodeProvider {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void verifyAuthCode(String key, String authCode){

        String code = getAuthCode(key);
        if (code == null || !code.equals(authCode)) {
            throw new RuntimeException("error.commom.sms.wrong_auth_code");
        }
        deleteAuthCode(key);
    }

    public String generateAuthCode(String key, TimeOut timeOut, Supplier<String> strategy) {
        String authCode;
        if (strategy == null){
            int generateLength = 6;
            String generateSource = "0123456789";
            StringBuilder code = new StringBuilder();
            for (int i = 0; i < generateLength; i++) {
                String nowStr = String.valueOf(
                        generateSource.charAt((int) Math.floor(Math.random() * generateSource.length())));
                code.append(nowStr);
                generateSource = generateSource.replaceAll(nowStr, "");
            }
            authCode = code.toString();
        }else {
            authCode = strategy.get();
        }

        redisTemplate.opsForValue()
                .set(key, authCode, timeOut.getDuration(), timeOut.getTimeUnit());
        return authCode;
    }

    public String getAuthCode(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    public void deleteAuthCode(String key) {
         redisTemplate.delete(key);
    }

}
