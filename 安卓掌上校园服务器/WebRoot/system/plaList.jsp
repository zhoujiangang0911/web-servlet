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
    <title>地图管理模块</title>
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
    <h1 align="center">地点信息管理</h1>
    <table width="936" height="46">
      <tr>
  <td><form action="plaSearch" method="post">
  <input type="hidden" name="action" value="plaadd" />
 <input type="submit" value="新地点添加"  style="width: 90px;height:30px; color:olive; font-size: 15px" />
</form> 
</td>

  </tr></table>
    <hr />
  <table border="1" width=100%>
  <tr style="background-color: aqua;">
  <td>编号</td>
  <td>地点名</td>
  <td>经度</td>
  <td>纬度</td>
  <td>地点信息</td>
  <td colspan=2>操作</td>
  </tr>
   <c:forEach var="list" items="${data.datas}">
   <tr>
   <td>${list.pid }</td>
   <td>${list.pname }</td>
   <td>${list.plongtitude }</td>
   <td>${list.platitude }</td>
   <td>${list.pinfo }</td>
   <td>
   <form action="plaSearch" method="post">
   <input type="hidden" name="pid" value="${list.pid}" />

   <input type="hidden" name="pageNow" value="${data.pageNow}" />
   <input type="hidden" name="action" value="update" />
   <input type="submit" align="center" value="修 改" />
   </form></td>
   <td>
   <form action="plaSearch" method="post">
   <input type="hidden" name="action" value="delete" />
   <input type="hidden" name="pid" value="${list.pid }" />
   <input type="hidden" name="pageNow" value="${data.pageNow}" />
   <input type="submit" align="center" value="删 除" onclick="return confirm('您确定要删除吗？')" />
   </form>
   </td>
   </tr>   
   </c:forEach>  
    <tr>
<td colspan="9" align="right">
<label style="color: blue; float: left;">
						共有 <span>${data.rowCount}</span> 条记录,每页显示 ${data.pageSize} 条记录，共 <span>${data.pageCount}</span> 页&nbsp;&nbsp;&nbsp;
						<a href="plaSearch?action=list&pageNow=1">首页</a> &nbsp;	<a href="plaSearch?action=list&pageNow=${data.previousPage}">上一页</a>
						<c:forEach var="i" begin="${data.startPage}"
							end="${data.endPage}">
							<a href="plaSearch?action=list&pageNow=${i}">
								<c:choose>
									<c:when test="${i eq data.pageNow}">
										<font color="red" size="5">${i}</font>
									</c:when>
									<c:otherwise>${i}</c:otherwise>
								</c:choose></a>
						</c:forEach>
						<a href="plaSearch?action=list&pageNow=${data.nextPage}">下一页</a> &nbsp; <a
							href="plaSearch?action=list&pageNow=${data.endPage}">末页</a>
						&nbsp;&nbsp;&nbsp;
					</label>
					<form action="plaSearch" method="post">
					 跳转到第
					 <input type="hidden" name="action" value="list">
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
