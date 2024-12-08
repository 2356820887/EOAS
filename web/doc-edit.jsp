<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>公文修改</title>
    <!-- 引入 Bootstrap 样式 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <!-- 页面标题 -->
    <h1 class="text-center mb-4">公文修改</h1>
    <form action="document" method="post" class="p-4 border rounded shadow">
        <!-- 隐藏字段 -->
        <input type="hidden" name="action" value="update"/>
        <input type="hidden" name="d_id" value="${document.d_id}"/>
        <input type="hidden" name="author_id" value="${document.author_id}"/>

        <!-- 标题输入框 -->
        <div class="mb-3">
            <label for="title" class="form-label">标题</label>
            <input type="text" name="title" id="title" class="form-control" value="${document.title}"
                   placeholder="请输入标题" required>
        </div>

        <!-- 正文输入框 -->
        <div class="mb-3">
            <label for="content" class="form-label">正文</label>
            <textarea name="content" id="content" class="form-control" rows="5" placeholder="请输入正文内容"
                      required>${document.content}</textarea>
        </div>

        <!-- 状态选择框 -->
        <div class="mb-3">
            <label for="status" class="form-label">状态</label>
            <select name="status" id="status" class="form-select">
                <option value="草稿" <c:if test="${document.d_status == '草稿'}">selected</c:if>>草稿</option>
                <option value="已发布" <c:if test="${document.d_status == '已发布'}">selected</c:if>>已发布</option>
                <option value="归档" <c:if test="${document.d_status == '归档'}">selected</c:if>>归档</option>
            </select>
        </div>

        <!-- 提交按钮 -->
        <div class="text-center">
            <button type="submit" class="btn btn-primary">保存</button>
            <a href="document?action=list-list" class="btn btn-secondary">返回</a>
        </div>
    </form>
</div>

<!-- 引入 Bootstrap 脚本 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
