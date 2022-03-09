package com.sharfine.sms.dp;


import com.sharfine.sms.constant.CommonConstant;
import com.sharfine.sms.enums.MobileRegularExp;
import lombok.Data;

/**
 * @author: Sharfine
 * @createTime: 2022/1/4 11:31
 */
@Data
public class Phone{
    public Phone(String phoneAreaCode, String number) {

        MobileRegularExp mobileRegularExp = MobileRegularExp.of(phoneAreaCode);
        this.regular = mobileRegularExp.getRegularExp();

        boolean matches = number.matches(this.regular);

        if (!matches) {
            throw new RuntimeException("error.commom.phone.wrong_format");
        }
        this.phoneAreaCode = phoneAreaCode;
        this.number = number;

    }

    public Phone(String phoneAreaCode, String number, String regular) {

        this.regular = regular;
        boolean matches = phoneAreaCode.matches(this.regular);

        if (!matches) {
            throw new RuntimeException("error.commom.phone.wrong_format");
        }
        this.phoneAreaCode = phoneAreaCode;
        this.number = number;

    }

    private String phoneAreaCode;

    private String number;

    private String regular;

    public String fullNumber() {
        return phoneAreaCode + number;
    }

    public String getCacheKey() {
        return CommonConstant.SMS_KEY_PREFIX + phoneAreaCode + number;
    }

}
