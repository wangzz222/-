<%--
  Created by IntelliJ IDEA.
  User: wzz
  Date: 7/8/周一
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--使用绝对路径--%>
<%
    String contextPath = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextPath + "/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<base href="<%=basePath%>">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title></title>
    <link rel="stylesheet" href="css/pintuer.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="js/jquery.js"></script>
    <script src="js/pintuer.js"></script>
    <script>
        function getChildList(pid)
        {
            //发送请求到服务器，根据pid查询并且返回该科室下面的所有二级科室                  //pid拼接的时候不能写到双引号里“”面，前面是字符串，就变成静态的了，传过去的就是pid，要用加号+拼接在外面
            //使用get提交方式获取服务器中返回的JSON格式的数据
            $.getJSON("${pageContext.request.contextPath}/depart/getChildDepartList?pid="+pid,function(data){  //使用jQuery的ajax，直接使用getJSON函数：第一个参数：传到服务器中的路径
                //function(data)：成功的回调函数
                var trstr = "<tr id=\"trchild_"+pid+"\"><td colspan='4'><table class=\"table table-hover text-center\">";
                $.each(data,function(i,item){  //匿名的函数，每次获取到的函数要干什么就写在function函数中，可以接收两个参数，第一次参数i表示位置索引，第二个参数item表示当前对象
                    trstr += "<tr><td>" + item.departmentId + "</td><td>" + item.departmentName + "</td><td>" + item.departmentDescription + "</td><td><div class=\"button-group\"> <a class=\"button border-main\" href= \"${pageContext.request.contextPath}/depart/toUpdate?did="+item.departmentId+"\"><span class=\"icon-edit\"></span>修改</a > <a class=\"button border-red\" href=\"javascript:void(0)\" onclick=\"deleteById("+item.departmentId+")\"><span class=\"icon-trash-o\"></span>删除</a > </div></td></tr>";  //动态生成每一个tr行
                })
                trstr += "</table></td></tr>";

                //在当前行的下面插入一个tr行
                //判断当前的一级科室下面是否已经展示了二级菜单，如果展示，移除，如果没有展示，展示
                //获取二级菜单行的id属性
                var childid = $("#trchild_"+pid).attr("id");  //undefined表示没有展示二级菜单
                var geid = "trchild_"+pid;
                if(childid == geid){  //移除二级菜单
                    $("#trchild_"+pid).remove();
                }else{
                    $("#tr_"+pid).after(trstr);
                }
                //这段代码放在成功之后的回调函数中执行

            });

        }

        function deleteById(id){
            if(confirm("确认删除吗")){
                window.location.href="${pageContext.request.contextPath}/depart/deleteById?id="+id;
            }
        }

    </script>
</head>
<body>
<form method="post" action="" id="listform">
    <div class="panel admin-panel">
        <div class="panel-head"><strong class="icon-reorder"> 内容列表</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
        <div class="padding border-bottom">
            <ul class="search" style="padding-left:10px;">
                <li> <a class="button border-main icon-plus-square-o" href="addDepart.jsp?pid=0"> 添加内容</a> </li>
            </ul>
        </div>
        <table class="table table-hover text-center">
            <tr>
                <th width="100" style="text-align:left; padding-left:20px;">编号</th>
                <th>科室名称</th>
                <th>科室简介</th>
                <th width="310">操作</th>
            </tr>
            <volist name="list" id="vo">
                <c:forEach items="${pageInfo.list}" var="depart">
                    <c:choose>
                        <c:when test="${depart.haschild}">
                            <tr  id="tr_${depart.departmentId}" onclick="getChildList(${depart.departmentId})" style="cursor: pointer">
                                <td>${depart.departmentId}</td>
                                <td>${depart.departmentName}</td>
                                <td>${depart.departmentDescription}</td>
                                <td>
                                    <div class="button-group">
                                        <a class="button border-main" href="addDepart.jsp?pid=${depart.departmentId}"><span class="icon-edit"></span> 添加</a>
                                    </div>
                                </td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <tr  id="tr_${depart.departmentId}">
                                <td>${depart.departmentId}</td>
                                <td>${depart.departmentName}</td>
                                <td>${depart.departmentDescription}</td>
                                <td>
                                    <div class="button-group">
                                        <a class="button border-main" href="addDepart.jsp?pid=${depart.departmentId}"><span class="icon-edit"></span> 添加</a>
                                        <a class="button border-red" href="javascript:void(0)" onclick="deleteById(${depart.departmentId})"><span class="icon-trash-o"></span> 删除</a>
                                    </div>
                                </td>
                            </tr>
                    </c:otherwise>  
                    </c:choose>
                </c:forEach>

                <tr>
                    <td style="text-align:left; padding:19px 0;padding-left:20px;"><input type="checkbox" id="checkall"/>
                        全选 </td>
                    <td colspan="7" style="text-align:left;padding-left:20px;"><a href="javascript:void(0)" class="button border-red icon-trash-o" style="padding:5px 15px;" onclick="DelSelect()"> 删除</a> <a href="javascript:void(0)" style="padding:5px 15px; margin:0 10px;" class="button border-blue icon-edit" onclick="sorts()"> 排序</a> 操作：
                        <select name="ishome" style="padding:5px 15px; border:1px solid #ddd;" onchange="changeishome(this)">
                            <option value="">首页</option>
                            <option value="1">是</option>
                            <option value="0">否</option>
                        </select>
                        <select name="isvouch" style="padding:5px 15px; border:1px solid #ddd;" onchange="changeisvouch(this)">
                            <option value="">推荐</option>
                            <option value="1">是</option>
                            <option value="0">否</option>
                        </select>
                        <select name="istop" style="padding:5px 15px; border:1px solid #ddd;" onchange="changeistop(this)">
                            <option value="">置顶</option>
                            <option value="1">是</option>
                            <option value="0">否</option>
                        </select>
                        &nbsp;&nbsp;&nbsp;

                        移动到：
                        <select name="movecid" style="padding:5px 15px; border:1px solid #ddd;" onchange="changecate(this)">
                            <option value="">请选择分类</option>
                            <option value="">产品分类</option>
                            <option value="">产品分类</option>
                            <option value="">产品分类</option>
                            <option value="">产品分类</option>
                        </select>
                        <select name="copynum" style="padding:5px 15px; border:1px solid #ddd;" onchange="changecopy(this)">
                            <option value="">请选择复制</option>
                            <option value="5">复制5条</option>
                            <option value="10">复制10条</option>
                            <option value="15">复制15条</option>
                            <option value="20">复制20条</option>
                        </select></td>
                </tr>
                <tr>
                    <td colspan="8">
                        <div class="pagelist">
                            <span class="current">总记录数：${pageInfo.total}</span>
                            <a href="${pageContext.request.contextPath}/depart/getDepartList?page=${pageInfo.prePage}">上一页</a>
                            <c:forEach items="${pageInfo.navigatepageNums}" var="num">
                                <c:choose>
                                    <c:when test="${num==pageInfo.pageNum}">
                                        <span class="current">${pageInfo.pageNum}</span>
