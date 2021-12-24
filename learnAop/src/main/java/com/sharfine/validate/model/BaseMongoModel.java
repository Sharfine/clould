package com.sharfine.validate.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * @author: Sharfine
 * @createTime: 2020/8/12 15:15
 */
@Data
public abstract class BaseMongoModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String mongoId;
}
