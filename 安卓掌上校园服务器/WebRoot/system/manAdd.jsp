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
  </head>
  <body bgcolor="#dddddd">
  <h1 align="center">新用户注册</h1>
 <form action="manSearch" method="post">
    <table align="center"  border="1">
    <tr>
    <td colspan="2"><label id="message" align="center" style="color:red;">${msg}</label></td>
    </tr>
    <tr>
    <td width=75px align=right>学号:</td>
    <td width=75px><input type="text" name="mid"  id="mid" /></td>
    </tr>
      <tr>
    <td align=right>姓名:</td>
    <td><input type="text" name="mname"  id="mname"/></td>
    </tr>
     <tr>
    <td align=right>密码:</td>
    <td><input type="text"  name="password"  id="password"/></td>
    </tr>
    
     <tr>
    <td width=75px align=right>学院编号:</td>
    <td width=75px><input type="text" name="mcollege"  id="mcollege" /></td>
    </tr>
      <tr>
    <td align=right>年龄:</td>
    <td><input type="text" name="mage"  id="mage"/></td>
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
    <td><input type="text"  name="maddress"  id="maddress"/></td>
    </tr>
     <tr>
    <td align=right>联系方式:</td>
    <td><input type="text"  name="mphone"  id="mphone"/></td>
    </tr>
    
    
   
   <input type="hidden" name="action" value="insert" />
     <tr>
    <td colspan="2"  align="center">
    <input type="submit"  value=" 注 册 " style="width: 80px;height:30px; color:olive; font-size:15px" /> &nbsp; &nbsp; 
   <input type="button"  value="取消"   style="width: 80px;height:30px; color:olive; font-size: 15px" onclick="javascript:history.go(-1);" />
   </td>
    </tr>
    
    </table>
    </form>
  </body>
</html>
