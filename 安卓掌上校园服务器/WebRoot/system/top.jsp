<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>">
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>安卓掌上校园系统</title>
<style type="text/css">
a{ font-family:"迷你简隶书"; background-color:#CCC; font-size:17px}
</style>
</head>
<body style="background-image:url('image/10.jpg')">
<div style="width:100%; height:135px;">
<table width="953" height="145" border="0">
<tr height="40">
<td width="250"></td>
<td colspan="4" style="text-align:center; vertical-align:middle; font-family:'迷你简隶书'; font-size:40px">安卓掌上校园系统</td>
<td></td>
<td width="35"></td>
<td width="35"></td>
<td width="70" valign="top"><a href="system/main.html" target="main">返回首页</a></td>
<td width="70" valign="top"><a href="#" onclick="window.opener=null;window.open('','_self');window.close();">退出系统</a></td>
</tr>
<tr>
<td></td>
<td width="100"></td>
<td width="100"></td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
</tr>
<tr height="30">
<td colspan="3"  ><font style="background-color:transparent; font-family:'宋体'">欢迎 <b><span>${uname}</span></b> 进入管理系统</font></td>
<td></td>
<td></td>
<td></td>
<td  align="center" width="40"><a href="javascript:history.go(-1);">后退</a></td>
<td  align="center"><a href="javascript:history.go(1);">前进</a></td>
<td  width="80" align="center"><a href="<%=basePath%>login.jsp" target="_parent">重新登陆</a></td>
<td  align="center"><a href="pswUpdate?action=update" target="main">修改密码</a></td>
</tr>
</table>
</div>
</body>
</html>
