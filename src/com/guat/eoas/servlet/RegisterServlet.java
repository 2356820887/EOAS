package com.guat.eoas.servlet;

import com.guat.eoas.pojo.Staff;
import com.guat.eoas.pojo.User;
import com.guat.eoas.service.StaffService;
import com.guat.eoas.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serial;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String remember = request.getParameter("remember");

        User user = new User();
        Staff staff = new Staff();
        UserService userService = new UserService();
        StaffService staffService = new StaffService();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        user.setUsername(username);
        user.setPassword(password);
        Integer user1 = userService.addUser(user);

        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String department = request.getParameter("department");
        String position = request.getParameter("position");
        String phone = request.getParameter("phone");

        staff.setName(name);
        staff.setGender(gender);
        staff.setDepartment(department);
        staff.setPosition(position);
        staff.setPhone(phone);
        staff.setEmail(email);
        Integer staff1 = staffService.addStaff(staff);

        if (user1 != null && staff1 != null && staff1 > 0 && user1 > 0) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            if (remember != null && remember.equals("on")) {
                session.setMaxInactiveInterval(60 * 60);
            } else {
                session.setMaxInactiveInterval(10 * 60);
            }
            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("login.jsp?error=1");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}