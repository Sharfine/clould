package com.sharfine.springsecurity.controller;


import com.sharfine.springsecurity.entity.bo.ApiResult;
import com.sharfine.springsecurity.entity.bo.LoginInfo;
import com.sharfine.springsecurity.service.AuthService;
import com.sharfine.springsecurity.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * <p>
 * 认证
 * </p>
 *
 * @author sharfine
 * @since 2020-06-30
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 登录方法
     * <p>
     * loginAccount：user
     * password：123456
     *
     * @param loginInfo
     * @return ApiResult
     */
    @PostMapping("/login")
    public ApiResult login(@Valid @RequestBody LoginInfo loginInfo) {
        return authService.login(loginInfo.getLoginAccount(), loginInfo.getPassword());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/hello")
    public ApiResult hello() {
        return ApiResult.ok("ok");
    }

    @PostMapping("/logout")
    public ApiResult logout() {
        return authService.logout();
    }

    @PostMapping("/refresh")
    public ApiResult refreshToken(HttpServletRequest request) {
        return authService.refreshToken(jwtUtil.getToken(request));
    }


}
