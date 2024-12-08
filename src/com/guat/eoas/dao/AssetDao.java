package com.guat.eoas.dao;

import com.guat.eoas.pojo.Asset;
import com.guat.eoas.utils.DataSourceConfig;
import lombok.Getter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssetDao {

    @Getter
    private static final QueryRunner queryRunner = new QueryRunner(DataSourceConfig.getDataSource());

    public List<Asset> getAssets(Integer id, String name, String type, String status, String location) throws SQLException {
        StringBuilder sql = new StringBuilder("select * from assets where 1=1");
        List<Object> params = new ArrayList<>();

        if (id != null) {
            sql.append(" and id = ?");
            params.add(id);
        }

        if (name != null && !name.isEmpty()) {
            sql.append(" and name like ?");
            params.add("%" + name + "%");
        }

        if (type != null && !type.isEmpty()) {
            sql.append(" and type = ?");
            params.add(type);
        }

        if (status != null && !status.isEmpty()) {
            sql.append(" and status = ?");
            params.add(status);
        }

        if (location != null && !location.isEmpty()) {
            sql.append(" and location like ?");
            params.add("%" + location + "%");
        }

        return queryRunner.query(sql.toString(), new BeanListHandler<>(Asset.class), params.toArray());
    }

    public Integer addAsset(Asset asset) throws SQLException {
        String sql = "INSERT INTO `eoas`.`assets` (`name`, `type`, `quantity`, `location`) VALUES (?, ?, ?, ?)";
        return queryRunner.update(sql, asset.getName(), asset.getType(), asset.getQuantity(), asset.getLocation());
    }

    public Integer updateAsset(Asset asset) throws SQLException {
        String sql = "UPDATE `eoas`.`assets` SET `name` = ?, `type` = ?, `quantity` = ?, `status` = ?, `location` = ? WHERE `id` = ?";
        return queryRunner.update(sql, asset.getName(), asset.getType(), asset.getQuantity(), asset.getStatus(), asset.getLocation(), asset.getId());
    }

    public Integer deleteAsset(Integer id) throws SQLException {
        String sql = "DELETE FROM `eoas`.`assets` WHERE `id` = ?";
        return queryRunner.update(sql, id);
    }
}
