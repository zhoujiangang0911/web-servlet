<%@ page language="java" import="java.util.*" import="java.util.List"  import="cn.rjtraining.model.District" 
	import="cn.rjtraining.model.College" 


 import="cn.rjtraining.model.UserType" pageEncoding="UTF-8"%>
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
		<title>注册管理员</title>
<script type="text/javascript" src="scripts/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="scripts/easy_validator.pack.js"></script>
<script type="text/javascript" src="scripts/jquery.bgiframe.min.js"></script>
<link  href="scripts/validate.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="scripts/jquery.filloptions.js"></script>
<script type="text/javascript" src="scripts/jquery.cascadingselect.js"></script>

		<script type="text/javascript">
//输入用户名密码，选择了正确的角色身份之后，直接点击enter键，就会触发登陆按钮的单击事件。

</script>

<script type="text/javascript">
function aa() {
	alter("asdas");
}

</script>
	</head>
	<body bgcolor="#e2e2e2" onkeydown="on_return();">
		<!-- body的属性onkeydown，这样在按下enter之后，就出发了on_return()方法。 -->
		<h1 align="center">
			管理员注册
		</h1>
		<form action="register" method="post" name="hello">
			<table border="1" align="center" bgcolor="">
			
					<tr height="32px">
					<td>
						用户类型：
					</td>
					<td>
					<select id ="usertype" name = "usertype" >
                    <option value="">选择用户类型</option>
						<%    
						List<UserType> ls = new ArrayList();
						ls = (List<UserType>)request.getAttribute("usertype");
						for (Object o : ls){
							UserType ut =(UserType) o;
							System.out.println(ut.getUsertypeid());
							System.out.println(ut.getUsertypename());
							%>
						<option  value="<%=ut.getUsertypeid()%>"><%=ut.getUsertypename()%></option>
						<%	
						}
					 	%>
					</td>
				</select>
				
				</tr>
				
				<tr height="32px">
					<td>
						学号：
					</td>
					<td>
						<input type="text" name="uid" style="width: 180px" id="uid" />
					</td>
					<!-- tabindex使table键按照自己的要求的顺序往下走。 -->
				</tr>
				<tr height="32px">
					<td>
						姓名：
					</td>
					<td>
						<input type="text" name="uname" style="width: 180px" id="uid" />
					</td>
					<!-- tabindex使table键按照自己的要求的顺序往下走。 -->
				</tr>
				<tr height="32px">
					<td>
						密码：
					</td>
					<td>
							<input type="password" name="password" style="width: 180px"
							id="password" />
					</td>
					
					<!-- tabindex使table键按照自己的要求的顺序往下走。 -->
				</tr>
				<tr height="32px">
					<td>
						年龄：
					</td>
					<td>
						<input type="text" name="age" style="width: 180px" id="uid" />
					</td>
					<!-- tabindex使table键按照自己的要求的顺序往下走。 -->
				</tr>
				<tr height="32px">
					<td>
						性别：
					</td>
					<td>
						<select id ="sex" name = "sex" >
                    		<option value="男">男</option>
					 		<option value="女">女</option>	
					 	</select>
					</td>
					<!-- tabindex使table键按照自己的要求的顺序往下走。 -->
				</tr>
				<tr height="32px">
					<td>
						电话：
					</td>
					<td>
						<input type="text" name="phone" style="width: 180px" id="uid" />
					</td>
					<!-- tabindex使table键按照自己的要求的顺序往下走。 -->
				</tr>
				<tr height="32px">
					<td>
						地址：
					</td>
					<td>
						<input type="text" name="address" style="width: 180px" id="uid" />
					</td>
					<!-- tabindex使table键按照自己的要求的顺序往下走。 -->
				</tr>
				<tr height="32px">
					<td>
						选择院系：
					</td>
					<td>
					<select id ="college" name = "college" >
					
                    <option value="">选择院系</option>
						<%    
						List<College> lsss = new ArrayList();
						lsss = (List<College>)request.getAttribute("listcollege");
						for (Object o : lsss){
							College c =(College) o;
							%>
						<option  value="<%=c.getCid() %>"><%=c.getCname()%></option>
						<%	
						}
					 	%>
					 	</select>
					</td>
				</tr>
				
			
				
				<tr height="32px">
					<td>
						选择籍贯：
					</td>
					<td>
					<select id ="sheng" name = "sheng" >
					
                    <option value="">选择省份</option>
						<%    
						List<District> lss = new ArrayList();
						lss = (List<District>)request.getAttribute("listsheng");
						for (Object o : lss){
							District d =(District) o;
							%>
						<option  value="<%=d.getId() %>"><%=d.getName() %></option>
						<%	
						}
					 	%>
					 	</select>
					</td>
				
				</tr>
				<tr height="32px">
					<td colspan="2" align="center">
						&nbsp; &nbsp; &nbsp;
						<input type="submit" id="sub" value="注 册" 
							style="width: 55px; height: 34px" />
						&nbsp; &nbsp;&nbsp;
						<input type="reset" value="重 置" style="width: 55px; height: 34px" 
							tabindex="7" />
							
						<input type="hidden" name="action" value="register" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
