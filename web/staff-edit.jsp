<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <title>员工信息编辑</title>
    <style>
        .container {
            max-width: 600px;
            margin-top: 50px;
        }
        .form-label {
            font-weight: bold;
        }
        .form-group {
            margin-bottom: 1.5rem;
        }
        .btn-primary {
            width: 100%;
        }
    </style>
</head>
<body>

<div class="container">
    <h2 class="text-center mb-4">修改员工信息</h2>
    <form action="staff?action=update&id=${staff[0].id}" method="post">
        <!-- 员工姓名 -->
        <div class="form-group">
            <label for="name" class="form-label">姓名</label>
            <input type="text" class="form-control" id="name" name="name" value="${staff[0].name}" required>
        </div>

        <!-- 性别 -->
        <div class="form-group">
            <label for="gender" class="form-label">性别</label>
            <select class="form-select" id="gender" name="gender">
                <option value="男" <c:if test="${staff[0].gender == '男'}">selected</c:if>>男</option>
                <option value="女" <c:if test="${staff[0].gender == '女'}">selected</c:if>>女</option>
            </select>
        </div>

        <!-- 邮箱 -->
        <div class="form-group">
            <label for="email" class="form-label">邮箱</label>
            <input type="email" class="form-control" id="email" name="email" value="${staff[0].email}" required>
        </div>

        <!-- 部门 -->
        <div class="form-group">
            <label for="department" class="form-label">部门</label>
            <input type="text" class="form-control" id="department" name="department" value="${staff[0].department}" required>
        </div>

        <!-- 职位 -->
        <div class="form-group">
            <label for="position" class="form-label">职位</label>
            <input type="text" class="form-control" id="position" name="position" value="${staff[0].position}" required>
        </div>

        <!-- 电话 -->
        <div class="form-group">
            <label for="phone" class="form-label">电话</label>
            <input type="tel" class="form-control" id="phone" name="phone" value="${staff[0].phone}" required>
        </div>

        <!-- 权限 -->
        <div class="form-group">
            <label for="role" class="form-label">权限</label>
            <select class="form-select" id="role" name="role">
                <option value="员工" <c:if test="${staff[0].role == '员工'}">selected</c:if>>员工</option>
                <option value="经理" <c:if test="${staff[0].role == '经理'}">selected</c:if>>经理</option>
                <option value="管理员" <c:if test="${staff[0].role == '管理员'}">selected</c:if>>管理员</option>
            </select>
        </div>

        <!-- 状态 -->
        <div class="form-group">
            <label for="status" class="form-label">状态</label>
            <select class="form-select" id="status" name="status">
                <option value="激活" <c:if test="${staff[0].status == '激活'}">selected</c:if>>激活</option>
                <option value="禁用" <c:if test="${staff[0].status == '禁用'}">selected</c:if>>禁用</option>
            </select>
        </div>

        <!-- 提交按钮 -->
        <div class="form-group">
            <button type="submit" class="btn btn-primary">提交修改</button>
        </div>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>

</body>
</html>
