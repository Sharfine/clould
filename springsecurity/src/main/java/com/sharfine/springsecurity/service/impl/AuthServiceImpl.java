package com.sharfine.springsecurity.service.impl;

import com.sharfine.springsecurity.entity.bo.AccessToken;
import com.sharfine.springsecurity.entity.bo.ApiResult;
import com.sharfine.springsecurity.cache.Cache;
import com.sharfine.springsecurity.constant.CacheName;
import com.sharfine.springsecurity.entity.UserDetail;
import com.sharfine.springsecurity.util.AuthUtil;
import com.sharfine.springsecurity.util.JwtUtil;
import com.sharfine.springsecurity.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private Cache caffeineCache;


    @Override
    public ApiResult login(String loginAccount, String password) {

        log.debug("进入login方法");
        // 1 创建UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken usernameAuthentication = new UsernamePasswordAuthenticationToken(loginAccount, password);
        // 2 认证
        Authentication authentication = this.authenticationManager.authenticate(usernameAuthentication);
        // 3 保存认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 4 生成自定义token
        AccessToken accessToken = jwtUtil.createToken((UserDetails) authentication.getPrincipal());

        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        // 放入缓存
        caffeineCache.put(CacheName.USER, userDetail.getUsername(), userDetail);
        return ApiResult.ok(accessToken);
    }

    @Override
    public ApiResult logout() {
        caffeineCache.remove(CacheName.USER, AuthUtil.getLoginAccount());
        SecurityContextHolder.clearContext();
        return ApiResult.ok();
    }

    @Override
    public ApiResult refreshToken(String token) {
        AccessToken accessToken = jwtUtil.refreshToken(token);
        UserDetail userDetail = caffeineCache.get(CacheName.USER, accessToken.getLoginAccount(), UserDetail.class);
        caffeineCache.put(CacheName.USER, accessToken.getLoginAccount(), userDetail);
        return ApiResult.ok(accessToken);
    }
}
