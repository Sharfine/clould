package com.sharfine.account.service.impl;

import com.sharfine.account.dao.AccountDao;
import io.seata.core.context.RootContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: Sharfine
 * @createTime: 2021/8/21 15:36
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountDao accountDao;
    //@Transactional
    @Override
    //@GlobalTransactional(rollbackFor = Exception.class)
    public void save() {
        accountDao.debit();
        //int i = 2 / 0;
        System.out.println(RootContext.getXID());
    }
}
