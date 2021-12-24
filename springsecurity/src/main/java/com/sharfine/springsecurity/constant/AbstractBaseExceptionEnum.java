package com.sharfine.springsecurity.constant;

/**
 * 异常枚举格式规范
 *
 * @author stylefeng
 * @date 2017/12/17 22:22
 */
public interface AbstractBaseExceptionEnum {

    /**
     * 获取异常的状态码
     *
     * @return 状态码
     * @author stylefeng
     * @date 2020/7/9 14:28
     */
    Integer getCode();

    /**
     * 获取异常的提示信息
     *
     * @return 提示信息
     * @author stylefeng
     * @date 2020/7/9 14:28
     */
    String getMessage();

}
