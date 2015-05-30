<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
div
{
border:2px solid;
border-radius:50px;
padding: 20px;
background-color: #F1ECDD;
border-color: #601407;
}

input[type = "submit"]:hover { 
    color: #601407;
}

</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Login</title>
</head>
<body>

<%
String userName = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
    if(cookie.getName().equals("user")) userName = cookie.getValue();
}
}
if(!(userName == null)) response.sendRedirect("UserAdmin.jsp");
%>

	<form id="frm" action="WebEventHandlerServlet" method="post">
		
			<center>
			<font size="5">	<b>Admin Login</b></font>
			</center>
			<table align="center">
				<tr><td>
				<div align="center" class="divCenter">
				     <p>
				        Enter ID:&nbsp;&nbsp;&nbsp; 
				        <input type="text" id="admin" name="admin" size=20 />
				     </p>
				     <p>
					    Password:&nbsp;&nbsp;
					    <input type="password" id="adminpw" name="adminpw" size=20 />
					</p>
					<span style="color: red"> <%
 	                if (request.getAttribute ("Invalid") != null)
 	                {
 		             out.println (request.getAttribute ("Invalid"));
 	                }
                    %> </span>
                    <p>
					<input type="submit" value="Submit" />
				    </p>
				    <input type="hidden" id="reqType" name="reqType" value = "AdminLogin">
				</div>		
			   </td></tr>
			</table>
		
	</form>
</body>

</html>