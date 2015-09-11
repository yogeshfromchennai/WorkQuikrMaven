<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.hexaware.workquikr.console.vo.ApplicationVO"%>
<%@page import="com.hexaware.workquikr.console.vo.RegisteredApplicationVO"%>
 <%@page import="com.hexaware.workquikr.console.delegate.NotificationDelegate"%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<head>
<%
	System.out.println("Username " + request.getRemoteUser());
	if(request.isUserInRole("manager")||request.isUserInRole("admin")) {
		System.out.println("User in Manager/Admin role");
		
	}
	else {
		 
		System.out.println("User in General role");
		%>
		<SCRIPT type="text/javascript">
		 document.getElementById("ulmenu").children[1].style.display="none";
		 document.getElementById("ulmenu").children[3].style.display="none";
		 document.getElementById("ulmenu").children[4].style.display="none";
		 </SCRIPT>
		 <%
	}
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WorkQuikr Console - Notification</title>
<link rel="shortcut icon" href="../images/hlogo-20120418-favicon.ico" >
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<script type="text/javascript" src="../js/jquery-1.7.1.js"></script>
<link rel="stylesheet" type="text/css" href="../css/jquery.alerts.css" />
<script type="text/javascript" src="../js/jquery.alerts.js"></script>
<script>
function getselectall(allattr){
	//var namek=allattr.name;
	//var checkboxes =document.getElementsByName(namek);
	//for(var i in checkboxes){		
      //  checkboxes[i].checked = allattr.checked;
    //}
}
</script>
<script>
function checkk(attrib){
	var input=attrib.name;
	//alert(input);
	var inputs = document.getElementsByName(input);
	//alert(inputs);
	var flag=0;
	for (var i = 0; i<inputs.length; i++) {
		if(!inputs[i].checked){
			$(".sel").attr('checked', false);
		}
	}	 
}
$(document).ready(function() {
	$(".sel").click(function(){
		
		 window.msg= $(this).attr('name');
		 window.msgk= $(this).attr('alt');
		//alert("type "+window.msgk);
		$('input[alt='+msgk+'][name='+msg+']').attr('checked', this.checked);
		
		//$('input [name=DemoCitiBank-web]').attr('checked',true);
		
	});
});
</script>
<script>
function push(alattr){
	var input=alattr.name;
	var deviceType=alattr.title;
	//alert("device type "+deviceType);
	var inputs = document.getElementsByName(input);
	var myArray = new Array();
	var queryString="";
	
	// Checking For Atleast One Selection
	var flag=0;
	for (var i = 0; i<inputs.length; i++) {
		if(inputs[i].checked){
			flag++;
		}
	}
	if(flag == 0){
		jAlert('Select a Device To Push');
		return false;
	}	
	for (var i = 0; i<inputs.length; i++) {
		if(inputs[i].checked){
			//alert(i);
			var deviceid = inputs[i].id;
			//alert("deviceid "+i+" "+ deviceid);
			var devicetoken = document.getElementById(deviceid).value;
			//alert("token "+devicetoken);
			if(devicetoken!="select-all")
				{
				 queryString+=deviceid+",";
				}
			var appname=document.getElementById(deviceid).name;
			if(devicetoken == "select-all"){
 				i+=1;
			} 
			
		}		
	}	
	window.location.href="/WorkQuikr-console/Pages/notify?act=3&deviceid="+queryString+"&appname="+appname+"&deviceType="+deviceType;
}
</script>

</head>
<body bgcolor="#edf0f2">
<div class="topheader"></div>

	<div id="header">
		<div class="containerk">
				<div class="logout"><a  href="logout.jsp" >Logout</a></div>
					<div class="logo-align">
						<img src="../images/HexlogWhite.png" class="logo" />
					</div>
<span style="color:white;font-family:Times New Roman;text-shadow:2px 2px 21px #f2ad18;"><span style="font-size:60px;font-weight:bold;">W</span><span style="font-size:30px;font-weight:bold;">ork</span><span style="font-size:60px;font-weight:bold;">Q</span><span style="font-size:30px;font-weight:bold;">uikr</span></span>
			</div>
		</div>
		<div class="menu_background">
			<div class="containerl">
				<div class="topmenu">
					<ul>
						<li><a href="/WorkQuikr-console/Pages/Home" >Catalog</a>
						</li>
						<li><a href="/WorkQuikr-console/Pages/Download">App Repository</a>
						</li>
						<li><a href="/WorkQuikr-console/Pages/notification.jsp" class="menulinkbg">Notification</a>
						</li>
						<li><a href="/WorkQuikr-console/Pages/report.jsp">Report</a> 
						</li>
						<li><a href="/WorkQuikr-console/Pages/remotedisable.jsp">RemoteDisable</a></li>
					</ul>

				</div>
			</div>
		</div>
		<div id="container">
			<div id="content">
		
			<fieldset class="fieldsetk">
				<legend class="legend"><img src="../images/mytry.png"/>Push Notification</legend>
		  	
				<%
				NotificationDelegate notificationDelegate=new NotificationDelegate();
						List<RegisteredApplicationVO> registeredapplicationDetails =notificationDelegate.getRegisteredApplicationList();
						System.out.println("*********"+registeredapplicationDetails);
					%>
						
												<%
							String deployedFileName = (String) request.getAttribute("message");
							if (deployedFileName != null && !deployedFileName.equals("")) {
								%>
								<div  class="success">
								 <% 
								out.print(deployedFileName);
								 %>
								 
								 </div><br/><% 
							}
						%>
						
						<table class="checktableStyle" align="center">

						<tr>
							<th>Application</th>
 							<th>Registered Users/UDID</th> 
