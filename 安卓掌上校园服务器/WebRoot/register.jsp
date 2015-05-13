<%@ page language="java" import="java.util.*" import="java.util.List"  import="cn.rjtraining.model.District" pageEncoding="UTF-8"%>
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
function on_return() {
	if (window.event.keyCode == 13) {
		if (document.all('sub') != null) {
			document.all('sub').click();
		}
	}
}
</script>
<script type="text/javascript">
	jQuery(document).ready(function(){
		//省市联动
		jQuery("#district").CascadingSelect(
			jQuery("#district1"),
			"getdistrict.htm?",
			{datatype:"json",textfield:"name",valuefiled:"id",parameter:"id"},
			function(){
				jQuery("#district1").AddOption("请选择城市","",true,0);
				jQuery("#district2").html("");
			}
		);
		jQuery("#district1").CascadingSelect(
			jQuery("#district2"),
			"getdistrict.htm?",
			{datatype:"json",textfield:"name",valuefiled:"id",parameter:"id"},
			function(){
				//jQuery("#district2").AddOption("请选择","-1",true,0);
			}
		);
		//省市联动
	});
</script>
	</head>
	<body bgcolor="#dddddd" onkeydown="on_return();">
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
					<select id = "sheng" name = "shengshi" >
					<option value = "学生">学生</option>
					<option value = "老师">老师</option>
					<option value = "游客">游客</option>
					</td>
				</select>
				</tr>
				
				<tr height="32px">
					<td>
						账号：
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
						<input type="text" name="uname" style="width: 180px" id="uname" />
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
				</tr>
				<tr height="32px">
					<td>
						选择籍贯：
					</td>
					<td>
					<select id = "sheng" name = "shengshi" >
					
                    <option value="$!{item.id}">$</option>
					<%
						 List<District> ls = new ArrayList();
						ls = (List<District>)request.getAttribute("listsheng");
						for (Object o : ls){
							District d =(District) o;
							System.out.println(d.getName());
						}
					 %>
					 
					
		
					<option value = "陕西省">陕西省</option>
					
					</td>
				</select>
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
