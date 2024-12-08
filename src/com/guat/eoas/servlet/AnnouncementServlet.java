package com.guat.eoas.servlet;

import com.guat.eoas.pojo.Announcement;
import com.guat.eoas.service.AnnouncementService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/anno")
public class AnnouncementServlet extends HttpServlet {
    private AnnouncementService service;
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    public void init() {
        service = new AnnouncementService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        switch (action) {
            case "list":
                System.out.println("anno list");
                getAnnouncement(request, response);
                break;
            case "new":
                System.out.println("anno new");
                getNewAnno(request, response);
                break;
            case "delete":
                System.out.println("anno delete");
                deleteAnnouncement(request, response);
                break;
            case "add":
                System.out.println("anno add");
                addAnnouncement(request, response);
                break;
            case "edit":
                System.out.println("anno edit");
                updateAnnouncement(request, response);
                break;
            default:
                getAnnouncement(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void getAnnouncement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Tid = request.getParameter("id");
        Integer id = null;
        if (Tid != null && !Tid.isEmpty()) {
            id = Integer.valueOf(Tid);
        }
        String title = request.getParameter("title");
        String TStartDate = request.getParameter("startDate");
        Timestamp startDate = null;
        if (TStartDate != null && !TStartDate.isEmpty()) {
            if (TStartDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                TStartDate += " 00:00:00";
            }
            System.out.println(TStartDate);
            startDate = Timestamp.valueOf(TStartDate);
        }
        String TEndDate = request.getParameter("endDate");
        Timestamp endDate = null;
        if (TEndDate != null && !TEndDate.isEmpty()) {
            if (TEndDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                TEndDate += " 23:59:59";
            }
            System.out.println(TEndDate);
            endDate = Timestamp.valueOf(TEndDate);
        }
        List<Announcement> anno = service.getAll(id, title, startDate, endDate);
        request.setAttribute("anno", anno);
        request.getRequestDispatcher("anno-list.jsp").forward(request, response);
    }

    private void getNewAnno(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Announcement announcement = service.getLatestAnnouncement();
        request.setAttribute("newAnno", announcement);
        request.getRequestDispatcher("anno.jsp").forward(request, response);
    }

    private void addAnnouncement(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        Announcement announcement = new Announcement();
        announcement.setTitle(title);
        announcement.setContent(content);
        Integer i = service.addAnnouncement(announcement);
        if (i > 0) {
            response.sendRedirect("anno?action=list");
        } else {
            response.sendRedirect("anno?action=list&error=1");
        }
    }

    private void updateAnnouncement(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String Tid = request.getParameter("id");
        Integer id = null;
        if (Tid != null && !Tid.isEmpty()) {
            id = Integer.valueOf(Tid);
        }
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        Announcement announcement = new Announcement();
        announcement.setTitle(title);
        announcement.setContent(content);
        announcement.setId(id);
        Integer i = service.updateAnnouncement(announcement);
        if (i > 0) {
            response.sendRedirect("anno?action=list");
        } else {
            response.sendRedirect("anno?action=list&error=1");
        }
    }

    private void deleteAnnouncement(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Integer i = service.deleteAnnouncement(id);
        if (i > 0) {
            response.sendRedirect("anno?action=list");
        } else {
            response.sendRedirect("anno?action=list&error=1");
        }
    }
}
