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
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" href="css/jquery.ui.all.css" />
<link rel="stylesheet" type="text/css" href="css/demos.css" />


<!-- <script type="text/javascript" charset="utf-8"> </script> -->
<script src="js/jquery.js"></script>
<script src="js/jquery.ui.core.js"></script>
<script src="js/jquery.ui.datepicker.js"></script>
<script src="js/jquery.ui.widget.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Generate Screening CSV</title>
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
				<b>Generate Screening CSV</b>
			</font></center>
			<table align="center">
				<tr><td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<font size=3><a href="UserAdmin.jsp">&lt;&lt;Back</a></font><br>
				<div align="center" class="diCenter">
				<p align="left">
				 <font size="2">From(Date):</font>
				 <input name="date" type="text" id="datepicker" /> 
		        </p>
		        <p align="left"> 
		         <font size="2">To(Date):</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		         <input name="dateSecond" type="text" id="datepicker2" /> 
			    </p>
			    <p> 
			     <input type="submit" value="Generate">
				</p>
				</div>
				</td></tr>
		 </table>		
		<input type="hidden" id="reqType" name="reqType" value = "ScreeningCsvReport">
	</form>


<%-- 	<%
		String toDate = request.getParameter("date");
		String tosDate = request.getParameter("dateSecond");
		DataTable d=null;
		//out.println("alert('" + toDate + "');");
		//getting the screening data filtered by Date here in html format
		if (toDate != null && toDate !="" && tosDate=="") {
			d= new DataTable();
			out.println(d.getTableData(toDate));
			//System.out.println(toDate);
		}
		
		else if (toDate != null && tosDate!=null) {
			d= new DataTable();
			out.println(d.getTableData(toDate, tosDate));
			//System.out.println(toDate);
		} 
	%> --%>


</body>
</html>