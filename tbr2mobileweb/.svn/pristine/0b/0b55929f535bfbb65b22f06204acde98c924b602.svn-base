<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
.divMain
{
border:2px solid;
border-radius:50px;
padding: 29px;
background-color: #F1ECDD;
border-color: #601407;
}
.divMain h3 
{
background:#f9e3a0;
color:black;
padding:10px;
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
<title>Reminder Summary</title>
</head>
<body>
	
			<Center><font size=5>
				<b>Reminder Status</b>
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
				Patient ID (Optional):
				<input type="text" id="upid" name="upid" size=20 />
				</p>
				<p>
				<strong>For OverAll Summary of Reminders,<br> leave Both Dates Empty.</strong>
				</p>
				<p>
				<span style ="color:red"> <%
                if(request.getAttribute("Invalid")!=null){out.println(request.getAttribute("Invalid"));}
                %>
               </span>
				</p>
				<input type="hidden" id="reqType" name="reqType" value = "ReminderStatus">						
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
                 if(request.getAttribute("resultString")!=null){
                	 out.println("<strong>"+request.getAttribute("resultString")+"</strong><br>");
                     out.println("System Date/Time: "+ request.getAttribute("dateTime")+"<br>");
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
				Reminders Summary
				</h3>
			    <p>
			  
			    <strong>Total Reminders Sent: </strong> ${allReminders}<br/>
			    <strong>No. of First Reminder Sent out: </strong>  ${firstReminders}<br/>
                <strong>No. of Second Reminder Sent out: </strong>  ${secondReminders}<br/>
                <strong>No. of Third Reminder Sent out: </strong>  ${thirdReminders}<br/>
			    <strong>Average No. of Reminders Sent per day: </strong>  ${remindersPerDay}<br/>
			    <strong>Average No. of First Reminder Sent per day: </strong>  ${firstRemindersPerDay}<br/>
			    <strong>Average No. of Second Reminders Sent per day: </strong>  ${secondRemindersPerDay}<br/>
			    <strong>Average No. of Third Reminders Sent per day: </strong>  ${thirdRemindersPerDay}<br/>
			    <strong>Total Responses:</strong> ${totalResponses} <br/>
                <strong>No. of Response received  after 1st Reminder:</strong> ${firstResponses} <br/>
                <strong>No. of Response received after 2nd Reminder:</strong> ${secondResponses} <br/>
                <strong>No. of Response received after 3rd Reminder:</strong> ${thirdResponses} <br/>
                <strong>Average No. of Response received per day: </strong>  ${remindersPerDay}<br/>
			    <strong>Average No. of Response received after first reminder: </strong>  ${firstResponsePerDay}<br/>
			    <strong>Average No. of Second received after second reminder: </strong>  ${secondResponsePerDay}<br/>
			    <strong>Average No. of Third received after third reminder: </strong>  ${thirdResponsePerDay}<br/><br/>
			    </p>
			</div>
			</td>
			
			<!-- Location Type Summary -->
			<td>
			
			<div align="center" class="divMain">
			
			<h3>
			Patient based Reminder Summary
		    </h3>
		    
			<p>
			<br/>
			<Strong>Patient ID: </Strong> ${pid}<br/><br/>
			<strong>Total No. of Reminder Sent: </strong> ${tpatientrem}<br/><br/>
            <strong>No. of First Reminder Sent: </strong>  ${firstpatientrem}<br/>
            <strong>No. of Second Reminder Sent </strong>  ${secondpatientrem}<br/>
            <strong>No. of Third Reminder Sent </strong>  ${thirdpatientrem}<br/><br/>
            
		    <strong>Total No. of Responses received:</strong> ${tpatientres} <br/><br/>
            <strong>No. of Response received  after 1st Reminder:</strong> ${firstpatientres} <br/>
            <strong>No. of Response received after 2nd Reminder:</strong> ${secondpatientres} <br/>
            <strong>No. of Response received after 3rd Reminder:</strong> ${thirdpatientres} <br/><br/>
                                           
            </p>
			
			</div>
			</td>
			
			</tr>
			</table>
		
	
</body>
<br>
</html>