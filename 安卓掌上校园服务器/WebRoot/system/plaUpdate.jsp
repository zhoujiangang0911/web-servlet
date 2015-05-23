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
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <body bgcolor="#f0f5fb">
  <h1 align="center">地点信息修改</h1>
 <form action="plaSearch" method="post">
    <table align="center" width="35%">
    <tr>
    <td colspan="2"><label id="message">${ msg }</label></td>
    </tr>
    <tr>
    <td width=130px align=right>地点编号:</td>
    <td><input type="text" name="pid" value="${pla.pid }" readonly="readonly" /></td>
    </tr>
      <tr>
    <td align=right>地点名:</td>
    <td><input type="text" name="pname"  id="pname"  value="${pla.pname }" /></td>
    </tr>
    <tr>
    
    <tr>
    <td align=right>经度:</td>
    <td><input type="text" name="plongtitude"  id="plongtitude"  value="${pla.plongtitude }" /></td>
    </tr>
    
    <tr>
    <td align=right>纬度:</td>
    <td><input type="text" name="platitude"  id="platitude"  value="${pla.platitude }" /></td>
    </tr>
    
    <td align=right>地点信息:</td>
    <td><input type="text" name="pinfo"  id="pinfo"  value="${pla.pinfo}" /></td>
    </tr>
    <tr>
    <input type="hidden" name="action" value="reup" />
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
