<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>会议记录</title>
    <!-- 引入 Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }

        h2 {
            color: #343a40;
        }

        .table {
            background-color: #ffffff;
        }

        .btn-sm {
            margin-right: 5px;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center mb-4">会议记录</h2>
    <!-- 表格部分 -->
    <table class="table table-bordered table-hover shadow-sm">
        <thead class="table-dark">
        <tr>
            <th>会议标题</th>
            <th>会议日期</th>
            <th>会议地点</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="meeting" items="${meetings}">
            <tr>
                <td>${meeting.title}</td>
                <td>${meeting.date}</td>
                <td>${meeting.location}</td>
                <td>
                    <button class="btn btn-primary btn-sm" onclick="edit(${meeting.id})">查看</button>
<%--                    <button class="btn btn-warning btn-sm" onclick="edit(${meeting.id})">修改</button>--%>
                    <button class="btn btn-danger btn-sm" onclick="del(${meeting.id})">删除</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <!-- 底部操作按钮 -->
    <div class="d-flex justify-content-between mt-4">
        <a href="meeting?action=list&doing=add" class="btn btn-success">录入会议</a>
    </div>
</div>
<!-- 引入 Bootstrap JS（可选） -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>


<script>
    function check(id) {
        window.location.href = 'meeting?action=list&id=' + id;
    }

    function edit(id) {
        window.location.href = 'meeting?action=id&id=' + id;
    }

    function del(id) {
        window.location.href = 'meeting?action=delete&id=' + id;
    }
</script>
</body>
</html>
