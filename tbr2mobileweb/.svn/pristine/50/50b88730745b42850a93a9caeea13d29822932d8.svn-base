<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
.divMain
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
padding-top: 0px;
background-color: #F1ECDD;
border-color: #601407;
}
.diCenter h3 
{
background:#f9e3a0;
color:black;
padding:10px;
}
td
{
text-align:left;
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
<script type='text/JavaScript' src='scw.js'></script>
<title>Screening Summary</title>
</head>
<body>
	
			<Center><font size=5>
				<b>Screening Status</b>
			</font></Center>
			<table align="center">
				<tr>
				
				<!-- Main Form! -->
				<td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="UserAdmin.jsp">&lt;&lt;Back</a> <br>
				<div align="center" class="divMain">
				<form id="frm" action="WebEventHandlerServlet" method="post">
				<p>
				From(Date):
 					<input id="date1" name="date1" onclick='scwShow(this,event);' readonly="readonly" value=""/>
                    <input onclick="scwShow(scwID('date1'),event);" style="cursor:pointer;width: 16px;height:16px;background-image: url('images/calendar_icon.png');"/>
				</p>
				<p>
				To(Date):&nbsp;&nbsp;&nbsp;&nbsp;
 					<input id="date2" name="date2" onclick='scwShow(this,event);' readonly="readonly" value=""/>
                    <input onclick="scwShow(scwID('date2'),event);" style="cursor:pointer;width: 16px;height:16px;background-image: url('images/calendar_icon.png');"/>
				</p>
				<p>
				Location Type (Optional):
						<select id="locationType" name="locationType">
						<option value="1">Select filter</option>
                        <option value="2">All PolyClinics</option>
                        <option value="3">All Diabetes Centers</option>
                        <option value="4">Prison Centers</option>
			            </select>
				</p>
				<p>
				<strong>For OverAll Summary of Screening Program,<br> leave Both Dates Empty.</strong>
				</p>
				<input type="hidden" id="reqType" name="reqType" value = "ScreeningStatus">
				<p>
				<span style ="color:red"> <%
                if(request.getAttribute("Invalid")!=null){out.println(request.getAttribute("Invalid"));}
                %>
               </span>
				</p>						
				<p><input type="submit"
						value="View Summary" />
				</p>				
				</form>
				</div>		
				</td></tr>	
					
			</table>
			<br>
			<center>
                <span style="font-size: 3;"><%
                 if(request.getAttribute("ResultTypeString")!=null){
                	 out.println("<strong>"+request.getAttribute("ResultTypeString")+"</strong><br>");
                     out.println("System Date: "+ request.getAttribute("attributedate")+"<br>");
                     out.println("System Time: "+ request.getAttribute("attributetime")+"<br>");
                     out.println("System Status: The system is running correctly"+"<br>");
                 } 
                 %>
            </span></center>
            <br>
			<table align="center">
			<tr>
			
			<!-- OverAll Summary -->
			<td>
			<div align="center" class="diCenter">
			  
			    <h3>
				OverAll Locations Summary
				</h3>
			    <p>
			  
			    <strong>Total Screened: </strong> ${ScreeningTotal}<br/>
                <strong>Total Suspects: </strong>  ${SuspectTotal}<br/>
                <strong>Total NonSuspects: </strong>  ${NonSuspectTotal}<br/>
                <strong>Number of Centers Active: </strong>  20 <br/>
                <strong>Wrong Suspect Entries: </strong>  ${WrongEntries}<br/>
                <strong>Correct Suspect Entries: </strong>  ${totalCorrectSuspectEntries}<br/>
                <strong>Working Days: </strong>  ${workingDays}<br/>
                <strong>Screening/day Overall: </strong>  ${screensperdayperoverall}<br/>
                <strong>Screening/day per Center: </strong>  ${screensperdaypercenter}<br/>
                <strong>Percent of suspects among total screened: </strong>  ${percentage}%<br/>
			    
			    </p>
			    <br>
			</div>
			</td>
			
			<!-- Location Type Summary -->
			<td>
			
			<center><p>
		    <Strong> <span style="font-size: 3;"><%
                 if(request.getAttribute("LocationName")!=null){
                     out.println("Location Type: "+ request.getAttribute("LocationName"));
                 } 
                 %>
            </span> </Strong>
			</p> </center>
			
			<div align="center" class="diCenter">
			
			<h3>
			Location Type Summary
		    </h3>
		    
			<p>
			
			<strong>Total Screened: </strong> ${allpoly}<br/>
            <strong>Total Suspects: </strong>  ${allpolySuspects}<br/>
            <strong>Total NonSuspects: </strong>  ${allpolyNonSuspects}<br/>
                                
            </p>
			
			</div>
			</td>
			
			<td>
			<div align="center" class="diCenter">
			<h3>
			Screens by Locations
		    </h3>
		    
		   <table>
            
            <tr>
            <td>
            <strong>CentralPolyclinic: </strong> ${CENTRALPOLYCLINIC}
            </td>
            <td>
            <strong>PolyDushanbe1: </strong>  ${POLYDUSHANBE1}
            </td>
            </tr>
            
            <tr>
            <td>
            <strong>PolyDushanbe2: </strong>  ${POLYDUSHANBE2}
            </td>
            <td>
            <strong>PolyDushanbe3: </strong> ${POLYDUSHANBE3}
            </td>
            </tr>
            
            <tr>
            <td>
            <strong>PolyDushanbe4: </strong>  ${POLYDUSHANBE4}
            </td>
            <td>
            <strong>PolyDushanbe5: </strong> ${POLYDUSHANBE5}
            </td>
            </tr>
            
            <tr>
            <td>
            <strong>PolyDushanbe6: </strong>  ${POLYDUSHANBE6}
            </td>
            <td>
            <strong>PolyDushanbe7: </strong> ${POLYDUSHANBE7}
            </td>
            </tr>
            
            <tr>
            <td>
            <strong>PolyDushanbe8: </strong>  ${POLYDUSHANBE8}
            </td>
            <td>
            <strong>PolyDushanbe9: </strong> ${POLYDUSHANBE9}
            </td>
            </tr>
            
            <tr>
            <td>
            <strong>PolyDushanbe10: </strong>  ${POLYDUSHANBE10}
            </td>
            <td>
            <strong>PolyDushanbe11: </strong> ${POLYDUSHANBE11}
            </td>
            </tr>
            
            <tr>
            <td>
            <strong>PolyDushanbe12: </strong>  ${POLYDUSHANBE12}
            </td>
            <td>
            <strong>PolyDushanbe14: </strong> ${POLYDUSHANBE14}
            </td>
            </tr>
            
            <tr>
            <td>
            <strong>PolyTursunzade1: </strong>  ${POLYTURSUNZADE1}
            </td>
            <td>
            <strong>PolyTursunzade2: </strong> ${POLYTURSUNZADE2} 
            </td>
            </tr>
            
            <tr>
            <td>
            <strong>DiabeteDushanbe1: </strong>  ${DIABETESDUSHANBE1} 
            </td>
            <td>
            <strong>DiabeteDushanbe2: </strong> ${DIABETESDUSHANBE2} 
            </td>
            </tr>
            
            <tr>
            <td>
            <strong>PrisionSystem: </strong>   ${PRISONSYSTEM} 
            </td>
            <td>
            <strong>Rudaki: </strong> ${RUDAKI} 
            </td>
            </tr>
            
            </table>
                              
			</div>
			</td>
			
			</tr>
			</table>
		
	
</body>
<br>
</html>