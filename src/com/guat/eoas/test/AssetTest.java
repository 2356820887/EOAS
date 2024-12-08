package com.guat.eoas.test;

import com.guat.eoas.pojo.Asset;
import com.guat.eoas.service.AssetService;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class AssetTest {

    private final AssetService service = new AssetService();

    @Test
    void getAsset() {
        Integer id = 1;
        String name = "S";
        String type = "O";
        String status = "损坏";
        Date startDate = Date.valueOf("2020-01-01");
        Date endDate = Date.valueOf("2010-01-02");
        String location = "a";
        List<Asset> list = service.getAllAssets(null, name, null, null, null);
        for (Asset asset : list) {
            System.out.println(asset);
        }
    }

    @Test
    void addAsset() {
        Asset asset = new Asset();
        asset.setName("asd");
        asset.setType("asdad");
        asset.setQuantity(2323);
        asset.setLocation("2312");
        Integer i = service.addAsset(asset);
        System.out.println(i > 0);
    }

    @Test
    void updateAsset() {
        Asset asset = new Asset();
        asset.setId(12);
        asset.setName("aaaa");
        asset.setType("aaa");
        asset.setQuantity(23);
        asset.setStatus("可用");
        asset.setLocation("asdfghj");
        Integer i = service.updateAsset(asset);
        System.out.println(i > 0);
    }

    @Test
    void deleteAsset() {
        Integer i = service.deleteAsset(12);
        System.out.println(i);
    }
}
