package com.sharfine.validate.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigInteger;
import java.util.List;

/**
 * @author: Sharfine
 * @createTime: 2020/8/12 15:33
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Document(collection = "eth_block")
public class EthBlock extends BaseBlock {

    private String nonce;

    @Field("unc")
    private String sha3Uncles;

    @Field("logsB")
    private String logsBloom;

    @Field("tRoot")
    private String txsRoot;

    @Field("sRoot")
    private String stateRoot;

    @Field("rRoot")
    private String rsRoot;

    /**
     * block header data does no nonce hash
     */
    @Field("mixH")
    private String mixHash;

    @Field("rub")
    private BigInteger difficulty;

    @Field("totalD")
    private BigInteger totalDifficulty;

    @Field("extD")
    private String extraData;

    /**
     * block byte size
     */
    private BigInteger size;

    @Field("gLimit")
    private BigInteger gasLimit;

    @Field("gUsed")
    private BigInteger gasUsed;

    private List<Object> txs;

    private List<String> uncles;

    @Field("sealFs")
    private List<String> sealFields;
}

