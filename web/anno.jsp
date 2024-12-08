<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Announcement List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <c:if test="${not empty newAnno}">
                <jsp:useBean id="newAnno" scope="request" type="com.guat.eoas.pojo.Announcement"/>
                <div class="card shadow-sm">
                    <div class="card-header bg-primary text-white">
                        <h2 class="card-title">${newAnno.title}</h2>
                    </div>
                    <div class="card-body">
                        <p class="text-muted">更新时间: ${newAnno.updated_at}</p>
                        <hr>
                        <div>${newAnno.content}</div>
                    </div>
                </div>
            </c:if>

            <c:if test="${empty newAnno}">
                <div class="alert alert-warning text-center" role="alert">
                    没有找到公告。
                </div>
            </c:if>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
</body>
</html>

