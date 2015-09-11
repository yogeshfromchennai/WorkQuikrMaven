<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<%@page import="com.hexaware.workquikr.console.vo.ApplicationVO"%>
<%@page import="com.hexaware.workquikr.console.vo.RegisteredApplicationVO"%>
<%@page import="com.hexaware.workquikr.console.vo.SlaveVO"%>
<%@page import="com.hexaware.workquikr.console.vo.AppEnvironmentVO"%>
<%@page import="com.hexaware.workquikr.console.delegate.CatalogDelegate"%>
<%@page import="com.hexaware.framework.config.ConfigHandler"%>


<%@page import="java.util.List"%>

<title>WorkQuikr Console - AppDownLoad</title>

<link rel="stylesheet" href="../css/jquery.mobile-1.1.1.css" />
<script src="../js/jquery-1.7.1.js"></script>
 <script src="../js/jquery.mobile-1.1.1.js"></script>


<link rel="shortcut icon" href="../images/hlogo-20120418-favicon.ico" >
<link rel="stylesheet" type="text/css" href="../css/mstyle.css" />
<link rel="stylesheet" type="text/css" href="../css/jquery.mobile-1.1.1.min.css" />
<script type="text/javascript" src="../js/jquery.mobile-1.1.1.min.js"></script>
<% 
String reqUserAgent = request.getHeader("user-agent");
String userAgent=null;
System.out.println(reqUserAgent);
if(reqUserAgent.contains("Mac OS X"))
{
	//Mac Product mobile devices
	userAgent="ios";
	
}
else if(reqUserAgent.contains("Android"))
{
	//Android OS devices
	userAgent="android";
}
else if(reqUserAgent.contains("BlackBerry"))
{
	//Android OS devices
	userAgent="BlackBerry";
	System.out.println("userAgent: "+userAgent);
}
else
{
	//Other Device Impl
	userAgent="android";
}
%>
</head>
<body  onload="useragent();" bgcolor="#edf0f2" >
<% 
String serverHost=null;
serverHost= ConfigHandler.getInstance().getSystemProperty("server.host");
System.out.println("------->"+serverHost);
%>
<div data-role="page" id="page1">
<div class="mtopheader"></div>
	<div id="mheader">
		<div class="mcontainerk">
							<div class="mlogo-align">
						<img src="../images/HexlogWhite1.png" class="mlogo" />
					</div>
			<span style="color:white;font-family:Times New Roman;text-shadow:2px 2px 21px #f2ad18;"><span style="font-size:35px;font-weight:bold;">W</span><span style="font-size:20px;font-weight:bold;">ork</span><span style="font-size:35px;font-weight:bold;">Q</span><span style="font-size:20px;font-weight:bold;">uikr</span></span>
			</div>
		</div><br/><br/>
		<div data-role="content" style="padding: 15px">
			 <div data-role="collapsible-set" data-theme="b" data-content-theme="b">
			  <%
			 				CatalogDelegate catalogDelegate=new CatalogDelegate();
							List<ApplicationVO> deployedDetails=catalogDelegate.getAllApplications();
						//	System.out.println("*********"+deployedDetails);
							 %>
					 <div data-role="collapsible" data-collapsed="">
					 <h3>
                            App Repository
                        </h3>
                        <ul data-role="listview"  class="item" data-divider-theme="b" data-inset="true">
                       	<%
								String tmp="";
                       		
							for (ApplicationVO deploymentVO : deployedDetails) {
								int androidAvailable=0;
                       			int iosAvailable=0;
                       			int bbAvailable=0;
								for(SlaveVO slave:deploymentVO.getSlvaeList())
								 {
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
									
								
								System.out.println("app name :"+deploymentVO.getContext()+" androidAvailable"+androidAvailable+" iosAvailable"+iosAvailable+" bbAvailable"+bbAvailable);
								if(userAgent.equalsIgnoreCase("android") && androidAvailable>0){
									
								
								if(!tmp.equals(deploymentVO.getContext()))
								{
								tmp=deploymentVO.getContext();
								%>
								
								<li data-role="list-divider" class="item" role="heading" data-icon="arrow-d" data-iconpos="left"><% String lcltm=deploymentVO.getContext(); String res=lcltm.replaceAll("-web","");%><%=res%><span style="float: right;"><a href="https://play.google.com/store/apps"><img src="../images/GooglePlayLogo.png"/></a></span></li>
								
								<%
								}
								}
								else if(userAgent.equalsIgnoreCase("ios") && iosAvailable>0){
									if(!tmp.equals(deploymentVO.getContext()))
									{
										tmp=deploymentVO.getContext();
										%>
										
										<li data-role="list-divider" role="heading" data-icon="arrow-d" data-iconpos="left"><% String lcltm=deploymentVO.getContext(); String res=lcltm.replaceAll("-web","");%><%=res%><span style="float: right;margin-bottom: 1px;"><a href="https://itunes.apple.com"><img src="../images/AppleStoreLogo1.png"/></a></span></li>
										
										<%
									}
								}
								else if(userAgent.equalsIgnoreCase("BlackBerry") && bbAvailable>0){
									if(!tmp.equals(deploymentVO.getContext()))
									{
										tmp=deploymentVO.getContext();
										%>
										
										<li data-role="list-divider" role="heading" data-icon="arrow-d" data-iconpos="left"><% String lcltm=deploymentVO.getContext(); String res=lcltm.replaceAll("-web","");%><%=res%></li>
										
										<%
									}
								}
								
								String androidApp=deploymentVO.getContext().replace("-web","");
							
									
									String lcltmk=slave.getSourcePath(); String resk=lcltmk.replaceAll("-web","");
									String result=resk.replaceAll("[0-9]", ""); String trimmed=result.replaceAll("[.]", "");String appversion=resk.replaceAll(trimmed, "");
									String appURL="";
									if(userAgent.equalsIgnoreCase("android")){
										
										 appURL="/WorkQuikr-Download/"+trimmed+"/"+slave.getVersion()+"/Android/"+androidApp+"-debug"+slave.getVersion()+".apk";
										 System.out.println("appURL: "+appURL);
									}									
									else if(userAgent.equalsIgnoreCase("ios")){
									//	appURL="itms-services://?action=download-manifest&url=http://172.25.106.108:8080/WorkQuikr-Download/"+trimmed+"/"+androidApp+".plist";
										appURL="itms-services://?action=download-manifest&url="+serverHost+"/WorkQuikr-Download/"+trimmed+"/"+slave.getVersion()+"/iOS/"+resk+".plist";
										 System.out.println("appURL: "+appURL);
									}
									else if(userAgent.equalsIgnoreCase("BlackBerry")){
											appURL="/WorkQuikr-Download/"+trimmed+"/"+appversion+"/"+"BB/OTAInstall/"+trimmed+".jad";
											 System.out.println("appURL: "+appURL);
										}
									if(userAgent.equalsIgnoreCase("android") &&  appEnvVo.getDeviceType().equalsIgnoreCase("android")){
													
											%>	
										<li data-theme="c">
										<a href="<%=appURL%>" rel="external" data-icon="arrow-d" data-iconpos="right" ><% String lcltm=slave.getSourcePath(); String res=lcltm.replaceAll("web",""); %><%=res%></a>
										</li>
										<%	
										}
										else if(userAgent.equalsIgnoreCase("ios") && appEnvVo.getDeviceType().equalsIgnoreCase("ios") ){
										%>
										<li data-theme="c">
										<a href="<%=appURL%>" rel="external" data-icon="arrow-d" data-iconpos="right" ><% String lcltm=slave.getSourcePath(); String res=lcltm.replaceAll("web",""); %><%=res%></a>
										</li>
											<%									
											}
									      else if(userAgent.equalsIgnoreCase("BlackBerry") && appEnvVo.getDeviceType().equalsIgnoreCase("blackberry")){%>						
										<li data-theme="c">
										<a href="<%=appURL%>" rel="external" data-icon="arrow-d" data-iconpos="right" ><% String lcltm=slave.getSourcePath(); String res=lcltm.replaceAll("web",""); %><%=res%></a>
										</li>
											<%
											}										
		  							}

								%>				
								<%
							} 
							 }
							
							%>
                        </ul>
					 </div>
			 </div>

<!--             	<div class="footer">© Hexaware Technologies. All rights reserved.<span class="logout"><a href="mindex.html" style="color:white;background:#f2ad18;font-weight: bolder;padding: 3px 3px 3px 3px;text-decoration:none;">Back</a></span></div> -->
		</div>
		<div class="mlogin-footer">
						 <div data-theme="a" data-role="footer" data-position="fixed">
			                	<div data-role="controlgroup" data-type="horizontal" >
			                	<span style="font-size: 10px;" >&copy;Hexaware Technologies. All rights reserved.</span>
			                	<a href="mindex.html" style="float: right;background: #f2ad18;"data-role="button" data-icon="back" data-iconpos="right" rel="external">Back</a>
			                	</div>		        
   			   </div>
   			   </div>
</div>
</body>
</html>