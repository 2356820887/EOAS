package com.guat.eoas.service;

import com.guat.eoas.dao.StaffDao;
import com.guat.eoas.pojo.Staff;

import java.sql.SQLException;
import java.util.List;

public class StaffService {

    private final StaffDao dao = new StaffDao();

    public List<Staff> getStaff(Integer id, String name, String email, String role, String status) {
        try {
            return dao.getStaff(id, name, email, role, status);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getStaffNames(Integer id) {
        try {
            return dao.getStaffNames(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer addStaff(Staff staff) {
        try {
            return dao.addStaff(staff);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer updateStaff(Staff staff) {
        try {
            return dao.updateStaff(staff);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer deleteStaff(Integer id) {
        try {
            return dao.deleteStaff(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
