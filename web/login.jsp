<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <title>用户登录</title>
</head>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
<body>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-12 col-md-4">
            <div class="login-container">
                <form class="px-4 py-3" action="login" method="post">
                    <c:if test="${param.error == '1'}">
                        <div class="error-message">用户名或密码错误</div>
                    </c:if>
                    <c:if test="${param.error == '2'}">
                        <div class="error-message">尚未登录，请登录</div>
                    </c:if>
                    <c:if test="${param.error == '3'}">
                        <div class="error-message">用户已被禁用，请联系管理员</div>
                    </c:if>
                    <c:if test="${param.msg == '1'}">
                        <div class="msg-message">注册成功，请登录</div>
                    </c:if>
                    <div class="mb-3">
                        <label for="username" class="form-label">用户名：</label>
                        <input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名" required>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">密码：</label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码" required>
                    </div>
                    <div class="mb-3">
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input" id="dropdownCheck" name="remember">
                            <label class="form-check-label" for="dropdownCheck">
                                记住我
                            </label>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">登录</button>
                </form>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="register.jsp">没有账号？注册</a>
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

    .login-container {
        background-color: #fff;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
        width: 350px;
    }

    .login-container h2 {
        text-align: center;
        margin-bottom: 20px;
    }

    .error-message {
        color: red;
        font-size: 12px;
        text-align: center;
    }

    .msg-message {
        color: dodgerblue;
        font-size: 12px;
        text-align: center;
    }
</style>
</html>
