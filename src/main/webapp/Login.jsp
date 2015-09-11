<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>WorkQuikr Console - Login</title>
<link rel="shortcut icon" href="../images/hlogo-20120418-favicon.ico" >
<link rel="stylesheet" type="text/css" href="../css/mstyle.css" />
<link rel="stylesheet" type="text/css" href="../css/jquery.mobile-1.1.1.min.css" />
<script type="text/javascript" src="../js/jquery.mobile-1.1.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<script type="text/javascript">

function useragent(){
	var userAgen=navigator.userAgent;
	
	if(userAgen.search("Mobile") != -1){
		  document.getElementById("mobile").style.display = "block";
		  document.getElementById("browser").style.display = "none";
		
	}
	else if((userAgen.search("Android") != -1) && userAgen.search("Mobile") == -1){
		 document.getElementById("mobile").style.display = "block";
		  document.getElementById("browser").style.display = "none";
		
	}
	else{
		document.getElementById("mobile").style.display = "none";
		  document.getElementById("browser").style.display = "block";
	}
	
}
function checkError(){
	var error='<%=request.getParameter("fail")%>';	
	var x="";
	if(error == 'null' || error == ""){
	x.innerHTML='';
	}
	else{
	x=document.getElementById('errormsg_0_Passwd');
	document.getElementById('errormsg_0_Passwdk').innerHTML= error;
	x.innerHTML = error;
	}
}
</script>
 <base>
</head>
<body onload="useragent();checkError();" bgcolor="#edf0f2">
<div id="mobile">
<div class="mtopheader"></div>
	<div id="mheader">
		<div class="mcontainerk">
							<div class="mlogo-align">
						<img src="../images/HexlogWhite1.png" class="mlogo" />
					</div>
			<span style="color:white;font-family:Times New Roman;text-shadow:2px 2px 21px #f2ad18;"><span style="font-size:35px;font-weight:bold;">W</span><span style="font-size:20px;font-weight:bold;">ork</span><span style="font-size:35px;font-weight:bold;">Q</span><span style="font-size:20px;font-weight:bold;">uikr</span></span>
			</div>
		</div><br/><br/><br/>
		<!-- 		added for login box -->
			<div class="mlogin-container-box">
				<fieldset class="mfieldset">
				<legend><img src="../images/nex.jpg"/><span style="font-size:19px;">S</span>ign <span style="font-size:19px;">I</span>n</legend>
					<center>
						<div class="signin-box">
						  <form name="frm" action="j_security_check" method="post">
						  <table border="0">
						
						<tr>
						<th></th>
						<th></th>
						</tr>
						
						<tbody>
						<tr>
						<td></td>
						<td><input type="text" class="roundborder" name="j_username"
										 placeholder="Username" />
									</td>
						</tr>
						<tr>
						<td></td>
						<td><input type="password"  class="roundborder" name="j_password"  placeholder="Password" /></td>
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
				<span  id="errormsg_0_Passwdk"  style="color:red;font-size:15px;background-repeat: no-repeat;padding:3px 0px 0px 18px;"></span>
					</div>
					</center>
				</fieldset>
				</div>
				<br/><br/>
<!-- 				<div class="mfooter">© Hexaware Technologies. All rights reserved.</div> -->
				</div>
				
<!-- 	end of mobile part			 -->
				
<div id="browser">
<div class="topheader"> </div>

		<div class="google-header-bar">
			<div class="login-container">
<!-- 			<br clear="all" /> -->
					 <div class="logo-align"><img src="../images/HexlogWhite.png" class="logo"/></div>
					 <h1 style="color:white;font-family:Times New Roman;text-shadow:2px 2px 21px #f2ad18;"><span style="font-size:50px;">W</span>ork<span style="font-size:50px;">Q</span>uikr</h1>
					 </div>
		</div>
		<br/>
<!-- 		added for login box -->
<div class="login-container-box">
	<fieldset class="fieldset">
	<legend ><img src="../images/nex.jpg"/><span style="font-size:23px;">S</span>ign <span style="font-size:23px;">I</span>n</legend>
		<center>
			<div class="signin-box">
<!-- 			  <h2><strong>Sign in </strong></h2>   -->
			  <form name="frm" action="j_security_check" method="post">
			  <table border="0">
			
			<tr>
			<th></th>
			<th></th>
			</tr>
			
			<tbody>
			<tr>
			<td><strong class="email-label">Username</strong></td>
			<td><input type="text"  name="j_username" /></td>
			</tr>
			<tr>
			<td><strong class="passwd-label">Password </strong></td>
			<td><input type="password" name="j_password"  /></td>
			</tr>
			<tr>
			</tr>
			<tr>
			<td></td>
			<td><input type="submit" name="signIn" id="signIn"
			        style="width: 80px; height: 32px;" value="Submit">
			        <input type="reset" name="signIn" id="signIn"
			       style="width: 80px; height: 32px;"></td>
			</tr>
			</tbody>
			</table>
			
			</form>
			<br/>
<!-- 			style="color:red;font-size:13px;" -->
	<span  id="errormsg_0_Passwd"  style="color:red;font-size:17px;background-repeat: no-repeat;padding:3px 0px 0px 18px;">
<!--   The username or password you entered is incorrect. -->
   </span>
			
<!-- 			<ul class="Replaced"> -->
<!-- 			  <li><img src="images/nex.jpg"/>Can't access your account?  -->
<!-- 			    </li> -->
<!-- 			  </ul> -->
			
			</div>
		</center>
	</fieldset>
	</div><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
	<div id="footer"><div class="ft_lt"><p>© Hexaware Technologies. All rights reserved.</p></div></div></div>
</body>
</html>