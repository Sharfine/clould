package com.sharfine.sms.dp;

import com.sharfine.sms.constant.CommonConstant;
import lombok.Data;

/**
 * @author: Sharfine
 * @createTime: 2022/1/4 11:46
 */
@Data
public class Email {

    public Email(String number){

        if (!number.matches(CommonConstant.EMAIL_REGEX)){
            throw new RuntimeException("error.commom.email.wrong_format");
        }
        this.number = number;
    }

    private String number;


    public String getCacheKey() {
        return CommonConstant.EMAIL_KEY_PREFIX  + number;
    }
}
