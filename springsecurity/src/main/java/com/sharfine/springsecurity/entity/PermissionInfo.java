package com.sharfine.springsecurity.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户对象
 * </p>
 *
 * @author sharfine
 * @since 2020-07-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PermissionInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private String id;

    private String permissionName;

    private String permissionUri;

    private String permissionMethod;

    private Integer activeStatus;

    private LocalDateTime createTime;


}
