package com.sharfine.validate.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.sharfine.validate.enums.DatabaseType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * 数据源配置
 *
 * @author: Sharfine
 * @createTime: 2020/7/31 14:52
 */
@Configuration
public class MultipleDataSourceConfig {

    @Bean(name = "dataSourceMaster")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource primaryDataSource() {
        return new DruidDataSource();
    }

    @Bean(name = "dataSourceSlave")
    @ConfigurationProperties(prefix = "slave.datasource")
    public DataSource slaveDataSource() {
        return new DruidDataSource();
    }

    @Primary
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        //配置默认数据源
        dynamicDataSource.setDefaultTargetDataSource(primaryDataSource());
        //配置多数据源
        HashMap<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DatabaseType.DATA_SOURCE_MASTER, primaryDataSource());
        dataSourceMap.put(DatabaseType.DATA_SOURCE_SLAVE, slaveDataSource());
        // 该方法是AbstractRoutingDataSource的方法
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        return dynamicDataSource;

    }

    /**
     * 配置@Transactional注解事务
     *
     * @return PlatformTransactionManager
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}
