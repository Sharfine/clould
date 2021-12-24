package com.sharfine.order.service.impl;

import com.sharfine.common.entity.Order;
import com.sharfine.common.feign.AccountClient;
import com.sharfine.order.dao.OrderDAO;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: Sharfine
 * @createTime: 2021/8/21 15:03
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDAO orderDAO;
    @Resource
    private AccountClient accountClient;

    @Override
    //@Transactional(rollbackFor = Exception.class)
    @GlobalTransactional(rollbackFor = Exception.class)
    public Order create(){

        int orderMoney = 10;

        //accountService.save(userId, orderMoney);

        Order order = new Order();
        order.setUserId("1");
        order.setCommodityCode("commodityCode");
        order.setCount(10);
        order.setMoney(orderMoney);
        orderDAO.insert(order);
        accountClient.save();
        System.out.println(RootContext.getXID());

        int i = 1/0;

        return order;
    }
}
