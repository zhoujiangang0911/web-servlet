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
	
  </head>
  <body bgcolor="#f0f5fb">
  <h1 align="center">学院信息修改</h1>
 <form action="colSearch" method="post">
    <table align="center" width="35%">
    <tr>
    <td colspan="2"><label id="message">${ msg }</label></td>
    </tr>
    <tr>
    <td style=" width: 90px" align=right>学院编号:</td>
    <td><input type="text" name="cid"  id="cid"  value="${co.cid}"  /></td>
    </tr>
    
      <tr>
    <td align=right>学院名称:</td>
    <td><input type="text" name="cname"  id="cname"  value="${co.cname }" /></td>
    </tr>
    
    <tr>
    <td align=right>院长:</td>
    <td><input type="text" name="cleader"  id="cleader"  value="${co.cleader }" /></td>
    </tr>
    
    <tr>
    <td align=right>总专业数:</td>
    <td><input type="text" name="cmajorsum"  id="cmajorsum"  value="${co.cmajorsum }" /></td>
    </tr>
    
     <tr>
    <td align=right>总班级数:</td>
    <td><input type="text" name="cbanjisum"  id="cbanjisum"  value="${co.cbanjisum }" /></td>
    </tr>
    
     <tr>
    <td align=right>学院信息:</td>
    <td><input type="text" name="cinfo" id="cinfo" value="${co.cinfo }"/></td>
    </tr>
    <tr></tr>
    <tr>
    <input type="hidden" name="action" value="reup" />
    <input type="hidden" name="pageNow" value="${pageNow }" />
     <td colspan="2" align="center">
    <input type="submit"  value=" 确认修改 "  style="width: 95px;height:30px; color:olive; font-size: small" /> &nbsp; &nbsp;
<!-- 
    <input type="reset" value="重新填写" style="width:95px;height:30px; color:olive; font-size: large"/></td>
-->
    </tr>
    </table>
    </form>
  </body>
</html>
