package com.sharfine.springsecurity.component;


import com.sharfine.springsecurity.entity.bo.PermissionInfoBO;
import com.sharfine.springsecurity.cache.Cache;
import com.sharfine.springsecurity.constant.CacheName;
import com.sharfine.springsecurity.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class InitProcessor {
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private Cache caffeineCache;

    @PostConstruct
    public void init() {
        List<PermissionInfoBO> permissionInfoList = permissionService.listPermissionInfoBO();
        permissionInfoList.forEach(permissionInfo -> {
            caffeineCache.put(CacheName.PERMISSION, permissionInfo.getPermissionUri() + ":" + permissionInfo.getPermissionMethod(), permissionInfo);
        });
    }
}
