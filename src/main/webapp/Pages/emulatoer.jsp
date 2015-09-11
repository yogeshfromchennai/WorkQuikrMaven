<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
	#top{
	
	margin:0px;
	}
	#bottom{
	margin:0px;
	}
	#left{
	margin-top:-5px;
	margin-bottom:-5px;
	background-image: leftpanel.png;
	float:left;
	}
	#right{
	margin-top:-5px;
	margin-bottom:-5px;
		background-image: rightpanel.png;
	float: left;
	}
	#browser{
	border:0px;
	margin-top:-5px;
	margin-bottom:-5px;
	background-image: leftpanel.png;
	float: left;
	}
</style>
<%
String url=request.getParameter("url"); 
%>
</head>
<body>
<center>
<div style="width:300px; height:300px;" >
	<div class="top" >
		<img src="images/top.png"  width="300px"/>
	</div>
	<div>
		<div id="left">
		<img src="images/leftpanel.png" height="300px"/>
		</div>
		<div id="browser">
		<iframe height="300px" width="251px" src="<%=request.getParameter("url")%>"></iframe>
		</div>
		
		<div id="right">
		<img src="images/rightpanel.png" height="300px"/>
		</div>
	</div>
	<div class="bottom">
	<img src="images/bottom.png"  width="300px"/>
	</div>
</div>	
</center>
</body>
</html>