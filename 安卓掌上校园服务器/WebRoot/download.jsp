<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<HTML>
	<HEAD>
		<META http-equiv=Content-Type content="text/html; ">
		<META NAME="Author" CONTENT="Alpha">
		<TITLE>FileDownload</TITLE>
	</HEAD>
	<BODY bgcolor="#6b9ac9">

			<div style="width: 100%; height: 100px ; left:200px"  >
			<h1 style="margin-left: 560px">扫描二维码下载</h1>
		</div>
		<div style="width: 100%; height: 350px ; left:200px"  >
			<img   align="center" src="image/a.png" style="margin-left: 530px" >
			<!-- <img src="http://qrcoder.sinaapp.com?t=http://192.168.1.111:8080/xscj/"  style="margin-left: 530px" align="center" > -->
		</div>
		
		<P ALIGN="CENTER" >
			
			<form action="FileDownload" method="post" name="FileDownload">
				<input type="button" value="AppDownload" onclick="submit()" 
					style="width: 100px; height:38px;background-color:#f52443 ;margin-left: 620px"  tabindex="100"  />
			</form>
		</P>
	</BODY>
</HTML>