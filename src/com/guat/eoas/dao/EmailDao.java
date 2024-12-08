package com.guat.eoas.dao;

import com.guat.eoas.pojo.Email;
import com.guat.eoas.utils.DataSourceConfig;
import lombok.Getter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmailDao {

    @Getter
    private static final QueryRunner queryRunner = new QueryRunner(DataSourceConfig.getDataSource());

    public List<Email> getEmails(Integer id, Integer senderId, Integer receiverId, String status) throws SQLException {
        StringBuilder sql = new StringBuilder("select * from emails where 1=1");
        List<Object> params = new ArrayList<>();
        if (id != null) {
            sql.append(" and id = ?");
            params.add(id);
        }
        if (senderId != null) {
            sql.append(" and sender_id = ?");
            params.add(senderId);
        }
        if (receiverId != null) {
            sql.append(" and receiver_id = ?");
            params.add(receiverId);
        }
        if (status != null && !status.isEmpty()) {
            sql.append(" and status = ?");
            params.add(status);
        }
        return queryRunner.query(sql.toString(), new BeanListHandler<>(Email.class), params.toArray());
    }

    public Integer addEmail(Email email) throws SQLException {
        String sql = "insert into emails (sender_id, receiver_id, subject, content) VALUE (?, ?, ?, ?)";
        return queryRunner.update(sql, email.getSender_id(), email.getReceiver_id(), email.getSubject(), email.getContent());
    }

    public Integer updateEmail(Email email) throws SQLException {
        String sql = "update emails set sender_id = ?, receiver_id = ?, subject = ?, content = ?, status = ? where id = ?";
        return queryRunner.update(sql, new BeanHandler<>(Email.class));
    }

    public Integer deleteEmail(Integer id) throws SQLException {
        String sql = "delete from emails where id = ?";
        return queryRunner.update(sql, id);
    }
}
