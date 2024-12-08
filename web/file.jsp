<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>文件管理</title>
    <!-- 引入 Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <div class="row">
        <!-- 左侧文件列表 -->
        <div class="col-md-4">
            <h4>文件列表</h4>
            <form action="file?action=upload" method="post" enctype="multipart/form-data" class="mb-3">
                <div class="input-group">
                    <input type="file" name="file" id="file" class="form-control" multiple>
                    <button class="btn btn-primary" type="submit">上传</button>
                </div>
            </form>
            <c:if test="${not empty message}">
                <div class="alert alert-info">${message}</div>
            </c:if>
            <table class="table table-hover table-bordered">
                <thead class="table-light">
                <tr>
                    <th>文件名</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${files != null && !files.isEmpty()}">
                    <c:forEach var="file" items="${files}">
                        <tr>
                            <td>${file.name}</td>
                            <td>
                                <a href="file?action=browse&id=${file.id}" class="btn btn-sm btn-info">查看</a>
                                <a href="file?action=download&id=${file.id}" class="btn btn-sm btn-success">下载</a>
                                <a href="file?action=delete&id=${file.id}" class="btn btn-sm btn-danger"
                                   onclick="return confirm('确定删除该文件吗？')">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${files == null || files.isEmpty()}">
                    <tr>
                        <td colspan="2" class="text-center">暂无文件</td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>

        <!-- 右侧文件内容显示 -->
        <div class="col-md-8">
            <h4>文件内容</h4>
            <c:if test="${not empty fileContent}">
                <div class="card">
                    <div class="card-header">
                        <strong>${fileName}</strong>
                    </div>
                    <div class="card-body">
                        <pre style="white-space: pre-wrap;">${fileContent}</pre>
                    </div>
                </div>
            </c:if>

            <c:if test="${empty fileContent}">
                <p class="text-muted">请选择文件查看内容。</p>
            </c:if>
        </div>
    </div>
</div>

<!-- 引入 Bootstrap JS（可选） -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
