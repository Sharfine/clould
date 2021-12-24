package com.sharfine.validate.config;

import com.sharfine.validate.enums.DatabaseType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 数据源路由实现类
 *
 * @author: Sharfine
 * @createTime: 2020/7/31 13:52
 */
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSource = DataSourceContextHolder.getDataSource();

        if (dataSource == null) {
            log.info("当前数据源为[primary]");
        } else {
            log.info("当前数据源为{}", dataSource);
        }

        return dataSource;
    }
}
