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
.diCenter
{
border:2px solid;
border-radius:50px;
padding: 20px;
background-color: #F1ECDD;
padding-bottom: 30px;
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
<title>Reminder Scheduling</title>
</head>
<body>
	
	         <br>
			<center><font size="5">
				<b>Reminders Scheduling</b>
			</font></center>
			<br>
			<table align="center">
				<tr>
				<!-- View Reminder Block -->
				<td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="UserAdmin.jsp">&lt;&lt;Back</a>
				<form id="frm" action="WebEventHandlerServlet" method="post"> 
				<div align="center" class="diCenter">
				<p>
				<strong>View Current Reminder Timings</strong>
				</p>
				<p align="left">
				Patient ID:&nbsp;&nbsp;&nbsp;
				<input type="text" id="upid" name="upid" size=20 />&nbsp;&nbsp;
				<input type="submit"
						value="View" />
				</p>
				<p align="left">
				Daily Reminder Start Time (hour 0-23)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<span> <%
 	            if (request.getAttribute ("hour") != null)
 	            {
                 %> <%=request.getAttribute ("hour")%>
	             <%
		        }
	            %> </span>
				</p>
				<p align="left">
				Daily Reminder Start Time (min. 0-59)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<span> <%
 	            if (request.getAttribute ("min") != null)
 	            {
                 %> <%=request.getAttribute ("min")%>
	             <%
		        }
	            %> </span>
				</p>
				<p align="left">
				Daily Reminder Start Time (sec. 0-59)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<span> <%
 	            if (request.getAttribute ("sec") != null)
 	            {
                 %> <%=request.getAttribute ("sec")%>
	             <%
		        }
	            %> </span>
				</p>
				<p align="left">
				Number of reminder to sent in a day&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<span> <%
 	            if (request.getAttribute ("ilteration") != null)
 	            {
                 %> <%=request.getAttribute ("ilteration")%>
	             <%
		        }
	            %> </span>
				</p>
				<p align="left">
				Gap between reminders (in hours)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<span> <%
 	            if (request.getAttribute ("gap") != null)
 	            {
                 %> <%=request.getAttribute ("gap")%>
	             <%
		        }
	            %> </span>
				</p>
				<p>
			    <span style ="color:red"> <%
                 if(request.getAttribute("viewuserexception")!=null){out.println(request.getAttribute("viewuserexception"));}
 	              %>
                </span>  
			    </p>
				<p><b>
				Default Reminder Settings are:- <br>
				hour: 8 , Min: 0 , Sec: 0, Ilteration: 3, Gap: 2
				</b></p>
				</div>
				<input type="hidden" id="reqType" name="reqType" value = "ViewReminder">
				</form>
				</td>
				<!-- Update Reminder Block -->
				<td>
				<br>
				<form id="frm" action="WebEventHandlerServlet" method="post">
				<div align="center">
				<p>
				<strong>Update Reminder Timings</strong>
				</p>
				<p align="left">
				Patient ID:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="text" id="upid" name="upid" size=20 />
				</p>
				<p>
				Daily Reminder Start Time (hour 0-23)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="text" id="rhour" name="rhour" size=2 />
				</p>
				<p>
				Daily Reminder Start Time (min. 0-59)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="text" id="rmin" name="rmin" size=2 />
				</p>
				<p>
				Daily Reminder Start Time (sec. 0-59)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="text" id="rsec" name="rsec" size=2 />
				</p>
				<p>
				Number of reminder to sent in a day&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="text" id="rnum" name="rnum" size=2 />
				</p>
				<p>
				Gap between reminders (in hours)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="text" id="rgap" name="rgap" size=2 /></p>
				<p>
			    <span style ="color:red"> <%
                 if(request.getAttribute("updateuserexception")!=null){out.println(request.getAttribute("updateuserexception"));}
 	              %>
                </span>  
			    </p>
				<p>
				<input type="submit"
						value="Update" /></p>
				</div>
				<input type="hidden" id="reqType" name="reqType" value = "UpdateReminder">
		        </form>
				</td></tr>
			</table>
			
			
			<table align="center">
			<tr><td>
			<form id="frm" action="WebEventHandlerServlet" method="post"> 
			<div>
			<p>
			<b> Stop Reminders for patient Id: </b>
			</p>
			<p align="center">
			<input type="text" id="upid" name="upid" size=20 />
			</p>
			<input type="hidden" id="reqType" name="reqType" value = "StopReminder">
			<span style ="color:red"> <%
                 if(request.getAttribute("stopreminderexception")!=null){out.println(request.getAttribute("stopreminderexception"));}
 	              %>
                </span> 
             <p align="center">
				<input type="submit"
						value="Stop Reminders" />
             </p>   
			</div>
			</form>
			</td></tr>
			</table>
</body>
<br>
</html>
