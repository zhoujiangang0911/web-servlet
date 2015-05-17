<%@ page language="java" import="java.util.*" import="cn.rjtraining.model.User" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>用户分布模块</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  <style type="text/css">
  td{ text-align: center}
  </style>
  <style type="text/css">
	body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=iO8pqDGQUvP8GzMyjRPoiOe8"></script>
	
  </head>
    <body bgcolor="#f0f5fb">
   	<h1 align="center">用户分布</h1>
		
	 <table border="1" width=100%>
  <tr style="background-color: aqua;">
  <td>学号</td>
  <td>姓名</td>
  <td>籍贯</td>
  <td>用户类型</td>
  <td colspan=2>操作</td>
  </tr>
   <%
   	List<User> ls =(List<User> )request.getAttribute("list");
   		for(Object o : ls){
		User user =(User) o;
		
    %>
  <tr>
   
   <td><%=user.getUid() %></td>
   <td><%=user.getUname() %></td>
   <td><%=user.getDistrictid() %></td>
   <td><%=user.getUsertypeid() %></td>
    <td>
    	<input type="button" value="地图查看" />
    </td>
   </tr>
  <%
    }
    %>
   
  </table>		
	
  </body>
</html>

