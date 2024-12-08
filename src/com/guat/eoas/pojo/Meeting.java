package com.guat.eoas.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Meeting {
    private Integer id;                  // 会议ID
    private String title;                // 会议标题
    private Timestamp date;              // 会议时间
    private String location;             // 会议地点
    private String description;          // 会议内容
    private String participants;         // 参会人员（以逗号分隔的字符串）
    private Timestamp createdAt;         // 记录创建时间
    private Timestamp updatedAt;         // 记录更新时间
}
