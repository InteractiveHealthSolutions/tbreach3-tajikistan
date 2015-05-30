<%-- Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. --%>
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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update User</title>
</head>
<body>
	<form id="frm" action="WebEventHandlerServlet" method="post">
		    <center><font size=5>
				<b>Update User</b>
			</font></center>
			<table align="center">
				<tr><td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="UserAdmin.jsp">&lt;&lt;Back</a> <br>
				<div align="center" class="divCenter">
				<p>
				Enter PID&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="text" id="upid" name="upid" size=20 />
				</p>
				<p>
				Password&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="text" id="upwd" name="upwd" size=20 />
				</p>
				<p align="left">
				Status of User&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<select id="ustatus" name="ustatus">
							<option>ACTIVE</option>
							<option>SUSPEND</option>
					</select>
				</p>
				<p>
				<span style="color: red"> <%
 	            if (request.getAttribute ("updateuserexception") != null)
 	            {
                 %> <%=request.getAttribute ("updateuserexception")%>
	             <%
		         }
	            %> </span>
				</p>
				<input type="hidden" id="reqType" name="reqType" value = "UpdateUser">
				<p>
                <input type="submit" value="Submit" />
                </p>
                </div>
			</td></tr>
			</table>
		
	</form>
</body>
<br>
</html>
