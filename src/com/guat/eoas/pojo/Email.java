package com.guat.eoas.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Email {
    private Integer id;
    private Integer sender_id;
    private String senderName;
    private Integer receiver_id;
    private String receiverName;
    private String subject;
    private String content;
    private Timestamp send_time;
    private String status; // enum('未读','已读','已删除')
}
