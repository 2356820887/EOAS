package com.guat.eoas.servlet;

import com.guat.eoas.pojo.Feedback;
import com.guat.eoas.pojo.Staff;
import com.guat.eoas.pojo.User;
import com.guat.eoas.service.FeedbackService;
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

@WebServlet("/feedback")
public class FeedbackServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    private FeedbackService service;

    @Override
    public void init() {
        service = new FeedbackService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        User user = (User) request.getSession().getAttribute("user");
        List<Staff> staff = new StaffService().getStaff(user.getId(), null, null, null, null);
        String role = staff.get(0).getRole();
        if (action.equals("list") & !role.equals("经理")) {
            action = "id";
        }

        switch (action) {
            case "list":
                System.out.println("feedback list");
                getFeedback(request, response);
                break;
            case "id":
                System.out.println("feedback id");
                getFeedbackById(request, response);
                break;
            case "add":
                System.out.println("feedback add");
                addFeedback(request, response);
                break;
            case "delete":
                System.out.println("feedback delete");
                deleteFeedback(request, response);
                break;
            case "update":
                System.out.println("feedback update");
                updateFeedback(request, response);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void getFeedback(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        List<Staff> staff = new StaffService().getStaff(user.getId(), null, null, null, null);
        String Tid = request.getParameter("id");
        Integer id = null;
        if (Tid != null && !Tid.isEmpty()) {
            id = Integer.valueOf(Tid);
        }
        String Tuid = request.getParameter("uid");
        Integer uid = null;
        if (Tid != null && !Tid.isEmpty()) {
            uid = Integer.valueOf(Tuid);
        }
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
        String status = request.getParameter("status");
        List<Feedback> list = service.findAll(id, uid, status, startDate, endDate);
        request.setAttribute("staff", staff.get(0));
        request.setAttribute("list", list);
        request.getRequestDispatcher("feedback.jsp").forward(request, response);
    }

    private void getFeedbackById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        List<Feedback> list = service.findAll(null, user.getId(), null, null, null);
        request.setAttribute("list", list);
        request.getRequestDispatcher("feedback.jsp").forward(request, response);
    }

    private void addFeedback(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String suggestion = request.getParameter("suggestion");
        Feedback feedback = new Feedback();
        feedback.setUser_id(Integer.valueOf(id));
        feedback.setSuggestion(suggestion);
        Integer i = service.addFeedback(feedback);
        if (i > 0) {
            response.sendRedirect("feedback?action=list&success=true");
        } else {
            response.sendRedirect("feedback?action=list&error=1");
        }
    }

    private void deleteFeedback(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Integer i = service.deleteFeedback(id);
        if (i > 0) {
            response.sendRedirect("feedback?action=list&delete=true");
        } else {
            response.sendRedirect("feedback?action=list&error=1");
        }
    }

    private void updateFeedback(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Integer uid = Integer.valueOf(request.getParameter("user_id"));
        String suggestion = request.getParameter("suggestion");
        String status = request.getParameter("status");
        Feedback feedback = new Feedback();
        feedback.setId(id);
        feedback.setUser_id(uid);
        feedback.setSuggestion(suggestion);
        feedback.setStatus(status);
        System.out.println("feedback:" + feedback);
        Integer i = service.updateFeedback(feedback);
        System.out.println("i:" + i);
        if (i > 0) {
            response.sendRedirect("feedback?action=list&success=true");
        } else {
            response.sendRedirect("feedback?action=list&error=1");
        }
    }
}