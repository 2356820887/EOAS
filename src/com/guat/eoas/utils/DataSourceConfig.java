package com.guat.eoas.utils;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Getter;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceConfig {

    @Getter
    private static DruidDataSource dataSource;

    static {
        dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/eoas?useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setInitialSize(5);  // 初始化连接数
        dataSource.setMinIdle(5);  // 最小空闲连接数
        dataSource.setMaxActive(20);  // 最大活动连接数
        dataSource.setMaxWait(60000);  // 获取连接的最大等待时间
        dataSource.setValidationQuery("SELECT 1");  // 验证连接是否有效的SQL
        dataSource.setTestWhileIdle(true);  // 空闲时测试连接
        dataSource.setTimeBetweenEvictionRunsMillis(60000);  // 空闲连接检测时间间隔
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
