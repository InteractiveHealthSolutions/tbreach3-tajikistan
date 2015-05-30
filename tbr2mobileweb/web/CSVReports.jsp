<%-- Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. --%>
<%@ page language="java" contentType="text/html; charset=UTF-8;"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
.diCenter
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
<link rel="stylesheet" type="text/css" href="css/data_table.css" />
<link rel="stylesheet" href="css/jquery.ui.all.css" />
<link rel="stylesheet" type="text/css" href="css/demos.css" />


<!-- <script type="text/javascript" charset="utf-8"> </script> -->
<script src="js/jquery.js"></script>
<script src="js/jquery.ui.core.js"></script>
<script src="js/jquery.ui.datepicker.js"></script>
<script src="js/jquery.ui.widget.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Generate Operational Research Forms CSV</title>
 <script type="text/javascript">
    $(document).ready(function(){
        $(function() {
        	$( "#datepicker" ).datepicker({ dateFormat: "yy-mm-dd" });
        	});
        
        $(function() {
        	$( "#datepicker2" ).datepicker({ dateFormat: "yy-mm-dd" });
        	});
        
        <%String mes = request.getParameter("ErrorMes");
			if (mes != null) {
				out.println("alert('" + mes + "');");
			}%>
    });
    </script>
</head>



<body>

	<form method="post" action="WebEventHandlerServlet">
	
	        <center><font size=5>
				<b>Generate OR CSVs</b>
			</font></center>
			<table align="center">
				<tr><td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<font size=3><a href="UserAdmin.jsp">&lt;&lt;Back</a></font><br>
				<div align="center" class="diCenter">
				<p align="left">
		        <font size=2>Select Location Type:</font> 
                <select name="form" id="formType">
                <option value="1">Select Report Type</option>
                <option value="2">Patient Registration Forms</option>
                <option value="3">Baseline Forms</option>
                <option value="4">Monitoring Forms</option>  
                <option value="5">Surveys Forms</option>  
                </select>
                </p>
                <p align="left">
		        <font size=2>From(Date): </font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		        <input name="date" type="text" id="datepicker" /> 
		        </p>
		        <p align="left">
		        <font size=2>To(Date): </font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		        <input name="dateSecond" type="text" id="datepicker2" /> 
			    </p>
			    <p>
			    <span style ="color:red"> <%
                 if(request.getAttribute("Invalid")!=null){out.println(request.getAttribute("Invalid"));}
 	              %>
                </span>  
			    </p>
			    <p>
				 
    	      <input type="hidden" id="reqType" name="reqType" value = "CsvReportsOr">
    	     
				</p>
			    <p>
      			<input type="submit" value="Generate">
			    </p>
			   </div>
               </td></tr>
           </table>
	</form>

<br>

</body>
</html>
