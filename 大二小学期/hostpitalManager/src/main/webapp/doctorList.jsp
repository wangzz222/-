<%--
  Created by IntelliJ IDEA.
  User: wzz
  Date: 2024/07/11
  Time: 9:48
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
    function  submitFormData(){
      //提交表单的事件
      $("#listform").submit();
    }

    function getPage(page){
      //设置当前页到查询表单中
      $("#page").val(page);
      $("#listform").submit();
    }

    function deleteById(id){
      if(confirm("确认删除吗")){
        window.location.href="${pageContext.request.contextPath}/doctor/deleteDoctor?id="+id;
      }
    }
  </script>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/doctor/getDoctorList" id="listform">
    <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 内容列表</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
        <li> <a class="button border-main icon-plus-square-o" href="${pageContext.request.contextPath}/addDoctor.jsp"> 添加医生</a> </li>
        <li>搜索：</li>
        <if condition="$iscid eq 1">
          <li>
            <input type="hidden" name="page" id="page" value="1">
            <select name="did" class="input" style="width:200px; line-height:17px;">
              <option value="-1">请选择科室</option>
              <c:forEach items="${dListLevelcsecond}" var="d">
                <option value="${d.departmentId}" ${doctorsQuery.did==d.departmentId?"selected":""}>${d.departmentName}</option>
              </c:forEach>
            </select>
          </li>
          <li>
            <select name="pid" class="input" style="width:200px; line-height:17px;">
              <option value="-1">请选择职称</option>
              <c:forEach items="${professionalTitlesList}" var="pt">
                <option value="${pt.id}"${doctorsQuery.pid==pt.id?"selected":""}>${pt.titleName}</option>
              </c:forEach>
            </select>
          </li>
        </if>
        <li>
          <input type="text" placeholder="请输入医生姓名 " value="${doctorsQuery.dname}" name="dname" class="input" style="width:150px; line-height:17px;display:inline-block" />
          <input type="text" placeholder="请输入医生工号 " value="${doctorsQuery.jobnum}" name="jobnum" class="input" style="width:150px; line-height:17px;display:inline-block" />
          <a href="javascript:void(0)" class="button border-main icon-search" onclick="submitFormData()" > 搜索</a>
        </li>
      </ul>
    </div>
    </div>
</form>

<table class="table table-hover text-center">
      <tr>
        <th width="100" style="text-align:left; padding-left:20px;">编号</th>
        <th width="10%">工号</th>
        <th>图片</th>
        <th>姓名</th>
        <th>科室</th>
        <th>职称</th>
        <th width="310">操作</th>
      </tr>
      <volist name="list" id="vo">
        <c:forEach items="${pageInfo.list}" var="doc">
          <tr>
            <td style="text-align:left; padding-left:20px;"><input type="checkbox" name="did" value="${doc.doctorId}" /></td>
            <td>${doc.jobNumber}</td>
            <td width="10%"><img src="${pageContext.request.contextPath}/${doc.avatar}" alt="" width="70" height="50" /></td>
<%--            <td width="10%">sb</td>--%>
            <td>${doc.name}</td>
            <td><font color="#00CC99">${doc.departments.departmentName}</font></td>
            <td>${doc.professionalTitles.titleName}</td>
            <td><div class="button-group"> <a class="button border-main" href="add.html"><span class="icon-edit"></span>修改</a> <a class="button border-red" href="javascript:void(0)" onclick="deleteById(${doc.doctorId})"><span class="icon-trash-o"></span> 删除</a> </div></td>
          </tr>
        </c:forEach>

        <tr>
          <td style="text-align:left; padding:19px 0;padding-left:20px;">
            <input type="checkbox" id="checkall"/> 全选
          </td>
          <td colspan="7" style="text-align:left;padding-left:20px;"><a href="javascript:void(0)" class="button border-red icon-trash-o" style="padding:5px 15px;" onclick="DelSelect()"> 批量删除</a> <a href="javascript:void(0)" style="padding:5px 15px; margin:0 10px;" class="button border-blue icon-edit" onclick="sorts()"> 排序</a> 操作：
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
        </tr>
        <tr>
          <td colspan="8">
            <div class="pagelist">
              <span class="current">总记录数：${pageInfo.total}</span>
              <a href="javascript:void(0)" onclick ="getPage(${pageInfo.prePage})">上一页</a>
              <c:forEach items="${pageInfo.navigatepageNums}" var="num">
                <c:choose>
                  <c:when test="${num==pageInfo.pageNum}">
                    <span class="current">${pageInfo.pageNum}</span>
                  </c:when>
                  <c:otherwise>
                    <a href="javascript:void(0)" onclick ="getPage(${num})">${num}</a>
                  </c:otherwise>
                </c:choose>
              </c:forEach>
              <a href="javascript:void(0)" onclick ="getPage(${pageInfo.nextPage})">下一页</a>
              <a href="javascript:void(0)" onclick ="getPage(${pageInfo.pages})">尾页</a>
            </div>
          </td>
        </tr>
    </table>
  </div>
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
    $("input[name='did']").each(function(){
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
    $("input[name='did']").each(function(){
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