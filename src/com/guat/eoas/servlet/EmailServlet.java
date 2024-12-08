package com.guat.eoas.servlet;

import com.guat.eoas.pojo.Email;
import com.guat.eoas.pojo.Staff;
import com.guat.eoas.pojo.User;
import com.guat.eoas.service.EmailService;
import com.guat.eoas.service.StaffService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.util.List;

@WebServlet("/email")
public class EmailServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    private EmailService service;

    public void init() {
        service = new EmailService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        switch (action) {
            case "inbox":
                System.out.println("inbox");
                getInbox(request, response);
                break;
            case "view":
                System.out.println("email view");
                getEmail(request, response);
                break;
            case "send":
                System.out.println("send");
                composeEmail(request, response);
                break;
            case "delete":
                System.out.println("delete");
                deleteEmail(request, response);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void getInbox(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从 session 获取当前登录用户的 ID
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            // 如果用户没有登录（即 session 中没有 user.id），可以返回错误或者重定向到登录页面
            response.sendRedirect("login.jsp");
            return;
        }
        Integer uid = user.getId();
        // 使用获取到的 uid 查询邮件列表
        List<Email> sendList = service.getEmails(null, uid, null, null);  // 获取发送的邮件
        List<Email> receiverList = service.getEmails(null, null, uid, null);  // 获取接收的邮件

        // 将邮件列表传递给 JSP 页面
        request.setAttribute("sendList", sendList);
        request.setAttribute("receiverList", receiverList);

        // 跳转到邮箱页面
        request.getRequestDispatcher("email.jsp").forward(request, response);
    }

    private void getEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        List<Email> email = service.getEmails(Integer.valueOf(id), null, null, null);
        List<String> staffName = new StaffService().getStaffNames(email.get(0).getSender_id());
        request.setAttribute("email", email.get(0));
        request.setAttribute("staff", staffName.get(0));
        request.getRequestDispatcher("email?action=inbox").forward(request, response);
    }

    private void composeEmail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userId = request.getParameter("user");
        if (userId.equals("1")) {
            List<Staff> userList = new StaffService().getStaff(null, null, null, null, null);
            System.out.println(userList);
            request.setAttribute("userList", userList);
            request.getRequestDispatcher("email-compose.jsp").forward(request, response);
        } else {
            User user = (User) request.getSession().getAttribute("user");
            Integer uid = user.getId();
            String rid = request.getParameter("recipient");
            String subject = request.getParameter("subject");
            String content = request.getParameter("content");
            Email email = new Email();
            email.setSender_id(uid);
            email.setReceiver_id(Integer.valueOf(rid));
            email.setSubject(subject);
            email.setContent(content);
            Integer i = service.addEmail(email);
            if (i > 0) {
                response.sendRedirect("email?action=inbox");
            } else {
                response.sendRedirect("email-compose.jsp?error=1");
            }
        }
    }

    private void deleteEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Integer i = service.deleteEmail(id);
        if (i > 0) {
            response.sendRedirect("email?action=inbox");
        } else {
            response.sendRedirect("email?action=inbox&error=1");
        }
    }
}