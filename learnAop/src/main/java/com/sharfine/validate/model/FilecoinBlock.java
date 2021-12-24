package com.sharfine.validate.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * @author: Sharfine
 * @createTime: 2020/8/12 15:16
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Document(collection = "filecoin_block")
public class FilecoinBlock extends BaseBlock {

    private Integer index;

    @Field("tic")
    private String ticket;

    @Field("elecP")
    private String electionProof;

    @Field("beaconE")
    private List<BeaconEntry> beaconEntries;

    @Field("winPP")
    private List<PoStProof> winPoStProof;

    private List<String> parents;

    @Field("parentW")
    private BigInteger parentWeight;

    @Field("parentSR")
    private String parentStateRoot;

    @Field("parentMR")
    private String parentMessageReceipts;

    @Field("msg")
    private String messages;

    @Field("blsA")
    private Signature blsAggregate;

    @Field("sig")
    private Signature blockSig;

    @Field("forkS")
    private Long forkSignaling;

    /**
     * block byte size
     */
    private Integer size;

    private Integer msgNum;

    private BigDecimal reward;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BeaconEntry {
        private Long round;
        private String data;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PoStProof {
        private Long poStProof;
        private String proofBytes;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Signature {
        private Long type;
        private String data;
    }
}

