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

a:link {
    text-decoration: none;
    color: brown;
}

a:visited {
    text-decoration: none;
    color: brown;
}

a:hover {
    text-decoration: none;
    color: #601407;
}

a:active {
    text-decoration: none;
    color: #601407;
}

</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-5">
<title>OR Reminders Daily Summary</title>
</head>
<body link="#601407" vlink="#601407" alink=brown>

	<form id="frm" action="WebEventHandlerServlet" method="post">
			<Center><font size="5">
				<b>Change User Location</b>
			</font></Center>
			<table align="center">
				<tr><td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="UserAdmin.jsp">&lt;&lt;Back</a> <br> 
				<div align="center" class="divCenter">
				<p align="left">
				 Enter User ID&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 <input type="text" id="uw" name="uw" size=20 />
				</p>
				
				<p align="left">
				Current Location&nbsp;&nbsp;&nbsp;&nbsp; 
				<select id="currentLocation" name="currentLocation">
							<option>CentralPolyClinic</option>
							<option>PolyDushanbe2</option>
							<option>PolyDushanbe3</option>
							<option>PolyDushanbe4</option>
							<option>PolyDushanbe5</option>
							<option>PolyDushanbe6</option>
							<option>PolyDushanbe7</option>
							<option>PolyDushanbe8</option>
							<option>PolyDushanbe9</option>
							<option>PolyDushanbe10</option>
							<option>PolyDushanbe11</option>
							<option>PolyDushanbe12</option>
							<option>PolyDushanbe13</option>
							<option>PolyDushanbe14</option>
							<option>PolyTursunzade1</option>
							<option>PolyTursunzade2</option>
							<option>DiabetesDushanbe1</option>
							<option>DiabetesDushanbe2</option>
							<option>Prisonsystem</option>
							<option>Rudaki</option>
							<option>PolyDushanbe1</option>
				</select>
				</p>

                <p align="left">
				New Location&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<select id="newLocation" name="newLocation">
							<option>CentralPolyClinic</option>
							<option>PolyDushanbe2</option>
							<option>PolyDushanbe3</option>
							<option>PolyDushanbe4</option>
							<option>PolyDushanbe5</option>
							<option>PolyDushanbe6</option>
							<option>PolyDushanbe7</option>
							<option>PolyDushanbe8</option>
							<option>PolyDushanbe9</option>
							<option>PolyDushanbe10</option>
							<option>PolyDushanbe11</option>
							<option>PolyDushanbe12</option>
							<option>PolyDushanbe13</option>
							<option>PolyDushanbe14</option>
							<option>PolyTursunzade1</option>
							<option>PolyTursunzade2</option>
							<option>DiabetesDushanbe1</option>
							<option>DiabetesDushanbe2</option>
							<option>Prisonsystem</option>
							<option>Rudaki</option>
							<option>PolyDushanbe1</option>
					</select>
				</p>
				<p>
				<span style="margin-left: 2em; color: red">
		          <%
			        if (session.getAttribute ("usersattribute") != null)
			         {
		              %><%=session.getAttribute ("usersattribute")%>
		              <%
			         }
		         %>
	            </span>
				</p>
				<p>
			    <input type="hidden" id="reqType" name="reqType" value = "ChangeLocation">
			   </p>	
				<p>
				<input type="submit"
						value="Submit" />
				</p>
				</div>
			   </td></tr>
			</table>
	</form>
	
</body>
</html>