package com.guat.eoas.service;

import com.guat.eoas.dao.UserDao;
import com.guat.eoas.pojo.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private final UserDao dao = new UserDao();

    public User getUserById(int id) {
        try {
            return dao.getUserById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User login(String username, String password) throws SQLException {
        User userByName = dao.getUserByName(username);
        if (userByName != null) {
            if (password.equals(userByName.getPassword())) {
                return userByName;
            }
        }
        return null;
    }

    public Integer addUser(User user) {
        try {
            return dao.addUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer updateUser(User user) {
        try {
            return dao.updateUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer deleteUser(int id) {
        try {
            return dao.deleteUser(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
