package com.guat.eoas.servlet;

import com.guat.eoas.pojo.Files;
import com.guat.eoas.service.FileService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@WebServlet("/file")
public class FileServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    FileService service;

    @Override
    public void init() {
        service = new FileService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        switch (action) {
            case "list":
                System.out.println("file list");
                browseFileList(request, response);
                break;
            case "download":
                System.out.println("file download");
                downloadFile(request, response);
                break;
            case "delete":
                System.out.println("file delete");
                deleteFile(request, response);
                break;
            case "upload":
                System.out.println("file upload");
                uploadFile(request, response);
                break;
            case "browse":
                System.out.println("file browse");
                browseFile(request, response);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void browseFileList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Files> files = service.getAllFiles();
        request.setAttribute("files", files);
        request.getRequestDispatcher("file.jsp").forward(request, response);
    }

    private void downloadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idStr = request.getParameter("id");
        if (idStr == null || idStr.isEmpty()) {
            response.getWriter().write("文件 ID 不能为空！");
            return;
        }

        int id = Integer.parseInt(idStr);
        Files files = service.getFileById(id);
        if (files == null) {
            response.getWriter().write("文件不存在！");
            return;
        }


        String uploadPath = "D:\\JavaProjects\\EOAS\\web\\WEB-INF\\upload";
        File directory = new File(uploadPath);
        File[] matchingFiles = directory.listFiles((dir, name) -> name.endsWith("_" + files.getName()));
        if (matchingFiles == null || matchingFiles.length == 0) {
            response.getWriter().write("文件不存在或已被删除！");
            return;
        }
        File file = matchingFiles[0];

        // 设置响应头，准备下载
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" +
                java.net.URLEncoder.encode(files.getName(), StandardCharsets.UTF_8));
        response.setContentLength((int) file.length());

        // 读取文件并写入响应输出流
        try (FileInputStream fis = new FileInputStream(file);
             ServletOutputStream os = response.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("文件下载失败！");
        }
    }

    private void deleteFile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String idStr = request.getParameter("id");
        if (idStr == null || idStr.isEmpty()) {
            response.getWriter().write("文件ID不能为空！");
            return;
        }
        Integer id = Integer.parseInt(idStr);
        boolean success = service.deleteFileById(id);

        // 删除服务器上的文件
        Files files = service.getFileById(id);
        if (files != null) {
            String filePath = "D:\\JavaProjects\\EOAS\\web\\WEB-INF\\upload" + files.getPath();
            File file = new File(filePath);
            if (file.exists() && file.delete()) {
                System.out.println("服务器文件删除成功：" + filePath);
            } else {
                System.out.println("服务器文件删除失败：" + filePath);
            }
        }

        if (success) {
            request.setAttribute("message", "删除成功");
        } else {
            request.setAttribute("message", "删除失败");
        }
        request.getRequestDispatcher("file?action=list").forward(request, response);
    }

    private void uploadFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uploadPath = "D:\\JavaProjects\\EOAS\\web\\WEB-INF\\upload";
        String message = "";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists() && !uploadDir.isDirectory()) {
            uploadDir.mkdir();
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");

        if (!ServletFileUpload.isMultipartContent(request)) {
            response.getWriter().write("错误：表单不是多部分内容！");
            return;
        }

        try {
            List<FileItem> list = upload.parseRequest(request);
            for (FileItem item : list) {
                if (item.isFormField()) {
                    // 处理普通表单字段
                    String name = item.getFieldName();
                    String value = item.getString("UTF-8");
                    System.out.println(name + "=" + value);
                } else {
                    // 处理文件上传字段
                    String fileName = item.getName();
                    if (fileName == null || fileName.trim().isEmpty()) {
                        continue;
                    }

                    // 处理文件名，避免路径问题
                    fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
                    // 添加唯一标识到文件名，避免文件名冲突
                    String uuid = UUID.randomUUID().toString();
                    String uniqueFileName = uuid + "_" + fileName;
                    // 获取文件类型（通过 MIME 类型）
                    String fileType = getServletContext().getMimeType(fileName);
                    if (fileType == null) {
                        fileType = "unknown"; // 如果无法识别类型
                    }

                    // 保存文件到指定目录
                    File storeFile = new File(uploadPath + File.separator + uniqueFileName);
                    item.write(storeFile);
                    System.out.println("storeFile:" + storeFile.getAbsolutePath());

                    // 将文件信息存储到数据库
                    Files files = new Files();
                    files.setName(fileName);
                    files.setPath("/WEB-INF/upload/" + uniqueFileName);
                    files.setType(fileType);
                    service.saveFile(files);

                    message = "文件上传成功：" + fileName;
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
            message = "文件上传失败：解析请求时出错！";
        } catch (Exception e) {
            e.printStackTrace();
            message = "文件上传失败：" + e.getMessage();
        }

        // 设置消息并跳转到文件列表页面
        request.setAttribute("message", message);
        response.sendRedirect("file?action=list");
    }

    private void browseFile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String idStr = request.getParameter("id");
        if (idStr == null || idStr.isEmpty()) {
            response.getWriter().write("文件 ID 不能为空！");
            return;
        }

        int id = Integer.parseInt(idStr);
        Files files = service.getFileById(id);
        if (files == null) {
            response.getWriter().write("文件不存在！");
            return;
        }

        // 获取文件路径
        String uploadPath = "D:\\JavaProjects\\EOAS\\web\\WEB-INF\\upload";
        File directory = new File(uploadPath);
        File[] matchingFiles = directory.listFiles((dir, name) -> name.endsWith("_" + files.getName()));
        if (matchingFiles == null || matchingFiles.length == 0) {
            response.getWriter().write("文件不存在或已被删除！");
            return;
        }
        File file = matchingFiles[0];

        // 判断是否为文本文件（可根据 MIME 类型或后缀名）
        if (!file.getName().endsWith(".txt")) {
            response.getWriter().write("仅支持查看文本文件内容！");
            return;
        }

        // 读取文件内容并返回
        StringBuilder fileContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            response.setContentType("text/plain;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            response.getWriter().write("读取文件内容失败！");
        }
        String fullFileName = file.getName();
        String originalFileName = fullFileName.substring(fullFileName.indexOf("_") + 1);

        // 将文件内容传递到 JSP 页面
        request.setAttribute("fileContent", fileContent.toString());
        request.setAttribute("fileName", originalFileName);
        request.getRequestDispatcher("file?action=list").forward(request, response);
    }

}