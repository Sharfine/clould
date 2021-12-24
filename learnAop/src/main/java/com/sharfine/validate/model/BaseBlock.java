package com.sharfine.validate.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * @author: Sharfine
 * @createTime: 2020/8/12 15:14
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class BaseBlock extends BaseMongoModel {

    /**
     * Block height
     */
    @Indexed(background = true)
    private Long height;

    /**
     * Block hash
     */
    @Indexed(unique = true, background = true)
    private String hash;

    /**
     * timestamp
     */
    @Field("time")
    @Indexed(background = true)
    private Date timestamp;

    /**
     * Parent block hash
     */
    @Field("pre")
    private String prevHash;

    /**
     * Address of blocker
     */
    @Indexed(background = true)
    private String blocker;

}

