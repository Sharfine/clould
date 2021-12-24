package com.sharfine.springsecurity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sharfine.springsecurity.entity.bo.PermissionInfoBO;
import com.sharfine.springsecurity.entity.PermissionInfo;
import com.sharfine.springsecurity.mapper.PermissionMapper;
import com.sharfine.springsecurity.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sharfine
 * @since 2020-06-30
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, PermissionInfo> implements PermissionService {


    @Override
    public List<PermissionInfoBO> listPermissionInfoBO() {
        return getBaseMapper().listPermissionInfoBO();
    }
}
