<%-- Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. --%>
<%@page import="javax.xml.crypto.AlgorithmMethod"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<script type="text/javascript">

<%
String userName = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
    if(cookie.getName().equals("user")) userName = cookie.getValue();
}
}
if(userName == null) response.sendRedirect("UserManagement.jsp");
%>

 //on load function
 function load(){
    var mon;
    // if monitoring form of patient is filled! -
    // Following code will not execute on forst load but whenever this form loads back from edit servlet! (months != null)
  <% if(request.getAttribute("months") != null) {
	  String monthsValue = (String) request.getAttribute("months");
	  String monthValueInString[] = monthsValue.split(" ");
      // Set months in month dropdown  
	   for(int i = 0; i<monthValueInString.length; i++){
		   %>
		   mon = <%= monthValueInString[i]%>
		   addOption(mon,mon);
		   <%
	   }
	%>   
	  document.getElementById('Month_list').selectedIndex = 0;
	  var editMonth = <%=request.getAttribute ("monthcurr")%>
	  // If form is loaded after updating a monitoring
	  // The edited form is loaded 
	  if (!(editMonth == null || editMonth == "")){
	     
         var Select1 = document.getElementById("Month_list");
         setSelectedValue(Select1, editMonth);
         document.getElementById("mon").innerHTML = editMonth;
  		 document.getElementById("formValue").innerHTML = "<%= request.getAttribute("hwmdcurr") %>"; 
         document.getElementById("baselinesmearmd").value = "<%= request.getAttribute("baselinesmearmdcurr") %>";
         var smearresult = "<%= request.getAttribute("smearresultcurr") %>"
         var Select2 = document.getElementById("smearresult");
         setSelectedValue(Select2, smearresult);
         var patientfeel = "<%= request.getAttribute("patientfeelcurr") %>" 
         var Select3 = document.getElementById("patientfeel");
         setSelectedValue(Select3, patientfeel);
         var expsideeffect = "<%= request.getAttribute("expsideeffectcurr") %>" 
         var Select4 = document.getElementById("expsideeffect");
         setSelectedValue(Select4, expsideeffect);
         if(expsideeffect == 1){
           var onesideeffect = "<%= request.getAttribute("oneSideEffect") %>"
           if(onesideeffect == "HEADACHE")
             document.getElementById("option1").checked = true;
           var twosideeffect = "<%= request.getAttribute("twoSideEffect") %>"  
           if(twosideeffect == "VOMITING")
             document.getElementById("option2").checked = true;
           var threesideeffect = "<%= request.getAttribute("threeSideEffect") %>"
           if(threesideeffect == "ABDOMINAL PAIN")
             document.getElementById("option3").checked = true;
           var foursideeffect = "<%= request.getAttribute("fourSideEffect") %>"  
           if(foursideeffect == "ARTHRALGIA")
             document.getElementById("option4").checked = true;
           var fivesideeffect = "<%= request.getAttribute("fiveSideEffect") %>"  
           if(fivesideeffect == "REDUCION IN VISION")
             document.getElementById("option5").checked = true;
           var sixsideeffect = "<%= request.getAttribute("sixSideEffect") %>"  
           if(sixsideeffect == "OTHERS"){
             document.getElementById("option6").checked = true;
             var other = "<%= request.getAttribute("othersideeffectcurr") %>"
             if(!(other == null || other == ""))
             document.getElementById("othersideeffect").value = other;
             }
           else document.getElementById("othersideeffect").disabled = true; 
           var patientconsult = "<%= request.getAttribute("patientconsultcurr") %>" 
           var Select5 = document.getElementById("patientconsult");
           setSelectedValue(Select5, patientconsult);
           if(patientconsult == 1){
              var onefacility = "<%= request.getAttribute("oneFacility") %>"
              if(onefacility == "HEALTH CENTER")
                document.getElementById("facility1").checked = true;
              var twofacility = "<%= request.getAttribute("twoFacility") %>"
              if(twofacility == "TB CONTROL CENTER")
                document.getElementById("facility2").checked = true;
              var threefacility = "<%= request.getAttribute("threeFacility") %>"
              if(threefacility == "PUBLIC HOSPITAL")
                document.getElementById("facility3").checked = true;
              var fourfacility = "<%= request.getAttribute("fourFacility") %>"
              if(fourfacility == "PRIVATE HOSPITAL")
                document.getElementById("facility4").checked = true;      
           }
           else {
                  document.getElementById("facility1").disabled = true;
                  document.getElementById("facility2").disabled = true;
                  document.getElementById("facility3").disabled = true;
                  document.getElementById("facility4").disabled = true; 
                }       
         }
         else{
             document.getElementById("option1").disabled = true;
             document.getElementById("option2").disabled = true;
             document.getElementById("option3").disabled = true;
             document.getElementById("option4").disabled = true;
             document.getElementById("option5").disabled = true;
             document.getElementById("option6").disabled = true;
             document.getElementById("othersideeffect").disabled = true;
             document.getElementById("patientconsult").disabled = true;
             document.getElementById("facility1").disabled = true;
             document.getElementById("facility2").disabled = true;
             document.getElementById("facility3").disabled = true;
             document.getElementById("facility4").disabled = true;
         }
       var missmedication = "<%= request.getAttribute("missmedicationcurr") %>" 
       var Select6 = document.getElementById("missmedication");
       setSelectedValue(Select6, missmedication);
       if(missmedication == 1){
           var durationmissmedication = "<%= request.getAttribute("durationmissmedicationcurr") %>" 
           var Select6 = document.getElementById("durationmissmedication");
           setSelectedValue(Select6, durationmissmedication);
       }
       else document.getElementById("durationmissmedication").disabled = true;
       var medicinewrapper = "<%= request.getAttribute("medicinewrappercurr") %>" 
       var Select7 = document.getElementById("medicinewrapper");
       setSelectedValue(Select7, medicinewrapper); 
       if(medicinewrapper == 2){
           var doseconsumption = "<%= request.getAttribute("doseconsumptioncurr")%>" 
           var Select8 = document.getElementById("doseconsumption");
           setSelectedValue(Select8, doseconsumption);
        }
       else document.getElementById("doseconsumption").disabled = true;     
	  }
	  else // upload last monitoring form if not returned after saving a monitoring form.    
	    fillMonitoringDetails();
 <% } %>
 }
 
 // Selects option with value = valueToSet for dropdown element SelectObj
 function setSelectedValue(selectObj, valueToSet) {
    for (var i = 0; i < selectObj.options.length; i++) {
        if (selectObj.options[i].value== valueToSet) {
            selectObj.options[i].selected = true;
            return;
        }
    }
}
 
 // Disable and enable otherSideEffectTextBox
 function otherSideEffect(){
  var x = document.getElementById("option6").checked;
  if(x){
    document.getElementById('othersideeffect').disabled = false;
  }
  else{
    document.getElementById("othersideeffect").value = "";
    document.getElementById('othersideeffect').disabled = true;
    }
 }
 
 // onChange of the dropdowns
 function change(val){
  if(val == 1){ //expsideeffect dropdown
	   var value = document.getElementById("expsideeffect").value;
	   if (value == 1){ //"YES"
	      checkboxchk("2");
	      document.getElementById('patientconsult').disabled = false;
	      document.getElementById('facility1').disabled = false;
	      document.getElementById('facility2').disabled = false;
	      document.getElementById('facility3').disabled = false;
	      document.getElementById('facility4').disabled = false;
	      }
	   else{
	      checkboxchk("1");
	      document.getElementById('patientconsult').disabled = true;
	      document.getElementById('facility1').disabled = true;
	      document.getElementById('facility2').disabled = true;
	      document.getElementById('facility3').disabled = true;
	      document.getElementById('facility4').disabled = true; 
	      } 
	 } 
   else if(val == 2){ //patientconsult dropdwon
       var value = document.getElementById("patientconsult").value;
       if (value == 1){
          document.getElementById('facility1').disabled = false;
	      document.getElementById('facility2').disabled = false;
	      document.getElementById('facility3').disabled = false;
	      document.getElementById('facility4').disabled = false; 
       } 
      else{
          document.getElementById('facility1').disabled = true;
	      document.getElementById('facility2').disabled = true;
	      document.getElementById('facility3').disabled = true;
	      document.getElementById('facility4').disabled = true; 
      }   
   }
   else if(val == 3){ // missmedication dropdown
       var value = document.getElementById("missmedication").value;
       if(value == 1)
          document.getElementById('durationmissmedication').disabled = false; 
       else  
          document.getElementById('durationmissmedication').disabled = true;   
   }
   else if(val == 4){ // medicinewrapper dropdown
      var value = document.getElementById("medicinewrapper").value;
       if(value == 1)
          document.getElementById('doseconsumption').disabled = true; 
       else  
          document.getElementById('doseconsumption').disabled = false;
   }	      
 }

 // fill Monitoring detail form for selected month in Month_list dopdown
 function fillMonitoringDetails(){
  document.getElementById("option1").checked = false;
  document.getElementById("option2").checked = false;
  document.getElementById("option3").checked = false;
  document.getElementById("option4").checked = false;
  document.getElementById("option5").checked = false;
  document.getElementById("option6").checked = false;
  document.getElementById("facility1").checked = false;
  document.getElementById("facility2").checked = false;
  document.getElementById("facility3").checked = false;
  document.getElementById("facility4").checked = false;
  document.getElementById('msg1').innerHTML= '';
  document.getElementById('msg2').innerHTML= '';
  document.getElementById('msg3').innerHTML= '';
  document.getElementById('msg4').innerHTML= '';
  document.getElementById("othersideeffect").value = "";
  document.getElementById('othersideeffect').disabled = true;
  var month = document.getElementById("Month_list").value;
  <%
  if(request.getAttribute("months") != null){
	 String monthsValue = (String) request.getAttribute("months");
	 String monthValueInString[] = monthsValue.split(" ");
	 for(String s: monthValueInString){%>
	     var m = <%= s %>
  		 if(m == month){ //Add s(month selected) to the all the key to get particula month data
  		   document.getElementById("mon").innerHTML = "<%= s %>";
  		   document.getElementById("formValue").innerHTML = "<%= request.getAttribute("hwmd"+s) %>";
  		   document.getElementById("formSubmit").value = "<%= request.getAttribute("hwmd"+s) %>";
  		   var baselineSmearResult = "<%= request.getAttribute("baselinesmearmd"+s) %>";
  		   if(baselineSmearResult == "NEGATIVE")
  		      document.getElementById("baselinesmearmd").value = "Отр";
  		   else if(baselineSmearResult == "1-9 AFB")
  		           document.getElementById("baselinesmearmd").value = "1-9 КУБ";
  		   else if(baselineSmearResult == "1" || baselineSmearResult == "1+")
  		           document.getElementById("baselinesmearmd").value = "1+";
  		   else if(baselineSmearResult == "2" || baselineSmearResult == "2+")
  		           document.getElementById("baselinesmearmd").value = "2+";
  		   else if(baselineSmearResult == "3" || baselineSmearResult == "3+")
  		           document.getElementById("baselinesmearmd").value = "3+";
  		   else if(baselineSmearResult == "NONE")
  		           document.getElementById("baselinesmearmd").value = "NONE";
  		                 
  		   document.getElementById("basesmear").value = document.getElementById("baselinesmearmd").value;                
  		   var smearResult = "<%= request.getAttribute("smearresult"+s)%>" 
  		  if(smearResult == "NEGATIVE")
  		    document.getElementById('smearresult').selectedIndex = 1;
  		  else if(smearResult == "1-9 AFB")
  		    document.getElementById('smearresult').selectedIndex = 2;
  		  else if(smearResult == "1+" || smearResult == "1")
  		    document.getElementById('smearresult').selectedIndex = 3;
  		  else if(smearResult == "2+" || smearResult == "2")
  		    document.getElementById('smearresult').selectedIndex = 4;  
  		  else if(smearResult == "3+" || smearResult == "3")
  		    document.getElementById('smearresult').selectedIndex = 5;
  		  else if(smearResult == "NONE")
  		    document.getElementById('smearresult').selectedIndex = 0;  
  		    
  		  var patientFeel = "<%= request.getAttribute("patientfeel"+s)%>"
  		  if(smearResult == "BETTER THAN BEFORE")
  		    document.getElementById('patientfeel').selectedIndex = 0;
  		  else if(smearResult == "WORSE THAN BEFORE")
  		    document.getElementById('patientfeel').selectedIndex = 1;
  		  else if(smearResult == "SAME AS BEFORE")
  		    document.getElementById('patientfeel').selectedIndex = 2;
  		  else if(smearResult == "NOT SURE")
  		    document.getElementById('patientfeel').selectedIndex = 3; 
  		  var expSideEffect = "<%= request.getAttribute("expsideeffect"+s)%>"
  		  if(expSideEffect == "YES"){
  		      document.getElementById('expsideeffect').selectedIndex = 0;
  		      checkboxchk("2");
  		      var sideEffect = "<%= request.getAttribute("sideeffect"+s)%>"
  		      CheckBoxInitiate(1,sideEffect); 
  		      var patientconsult = "<%= request.getAttribute("patientconsult"+s)%>"
  		      if(patientconsult == "YES"){
  		          document.getElementById('patientconsult').selectedIndex = 0;
  		          var healthfacility = "<%= request.getAttribute("healthfacility"+s)%>"
  		          document.getElementById('facility1').disabled = false;
				  document.getElementById('facility2').disabled = false;
				  document.getElementById('facility3').disabled = false;
				  document.getElementById('facility4').disabled = false;				  
  		          CheckBoxInitiate(2,healthfacility);
  		         }
  		      else { 
  		         document.getElementById('patientconsult').selectedIndex = 1;
  		         document.getElementById('facility1').disabled = true;
			     document.getElementById('facility2').disabled = true;
			     document.getElementById('facility3').disabled = true;
			     document.getElementById('facility4').disabled = true;
  		         }  
  		    }
  		  else if(expSideEffect == "NO"){
  		      document.getElementById('expsideeffect').selectedIndex = 1;
  		      checkboxchk("1"); 
  		      document.getElementById('othersideeffect').disabled = true;
  		      document.getElementById('patientconsult').disabled = true;
  		      document.getElementById('facility1').disabled = true;
			  document.getElementById('facility2').disabled = true;
			  document.getElementById('facility3').disabled = true;
			  document.getElementById('facility4').disabled = true;
  		    }
  		  var missMedication = "<%= request.getAttribute("missmedication"+s)%>"
  		  if(missMedication == "YES"){
  		     document.getElementById('durationmissmedication').disabled = false;
  		     document.getElementById('missmedication').selectedIndex = 0;
  		     var durationmissmedication = "<%= request.getAttribute("durationmissmedication"+s)%>"
  		     if(durationmissmedication == "ONCE")
  		         document.getElementById('durationmissmedication').selectedIndex = 0;
  		     else if(durationmissmedication == "TWICE")
  		         document.getElementById('durationmissmedication').selectedIndex = 1;
  		     else if(durationmissmedication == "THRICE")
  		         document.getElementById('durationmissmedication').selectedIndex = 2;  
  		     else if(durationmissmedication == "FOR A WEEK")
  		         document.getElementById('durationmissmedication').selectedIndex = 3;  
  		     else if(durationmissmedication == "MORE THAN A WEEK")
  		         document.getElementById('durationmissmedication').selectedIndex = 4;  
  		     else if(durationmissmedication == "NOT SURE")
  		         document.getElementById('durationmissmedication').selectedIndex = 5;                      
  		  }
  		  else { 
  		    document.getElementById('durationmissmedication').disabled = true; 
  		    document.getElementById('missmedication').selectedIndex = 1; 
  		    }
  		  var medicinewrapper = "<%= request.getAttribute("medicinewrapper"+s)%>"
  		  if(medicinewrapper == "YES"){
  		     document.getElementById('doseconsumption').disabled = true;
  		     document.getElementById('medicinewrapper').selectedIndex = 0;
  		  }
  		  else{
  		    document.getElementById('doseconsumption').disabled = false; 
  		    document.getElementById('medicinewrapper').selectedIndex = 1;
  		    var doseconsumption = "<%= request.getAttribute("doseconsumption"+s)%>"
  		     if(doseconsumption == "1")
  		        document.getElementById('doseconsumption').selectedIndex = 0;
  		     if(doseconsumption == "2")
  		        document.getElementById('doseconsumption').selectedIndex = 1;
  		     if(doseconsumption == "3")
  		        document.getElementById('doseconsumption').selectedIndex = 2;
  		     if(doseconsumption == "4")
  		       document.getElementById('doseconsumption').selectedIndex = 3;
  		     if(doseconsumption == "5")
  		       document.getElementById('doseconsumption').selectedIndex = 4;
  		     if(doseconsumption == ">5")
  		       document.getElementById('doseconsumption').selectedIndex = 5;
  		     if(doseconsumption == ">10")
  		      document.getElementById('doseconsumption').selectedIndex = 6;
  		     if(doseconsumption == ">15") 
  		      document.getElementById('doseconsumption').selectedIndex = 7;
  		  } 
  	    }
  <% } 	  
   }
  %>
 } 
 
 // Set Values to CheckBoxes
 function CheckBoxInitiate(val,string){
 
  if(val == 1){
	   document.getElementById("option1").checked = false;
	   document.getElementById("option2").checked = false;
	   document.getElementById("option3").checked = false;
	   document.getElementById("option4").checked = false;
	   document.getElementById("option5").checked = false;
	   document.getElementById("option6").checked = false;
	   
	   var sideEffectArray = string.split(",");
	   for (var i = 0; i < sideEffectArray.length; i++) {
	         if(sideEffectArray[i]=="HEADACHE"){
		        document.getElementById("option1").checked = true;
		     }else if(sideEffectArray[i]=="VOMITING"){
		        document.getElementById("option2").checked = true;
		     }else if(sideEffectArray[i]=="ABDOMINAL PAIN"){
		        document.getElementById("option3").checked = true;
		     }else if(sideEffectArray[i]=="ARTHRALGIA"){
		        document.getElementById("option4").checked = true;
		     }else if(sideEffectArray[i]=="REDUCTION IN VISION"){
		        document.getElementById("option5").checked = true;
		     }else{
		      document.getElementById("option6").checked = true;
		      document.getElementById("othersideeffect").value = sideEffectArray[i];
		      document.getElementById('othersideeffect').disabled = false;
		     }
	    } 
    }
   if(val == 2){
       document.getElementById("facility1").checked = false;
	   document.getElementById("facility2").checked = false;
	   document.getElementById("facility3").checked = false;
	   document.getElementById("facility4").checked = false;
	   var facilityArray = string.split(",");
	   for (var i = 0; i < facilityArray.length; i++) {
	         if(facilityArray[i]=="HEALTH CENTER"){
		        document.getElementById("facility1").checked = true;
		     }else if(facilityArray[i]=="TB CONTROL CENTER"){
		        document.getElementById("facility2").checked = true;
		     }else if(facilityArray[i]=="PUBLIC HOSPITAL"){
		        document.getElementById("facility3").checked = true;
		     }else if(facilityArray[i]=="PRIVATE HOSPITAL"){
		        document.getElementById("facility4").checked = true;
	         }
       }
       
    }   
    
 }
 
 
 // Diabable and enable the checkboxes
 function checkboxchk(chk){
  
  if(chk == "1"){
    document.getElementById('option1').disabled = true;
    document.getElementById('option2').disabled = true;
    document.getElementById('option3').disabled = true;
    document.getElementById('option4').disabled = true;
    document.getElementById('option5').disabled = true;
    document.getElementById('option6').disabled = true;
    document.getElementById('patientconsult').disabled = true;
    document.getElementById('facility1').disabled = true;
    document.getElementById('facility2').disabled = true;
    document.getElementById('facility3').disabled = true;
    document.getElementById('facility4').disabled = true;
    }
    else{
    document.getElementById('option1').disabled = false;
    document.getElementById('option2').disabled = false;
    document.getElementById('option3').disabled = false;
    document.getElementById('option4').disabled = false;
    document.getElementById('option5').disabled = false;
    document.getElementById('option6').disabled = false;
    document.getElementById('patientconsult').disabled = false;
    document.getElementById('facility1').disabled = false;
    document.getElementById('facility2').disabled = false;
    document.getElementById('facility3').disabled = false;
    document.getElementById('facility4').disabled = false;
   }
 }

