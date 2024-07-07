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
    <script src="js/jquery.js"></script>
    <script>
        var flag = false;
        function checkName(name){
            // alert(name)
        //     把用户输入的名称发送到服务器验证
            //参数传递 1 直接拼接在url地址后面 使用？传参  使用&拼接  checkName？sname=admin&id=10
            <%--$.get("${pageContext.request.contextPath}/checkName？sname=" + name, function (){},"text");--%>
            //2 使用大括号{}封装参数 形式 {参数名称：参数值，参数名称：参数值，。。。。。}
            $.get("${pageContext.request.contextPath}/checkName",{sname:name}, function (res){
                // alert(res)
                if(res == "true"){//可用
                    $("#snamemsg").html("√").css("color","green")
                    flag =  true;
                }else{//不可用
                    $("#snamemsg").html("姓名已被占用！").css("color","red")
                    flag = false;
                }
            },"text");
            return flag;

        }
        function checkFormdata(){
            //调用表单验证 通过true
            //获取扁担中输入的姓名
            var sanem = $("[name=sname]").val();
            if (checkName(sname)){
                return true;
            }else{
                return  false;
            }
        }
    </script>
</head>
<body>
    <table align="center"cellpadding="5px">
        <form action="${pageContext.request.contextPath}/addStudent" method="post" onsubmit="return checkFormData()">
            <caption><h2>添加学生信息</h2> </caption>
            <tr>
                <td>姓名：</td>
                <%--onblur 失去焦点事件--%>
                <td><input type="text" name="sname" required onblur="checkName(this.value) ">
                    <span id="snamemsg"></span>
                </td>
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
                    <input type="reset" value="重置">
                    <input type="submit" value="提交">
                </td>
            </tr>
        </form>
    </table>
</body>
</html>
