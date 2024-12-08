package com.guat.eoas.service;

import com.guat.eoas.dao.AssetDao;
import com.guat.eoas.pojo.Asset;

import java.sql.SQLException;
import java.util.List;

public class AssetService {

    private final AssetDao dao = new AssetDao();

    public List<Asset> getAllAssets(Integer id, String name, String type, String status, String location) {
        try {
            return dao.getAssets(id, name, type, status, location);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer addAsset(Asset asset) {
        try {
            return dao.addAsset(asset);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer updateAsset(Asset asset) {
        try {
            return dao.updateAsset(asset);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer deleteAsset(Integer id) {
        try {
            return dao.deleteAsset(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
