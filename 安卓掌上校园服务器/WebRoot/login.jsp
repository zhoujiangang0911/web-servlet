<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>欢迎进入安卓掌上校园系统</title>
<script type="text/javascript">
	/*验证登录名和密码是否为空，如果用户名为空，焦点就是用户名，密码为空，焦点就是密
	 码。
	 如果都不为空，那么就将表单内容进行提交。*/
	function check() {
		
		var id = document.getElementById("uid");
		var psw = document.getElementById("password");
		
		if (id.value == 0) {
			alert("账asdfasdf不能为空！");
			hello.uid.focus();
			id.select();
		} else if (psw.value == 0) {
			alert("密码不能为空");
			hello.password.focus();
			psw.select();
		} else {
			
			hello.submit();
		}
	}
	//输入用户名密码，选择了正确的角色身份之后，直接点击enter 键，就会触发登陆按钮的单击事件。
	
	function checks() {
		document.Myform.action =DistrictServlete.action;
		Myform.submit();
	}
	
	function on_return() {
		if (window.event.keyCode == 13) {
			if (document.all('sub') != null) {
				document.all('sub').click();
			}
		}
	}
</script>
</head>
<body style="background-image:url('image/10.jpg')";>
	<table width="100%" height="100%" border="0">
		<!--按百分比-->
		<tr height="207">
			<td width="340"></td>
			<td colspan="2">
				<table align="center" border="0">
					<tr height="103">
						<Td></Td>
					</tr>
					<tr>
						<td align="center"><font
							style="font-family:'迷你简隶书';
font-size:56px">安卓掌上校园系统</font>
						</td>
					</tr>
					<tr>
						<td align="center"><font
							style="font-family:'迷你简隶书'; 
font-size:30px">服务器</font>
						</td>
					</tr>
				</table></td>
			<td width="340"></td>
		</tr>
		<tr height="207">
			<td></td>
			<td><img src="image/2.jpg" width="280" height="220" />
			</td>

			<td width="340" align="center">
				<form action="login" method="post" name="hello">
					<table border="0" width="313" height="204">
						<tr height="40px">
							<td colspan="3" align="center">${msg}</td>
						</tr>
						<tr height="32px">
							<td>账号：</td>
							<td><input type="text" name="uid" style="width:180px"
								tabindex="1" id="uid" />
							</td>
							<!--
tabindex 使table 键按照自己的要求的顺序往下走。-->
						</tr>
						<tr height="32px">
							<Td>密码：</Td>
							<td><input type="password" name="password"
								style="width:180px" tabindex="2" id="password" />
							</td>
						</tr>
						<!-- 
<tr height="32px">
<Td>身份：</Td>
<td>
 
<input type="radio" name="role" value="student" checked="checked" tabindex="3"/>学生
<input type="radio" name="role" value="teacher" tabindex="4"/>教师

<input type="radio" name="role" value="admin" tabindex="5"/>管理员
</td>
</tr>
-->
						<tr height="32px">
							<Td colspan="2" align="center">&nbsp;
							<input type="button"
								id="sub" value="登陆" onclick="check()"
								style="
										width:55px; height:34px" tabindex="6" /> &nbsp;
								&nbsp;&nbsp; 
								<input type="reset" value="重置"
								style=" width:55px; height:34px" tabindex="7" /> &nbsp;&nbsp;
								
								 <a
								href="register.jsp" onclick="checks()">注册</a></Td>
						</tr>
						</form>
						<form action="DistrictServlete" method="post" name="DistrictServlete"><input type="submit" value="注册"
								style=" width:55px; height:34px" tabindex="7" /> </form>
					</table>
			</td>

		</tr>
		<tr>
			<td width="340"><br>
			</td>
			<td colspan="2" align="center" valign="middle">&copy; 2015-06-05
				www.xust.edu.cn <br />
			<font> 西安市临潼区陕鼓大道48号 Tel: 029-83151385 83151375</font></td>

		</tr>
	</table>
</body>
</html>