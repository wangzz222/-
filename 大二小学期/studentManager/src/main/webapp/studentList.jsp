<%--
  Created by IntelliJ IDEA.
  User: 15717
  Date: 7/4/周四
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1" align="center" style="border-collapse:collapse"; width="500px">

    <tr>
        <th colspan="7">
            <%--请求数据库--%>
            <a href="${pageContext.request.contextPath}/toAdd">添加</a>
        </th>
    </tr>
    <tr>
        <th>编号</th>
        <th>姓名</th>
        <th>年龄</th>
        <th>手机号</th>
        <th>性别</th>
        <th>班级</th>
        <th>操作 </th>
    </tr>
    <%--items:设置遍历的对象var：设置遍历对象别名--%>
    <c:forEach items="${slist}" var="stu" >
        <tr align="center">
            <td>${stu.sid}</td>
            <td>${stu.sname}</td>
            <td>${stu.age}</td>
            <td>${stu.phone}</td>
            <td>${stu.sex}</td>
            <td>${stu.grade.gname}</td>
            <td>
                <a href="${pageContext.request.contextPath}/deleteStudent?sid=${stu.sid}">删除</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
