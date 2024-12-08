<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>资产管理</title>
    <!-- 引入 Bootstrap 样式 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2 class="text-center mb-4">资产管理</h2>

    <!-- 搜索栏 -->
    <form id="searchForm" class="row g-3 mb-4">
        <div class="col-md-3">
            <input type="text" class="form-control" id="searchName" name="name" placeholder="搜索名称">
        </div>
        <div class="col-md-3">
            <input type="text" class="form-control" id="searchType" name="type" placeholder="搜索类型">
        </div>
        <div class="col-md-3">
            <input type="text" class="form-control" id="searchLocation" name="location" placeholder="搜索存放地点">
        </div>
        <div class="col-md-3">
            <input type="text" class="form-control" id="searchStatus" name="status" placeholder="搜索状态">
        </div>
        <div class="col-md-12 text-end">
            <button type="button" class="btn btn-primary" id="searchBtn">搜索</button>
            <button type="reset" class="btn btn-secondary">重置</button>
        </div>
    </form>

    <!-- 表格 -->
    <div class="table-responsive">
        <table class="table table-striped table-bordered" id="assetTable">
            <thead class="table-dark">
            <tr>
                <th>名称</th>
                <th>类型</th>
                <th>数量</th>
                <th>状态</th>
                <th>存放地点</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="assetTableBody">
            <c:forEach var="asset" items="${assets}">
                <tr data-id="${asset.id}">
                    <td>${asset.name}</td>
                    <td>${asset.type}</td>
                    <td>${asset.quantity}</td>
                    <td>${asset.status}</td>
                    <td>${asset.location}</td>
                    <td>
                        <button class="btn btn-sm btn-warning me-2 edit-btn">编辑</button>
                        <button class="btn btn-sm btn-danger delete-btn">删除</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- 新增按钮 -->
    <div class="text-end mt-3">
        <button type="button" class="btn btn-success" id="addNewBtn">新增</button>
    </div>
</div>

