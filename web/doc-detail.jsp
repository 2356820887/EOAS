<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>公文详情</title>
    <!-- 引入 Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <!-- 返回按钮 -->
    <div class="d-flex justify-content-start mb-3">
        <a href="javascript:history.back()" class="btn btn-secondary">返回</a>
    </div>

    <!-- 公文详情 -->
    <div class="card">
        <div class="card-header">
            <h2 class="card-title">${documents[0].title}</h2>
        </div>
        <div class="card-body">
            <h5 class="card-subtitle mb-2 text-muted">作者: ${documents[0].author_name}</h5>
            <p class="text-muted">更新时间: ${documents[0].updated_at}</p>
            <p class="card-text">${documents[0].content}</p>
        </div>
    </div>
</div>

<!-- 引入 Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
