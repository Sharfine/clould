package com.sharfine.springsecurity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sharfine.springsecurity.entity.RoleInfo;
import com.sharfine.springsecurity.entity.UserDetail;
import com.sharfine.springsecurity.entity.UserInfo;
import com.sharfine.springsecurity.service.RoleInfoService;
import com.sharfine.springsecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleInfoService roleInfoService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.debug("开始登陆验证，用户名为: {}", s);

        // 根据用户名验证用户
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserInfo::getUsername, s);
        UserInfo userInfo = userService.getOne(queryWrapper);
        if (userInfo == null) {
            throw new UsernameNotFoundException("用户名不存在，登陆失败。");
        }

        // 构建UserDetail对象
        UserDetail userDetail = new UserDetail();
        userDetail.setUserInfo(userInfo);
        List<RoleInfo> roleInfoList = roleInfoService.listRoleByUserId(userInfo.getId());
        userDetail.setRoleInfoList(roleInfoList);
        return userDetail;
    }
}
