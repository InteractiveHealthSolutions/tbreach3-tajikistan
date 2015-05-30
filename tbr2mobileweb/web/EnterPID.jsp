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
<script type="text/javascript">
/* function smefunction()
{
	var usr = document.getElementById("hw");
	var pss = document.getElementById("pw");
	document.getElementById("frm").action='loginServlet?usr='+usr+'&=pass'+pss+'&appver='+"130130621"+'&type='+"lg";
	document.getElementById("frm").submit();
	
} */
</script>
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
<body>

	<form id="frm" action="WebEventHandlerServlet" method="post">
				
			<center><font size="5"><b>Lab Tech Login</b></font></center>
			
			<table align="center">
			<tr><td>
			<div align="center" class="divCenter">
			        <p>
			        Enter User ID:
				    <input type="text" id="hw" name="hw" size=20 />
					</p>
					<p>
					Password:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="password" id="pw" name="pw" size=20 />
					</p>
					<span style="color: red" > <%
 	                if (request.getAttribute ("Invalid") != null)
 	                {
 		                out.println (request.getAttribute ("Invalid"));
 	                }
                    %> </span>
                    <p>
                    <input type="hidden" id="reqType" name="reqType" value = "LabTechLogin">
					<input type="submit"
						value="Submit" onclick="smefunction();" />
					</p>					
			</div>
			</td></tr>
			</table>	
	</form>
</body>
<br>
<center><a href="UserManagement.jsp">Admin Panel</a></center>
</html>
