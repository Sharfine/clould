package com.sharfine.sms.constant;

/**
 * 全局公共常量
 *
 * @author abm
 */
public final class CommonConstant {

    /**
     * 短信前缀
     */
    public static final String SMS_KEY_PREFIX = "SMS_AUTH_CODE_";

    /**
     * 邮箱前缀
     */
    public static final String EMAIL_KEY_PREFIX = "EMAIL_AUTH_CODE_";

    public static final String CHINA_AREA_CODE = "86";

    public static final String PHONE_REGEX = "^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$";

    public static final String EMAIL_REGEX = "^\\w+@[a-zA-Z0-9]+((\\.[a-z0-9A-Z]{1,})+)$";

    public static final String PASSWORD_REGEX = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,}$";

}
