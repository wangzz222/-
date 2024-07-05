<%--
  Created by IntelliJ IDEA.
  User: 15717
  Date: 7/4/周四
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
</head>
<body>
    <%--获取作用域中的数据--%>
    <%----%><%----%>

    <%--指定作用域 从指定的作用开始查找--%>
    <%--不指定作用域 从小的作用开始查找--%>

    request作用域：${req}<br>
    session作用域：${sess}<br>
    application作用域：${app}<br>

</body>
</html>