<!-- 引入 Bootstrap 脚本 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', () => {
        const assetTableBody = document.getElementById('assetTableBody');
        const addNewBtn = document.getElementById('addNewBtn');

        addNewBtn.addEventListener('click', () => {
            // 创建一个新的表格行
            const newRow = document.createElement('tr');

            newRow.innerHTML = `
                <td><input type="text" class="form-control" placeholder="输入名称" id="newName"></td>
                <td><input type="text" class="form-control" placeholder="输入类型" id="newType"></td>
                <td><input type="number" class="form-control" placeholder="输入数量" id="newQuantity"></td>
                <td>
                    <select class="form-control" id="newStatus">
                        <option value="可用">可用</option>
                        <option value="损坏">损坏</option>
                        <option value="报废">报废</option>
                    </select>
                </td>
                <td><input type="text" class="form-control" placeholder="输入存放地点" id="newLocation"></td>
                <td>
                    <button class="btn btn-sm btn-success" id="confirmAddBtn">确定</button>
                </td>
            `;

            // 将新行添加到表格中
            assetTableBody.appendChild(newRow);

            // 确定按钮的逻辑
            const confirmAddBtn = newRow.querySelector('#confirmAddBtn');
            confirmAddBtn.addEventListener('click', () => {
                const newName = document.getElementById('newName').value;
                const newType = document.getElementById('newType').value;
                const newQuantity = document.getElementById('newQuantity').value;
                const newStatus = document.getElementById('newStatus').value;
                const newLocation = document.getElementById('newLocation').value;

                // 检查所有字段是否已填写
                if (!newName || !newType || !newQuantity || !newStatus || !newLocation) {
                    alert('请填写所有字段！');
                    return;
                }

                // 构造表单编码的数据字符串
                const formData = new URLSearchParams();
                formData.append('name', newName);
                formData.append('type', newType);
                formData.append('quantity', newQuantity);
                formData.append('status', newStatus);
                formData.append('location', newLocation);

                // 将数据发送到后端
                fetch('asset?action=add', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: formData.toString()
                })
                    .then(response => {
                        if (response.ok) {
                            alert('新增成功！');
                            location.reload(); // 刷新页面以显示新数据
                        } else {
                            alert('新增失败，请重试！');
                        }
                    })
                    .catch(error => {
                        console.error('请求出错：', error);
                        alert('新增失败，请重试！');
                    });
            });
        });

        assetTableBody.addEventListener('click', (e) => {
            // 处理点击编辑按钮的逻辑
            if (e.target.classList.contains('edit-btn')) {
                const editBtn = e.target;
                const row = editBtn.closest('tr');
                const cells = row.querySelectorAll('td');

                // 获取当前行的数据
                const name = cells[0].innerText.trim();
                const type = cells[1].innerText.trim();
                const quantity = cells[2].innerText.trim();
                const status = cells[3].innerText.trim();
                const location = cells[4].innerText.trim();

                // 将列替换为输入框或下拉框
                cells[0].innerHTML = `<input type="text" class="form-control" value="` + name + `">`;
                cells[1].innerHTML = `<input type="text" class="form-control" value="` + type + `">`;
                cells[2].innerHTML = `<input type="number" class="form-control" value=" ` + quantity + ` ">`;

                // 创建 <select> 标签
                let selectHtml = '<select class="form-control">';
                // 根据状态值修改 <select> 中的选项
                selectHtml += status === '可用' ? '<option value="可用" selected>可用</option>' : '<option value="可用">可用</option>';
                selectHtml += status === '损坏' ? '<option value="损坏" selected>损坏</option>' : '<option value="损坏">损坏</option>';
                selectHtml += status === '报废' ? '<option value="报废" selected>报废</option>' : '<option value="报废">报废</option>';
                selectHtml += '</select>';

                // 更新单元格的内容
                cells[3].innerHTML = selectHtml;
                cells[4].innerHTML = `<input type="text" class="form-control" value="` + location + `">`;

                // 替换按钮为"保存"
                editBtn.textContent = '保存';
                editBtn.classList.remove('edit-btn', 'btn-warning');
                editBtn.classList.add('save-btn', 'btn-success');
            }

            // 处理点击保存按钮的逻辑
            if (e.target.classList.contains('save-btn')) {
                const saveBtn = e.target;
                const row = saveBtn.closest('tr');
                const cells = row.querySelectorAll('td');

                // 获取编辑后的数据
                const updatedName = cells[0].querySelector('input').value;
                const updatedType = cells[1].querySelector('input').value;
                const updatedQuantity = cells[2].querySelector('input').value;
                const updatedStatus = cells[3].querySelector('select').value;
                const updatedLocation = cells[4].querySelector('input').value;

                // 获取行的唯一 ID（通过 data-id 属性设置）
                const assetId = row.getAttribute('data-id');

                // 校验数据
                if (!updatedName || !updatedType || !updatedQuantity || !updatedStatus || !updatedLocation) {
                    alert('请填写所有字段！');
                    return;
                }

                // 构造表单数据
                const formData = new URLSearchParams();
                formData.append('name', updatedName);
                formData.append('type', updatedType);
                formData.append('quantity', updatedQuantity);
                formData.append('status', updatedStatus);
                formData.append('location', updatedLocation);

                // 发送数据到后端
                fetch(`asset?action=edit&id=` + assetId, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: formData.toString()
                }).then(response => {
                    if (response.ok) {
                        alert('修改成功！');

                        // 恢复为普通文本模式
                        cells[0].innerText = updatedName;
                        cells[1].innerText = updatedType;
                        cells[2].innerText = updatedQuantity;
                        cells[3].innerText = updatedStatus;
                        cells[4].innerText = updatedLocation;

                        // 替换按钮为"编辑"
                        saveBtn.textContent = '编辑';
                        saveBtn.classList.remove('save-btn', 'btn-success');
                        saveBtn.classList.add('edit-btn', 'btn-warning');
                    } else {
                        alert('修改失败，请重试！');
                    }
                }).catch(error => {
                    console.error('请求出错：', error);
                    alert('修改失败，请重试！');
                });
            }

            if (e.target.classList.contains('delete-btn')) {
                const delBtn = e.target;
                const row = delBtn.closest('tr');
                const assetId = row.getAttribute('data-id');
                console.log(assetId);
                fetch(`asset?action=delete&id=` + assetId, {
                    method: 'GET'
                }).then(response => {
                    if (response.ok) {
                        console.log('删除成功');
                    } else {
                        alert('删除失败，请重试！');
                    }
                }).catch(error => {
                    console.error('请求出错：', error);
                    alert('删除失败，请重试！');
                });
            }


        });
    });
</script>
</body>
</html>
