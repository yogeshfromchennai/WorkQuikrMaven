<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.hexaware.workquikr.console.vo.ApplicationVO"%>
<%@page import="com.hexaware.workquikr.console.vo.AdapterVo"%>
<%@page import="com.hexaware.workquikr.console.vo.AppEnvironmentVO"%>
<%@page import="com.hexaware.workquikr.console.delegate.CatalogDelegate"%>
<%@page import="com.hexaware.workquikr.console.vo.SlaveVO"%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%-- <%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%> --%>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WorkQuikr-Console</title>
<!-- Added for Jquery -->
<script type="text/javascript" src="../js/jquery-1.7.1.js"></script>
<link rel="shortcut icon" href="../images/hlogo-20120418-favicon.ico" >

<link rel="stylesheet" type="text/css" href="../css/style.css" />
<SCRIPT type="text/javascript">
function iframeSetter(urlName,type)
{
window.landingurl=urlName;
window.deviceType=type;
document.getElementById('ifrm-portrait').src='/'+urlName;
document.getElementById('ifrm').src='/'+urlName;
document.getElementById('android-ifrm-portrait').src='/'+urlName;
document.getElementById('blackberry-ifrm-portrait').src='/'+urlName;
if(deviceType=="android"){
	$("#portrait").hide();
	$("#android-portrait").show();
	$("#ios-portrait").hide();
	$("#landscape").hide();
	$("#ios-landscape").hide();
	$("#android-landscape").hide();
	$("#blackberry-portrait").hide();
	$("#blackberry-landscape").hide();
	
}
else if(deviceType=="ios"){	
	$("#ios-portrait").show();
	$('#ifrm').show();
	$("#portrait").hide();
	$("#landscape").hide();
	$("#ios-landscape").hide();
	$("#android-portrait").hide();
	$("#android-landscape").hide();
	$("#blackberry-portrait").hide();
	$("#blackberry-landscape").hide();
}
else if(deviceType=="blackberry"){	
	$("#blackberry-portrait").show();
	$("#blackberry-landscape").hide();
	$("#ios-portrait").hide();
	$("#portrait").hide();
	$("#landscape").hide();
	$("#ios-landscape").hide();
	$("#android-portrait").hide();
	$("#android-landscape").hide();

	
}

}
</SCRIPT>
<script>

	$(document).ready(function() {
		// alert("loaded");
		
		$("#btnclick").click(function(){
			//alert(document.getElementById("deploy1").src);
		$("#shrink").toggle("slow"); return true;});
		$("#adptpclick").click(function(){$("#shrink1").toggle("slow"); return true;});
		$("#depclick").click(function(){$("#deploy").toggle("slow"); return true;});
	});
	
</script>
<!-- added for pop-up window -->
<script type="text/javascript" src="../js/jqModal.js"></script>
<script>
$().ready(function() {
  $('#dialog').jqm();
});
</script>
<link rel="stylesheet" type="text/css" href="Emulatorcss/style.css" />
<STYLE type="text/css">
  
.jqmWindow {
    display: none;
    
    position: fixed;
    top: 5%;
    left: 50%;
    
    margin-left: -300px;
/*     width: 500px; */
    
    background-color: #000 transparent;
    color: #333;
/*     border: 1px solid black; */
    padding: 12px;
}

