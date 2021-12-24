package com.sharfine.springsecurity.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.sharfine.springsecurity.entity.RoleInfo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author sharfine
 * @since 2020-06-30
 */
public interface RoleInfoService extends IService<RoleInfo> {

    List<RoleInfo> listRoleByUserId(String userId);

}
