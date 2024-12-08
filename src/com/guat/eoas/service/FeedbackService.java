package com.guat.eoas.service;

import com.guat.eoas.dao.FeedbackDao;
import com.guat.eoas.pojo.Feedback;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class FeedbackService {

    private final FeedbackDao dao = new FeedbackDao();

    public List<Feedback> findAll(Integer id, Integer userId, String status, Timestamp startDate, Timestamp endDate) {
        try {
            return dao.getFeedbacks(id, userId, status, startDate, endDate);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer addFeedback(Feedback feedback) {
        try {
            return dao.addFeedback(feedback);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer updateFeedback(Feedback feedback) {
        try {
            return dao.updateFeedback(feedback);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer deleteFeedback(Integer id) {
        try {
            return dao.deleteFeedback(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
