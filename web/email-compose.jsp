<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>发邮件</title>
    <!-- 引入 Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container my-4">
<h1 class="text-center mb-4">发邮件</h1>

<!-- 发邮件表单 -->
<form action="email?action=send&user=0" method="post" class="card p-4 shadow-sm">
    <!-- 收件人 -->
    <div class="mb-3">
        <label for="recipient" class="form-label">收件人</label>
        <select id="recipient" name="recipient" class="form-select" required>
            <option value="" disabled selected>请选择收件人</option>
            <c:forEach var="user" items="${userList}">
                <option value="${user.id}">${user.name} (${user.email})</option>
            </c:forEach>
        </select>
    </div>

    <!-- 标题 -->
    <div class="mb-3">
        <label for="subject" class="form-label">标题</label>
        <input type="text" id="subject" name="subject" class="form-control" placeholder="请输入邮件标题" required>
    </div>

    <!-- 正文 -->
    <div class="mb-3">
        <label for="content" class="form-label">正文</label>
        <textarea id="content" name="content" class="form-control" rows="6" placeholder="请输入邮件内容" required></textarea>
    </div>

    <!-- 提交按钮 -->
    <div class="d-flex justify-content-end">
        <button type="reset" class="btn btn-secondary me-2">重置</button>
        <button type="submit" class="btn btn-primary">发送邮件</button>
    </div>
</form>

<!-- 引入 Bootstrap 的 JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
