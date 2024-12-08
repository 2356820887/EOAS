package com.guat.eoas.service;

import com.guat.eoas.dao.AnnouncementDao;
import com.guat.eoas.pojo.Announcement;
import com.guat.eoas.utils.DataSourceConfig;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class AnnouncementService {

    private final AnnouncementDao dao = new AnnouncementDao();

    public List<Announcement> getAll(Integer id, String title, Timestamp startDate, Timestamp endDate) {
        try {
            return dao.getAnnouncements(id, title, startDate, endDate);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer addAnnouncement(Announcement announcement) {
        Connection conn = null;
        try {
            conn = DataSourceConfig.getConnection();
            conn.setAutoCommit(false);
            Integer i = dao.addAnnouncement(conn, announcement);
            conn.commit();
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        return 0;
    }

    public Integer updateAnnouncement(Announcement announcement) {
        Connection conn = null;
        try {
            conn = DataSourceConfig.getConnection();
            conn.setAutoCommit(false);
            Integer i = dao.updateAnnouncement(conn, announcement);
            conn.commit();
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        return 0;
    }

    public Integer deleteAnnouncement(Integer id) {
        Connection conn = null;
        try {
            conn = DataSourceConfig.getConnection();
            conn.setAutoCommit(false);
            Integer i = dao.deleteAnnouncement(conn, id);
            conn.commit();
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        return 0;
    }

    public Announcement getLatestAnnouncement() {
        try {
            return dao.getLatestAnnouncement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
