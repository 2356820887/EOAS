<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>意见列表</title>
    <!-- 引入 Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        /* 让表格列宽自动适应内容 */
        table {
            width: 100%; /* 确保表格宽度为100% */
            table-layout: auto; /* 自动调整列宽 */
        }

        td {
            max-width: 500px;
            word-wrap: break-word; /* 自动换行 */
        }

        .text-truncate {
            max-width: 200px;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        .d-none {
            display: none !important; /* 隐藏元素 */
        }

        /* 完整内容的样式 */
        .full-content {
            max-height: 200px; /* 最大高度，内容超出时显示滚动条 */
            overflow-y: auto; /* 垂直滚动条 */
            overflow-x: auto; /* 水平滚动条（防止内容溢出） */
            padding: 10px;
            width: 100%; /* 确保宽度占满容器 */
            transition: max-height 0.3s ease; /* 平滑动画 */
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">意见</h2>

    <!-- 提示信息 -->
    <c:if test="${success == 'true'}">
        <div class="alert alert-success">反馈成功！</div>
    </c:if>
    <c:if test="${delete == 'true'}">
        <div class="alert alert-success">删除成功！</div>
    </c:if>

    <!-- 如果是经理 -->
    <c:if test="${staff.role == '经理'}">
        <table class="table table-striped table-bordered">
            <thead class="table-dark">
            <tr>
                <th>提交用户</th>
                <th>意见内容</th>
                <th>意见状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="feedback" items="${list}">
                <tr>
                    <td>${feedback.name}</td>
                    <td>
                        <div class="text-truncate" id="short-${feedback.id}">
                                ${feedback.suggestion}
                        </div>
                        <div class="d-none full-content" id="full-${feedback.id}">
                                ${feedback.suggestion}
                        </div>
                    </td>
                    <td>
                        <!-- 修改状态表单 -->
                        <form action="feedback?action=update" method="post" class="d-inline">
                            <input type="hidden" name="id" value="${feedback.id}">
                            <input type="hidden" name="user_id" value="${staff.id}">
                            <input type="hidden" name="suggestion" value="${feedback.suggestion}">
                            <label for="state">
                                <select name="status" id="state" class="form-select form-select-sm d-inline w-auto">
                                    <option value="已处理" ${feedback.status == '已处理' ? 'selected' : ''}>已处理</option>
                                    <option value="未处理" ${feedback.status == '未处理' ? 'selected' : ''}>未处理</option>
                                </select>
                            </label>
                            <button type="submit" class="btn btn-sm btn-primary">修改</button>
                        </form>
                    </td>
                    <td>
                        <!-- 查看详情按钮 -->
                        <button
                                class="btn btn-sm btn-info"
                                onclick="toggleDetail(${feedback.id})"
                                id="btn-${feedback.id}">
                            查看详情
                        </button>
                        <!-- 删除按钮 -->
                        <form action="feedback?action=delete" method="post" class="d-inline">
                            <input type="hidden" name="id" value="${feedback.id}">
                            <button type="submit" class="btn btn-sm btn-danger">删除</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <!-- 如果不是经理 -->
    <c:if test="${staff.role != '经理'}">
        <div>已提交的意见</div>
        <table class="table table-striped table-bordered">
            <thead class="table-dark">
            <tr>
                <th>意见内容</th>
                <th>意见状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="feedback" items="${list}">
                <tr>
                    <td>
                        <div class="text-truncate" id="short-${feedback.id}">
                                ${feedback.suggestion}
                        </div>
                        <div class="d-none full-content" id="full-${feedback.id}">
                                ${feedback.suggestion}
                        </div>
                    </td>
                    <td>${feedback.status}</td>
                    <td>
                        <!-- 查看详情按钮 -->
                        <button
                                class="btn btn-sm btn-info"
                                onclick="toggleDetail(${feedback.id})"
                                id="btn-${feedback.id}">
                            查看详情
                        </button>
                        <!-- 删除按钮 -->
                        <form action="feedback?action=delete" method="post" class="d-inline">
                            <input type="hidden" name="id" value="${feedback.id}">
                            <button type="submit" class="btn btn-sm btn-danger">删除</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <form action="feedback?action=add" method="post">
            <input type="hidden" name="id" value="${user.id}">
            <div class="mb-3">
                <label for="suggestion" class="form-label">提交意见</label>
                <textarea name="suggestion" id="suggestion" class="form-control" rows="4"></textarea>
            </div>
            <button type="submit" class="btn btn-primary">提交</button>
        </form>
    </c:if>
</div>

<!-- 引入 Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function toggleDetail(feedbackId) {
        const shortDiv = document.getElementById(`short-` + feedbackId);
        const fullDiv = document.getElementById(`full-` + feedbackId);
        const button = document.getElementById(`btn-` + feedbackId);

        if (!shortDiv || !fullDiv || !button) {
            console.error(`Element not found for feedbackId: ` + feedbackId);
            return; // 如果元素不存在，直接返回
        }

        // 切换显示状态
        shortDiv.classList.toggle('d-none');
        fullDiv.classList.toggle('d-none');

        // 切换按钮文本
        if (fullDiv.classList.contains('d-none')) {
            fullDiv.classList.add('shrink'); // 显示时缩小宽度
            button.textContent = '查看详情';
        } else {
            fullDiv.classList.remove('shrink'); // 隐藏时恢复宽度
            button.textContent = '收起';
        }
    }
</script>
</body>
</html>
