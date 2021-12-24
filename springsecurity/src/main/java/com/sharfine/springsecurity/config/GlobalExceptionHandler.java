package com.sharfine.springsecurity.config;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.sharfine.springsecurity.constant.AbstractBaseExceptionEnum;
import com.sharfine.springsecurity.constant.ApiStatus;
import com.sharfine.springsecurity.constant.CommonConstant;
import com.sharfine.springsecurity.constant.SymbolConstant;
import com.sharfine.springsecurity.entity.bo.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


/**
 * 全局异常处理器
 *
 * @author xuyuxiang, fengshuonan
 * @date 2020/3/18 19:03
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

/*

    */
/**
     * 拦截未知的运行时异常
     *
     * @author xuyuxiang
     * @date 2020/3/18 19:41
     *//*

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiResult serverError(Throwable e) {
        log.error(">>> 服务器运行异常，请求号为：", e);
        e.printStackTrace();
        return renderJson(e);
    }
*/

    /**
     * 拦截未知的运行时异常
     *
     * @author xuyuxiang
     * @date 2020/3/18 19:41
     */
    @ExceptionHandler(Exception.class)
    //@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult serverError(Exception e) {
        log.error(">>> 服务器运行异常，请求号为：", e);
        return ApiResult.error(ApiStatus.ERROR.getMsg(), e.getMessage());
    }

    /**
     * 渲染异常json
     *
     * @author stylefeng
     * @date 2020/5/5 16:22
     */
    private ApiResult renderJson(Integer code, String message) {
        return renderJson(code, message, null);
    }

    /**
     * 渲染异常json
     *
     * @author stylefeng
     * @date 2020/5/5 16:22
     */
    private ApiResult renderJson(AbstractBaseExceptionEnum baseExceptionEnum) {
        return renderJson(baseExceptionEnum.getCode(), baseExceptionEnum.getMessage(), null);
    }
    /**
     * 渲染异常json
     * <p>
     * 根据异常枚举和Throwable异常响应，异常信息响应堆栈第一行
     *
     * @author stylefeng
     * @date 2020/5/5 16:22
     */
    private ApiResult renderJson(Integer code, String message, Throwable e) {
        if (ObjectUtil.isNotNull(e)) {

            //获取所有堆栈信息
            StackTraceElement[] stackTraceElements = e.getStackTrace();

            //默认的异常类全路径为第一条异常堆栈信息的
            String exceptionClassTotalName = stackTraceElements[0].toString();

            //遍历所有堆栈信息，找到cn.stylefeng开头的第一条异常信息
            for (StackTraceElement stackTraceElement : stackTraceElements) {
                if (stackTraceElement.toString().contains(CommonConstant.DEFAULT_PACKAGE_NAME)) {
                    exceptionClassTotalName = stackTraceElement.toString();
                    break;
                }
            }
            return ApiResult.instance(ApiStatus.UNAUTHORIZED);
        } else {
            return ApiResult.instance(ApiStatus.UNAUTHORIZED);
        }
    }

    /**
     * 获取请求参数不正确的提示信息
     * <p>
     * 多个信息，拼接成用逗号分隔的形式
     *
     * @author stylefeng
     * @date 2020/5/5 16:50
     */
    private String getArgNotValidMessage(BindingResult bindingResult) {
        if (bindingResult == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();

        //多个错误用逗号分隔
        List<ObjectError> allErrorInfos = bindingResult.getAllErrors();
        for (ObjectError error : allErrorInfos) {
            stringBuilder.append(SymbolConstant.COMMA).append(error.getDefaultMessage());
        }

        //最终把首部的逗号去掉
        return StrUtil.removePrefix(stringBuilder.toString(), SymbolConstant.COMMA);
    }

}
