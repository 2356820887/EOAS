package com.guat.eoas.dao;

import com.guat.eoas.pojo.Files;
import com.guat.eoas.utils.DataSourceConfig;
import lombok.Getter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class FileDao {

    @Getter
    private static QueryRunner queryRunner = new QueryRunner(DataSourceConfig.getDataSource());

    public void saveFile(Files file) throws SQLException {
        String sql = "insert into files (name, path, type) values (?, ?, ?)";
        queryRunner.update(sql, file.getName(), file.getPath(), file.getType());
    }

    public List<Files> findAll() throws SQLException {
        String sql = "select * from files order by id desc";
        return queryRunner.query(sql, new BeanListHandler<>(Files.class));
    }

    public Files findById(Integer id) throws SQLException {
        String sql = "select * from files where id = ?";
        return queryRunner.query(sql, new BeanHandler<>(Files.class), id);
    }

    public Integer deleteById(Integer id) throws SQLException {
        String sql = "delete from files where id = ?";
        return queryRunner.update(sql, id);
    }
}