<%--                                        <a href="${pageContext.request.contextPath}/depart/getDepartList?page=${pageInfo.pageNum}" class="current">${num}</a>--%>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${pageContext.request.contextPath}/depart/getDepartList?page=${num}">${num}</a>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <a href="${pageContext.request.contextPath}/depart/getDepartList?page=${pageInfo.nextPage}">下一页</a>
                            <a href="${pageContext.request.contextPath}/depart/getDepartList?page=${pageInfo.pages}">尾页</a>
                        </div>
                    </td>
                </tr>
        </table>
    </div>
</form>
<script type="text/javascript">

    //搜索
    function changesearch(){

    }

    //单个删除
    function del(id,mid,iscid){
        if(confirm("您确定要删除吗?")){

        }
    }

    //全选
    $("#checkall").click(function(){
        $("input[name='id[]']").each(function(){
            if (this.checked) {
                this.checked = false;
            }
            else {
                this.checked = true;
            }
        });
    })

    //批量删除
    function DelSelect(){
        var Checkbox=false;
        $("input[name='id[]']").each(function(){
            if (this.checked==true) {
                Checkbox=true;
            }
        });
        if (Checkbox){
            var t=confirm("您确认要删除选中的内容吗？");
            if (t==false) return false;
            $("#listform").submit();
        }
        else{
            alert("请选择您要删除的内容!");
            return false;
        }
    }

    //批量排序
    function sorts(){
        var Checkbox=false;
        $("input[name='id[]']").each(function(){
            if (this.checked==true) {
                Checkbox=true;
            }
        });
        if (Checkbox){

            $("#listform").submit();
        }
        else{
            alert("请选择要操作的内容!");
            return false;
        }
    }


    //批量首页显示
    function changeishome(o){
        var Checkbox=false;
        $("input[name='id[]']").each(function(){
            if (this.checked==true) {
                Checkbox=true;
            }
        });
        if (Checkbox){

            $("#listform").submit();
        }
        else{
            alert("请选择要操作的内容!");

            return false;
        }
    }

    //批量推荐
    function changeisvouch(o){
        var Checkbox=false;
        $("input[name='id[]']").each(function(){
            if (this.checked==true) {
                Checkbox=true;
            }
        });
        if (Checkbox){


            $("#listform").submit();
        }
        else{
            alert("请选择要操作的内容!");

            return false;
        }
    }

    //批量置顶
    function changeistop(o){
        var Checkbox=false;
        $("input[name='id[]']").each(function(){
            if (this.checked==true) {
                Checkbox=true;
            }
        });
        if (Checkbox){

            $("#listform").submit();
        }
        else{
            alert("请选择要操作的内容!");

            return false;
        }
    }


    //批量移动
    function changecate(o){
        var Checkbox=false;
        $("input[name='id[]']").each(function(){
            if (this.checked==true) {
                Checkbox=true;
            }
        });
        if (Checkbox){

            $("#listform").submit();
        }
        else{
            alert("请选择要操作的内容!");

            return false;
        }
    }

    //批量复制
    function changecopy(o){
        var Checkbox=false;
        $("input[name='id[]']").each(function(){
            if (this.checked==true) {
                Checkbox=true;
            }
        });
        if (Checkbox){
            var i = 0;
            $("input[name='id[]']").each(function(){
                if (this.checked==true) {
                    i++;
                }
            });
            if(i>1){
                alert("只能选择一条信息!");
                $(o).find("option:first").prop("selected","selected");
            }else{

                $("#listform").submit();
            }
        }
        else{
            alert("请选择要复制的内容!");
            $(o).find("option:first").prop("selected","selected");
            return false;
        }
    }

</script>
</body>
</html>