package com.sharfine.springsecurity.component;


import com.sharfine.springsecurity.constant.ApiStatus;
import com.sharfine.springsecurity.entity.bo.ApiResult;
import com.sharfine.springsecurity.util.JacksonUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 用户未登录异常处理类
 * </p>
 * 当用户尝试访问需要权限才能的REST资源而不提供Token或者Token错误或者过期时，
 * 将调用此方法发送401响应以及错误信息
 *
 * @author sharfine
 * @since 2020-06-30
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().println(JacksonUtil.toJsonString(ApiResult.instance(ApiStatus.UNAUTHORIZED)));
        response.getWriter().flush();
    }
}
