package com.guat.eoas.dao;

import com.guat.eoas.pojo.Documents;
import com.guat.eoas.utils.DataSourceConfig;
import lombok.Getter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DocumentDao {

    @Getter
    private static final QueryRunner queryRunner = new QueryRunner(DataSourceConfig.getDataSource());

    public List<Documents> findAll(Integer id, String title, Integer aId, Timestamp startDate, Timestamp endDate, String status, String name) throws SQLException {
        StringBuilder sql = new StringBuilder(
                "SELECT d.d_id, d.title, d.content, d.author_id, d.created_at, d.updated_at, d.d_status, s.name AS author_name " +
                        "FROM documents d " +
                        "LEFT JOIN staff s ON d.author_id = s.id " +
                        "WHERE 1=1"
        );

        List<Object> params = new ArrayList<>();

        // 过滤条件：ID
        if (id != null) {
            sql.append(" AND d.d_id = ?");
            params.add(id);
        }

        // 过滤条件：标题
        if (title != null && !title.isEmpty()) {
            sql.append(" AND d.title LIKE ?");
            params.add("%" + title + "%");
        }

        // 过滤条件：作者ID
        if (aId != null) {
            sql.append(" AND d.author_id = ?");
            params.add(aId);
        }

        // 过滤条件：日期范围
        if (startDate != null && endDate != null) {
            sql.append(" AND d.updated_at BETWEEN ? AND ?");
            params.add(startDate);
            params.add(endDate);
        }

        // 过滤条件：状态
        if (status != null && !status.isEmpty()) {
            sql.append(" AND d.d_status = ?");
            params.add(status);
        }

        // 过滤条件：作者名称
        if (name != null && !name.isEmpty()) {
            sql.append(" AND s.name LIKE ?");
            params.add("%" + name + "%");
        }

        // 执行查询
        return queryRunner.query(sql.toString(), new BeanListHandler<>(Documents.class), params.toArray());
    }



    public Integer add(Documents document) throws SQLException {
        String sql = "INSERT INTO `eoas`.`documents` (`d_id`, `title`, `content`, `author_id`) VALUES (?, ?, ?, ?)";
        return queryRunner.update(sql, document.getD_id(), document.getTitle(), document.getContent(), document.getAuthor_id());
    }

    public Integer update(Documents document) throws SQLException {
        String sql = "update documents set title = ?, content = ?, author_id = ?, d_status = ? where d_id = ?";
        return queryRunner.update(sql, document.getTitle(), document.getContent(), document.getAuthor_id(), document.getD_status(), document.getD_id());
    }

    public Integer delete(Integer id) throws SQLException {
        String sql = "delete from documents where d_id = ?";
        return queryRunner.update(sql, id);
    }
}
