package com.sharfine.validate.enums;

import lombok.Getter;

/**
 * @author: Sharfine
 * @createTime: 2020/7/31 11:34
 */

public interface DatabaseType {

    /**
     * MASTER
     */
    String DATA_SOURCE_MASTER = "dataSourceMaster";
    /**
     * SLAVE
     */
    String DATA_SOURCE_SLAVE = "dataSourceSlave";


}
