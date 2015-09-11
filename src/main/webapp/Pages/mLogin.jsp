<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1"> 
<title>WorkQuikr Console - Login</title>
<link rel="shortcut icon" href="images/hlogo-20120418-favicon.ico" >
<link rel="stylesheet" type="text/css" href="css/mstyle.css" />
<link rel="stylesheet" type="text/css" href="css/jquery.mobile-1.1.1.min.css" />
<script type="text/javascript" src="js/jquery.mobile-1.1.1.min.js"></script>

<script>
window.onload = function(){ 
//	alert("welcome");
	var error='<%=request.getParameter("fail") %>';
	var x="";
	 if(error == 'null' || error == ""){
         x.innerHTML='';
   }
   else{
         x=document.getElementById('errormsg_0_Passwd');
         x.innerHTML = error;
   }
}
</script>
</head>
<body  bgcolor="#edf0f2" >
<div class="topheader"></div>
	<div id="header">
		<div class="containerk">
							<div class="logo-align">
						<img src="images/HexlogWhite1.png" class="logo" />
					</div>
			<span style="color:white;font-family:Times New Roman;text-shadow:2px 2px 21px #f2ad18;"><span style="font-size:35px;font-weight:bold;">W</span><span style="font-size:20px;font-weight:bold;">ork</span><span style="font-size:35px;font-weight:bold;">Q</span><span style="font-size:20px;font-weight:bold;">uikr</span></span>
			</div>
		</div><br/><br/><br/>
		<!-- 		added for login box -->
			<div class="login-container-box">
				<fieldset class="fieldset">
				<legend><img src="images/nex.jpg"/><span style="font-size:19px;">S</span>ign <span style="font-size:19px;">I</span>n</legend>
					<center>
						<div class="signin-box">
						  <form name="frm" action="LoginServlet" method="post">
						  <table border="0">
						
						<tr>
						<th></th>
						<th></th>
						</tr>
						
						<tbody>
						<tr>
						<td></td>
						<td><input type="text" class="roundborder" name="Email"
										id="Email" placeholder="Username" />
									</td>
						</tr>
						<tr>
						<td></td>
						<td><input type="password"  class="roundborder" name="Passwd" id="Passwd" placeholder="Password" /></td>
						</tr>
						<tr>
						</tr>
						<tr>
						<td></td>
						<td><a><input type="submit"  class="roundbtn" name="signIn" id="signIn"
						         value="Submit"/></a>
						        <a><input type="reset" class="roundbtn" name="signIn" id="signIn"
						       /></a></td>
						</tr>
						</tbody>
						</table>
						
						</form>
						<br/>			
				<span  id="errormsg_0_Passwd"  style="color:red;font-size:15px;background-repeat: no-repeat;padding:3px 0px 0px 18px;"></span>
					</div>
					</center>
				</fieldset>
				</div><br/><br/><br/><br/>
				
				<div class="footer">© Hexaware Technologies. All rights reserved.</div>
</body>
</html>