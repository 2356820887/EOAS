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

        .meeting-card {
            background-color: #ffffff;
            border: 1px solid #dee2e6;
            border-radius: 10px;
            padding: 20px;
            margin-bottom: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        h2 {
            color: #343a40;
        }

        .participants {
            font-style: italic;
            color: #6c757d;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center mb-4">会议记录</h1>
    <div class="meeting-card">
        <h2>${meetings.title}</h2>
        <p><strong>日期：</strong>${meetings.date}</p>
        <p><strong>地点：</strong>${meetings.location}</p>
        <p><strong>描述：</strong>${meetings.description}</p>
        <p class="participants"><strong>参与者：</strong>${meetings.participants}</p>
    </div>

    <!-- 编辑会议表单 -->
    <h2 class="text-center mb-4">编辑会议</h2>
    <form action="meeting?action=edit" method="post">
        <input type="hidden" name="id" value="${meetings.id}"> <!-- 隐藏会议 ID，用于更新 -->
        <div class="mb-3">
            <label for="title" class="form-label">会议标题</label>
            <input type="text" class="form-control" id="title" name="title" value="${meetings.title}" required>
        </div>
        <div class="mb-3">
            <label for="location" class="form-label">地点</label>
            <input type="text" class="form-control" id="location" name="location" value="${meetings.location}" required>
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">会议描述</label>
            <textarea class="form-control" id="description" name="description" rows="4" required>${meetings.description}</textarea>
        </div>
        <div class="text-center mt-4">
            <button type="submit" class="btn btn-primary">保存修改</button>
            <a href="meeting?action=list" class="btn btn-secondary">返回</a>
        </div>
        <div class="mb-3">
            <label for="participants" class="form-label">参会人员</label>
            <div id="participants">
                <c:forEach var="staff" items="${staff}">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" name="participants" id="staff${staff}" value="${staff}">
                        <label class="form-check-label" for="staff${staff}">
                                ${staff}
                        </label>
                    </div>
                </c:forEach>
            </div>
        </div>
    </form>
</div>

<!-- 引入 Bootstrap JS（可选） -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
