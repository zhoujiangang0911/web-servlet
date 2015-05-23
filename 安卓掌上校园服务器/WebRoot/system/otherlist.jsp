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
  <style type="text/css">
  td{ text-align: center}
  </style>
  </head>
    <body bgcolor="#f0f5fb">
    <h1 align="center">其他用户信息</h1>
    <table width=100%>
      <tr>
  </tr></table>
    <hr />
  <table border="1" width=100%>
  <tr style="background-color: aqua;">
  <td>账号</td>
  <td>姓名</td>
 <!--   <td>密码</td>
 -->
 <!-- <td>学院编号</td>
  <td>年龄</td>
  <td>性别</td>
  <td>居住地址</td>
   <td>联系方式</td>
   -->
  <td colspan=2>操作</td>
  </tr>
   <c:forEach var="list" items="${data.datas}">
   <tr>
   <td>${list.uid }</td>
   <td>${list.uname }</td>
   <!-- 
   <td>${list.password }</td>
   
    <td>${list.collegeid }</td>
   <td>${list.age }</td>
   <td>${list.sex }</td>
   <td>${list.address }</td>
    <td>${list.phone }</td>
     -->
   <td>
   <form action="manSearch" method="post">
   <input type="hidden" name="uid" value="${list.uid}" />

   <input type="hidden" name="pageNow" value="${data.pageNow}" />
   <input type="hidden" name="action" value="update_other" />
   <input type="submit" value="修 改" />
   </form></td>
   <td>
   <form action="manSearch" method="post">
   <input type="hidden" name="action" value="delete_other" />
   <input type="hidden" name="uid" value="${list.uid }" />
   <input type="hidden" name="pageNow" value="${data.pageNow}" />
   <input type="submit" value="删 除" onclick="return confirm('您确定要删除吗？')" />
   </form>
   </td>
   </tr>   
   </c:forEach>  
    <tr>
<td colspan="9" align="right">
<label style="color: blue; float: left;">
						共有 <span>${data.rowCount}</span> 条记录,每页显示 ${data.pageSize} 条记录，共 <span>${data.pageCount}</span> 页&nbsp;&nbsp;&nbsp;
						<a href="manSearch?action=otherlist&pageNow=1">首页</a> &nbsp;	<a href="manSearch?action=otherlist&pageNow=${data.previousPage}">上一页</a>
						<c:forEach var="i" begin="${data.startPage}"
							end="${data.endPage}">
							<a href="manSearch?action=otherlist&pageNow=${i}">
								<c:choose>
									<c:when test="${i eq data.pageNow}">
										<font color="red" size="5">${i}</font>
									</c:when>
									<c:otherwise>${i}</c:otherwise>
								</c:choose></a>
						</c:forEach>
						<a href="manSearch?action=otherlist&pageNow=${data.nextPage}">下一页</a> &nbsp; <a
							href="manSearch?action=otherlist&pageNow=${data.endPage}">末页</a>
						&nbsp;&nbsp;&nbsp;
					</label>
					<form action="manSearch" method="post">
					 跳转到第
					 <input type="hidden" name="action" value="otherlist">
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
