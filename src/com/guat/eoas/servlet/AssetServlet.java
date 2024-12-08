package com.guat.eoas.servlet;

import com.guat.eoas.pojo.Asset;
import com.guat.eoas.service.AssetService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serial;
import java.sql.Date;
import java.util.List;

@WebServlet("/asset")
public class AssetServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    private AssetService service;

    @Override
    public void init() {
        service = new AssetService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        switch (action) {
            case "list":
                System.out.println("asset list");
                getAsset(request, response);
                break;
            case "add":
                System.out.println("asset add");
                addAsset(request, response);
                break;
            case "edit":
                System.out.println("asset edit");
                updateAsset(request, response);
                break;
            case "delete":
                System.out.println("asset delete");
                deleteAsset(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void getAsset(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Tid = request.getParameter("id");
        Integer id = null;
        if (Tid != null && !Tid.isEmpty()) {
            id = Integer.valueOf(Tid);
        }
        String type = request.getParameter("type");
        String quantity = request.getParameter("quantity");
        String status = request.getParameter("status");
        String location = request.getParameter("location");
        List<Asset> list = service.getAllAssets(id, type, quantity, status, location);
        request.setAttribute("assets", list);
        request.getRequestDispatcher("asset.jsp").forward(request, response);
    }

    private void addAsset(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String quantity = request.getParameter("quantity");
        String location = request.getParameter("location");
        Asset asset = new Asset();
        asset.setName(name);
        asset.setType(type);
        asset.setQuantity(Integer.valueOf(quantity));
        asset.setLocation(location);
        Integer i = service.addAsset(asset);
        if (i > 0) {
            response.sendRedirect("asset?action=list");
        } else {
            response.sendRedirect("asset?action=list&error=1");
        }
    }

    private void updateAsset(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        String quantity = request.getParameter("quantity");
        String location = request.getParameter("location");
        String status = request.getParameter("status");
        Asset asset = new Asset();
        asset.setId(id);
        asset.setName(name);
        asset.setType(type);
        asset.setQuantity(Integer.valueOf(quantity));
        asset.setLocation(location);
        asset.setStatus(status);
        Integer i = service.updateAsset(asset);
        if (i > 0) {
            response.sendRedirect("asset?action=list");
        } else {
            response.sendRedirect("asset?action=list&error=1");
        }
    }

    private void deleteAsset(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Integer i = service.deleteAsset(id);
        System.out.println(i);
        if (i > 0) {
            response.sendRedirect("asset?action=list");
        } else {
            response.sendRedirect("asset?action=list&error=1");
        }
    }
}