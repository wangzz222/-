<%--
  Created by IntelliJ IDEA.
  User: 15717
  Date: 7/3/周三
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%--提交到服务器的路径--%>
    <%--路径：相对路径 绝对路径--%>
    <form action="login" method="post">
        用户名：<input type="text" name="uname"><br>
        密码：<input type="password" name="pwd"><br>
        <input type="submit" value="登录">
    </form>
</body>
</html>
