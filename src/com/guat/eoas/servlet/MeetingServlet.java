package com.guat.eoas.servlet;

import com.guat.eoas.pojo.Meeting;
import com.guat.eoas.service.MeetingService;
import com.guat.eoas.service.StaffService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/meeting")
public class MeetingServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    MeetingService meetingService = new MeetingService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        switch (action) {
            case "list":
                System.out.println("meeting list");
                getMeeting(request, response);
                break;
            case "id":
                System.out.println("meeting id");
                getMeetingById(request, response);
                break;
            case "add":
                System.out.println("meeting add");
                addMeeting(request, response);
                break;
            case "edit":
                System.out.println("meeting edit");
                updateMeeting(request, response);
                break;
            case "delete":
                System.out.println("meeting delete");
                deleteMeeting(request, response);
                break;
            default:
                getMeeting(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void getMeeting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String doing = request.getParameter("doing");
        String tId = request.getParameter("id");
        Integer id = null;
        if (tId != null && !tId.isEmpty()) {
            id = Integer.parseInt(tId);
        }
        String title = request.getParameter("title");
        String startDate1 = request.getParameter("startDate");
        Timestamp startDate = null;
        if (startDate1 != null && !startDate1.isEmpty()) {
            if (startDate1.matches("\\d{4}-\\d{2}-\\d{2}")) {
                startDate1 += " 00:00:00";
            }
            startDate = Timestamp.valueOf(startDate1);
        }
        String endDate1 = request.getParameter("endDate");
        Timestamp endDate = null;
        if (endDate1 != null && !endDate1.isEmpty()) {
            if (endDate1.matches("\\d{4}-\\d{2}-\\d{2}")) {
                endDate1 += " 23:59:59";
            }
            endDate = Timestamp.valueOf(endDate1);
        }
        String location = request.getParameter("location");
        String name = request.getParameter("name");
        List<Meeting> meetings = meetingService.getMeetings(id, title, startDate, endDate, name, location);
        List<String> staff = new StaffService().getStaffNames(null);
        request.setAttribute("meetings", meetings);
        request.setAttribute("staff", staff);
        if (doing == null) {
            if (id == null) {
                request.getRequestDispatcher("meeting.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("meeting-detail.jsp").forward(request, response);
            }
        } else if (doing.equals("add")) {
            request.getRequestDispatcher("meeting-add.jsp").forward(request, response);
        }
    }

    private void getMeetingById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tId = request.getParameter("id");
        Integer id = null;
        if (tId != null && !tId.isEmpty()) {
            id = Integer.parseInt(tId);
        }
        List<Meeting> meetings = meetingService.getMeetings(id, null, null, null, null, null);
        List<String> staff = new StaffService().getStaffNames(null);
        request.setAttribute("meetings", meetings.get(0));
        request.setAttribute("staff", staff);
        request.getRequestDispatcher("meeting-detail.jsp").forward(request, response);
    }

    private void addMeeting(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        String date = request.getParameter("date");
        date += " 00:00:00";
        String location = request.getParameter("location");
        String description = request.getParameter("description");
        String[] participants = request.getParameterValues("participants");
        String participant = String.join(",", participants);
        Meeting meeting = new Meeting();
        meeting.setTitle(title);
        meeting.setDate(Timestamp.valueOf(date));
        meeting.setDescription(description);
        meeting.setLocation(location);
        meeting.setParticipants(participant);
        Integer i = meetingService.addMeeting(meeting);
        if (i != null) {
            response.sendRedirect("meeting?action=list");
        } else {
            response.sendRedirect("meeting-add.jsp?error=1");
        }
    }

    private void updateMeeting(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String tId = request.getParameter("id");
        Integer id = null;
        if (tId != null && !tId.isEmpty()) {
            id = Integer.parseInt(tId);
        }
        String location = request.getParameter("location");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String[] participants = request.getParameterValues("participants");
        String participant = String.join(",", participants);
        System.out.println(participant);
        Meeting meeting = new Meeting();
        meeting.setTitle(title);
        meeting.setLocation(location);
        meeting.setDate(new Timestamp(System.currentTimeMillis()));
        meeting.setDescription(description);
        meeting.setId(id);
        meeting.setParticipants(participant);
        Integer i = meetingService.updateMeeting(meeting);
        if (i != null) {
            response.sendRedirect("meeting?action=list");
        } else {
            response.sendRedirect("meeting-edit.jsp?error=1");
        }
    }

    private void deleteMeeting(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        Integer i = meetingService.deleteMeeting(Integer.parseInt(id));
        if (i != null) {
            response.sendRedirect("meeting?action=list");
        } else {
            response.sendRedirect("meeting?action=list&error=1");
        }
    }

}