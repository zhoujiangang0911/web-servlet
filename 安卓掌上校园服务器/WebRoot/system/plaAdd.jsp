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
  <h1 align="center">新地点信息添加</h1>
 <form action="plaSearch" method="post">
    <table align="center"  border="1">
    <tr>
    <td colspan="2"><label id="message" align="center" style="color:red;">${msg}</label></td>
    </tr>
    <tr>
    <td width=75px align=right>地点编号:</td>
    <td width=75px><input type="text" name="pid"  id="pid" /></td>
    </tr>
      <tr>
    <td align=right>地点名:</td>
    <td><input type="text" name="pname"  id="pname"/></td>
    </tr>
    
     <tr>
    <td width=75px align=right>经度:</td>
    <td width=75px><input type="text" name="plongtitude"  id="plongtitude" /></td>
    </tr>
    
     <tr>
    <td width=75px align=right>纬度</td>
    <td width=75px><input type="text" name="platitude"  id="platitude" /></td>
    </tr>
    
     <tr>
    <td align=right>地点信息:</td>
    <td><input width=75px height=75px type="text"  name="pinfo"  id="pinfo"/></td>
    </tr>
    
   <input type="hidden" name="action" value="insert" />
     <tr>
    <td colspan="2"  align="center">
    <input type="submit"  value="添加 " style="width: 80px;height:30px; color:olive; font-size:15px" /> &nbsp; &nbsp; 
   <input type="button"  value="取消"   style="width: 80px;height:30px; color:olive; font-size: 15px" onclick="javascript:history.go(-1);" />
   </td>
    </tr>
    
    </table>
    </form>
  </body>
</html>
