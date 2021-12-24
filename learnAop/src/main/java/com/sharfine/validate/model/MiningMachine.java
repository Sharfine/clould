package com.sharfine.validate.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Document(collection = "mining_machine")
public class MiningMachine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String mongoId;

    @Indexed(unique = true)
    private String id;

    private String machineId;

    private String machineName;

    private String city;

    private String roomName;

    private String cabinet;

    private String cpu;

    private String cpuType;

    private String ram;

    private String motherboard;

    private String gpu;

    private String storage;

    private String networkCard;

    private String os;

    private Date createTime;

    private Date updateTime;

}
