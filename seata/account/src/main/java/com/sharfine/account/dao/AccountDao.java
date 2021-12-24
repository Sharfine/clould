package com.sharfine.account.dao;


import org.apache.ibatis.annotations.Update;

/**
 * @author: Sharfine
 * @createTime: 2021/8/21 15:37
 */
public interface AccountDao {
    /**

     */
    @Update("update `account` set money = 100 where user_id = '1'")
    void debit();
}
