package com.guat.eoas.dao;

import com.guat.eoas.pojo.Announcement;
import com.guat.eoas.utils.DataSourceConfig;
import lombok.Getter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AnnouncementDao {

    @Getter
    private static QueryRunner queryRunner = new QueryRunner(DataSourceConfig.getDataSource());

    public List<Announcement> getAnnouncements(Integer id, String title, Timestamp startDate, Timestamp endDate) throws SQLException {
        StringBuilder sql = new StringBuilder("SELECT * FROM announcements WHERE 1=1");
        List<Object> params = new ArrayList<>();

        // 动态添加条件
        if (id != null) {
            sql.append(" AND id = ?");
            params.add(id);
        }

        if (title != null && !title.isEmpty()) {
            sql.append(" AND title LIKE ?");
            params.add("%" + title + "%");
        }

        if (startDate != null && endDate != null) {
            sql.append(" AND updated_at BETWEEN ? AND ?");
            params.add(startDate);
            params.add(endDate);
        }

        // 按时间排序
        sql.append(" ORDER BY updated_at DESC");

        // 执行查询
        return queryRunner.query(sql.toString(), new BeanListHandler<>(Announcement.class), params.toArray());
    }



    public Integer addAnnouncement(Connection conn, Announcement announcement) throws SQLException {
        String sql = "INSERT INTO `eoas`.`announcements` (`title`, `content`) VALUES (?, ?)";
        return queryRunner.update(conn, sql, announcement.getTitle(), announcement.getContent());
    }

    public Integer updateAnnouncement(Connection conn, Announcement announcement) throws SQLException {
        String sql = "UPDATE `eoas`.`announcements` SET `title` = ?, `content` = ? WHERE `id` = ?";
        return queryRunner.update(conn,sql, announcement.getTitle(), announcement.getContent(), announcement.getId());
    }

    public Integer deleteAnnouncement(Connection conn, Integer id) throws SQLException {
        String sql = "delete from `eoas`.`announcements` where `id` = ?";
        return queryRunner.update(conn, sql, id);
    }

    public Announcement getLatestAnnouncement() throws SQLException {
        String sql = "SELECT * FROM announcements ORDER BY updated_at DESC LIMIT 1";
        return queryRunner.query(sql, new BeanHandler<>(Announcement.class));
    }
}
