<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: abc
  Date: 29/11/24
  Time: 12:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/san-pham/add" method="post">
    Mã: <input type="text" name="ma" id="ma" value="${sp.ma}">
    <h3>${message}</h3>
    <br>
    Loại sản phẩm
    <select name="" id="">
        <c:forEach items="${listCombobox}" var="lsp">
            <option value="${lsp.id}">${lsp.ten}</option>
        </c:forEach>
    </select>
    <br>
    Tên: <input type="text" name="ten" id="ten" value="${sp.ten}">
    <h3>${message1}</h3><br>
    Mô tả: <input type="text" name="mota" id="mota" value="${sp.mota}">
    <h3>${message2}</h3><br>
    <button type="submit" onclick="addAndAlert()">Add</button>
</form>

<table>
    <thead>
    <tr>
        <th>Mã</th>
        <th>Tên</th>
        <th>Mô tả</th>
        <th>Website</th>
        <th>Giá bán</th>
        <th>Số lượng</th>
        <th>Mã loại sản phẩm</th>
        <th>Tên loại sản phẩm</th>
        <th>Trạng thái</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${listProduct}" var="sp">
        <tr>
            <td>${sp.ma}</td>
            <td>${sp.ten}</td>
            <td>${sp.mota}</td>
            <td>${sp.website}</td>
            <td>${sp.giaBan}</td>
            <td>${sp.soLuong}</td>
            <td>${sp.idLoaiSp.ma}</td>
            <td>${sp.idLoaiSp.ten}</td>
            <td>${sp.trangThai == 1 ?"Còn hàng":"Hết hàng"}</td>
            <td>
                <button>
                    <a href="/san-pham/detail?id=${sp.id}">
                        Detail
                    </a>
                </button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script>
    function addAndAlert() {
        let ma = document.getElementById("ma").value;
        let ten = document.getElementById("ten").value;
        let moTa = document.getElementById("mota").value;

        if (ma === "" || ten === "" || moTa === "") {
            alert('Bạn chưa nhập đầy đủ thông tin');
            return false;
        }

        if (confirm('Bạn có muốn add không?')) {
            alert("Add thành công");
        } else {
            event.preventDefault();
            return false;
        }
    }
</script>
</body>
</html>
