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
