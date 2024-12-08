package com.guat.eoas.test;

import com.guat.eoas.dao.UserDao;
import com.guat.eoas.pojo.User;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

class UserTest {

    private final UserDao dao = new UserDao();

    @Test
    void getUserByIdTest() throws SQLException {
        System.out.println(dao.getUserById(1));
    }

    @Test
    void getUserByNameTest() throws SQLException {
        System.out.println(dao.getUserByName("Chad Coleman"));
    }

    @Test
    void addUserTest() throws SQLException {
        User u = new User();
        u.setId(11);
        u.setUsername("Chad Coleman");
        u.setPassword("1234");
        Integer i = dao.addUser(u);
        System.out.println(i);
    }

    @Test
    void deleteUserTest() throws SQLException {
        Integer flag = dao.deleteUser(8);
        System.out.println(flag);
    }

    @Test
    void updateUserTest() throws SQLException {
        User u = new User();
        u.setId(7);
        u.setUsername("Chad Coleman");
        u.setPassword("1234");
        Integer i = dao.updateUser(u);
        System.out.println(i);
    }
}