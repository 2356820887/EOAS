package com.guat.eoas.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

	public static Connection getConnection() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/eoas?characterEncoding=utf-8&serverTimeZone=Asia/Shanghai";
        String username = "root";
        String password = "root";
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
//            System.out.println("数据库连接成功");
        } catch (ClassNotFoundException e) {
            System.out.println("驱动获取失败");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
        return conn;
    }

	public static void CloseConnection(Connection conn) {
        try {
            conn.close();
            System.out.println("数据库连接关闭成功");
        } catch (SQLException e) {
            System.out.println("数据库连接关闭失败");
            e.printStackTrace();
        }
    }

	public static void main(String[] args) {
        Connection conn = getConnection();
        CloseConnection(conn);
    }
}
