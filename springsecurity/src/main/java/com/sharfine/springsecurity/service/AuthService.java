package com.sharfine.springsecurity.service;


import com.sharfine.springsecurity.entity.bo.ApiResult;

public interface AuthService {

    ApiResult login(String loginAccount, String password);

    ApiResult logout();

    ApiResult refreshToken(String token);
}
