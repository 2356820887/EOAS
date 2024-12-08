package com.guat.eoas.dao;

import com.guat.eoas.pojo.User;
import com.guat.eoas.utils.DataSourceConfig;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDao {

    private final QueryRunner queryRunner = new QueryRunner(DataSourceConfig.getDataSource());

    public User getUserById(Integer id) throws SQLException {
        String sql = "select * from user where id = ?";
        return queryRunner.query(sql, new BeanHandler<>(User.class), id);
    }

    public User getUserByName(String username) throws SQLException {
        String sql = "select * from user where username = ?";
        return queryRunner.query(sql, new BeanHandler<>(User.class), username);
    }

    public Integer addUser(User user) throws SQLException {
        String sql = "insert into user (id, username, password) values(?,?,?)";
        return queryRunner.update(sql, user.getId(), user.getUsername(), user.getPassword());
    }

    public Integer deleteUser(Integer id) throws SQLException {
        String sql = "delete from user where id = ?";
        return queryRunner.update(sql, id);
    }

    public Integer updateUser(User user) throws SQLException {
        String sql = "update user set username = ?, password = ? where id = ?";
        return queryRunner.update(sql, user.getUsername(), user.getPassword(), user.getId());
    }
}
