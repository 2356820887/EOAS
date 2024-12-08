<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>企业办公自动化系统</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
            crossorigin="anonymous"></script>
    <style>
        html, body {
            height: 100%;
            margin: 0;
            display: flex;
            flex-direction: column;
        }

        .container-fluid {
            flex: 1;
            display: flex;
            flex-direction: row;
            height: 100%;
        }

        .sidebar {
            height: 100%;
            position: relative;
        }

        .nav-link {
            color: #333;
        }

        .nav-link.active {
            background-color: #007bff;
            color: #fff;
        }

        .nav-link:hover {
            color: #007bff;
        }

        .sidebar-title {
            font-size: 1.5rem;
            font-weight: bold;
            margin: 1rem;
        }

        .logout {
            position: absolute;
            bottom: 1rem;
            width: 100%;
        }

        .content {
            flex: 1;
            display: flex;
            flex-direction: column;
        }

        iframe {
            flex: 1;
            width: 100%;
            border: none;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <!-- 左侧导航栏 -->
    <nav class="col-md-3 col-lg-2 bg-light sidebar">
        <div class="sidebar-title">企业办公自动化系统</div>
        <div class="sidebar-title">你好，${user.username}</div>
        <ul class="nav flex-column">
            <li class="nav-item">
                <a class="nav-link" href="anno?action=new" target="iframe"><i class="fas fa-home"></i> 主页</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="document?action=list" target="iframe"><i class="fas fa-file-alt"></i> 公文管理</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="meeting?action=list" target="iframe"><i class="fas fa-calendar"></i> 会议管理</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="anno?action=list" target="iframe"><i class="fas fa-bullhorn"></i> 公告管理</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="staff?action=list" target="iframe"><i class="fas fa-users"></i> 人力资源管理</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="asset?action=list" target="iframe"><i class="fas fa-box"></i> 资产管理</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="file?action=list" target="iframe"><i class="fas fa-folder"></i> 文档管理</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="email?action=inbox&uid=${user.id}" target="iframe"><i class="fas fa-envelope"></i> 邮件管理</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="feedback?action=list" target="iframe"><i class="fas fa-comments"></i> 意见管理</a>
            </li>
            <li class="nav-item logout">
                <a class="nav-link text-danger" href="logout"><i class="fas fa-sign-out-alt"></i> 退出系统</a>
            </li>
        </ul>
    </nav>

    <!-- 右侧内容区 -->
    <div class="content col-md-9 col-lg-10">
        <iframe name="iframe" src="anno?action=new"></iframe>
    </div>
</div>
</body>
</html>
