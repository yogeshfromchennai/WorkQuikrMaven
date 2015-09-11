<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" href="images/hlogo-20120418-favicon.ico">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="js/ajaxrequest.js"></script>
<script type="text/javascript" src="js/hex-framework.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
function chk(){
	
	window.location.href="/WorkQuikr-console/XMLAdapter";
}
// Xml Adapter Invoker
function invokeXmlAdapter()
{
	alert("inside adapter");
	var name=null;
	var procedure=null;
	var param=null;
	 
	
	if(param=="undefined")
	{
		param=null;
	}
	
	AjaxRequest.post(
			  {
				'url':"http://172.25.108.44:8080/WorkQuikr-console/XMLAdapter",
				'parameters':{ 'name':name, 'procedure':procedure,"param":param},  
			    'onSuccess':onResult,
			    'onError':onError
			  }
			); 
}
onResult= function success(req){
	alert(req.responseText);
	var out=req.responseXML;
	txt1="";
	txt2="";
	txt3="";
	txt4="";
	var d=out.getElementsByTagName("id");
	var x=out.getElementsByTagName("Maths");
	var s=out.getElementsByTagName("science");
	var l=out.getElementsByTagName("Language");
	for (i=0;i<d.length;i++)
    {
    txt1=txt1 + d[i].childNodes[0].nodeValue + "<br />";
    }
	for (i=0;i<x.length;i++)
    {
    txt2=txt2 + x[i].childNodes[0].nodeValue + "<br />";
    }
	
	for (i=0;i<s.length;i++)
    {
    txt3=txt3 + s[i].childNodes[0].nodeValue + "<br />";
    }
	
	for (i=0;i<l.length;i++)
    {
    txt4=txt4 + l[i].childNodes[0].nodeValue + "<br />";
    }
	document.getElementById("maths").innerHTML=txt2;
	document.getElementById("Id").innerHTML=txt1;
	document.getElementById("science").innerHTML=txt3;
	document.getElementById("Language").innerHTML=txt4;
	document.getElementById("xmlAdapterOutput").innerHTML=req.responseXML;
};
onError=function fail(){
	alert(req.responseText);
};

function callFunction(){
	
	var frm=document.getElementById("xmlAdapterOutput");
	
	 	ifrm = document.createElement("IFRAME");
	   ifrm.setAttribute("src", "http://localhost:8080//WorkQuikr-console/XMLAdapter");
	   ifrm.setAttribute("frameBorder", 0);
	   ifrm.style.width = 550+"px";
	   ifrm.style.height =200+"px";
	   frm.appendChild(ifrm);
	  
}

function xmlInvoker(){
	invokeXmlAdapter();
}

</script>
</head>
<body  bgcolor="#edf0f2" onload='callFunction()'>
	<div class="topheader"></div>
	<div id="header">
		<div class="containerk">
			<div class="logout">
				<a href="Login.jsp">Logout</a>
			</div>
			<div class="logo-align">
				<img src="images/HexlogWhite.png" class="logo" />
			</div>
<span style="color:white;font-family:Times New Roman;text-shadow:2px 2px 21px #f2ad18;"><span style="font-size:60px;font-weight:bold;">W</span><span style="font-size:30px;font-weight:bold;">ork</span><span style="font-size:60px;font-weight:bold;">Q</span><span style="font-size:30px;font-weight:bold;">uikr</span></span>
		</div>
	</div>
		<div class="menu_background">
		<div class="containerl">
			<div class="topmenu">
					<ul>
						<li><a href="index.jsp" >Catalog</a>
						</li>
						<li><a href="appdownload.jsp">App Repository</a>
						</li>
						<li><a href="notification.jsp" >Notification</a>
						</li>
						<li><a href="xmladapter.jsp" class="menulinkbg">Report</a> 
						</li>
					</ul>

			</div>
		</div>
	</div>
	<div id="container">
	<div id="content">
				
				<input type="button" value="XmlAdapter" onclick="chk();"/>
				<br/>
				<input type="button" value="Consume" onclick="invokeXmlAdapter()"/>
				<div id="xmlAdapterOutput" style="color: green;"> 
				
				</div>
				<br/>
				<br/>
				<iframe src="http://localhost:8080//WorkQuikr-console/XMLAdapter" frameBorder="9"></iframe>
				<br/>
				<br/>
				<div id="frameOutput">
				</div>
				<br/>
				<br/>
				<table border="1">
				<tr style="background-color: cyan;">
				<td>Id</td>
				<td>Maths</td>
				<td>Science</td>
				<td >Language</td>
				</tr>
				<tr>
				<td id="Id"></td>
				<td id="maths"></td>
				<td id="science"></td>
				<td id="Language"></td>
				</tr>				
				</table>				
				
	</div>
	</div>

</body>
</html>