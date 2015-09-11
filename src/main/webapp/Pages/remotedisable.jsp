<%@page import="com.hexaware.workquikr.console.delegate.RemoteDisableDelegate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<%@page import="com.hexaware.workquikr.console.vo.RemoteDisableVO"%>
<%@page import="com.hexaware.workquikr.console.delegate.NotificationDelegate"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<title>WorkQuikr Console - RemoteDisable</title>
<link rel="shortcut icon" href="../images/hlogo-20120418-favicon.ico" >
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<script type="text/javascript" src="../js/jquery-1.7.1.js"></script>

<script>
$(document).ready(function() {
	$(".sel").click(function(){
		
		 window.msg= $(this).attr('name');
		 window.msgk= $(this).attr('alt');
		//alert("type "+window.msgk);
		$('input[name='+msg+']').attr('checked', this.checked);
		
		//$('input [name=DemoCitiBank-web]').attr('checked',true);
		
	});
});
</script>
<script>
function enable(tkn){
	var token=tkn.alt;
	var isEnabled=tkn.name;
	var alter="";
	
	if(isEnabled.toLowerCase()=="true"){
		alter="false";
	}
	else if(isEnabled.toLowerCase()=="false"){
		alter="true";
	}
	
	window.location.href="/WorkQuikr-console/RemoteDisableCheck?act=3&enabler="+alter+"&token="+token;
	
}
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
					<li><a href="/WorkQuikr-console/Pages/notification.jsp">Notification</a></li>
					<li><a href="/WorkQuikr-console/Pages/report.jsp">Report</a></li>
					<li><a href="/WorkQuikr-console/Pages/remotedisable.jsp" class="menulinkbg">RemoteDisable</a></li>
				</ul>

			</div>
		</div>
	</div>
	
	<div id="container">
		<div id="content">

			<fieldset class="fieldsetk">
				<legend class="legend">
					<img src="../images/mytry.png" />Remote Disable
				</legend>
				<div class="deployedApp">
					<%
							RemoteDisableDelegate remoteDisableDelegate=new RemoteDisableDelegate();
							List<RemoteDisableVO> userDetails=remoteDisableDelegate.getUsersList();
							
							System.out.println("*********"+userDetails);
							
							 %>
					<table class="checktableStyle" align="center">

						<tr>
							<th>AppName</th>
							<th>User</th>
							<th>Date</th>
							<th>Enable/Disable</th>
							


						</tr>
						
						<%
						String tmp="";
						for(RemoteDisableVO remoteDisableVO:userDetails){
							//	System.out.println("Device Type outside--->" +registeredVO.getDevice_Type()+"APP NAME outside--->"+registeredVO.getApplicationName()+" User Name outside--->"+registeredVO.getDevice_Id() );
							if (!tmp.equals(remoteDisableVO.getAppName())) {
									tmp = remoteDisableVO.getAppName();		
									System.out.println(remoteDisableVO.getAppName());
					%>
					<tr class="borderline">
						<td style="padding-top: 1.5em;" >&nbsp;<b><% String lcltm=remoteDisableVO.getAppName(); String res=lcltm.replaceAll("-web","");%><%=res%></b></td>
						<td style="padding-top: 1.5em;"><center></center></td>
						<td style="padding-top: 1.5em;"><center></center></td>
						<td style="padding-top: 1.5em;"></td>
						
						
					</tr>
					
					<%		
							}
					%>
					<tr >
						<td></td>
						<td><center><%=remoteDisableVO.getUserID()%></center></td>
						<td ><% Date date=remoteDisableVO.getDate(); String formats[]={"dd-MM-yyyy"}; SimpleDateFormat sdf=new SimpleDateFormat(formats[0]); String d=sdf.format(date); %><%=d%></td>
						<td><input type="button" alt="<%=remoteDisableVO.getToken()%>" name="<%=remoteDisableVO.getEnabler()%>" value="Enable/Disable" onclick="enable(this);">
						 <span style="padding-left: 5px;padding-top: 3px;"><% String isEnabled=remoteDisableVO.getEnabler();
								if(isEnabled.equalsIgnoreCase("true")){
									%>
									<img src="../images/tick-21.png" alt="true" border="0" />
									<% 
								}
								else if(isEnabled.equalsIgnoreCase("false")){
									%>
									<img src="../images/wrong_21.png" alt="false" border="0"  />
									<% 
								}
								%>
						
						</span>
						</td>
					</tr>
							<% 
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