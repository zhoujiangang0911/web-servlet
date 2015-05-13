<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
</head>
<body style="background-image:url('image/10.jpg')";>
<table width="200" border="0" align="right" cellpadding="5" cellspacing="0" style="background:#006699">

	<tr>
		<td align="center">
			<table width="150" height="40" style="background-color:#CCCCCC">
				<tr>
					<td align="center" style="font-size:20px">
					<!-- a超链接的标签 href是超链接的地址 target执行a标签之后，显示的位置 -->
						<a href="manSearch?action=list" target="main"><font color="#000000" face="新宋体"><b>用户信息</b></font></a>
					</td>
				</tr>
			</table>				
		</td>
	</tr>
	
		<tr>
		<td align="center">
			<table width="150" height="40" style="background-color:#CCCCCC">
				<tr>
					<td align="center" style="font-size:20px">
					<!-- a超链接的标签 href是超链接的地址 target执行a标签之后，显示的位置 -->
						<a href="colSearch?action=list" target="main"><font color="#000000" face="新宋体"><b>学院信息</b></font></a>
					</td>
				</tr>
			</table>				
		</td>
	</tr>
	
	<tr>
		<td align="center">
			<table width="150" height="40" style="background-color:#CCCCCC">
				<tr>
					<td align="center" style="font-size:20px">
					<!-- a超链接的标签 href是超链接的地址 target执行a标签之后，显示的位置 -->
						<a href="plaSearch?action=list" target="main"><font color="#000000" face="新宋体"><b>地图信息</b></font></a>
					</td>
				</tr>
			</table>				
		</td>
	</tr>
	
</table>
</body>
</html>
