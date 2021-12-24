package com.sharfine.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sharfine.springsecurity.entity.bo.PermissionInfoBO;
import com.sharfine.springsecurity.entity.PermissionInfo;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author sharfine
 * @since 2020-06-30
 */
public interface PermissionMapper extends BaseMapper<PermissionInfo> {

    List<PermissionInfoBO> listPermissionInfoBO();

}
