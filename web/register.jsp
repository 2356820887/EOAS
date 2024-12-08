<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <title>用户注册</title>
</head>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-12 col-md-8">
            <div id="app" class="register-container">
                <h2>欢迎</h2>
                <form action="register" method="post">
                    <div class="row">
                        <!-- 第一列 -->
                        <div class="col-md-6">
                            <div class="mb-3">
                                <label for="username" class="form-label">用户名：</label>
                                <input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名">
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label">密码：</label>
                                <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码">
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label">邮箱：</label>
                                <input type="email" class="form-control" id="email" name="email" placeholder="请输入邮箱">
                            </div>
                            <div class="mb-3">
                                <label for="name" class="form-label">姓名：</label>
                                <input type="text" class="form-control" id="name" name="name" placeholder="请输入姓名">
                            </div>
                        </div>

                        <!-- 第二列 -->
                        <div class="col-md-6">
                            <div class="mb-3">
                                <label for="gender" class="form-label">性别：</label>
                                <select class="form-select" id="gender" name="gender">
                                    <option value="男" selected>男</option>
                                    <option value="女">女</option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="department" class="form-label">部门：</label>
                                <input type="text" class="form-control" id="department" name="department" placeholder="请输入部门">
                            </div>
                            <div class="mb-3">
                                <label for="position" class="form-label">职务：</label>
                                <input type="text" class="form-control" id="position" name="position" placeholder="请输入职务">
                            </div>
                            <div class="mb-3">
                                <label for="phone" class="form-label">电话：</label>
                                <input type="tel" class="form-control" id="phone" name="phone" placeholder="请输入手机号">
                            </div>
                        </div>
                    </div>

                    <div class="mb-3 text-center">
                        <input class="btn btn-primary" type="submit" value="注册">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>

<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f9;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }

    .register-container {
        background-color: #fff;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
    }

    .register-container h2 {
        text-align: center;
        margin-bottom: 20px;
    }
</style>

<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f9;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }

    .register-container {
        background-color: #fff;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
        /*width: 350px;*/
    }

    .register-container h2 {
        text-align: center;
        margin-bottom: 20px;
    }
</style>
</html>
