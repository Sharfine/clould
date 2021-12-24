package com.sharfine.account;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.sharfine.common.dao", "com.sharfine.account.dao"})
@SpringBootApplication(scanBasePackages = {"com.sharfine.common","com.sharfine.account"})
@EnableAutoDataSourceProxy
public class AccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

}
