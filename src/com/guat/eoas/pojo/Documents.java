package com.guat.eoas.pojo;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class Documents {
    private Integer d_id;
    private String title;
    private String content;
    private Integer author_id;
    private String author_name;
    private Timestamp created_at;
    private Timestamp updated_at;
    private String d_status;
}
