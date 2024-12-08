<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>公告管理</title>
    <!-- 引入 Bootstrap 样式 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2 class="text-center mb-4">公告管理</h2>
    <div class="list-group">
        <c:forEach var="anno" items="${anno}">
            <div class="list-group-item mb-3" id="${anno.id}">
                <!-- 公告标题 -->
                <h5 class="mb-2">
                    <span class="title">${anno.title}</span>
                    <input type="text" class="form-control d-none title-input" value="${anno.title}">
                </h5>
                <p class="text-muted mb-2">最后更新：${anno.updated_at}</p>

                <!-- 公告内容 -->
                <div class="mb-3">
                    <span class="content">${anno.content}</span>
                    <textarea class="form-control d-none content-textarea" rows="5">${anno.content}</textarea>
                </div>

                <!-- 操作按钮 -->
                <div>
                    <button class="btn btn-primary btn-sm me-2 edit-btn">修改</button>
                    <button class="btn btn-success btn-sm d-none save-btn">完成</button>
                    <button class="btn btn-danger btn-sm" onclick="del(${anno.id})">删除</button>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<!-- 引入 Bootstrap 和自定义脚本 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', () => {
        // 点击 "修改" 按钮
        document.querySelectorAll('.edit-btn').forEach(editBtn => {
            editBtn.addEventListener('click', () => {
                const parent = editBtn.closest('.list-group-item');

                // 获取标题和内容相关元素
                const titleSpan = parent.querySelector('.title');
                const titleInput = parent.querySelector('.title-input');
                const contentSpan = parent.querySelector('.content');
                const contentTextarea = parent.querySelector('.content-textarea');
                const saveBtn = parent.querySelector('.save-btn');

                // 切换到编辑模式
                titleSpan.classList.add('d-none');
                titleInput.classList.remove('d-none');
                contentSpan.classList.add('d-none');
                contentTextarea.classList.remove('d-none');
                editBtn.classList.add('d-none');
                saveBtn.classList.remove('d-none');
            });
        });

        // 点击 "完成" 按钮
        document.querySelectorAll('.save-btn').forEach(saveBtn => {
            saveBtn.addEventListener('click', () => {
                const parent = saveBtn.closest('.list-group-item');

                // 获取标题和内容相关元素
                const titleSpan = parent.querySelector('.title');
                const titleInput = parent.querySelector('.title-input');
                const contentSpan = parent.querySelector('.content');
                const contentTextarea = parent.querySelector('.content-textarea');
                const editBtn = parent.querySelector('.edit-btn');

                // 获取修改后的值
                const updatedTitle = titleInput.value;
                const updatedContent = contentTextarea.value;

                // 将修改后的值显示在普通模式中
                titleSpan.textContent = updatedTitle;
                contentSpan.textContent = updatedContent;

                // 切换回普通模式
                titleSpan.classList.remove('d-none');
                titleInput.classList.add('d-none');
                contentSpan.classList.remove('d-none');
                contentTextarea.classList.add('d-none');
                saveBtn.classList.add('d-none');
                editBtn.classList.remove('d-none');

                // 输出修改后的值到控制台
                console.log(updatedTitle);
                console.log(updatedContent);
                console.log('anno?action=edit&id=' + parent.id.split('-')[0]);
                const data = new URLSearchParams();
                data.append('title', updatedTitle);
                data.append('content', updatedContent);
                fetch('anno?action=edit&id=' + parent.id.split('-')[0], {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: data
                }).then(res => {
                    console.log(res);
                }).then(data => {
                    console.log(data);
                }).catch(err => {
                    console.log(err);
                })
            });
        });
    });

    function del(id) {
        console.log(id);
        window.location.href = 'anno?action=delete&id=' + id;
    }
</script>
</body>
</html>
