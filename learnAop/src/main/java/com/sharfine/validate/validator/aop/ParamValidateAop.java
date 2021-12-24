package com.sharfine.validate.validator.aop;


import com.sharfine.validate.utils.CheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author: Sharfine
 * @createTime: 2020/7/30 13:46
 */
@Aspect
//@Order(AopSortConstants.PARAM_VALIDATE_AOP_SORT)
@Slf4j
public class ParamValidateAop {
    @Pointcut(value = "execution(* com.sharfine.validate.controller.ValidController.*(..))")
    private void cutService() {
    }

    @Around("cutService()")
    public Object doInvoke(ProceedingJoinPoint point) throws Throwable {

        Object[] methodParams = point.getArgs();

        if (methodParams == null || methodParams.length <= 0) {

            return point.proceed();
        } else {
            //如果参数中，包含BaseValidatingParam的子类就开始校验参数
            CheckUtil.validateParams(methodParams);
            return point.proceed();

        }
    }
}
