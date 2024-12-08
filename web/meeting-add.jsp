<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>录入会议</title>
    <!-- 引入 Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            max-width: 600px; /* 设置最大宽度，缩窄页面 */
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center mb-4">录入会议</h1>
    <form action="meeting?action=add" method="post">
        <div class="mb-3">
            <label for="title" class="form-label">标题</label>
            <input type="text" class="form-control" id="title" name="title" value="${meeting.title}" required>
        </div>
        <div class="mb-3">
            <label for="date" class="form-label">日期</label>
            <input type="date" class="form-control" id="date" name="date" value="${meeting.date}" required>
        </div>
        <div class="mb-3">
            <label for="location" class="form-label">地点</label>
            <input type="text" class="form-control" id="location" name="location" value="${meeting.location}" required>
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">会议内容</label>
            <textarea class="form-control" id="description" name="description" rows="4" placeholder="请输入会议内容...">${meeting.description}</textarea>
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
        <div class="text-center">
            <button type="submit" class="btn btn-primary">提交</button>
            <a href="index.jsp" class="btn btn-secondary">取消</a>
        </div>
    </form>
</div>
<!-- 引入 Bootstrap JS（可选） -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
