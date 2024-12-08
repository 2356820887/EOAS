package com.guat.eoas.service;

import com.guat.eoas.dao.MeetingDao;
import com.guat.eoas.pojo.Meeting;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class MeetingService {

    private final MeetingDao dao = new MeetingDao();

    public List<Meeting> getMeetings(Integer id, String title, Timestamp startDate, Timestamp endDate, String participants, String location) {
        try {
            return dao.getMeetings(id, title, startDate, endDate, participants, location);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer addMeeting(Meeting meeting) {
        try {
            return dao.AddMeeting(meeting);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer updateMeeting(Meeting meeting) {
        try {
            return dao.UpdateMeeting(meeting);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer deleteMeeting(int id) {
        try {
            return dao.DeleteMeeting(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
