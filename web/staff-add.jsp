<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>员工新增</title>
    <!-- 引入 Bootstrap 样式 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>

<div class="container mt-5">
    <h2 class="text-center mb-4">新增员工</h2>

    <!-- 表单区域 -->
    <form action="staff" method="post">
        <input type="hidden" name="action" value="add">

        <div class="mb-3">
            <label for="name" class="form-label">姓名：</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>

        <div class="mb-3">
            <label for="password" class="form-label">密码：</label>
            <input type="password" class="form-control" id="password" name="password">
        </div>

        <div class="mb-3">
            <label for="email" class="form-label">邮箱：</label>
            <input type="email" class="form-control" id="email" name="email" required>
        </div>

        <div class="mb-3">
            <label for="gender" class="form-label">性别：</label>
            <select id="gender" name="gender" class="form-select" required>
                <option value="男">男</option>
                <option value="女">女</option>
            </select>
        </div>

        <div class="mb-3">
            <label for="department" class="form-label">部门：</label>
            <input type="text" class="form-control" id="department" name="department" required>
        </div>

        <div class="mb-3">
            <label for="position" class="form-label">职位：</label>
            <input type="text" class="form-control" id="position" name="position" required>
        </div>

        <div class="mb-3">
            <label for="phone" class="form-label">电话：</label>
            <input type="tel" class="form-control" id="phone" name="phone" required>
        </div>

        <div class="text-center">
            <button type="submit" class="btn btn-primary">提交</button>
        </div>
    </form>
</div>

<!-- 引入 Bootstrap 和 JS 依赖 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>
