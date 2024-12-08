package com.guat.eoas.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Files {
    private Integer id;
    private String name;
    private String path;
    private Timestamp time;
    private String type;
}
