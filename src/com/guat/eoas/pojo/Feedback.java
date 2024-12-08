package com.guat.eoas.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Feedback {
    private Integer id;
    private Integer user_id;
    private String name;
    private String suggestion;
    private String status; // enum('未处理','已处理')
    private Timestamp created_at;
}
