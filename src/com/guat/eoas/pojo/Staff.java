package com.guat.eoas.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Staff {
    private Integer id;
    private String email;
    private String role; // enum('管理员','员工','经理')
    private String status; // enum('激活','禁用')
    private String name;
    private String gender; // enum('男','女')
    private String department;
    private String position;
    private String phone;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
