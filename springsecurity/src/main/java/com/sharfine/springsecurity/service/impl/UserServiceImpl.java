package com.sharfine.springsecurity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sharfine.springsecurity.entity.UserInfo;
import com.sharfine.springsecurity.mapper.UserMapper;
import com.sharfine.springsecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * User服务实现类
 * </p>
 *
 * @author sharfine
 * @since 2020-06-30
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserInfo> implements UserService {

}
