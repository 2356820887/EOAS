package com.guat.eoas.dao;

import com.guat.eoas.pojo.Meeting;
import com.guat.eoas.utils.DataSourceConfig;
import lombok.Getter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MeetingDao {

    @Getter
    private static QueryRunner queryRunner = new QueryRunner(DataSourceConfig.getDataSource());

    // 获取会议列表的方法（无需事务处理，因为是只读操作）
    public List<Meeting> getMeetings(Integer id, String title, Timestamp startDate, Timestamp endDate, String participants, String location) throws SQLException {
        StringBuilder sql = new StringBuilder("SELECT * FROM meetings WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (id != null) {
            sql.append(" AND id = ?");
            params.add(id);
        }

        if (title != null && !title.trim().isEmpty()) {
            sql.append(" AND title LIKE ?");
            params.add("%" + title + "%");
        }

        if (startDate != null && endDate != null) {
            sql.append(" AND date BETWEEN ? AND ?");
            params.add(startDate);
            params.add(endDate);
        }

        if (participants != null && !participants.trim().isEmpty()) {
            sql.append(" AND participants LIKE ?");
            params.add("%" + participants + "%");
        }

        if (location != null && !location.trim().isEmpty()) {
            sql.append(" AND location LIKE ?");
            params.add("%" + location + "%");
        }

        return queryRunner.query(sql.toString(), new BeanListHandler<>(Meeting.class), params.toArray());
    }

    // 添加会议的方法（带事务处理）
    public Integer AddMeeting(Meeting meeting) throws SQLException {
        Connection conn = null;
        try {
            conn = DataSourceConfig.getDataSource().getConnection();
            conn.setAutoCommit(false);  // 开始事务

            String sql = "INSERT INTO `eoas`.`meetings` (`title`, `date`, `location`, `description`, `participants`) VALUES (?, ?, ?, ?, ?)";
            int result = queryRunner.update(conn, sql, meeting.getTitle(), meeting.getDate(), meeting.getLocation(), meeting.getDescription(), meeting.getParticipants());

            conn.commit();  // 提交事务
            return result;
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();  // 回滚事务
            }
            throw e;  // 抛出异常
        } finally {
            if (conn != null) {
                conn.close();  // 关闭连接
            }
        }
    }

    // 更新会议的方法（带事务处理）
    public Integer UpdateMeeting(Meeting meeting) throws SQLException {
        Connection conn = null;
        try {
            conn = DataSourceConfig.getDataSource().getConnection();
            conn.setAutoCommit(false);  // 开始事务

            String sql = "UPDATE meetings SET title = ?, date = ?, location = ?, description = ?, participants = ? WHERE id = ?";
            int result = queryRunner.update(conn, sql, meeting.getTitle(), meeting.getDate(), meeting.getLocation(), meeting.getDescription(), meeting.getParticipants(), meeting.getId());

            conn.commit();  // 提交事务
            return result;
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();  // 回滚事务
            }
            throw e;  // 抛出异常
        } finally {
            if (conn != null) {
                conn.close();  // 关闭连接
            }
        }
    }

    // 删除会议的方法（带事务处理）
    public Integer DeleteMeeting(int id) throws SQLException {
        Connection conn = null;
        try {
            conn = DataSourceConfig.getDataSource().getConnection();
            conn.setAutoCommit(false);  // 开始事务

            String sql = "DELETE FROM meetings WHERE id = ?";
            int result = queryRunner.update(conn, sql, id);

            conn.commit();  // 提交事务
            return result;
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();  // 回滚事务
            }
            throw e;  // 抛出异常
        } finally {
            if (conn != null) {
                conn.close();  // 关闭连接
            }
        }
    }
}
