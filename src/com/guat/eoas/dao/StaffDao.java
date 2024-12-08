package com.guat.eoas.dao;

import com.guat.eoas.pojo.Staff;
import com.guat.eoas.utils.DataSourceConfig;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffDao {

    private final QueryRunner queryRunner = new QueryRunner(DataSourceConfig.getDataSource());

    public List<Staff> getStaff(Integer id, String name, String email, String role, String status) throws SQLException {
        StringBuilder sql = new StringBuilder("select * from staff where 1=1 ");
        List<Object> params = new ArrayList<>();
        if (id != null) {
            sql.append(" and id = ? ");
            params.add(id);
        }
        if (role != null && !role.isEmpty()) {
            sql.append(" and role = ? ");
            params.add(role);
        }
        if (status != null && !status.isEmpty()) {
            sql.append(" and status = ? ");
            params.add(status);
        }
        if (name != null && !name.isEmpty()) {
            sql.append(" and name like ? ");
            params.add("%" + name + "%");
        }
        if (email != null && !email.isEmpty()) {
            sql.append(" and email like ? ");
            params.add("%" + email + "%");
        }
        return queryRunner.query(sql.toString(), new BeanListHandler<>(Staff.class), params.toArray());
    }

    public List<String> getStaffNames(Integer id) throws SQLException {
        StringBuilder sql = new StringBuilder("SELECT name FROM staff WHERE 1=1 ");
        List<Object> params = new ArrayList<>();
        if (id != null) {
            sql.append(" AND id = ? ");
            params.add(id);
        }
        return queryRunner.query(sql.toString(), new ColumnListHandler<>(), params.toArray());
    }


    public Integer addStaff(Staff staff) throws SQLException {
        String sql = "insert into staff (email, name, gender, department, position, phone) values (?, ?, ?, ?, ?, ?)";
        return queryRunner.update(sql, staff.getEmail(), staff.getName(), staff.getGender(), staff.getDepartment(), staff.getPosition(), staff.getPhone());
    }

    public Integer updateStaff(Staff staff) throws SQLException {
        String sql = "update staff set email = ?, role = ?, status = ?, name = ?, gender = ?, department = ?, position = ?, phone = ? where id = ?";
        return queryRunner.update(sql, staff.getEmail(), staff.getRole(), staff.getStatus(), staff.getName(), staff.getGender(), staff.getDepartment(), staff.getPosition(), staff.getPhone(), staff.getId());
    }

    public Integer deleteStaff(Integer id) throws SQLException {
        String sql = "delete from staff where id = ?";
        return queryRunner.update(sql, id);
    }
}
