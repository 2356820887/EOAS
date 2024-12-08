package com.guat.eoas.service;

import com.guat.eoas.dao.FileDao;
import com.guat.eoas.pojo.Files;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

public class FileService {
    private final FileDao dao = new FileDao();

    // 保存文件信息
    public void saveFile(Files file) {
        try {
            dao.saveFile(file);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 获取所有文件信息
    public List<Files> getAllFiles() {
        try {
            return dao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 根据文件 ID 获取文件信息
    public Files getFileById(Integer id) {
        try {
            return dao.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 根据文件 ID 删除文件
    public boolean deleteFileById(Integer id) {
        try {
            Files byId = dao.findById(id);
            if (byId == null) {
                System.out.println("文件不存在");
                return false;
            }
            File file = new File(byId.getPath());
            if (file.exists() && file.isFile()) {
                if (!file.delete()) {
                    System.out.println("文件删除失败");
                    return false;
                }
            } else {
                System.out.println("文件不存在或已删除：" + file.getAbsolutePath());
            }
            Integer i = dao.deleteById(id);
            return i > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
