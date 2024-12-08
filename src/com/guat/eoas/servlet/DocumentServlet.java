package com.guat.eoas.servlet;

import com.guat.eoas.pojo.Documents;
import com.guat.eoas.service.DocumentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/document")
public class DocumentServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    private DocumentService service;

    @Override
    public void init() {
        service = new DocumentService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        switch (action) {
            case "list":
                System.out.println("doc list");
                getDocuments(request, response);
                break;
            case "id":
                System.out.println("doc id");
                getDocumentById(request, response);
                break;
            case "update":
                System.out.println("doc update");
                updateDocument(request, response);
                break;
            case "delete":
                System.out.println("doc delete");
                deleteDocument(request, response);
                break;
            case "add":
                System.out.println("doc add");
                addDocument(request, response);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void getDocuments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Tid = request.getParameter("id");
        Integer id = null;
        if (Tid != null && !Tid.isEmpty()) {
            id = Integer.valueOf(Tid);
        }
        String title = request.getParameter("title");
        String TAuthor = request.getParameter("author");
        Integer author = null;
        if (TAuthor != null && !TAuthor.isEmpty()) {
            author = Integer.valueOf(TAuthor);
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
            // 如果传入的日期是 yyyy-MM-dd 格式，补全为 yyyy-MM-dd 23:59:59
            if (TEndDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
                TEndDate += " 23:59:59";
            }
            System.out.println(TEndDate);
            endDate = Timestamp.valueOf(TEndDate);
        }
        String status = request.getParameter("status");
        String name = request.getParameter("name");
        List<Documents> list = service.getDocuments(id, title, author, startDate, endDate, status, name);
        request.setAttribute("documents", list);
        if (id == null) {
            request.getRequestDispatcher("document.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("doc-detail.jsp").forward(request, response);
        }
    }

    private void getDocumentById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        List<Documents> documents = service.getDocuments(id, null, null, null, null, null, null);
        System.out.println(documents.get(0));
        request.setAttribute("document", documents.get(0));
        request.getRequestDispatcher("doc-edit.jsp").forward(request, response);
    }

    private void addDocument(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        Documents documents = new Documents();
        documents.setTitle(title);
        documents.setContent(content);
        documents.setAuthor_id(Integer.valueOf(id));
        Integer i = service.addDocument(documents);
        if (i > 0) {
            response.sendRedirect("document?action=list");
        } else {
            response.sendRedirect("doc-add.jsp?error=1");
        }
    }

    private void updateDocument(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("d_id");
        String title = request.getParameter("title");
        String author_id = request.getParameter("author_id");
        System.out.println(author_id);
        String content = request.getParameter("content");
        String status = request.getParameter("status");
        Documents document = new Documents();
        document.setD_id(Integer.valueOf(id));
        document.setTitle(title);
        document.setAuthor_id(Integer.valueOf(author_id));
        document.setContent(content);
        document.setD_status(status);
        Integer flag = service.updateDocument(document);
        if (flag > 0) {
            response.sendRedirect("document?action=list");
        } else {
            response.sendRedirect("document?action=list&error=1");
        }
    }

    private void deleteDocument(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        System.out.println(id);
        Integer flag = service.deleteDocument(id);
        if (flag > 0) {
            response.sendRedirect("document?action=list");
        } else {
            response.sendRedirect("document?action=list&error=1");
        }
    }
}