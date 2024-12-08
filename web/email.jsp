<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>邮箱</title>
    <!-- 引入 Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* 隐藏不需要的列表 */
        .email-list {
            display: none;
        }

        /* 显示当前选中的列表 */
        .active {
            display: block;
        }
    </style>
</head>
<body class="container my-4">
<h1 class="text-center mb-4">邮箱</h1>

<!-- 按钮 -->
<div class="d-flex justify-content-between mb-3">
    <div>
        <button id="inboxButton" class="btn btn-secondary me-2" onclick="showInbox()">收件箱</button>
        <button id="sentButton" class="btn btn-primary" onclick="showSent()">已发送</button>
    </div>
    <a href="email?action=send&user=1" class="btn btn-success">发邮件</a>
</div>

<!-- 收件箱邮件列表 -->
<div id="inboxList" class="email-list active">
    <h2>收件箱</h2>
    <table class="table table-striped table-bordered">
        <thead class="table-dark">
        <tr>
            <th>标题</th>
            <th>发送时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="email" items="${receiverList}">
            <tr>
                <td>${email.subject}</td>
                <td>${email.send_time}</td>
                <td>
                    <a href="email?action=view&id=${email.id}" class="btn btn-sm btn-info">查看</a>
                    <a href="email?action=delete&id=${email.id}" class="btn btn-sm btn-danger">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- 已发送邮件列表 -->
<div id="sentList" class="email-list">
    <h2>已发送</h2>
    <table class="table table-striped table-bordered">
        <thead class="table-dark">
        <tr>
            <th>标题</th>
            <th>发送时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="email" items="${sendList}">
            <tr>
                <td>${email.subject}</td>
                <td>${email.send_time}</td>
                <td>
                    <a href="email?action=view&id=${email.id}" class="btn btn-sm btn-info">查看</a>
                    <a href="email?action=delete&id=${email.id}" class="btn btn-sm btn-danger">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- 显示邮件内容 -->
<c:if test="${not empty email}">
    <h2>邮件内容</h2>
    <div class="card p-3">
        <p><strong>标题:</strong> ${email.subject}</p>
        <p><strong>发件人:</strong> ${staff}</p>
        <p><strong>发件时间:</strong> ${email.send_time}</p>
        <p><strong>内容:</strong></p>
        <div>${email.content}</div>
    </div>
</c:if>

<!-- 引入 Bootstrap 的 JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript">
    function showInbox() {
        document.getElementById("inboxList").classList.add("active");
        document.getElementById("sentList").classList.remove("active");

        // 调整按钮样式
        document.getElementById("inboxButton").classList.remove("btn-primary");
        document.getElementById("inboxButton").classList.add("btn-secondary");

        document.getElementById("sentButton").classList.remove("btn-secondary");
        document.getElementById("sentButton").classList.add("btn-primary");
    }

    function showSent() {
        document.getElementById("sentList").classList.add("active");
        document.getElementById("inboxList").classList.remove("active");

        // 调整按钮样式
        document.getElementById("sentButton").classList.remove("btn-primary");
        document.getElementById("sentButton").classList.add("btn-secondary");

        document.getElementById("inboxButton").classList.remove("btn-secondary");
        document.getElementById("inboxButton").classList.add("btn-primary");
    }
</script>
</body>
</html>
