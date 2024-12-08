package com.guat.eoas.service;

import com.guat.eoas.dao.EmailDao;
import com.guat.eoas.pojo.Email;

import java.sql.SQLException;
import java.util.List;

public class EmailService {

    private final EmailDao dao = new EmailDao();

    public List<Email> getEmails(Integer id, Integer senderId, Integer receiverId, String status) {
        try {
            return dao.getEmails(id,senderId,receiverId,status);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer addEmail(Email email) {
        try {
            return dao.addEmail(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer updateEmail(Email email){
        try {
            return dao.updateEmail(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer deleteEmail(Integer id) {
        try {
            return dao.deleteEmail(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
