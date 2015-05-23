<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>安卓掌上校园系统</title>
</head>
<frameset rows="145,*" frameborder="no" border="0" framespacing="0">
  <frame src="system/login.jsp" name="main" scrolling="no" noresize="noresize" id="leftFrame" title="leftFrame" />
  </frameset>

</html>