.jqmOverlay { background-color: #000; }

* html .jqmWindow {
     position: absolute;
     top: expression((document.documentElement.scrollTop || document.body.scrollTop) + Math.round(17 * (document.documentElement.offsetHeight || document.body.clientHeight) / 100) + 'px');
}
</STYLE>
<script>
function checkk(attrib){
	 if(document.getElementById('sell').checked == false && attrib.checked == true)
		 {
		 document.getElementById('sell').checked = true;
		 }
	 if(document.getElementById('sell').checked == true && attrib.checked == false){
		 document.getElementById('sell').checked = false;
	 }
	 
}

  
$(document).ready(function() {
	window.bool="land";
	$("#reload").click(function(){
		
		if(window.bool=="land" && window.deviceType=="android")
			{
			
			document.getElementById('android-ifrm-landscape').src='/'+window.landingurl;
		$("#landscape").hide();
		$("#android-landscape").show("slow");
		$("#portrait").hide();
		$("#ios-portrait").hide();
		$("#ios-landscape").hide();
		$("#android-portrait").hide();
		$('#android-ifrm-landscape').show("slow");
		$("#blackberry-portrait").hide();
		$("#blackberry-landscape").hide();
		window.bool="port";
			}
		else if(window.bool=="port" && window.deviceType=="android")
			{
			document.getElementById('android-ifrm-portrait').src='/'+window.landingurl;
			$("#landscape").hide();
			$("#portrait").hide();
			$("#ios-portrait").hide();
			$("#ios-landscape").hide();
			$("#android-portrait").show("slow");
			$("#android-landscape").hide();
			$('#android-ifrm-portrait').show("slow");
			$("#blackberry-portrait").hide();
			$("#blackberry-landscape").hide();
			window.bool="land";
				}
		else if(window.bool=="land" && window.deviceType=="ios"){
			document.getElementById('ios-ifrm-landscape').src='/'+window.landingurl;
			$("#ios-landscape").show("slow");
			$("#ios-portrait").hide();
			$("#landscape").hide();			
			$("#portrait").hide();
			$("#android-portrait").hide();
			$("#android-landscape").hide();
			$('#ios-ifrm-landscape').show("slow");		
			$("#blackberry-portrait").hide();
			$("#blackberry-landscape").hide();
			window.bool="port";
		}
		else if(window.bool=="port" && window.deviceType=="ios"){
			document.getElementById('ifrm').src='/'+window.landingurl;
			$("#ios-portrait").show("slow");
			$("#ios-landscape").hide();
			$("#landscape").hide();			
			$("#portrait").hide();	
			$("#android-portrait").hide();
			$("#android-landscape").hide();
			$('#ifrm').show("slow");
			$("#blackberry-portrait").hide();
			$("#blackberry-landscape").hide();
			window.bool="land";
		}
		else if(window.bool=="land" && window.deviceType=="blackberry"){
			document.getElementById('blackberry-ifrm-landscape').src='/'+window.landingurl;
			$("#blackberry-portrait").hide();
			$("#blackberry-landscape").show("slow");
			$("#blackberry-ifrm-landscape").show("slow");
			$("#ios-landscape").hide();
			$("#ios-portrait").hide();
			$("#landscape").hide();			
			$("#portrait").hide();
			$("#android-portrait").hide();
			$("#android-landscape").hide();
			$('#ios-ifrm-landscape').hide();
			window.bool="port";
		}
		else if(window.bool=="port" && window.deviceType=="blackberry"){
			document.getElementById('blackberry-ifrm-portrait').src='/'+window.landingurl;
			$("#blackberry-portrait").show("slow");
			$("#blackberry-landscape").hide();
			$("#blackberry-ifrm-portrait").show("slow");
			$("#ios-portrait").hide();
			$("#ios-landscape").hide();
			$("#landscape").hide();			
			$("#portrait").hide();	
			$("#android-portrait").hide();
			$("#android-landscape").hide();
			$('#ifrm').hide();
			window.bool="land";
		}
		
	}
			);
	
	$(".sel").click(function(){
		
		 window.msg= $(this).attr('name');
		//alert("name "+window.msg);
		$('input[name='+msg+']').attr('checked', this.checked);
	//	document.getElementById("hid").value=window.msg;
// 		$('input [name=DemoCitiBank-web]').attr('checked',true);
		
	});
	

	
});
</script>
<!-- pop-window ends -->

<!-- Added for Confirm Box -->
<link rel="stylesheet" type="text/css" href="../css/jquery.alerts.css" />
<script type="text/javascript" src="../js/jquery.alerts.js"></script>
<script>
function undeployAdap(variable) {
	//alert(variable);
	var src=variable;
	jAlert('Confirmed: Confirmation Results');
	jConfirm('Are You Sure?', 'Confirm', function(r) {
		if(r == true){
			window.location.href="/WorkQuikr-console/Pages/Undeploy?type=undeployAdapter&source="+src;
		}
	});
}
</script>
<!-- Confirm Box ends here -->
<!-- Added for Form Validation starts here -->
<script>
function validate(){
	
	var str=document.frm.file.value;
	if (document.frm.file.value.length == 0){
		jAlert('Select a file to Deploy');
		return false;
	}
	
	if(str.indexOf(".war") == -1 && str.indexOf(".xml") == -1 ) {	
		jAlert('Invalid Deployment Profile');
		return false;
	}
	return true;
}
</script>
<!-- Form Validation ends here -->
<!-- Bulk Delete starts here -->
<script>
function getselectall(allattr){
// 	var namek=allattr.name;
// 	alert(namek);
// 	var chk,checkboxes =document.getElementsByName(namek);
// 	alert(checkboxes);
// 	for(var i in checkboxes){
// 		alert("inside loop "+i+" "+checkboxes[i]);
// 		alert("wor "+allattr.checked);
// 		alert(checkboxes[i].checked);
// 			var chk=checkboxes[i];
// 			alert("google "+chk);
// 			chk.checked=allattr.checked;
			
			
//     }
}
</script>
<script>
function del(allattributes){
	
	
	var input=allattributes.name;
	var inputs = document.getElementsByName(input);
	var queryString="";
	//Checking For Atleast One Selection
	var flag=0;
	for (var i = 0; i<inputs.length; i++) {
		if(inputs[i].checked){
			flag++;
		}
	}
	if(flag == 0){
		jAlert('Select an application  to undeploy');
		return false;
	}
	jConfirm('Are you sure, you want to undeploy?', 'Confirm', function(r) {
		if(r == true){

			for (var i = 0; i<inputs.length; i++) {
				if(inputs[i].checked){
					
					var webId=inputs[i].id;
					var sourcepath=document.getElementById(webId).value;
					//alert("checked"+i+"  "+"webId"+webId+" "+sourcepath);
					var appname=document.getElementById(webId).name;
					if(webId == "sell"){
		 				i+=1;
					}  
					if(webId != "sell"){
						queryString+=webId+",";
					}  
				}
			}
			window.location.href="/WorkQuikr-console/Pages/Undeploy?type=undeployRegisteredApplication&source="+appname+"&webId="+queryString;
		}
	});
	
	}
</script>
<!-- Bulk Delete Ends here -->

<style type="text/css">
		.frmPos{
		position:absolute;
		left:50px;
		top:50px;
		}
		.androidPort{
position:absolute;
left:39px;
top:78px;
}
.androidland{
position:absolute;
left:78px;
top:33px;
}
.blackberryPort{
position:absolute;
left:41px;
top:130px;
}
.blackberryland{
position:absolute;
left:132px;
top:41px;
}
	</style>
	
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
					<ul id="ulmenu">	
		
						<li><a href="/WorkQuikr-console/Pages/Home" class="menulinkbg">Catalog</a>
						</li>
						<li><a href="/WorkQuikr-console/Pages/Download">App Repository</a>
						</li>			
						<li><a href="/WorkQuikr-console/Pages/notification.jsp">Notification</a>
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
				<legend class="legend" id="depclick"><img src="../images/mytry.png" id="deploy1"/>Deploy</legend>
				<div id="deploy">
					<!-- <div id="deployHeader">&nbsp;&nbsp;&nbsp;<img src="images/mytry.png"/>Deployer</div> -->
					<div style="text-align:center;">
					<form action="UploadFile" method="post"
						enctype="multipart/form-data" name="frm" onSubmit="return validate();">
						<div id="deployHolder">
							Select a war/Adapter(xml) file to Deploy: <input type="file" name="file"
								size="50" /> <input type="submit" value="Deploy" /><br/>
								<input type="checkbox" name="chbox" value="true"/>Notify all registered users<br/>
								NOTE: <i>Ensure all the dependencies have been deployed</i>
						</div>
					</form>
<!-- 					<p class="success"> -->
						<%
							String deployedFileName = (String) request.getAttribute("message");
							if (deployedFileName != null && !deployedFileName.equals("")) {
								%>
								<div  class="success">
								 <% 
								out.print(deployedFileName);
								 %>
								 </div><% 
								 
							}
						%>
<!-- 						</p> -->
					</div>
				</div>
			</fieldset>
			<br />

			<fieldset class="fieldsetk">

				<legend class="legend" id="btnclick"><img src="../images/mytry.png"/>Deployed Applications</legend>



				<!-- <div id="deployHeader">&nbsp;&nbsp;&nbsp;<img src="images/mytry.png"/>Deployed Application</div> -->
				<div class="deployedApp" id="shrink">
					<%
					
						List<ApplicationVO> deployedDetails =(List<ApplicationVO>)request.getAttribute("DeploymentVoList");
						
					%>
<!-- 					<center> -->
					
						<%
							String deployedFileNamek = (String) request.getAttribute("msg");
							if (deployedFileNamek != null && !deployedFileNamek.equals("")) {
								%>
								<div  class="success" align="center">
								
								 <% 
								out.print(deployedFileNamek);
								 %>
								 </div><br/><% 
							}
						%>
						
					<table class="checktableStyle" align="center">

						<tr>
							<th >Name</th>
							<th>Preview</th>
							<th>Version</th>
							<th>Deployed Date</th>
							<th>Undeploy</th>
							<th></th>
						</tr>

					
						<%
							String tmp = "";
						for (ApplicationVO deploymentVO: deployedDetails) { 		 
						%>
						<tr class="borderline">
							<td class="jclick" style="padding-top: 1.5em;"><b><% String lcltm=deploymentVO.getContext(); String res=lcltm.replaceAll("-web","");%><%=res%></b>
							</td>
							<td style="padding-top: 1.5em;"></td>
							<td style="padding-top: 1.5em;"></td>
							<td style="padding-top: 1.5em;"></td>
							<td style="padding-top: 1.5em;"><center><input type="checkbox" name="<%=deploymentVO.getContext() %>" id="sell" class="sel" value="select-all" onclick="getselectall(this);" ></input></center><input type="hidden" id="hid"/></td>
							<td style="padding-top: 1.5em;"><input type="button" name="<%=deploymentVO.getContext()%>" value="Undeploy" onclick="del(this);"/></td>
						</tr>
					<%  for(SlaveVO slave:deploymentVO.getSlvaeList())
					 {
					 %>
						<tr class="codeA">
							<td></td>
							<td>
								
<!--								<% String trim=slave.getSourcePath(); String inter=trim.replaceAll("-web",""); String result=inter.replaceAll("[0-9]", ""); String trimmed=result.replaceAll("[.]", ""); %><%=trimmed %>-->
							
							<% int web_id=slave.getWebId();
// 							List<AppEnvironmentVO> appEnvironment = db.getDeviceType(web_id);
							int androidAvailable=0;int iosAvailable=0;int bbAvailable=0;
							%>
							<% 
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
								%>
								<a href="#" class="jqModal" title="<%=slave.getSourcePath()%>" name="android" onclick="iframeSetter(this.title,this.name)">
									<img src="../images/icon_android.png" alt="android" border="0"/></a>
							<% }else {%>
							<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
							<%}	if(iosAvailable>0){%>
							<a href="#" class="jqModal" title="<%=slave.getSourcePath()%>" name="ios" onclick="iframeSetter(this.title,this.name)">
									<img src="../images/icon_ios.png" alt="ios" border="0" /></a>
							<%}else {%>
							<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
							<% }if(bbAvailable>0){%>
							<a href="#" class="jqModal" title="<%=slave.getSourcePath()%>" name="blackberry" onclick="iframeSetter(this.title,this.name)">
									<img src="../images/icon_blackberry.png" alt="Blackberry" border="0" /></a>
							<%}else {%>
							<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
								<%
								}
							%>
							</td>
					
							<td>
								<center><%=slave.getVersion()%></center></td>
							<td><% Date date=slave.getDeploymentDate(); String formats[]={"dd-MM-yyyy"}; SimpleDateFormat sdf=new SimpleDateFormat(formats[0]); String d=sdf.format(date); %><%=d%></td>

							<td><center><input type="checkbox" name="<%=deploymentVO.getContext() %>" id="<%=slave.getWebId() %>" value="<%=slave.getSourcePath()%>"  ></input></center></td>
							<td></td>
						</tr>
						
						<%
						
								}
							}
						%>
					</table>
					
					<div class="jqmWindow" id="dialog">
					
					<div style="float:left;">
						<div id="portrait">
								<div style="width:300px; height:100px;">
											<div id="portrait-top" >
												<img src="images/portrait/top.png"  width="296px"/>
											</div>
											<div>
												<div id="portrait-left">
												<img src="images/portrait/leftpanel.png" height="300px"/>
												</div>
											
												<div id="portrait-browser">
												<iframe height="300px" width="251px" src="http://localhost:8080/" frameBorder="0" style="background-color:white;" id="ifrm-portrait"></iframe>
												</div>
												
												<div id="portrait-right">
												<img src="images/portrait/rightpanel.png" height="300px"/>
												</div>
											</div>
											<div id="portrait-bottom">
											<img src="images/portrait/bottom.png"  width="296px"/>
											</div>
									</div>
							</div>
							
							<div id="landscape">
										<div style="width: 100%; height: 300px;">
												<div id="landscape-top"><img src="images/landscape/top.png" height="298px" /></div>
													<div id="landscape-middle" style="width: 298px">
														<div id="landscape-right"><img src="images/landscape/rightpanel.png" width="300px" /></div>
														<div id="landscape-browser"><iframe width="300px" height="258px" src="" frameBorder="0" id="ifrm-landscape" style="background-color:white;"></iframe></div>
														<div id="landscape-left"><img src="images/landscape/leftpanel.png" width="300px" /></div>
											
												</div>
											<div id="landscape-bottom"><img src="images/landscape/bottom.png" height="296px" /></div>
											</div>
											
							</div>
							
							<div id="ios-portrait">
									<img src="../images/ipad-portrait.png" height="650px" width="500px" alt="ipadimage"/>
							<iframe  class="frmPos" style="background-color:white;" height="560px" width="425px" src="" frameBorder="0" id="ifrm"></iframe>
							</div>
							<div id="ios-landscape">
								<img src="../images/ipad-landscape.png" height="500px" width="650px" alt="ipadimage"/>
								<iframe  class="frmPos" style="background-color:white;" height="425px" width="560px" src="" frameBorder="0" id="ios-ifrm-landscape"></iframe>
							</div>
							<div id="android-portrait">
							<img src="../images/android-portrait-emulator.png" height="552px" width="294px"/>
							<IFRAME class="androidPort" src="" height="378px" width="245px" style="border:none;background-color:white;" id="android-ifrm-portrait"></IFRAME>
							</div>
							<div id="android-landscape">
							<img id="androidLandImg" src="../images/android-landscape-emulator.png" height="294px" width="552px"/>
							<IFRAME class="androidland" src="" height="245px" width="378px" style="border:none;background-color:white;" id="android-ifrm-landscape"> </IFRAME>
							</div>
							<div id="blackberry-portrait">
							<img src="../images/BlackBerry_portrait_emulator.png" height="552px" width="294px"/>
							<IFRAME class="blackberryPort" src="" height="300px" width="235px" style="border:none;background-color:white;" id="blackberry-ifrm-portrait"></IFRAME>
							</div>
							<div id="blackberry-landscape">
							<img  src="../images/BlackBerry-Storm-Landscape_Emulator.png" height="294px" width="552px"/>
							<IFRAME class="blackberryland" src="" height="238px" width="307px" style="border:none;background-color:white;" id="blackberry-ifrm-landscape"> </IFRAME>
							</div>
							
						
						</div>	
						<div style="float:left;">
											<a href="#" class="jqmClose"><img src="../images/close.png" alt="close" style="border:0px;"/></a>
							</div>
							&nbsp;&nbsp;&nbsp;
							<div style="float:right;" id="reload"><a href="#"><img src="../images/icon_toolbar_rotate1-1.gif" alt="Reload" style="border:0px;"/></a></div>
					 </div>
					 
