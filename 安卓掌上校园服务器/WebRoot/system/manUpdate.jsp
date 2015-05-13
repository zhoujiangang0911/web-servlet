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
    <td width=130px align=right>学号:</td>
    <td><input type="text" name="mid" value="${man.mid }" readonly="readonly" /></td>
    </tr>
    
      <tr>
    <td align=right>姓名:</td>
    <td><input type="text" name="mname"  id="mname"  value="${man.mname }" /></td>
    </tr>
    
    <tr>
    <td align=right>密码:</td>
    <td><input type="text" name="password"  id="password"  value="${man.password}" /></td>
    </tr>
    
    <tr>
    <td align=right>学院编号:</td>
    <td><input type="text" name="mcollege"  id="mcollege"  value="${man.mcollege}" /></td>
    </tr>
    
    <tr>
    <td align=right>年龄:</td>
    <td><input type="text" name="mage"  id="mage" value="${man.mage}"/></td>
    </tr>
    
       <tr>
    <td align=right>性别:</td>
    <td><select name="msex" width=40px>
    <option value="男">男</option>
    <option value="女">女</option>
    </select>
    </tr>
    
     <tr>
    <td align=right>住址:</td>
    <td><input type="text"  name="maddress"  id="maddress" value="${man.maddress}" /></td>
    </tr>
     <tr>
     
    <td align=right>联系方式:</td>
    <td><input type="text"  name="mphone"  id="mphone" value="${man.mphone}" /></td>
    </tr>
    
    
    
    
    <tr>
    <input type="hidden" name="action" value="reup" />
    <input type="hidden" name="pageNow" value="${pageNow }" />
    <td colspan="2" align="center">
    <input type="submit"  value=" 确认修改 "  style="width: 95px;height:30px; color:olive; font-size: small" /> &nbsp; &nbsp; 
    <input type="reset"   value="重新填写"  style="width: 95px;height:30px; color:olive; font-size: small" /></td>
    </tr>
    </table>
    </form>
  </body>
</html>
