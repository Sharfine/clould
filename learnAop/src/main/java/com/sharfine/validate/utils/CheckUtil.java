package com.sharfine.validate.utils;

import cn.hutool.core.util.StrUtil;
import com.sharfine.validate.validator.BaseValidatingParam;


import java.util.stream.Stream;

/**
 * @author: Sharfine
 * @createTime: 2020/7/30 14:06
 * 校验自定义参数是否为空
 */
public class CheckUtil {

    public static void validateParams(Object[] methodParams) {
        Stream.of(methodParams).forEach(methodParam -> {
                    if (methodParam instanceof BaseValidatingParam) {
                        BaseValidatingParam baseValidatingParam = (BaseValidatingParam) methodParam;
                        //验证是哪种校验
                        //按照业务继承BaseValidatingParam并各自写校验逻辑
                        String checkResult = baseValidatingParam.checkParam();
                        //如果校验结果不为空，则代表参数校验有空的或者不符合规则的，并且checkResult为参数错误的提示信息
                        if (StrUtil.isNotEmpty(checkResult)) {
                            throw new RuntimeException(checkResult);
                        }

                    }
                }
        );
    }
}
