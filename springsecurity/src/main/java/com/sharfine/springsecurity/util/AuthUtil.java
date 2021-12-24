package com.sharfine.springsecurity.util;


import com.sharfine.springsecurity.entity.UserDetail;
import com.sharfine.springsecurity.entity.UserInfo;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class AuthUtil {
    public static UserDetail getUserDetail() {
        return (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static UserInfo getUserInfo() {
        return ((UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserInfo();
    }

    public static String getLoginAccount() {
        return ((UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    public static String getUserId() {
        return ((UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
    }

    public static List<String> getAuthorities() {
        return ((UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRoles();
    }
}
