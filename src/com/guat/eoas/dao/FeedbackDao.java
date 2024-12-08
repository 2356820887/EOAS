package com.guat.eoas.dao;

import com.guat.eoas.pojo.Feedback;
import com.guat.eoas.utils.DataSourceConfig;
import lombok.Getter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDao {

    @Getter
    private static QueryRunner queryRunner = new QueryRunner(DataSourceConfig.getDataSource());

    public List<Feedback> getFeedbacks(Integer id, Integer userId, String status, Timestamp startDate, Timestamp endDate) throws SQLException {
        StringBuilder sql = new StringBuilder("SELECT f.id, f.user_id, s.`name`, f.suggestion, f.`status`, f.created_at " +
                "FROM feedback f LEFT JOIN staff s ON f.user_id = s.id WHERE 1=1");
        List<Object> params = new ArrayList<>();
        if (id != null) {
            sql.append(" and f.id = ?");
            params.add(id);
        }
        if (userId != null) {
            sql.append(" and f.user_id = ?");
            params.add(userId);
        }
        if (status != null && !status.isEmpty()) {
            sql.append(" and f.status = ?");
            params.add(status);
        }
        if (startDate != null && endDate != null) {
            sql.append(" and f.created_at between ? and ?");
            params.add(startDate);
            params.add(endDate);
        }
        return queryRunner.query(sql.toString(), new BeanListHandler<>(Feedback.class), params.toArray());
    }

    public Integer addFeedback(Feedback feedback) throws SQLException {
        String sql = "INSERT INTO feedback (user_id, suggestion) VALUE (?,?)";
        return queryRunner.update(sql, feedback.getUser_id(), feedback.getSuggestion());
    }

    public Integer updateFeedback(Feedback feedback) throws SQLException {
        String sql = "UPDATE feedback SET user_id = ?, suggestion = ? ,status = ?WHERE id = ?";
        return queryRunner.update(sql, feedback.getUser_id(), feedback.getSuggestion(), feedback.getStatus(), feedback.getId());
    }

    public Integer deleteFeedback(Integer id) throws SQLException {
        String sql = "DELETE FROM feedback WHERE id = ?";
        return queryRunner.update(sql, id);
    }
}
