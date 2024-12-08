package com.guat.eoas.test;

import com.guat.eoas.pojo.Announcement;
import com.guat.eoas.service.AnnouncementService;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class AnnouncementTest {

    private final AnnouncementService service = new AnnouncementService();

    @Test
    void getAnnouncement() throws SQLException {
        int id = 1;
        String title = "M";
        List<Announcement> list = service.getAll(null, title, null, null);
        for (Announcement announcement : list) {
            System.out.println(announcement);
        }
    }

    @Test
    void addAnnouncement() throws SQLException {
        Announcement announcement = new Announcement();
        announcement.setTitle("M");
        announcement.setContent("aaaaaaaaaaaaaaa");
        Integer i = service.addAnnouncement(announcement);
        System.out.println(i > 0);
    }

    @Test
    void updateAnnouncement() throws SQLException {
        Announcement announcement = new Announcement();
        announcement.setId(11);
        announcement.setTitle("dssdsddssdds");
        announcement.setContent("alalallalalalalal");
        Integer i = service.updateAnnouncement(announcement);
        System.out.println(i > 0);
    }

    @Test
    void deleteAnnouncement() throws SQLException {
        Integer i = service.deleteAnnouncement(11);
        System.out.println(i > 0);
    }

    @Test
    void getNewestAnnouncement() throws SQLException {
        Announcement anno = service.getLatestAnnouncement();
        System.out.println(anno);
    }
}
