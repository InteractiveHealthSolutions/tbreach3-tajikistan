<%-- Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"
    
 import="java.io.*,java.util.*,org.irdresearch.tbreach2.server.DataTable"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/data_table.css" />
<link rel="stylesheet" href="css/jquery.ui.all.css" />
<link rel="stylesheet" type="text/css" href="css/demos.css" />


<script type="text/javascript" charset="utf-8"> </script>
<script src="js/jquery.js"></script>
<script src="js/jquery.ui.core.js"></script>
<script src="js/jquery.ui.datepicker.js"></script>
<script src="js/jquery.ui.widget.js"></script>
<script type="text/javascript" charset="utf-8" src="js/jquery.dataTables.js"></script>
<script type="text/javascript" charset="utf-8" src="media/js/ZeroClipboard.js"></script>
<script type="text/javascript" charset="utf-8" src="media/js/TableTools.js"></script>

<style type="text/css" title="currentStyle">
			@import "media/css/TableTools.css";
		</style>
		


 <script type='text/javascript'>
    function doSomething(id, sop){
        var scridd = document.getElementById(id).innerHTML;
        var scrid = document.getElementById(sop).value;
       
       // alert(scridd);
        if(!(scrid=="false" || scrid=="true"))
        	{
        	alert("Only allowed to enter true or false!!");
        	}
        
		else {
			 var date = document.getElementById('datepicker').value;
			//alert("Please enter dates");
			//alert(date);
			//assign patient ids to all such patients
			window.location = 'UpdateScreeningServlet?screeningID='+scridd +'&suspect='+scrid+'&date='+date;
			 }

		}
    

    
	</script> 
    <script type="text/javascript">
    $(document).ready(function(){
        $('#datatable').dataTable({
        	
            "iDisplayLength": 20,  
            "sPaginationType": "full_numbers", 
            "aLengthMenu": [[20, 50, 100, -1], [20, 50, 100, "All"]], 
          /*  "aLengthMenu": [[20, 50, 100,]], */
           "sEmptyTable": "No record found",
           "bDeferRender": true,
           "oLanguage": {
               "sSearch": "Filter: "
           },
           "sDom": 'T<"clear">lfrtip',
       	 "oTableTools": {
				/* "sSwfPath": "media/swf/xls.swf" */
				"aButtons": [
               {
                   "sExtends": "xls",
                   "sButtonText": "Excel",
                   "mColumns": [ 0, 1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12 ]
               },
               
           ]
		}
        });
        
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

<%

	String ss = request.getParameter("date");
	String ss1 = request.getParameter("dateSecond");
	String po = "";
	String po2 = "";
	if (ss != null && ss1 != null) {
		po = ss;
		po2 = ss1;
	}
%><!-- action="ScreeningWeb.jsp" -->
<body>
	<form name="dateForm" action="ScreeningWeb.jsp">
		Select Date: <input name="date" type="text" id="datepicker" value="<%=po%>" /> 
		Select DateSecond: <input name="dateSecond" type="text" id="datepicker2" value="<%=po2%>" /> 
			<input type="submit" value="Submit">
	</form>


	<%
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
	%>

</body>
</html>
