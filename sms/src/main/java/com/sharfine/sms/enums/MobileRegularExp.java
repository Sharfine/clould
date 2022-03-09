package com.sharfine.sms.enums;


import lombok.Getter;

/**
 * @author: Sharfine
 * @createTime: 2022/1/5 16:04
 */
@Getter
public enum MobileRegularExp {

    CN("中国", "+86", "^(\\+?0?86\\-?)?1[345789]\\d{9}$"),
    TW("台湾", "+886", "^(\\+?886\\-?|0)?9\\d{8}$"),
    HK("香港", "+852", "^(\\+?852\\-?)?[569]\\d{3}\\-?\\d{4}$");

    /**
     * 国际名称
     */
    private String national;

    /**
     * 正则表达式
     */
    private String regularExp;

    private String areaCode;

    MobileRegularExp(String national, String areaCode, String regularExp) {
        this.national = national;
        this.regularExp = regularExp;
        this.areaCode = areaCode;
    }


    public static MobileRegularExp of(String areaCode) {

       MobileRegularExp[] values = MobileRegularExp.values();
        for (MobileRegularExp value : values) {
            if (value.getAreaCode().equals(areaCode)) {
                return value;
            }
        }
        throw new RuntimeException("error.commom.phone.wrong_area_code");
    }


}
