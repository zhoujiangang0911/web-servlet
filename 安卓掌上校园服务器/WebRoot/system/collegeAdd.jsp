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
    
    <title>My JSP</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body bgcolor="#f0f5fb">

<table align="center" width="35%" >
 <tr>
  <h1 align="center">添加新学院</h1>
 </tr>
  <form action="colSearch" method="post">
 <tr>
    <td colspan="2" align="center"><label id="message">${ msg }</label></td>
 </tr>
 <tr>
    <td style=" width: 90px" align=right>学院编号:</td>
    <td><input type="text" name="cid"  id="cid" /></td>
 </tr>
 <tr>
    <td align=right>学院名称:</td>
    <td><input type="text" name="cname"  id="cname"/></td>
 </tr>
 
  <tr>
    <td style=" width: 90px" align=right>院长:</td>
    <td><input type="text" name="cleader"  id="cleader" /></td>
 </tr>
 
  <tr>
    <td style=" width: 90px" align=right>总专业数:</td>
    <td><input type="text" name="cmajorsum"  id="cmajorsum" /></td>
 </tr>
 
 <tr>
    <td style=" width: 90px" align=right>总班级数:</td>
    <td><input type="text" name="cbanjisum"  id="cbanjisum" /></td>
 </tr>
 
 
 <tr>
    <td align=right>学院信息:</td>
    <td><input type="text" name="cinfo"  id="cinfo"/></td>
</tr>
    
    <tr></tr>
    <tr>
    <input type="hidden" name="action" value="insert" />
    <td colspan="2" align="center">
    <input type="submit"  value="注册 "
    style="width: 50px;height:30px; color:olive; font-size: 18px" /> 
    &nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp; 
    <input type="button"  value="取消"  style="width: 50px;height:30px; color:olive; font-size: 18px" onclick="javascript:history.go(-1);"/></td>
    </tr>
    </table>
</form>
  </body>
</html>
