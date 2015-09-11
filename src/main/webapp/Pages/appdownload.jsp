<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.hexaware.workquikr.console.vo.ApplicationVO"%>
<%@page
	import="com.hexaware.workquikr.console.vo.RegisteredApplicationVO"%>
<%@page import="com.hexaware.workquikr.console.vo.AppEnvironmentVO"%>
<%@page import="com.hexaware.workquikr.console.vo.SlaveVO"%>
<%@page import="com.hexaware.workquikr.console.delegate.CatalogDelegate"%>
<%@page import="com.hexaware.framework.config.ConfigHandler"%>

<%@page import="java.util.List"%>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WorkQuikr Console - AppDownLoad</title>
<link rel="shortcut icon" href="../images/hlogo-20120418-favicon.ico">
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<script type="text/javascript" src="../js/jquery-1.7.1.js"></script>

</head>
<body bgcolor="#edf0f2">
	<% 
String serverHost=null;
serverHost= ConfigHandler.getInstance().getSystemProperty("server.host");
System.out.println("------->"+serverHost);
%>
	<div class="topheader"></div>

	<div id="header">
		<div class="containerk">
			<div class="logout">
				<a href="logout.jsp">Logout</a>
			</div>
			<div class="logo-align">
				<img src="../images/HexlogWhite.png" class="logo" />
			</div>
			<span
				style="color: white; font-family: Times New Roman; text-shadow: 2px 2px 21px #f2ad18;"><span
				style="font-size: 60px; font-weight: bold;">W</span><span
				style="font-size: 30px; font-weight: bold;">ork</span><span
				style="font-size: 60px; font-weight: bold;">Q</span><span
				style="font-size: 30px; font-weight: bold;">uikr</span>
			</span>
		</div>
	</div>
	<div class="menu_background">
		<div class="containerl">
			<div class="topmenu">
				<ul>
					<% 
if(request.isUserInRole("manager")||request.isUserInRole("admin")) {	
%>
					<li><a href="/WorkQuikr-console/Pages/Home">Catalog</a></li>
					<%
}
%>
					<li><a href="/WorkQuikr-console/Pages/Download"
						class="menulinkbg">App Repository</a></li>
					<% 
if(request.isUserInRole("manager")||request.isUserInRole("admin")) {	
%>
					<li><a href="/WorkQuikr-console/Pages/notification.jsp">Notification</a>
					</li>
					<li><a href="/WorkQuikr-console/Pages/report.jsp">Report</a></li>
					<li><a href="/WorkQuikr-console/Pages/remotedisable.jsp">RemoteDisable</a></li>
					<%
}
%>
				</ul>

			</div>
		</div>
	</div>
	<div id="container">
		<div id="content">

			<fieldset class="fieldsetk">
				<legend class="legend">
					<img src="../images/mytry.png" />Application Repository
				</legend>
				<div class="deployedApp">
					<%
						
							List<ApplicationVO> deployedDetails =(List<ApplicationVO>)request.getAttribute("DeploymentVoList");
							System.out.println("*********"+deployedDetails);
							
							 %>
					<table class="checktableStyle" align="center">

						<tr>
							<th>Name</th>
							<th>Version</th>
							<th>Download</th>


						</tr>
						<tr>
							<td>&nbsp;</td>
							<td></td>
							<td></td>

						</tr>
						<%
								String tmp="";
							for (ApplicationVO deploymentVO : deployedDetails) {
								
								if(!tmp.equals(deploymentVO.getContext()))
								{
								tmp=deploymentVO.getContext();
								%>
						<tr class="borderline">
							<td><b> <% String lcltm=deploymentVO.getContext(); String res=lcltm.replaceAll("-web","");%><%=res%></b>
							</td>
							<td></td>
							<td></td>
						</tr>
						<%
								}
								String androidApp=deploymentVO.getContext().replace("-web","");
								for(SlaveVO slave:deploymentVO.getSlvaeList())
								 {
									String lcltmk=slave.getSourcePath(); String resk=lcltmk.replaceAll("-web","");
									String result=resk.replaceAll("[0-9]", ""); String trimmed=result.replaceAll("[.]", "");String appversion=resk.replaceAll(trimmed, ""); 
								    String appAndroidURL=serverHost+"/WorkQuikr-Download/"+trimmed+"/"+slave.getVersion()+"/Android/"+androidApp+"-debug"+slave.getVersion()+".apk";
								    String appIosURL=serverHost+"/WorkQuikr-Download/"+trimmed+"/"+slave.getVersion()+"/iOS/"+resk+".plist";
								    String appBlackberryURL=serverHost+"/WorkQuikr-Download/"+trimmed+"/"+appversion+"/BB/StandardInstall/"+trimmed+".cod";
								   
								
								%>
						<tr>
							<td></td>
							<td>
								<% String lcltm=slave.getSourcePath(); String res=lcltm.replaceAll("-web","");String output=res.replaceAll(trimmed, ""); %><%=output%></td>
							<td>
								<% int web_id=slave.getWebId();
// 							List<AppEnvironmentVO> appEnvironment = db.getDeviceType(web_id);
							int androidAvailable=0;int iosAvailable=0;int bbAvailable=0;
							%> <% 
  							for (AppEnvironmentVO appEnvVo : slave.getApEnvList()) {
								
									
								if(appEnvVo.getDeviceType().equalsIgnoreCase("android")){
									androidAvailable=androidAvailable+1;
									}
									else if(appEnvVo.getDeviceType().equalsIgnoreCase("ios")){
										iosAvailable=iosAvailable+1;
									}
									else if(appEnvVo.getDeviceType().equalsIgnoreCase("blackberry")){
										bbAvailable=bbAvailable+1;
									}
  							}	
							if(androidAvailable>0){
								%> <a href="<%=appAndroidURL%>"
								title="<%=slave.getSourcePath()%>" name="android"> <img
									src="../images/icon_android.png" alt="android" border="0" /></a><% }else {%> <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> <%}	if(iosAvailable>0){%>
								<a href="<%=appIosURL%>" title="<%=slave.getSourcePath()%>"
								name="ios"> <img src="../images/icon_ios.png" alt="ios"
									border="0" /></a> <%}else {%> <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
								<% }if(bbAvailable>0){%> <a href="<%=appBlackberryURL%>"
								title="<%=slave.getSourcePath()%>" name="blackberry"> <img
									src="../images/icon_blackberry.png" alt="ios" border="0" /></a> <%}else {%>
								<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> <%
								}
							%>
							</td>
						</tr>
						<%
							} 
							 }
							
							%>
					</table>
				</div>
			</fieldset>


		</div>
	</div>

	<div id="footer">
		<p>&copy; Hexaware Technologies. All rights reserved.</p>
	</div>
</body>
</html>