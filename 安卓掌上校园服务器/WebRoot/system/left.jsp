<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
int one = 20;
int two = 100;
int three = 50;
int four = 30; 
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
						<a href="manSearch?action=list" target="main"><font color="#000000" face="新宋体"><b>学生用户</b></font></a>
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
						<a href="manSearch?action=otherlist" target="main"><font color="#000000" face="新宋体"><b>其他用户</b></font></a>
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
	
	<!-- 
	<tr>
		<td align="center">
			<table width="150" height="40" style="background-color:#CCCCCC">
				<tr>
					<td align="center" style="font-size:20px">
					
						<a href="userdistribution?action=list"  target="main"><font color="#000000" face="新宋体"><b>用户分布</b></font></a>
					</td>
				</tr>
			</table>				
		</td>
	</tr>
	
	 -->
	 	<tr>
		<td align="center">
			<table width="150" height="40" style="background-color:#CCCCCC">
				<tr>
					<td align="center" style="font-size:20px">
						<a href="jfree?action=list"  target="main"><font color="#000000" face="新宋体"><b>院系分布</b></font></a>
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
						<a href="map?action=list"  target="main"><font color="#000000" face="新宋体"><b>地图显示</b></font></a>
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
						<a href="download.jsp"  target="main"><font color="#000000" face="新宋体"><b>APP下载</b></font></a>
					</td>
				</tr>
			</table>					
		</td>
	
	<tr>
		<td align="center">
			<table width="150" height="40" style="background-color:#CCCCCC">
				<tr>
					<td align="center" style="font-size:20px">
						<a href="userjfree?action=list"  target="main"><font color="#000000" face="新宋体"><b>老乡分布</b></font></a>
					</td>
				</tr>
			</table>					
		</td>
	</tr>
	
	
</table>
</body>
</html>
