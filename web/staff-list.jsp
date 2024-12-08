<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <title>Index</title>
    <style>
        .table {
            table-layout: fixed;
            width: 100%;
        }

        .table th, .table td {
            width: 4em;
            text-align: center;
            word-wrap: break-word;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        .table-responsive {
            overflow-x: auto;
        }

        /* 定位新增按钮 */
        .add-button {
            position: relative;
        }

        /* 给表单的按钮和输入框添加间距 */
        .search-btn-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
    </style>
</head>

<body>
<div class="container mt-3">
    <!-- 查询表单和新增按钮 -->
    <div class="row mb-3">
        <div class="col-10 search-btn-container">
            <form action="staff" method="get" class="row g-3 w-100">
                <input type="hidden" name="action" value="list" />
                <div class="col-auto">
                    <label for="name" class="form-label">用户名</label>
                    <input type="text" class="form-control" id="name" name="name" value="${param.name}">
                </div>
                <div class="col-auto">
                    <label for="email" class="form-label">邮箱</label>
                    <input type="text" class="form-control" id="email" name="email" value="${param.email}">
                </div>
                <div class="col-auto">
                    <label for="role" class="form-label">用户角色</label>
                    <select class="form-select" id="role" name="role">
                        <option value="" <c:if test="${param.role == ''}">selected</c:if>>全部</option>
                        <option value="员工" <c:if test="${param.role == '员工'}">selected</c:if>>员工</option>
                        <option value="经理" <c:if test="${param.role == '经理'}">selected</c:if>>经理</option>
                        <option value="管理员" <c:if test="${param.role == '管理员'}">selected</c:if>>管理员</option>
                    </select>
                </div>
                <div class="col-auto">
                    <label for="status" class="form-label">用户状态</label>
                    <select class="form-select" id="status" name="status">
                        <option value="" <c:if test="${param.status == ''}">selected</c:if>>全部</option>
                        <option value="激活" <c:if test="${param.status == '激活'}">selected</c:if>>激活</option>
                        <option value="禁用" <c:if test="${param.status == '禁用'}">selected</c:if>>禁用</option>
                    </select>
                </div>
                <div class="col-auto d-flex align-items-end">
                    <button type="submit" class="btn btn-primary w-100">查询</button>
                    <input type="reset" class="btn btn-warning w-100" value="重置">
                </div>
            </form>
        </div>

        <!-- 新增按钮 -->
        <div class="col-2 text-end">
            <a href="staff-add.jsp" class="btn btn-success">新增</a>
        </div>
    </div>

    <!-- 员工信息表格 -->
    <div class="table-responsive">
        <table class="table table-striped table-bordered">
            <thead>
            <tr>
                <th scope="col">用户ID</th>
                <th scope="col">姓名</th>
                <th scope="col">用户邮箱</th>
                <th scope="col">性别</th>
                <th scope="col">部门</th>
                <th scope="col">职位</th>
                <th scope="col">联系电话</th>
                <th scope="col">用户角色</th>
                <th scope="col">用户状态</th>
                <th scope="col">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="staff" items="${staffList}">
                <tr>
                    <td>${staff.id}</td>
                    <td>${staff.name}</td>
                    <td>${staff.email}</td>
                    <td>${staff.gender}</td>
                    <td>${staff.department}</td>
                    <td>${staff.position}</td>
                    <td>${staff.phone}</td>
                    <td>${staff.role}</td>
                    <td>${staff.status}</td>
                    <td style="width: 50px">
                        <div class="btn-group" role="group">
                            <button type="button" class="btn btn-primary" onclick="edit(${staff.id})">修改</button>
                            <button type="button" class="btn btn-danger" onclick="deleteById(${staff.id})">删除</button>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script>
    function deleteById(id) {
        console.log(id);
        if (confirm('确认删除吗？')) {
            window.location.href = "staff?action=delete&id=" + id;
        }
    }

    function edit(id) {
        console.log(id)
        window.location.href = "staff?action=id&id=" + id;
    }
</script>

</body>
</html>
