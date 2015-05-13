<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>用户管理模块</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <style type="text/css">
  td{ text-align: center}
  </style>
    <body bgcolor="#f0f5fb">
    <h1 align="center">用户信息管理</h1>
    <table width=100%>
      <tr>
  <td><form action="manSearch" method="post" name="form" >
  <input type="hidden" name="action" value="manadd" />
 <input type="submit" value="新用户注册"  style="width: 90px;height:30px; color:olive; font-size: 15px" />
</form> 
</td>
<td >
 <form action="manSearch" method=post>
 &nbsp;&nbsp;&nbsp;&nbsp;按 <select name="type">
<option value="mid">学号</option>
<option value="mname">姓名</option>
 </select>
 查找：
 <input type="hidden"  name="action" value="search">
 <input type="text" name="key" />
 <input type="submit" value="查询" /> 
 </form> 
 </td>
  </tr></table>
    <hr />
  <table border="1" width=100%>
  <tr style="background-color: aqua;">
  <td>学号</td>
  <td>姓名</td>
  <td>密码</td>
  <td>操作</td>
  </tr>
   <c:forEach var="list" items="${data.datas}">
   <tr>
   <td>${list.mid }</td>
   <td>${list.mname }</td>
   <td>${list.password }</td>
   <td><a href="">修 改</a> &nbsp;&nbsp; 
      <a href="manSearch?action=delete&sid=${list.mid }&pageNow=${data.pageNow}" onclick="return confirm('您确定要删除吗？')" >删 除</a></td>
   </tr>   
   </c:forEach>  
    <tr>
<td colspan="8" align="right">
<label style="color: blue; float: left;">
						共有 <span>${data.rowCount}</span> 条记录,每页显示 ${data.pageSize} 条记录，共 <span>${data.pageCount}</span> 页&nbsp;&nbsp;&nbsp;
						<a href="manSearch?action=search&type=${type}&key=${key}&pageNow=1">首页</a> &nbsp;	<a href="manSearch?action=search&type=${type}&key=${key}&pageNow=${data.previousPage}">上一页</a>
						<c:forEach var="i" begin="${data.startPage}"
							end="${data.endPage}">
							<a href="manSearch?action=search&type=${type}&key=${key}&pageNow=${i}">
								<c:choose>
									<c:when test="${i eq data.pageNow}">
										<font color="red" size="5">${i}</font>
									</c:when>
									<c:otherwise>${i}</c:otherwise>
								</c:choose></a>
						</c:forEach>
						<a href="manSearch?action=search&type=${type}&key=${key}&pageNow=${data.nextPage}">下一页</a> &nbsp; <a
							href="manSearch?action=search&type=${type}&key=${key}&pageNow=${data.endPage}">末页</a>
						&nbsp;&nbsp;&nbsp;
					</label>
					<form action="manSearch" method="post">
					 跳转到第
					 <input type="hidden" name="action" value="search">
						<input name="pageNow" type="text"
							onkeyup="this.value=this.value.replace(/[\D]/g,'');if(window.event.keyCode==13){this.form.submit();}"
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"
							value="${data.pageNow}" style="width: 30px; height: 25px" />
						页
						<input type="submit" style="height: 25px" value="Go" />
					</form>
</td>
</tr> 
  </table>
  </body>
</html>
