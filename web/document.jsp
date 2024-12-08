<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>公文列表</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>公文列表</h2>
        <button class="btn btn-primary" onclick="add()">新增</button>
    </div>

    <!-- 搜索框 -->
    <form class="row g-3 mb-4" action="document">
        <input type="hidden" name="action" value="list">
        <div class="col-md-3">
            <label for="name" class="form-label">名字</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="请输入名字" value=${param.name}>
        </div>
        <div class="col-md-3">
            <label for="title" class="form-label">标题</label>
            <input type="text" class="form-control" id="title" name="title" placeholder="请输入标题" value=${param.title}>
        </div>
        <div class="col-md-3">
            <label for="startDate" class="form-label">起始日期</label>
            <input type="date" class="form-control" id="startDate" name="startDate" value=${param.startDate}>
        </div>
        <div class="col-md-3">
            <label for="endDate" class="form-label">结束日期</label>
            <input type="date" class="form-control" id="endDate" name="endDate" value=${param.endDate}>
        </div>
        <div class="col-md-3">
            <label for="status" class="form-label">状态</label>
            <select id="status" name="status" class="form-select">
                <option value="" <c:if test="${param.status == ''}"> selected</c:if>></option>
                <option value="草稿" <c:if test="${param.status == '草稿'}"> selected</c:if>>草稿</option>
                <option value="已发布" <c:if test="${param.status == '已发布'}"> selected</c:if>>已发布</option>
                <option value="归档" <c:if test="${param.status == '归档'}"> selected</c:if>>归档</option>
            </select>
        </div>
        <div class="col-auto" style="margin-top: 50px;">
            <button type="submit" class="btn btn-success">搜索</button>
            <input type="reset" class="btn btn-warning">
        </div>
    </form>

    <!-- 公文列表 -->
    <table class="table table-bordered table-hover">
        <thead class="table-dark">
        <tr>
            <th>id</th>
            <th>标题</th>
            <th>作者</th>
            <th>更新时间</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="document" items="${documents}">
            <tr>
                <td>${document.d_id}</td>
                <td>${document.title}</td>
                <td>${document.author_name}</td>
                <td>${document.updated_at}</td>
                <td>${document.d_status}</td>
                <td>
                    <button class="btn btn-primary btn-sm" onclick="checkDoc(${document.d_id})">查看</button>
                    <button class="btn btn-warning btn-sm" onclick="edit(${document.d_id})">修改</button>
                    <button class="btn btn-danger btn-sm" onclick="del(${document.d_id})">删除</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function checkDoc(id){
        console.log(id);
        window.location.href = "document?action=list&id=" + id;
    }
    function edit(id){
        console.log(id);
        window.location.href = "document?action=id&id=" + id;
    }
    function del(id){
        console.log(id);
        window.location.href = "document?action=delete&id=" + id;
    }
    function add(){
        window.location.href = "doc-add.jsp";
    }
</script>
</body>
</html>
