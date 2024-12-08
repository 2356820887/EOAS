package com.guat.eoas.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Asset {
    private Integer id;
    private String name;
    private String type;
    private Integer quantity;
    private String status; // enum('可用','损坏','报废')
    private String location;
    private Timestamp createdAt;
}
