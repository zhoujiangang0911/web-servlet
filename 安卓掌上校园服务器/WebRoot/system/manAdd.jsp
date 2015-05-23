<%@ page language="java" import="java.util.*" import="java.util.List"  import="cn.rjtraining.model.District" 
	import="cn.rjtraining.model.College" 
	import="java.util.List"  import="cn.rjtraining.model.District" 
	import="cn.rjtraining.model.College" 


 import="cn.rjtraining.model.UserType" 
	pageEncoding="utf-8"%>
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
  <h1 align="center">学生用户注册</h1>
 <form action="manSearch" method="post">
    <table align="center"  border="1">
    <tr>
    <td colspan="2"><label id="message" align="center" style="color:red;">${msg}</label></td>
    </tr>
    <tr>
    <td width=75px align=right>学号:</td>
    <td width=75px><input type="text" name="uid"  id="uid" /></td>
    </tr>
      <tr>
    <td align=right>姓名:</td>
    <td><input type="text" name="uname"  id="uname"/></td>
    </tr>
     <tr>
    <td align=right>密码:</td>
    <td><input type="text"  name="password"  id="password"/></td>
    </tr>
    <tr  align="right">
					<td>
						院系：
					</td>
					<td align="left">
					<select  align="right" id ="college" name = "college" >
					
                    <option align="right" value="">选择院系</option>
						<%    
						List<College> ls = new ArrayList();
						ls = (List<College>)request.getAttribute("listcollege");
						for (Object o : ls){
							College d =(College) o;
							%>
						<option  value="<%=d.getCid() %>"><%=d.getCname() %></option>
						<%	
						}
					 	%>
					 	</select>
					</td>
				
				</tr>
    
    <!--  <tr>
    <td width=75px align=right>学院编号:</td>
    <td width=75px><input type="text" name="college"  id="college" /></td>
    </tr> -->
    
    
      <tr>
    <td align=right>年龄:</td>
    <td><input type="text" name="age"  id="age"/></td>
    </tr>
    
       <tr>
    <td align=right>性别:</td>
    <td><select name="sex" width=40px>
    <option value="男">男</option>
    <option value="女">女</option>
    </select>
    </tr>
    
  
    
    
     <tr>
    <td align=right>住址:</td>
    <td><input type="text"  name="address"  id="address"/></td>
    </tr>
     <tr>
    <td align=right>联系电话:</td>
    <td><input type="text"  name="phone"  id="phone"/></td>
    </tr>
    
    <tr  align="right">
					<td>
						籍贯：
					</td>
					<td align="left">
					<select  id ="sheng" name = "sheng" >
					
                    <option  value="">选择省份</option>
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
    
   <!--   <tr>
    <td width=75px align=right>籍贯编号:</td>
    <td width=75px><input type="text" name="sheng"  id="sheng" /></td>
    </tr> -->
    
   <input type="hidden" name="action" value="insert" />
     <tr>
    <td colspan="2"  align="center">
    <input type="submit"  value=" 注 册 " style="width: 80px;height:30px; color:olive; font-size:15px" /> &nbsp; &nbsp; 
   <input type="button"  value="取消"   style="width: 80px;height:30px; color:olive; font-size: 15px" onclick="javascript:history.go(-1);" />
   </td>
    </tr>
    
    </table>
    </form>
  </body>
</html>
