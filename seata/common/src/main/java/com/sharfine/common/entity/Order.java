package com.sharfine.common.entity;

import lombok.Data;

/**
 * @author: Sharfine
 * @createTime: 2021/8/21 15:18
 */
@Data
public class Order {

    private Integer id;

    private String userId;

    private String commodityCode;

    private Integer count;

    private Integer money;

}
