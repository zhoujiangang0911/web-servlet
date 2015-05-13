<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title>修改密码</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<script type="text/javascript">
function check(){
	var psw1=document.getElementById("psw1");
	var psw2=document.getElementById("psw2");
	if(psw1.value.length>=6){
		if(psw1.value==psw2.value){
			pswup.submit();
		}else{
			alert("两次密码不相同，请重新输入");
			psw2.value="";
			psw1.select();
		}		
	}else{
		alert("密码不能小于6位");
		psw1.value="";
		psw2.value="";
		psw1.focus();
	}
}
</script>
  </head>  
  <body bgcolor="#f0f5fb">
    <h1 align="center">修改密码</h1>
    <form action="pswUpdate" method="post" name="pswup">
    <table align="center">
    <tr>
    <td colspan="2">${msg }</td>
    </tr>
    <tr>
    <td>用户账号:</td>
    <td><input type="text" name="uid" value="${uid}" readonly="readonly"/></td>
    </tr>
      <tr>
    <td>用户姓名:</td>
    <td><input type="text" name="uname" value="${uname }" readonly="readonly"/></td>
    </tr>
    <tr>
    <td>原始密码：</td>
    <td><input type="password" name="oldpassword" placeholder="请输入旧密码..."/></td>
    </tr>
    <tr>
    <td>更新密码：</td>
    <td><input type="password" name="newpsw"  id="psw1"  placeholder="请输入新密码..."/></td>
    </tr>
    <tr>
    <td>密码确认：</td>
    <td><input type="password" name="repsw" id="psw2"  placeholder="请确认新密码..."/></td>
    </tr>
    <tr>
    <input type="hidden" name="action" value="confirm" />
    <td colspan="2" align=center><input type="button"  value="提交" onclick="check()"/>
    <input type="button" value="重置" onclick="javascript:hostory(-1);"/>
    </td>
    </tr>
    </table>
    </form>
  </body>
</html>
