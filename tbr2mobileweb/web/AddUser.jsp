<%-- Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	response.setHeader ("Cache-Control", "no-cache"); //HTTP 1.1 
	response.setHeader ("Pragma", "no-cache"); //HTTP 1.0 
	response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New User</title>
</head>
<body link="#601407" vlink="#601407" alink=brown>
	<form id="frm" action="WebEventHandlerServlet" method="post">
			<center><font size="5">
				<b>Add New User</b>
			</font></center>
			<table align=center>
				<tr><td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="UserAdmin.jsp">&lt;&lt;Back</a> <br> 
				<div align="center" class="divCenter">
				<p align="left">
				EnterPID&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="text" id="apid" name="apid" size=20 />
				</p>
				<p align="left">
				First Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="text" id="afname" name="afname" size=20 />
				</p>
				<p align="left">
				Last Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="text" id="alname" name="alname" size=20 />
				</p>
				<p align="left">
				Location of User&nbsp;&nbsp; 
				<select id="acurrentLocation" name="acurrentLocation">
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
				 Role of User&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 <select id="arole" name="arole">
							<option>LabTech</option>
							<option>ADMIN</option>
							<option>Doctor</option>
					</select>
			   </p>
			   <p>
			   <span style="color: red"> <%
 	           if (request.getAttribute ("adduserexception") != null)
 	           {
                 %> <%=request.getAttribute ("adduserexception")%>
	              <%
		       }
	           %> </span>
			   </p>
			   <p>
			    <input type="hidden" id="reqType" name="reqType" value = "AddUser">
			   </p>
               <p> <input type="submit"
						value="Submit" />
			 </p>
			 </div>
			</td></tr>
			</table>
	</form>
</body>
<br>
</html>
