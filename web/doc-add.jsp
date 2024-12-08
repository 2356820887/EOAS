<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>新增公文</title>
    <!-- 引入 Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow">
                <div class="card-header bg-primary text-white">
                    <h4 class="mb-0">新增公文</h4>
                </div>
                <div class="card-body">
                    <form action="document?action=add" method="post">
                        <!-- 错误提示 -->
                        <c:if test="${error == 1}">
                            <div class="alert alert-danger" role="alert">
                                出现错误，请重试
                            </div>
                        </c:if>
                        <!-- 隐藏字段 -->
                        <input type="hidden" name="id" value="${user.id}">
                        <!-- 标题 -->
                        <div class="mb-3">
                            <label for="title" class="form-label">标题</label>
                            <input type="text" class="form-control" name="title" id="title" placeholder="请输入标题" value="${param.title}">
                        </div>
                        <!-- 正文 -->
                        <div class="mb-3">
                            <label for="content" class="form-label">正文</label>
                            <textarea class="form-control" name="content" id="content" rows="4" placeholder="请输入正文">${param.content}</textarea>
                        </div>
                        <!-- 按钮组 -->
                        <div class="d-flex justify-content-between">
                            <!-- 返回按钮 -->
                            <a href="document?action=list" class="btn btn-secondary">返回</a>
                            <!-- 确认按钮 -->
                            <button type="submit" class="btn btn-primary">确认</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 引入 Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
