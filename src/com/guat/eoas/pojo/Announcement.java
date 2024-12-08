package com.guat.eoas.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Announcement {
    private Integer id;
    private String title;
    private String content;
    private Timestamp created_at;
    private Timestamp updated_at;
}