<!--							<th>Version</th>-->
							<th>Registered Date</th>
							<th></th>
							<th></th>
						</tr>
						 
						<%
							String tmp = "";
						String deviceTypek="";
							for (RegisteredApplicationVO registeredVO : registeredapplicationDetails) {
								//	System.out.println("Device Type outside--->" +registeredVO.getDevice_Type()+"APP NAME outside--->"+registeredVO.getApplicationName()+" User Name outside--->"+registeredVO.getDevice_Id() );
								if (!tmp.equals(registeredVO.getApplicationName())) {
									//System.out.println("Device Type inside--->" +registeredVO.getDevice_Type()+"APP NAME inside--->"+registeredVO.getApplicationName()+" User Name inside--->"+registeredVO.getDevice_Id());
									tmp = registeredVO.getApplicationName();
									deviceTypek="";
									
						%>
						<tr class="borderline">
							<td style="padding-top: 1.5em;" >&nbsp;<b><% String lcltm=registeredVO.getApplicationName(); String res=lcltm.replaceAll("-web","");%><%=res%></b>
							</td>
<!--							<td></td>-->
							<td></td>
							<td></td>
<!--							onclick="getselectall(this);"-->
							<td style="padding-top: 1.5em;"><center></center></td>
							<td style="text-align:center;padding-top: 1.5em;"></td>
						</tr>
						
						<%
						
								}
								if (!deviceTypek.equals(registeredVO.getDevice_Type())) {
									deviceTypek=registeredVO.getDevice_Type();
									//System.out.println("dvc:"+deviceTypek+"VO"+registeredVO.getDevice_Type()+"APP NAME inside--->"+registeredVO.getApplicationName()+" User Name inside--->"+registeredVO.getDevice_Id());
							
						%>
						<tr class="borderline">
							
							<% String lcltm=registeredVO.getDevice_Type(); 
							if(lcltm.equalsIgnoreCase("android")){
							%>
							
							<td style="padding-top: 1.5em;" align="center">&nbsp;<img src="../images/icon_android.png" alt="android" border="0"  /></td>
							<%
							} else if (lcltm.equalsIgnoreCase("ios")) {%>
							<td style="padding-top: 1.5em;" align="center" >&nbsp;<img src="../images/icon_ios.png" alt="ios" border="0" /></td>
							<%} %>
							<td></td>
							<td></td>					
							<td style="padding-top: 1.5em;"><center><input type="checkbox" value="select-all" name="<%=registeredVO.getApplicationName()%>" id="<%=registeredVO.getApplicationName()%>" alt="<%=registeredVO.getDevice_Type()%>" class="sel" value="select-all" onclick="getselectall(this);" /></center></td>
							<td style="text-align:center;padding-top: 1.5em;"><input type="button" Value="Push" name="<%=registeredVO.getApplicationName()%>" src="<%=registeredVO.getDevice_Token()%>" title="<%=registeredVO.getDevice_Type()%>" onclick="push(this);" /></td>
						</tr>
						
						<%
						
								}
						%>
						
						<tr>
						<td></td>
						<td><%=registeredVO.getDevice_Id() %></td> 
<!--						<td><%=registeredVO.getVersion() %></td>-->
						<td><% Date date=registeredVO.getDate(); String formats[]={"dd-MM-yyyy"}; SimpleDateFormat sdf=new SimpleDateFormat(formats[0]); String d=sdf.format(date); %><%=d %></td>
<%-- 						<a href="/WorkQuikr-console/notify?act=3&deviceToken=<%=registeredVO.getDevice_Token()%>">Push</a> --%>
						<td><center><input type="checkbox" name="<%=registeredVO.getApplicationName()%>" id="<%=registeredVO.getS_no()%>" value="<%=registeredVO.getDevice_Token()%>" alt="<%=registeredVO.getDevice_Type()%>" onclick="checkk(this)" ></input></center></td>
						<td></td>
						</tr>
						
						<%
										
								
						}
						%>
						
					</table> 
					<br/>
				</fieldset>


		</div>
</div>
<div id="footer">
		<p>© Hexaware Technologies. All rights reserved.</p>
	</div>
</body>
</html>