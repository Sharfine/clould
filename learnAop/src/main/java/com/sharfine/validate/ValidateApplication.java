package com.sharfine.validate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan("com.sharfine.validate.dao")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ValidateApplication {

    public static void main(String[] args) {
        SpringApplication.run(ValidateApplication.class, args);
    }

}
