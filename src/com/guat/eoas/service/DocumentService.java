package com.guat.eoas.service;

import com.guat.eoas.dao.DocumentDao;
import com.guat.eoas.dao.StaffDao;
import com.guat.eoas.pojo.Documents;
import com.guat.eoas.pojo.Staff;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class DocumentService {

    private final DocumentDao dao = new DocumentDao();

    public List<Documents> getDocuments(Integer id, String title, Integer aId, Timestamp startDate, Timestamp endDate, String status, String name) {
        try {
            return dao.findAll(id, title, aId, startDate, endDate, status, name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer addDocument(Documents document) {
        try {
            return dao.add(document);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer updateDocument(Documents document) {
        try {
            return dao.update(document);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer deleteDocument(Integer id) {
        try {
            return dao.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