<!-- 					</center> -->
				</div>

			</fieldset><!--End of Deployed application -->
			<br />
			<!--Adapter Starts Here -->
			<fieldset class="fieldsetk">
				<legend class="legend" id="adptpclick"><img src="../images/mytry.png"/>Deployed Adapters</legend>
				<br />
				<div id="shrink1">
				<%
					List<AdapterVo> adapterDetails =(List<AdapterVo>)request.getAttribute("AdapterVoList");
				%>
				<table class="checktableStyle" align="center" >

					<tr>
						<th>Type</th>
						<th>&nbsp;</th>
<!--						<th>Resource Path</th>-->
						<th>Resource</th>
<!--						<th>Version</th>-->
						<th>Date</th>
						<th>Undeploy</th>
						</tr>
					<tr >
						<td>&nbsp;</td>
<!--						<td></td>-->
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>

						<%
							//String tmp1 = "";
						System.out.println("size"+adapterDetails.size());
						String tmp1 = "";
							for (AdapterVo adeploymentVO : adapterDetails) {
								if (!tmp1.equals(adeploymentVO.getType())) {
								tmp1 = adeploymentVO.getType();
						%>
						
				
					<tr class="borderline">
						<td><b><%=adeploymentVO.getType() %></b>	</td>
<!--						<td></td>-->
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<%
					}
					String appURL="/WorkQuikr-console/Adapters/"+adeploymentVO.getName()+".xml";
					%>
					<tr>
							<td></td>
							<td></td>
							<td><a href="<%=appURL%>"><%=adeploymentVO.getName() %></a></td>
<!--							<td><%=adeploymentVO.getName() %></td>-->
<!--							<td><%=adeploymentVO.getVersion() %></td>-->
							<td><% Date date=adeploymentVO.getDeployedDate(); String formats[]={"dd-MM-yyyy"}; SimpleDateFormat sdf=new SimpleDateFormat(formats[0]); String d=sdf.format(date); %><%=d%></td>
							<td>
<%-- 							UndeployAdapter?source=<%=deploymentVO.getResourceLocation()%>     onclick="undeployAdap(this.title)"   --%>
							<a href="#"  title="<%=adeploymentVO.getResourceLocation()%>" onclick="undeployAdap(this.title)" > <div style="text-align:center;"><img alt="Undeploy" style="border:0px;" src="../images/delete.gif"/></div></a>
							</td>
						</tr>
					<%
							}
					%>

				</table>
<!-- 				<div id="cfrm"> -->
<!-- 				<fieldset> -->
<!-- 				<LEGEND>Confirm</LEGEND> -->
<!-- 				Do you want to delete? -->
<!-- 				</fieldset> -->
<!-- 				</div> -->
				</div>
			</fieldset><!--End of Adapter -->
			<br />
		</div>


	</div>

	<div id="footer">
		<p>© Hexaware Technologies. All rights reserved.</p>
	</div>
</body>
</html>