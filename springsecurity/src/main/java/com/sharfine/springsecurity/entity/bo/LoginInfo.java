package com.sharfine.springsecurity.entity.bo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginInfo {

    @NotBlank(message = "登陆用户名为空")
    private String loginAccount;

    @NotBlank(message = "登陆密码为空")
    private String password;
}
