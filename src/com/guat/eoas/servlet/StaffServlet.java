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
import java.io.IOException;
import java.io.Serial;
import java.util.List;

@WebServlet("/staff")
public class StaffServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    private StaffService service;

    @Override
    public void init() {
        service = new StaffService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        switch (action) {
            case "list":
                System.out.println("staff list");
                getAllStaff(request, response);
                break;
            case "add":
                System.out.println("staff add");
                addStaff(request, response);
                break;
            case "delete":
                System.out.println("staff delete");
                deleteStaff(request, response);
                break;
            case "update":
                System.out.println("staff update");
                updateStaff(request, response);
                break;
            case "id":
                System.out.println("staff id");
                getStaffById(request, response);
                break;
            default:
                System.out.println("action not found");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action: " + action);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void getAllStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        Integer id = null;
        if (idParam != null && !idParam.isEmpty()) {
            id = Integer.valueOf(idParam);
        }
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        String status = request.getParameter("status");
        List<Staff> list = service.getStaff(id, name, email, role, status);
        request.setAttribute("staffList", list);
        request.getRequestDispatcher("staff-list.jsp").forward(request, response);
    }

    private void getStaffById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        List<Staff> staff = service.getStaff(Integer.valueOf(id), null, null, null, null);
        request.setAttribute("staff", staff);
        request.getRequestDispatcher("staff-edit.jsp").forward(request, response);
    }

    private void addStaff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String department = request.getParameter("department");
        String position = request.getParameter("position");
        String phone = request.getParameter("phone");
        Staff staff = new Staff();
        User user = new User();
        staff.setName(name);
        staff.setEmail(email);
        staff.setGender(gender);
        staff.setDepartment(department);
        staff.setPosition(position);
        staff.setPhone(phone);
        user.setUsername(name);
        user.setPassword(password);
        Integer i = service.addStaff(staff);
        Integer id = service.getStaff(null, null, email, null, null).get(0).getId();
        user.setId(id);
        Integer j = new UserService().addUser(user);
        if (i > 0 && j > 0) {
            response.sendRedirect("staff?action=list");
        } else {
            response.sendRedirect("staff?action=add&error=1");
        }
    }

    private void deleteStaff(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        System.out.println("delID" + id);
        Integer i = service.deleteStaff(Integer.valueOf(id));
        if (i > 0) {
            response.sendRedirect("staff?action=list");
        } else {
            response.sendRedirect("staff?errDel=1&action=list");
        }
    }

    private void updateStaff(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        String status = request.getParameter("status");
        String gender = request.getParameter("gender");
        String department = request.getParameter("department");
        String position = request.getParameter("position");
        String phone = request.getParameter("phone");
        Staff staff = new Staff();
        staff.setId(Integer.valueOf(id));
        staff.setName(name);
        staff.setEmail(email);
        staff.setRole(role);
        staff.setStatus(status);
        staff.setGender(gender);
        staff.setDepartment(department);
        staff.setPosition(position);
        staff.setPhone(phone);
        Integer i = service.updateStaff(staff);
        if (i > 0) {
            response.sendRedirect("staff?action=list");
        } else {
            response.sendRedirect("staff-edit?error=1");
        }
    }
}
