<%@ page language="java" import="java.util.*" import="cn.rjtraining.model.User,cn.rjtraining.model.Place"
			

 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
List<Place> ls =(List<Place>) request.getAttribute("placelist");
int a= ls.size();
double lon[] = new double [a];
double lat[]  = new double [a];
String ss[] = new String [a];
for(int i =0;i< a;i++){
	lon[i] = ls.get(i).getPlongtitude();
	lat[i] = ls.get(i).getPlatitude();
	ss[i] = ls.get(i).getPname();
}	
	
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>用户分布模块</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  <style type="text/css">
  td{ text-align: center}
  </style>
  <style type="text/css">
	body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=iO8pqDGQUvP8GzMyjRPoiOe8"></script>
	<style type="text/css">
		.baidumap{
			width:500px; 
			height:500px;
		}
	</style>
  </head>
    <body bgcolor="#f0f5fb">
	<div style="height:50px  ;background: #e2e2e2" align="right" >
		<h1 align="center" ><font color="#000000">学员分布</font></h1>
	</div>
    <div style="height:700px ;align="right" id="allmap"></div>
  </body>
</html>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("allmap");    // 创建Map实例
	map.centerAndZoom(new BMap.Point(109.199823, 34.372851), 17);  // 初始化地图,设置中心点坐标和地图级别
	map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
	map.setCurrentCity("西安");          // 设置地图显示的城市 此项是必须设置的
	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
	map.centerAndZoom(point, 12);
	function addMarker(point){
	  var marker = new BMap.Marker(point);
	  map.addOverlay(marker);
	}
	// 编写自定义函数,创建标注
	function addMarker(point,label){
		var marker = new BMap.Marker(point);
		map.addOverlay(marker);
		marker.setLabel(label);
	}
	// 随机向地图添加25个标注
	var bounds = map.getBounds();
	var sw = bounds.getSouthWest();
	var ne = bounds.getNorthEast();
	var lngSpan = Math.abs(sw.lng - ne.lng);
	var latSpan = Math.abs(ne.lat - sw.lat);
	for (var i = 0; i < <%=a%>; i ++) {
	     <%
	     	for (int i=0;i<a;i++){
	     %>
	     var marker = new BMap.Marker(point);
		var point = new BMap.Point(<%=lon[i]%>,<%=lat[i]%> );
		var label = new BMap.Label("<%=ss[i]%>",{offset:new BMap.Size(20,-10)});
		addMarker(point,label);
		<%}
		%>
	}
	alter(a);
	
</script>
	
	

