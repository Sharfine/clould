package com.sharfine.order.dao;

import com.sharfine.common.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: Sharfine
 * @createTime: 2021/8/21 15:17
 */
@Mapper
public interface OrderDAO {
    @Insert("insert into `order` values(null,'1','1',1,1)")
    void insert(Order order);
}