// Add new option to the dropdown (use for them month dropdown)
 function addOption(text,value)
 {
  var select = document.getElementById("Month_list");
  var optn = document.createElement("OPTION");
  optn.text = text;
  optn.value = value;
  select.add(optn, 0);
  }
 
 // enable and disable other relationship textbox
  function copy() {
    var value = document.getElementById("relationshipOption").value;
    if(value != 1){
     var elem = document.getElementById("other");
     elem.value = "";
     document.getElementById('other').disabled = true;
     }
    else
     document.getElementById('other').disabled = false;
    }
  
  // onChange regimen function
  function changeRegimen(){
   var value = document.getElementById("regimenbd").value;
   if(value == "RHZES")
     document.getElementById('streptomycin').disabled = false;
   else{
     document.getElementById('streptomycin').disabled = true;
     document.getElementById('streptomycin').selectedIndex = 0;
     } 
       
  } 



</script>

<style>
.divCenter
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
padding: 55px;
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
<!-- <meta http-equiv="Content-Type" content="text/html; charset=windows-1251">  -->
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit Form</title>
</head>

<body onload="load()">
 <center>
		<font size="5">
			<b> Edit Form </b>
		</font>
 </center>
 
 <table align="center">
	<tr><td>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="UserAdmin.jsp">&lt;&lt;Back</a> <br>
	<div align="center" class="divCenter">
		<form id="frm" action="WebEventHandlerServlet" method="post">
		<p>
		Patient ID:
		<input type="text" id="upid" name="upid" size=20 />
		</p>
        <p>
		<span style="color: red"> <%
 	    if (request.getAttribute ("errorattribute") != null)
 	    {
        %> <%=request.getAttribute ("errorattribute")%>
	    <%
	    }
	    %> </span>
		</p>
		<p>
		  <input type="hidden" id="reqType" name="reqType" value = "ViewOrForms">
         </p>
		<p><input type="submit"
						value="View Forms" />
				</p>
		</form>
	</div>
	</td></tr>
 </table>

 <br>
 
 <% if (request.getAttribute ("upid") != null) {%>
  <center>
		<font size="4">
			 Forms of Patient Id: <b> <%= request.getAttribute("upid") %> </b>
		</font>
 </center>
 <% } %>
    
 <table align="center">
   <tr>
    
    <td>
       <% if (request.getAttribute ("patreg") != null){%>
             <div align="left" class="divCenter">
    
             <h3>Реги�?траци�? пациента</h3>
             <form id="frm2" action="editServlet" method="post">
             
             <input type="hidden" name="frm" id="frm" value = "regform">
             <input type="hidden" id="upid" name="upid" value = "<%= request.getAttribute("upid") %>">
             <input type="hidden" id="reqType" name="reqType" value = "EditOrForms"> 
             
             Form Submitted by:
             <span> <%
 	           if (request.getAttribute ("hw") != null)
 	            {
                 %> <%=request.getAttribute ("hw")%>
	             <%
		         }
	         %> </span>	
             <br>
		    Им�?:
		    <% 
		    if (request.getAttribute("firstname") != null){
		    %>
		      <input type ="text" id="firstname" name="firstname" value = "<%= request.getAttribute("firstname") %>" />
		    <%} else { %>
		      <input type ="text" name="firstname" value = "" />
		    <%}%>
		    <span style="color: red"> <%
 	         if (request.getAttribute ("fnerr") != null)
 	         {
             %> <%=request.getAttribute ("fnerr")%>
	         <%
	         }
	         %> </span>
		    <br>
		    Фамили�?:
		    <% 
		    if (request.getAttribute("lastname") != null){
		    %>
		     <input type ="text" id="lastname" name="lastname" value = "<%= request.getAttribute("lastname") %>" />
		    <%} else { %>
		      <input type ="text" name="lastname" value = "" />
		    <%}%>
		    <span style="color: red"> <%
 	         if (request.getAttribute ("lnerr") != null)
 	         {
             %> <%=request.getAttribute ("lnerr")%>
	         <%
	         }
	         %> </span>	
		    <br>
		    Пол:
			<select id="genderOption" name="genderOption">
							<option <% if(request.getAttribute("gender") != null) { String value = (String)request.getAttribute("gender"); if(value.equals("M")) { %> selected="selected" <% } } %> value="M" >Муж</option>
							<option <% if(request.getAttribute("gender") != null) { String value = (String)request.getAttribute("gender"); if(value.equals("F")) { %> selected="selected" <% } } %> value="F" >Жен</option>
				    </select>
			<br>	
		    �?дре�?: номер дома:
		    <% 
		    if (request.getAttribute("addhouse") != null){
		    %>
		      <input type ="text" name="addhouse" value = "<%= request.getAttribute("addhouse") %>" />
		    <%} else { %>
		      <input type ="text" name="addhouse" value = "" />
		    <%}%>
		    <span style="color: red"> <%
 	         if (request.getAttribute ("addhouseerr") != null)
 	         {
             %> <%=request.getAttribute ("addhouseerr")%>
	         <%
	         }
	         %> </span>	
		    <br>
		    �?дре�?: улица:
		    <% 
		    if (request.getAttribute("addstreet") != null){
		    %>
		      <input type ="text" name="addstreet" value = "<%= request.getAttribute("addstreet") %>" />
		    <%} else { %>
		      <input type ="text" name="addstreet" value = "" />
		    <%}%>
		    <span style="color: red"> <%
 	         if (request.getAttribute ("addstreeterr") != null)
 	         {
             %> <%=request.getAttribute ("addstreeterr")%>
	         <%
	         }
	         %> </span>		
		    <br>
		    �?дре�?: микрорайон:
		    <% 
		    if (request.getAttribute("adddistrict") != null){
		    %>
		      <input type ="text" name="adddistrict" value = "<%= request.getAttribute("adddistrict") %>" />
		    <%} else { %>
		      <input type ="text" name="adddistrict" value = "" />
		    <%}%>
		    <span style="color: red"> <%
 	         if (request.getAttribute ("adddistricterr") != null)
 	         {
             %> <%=request.getAttribute ("adddistricterr")%>
	         <%
	         }
	         %> </span>		
		    <br>
		    �?дре�?: номер квартиры:
		    <% 
		    if (request.getAttribute("addflat") != null){
		    %>
		      <input type ="text" name="addflat" value = "<%= request.getAttribute("addflat") %>" />
		    <%} else { %>
		      <input type ="text" name="addflat" value = "" />
		    <%}%>
		    <span style="color: red"> <%
 	         if (request.getAttribute ("addflaterr") != null)
 	         {
             %> <%=request.getAttribute ("addflaterr")%>
	         <%
	         }
	         %> </span>		
		    <br>
		    �?омер телефона дл�? СМС:
		    <% 
		    if (request.getAttribute("phone1") != null){
		    %>
		      <input type ="text" name="phone1" value = "<%= request.getAttribute("phone1") %>" />
		    <%} else { %>
		      <input type ="text" name="phone1" value = "" />
		    <%}%>
		    <span style="color: red"> <%
 	         if (request.getAttribute ("phone1err") != null)
 	         {
             %> <%=request.getAttribute ("phone1err")%>
	         <%
	         }
	         %> </span>		
		    <br>
		    �?омер телефона дл�? �?в�?зи:
		    <% 
		    if (request.getAttribute("homephone") != null){
		    %>
		      <input type ="text" name="homephone" value = "<%= request.getAttribute("homephone") %>" />
		    <%} else { %>
		      <input type ="text" name="homephone" value = "" />
		    <%}%>
		    <span style="color: red"> <%
 	         if (request.getAttribute ("homephoneerr") != null)
 	         {
             %> <%=request.getAttribute ("homephoneerr")%>
	         <%
	         }
	         %> </span>		
		    <br>
		    Дата рождени�?:
		    <% 
		    if (request.getAttribute("dob") != null){
		    %>
		      <input type ="text" name="dob" <% String value = (String)request.getAttribute("dob"); String[] res = value.split(" "); %> value = "<%= res[0] %>" />
		    <%} else { %>
		      <input type ="text" name="dob" value = "" />
		    <%}%>
		    (YYYY-MM-DD)
		    <span style="color: red"> <%
 	         if (request.getAttribute ("doberr") != null)
 	         {
             %> <%=request.getAttribute ("doberr")%>
	         <%
	         }
	         %> </span>		
		    <br>
		    Дата начала лечени�?:
		    <% 
		    if (request.getAttribute("dateTreatment") != null){
		    %>
		      <input type ="text" name="dateTreatment" <% String value = (String)request.getAttribute("dateTreatment"); String[] res = value.split(" "); %> value = "<%= res[0] %>" />
		    <%} else { %>
		      <input type ="text" name="dateTreatment" value = "" />
		    <%}%>
		    (YYYY-MM-DD)
		    <span style="color: red"> <%
 	         if (request.getAttribute ("treatdateerr") != null)
 	         {
             %> <%=request.getAttribute ("treatdateerr")%>
	         <%
	         }
	         %> </span>	
		    <br>
		    Кем волонтер �?вл�?ет�?�? дл�? пациента?
			<select id="relationshipOption" name="relationshipOption" onchange="copy();">
			                <option value=1 selected="selected">Другие</option>
							<option <% if(request.getAttribute("realtionshipfamily") != null) { String value = (String)request.getAttribute("realtionshipfamily"); if(value.equals("CHILD")) { %> selected="selected" <% } } %> value="CHILD">Сын/дочь</option>
							<option <% if(request.getAttribute("realtionshipfamily") != null) { String value = (String)request.getAttribute("realtionshipfamily"); if(value.equals("PARENT")) { %> selected="selected" <% } } %> value="PARENT">Мать/отец</option>
				            <option <% if(request.getAttribute("realtionshipfamily") != null) { String value = (String)request.getAttribute("realtionshipfamily"); if(value.equals("SIBLING")) { %> selected="selected" <% } } %> value="SIBLING">Брат/�?е�?тра</option>
				            <option <% if(request.getAttribute("realtionshipfamily") != null) { String value = (String)request.getAttribute("realtionshipfamily"); if(value.equals("SPOUSE")) { %> selected="selected" <% } } %> value="SPOUSE">Супруг/�?упруга</option>
				            <option <% if(request.getAttribute("realtionshipfamily") != null) { String value = (String)request.getAttribute("realtionshipfamily"); if(value.equals("GRANDPARENT")) { %> selected="selected" <% } } %> value="GRANDPARENT">Бабушка/дедушка</option>
				            <option <% if(request.getAttribute("realtionshipfamily") != null) { String value = (String)request.getAttribute("realtionshipfamily"); if(value.equals("GRANDCHILD")) { %> selected="selected" <% } } %> value="GRANDCHILD">Внук/внучка</option>
				            <option <% if(request.getAttribute("realtionshipfamily") != null) { String value = (String)request.getAttribute("realtionshipfamily"); if(value.equals("AUNT/UNCLE")) { %> selected="selected" <% } } %> value="AUNT/UNCLE">Тет�?/д�?д�?</option>
				            <option <% if(request.getAttribute("realtionshipfamily") != null) { String value = (String)request.getAttribute("realtionshipfamily"); if(value.equals("COUSIN")) { %> selected="selected" <% } } %> value="COUSIN">Двоюродна�? �?е�?тра/брат</option>
				    </select>
			<br>
			Другие:
			<input type ="text" name="other" id="other" <% String res = (String)request.getAttribute("realtionshipfamily"); if(!(res.equals("CHILD") || res.equals("PARENT") || res.equals("SIBLING") || res.equals("SPOUSE") || res.equals("GRANDPARENT") || res.equals("GRANDCHILD") || res.equals("AUNT/UNCLE") || res.equals("COUSIN") || res.equals("1"))){ %> value = "<%= res %>" <% } else { %> disabled="disabled" <% } %>/>
			<span style="color: red"> <%
 	         if (request.getAttribute ("othererr") != null)
 	         {
             %> <%=request.getAttribute ("othererr")%>
	         <%
	         }
	         %> </span>
			<br>
			Семейное положение:
			<select id="MartialOption" name="MartialOption">
							<option <% if(request.getAttribute("maritalstatus") != null) { String value = (String)request.getAttribute("maritalstatus"); if(value.equals("SINGLE")) { %> selected="selected" <% } } %> value="SINGLE">�?е женат/не замужем</option>
							<option <% if(request.getAttribute("maritalstatus") != null) { String value = (String)request.getAttribute("maritalstatus"); if(value.equals("MARRIED")) { %> selected="selected" <% } } %> value="MARRIED">Женат/замужем</option>
				            <option <% if(request.getAttribute("maritalstatus") != null) { String value = (String)request.getAttribute("maritalstatus"); if(value.equals("DIVORCED")) { %> selected="selected" <% } } %> value="DIVORCED">Разведен/а</option>
				            <option <% if(request.getAttribute("maritalstatus") != null) { String value = (String)request.getAttribute("maritalstatus"); if(value.equals("WIDOW")) { %> selected="selected" <% } } %> value="WIDOW">Живу раздельно</option>
				            <option <% if(request.getAttribute("maritalstatus") != null) { String value = (String)request.getAttribute("maritalstatus"); if(value.equals("WIDOWER")) { %> selected="selected" <% } } %> value="WIDOWER">Вдовец/Вдова</option>
				            <option <% if(request.getAttribute("maritalstatus") != null) { String value = (String)request.getAttribute("maritalstatus"); if(value.equals("REFUSED")) { %> selected="selected" <% } } %> value="REFUSED">�?ет ответа</option>
				    </select>
			<br>
			Образование:
			<select id="EducationOption" name="EducationOption">
							<option <% if(request.getAttribute("education") != null) { String value = (String)request.getAttribute("education"); if(value.equals("PRE-SCHOOL")) { %> selected="selected" <% } } %> value="PRE-SCHOOL">Дошкольное</option>
							<option <% if(request.getAttribute("education") != null) { String value = (String)request.getAttribute("education"); if(value.equals("PRIMARY SCHOOL")) { %> selected="selected" <% } } %> value="PRIMARY SCHOOL">�?ачальна�? школа</option>
				            <option <% if(request.getAttribute("education") != null) { String value = (String)request.getAttribute("education"); if(value.equals("SECONDARY SCHOOL")) { %> selected="selected" <% } } %> value="SECONDARY SCHOOL">Средн�?�? школа</option>
				            <option <% if(request.getAttribute("education") != null) { String value = (String)request.getAttribute("education"); if(value.equals("POST-SECONDARY SCHOOL")) { %> selected="selected" <% } } %> value="POST-SECONDARY SCHOOL">Среднее �?пециальное</option>
				            <option <% if(request.getAttribute("education") != null) { String value = (String)request.getAttribute("education"); if(value.equals("UNIVERSITY")) { %> selected="selected" <% } } %> value="UNIVERSITY">Вы�?шее</option>
				            <option <% if(request.getAttribute("education") != null) { String value = (String)request.getAttribute("education"); if(value.equals("NONE")) { %> selected="selected" <% } } %> value="NONE">�?ет</option>
				    </select>
			<br>
			Доход на 1 члена �?емьи в ме�?�?ц:
			<select id="FamilyOption" name="FamilyOption">
							<option <% if(request.getAttribute("incomefamily") != null) { String value = (String)request.getAttribute("incomefamily"); if(value.equals("UP TO 100 SOMONI")) { %> selected="selected" <% } } %> value="UP TO 100 SOMONI">До 100 �?омони</option>
							<option <% if(request.getAttribute("incomefamily") != null) { String value = (String)request.getAttribute("incomefamily"); if(value.equals("UP TO 200 SOMONI")) { %> selected="selected" <% } } %> value="UP TO 200 SOMONI">До 200 �?омони</option>
				            <option <% if(request.getAttribute("incomefamily") != null) { String value = (String)request.getAttribute("incomefamily"); if(value.equals("UP TO 300 SOMONI")) { %> selected="selected" <% } } %> value="UP TO 300 SOMONI">До 300 �?омони</option>
				            <option <% if(request.getAttribute("incomefamily") != null) { String value = (String)request.getAttribute("incomefamily"); if(value.equals("UP TO 400 SOMONI")) { %> selected="selected" <% } } %> value="UP TO 400 SOMONI">До 400 �?омони</option>
				            <option <% if(request.getAttribute("incomefamily") != null) { String value = (String)request.getAttribute("incomefamily"); if(value.equals("More than 400 Somoni")) { %> selected="selected" <% } } %> value="More than 400 Somoni">Более 400 �?омони</option>
				            <option <% if(request.getAttribute("incomefamily") != null) { String value = (String)request.getAttribute("incomefamily"); if(value.equals("REFUSED")) { %> selected="selected" <% } } %> value="REFUSED">нет ответа</option>
				    </select>
			<br> 
			<span style="color: red"> <%
 	        if (request.getAttribute ("regmsg") != null)
 	        {
            %> <%=request.getAttribute ("regmsg")%>
	        <%
	        }
	        %> </span>
			<p align="right">
					<input type="submit" value="Update Form" />
		   </p>   
       </form>
             
       </div>
       <%}%>  
    </td>
    
    <td>
       <% if (request.getAttribute ("basdet") != null){%>
              <div align="left" class="diCenter">
    
              <h3>И�?ходные данные</h3>
              <form id="frm3" action="editServlet" method="post">
             
            <input type="hidden" name="frm" id="frm" value = "baseform">  
            <input type="hidden" name="upid" value = "<%= request.getAttribute("upid") %>"> 
            <input type="hidden" id="reqType" name="reqType" value = "EditOrForms">  
              
            Form Submitted by:
		    <span> <%
		 	           if (request.getAttribute ("hwbd") != null)
		 	            {
		                 %> <%=request.getAttribute ("hwbd")%>
			             <%
				         }
			       %> </span>	
		    <br>
		    Ве�? пациента (кг):
		    <% 
		    if (request.getAttribute("weightbd") != null){
		    %>
		      <input type ="text" id="weightbd" name="weightbd" value = "<%= request.getAttribute("weightbd") %>" />    <%} 
		     else { %>
		      <input type ="text" name="weightbd" value = ""/>
		    <%}%>
		    <span style="color: red"> <%
 	         if (request.getAttribute ("weighterr") != null)
 	         {
             %> <%=request.getAttribute ("weighterr")%>
	         <%
	         }
	         %> </span>	
		    <br> 
		    Микро�?копи�? мазка (диагно�?тика):
			<select id="baselinesputum" name="baselinesputum">
			                <option <% if(request.getAttribute("baselinesputumbd") != null) { String value = (String)request.getAttribute("baselinesputumbd"); if(value.equals("NONE")) { %> selected="selected" <% } } %> value="NONE" >�?ет</option>
							<option <% if(request.getAttribute("baselinesputumbd") != null) { String value = (String)request.getAttribute("baselinesputumbd"); if(value.equals("NEGATIVE")) { %> selected="selected" <% } } %> value="NEGATIVE" >Отр</option>
							<option <% if(request.getAttribute("baselinesputumbd") != null) { String value = (String)request.getAttribute("baselinesputumbd"); if(value.equals("1-9 AFB")) { %> selected="selected" <% } } %> value="1-9 AFB" >1-9 КУБ</option>
				            <option <% if(request.getAttribute("baselinesputumbd") != null) { String value = (String)request.getAttribute("baselinesputumbd"); if(value.contains("1")) { %> selected="selected" <% } } %> value="1" >1+</option>
				            <option <% if(request.getAttribute("baselinesputumbd") != null) { String value = (String)request.getAttribute("baselinesputumbd"); if(value.contains("2")) { %> selected="selected" <% } } %> value="2" >2+</option>
				            <option <% if(request.getAttribute("baselinesputumbd") != null) { String value = (String)request.getAttribute("baselinesputumbd"); if(value.contains("3")) { %> selected="selected" <% } } %> value="3" >3+</option>
				    </select>
			<br>
			Рентгенографи�? легких (диагно�?тика):<br>
		    <select id="baselinechest" name="baselinechest">
		                    <option <% if(request.getAttribute("baselinechestbd") != null) { String value = (String)request.getAttribute("baselinechestbd"); if(value.equals("NONE")) { %> selected="selected" <% } } %> value="NONE">�?ет</option>
							<option <% if(request.getAttribute("baselinechestbd") != null) { String value = (String)request.getAttribute("baselinechestbd"); if(value.equals("NORMAL")) { %> selected="selected" <% } } %> value="NORMAL">�?ормальный</option>
							<option <% if(request.getAttribute("baselinechestbd") != null) { String value = (String)request.getAttribute("baselinechestbd"); if(value.equals("SUGGESTIVE OF TB")) { %> selected="selected" <% } } %> value="SUGGESTIVE OF TB">Подозрение на ТБ</option>
				            <option <% if(request.getAttribute("baselinechestbd") != null) { String value = (String)request.getAttribute("baselinechestbd"); if(value.contains("TB PROCESS")) { %> selected="selected" <% } } %> value="TB PROCESS">Большое подозрение на ТБ</option>
				            <option <% if(request.getAttribute("baselinechestbd") != null) { String value = (String)request.getAttribute("baselinechestbd"); if(value.contains("GROSSLY ABNORMAL (TB RELATED)")) { %> selected="selected" <% } } %> value="GROSSLY ABNORMAL (TB RELATED)">Де�?труктивно�?ть (обу�?ловлена ТБ)</option>
				            <option <% if(request.getAttribute("baselinechestbd") != null) { String value = (String)request.getAttribute("baselinechestbd"); if(value.contains("GROSSLY ABNORMAL (UNRELATED TO TB)")) { %> selected="selected" <% } } %> value="GROSSLY ABNORMAL">Де�?труктивно�?ть (обу�?ловлена не ТБ)</option>
				    </select>
			<br>
			Локализаци�? ТБ:
		    <select id="anyotherxray" name="anyotherxray" onchange="otherXrayResult();">
							<option <% if(request.getAttribute("otherxraysitebd") != null) { String value = (String)request.getAttribute("otherxraysitebd"); if(value.equals("LUNGS")) { %> selected="selected" <% } } %> value="LUNGS">Легкие</option>
							<option <% if(request.getAttribute("otherxraysitebd") != null) { String value = (String)request.getAttribute("otherxraysitebd"); if(value.equals("PELVIS")) { %> selected="selected" <% } } %> value="PELVIS">Таз</option>
				            <option <% if(request.getAttribute("otherxraysitebd") != null) { String value = (String)request.getAttribute("otherxraysitebd"); if(value.contains("BONES")) { %> selected="selected" <% } } %> value="BONES">Ко�?ти</option>
				            <option <% if(request.getAttribute("otherxraysitebd") != null) { String value = (String)request.getAttribute("otherxraysitebd"); if(value.contains("OTHER")) { %> selected="selected" <% } } %> value="OTHER">Другие</option>
				    </select>   
			<br>
			Рентгенографи�? органов (диагно�?тика):<br>
		    <select id="xrayresult" name="xrayresult">
		                    <option <% if(request.getAttribute("xrayresultbd") != null) { String value = (String)request.getAttribute("xrayresultbd"); if(value.equals("NONE")) { %> selected="selected" <% } } %> value="NONE">�?ет</option>
							<option <% if(request.getAttribute("xrayresultbd") != null) { String value = (String)request.getAttribute("xrayresultbd"); if(value.equals("NORMAL")) { %> selected="selected" <% } } %> value="NORMAL">�?ормальный</option>
							<option <% if(request.getAttribute("xrayresultbd") != null) { String value = (String)request.getAttribute("xrayresultbd"); if(value.equals("SUGGESTIVE OF TB")) { %> selected="selected" <% } } %> value="SUGGESTIVE OF TB">Подозрение на ТБ</option>
				            <option <% if(request.getAttribute("xrayresultbd") != null) { String value = (String)request.getAttribute("xrayresultbd"); if(value.contains("TB PROCESS")) { %> selected="selected" <% } } %> value="TB PROCESS">Большое подозрение на ТБ</option>
				            <option <% if(request.getAttribute("xrayresultbd") != null) { String value = (String)request.getAttribute("xrayresultbd"); if(value.contains("GROSSLY ABNORMAL (TB RELATED)")) { %> selected="selected" <% } } %> value="GROSSLY ABNORMAL (TB RELATED)">Де�?труктивно�?ть (обу�?ловлена ТБ)</option>
				            <option <% if(request.getAttribute("xrayresultbd") != null) { String value = (String)request.getAttribute("xrayresultbd"); if(value.contains("GROSSLY ABNORMAL (UNRELATED TO TB)")) { %> selected="selected" <% } } %> value="GROSSLY ABNORMAL (UNRELATED TO TB)">Де�?труктивно�?ть (обу�?ловлена не ТБ)</option>
				    </select>	 	    
			<br>
			Результаты GeneXpert:
			<select id="genexpertresult" name="genexpertresult">
			                <option <% if(request.getAttribute("baselinegenexpertbd") != null) { String value = (String)request.getAttribute("baselinegenexpertbd"); if(value.equals("NONE")) { %> selected="selected" <% } } %> value="NONE">�?ет</option>
							<option <% if(request.getAttribute("baselinegenexpertbd") != null) { String value = (String)request.getAttribute("baselinegenexpertbd"); if(value.equals("MTB +")) { %> selected="selected" <% } } %> value="MTB +">МБТ +</option>
							<option <% if(request.getAttribute("baselinegenexpertbd") != null) { String value = (String)request.getAttribute("baselinegenexpertbd"); if(value.equals("MTB -")) { %> selected="selected" <% } } %> value="MTB -">МБТ -</option>
				    </select>
			<br>
			Чув�?твительно�?ть к Rif:
			<select id="genexpertdrugresult" name="genexpertdrugresult">
			                <option <% if(request.getAttribute("drugsensitivity") != null) { String value = (String)request.getAttribute("drugsensitivity"); if(value.equals("NONE")) { %> selected="selected" <% } } %> value="NONE">�?ет</option>
							<option <% if(request.getAttribute("drugsensitivity") != null) { String value = (String)request.getAttribute("drugsensitivity"); if(value.equals("MTB Rif +")) { %> selected="selected" <% } } %> value="MTB Rif +">МБТ Rif +</option>
							<option <% if(request.getAttribute("drugsensitivity") != null) { String value = (String)request.getAttribute("drugsensitivity"); if(value.equals("MTB Rif -")) { %> selected="selected" <% } } %> value="MTB Rif -">МБТ Rif -</option>
				    </select>
			<br>
			Тип пациента:
			<select id="patienttype" name="patienttype">
							<option <% if(request.getAttribute("typepatientbd") != null) { String value = (String)request.getAttribute("typepatientbd"); if(value.equals("NEW")) { %> selected="selected" <% } } %> value="NEW">�?овый �?лучай</option>
							<option <% if(request.getAttribute("typepatientbd") != null) { String value = (String)request.getAttribute("typepatientbd"); if(value.equals("RELAPSE")) { %> selected="selected" <% } } %> value="RELAPSE">Рецидив</option>
				            <option <% if(request.getAttribute("typepatientbd") != null) { String value = (String)request.getAttribute("typepatientbd"); if(value.contains("TRANSFER IN")) { %> selected="selected" <% } } %> value="TRANSFER IN">Переведен из</option>
				            <option <% if(request.getAttribute("typepatientbd") != null) { String value = (String)request.getAttribute("typepatientbd"); if(value.contains("TREATMENT AFTER DEFAULT")) { %> selected="selected" <% } } %> value="TREATMENT AFTER DEFAULT">По�?ле отрыва</option>
				            <option <% if(request.getAttribute("typepatientbd") != null) { String value = (String)request.getAttribute("typepatientbd"); if(value.contains("TREATMENT AFTER FAILURE")) { %> selected="selected" <% } } %> value="TREATMENT AFTER FAILURE">По�?ле неудачи лечени�?</option>
				            <option <% if(request.getAttribute("typepatientbd") != null) { String value = (String)request.getAttribute("typepatientbd"); if(value.contains("OTHER")) { %> selected="selected" <% } } %> value="OTHER">Другие</option>
		            </select>
			<br>
			Категори�? лечени�? пациента:
			<select id="catpatientbd" name="catpatientbd">
							<option <% if(request.getAttribute("catpatientbd") != null) { String value = (String)request.getAttribute("catpatientbd"); if(value.equals("CATEGORY 1")) { %> selected="selected" <% } } %> value="CATEGORY 1">Категори�? 1</option>
							<option <% if(request.getAttribute("catpatientbd") != null) { String value = (String)request.getAttribute("catpatientbd"); if(value.equals("CATEGORY 2")) { %> selected="selected" <% } } %> value="CATEGORY 2">Категори�? 2</option>
				            <option <% if(request.getAttribute("catpatientbd") != null) { String value = (String)request.getAttribute("catpatientbd"); if(value.contains("CATEGORY 3")) { %> selected="selected" <% } } %> value="CATEGORY 3">Категори�? 3</option>
		            </select>
			<br>
			Режим лечени�?:
			<select id="regimenbd" name="regimenbd" onchange="changeRegimen();">
							<option <% if(request.getAttribute("regimenbd") != null) { String value = (String)request.getAttribute("regimenbd"); if(value.equals("RHZE")) { %> selected="selected" <% } } %> value="RHZE">RHZE</option>
							<option <% if(request.getAttribute("regimenbd") != null) { String value = (String)request.getAttribute("regimenbd"); if(value.equals("RHZES")) { %> selected="selected" <% } } %> value="RHZES">RHZES</option>
		            </select>
			<br>
			Доза назначенного препарата:
			<select id="fixeddose" name="fixeddose">
							<option <% if(request.getAttribute("fixeddosebd") != null) { String value = (String)request.getAttribute("fixeddosebd"); if(value.equals("1")) { %> selected="selected" <% } } %> value="1">1</option>
							<option <% if(request.getAttribute("fixeddosebd") != null) { String value = (String)request.getAttribute("fixeddosebd"); if(value.equals("1.5")) { %> selected="selected" <% } } %> value="1.5">1.5</option>
							<option <% if(request.getAttribute("fixeddosebd") != null) { String value = (String)request.getAttribute("fixeddosebd"); if(value.equals("2")) { %> selected="selected" <% } } %> value="2">2</option>
							<option <% if(request.getAttribute("fixeddosebd") != null) { String value = (String)request.getAttribute("fixeddosebd"); if(value.equals("3")) { %> selected="selected" <% } } %> value="3">3</option>
							<option <% if(request.getAttribute("fixeddosebd") != null) { String value = (String)request.getAttribute("fixeddosebd"); if(value.equals("4")) { %> selected="selected" <% } } %> value="4">4</option>
							<option <% if(request.getAttribute("fixeddosebd") != null) { String value = (String)request.getAttribute("fixeddosebd"); if(value.equals("5")) { %> selected="selected" <% } } %> value="5">5</option>					
		            </select>
			<br>
			Доза �?трептомицина:
			<select id="streptomycin" name="streptomycin" <% if( String.valueOf(request.getAttribute("regimenbd")).equals("RHZE")){%> disabled="disabled" <% } %>>
							<option value="---">-------</option>
							<option <% if(request.getAttribute("streptomycinbd") != null) { String value = (String)request.getAttribute("streptomycinbd"); if(value.equals("250")) { %> selected="selected" <% } } %> value="250">250</option>
							<option <% if(request.getAttribute("streptomycinbd") != null) { String value = (String)request.getAttribute("streptomycinbd"); if(value.equals("500")) { %> selected="selected" <% } } %> value="500">500</option>
							<option <% if(request.getAttribute("streptomycinbd") != null) { String value = (String)request.getAttribute("streptomycinbd"); if(value.equals("750")) { %> selected="selected" <% } } %> value="750">750</option>
		   </select>
		   <span style="color: red"> <%
 	         if (request.getAttribute ("streerrerr") != null)
 	         {
             %> <%=request.getAttribute ("streerrerr")%>
	         <%
	         }
	         %> </span>	 
			<br>
              <span style="color: red"> <%
 	        if (request.getAttribute ("basemsg") != null)
 	        {
            %> <%=request.getAttribute ("basemsg")%>
	        <%
	        }
	        %> </span>
			<p align="right">
					<input type="submit" value="Update Form" />
		   </p> 
              </form>
              
              </div>
       <% } %>
    </td>
    
    </tr>
 </table>
 
 <table align="center">
    <tr><td>
    
      <% if (request.getAttribute ("mondet") != null){%> 
    
       <div align="left" class="divCenter">
       <h3>Мониторинг</h3>
       <form id="frm4" action="editServlet" method="post">
       
          <input type="hidden" name="frm" id="frm" value = "monform">  
          <input type="hidden" name="upid" id="upid" value = "<%= request.getAttribute("upid") %>">  
          <input type="hidden" name="formSubmit" id="formSubmit" value = ""> 
          <input type="hidden" name="basesmear" id="basesmear" value = "">
          <input type="hidden" id="reqType" name="reqType" value = "EditOrForms"> 
          
          Ме�?�?ц лечени�?:
          <SELECT NAME="Month_list" id="Month_list" onchange="fillMonitoringDetails();">
          </SELECT>
          <br>
       
          Form Submitted by:
          <span id="formValue"> </span>	
          <br>
		  
		  Микро�?копи�? мазка (диагно�?тика)
		  <input type ="text" name="baselinesmearmd" id="baselinesmearmd" value=""  disabled="disabled"/>
		  <br>
		  
		  Микро�?копи�? мазка (<span id="mon"></span>&nbsp;ме�?.):
          <SELECT NAME="smearresult" id="smearresult">
           <option value="�?ет">Отр</option>
           <option value="NEGATIVE">Отр</option>
           <option value="1-9 AFB">1-9 КУБ</option>
           <option value="1+">1+</option>
           <option value="2+">2+</option>
           <option value="3+">3+</option>
          </SELECT>
          <br>
          
          Самочув�?твие:
          <SELECT NAME="patientfeel" id="patientfeel">
           <option value="BETTER THAN BEFORE">Улучшение</option>
           <option value="WORSE THAN BEFORE">Ухудшение</option>
           <option value="SAME AS BEFORE">Без изменений</option>
           <option value="NOT SURE">�?е знаю</option>
          </SELECT>
          <br>
          
          Были побочные дей�?тви�??
          <SELECT NAME="expsideeffect" id="expsideeffect" onChange="change(1)">
           <option value="1">Да</option>
           <option value="2">�?ет</option>
          </SELECT>
          <br>
         
          Какие побочные дей�?тви�??
          <table>
           <tr>
           <td>
             <input type="checkbox" id="option1" name="option1" value="HEADACHE"> Головна�? боль
           </td>
           <td>
             <input type="checkbox" id="option2" name="option2" value="VOMITING"> Рвота
           </td>
           </tr>
           <tr>
           <td>
             <input type="checkbox" id="option3" name="option3" value="ABDOMINAL PAIN"> Боль в животе
           </td>
           <td>
             <input type="checkbox" id="option4" name="option4" value="ARTHRALGIA"> Боль в �?у�?тавах
           </td>
           </tr>
           <tr>
           <td>
             <input type="checkbox" id="option5" name="option5" value="REDUCTION IN VISION"> Ухудшение зрени�?
           </td> 
           <td>
             <input type="checkbox" id="option6" name="option6" value="OTHERS" onclick="otherSideEffect();"> Другие
           </td>
           <td>
             <span id="msg1" style="color: red"> <%
 	         if (request.getAttribute ("sideEffecterr") != null)
 	         {
             %> <%=request.getAttribute ("sideEffecterr")%>
	         <%
	         }
	         %> </span> 
           </td>  
           </tr>
          </table>
          
        
          Другие:
		  <input type ="text" name="othersideeffect" id="othersideeffect" value=""/>
		  <span id="msg2" style="color: red"> <%
 	         if (request.getAttribute ("othersideEffecterr") != null)
 	         {
             %> <%=request.getAttribute ("othersideEffecterr")%>
	         <%
	         }
	         %> </span>
	         <br>
		  
		  Получали кон�?ультацию при побочных дей�?тви�?х?
		  <SELECT NAME="patientconsult" id="patientconsult" onChange="change(2)">
           <option value="1">Да</option>
           <option value="2">�?ет</option>
          </SELECT>
          <br>
          
          Где получали кон�?ультацию при побочных дей�?тви�?х?<br>
          <table>
          <tr>
          <td> <input type="checkbox" id="facility1" name="facility1" value="HEALTH CENTER">Центр здоровь�? </td>
          <td> <input type="checkbox" id="facility2" name="facility2" value="TB CONTROL CENTER">ТБ центр</td>
          <td>
            <span id="msg3" style="color: red"> <%
 	         if (request.getAttribute ("facilityerr") != null)
 	         {
             %> <%=request.getAttribute ("facilityerr")%>
	         <%
	         }
	         %> </span>
           </td>
          </tr>
          <tr>
           <td> <input type="checkbox" id="facility3" name="facility3" value="PUBLIC HOSPITAL"> Го�?.больница</td>
           <td> <input type="checkbox" id="facility4" name="facility4" value="PRIVATE HOSPITAL"> Ча�?тна�? больница</td>
          </tr>
          </table>
         
          Пропу�?кали ли прием препаратов в прошлом ме�?�?це?
          <SELECT NAME="missmedication" id="missmedication" onChange="change(3)">
           <option value="1">Да</option>
           <option value="2">�?ет</option>
          </SELECT>
          <br>
          
          Сколько раз пропу�?кали прием препаратов?
          <SELECT NAME="durationmissmedication" id="durationmissmedication">
           <option value="ONCE">один раз</option>
           <option value="TWICE">два раза</option>
           <option value="THRICE">три раза</option>
           <option value="FOR A WEEK">неделю</option>
           <option value="MORE THAN A WEEK">более недели</option>
           <option value="NOT SURE">не помню</option>
          </SELECT>
          <br>
          
           Упаковка препаратов за прошедший ме�?�?ц пу�?та�??
          <SELECT NAME="medicinewrapper" id="medicinewrapper" onChange="change(4)">
           <option value="1">Да</option>
           <option value="2">�?ет</option>
          </SELECT>
          <br>
          
           Сколько доз не было прин�?то?
           <SELECT NAME="doseconsumption" id="doseconsumption">
           <option value="1">1</option>
           <option value="2">2</option>
           <option value="3">3</option>
           <option value="4">4</option>
           <option value="5">5</option>
           <option value=">5">&gt;5</option>
           <option value=">10">&gt;10</option>
           <option value=">15">&gt;15</option>
          </SELECT>
          <br>
          <span id="msg4" style="color: red"> <%
 	        if (request.getAttribute ("monmsg") != null)
 	        {
            %> <%=request.getAttribute ("monmsg")%>
	        <%
	        }
	        %> </span>
          
           <p align="right">
					<input type="submit" value="Update Form" />
		   </p> 
      
       </form>
       </div>
       
      <% } %>
       
    </td></tr>
 </table>    
   
</body>
</html>
