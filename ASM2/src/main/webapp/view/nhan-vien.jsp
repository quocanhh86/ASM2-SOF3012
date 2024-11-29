<%--
  Created by IntelliJ IDEA.
  User: abc
  Date: 29/11/24
  Time: 00:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Bạn đã đăng nhập vào trang sinh viên với role là: ${sessionScope.role}
<button> <a href="/test-filter/logout">Đăng xuất</a></button>
</body>
</html>
