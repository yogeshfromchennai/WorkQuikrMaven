<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%
response.setHeader("Cache-Control","no-store"); //HTTP 1.1
response.setHeader("Pragma","no-cache"); //HTTP 1.0
response.setDateHeader("Expires", 0); 
if( request.getCookies() != null )
{
    for (Cookie cookie: request.getCookies()) 
    {  
        cookie.setMaxAge(0);
		//cookie.setPath("/");
        cookie.setComment("EXPIRING COOKIE at " + System.currentTimeMillis());
        response.addCookie(cookie);        
    }
}
session.invalidate();
response.sendRedirect("/WorkQuikr-console/Pages/Home");

%>
</head>
<body>

</body>
</html>