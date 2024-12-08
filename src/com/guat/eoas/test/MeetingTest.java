package com.guat.eoas.test;

import com.guat.eoas.pojo.Meeting;
import com.guat.eoas.service.MeetingService;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class MeetingTest {

    private final MeetingService service = new MeetingService();

    @Test
    void getMeetings() throws SQLException {
        int id = 1;
        String title = "M";
        String location = "M";
        List<Meeting> meetings = service.getMeetings(null, null, null, null, null,null);
        for (Meeting meeting : meetings) {
            System.out.println(meeting);
        }
    }

    @Test
    void addMeeting() throws SQLException {
        String title = "M";
        Timestamp date = Timestamp.valueOf("2010-01-01 00:00:00");
        String location = "S";
        String description = "alkhsdakljsdj";
        Meeting meeting = new Meeting();
        meeting.setTitle(title);
        meeting.setDescription(description);
        meeting.setLocation(location);
        meeting.setDate(date);
        Integer i = service.addMeeting(meeting);
        System.out.println(i > 0);
    }

    @Test
    void updateMeeting() throws SQLException {
        Meeting meeting = new Meeting();
        meeting.setId(12);
        meeting.setTitle("MS");
        meeting.setDescription("alkhsd");
        meeting.setLocation("SS");
        meeting.setDate(Timestamp.valueOf("2015-01-02 01:20:00"));
        Integer i = service.updateMeeting(meeting);
        System.out.println(i > 0);
    }

    @Test
    void deleteMeeting() throws SQLException {
        Integer i = service.deleteMeeting(12);
        System.out.println(i > 0);
    }
}
