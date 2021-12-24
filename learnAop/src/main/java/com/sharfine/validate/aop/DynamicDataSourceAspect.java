package com.sharfine.validate.aop;

import com.sharfine.validate.annotation.DataSource;
import com.sharfine.validate.config.DataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 拦截controller里面所有方法，如果没有DataSource注解就用默认数据源，有哪个就注入哪个
 *
 * @author: Sharfine
 * @createTime: 2020/7/31 14:20
 */
@Component
@Aspect
@Order(1)//要让该切面调用先于AbstractRoutingDataSource的determineCurrentLookupKey()
public class DynamicDataSourceAspect {
    @Pointcut(value = "@annotation(com.sharfine.validate.annotation.DataSource)")
    public void pointcut() {

    }

    @Before(value = "pointcut()")
    public void before(JoinPoint point) {
        try {

            //获得当前访问的class
            Class<?> className = point.getTarget().getClass();
            //获得访问的方法名
            String methodName = point.getSignature().getName();
            //得到方法的参数的类型
            Class[] argClass = ((MethodSignature) point.getSignature()).getParameterTypes();

            String dataSource = "master";
            // 得到访问的方法对象
            try {
                Method method = className.getDeclaredMethod(methodName, argClass);
                // 判断是否存在@DS注解
                if (method.isAnnotationPresent(DataSource.class)) {
                    DataSource annotation = method.getAnnotation(DataSource.class);
                    // 取出注解中的数据源名
                    dataSource = annotation.name();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            DataSourceContextHolder.setDataSource(dataSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After(value = "pointcut()")
    public void after(JoinPoint point) {
        DataSourceContextHolder.clearDataSource();
    }
}
