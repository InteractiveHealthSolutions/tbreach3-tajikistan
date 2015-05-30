<%-- Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isErrorPage="true"
	import="java.io.*, java.util.*, org.irdresearch.tbreach2.shared.model.*"%>


<%
  // Get the exception stored in the session.
  exception = (Throwable) session.getAttribute("jspException");

  if (exception == null) {
    response.sendRedirect("login.jsp");
    return;
  }
  // Remove the error from the session.  
  session.removeAttribute("jspException");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>Oops! An Unexpected Error or Exception has Occurred!</h3>
	<h3>Exception:</h3>
	<!-- Display the exception name. -->
	<%=exception%><br>

	<br>

	<!-- Display the stack trace for debugging. -->
	<h3>Stack Trace:</h3>
	<%
      StackTraceElement[] ste = exception.getStackTrace();

      for (int i = 0; i < ste.length -1; i++) {
        out.println(ste[i] + " at<br>");
      }

      out.println(ste[ste.length - 1] + "<br>");
    %><br>
</body>
</html>
