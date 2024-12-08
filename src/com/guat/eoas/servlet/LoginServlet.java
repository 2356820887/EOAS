package com.guat.eoas.servlet;

import com.guat.eoas.pojo.Staff;
import com.guat.eoas.pojo.User;
import com.guat.eoas.service.StaffService;
import com.guat.eoas.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serial;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        UserService service = new UserService();
        StaffService staffService = new StaffService();
        try {
            User user = service.login(username, password);
            if (user != null) {
                List<Staff> staff = staffService.getStaff(user.getId(), null, null, null, null);
                if (staff.get(0).getStatus().equals("激活")) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    if (remember != null) {
                        session.setMaxInactiveInterval(7 * 60 * 60);
                    }
                    session.setMaxInactiveInterval(30 * 60);
                    response.sendRedirect("index.jsp?page=anno.jsp");
                } else {
                    response.sendRedirect("login.jsp?error=3");
                }
            } else {
                response.sendRedirect("login.jsp?error=1");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doGet(request, response);
    }
}
