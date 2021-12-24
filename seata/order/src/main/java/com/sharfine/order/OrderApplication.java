package com.sharfine.order;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = {"com.sharfine.common.feign"})
@MapperScan(basePackages = {"com.sharfine.common.dao","com.sharfine.order.dao"})
@SpringBootApplication(scanBasePackages = "com.sharfine")
@EnableAutoDataSourceProxy
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}
