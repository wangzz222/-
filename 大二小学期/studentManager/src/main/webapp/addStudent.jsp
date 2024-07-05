<%--
  Created by IntelliJ IDEA.
  User: 15717
  Date: 7/5/周五
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <table align="center"cellpadding="5px">
        <form action="#" method="post" >
            <caption><h2>添加学生信息</h2> </caption>
            <tr>
                <td>姓名：</td>
                <td><input type="text" name="sname"></td>
            </tr>
            <tr>
                <td>年龄：</td>
                <td><input type="text" name="age"></td>
            </tr>
            <tr>
                <td>性别：</td>
                <td>
                    <input type="radio" name="sex" value="男">男
                    <input type="radio" name="sex" value="女">女
                </td>
            </tr>
            <tr>
                <td>电话：</td>
                <td><input type="text" name="phone"></td>
            </tr>
            <tr>
                <td>班级：</td>
                <td>
                    <select name="gid">

                        <%--请求数据库--%>
                        <c:forEach items="${gradeList}" var="g">
                            <option value="${g.gid}">${g.gname}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="button" value="重置">
                    <input type="button" value="提交">
                </td>
            </tr>
        </form>
    </table>
</body>
</html>
