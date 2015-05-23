<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <body bgcolor="#f0f5fb">
  <h1 align="center">用户信息修改</h1>
 <form action="manSearch" method="post">
    <table align="center" width="35%">
    <tr>
    <td colspan="2"><label id="message">${ msg }</label></td>
    </tr>
    
    <tr>
    <td width=130px align=right>账号:</td>
    <td><input type="text" name="uid" value="${user.uid }" readonly="readonly" /></td>
    </tr>
    
        
      <tr>
    <td align=right>用户类型:</td>
    <td><input type="text" name="usertype"  id="usertype"  value="${user.usertypeid}" readonly="readonly" /></td>
    </tr>
    
      <tr>
    <td align=right>姓名:</td>
    <td><input type="text" name="uname"  id="uname"  value="${user.uname }" /></td>
    </tr>
    
    <tr>
    <td align=right>密码:</td>
    <td><input type="text" name="password"  id="password"  value="${user.password}" /></td>
    </tr>

    
    <tr>
    <input type="hidden" name="action" value="reup_other" />
    <input type="hidden" name="pageNow" value="${pageNow }" />
    <td colspan="2" align="center">
    <input type="submit"  value=" 确认修改 "  style="width: 95px;height:30px; color:olive; font-size: small" /> &nbsp; &nbsp; 
   <!-- 
    <input type="reset"   value="重新填写"  style="width: 95px;height:30px; color:olive; font-size: small" /></td>
    -->
    </tr>
    </table>
    </form>
  </body>
</html>
