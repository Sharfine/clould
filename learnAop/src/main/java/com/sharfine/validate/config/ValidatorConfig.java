package com.sharfine.validate.config;

import com.sharfine.validate.validator.aop.ParamValidateAop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Sharfine
 * @createTime: 2020/7/30 13:43
 */
@Configuration
public class ValidatorConfig {

    @Bean
    public ParamValidateAop paramValidateAop() {
        return new ParamValidateAop();
    }
}
