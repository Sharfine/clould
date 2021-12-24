package com.sharfine.springsecurity.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sharfine.springsecurity.entity.RoleInfo;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author sharfine
 * @since 2020-06-30
 */
public interface RoleInfoMapper extends BaseMapper<RoleInfo> {

    List<RoleInfo> listRoleByUserId(String userId);

}
