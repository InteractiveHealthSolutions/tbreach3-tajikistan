/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package org.irdresearch.tbreach2.server;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.ZipOutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.irdresearch.tbreach2.mobileevent.CSVUtil;
import org.irdresearch.tbreach2.mobileevent.DateUtils;
import org.irdresearch.tbreach2.shared.model.BaselineDetails;
import org.irdresearch.tbreach2.shared.model.LabMapping;
import org.irdresearch.tbreach2.shared.model.LabMappingId;
import org.irdresearch.tbreach2.shared.model.Location;
import org.irdresearch.tbreach2.shared.model.MonitoringResults;
import org.irdresearch.tbreach2.shared.model.PatientDetails;
import org.irdresearch.tbreach2.shared.model.PatientReminder;
import org.irdresearch.tbreach2.shared.model.Screening;
import org.irdresearch.tbreach2.shared.model.Users;

/**
 * Servlet implementation class AddUserServlet
 */
public class WebEventHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServerServiceImpl ssl;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WebEventHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String reqType = request.getParameter("reqType");
		
		if(reqType.equals("AddDocContactNumber")){
			doAddDocContactNumber(request,response);
		}else if (reqType.equals("AddUser")){
			doAddUser(request,response);
		}else if (reqType.equals("ChangeLocation")){
			doChangeLocation(request,response);
		}else if (reqType.equals("UpdatePatientContactNo")){
			doChangePatientMobileNo(request,response);
		}else if (reqType.equals("CsvReportsOr")){
			doCSVReportsForOR(request,response);
		}else if (reqType.equals("ViewOrForms")){
			doViewOrForms(request,response);
		}else if (reqType.equals("LabTechLogin")){
			doLabTechLogin(request,response);
		}else if (reqType.equals("ViewReminder")){
			doViewReminder(request,response);
		}else if (reqType.equals("UpdateReminder")){
			doUpdateReminder(request,response);
		}else if(reqType.equals("ReminderStatus")){
			doReminderStatus(request,response);
		}else if(reqType.equals("ScreeningCsvReport")){
			doScreeningCsvReport(request,response);
		}else if(reqType.equals("AdminLogin")){
			doAdminLogin(request,response);
		}else if(reqType.equals("UpdateUser")){
			doUpdateUser(request,response);
		}else if(reqType.equals("ScreeningStatus")){
			doScreeningStatus(request,response);
		}else if(reqType.equals("OrResponse")){
			doOrResponseStatus(request,response);
		}else if (reqType.equals("StopReminder")){
			doStopReminders(request,response);
		}
		
	}

	private void doStopReminders(HttpServletRequest request,
		HttpServletResponse response) {
		
		 try {
			RequestDispatcher dispatcher;
			ServerServiceImpl ssl = new ServerServiceImpl ();
			String forwardurl = "/ReminderAdmin.jsp";
			String upid = request.getParameter ("upid");
			
			if(upid == null || upid == ""){
				request.setAttribute("stopreminderexception","Patient id can't be empty.");
			}
			else{
				PatientDetails pd = ssl.findPatientByPatientID(upid);
				if(pd == null){
					request.setAttribute("stopreminderexception","Patient id:"+upid+" not found.");
				}
				else{
					if(pd.getActive().equals("0"))
						request.setAttribute("stopreminderexception","Reminders for patient id:"+upid+" already stopped.");
					else{
						Date d = new Date();
						pd.setActive("0");
						pd.setInactiveDate(d);
						ssl.update(pd);
						
						PatientReminder pr = ssl.findPatientReminder (upid);
						ssl.delete(pr);
						
					}
				}
			}
			dispatcher=getServletContext().getRequestDispatcher(forwardurl);
	        dispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return;
		
	}

	private void doOrResponseStatus(HttpServletRequest request, HttpServletResponse response) {
		try{
			
			RequestDispatcher dispatcher;
			ServerServiceImpl ssl = new ServerServiceImpl ();
			String forwardurl = "/DailyResponseStatus.jsp";
			
			String date = request.getParameter ("date1"); 
			String show = request.getParameter ("show");
			String newDate = null;
			
			try
			{
				final String OLD_FORMAT = "dd-MM-yyyy";
				final String NEW_FORMAT = "yyyy-MM-dd";
				
				if(date != ""){
					SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
					Date todayDate = new Date();
					Date date2 = sdf.parse(date);
					sdf.applyPattern(NEW_FORMAT);
					newDate = sdf.format (date2);
					if (date2.after(todayDate)){
						request.setAttribute("Error","Date can't be of future.");
						 
				        dispatcher=getServletContext().getRequestDispatcher(forwardurl);
				        dispatcher.forward(request, response);
				        return;
					}
					else{
						Calendar c = Calendar.getInstance();
						c.setTime(date2);
						int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
						if(dayOfWeek == 1){
							request.setAttribute("Error","No daily medicine reminders on Sunday.");
							 
					        dispatcher=getServletContext().getRequestDispatcher(forwardurl);
					        dispatcher.forward(request, response);
					        return;
							
						}
						
					}
				}
				else{
					request.setAttribute("Error","Date can't be empty.");
					 
			        dispatcher=getServletContext().getRequestDispatcher(forwardurl);
			        dispatcher.forward(request, response);
			        return;
				}
				
				String dateFilter = newDate + "%";
				request.setAttribute("date",date);
				
               try {
					long no = ssl.count("reminder_history","where sent_date like '"+dateFilter+"'");
					if(no == 0)
						request.setAttribute("Error","Reminders were not scheduled. (Server Problem)");
					else{
					long no1 = ssl.count("reminder_history","where sent_date like '"+dateFilter+"' AND reminder_status = 'SENT'");	
					if(no1 == 0)
						request.setAttribute("Error","Reminders were schedule but failed. (Mobile Problem)");
					}
						
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
				if(show.equals("2")){
				   request.setAttribute("show","2");
				   String pids[][] = ssl.getTableData("SELECT * from tbreach2.patientdetails where STARTTIMEFORM <= date('"+newDate+"')  AND (INACTIVEDATE is null or INACTIVEDATE > date('"+newDate+"') ) AND PID NOT IN (SELECT PID FROM tbreach2.patient_response where recieve_date like '"+dateFilter+"');");
				   int len = pids.length;
				   request.setAttribute("length",len);
				   for(int i = 0; i < len; i++){
					request.setAttribute("id"+i,pids[i][0]);
				   }
				}
				else{
				   request.setAttribute("show","1");
				   String pids[][] = ssl.getTableData("SELECT * FROM tbreach2.patient_response where recieve_date like '"+dateFilter+"';");
				   int len = pids.length;
				   request.setAttribute("length",len);
				   for(int i = 0; i < len; i++){
					request.setAttribute("id"+i,pids[i][8]);
				   }
				}
				
			}
			catch (ParseException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			dispatcher=getServletContext().getRequestDispatcher(forwardurl);
	        dispatcher.forward(request, response);
	        return;
			
			
		}catch (ServletException e) {
			// TODO Auto-generated catch block
			  e.printStackTrace();
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			  e.printStackTrace();
		}
	}

	private void doScreeningStatus(HttpServletRequest request, HttpServletResponse response) {
		try{
		RequestDispatcher dispatcher;
		ServerServiceImpl ssl = new ServerServiceImpl ();
		String forwardurl = "/SystemStatus.jsp";
		String totalScreened = null;
		String totalSuspects = null;
		String totalNonSuspect = null;
		Date d = null;
		String completeTimeIn=null;
		String formattedDate = null;
		
		String dateFrom = request.getParameter ("date1");
		String dateTo = request.getParameter ("date2");
		String newDateTo = null;
		String newDateFrom =  null;
		
		try
		{

			final String OLD_FORMAT = "dd-MM-yyyy";
			final String NEW_FORMAT = "yyyy-MM-dd";
			
			if(dateTo != ""){
				SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
				Date date1 = sdf.parse(dateTo);
				sdf.applyPattern(NEW_FORMAT);
				newDateTo = sdf.format(date1);				
			}
			
			if(dateFrom != ""){
				SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
				Date date2 = sdf.parse(dateFrom);
				sdf.applyPattern(NEW_FORMAT);
				newDateFrom = sdf.format (date2);	
			}		
						
		}
		catch (ParseException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String dateFilter = "1=1";
		Integer numberofworkingdayssinceStart = 0;
		try
		{
			numberofworkingdayssinceStart = Integer.valueOf(String.valueOf(ssl.workingDays()));
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(dateTo == "" && dateFrom == ""){
			dateFilter = "1=1";
		}		
		else if(dateTo != "" && dateFrom != ""){
			int dayCheck = 0;
			try
			{
				dayCheck = (int) (long) differenceInTwoDates (newDateFrom, newDateTo);
			}
			catch (ParseException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(dayCheck < 0){
				request.setAttribute("Invalid", "Please fill valid Date");
				dispatcher=getServletContext().getRequestDispatcher(forwardurl);
			    dispatcher.forward(request, response);
			    return;
			}			
			dateFilter = "DateEntered between '"+newDateFrom+"%' and '"+newDateTo+" 23:59:59'";
			try
			{
				numberofworkingdayssinceStart = (int) (long) differenceInTwoDates (newDateFrom, newDateTo);
			}
			catch (ParseException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println (dateFilter);
		}
		else {
				request.setAttribute("Invalid", "Please fill in Both Date");
				dispatcher=getServletContext().getRequestDispatcher(forwardurl);
			    dispatcher.forward(request, response);
			    return;			
		}
		
		String locationType = request.getParameter("locationType");
		String polyLocationType = null;
		String polyLocationSuspects = null;
		String polyLocationNonSuspects = null;
		System.out.println("Printing Drop Down Value in Servlet "+locationType);
		/*String screenspercenter = null;
		String suspectspercenter = null;*/
		String CENTRALPOLYCLINIC= null;
		String POLYDUSHANBE2 = null;
		String POLYDUSHANBE3 = null;
		String POLYDUSHANBE4 = null;
		String POLYDUSHANBE5 = null;
		String POLYDUSHANBE6 = null;
		String POLYDUSHANBE7 = null;
		String POLYDUSHANBE8 = null;
		String POLYDUSHANBE9 = null;
		String POLYDUSHANBE10 = null;
		String POLYDUSHANBE11 = null;
		String POLYDUSHANBE12 = null;
		String POLYDUSHANBE14 = null;
		String POLYTURSUNZADE1 = null;
		String POLYTURSUNZADE2 = null;
		String DIABETESDUSHANBE1 = null;
		String DIABETESDUSHANBE2 = null;
		String PRISONSYSTEM = null;
		String RUDAKI = null;
		String POLYDUSHANBE1 = null;
		String wrongEntries = null;
		int totalCorrectSuspectEntries= 0;
		Integer screensperdayperCenter = 0;
		Integer screensperdayperOverall = 0;
		double percentofsuspectsTotalScreened = 0.0;
		double a=0;
		double b=0;
		String percentage = null;
		try{
		d = new Date();
		DateFormat df = new SimpleDateFormat("dd/MM/yy");
		formattedDate = df.format(new Date()); 
		
		completeTimeIn = d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
		//completeTime =  Integer.toString(completeTimeIn);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		//get Screened Suspect Count
		try {
			totalScreened = String.valueOf(ssl.count("screening", dateFilter));
			totalSuspects = String.valueOf(ssl.count("screening", "Suspect = true and " + dateFilter));
			totalNonSuspect = String.valueOf(ssl.count("screening", "Suspect = false and " + dateFilter));
			wrongEntries = String.valueOf(ssl.count("screening", "cough='NO' and tbhistory='NO' and familyTbhistory='NO' and Suspect=true and " + dateFilter));
			totalCorrectSuspectEntries = Integer.valueOf(totalSuspects)-Integer.valueOf(wrongEntries);
			
			screensperdayperOverall = Integer.valueOf(totalScreened)/numberofworkingdayssinceStart;
			screensperdayperCenter = screensperdayperOverall/20;
			a = (double)(totalCorrectSuspectEntries);
			b = (double)Double.parseDouble(totalScreened);
			percentofsuspectsTotalScreened = (a/b)*100;
			percentage = String.format("%.2f", percentofsuspectsTotalScreened);
			/*screenspercenter = String.valueOf(Integer.parseInt(totalScreened)/20);
			suspectspercenter = String.valueOf(Integer.parseInt(totalSuspects)/20);*/
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		//get by each location
		try
		{
			CENTRALPOLYCLINIC = String.valueOf(ssl.count("screening", "ScreenLocation = 01 and " + dateFilter));
			POLYDUSHANBE2 = String.valueOf(ssl.count("screening", "ScreenLocation = 02 and " + dateFilter));
			POLYDUSHANBE3 = String.valueOf(ssl.count("screening", "ScreenLocation = 03 and " + dateFilter));
			POLYDUSHANBE4 = String.valueOf(ssl.count("screening", "ScreenLocation = 04 and " + dateFilter));
			POLYDUSHANBE5 = String.valueOf(ssl.count("screening", "ScreenLocation = 05 and " + dateFilter));
			POLYDUSHANBE6 = String.valueOf(ssl.count("screening", "ScreenLocation = 06 and " + dateFilter));
			POLYDUSHANBE7 = String.valueOf(ssl.count("screening", "ScreenLocation = 07 and " + dateFilter));
			POLYDUSHANBE8 = String.valueOf(ssl.count("screening", "ScreenLocation = 08 and " + dateFilter));
			POLYDUSHANBE9 = String.valueOf(ssl.count("screening", "ScreenLocation = 09 and " + dateFilter));
			POLYDUSHANBE10 = String.valueOf(ssl.count("screening", "ScreenLocation = 10 and " + dateFilter));
			POLYDUSHANBE11 = String.valueOf(ssl.count("screening", "ScreenLocation = 11 and " + dateFilter));
			POLYDUSHANBE12 = String.valueOf(ssl.count("screening", "ScreenLocation = 12 and " + dateFilter));
			POLYDUSHANBE14 = String.valueOf(ssl.count("screening", "ScreenLocation = 14 and " + dateFilter));
			POLYTURSUNZADE1 = String.valueOf(ssl.count("screening", "ScreenLocation = 15 and " + dateFilter));
			POLYTURSUNZADE2 = String.valueOf(ssl.count("screening", "ScreenLocation = 16 and " + dateFilter));
			DIABETESDUSHANBE1 = String.valueOf(ssl.count("screening", "ScreenLocation = 17 and " + dateFilter));
			DIABETESDUSHANBE2 = String.valueOf(ssl.count("screening", "ScreenLocation = 18 and " + dateFilter));
			PRISONSYSTEM = String.valueOf(ssl.count("screening", "ScreenLocation = 19 and " + dateFilter));
			RUDAKI = String.valueOf(ssl.count("screening", "ScreenLocation = 20 and " + dateFilter));
			POLYDUSHANBE1 = String.valueOf(ssl.count("screening", "ScreenLocation = 21 and " + dateFilter));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//get by Location Type
		try{
			if(!locationType.equalsIgnoreCase("1")){
			if(locationType.equalsIgnoreCase("2"))
					{
				polyLocationType = String.valueOf(ssl.count("screening, location", "screening.ScreenLocation = location.LocationID and location.LocationName like '%POLY%' and " + dateFilter));
				polyLocationSuspects = String.valueOf(ssl.count("screening, location", "screening.ScreenLocation = location.LocationID and " +
						"location.LocationName like '%POLY%' and screening.Suspect=true and " + dateFilter));
				polyLocationNonSuspects = String.valueOf(ssl.count("screening, location", "screening.ScreenLocation = location.LocationID and " +
						"location.LocationName like '%POLY%' and screening.Suspect=false and " + dateFilter));

					}
			
			else if(locationType.equalsIgnoreCase("3"))
			{
		polyLocationType = String.valueOf(ssl.count("screening, location", "screening.ScreenLocation = location.LocationID and location.LocationName like '%DIABET%' and " + dateFilter));
		polyLocationSuspects = String.valueOf(ssl.count("screening, location", "screening.ScreenLocation = location.LocationID and " +
				"location.LocationName like '%DIABET%' and screening.Suspect=true and " + dateFilter));
		polyLocationNonSuspects = String.valueOf(ssl.count("screening, location", "screening.ScreenLocation = location.LocationID and " +
				"location.LocationName like '%DIABET%' and screening.Suspect=false and " + dateFilter));

			}
			
			else if(locationType.equalsIgnoreCase("4"))
			{
		polyLocationType = String.valueOf(ssl.count("screening, location", "screening.ScreenLocation = location.LocationID and location.LocationName like '%PRIS%' and " + dateFilter));
		polyLocationSuspects = String.valueOf(ssl.count("screening, location", "screening.ScreenLocation = location.LocationID and " +
				"location.LocationName like '%PRIS%' and screening.Suspect=true and " + dateFilter));
		polyLocationNonSuspects = String.valueOf(ssl.count("screening, location", "screening.ScreenLocation = location.LocationID and " +
				"location.LocationName like '%PRIS%' and screening.Suspect=false and " + dateFilter));

			}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		String resultTypeString = null;
		if(dateFrom != "" && dateTo != "")
			resultTypeString = "Summary from "+dateFrom+" to "+dateTo;
		else
			resultTypeString = "Reminder Status of overall program";
		
		//setting attributes
        request.setAttribute ("ResultTypeString", resultTypeString);
		request.setAttribute("attributedate", formattedDate);
		request.setAttribute("attributetime", completeTimeIn);
		request.setAttribute("ScreeningTotal", totalScreened);
		request.setAttribute("SuspectTotal", totalSuspects);
		request.setAttribute("NonSuspectTotal", totalNonSuspect);
		request.setAttribute("allpoly", polyLocationType);
		request.setAttribute("allpolySuspects", polyLocationSuspects);
		request.setAttribute("allpolyNonSuspects", polyLocationNonSuspects);
		
		if (locationType.equals ("2"))
			request.setAttribute("LocationName", "All PolyClinics");
		else if (locationType.equals ("3"))
			request.setAttribute("LocationName", "All Diabetes Centers");
		else if (locationType.equals ("4"))
			request.setAttribute("LocationName", "Prison Centers");
		
		//setting each location attribute in request
		request.setAttribute("CENTRALPOLYCLINIC", CENTRALPOLYCLINIC);
		request.setAttribute("POLYDUSHANBE2", POLYDUSHANBE2);
		request.setAttribute("POLYDUSHANBE3", POLYDUSHANBE3);
		request.setAttribute("POLYDUSHANBE4", POLYDUSHANBE4);
		request.setAttribute("POLYDUSHANBE5", POLYDUSHANBE5);
		request.setAttribute("POLYDUSHANBE6", POLYDUSHANBE6);
		request.setAttribute("POLYDUSHANBE7", POLYDUSHANBE7);
		request.setAttribute("POLYDUSHANBE8", POLYDUSHANBE8);
		request.setAttribute("POLYDUSHANBE9", POLYDUSHANBE9);
		request.setAttribute("POLYDUSHANBE10", POLYDUSHANBE10);
		request.setAttribute("POLYDUSHANBE11", POLYDUSHANBE11);
		request.setAttribute("POLYDUSHANBE12", POLYDUSHANBE12);
		request.setAttribute("POLYDUSHANBE14", POLYDUSHANBE14);
		request.setAttribute("POLYTURSUNZADE1", POLYTURSUNZADE1);
		request.setAttribute("POLYTURSUNZADE2", POLYTURSUNZADE2);
		request.setAttribute("DIABETESDUSHANBE1", DIABETESDUSHANBE1);
		request.setAttribute("DIABETESDUSHANBE2", DIABETESDUSHANBE2);
		request.setAttribute("PRISONSYSTEM", PRISONSYSTEM);
		request.setAttribute("RUDAKI", RUDAKI);
		request.setAttribute("POLYDUSHANBE1", POLYDUSHANBE1);
		request.setAttribute("WrongEntries", wrongEntries);
		request.setAttribute("totalCorrectSuspectEntries", totalCorrectSuspectEntries);
		request.setAttribute("workingDays", numberofworkingdayssinceStart);
		request.setAttribute("screensperdayperoverall", screensperdayperOverall);
		request.setAttribute("screensperdaypercenter", screensperdayperCenter);
		request.setAttribute("percentage", percentage);
		
		dispatcher=getServletContext().getRequestDispatcher(forwardurl);
	    dispatcher.forward(request, response);
	    return;
	    
		}catch (ServletException e) {
			// TODO Auto-generated catch block
			  e.printStackTrace();
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			  e.printStackTrace();
		}
		
	}

	private void doUpdateUser(HttpServletRequest request,HttpServletResponse response) {
		try {
		RequestDispatcher dispatcher;
		ssl = new ServerServiceImpl ();
		String upid = request.getParameter("upid");
		String upwd = request.getParameter("upwd");
		String ustatus = request.getParameter("ustatus");
		String adestination = "/UpdateUser.jsp";
		
		if(upid.isEmpty())
		{
			request.setAttribute("updateuserexception", "Please enter PID!");
            dispatcher=getServletContext().getRequestDispatcher(adestination);
            dispatcher.forward(request,response);
			return;
		}
		
		Users users = null;
		try
		{
			 users = ssl.findUser (upid);
			
		}
		catch (Exception e)
		{
			e.printStackTrace ();
			
			
		}
		if(users == null)
		{
			request.setAttribute("updateuserexception", "This PID does not exist!");
            dispatcher=getServletContext().getRequestDispatcher(adestination);
            dispatcher.forward(request,response);
			return;
			
		}
		else
		{
			try
			{
			users.setPassword(upwd);
			users.setStatus(ustatus);
			ssl.updateUser(users);
			request.setAttribute("updateuserexception", "User Updated!");
            dispatcher=getServletContext().getRequestDispatcher(adestination);
            dispatcher.forward(request,response);
			return;
			}
			catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("updateuserexception", "Error Occured. Could not update user. Please try again!");
	            dispatcher=getServletContext().getRequestDispatcher(adestination);
	            dispatcher.forward(request,response);
				return;
			}
		}
		
	}catch (ServletException e) {
		// TODO Auto-generated catch block
		  e.printStackTrace();
    } catch (IOException e) {
		// TODO Auto-generated catch block
		  e.printStackTrace();
	}
		
	}

	private void doAdminLogin(HttpServletRequest request,HttpServletResponse response) {
		try{
		
		String destadmin="/UserAdmin.jsp";
		String destadmin2="/UserManagement.jsp";
        RequestDispatcher dispatcher;
        HttpSession session=request.getSession();
        
        ServerServiceImpl ssl = new ServerServiceImpl();
        String ssn=request.getParameter("admin");
        String pass=request.getParameter("adminpw");
        Users user = null;
        String role = null;
        String status = null;
        String name = null;
		if (ssl.authenticate (ssn, pass))
		{

			try
			{
				user = ssl.findUser (ssn);
				role = user.getRole();
				status = user.getStatus();
				name = user.getFirstName () + " " + user.getLastName ();
			}

				
                catch(Exception e){
                	e.printStackTrace ();
              
                }
			if(role.equalsIgnoreCase("admin")&&!(status.equalsIgnoreCase("suspend")) )
			{

				session.setAttribute("users", user);
                dispatcher=getServletContext().getRequestDispatcher(destadmin);
                
                // Session creation!
                Cookie loginCookie = new Cookie("user",name);
                //setting cookie to expiry in 30 mins
                loginCookie.setMaxAge(30*60);
                response.addCookie(loginCookie);
                
			}
			/*if(status.equalsIgnoreCase("suspend"))
			{
				request.setAttribute("Invalid", "Account Suspended!");
				dispatcher=getServletContext().getRequestDispatcher(destadmin2);
			}*/

			else
			{
				request.setAttribute("Invalid", "Only Admins Allowed or Account Suspended!");
				dispatcher=getServletContext().getRequestDispatcher(destadmin2);
			}
			
			
                dispatcher.forward(request,response);
                return;
		}
		
        else{
        	
        request.setAttribute("Invalid","Invalid Admin Username or Password!");
        														 
        dispatcher=getServletContext().getRequestDispatcher(destadmin2);
        dispatcher.forward(request, response);
        return;
        }
		
		}catch (ServletException e) {
			// TODO Auto-generated catch block
			  e.printStackTrace();
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			  e.printStackTrace();
		}
		
	}

	private void doScreeningCsvReport(HttpServletRequest request, HttpServletResponse response) {
		ssl = new ServerServiceImpl ();
		String date = request.getParameter("date");
		String dateSecond = request.getParameter("dateSecond");
		
        try{

        	String[] s={"ScreeningID","PatientID","screening.FirstName","screening.LastName","CHWID","Age","Gender","Suspect","Cough","CoughDuration","ProductiveCough","Fever","DateEntered","LocationName"};
    		String screeningData[][] = ssl.getTableData("screening, location", s, "DateEntered between '"+date+"%' and '"+dateSecond+" 23:59:59"+"' and " +
    				"screening.ScreenLocation=location.LocationID");
    		
    		
    		
			response.setContentType("application/zip"); 
			
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=ScreeningExport_"+DateUtils.convertToString(new Date()).substring(0,10)+".zip"); 


			ZipOutputStream zip = null;
			try {
				zip = new ZipOutputStream(response.getOutputStream());
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			CSVUtil.makeCsv(zip,screeningData, null);
 			
        }
        catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void doReminderStatus(HttpServletRequest request,HttpServletResponse response) {
		
		try {
		
		RequestDispatcher dispatcher;
		ServerServiceImpl ssl = new ServerServiceImpl ();
		String forwardurl = "/ReminderStatus.jsp";

		String dateSummaryFrom = request.getParameter("date1");
		String dateSummaryTo = request.getParameter("date2");
		String patientId = request.getParameter ("upid");
		String completeTimeIn=null;
		String formattedDate = null;
		String completeDate=null;
		String totalReminders = null;
		String totalFirstReminders = null;
		String totalSecondReminders = null;
		String totalThirdReminders = null;
		String totalResponses = null;
		String firstIlterationResponse = null;
		String secondIlterationResponse = null;
		String thirdIlterationResponse = null;
		String dateFilterReminder = null;
		String dateFilterResponse = null;
		String sqlDateFrom = null;
		String sqlDateTo = null;
		String remindersPerDay = null;
		String firstRemindersPerDay = null;
		String secondRemindersPerDay = null;
		String thirdRemindersPerDay = null;
		String responsePerDay = null;
		String firstResponsePerDay = null;
		String secondResponsePerDay = null;
		String thirdResponsePerDay = null;
		String totalPatientReminders = null;
		String totalFirstPatientReminders = null;
		String totalSecondPatientReminders = null;
		String totalThirdPatientReminders = null;
		String totalPatientResponses = null;
		String firstIlterationPatientResponse = null;
		String secondIlterationPatientResponse = null;
		String thirdIlterationPatientResponse = null;
		
		int dayCheck = Integer.valueOf(String.valueOf(ssl.workingDaysReminder()));
		Date d = new Date();
		DateFormat df = new SimpleDateFormat("dd-MM-yyy");
		formattedDate = df.format(new Date()); 
		completeTimeIn = d.getHours()+":"+d.getMinutes()+":"+d.getSeconds();
		completeDate = formattedDate + " " + completeTimeIn;
		
		if (dateSummaryFrom == "" && dateSummaryTo == ""){
		    dateFilterReminder = "1 = 1";
		    dateFilterResponse = "1 = 1";
		}
		else{
			 if(dateSummaryFrom != "" && dateSummaryTo != "" ){				
				
				final String OLD_FORMAT = "dd-MM-yyy";
				final String NEW_FORMAT = "yyyy-MM-dd"; 
						
				try
				{
					
					SimpleDateFormat sdfFrom = new SimpleDateFormat(OLD_FORMAT);
					SimpleDateFormat sdfTo = new SimpleDateFormat(OLD_FORMAT);
					Date dsTo, dsFrom;
					dsTo = sdfTo.parse(dateSummaryFrom);
					dsFrom = sdfFrom.parse(dateSummaryTo);
					sdfFrom.applyPattern(NEW_FORMAT);
					sdfTo.applyPattern(NEW_FORMAT);
					sqlDateFrom = sdfFrom.format(dsTo);
					sqlDateTo = sdfTo.format(dsFrom);
				}
				catch (ParseException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try
				{
					dayCheck = (int) (long) differenceInTwoDates (sqlDateFrom, sqlDateTo);
				}
				catch (ParseException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(dayCheck < 0){
					request.setAttribute("Invalid", "Please fill valid Date");
					dispatcher=getServletContext().getRequestDispatcher(forwardurl);
				    dispatcher.forward(request, response);
				    return;
				}			
				
				dateFilterReminder = "sent_date between '"+sqlDateFrom+"%' and '"+sqlDateTo+" 23:59:59'";
				dateFilterResponse = "recieve_date between '"+sqlDateFrom+"%' and '"+sqlDateTo+" 23:59:59'";
				System.out.println (sqlDateFrom+ "  "+sqlDateTo);
			}
			 else{
				 request.setAttribute("Invalid", "Please fill in Both Date");
			     dispatcher=getServletContext().getRequestDispatcher(forwardurl);
				 dispatcher.forward(request, response);
				 return;
			 }
				 
		}
		
				try
				{
					totalReminders = String.valueOf(ssl.dailyReminderCount (dateFilterReminder));				
					totalFirstReminders = String.valueOf(ssl.dailyReminderCountPerDate (dateFilterReminder, 1));
					totalSecondReminders = String.valueOf(ssl.dailyReminderCountPerDate (dateFilterReminder, 2));
					totalThirdReminders = String.valueOf(ssl.dailyReminderCountPerDate (dateFilterReminder, 3));
					totalResponses = String.valueOf (ssl.dailyPatientResponseCount (dateFilterResponse));
				}
				catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try
				{
					int count[] = ssl.countResponsePerIlteration (dateFilterResponse);
					System.out.println (count[0]+"-----"+count[1]+"-----"+count[2]);
					firstIlterationResponse = String.valueOf (count[0]);
					secondIlterationResponse = String.valueOf (count[1]);
					thirdIlterationResponse = String.valueOf (count[2]);
					remindersPerDay = String.valueOf(Integer.parseInt(totalReminders)/dayCheck);
					firstRemindersPerDay = String.valueOf(Integer.parseInt(totalFirstReminders)/dayCheck);
					secondRemindersPerDay = String.valueOf(Integer.parseInt(totalSecondReminders)/dayCheck);
					thirdRemindersPerDay = String.valueOf(Integer.parseInt(totalThirdReminders)/dayCheck);
					responsePerDay = String.valueOf(Integer.parseInt(totalResponses)/dayCheck);
					firstResponsePerDay = String.valueOf(Integer.parseInt(firstIlterationResponse)/dayCheck);
					secondResponsePerDay = String.valueOf(Integer.parseInt(secondIlterationResponse)/dayCheck);
					thirdResponsePerDay = String.valueOf(Integer.parseInt(thirdIlterationResponse)/dayCheck);
				}
				catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		if(!(patientId == null || patientId == "")){
			dateFilterReminder = dateFilterReminder + " AND PID = " + patientId;
			dateFilterResponse = dateFilterResponse + " AND PID = " + patientId;
			
			try
			{
				totalPatientReminders = String.valueOf(ssl.dailyReminderCount (dateFilterReminder));
				totalFirstPatientReminders = String.valueOf(ssl.dailyReminderCountPerDate (dateFilterReminder, 1));
				totalSecondPatientReminders = String.valueOf(ssl.dailyReminderCountPerDate (dateFilterReminder, 2));
				totalThirdPatientReminders = String.valueOf(ssl.dailyReminderCountPerDate (dateFilterReminder, 3));
				totalPatientResponses = String.valueOf (ssl.dailyPatientResponseCount (dateFilterResponse));
				int count[] = ssl.countResponsePerIlteration (dateFilterResponse);
				firstIlterationPatientResponse = String.valueOf (count[0]);
				secondIlterationPatientResponse = String.valueOf (count[1]);
				thirdIlterationPatientResponse = String.valueOf (count[2]);
				
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute ("pid", patientId);
			
			request.setAttribute ("tpatientrem", totalPatientReminders);
			request.setAttribute ("firstpatientrem", totalFirstPatientReminders);
			request.setAttribute ("secondpatientrem", totalSecondPatientReminders);
			request.setAttribute ("thirdpatientrem", totalThirdPatientReminders);
			
			request.setAttribute ("tpatientres", totalPatientResponses);
			request.setAttribute ("firstpatientres", firstIlterationPatientResponse);
			request.setAttribute ("secondpatientres", secondIlterationPatientResponse);
			request.setAttribute ("thirdpatientres", thirdIlterationPatientResponse);
			
		}		
		
		String resultString = null;
		if(dateSummaryFrom != "" && dateSummaryTo != "")
		   resultString = "Reminder Status from "+dateSummaryFrom+" to "+dateSummaryTo;
		else
			resultString = "Reminder Status of overall program";
		
		request.setAttribute ("resultString", resultString);
		request.setAttribute ("dateTime", completeDate);
		request.setAttribute("allReminders", totalReminders);
		request.setAttribute("firstReminders", totalFirstReminders);
		request.setAttribute("secondReminders", totalSecondReminders);
		request.setAttribute("thirdReminders", totalThirdReminders);
		request.setAttribute("totalResponses", totalResponses);
		request.setAttribute("firstResponses", firstIlterationResponse);
		request.setAttribute("secondResponses", secondIlterationResponse);
		request.setAttribute("thirdResponses", thirdIlterationResponse);
		request.setAttribute("remindersPerDay", remindersPerDay);
		request.setAttribute("firstRemindersPerDay", firstRemindersPerDay);
		request.setAttribute("secondRemindersPerDay", secondRemindersPerDay);
		request.setAttribute("thirdRemindersPerDay", thirdRemindersPerDay);
		request.setAttribute("responsePerDay", responsePerDay);
		request.setAttribute("firstResponsePerDay", firstResponsePerDay);
		request.setAttribute("secondResponsePerDay", secondResponsePerDay);
		request.setAttribute("thirdResponsePerDay", thirdResponsePerDay);

		dispatcher=getServletContext().getRequestDispatcher(forwardurl);
	    dispatcher.forward(request, response);
	    return;
	    
	}catch (ServletException e) {
		// TODO Auto-generated catch block
		  e.printStackTrace();
    } catch (IOException e) {
		// TODO Auto-generated catch block
		  e.printStackTrace();
	}
 }

	private long differenceInTwoDates(String d1, String d2) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dateFrom = formatter.parse(d1);
		Date dateTo = formatter.parse (d2);
		long diff = dateTo.getTime() - dateFrom.getTime();
		long diffDays = diff / (24 * 60 * 60 * 1000);
		return diffDays+1;
	}

	private void doUpdateReminder(HttpServletRequest request,HttpServletResponse response) {
	   try{
		   RequestDispatcher dispatcher;
			ssl = new ServerServiceImpl ();
			
			//TODO: change accordingly! 
			
			String upid = request.getParameter("upid");
			String rhour = request.getParameter ("rhour");
			String rmin = request.getParameter ("rmin");
			String rsec = request.getParameter ("rsec");
			String rnum = request.getParameter ("rnum");
			String rgap = request.getParameter ("rgap");
			String adestination = "/ReminderAdmin.jsp";
			
			if(upid.isEmpty())
			{
				request.setAttribute("updateuserexception", "Please enter PID!");
	            dispatcher=getServletContext().getRequestDispatcher(adestination);
	            dispatcher.forward(request,response);
				return;
			}
			
			PatientDetails patient = null;
			try
			{
				 patient = ssl.findPatientByPatientID (upid);
				
			}
			catch (Exception e)
			{
				request.setAttribute("updateuserexception", "Error while trying to find patient. Please try again");
	            dispatcher=getServletContext().getRequestDispatcher(adestination);
	            dispatcher.forward(request,response);
				return;			
			}
			

			int hour;
			int min = 0;
			int sec = 0;
			int num;
			int gap;
			
			if(rhour.isEmpty())
			{
				request.setAttribute("updateuserexception", "Please enter hour to start daily reminder!");
	            dispatcher=getServletContext().getRequestDispatcher(adestination);
	            dispatcher.forward(request,response);
				return;
			}
			else {
				hour = Integer.parseInt (rhour);
				if(hour<0 || hour>23)
				{
					request.setAttribute("updateuserexception", "Please enter valid hour to start daily reminder!");
					dispatcher=getServletContext().getRequestDispatcher(adestination);
					dispatcher.forward(request,response);
					return;
				}
			}
			
			if(rnum.isEmpty ())
			{
				request.setAttribute("updateuserexception", "Please enter number of reminders to sent in a day!");
	            dispatcher=getServletContext().getRequestDispatcher(adestination);
	            dispatcher.forward(request,response);
				return;
			}
			else{
				num = Integer.parseInt (rnum);
				if(num<0 || num>10)
			    {
					request.setAttribute("updateuserexception", "Please eneter valid number of reminders to sent in a day!");
					dispatcher=getServletContext().getRequestDispatcher(adestination);
					dispatcher.forward(request,response);
					return;
			    }
			}
			if(rgap.isEmpty ())
			{
				request.setAttribute("updateuserexception", "Please eneter the gap between the reminders!");
	            dispatcher=getServletContext().getRequestDispatcher(adestination);
	            dispatcher.forward(request,response);
				return;
			}
			else{
				gap = Integer.parseInt (rgap);
				if(gap<0 || gap>10)
				{
					request.setAttribute("updateuserexception", "Please eneter the valid gap between the reminders!");
					dispatcher=getServletContext().getRequestDispatcher(adestination);
					dispatcher.forward(request,response);
					return;
				}
			}
			
			if(!rmin.isEmpty ()){
				min = Integer.parseInt (rmin);
			}
			
			if(!rsec.isEmpty ()){
				sec = Integer.parseInt (rsec);
			}
			
			if(patient == null)
			{
				request.setAttribute("updateuserexception", "This PID does not exist!");
	            dispatcher=getServletContext().getRequestDispatcher(adestination);
	            dispatcher.forward(request,response);
				return;
				
			}
			else
			{
				try
				{
				
				PatientReminder pr = ssl.findPatientReminder (upid);
				Date d = new Date();
				pr.setTimeHour (hour);
				pr.setTimeMinute (min);
				pr.setTimeSecond (sec);
				pr.setGapInHours (gap);
				pr.setIterations (num);
				pr.setLastUpdated (d);
				pr.setLastEditedByUserName (ssl.getCurrentUser ());
				ssl.update (pr);			
				request.setAttribute("updateuserexception", "Patient Reminder Re-scheduled!");
	            dispatcher=getServletContext().getRequestDispatcher(adestination);
	            dispatcher.forward(request,response);
				return;
				}
				catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("updateuserexception", "Error Occured. Could not Re-schedule Reminder. Please try again!");
		            dispatcher=getServletContext().getRequestDispatcher(adestination);
		            dispatcher.forward(request,response);
					return;
				}
			}
		   
	   }catch (ServletException e) {
			// TODO Auto-generated catch block
			  e.printStackTrace();
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			  e.printStackTrace();
		}
		
	}

	private void doViewReminder(HttpServletRequest request, HttpServletResponse response) {
		try{
			
		RequestDispatcher dispatcher;
		ssl = new ServerServiceImpl ();
		
		//TODO: change accordingly! 
		
		String upid = request.getParameter("upid");
		String adestination = "/ReminderAdmin.jsp";
		
		if(upid.isEmpty())
		{
			request.setAttribute("viewuserexception", "Please enter PID!");
            dispatcher=getServletContext().getRequestDispatcher(adestination);
            dispatcher.forward(request,response);
			return;
		}
		
		PatientDetails patient = null;
		try
		{
			 patient = ssl.findPatientByPatientID (upid);
			
		}
		catch (Exception e)
		{
			e.printStackTrace ();			
		}
				
		if(patient == null)
		{
			request.setAttribute("viewuserexception", "This PID does not exist!");
            dispatcher=getServletContext().getRequestDispatcher(adestination);
            dispatcher.forward(request,response);
			return;			
		}
		else
		{
			try
			{
			PatientReminder pr = ssl.findPatientReminder (upid);
			String hour = String.valueOf(pr.getTimeHour());
			String min = String.valueOf(pr.getTimeMinute());
			String second = String.valueOf(pr.getTimeSecond());
			String gap = String.valueOf(pr.getGapInHours());
			String ilteration = String.valueOf(pr.getIterations());			
			request.setAttribute("hour", hour);
			request.setAttribute("min", min);
			request.setAttribute("sec", second);
			request.setAttribute("gap", gap);
			request.setAttribute("ilteration", ilteration);
            dispatcher=getServletContext().getRequestDispatcher(adestination);
            dispatcher.forward(request,response);
			return;
			}
			catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("viewuserexception", "Error Occured. Could not fetch Reminder. Please try again!");
	            dispatcher=getServletContext().getRequestDispatcher(adestination);
	            dispatcher.forward(request,response);
				return;
			}
		} 
		}catch (ServletException e) {
			// TODO Auto-generated catch block
			  e.printStackTrace();
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			  e.printStackTrace();
		}
		

	}

	private void doLabTechLogin(HttpServletRequest request, HttpServletResponse response) {
		try{ 
		
		 String dest="/registrationBook.jsp";
		 String dest2="/EnterPID.jsp";
		 RequestDispatcher dispatcher;
         HttpSession session=request.getSession();
         ServerServiceImpl ssl = new ServerServiceImpl();
         String ssn=request.getParameter("hw");
         String pass=request.getParameter("pw");
         String userid = null;
         String role = null;
         Users users = null;
         String status = null;
         String currelocationid = null;
        
                
    		if (ssl.authenticate (ssn, pass))
    		{

    			try
    			{
    				users = ssl.findUser (ssn);
    				userid = users.getPid();
    				role = users.getRole();
    				status = users.getStatus();
    					
    				LabMapping lmp = ssl.findMappingByPerson(userid);
    				currelocationid = lmp.getId().getLocationId();
    				
    			}

    				
                    catch(Exception e){
                    	e.printStackTrace ();
                  
                    }
    			if(status.equalsIgnoreCase("suspend"))
    			{
    				request.setAttribute("Invalid", "Account Suspended");
    				dispatcher=getServletContext().getRequestDispatcher(dest2);
    				dispatcher.forward(request,response);
                    return;
    			}

    				session.setAttribute("users", users);
                    session.setAttribute("currentuser", currelocationid);
                    dispatcher=getServletContext().getRequestDispatcher(dest);
    			
    			
                    dispatcher.forward(request,response);
                    return;
    		}
            else{
            	
            request.setAttribute("Invalid","Invalid Username or Password!");
            														 
            dispatcher=getServletContext().getRequestDispatcher(dest2);
            dispatcher.forward(request, response);
            return;
            }
		
	} catch (ServletException e) {
		// TODO Auto-generated catch block
		  e.printStackTrace();
    } catch (IOException e) {
		// TODO Auto-generated catch block
		  e.printStackTrace();
	}
		
	}

	private void doViewOrForms(HttpServletRequest request, HttpServletResponse response) {
		
		try{
			
		RequestDispatcher dispatcher;
        String dest="/EditForm.jsp";
		String username = request.getParameter("upid");
		
		ssl = new ServerServiceImpl ();
		
		Screening screening = null;
		try
		{
			screening = ssl.findScreeningByPatientID(username);
		}
		catch (Exception e)
		{
			request.setAttribute("errorattribute", "Error while finding patient");
	        dispatcher=getServletContext().getRequestDispatcher(dest);
	        dispatcher.forward(request,response);
	        return;
		}
				
		if (screening == null)
		{
			request.setAttribute("errorattribute", "Patient doesn't exists!");
	        dispatcher=getServletContext().getRequestDispatcher(dest);
	        dispatcher.forward(request,response);
	        return;
		}
		
		
		try
		{
			long pno = ssl.count ("patientdetails", "where pid='" + username + "'" );
			if (pno == 0){
				request.setAttribute("errorattribute", "OR forms for the patient are not filled!");
		        dispatcher=getServletContext().getRequestDispatcher(dest);
		        dispatcher.forward(request,response);
		        return;	
			}		  	
		}
		catch (Exception e3)
		{
			// TODO Auto-generated catch block
			e3.printStackTrace();
			request.setAttribute("errorattribute", "Error while finding forms.");
	        dispatcher=getServletContext().getRequestDispatcher(dest);
	        dispatcher.forward(request,response);
	        return;
		} 	
		
		PatientDetails pd = null;
		pd = ssl.findPatientByPatientID (username);
		
		try{
			    request.setAttribute ("upid",username);
			    request.setAttribute ("patreg", "yes");
			    request.setAttribute("addhouse", pd.getAddressHouse ());
			    request.setAttribute("addstreet", pd.getAddressStreet ());
			    request.setAttribute("adddistrict", pd.getAddressDistrict ());
			    request.setAttribute("addflat", pd.getAddressFlat ());
			    request.setAttribute("phone1", pd.getPhone1 ());
			    request.setAttribute("homephone", pd.getHomePhone ());
			    request.setAttribute("hw", pd.getHealthWorkerID ());
			    request.setAttribute("realtionshipfamily", pd.getRelationshipFamily ());
			    request.setAttribute("maritalstatus", pd.getMaritalStatus ());
			    request.setAttribute("education", pd.getEducation ());
			    request.setAttribute("incomefamily", pd.getIncomeFamilyMember ());
			    request.setAttribute("gender", pd.getGender ());
			    request.setAttribute("firstname", pd.getFirstName ());
			    request.setAttribute("lastname", pd.getlastName ());
			    request.setAttribute("dob", String.valueOf (pd.getdob ()));
			    request.setAttribute ("dateTreatment",String.valueOf(pd.getStartTreatment ()));
			    
			    try{
					
					long dno =ssl.count ("baselinedetails", "where patientId='" + username + "'");
					if (dno != 0){
						BaselineDetails bd = ssl.findBaselineByPatientID (username);
						request.setAttribute ("basdet", "yes");
						request.setAttribute ("hwbd", bd.getChwId ());
						request.setAttribute ("baselinesputumbd", bd.getBaselineSputum ());
						request.setAttribute ("baselinechestbd", bd.getBaselineChest ());
						request.setAttribute ("baselinegenexpertbd", bd.getBaselineGeneXpert ());
						request.setAttribute ("drugsensitivity", bd.getGeneXpertDrugSensitivity ());
						request.setAttribute ("baselinegenexpertbd", bd.getBaselineGeneXpert ());
						request.setAttribute ("catpatientbd", bd.getPatientCategory ());
						request.setAttribute ("typepatientbd", bd.getTypePatient ());
						request.setAttribute ("weightbd", bd.getWeight ());
						request.setAttribute ("regimenbd", bd.getRegimen ());
						request.setAttribute ("fixeddosebd", bd.getFixedDose ());
						if(!(bd.getStrepto() == null || bd.getStrepto() == ""))
						  request.setAttribute ("streptomycinbd", bd.getStrepto ());
						request.setAttribute ("otherxraysitebd", bd.getOtherXraySite ());
						request.setAttribute ("xrayresultbd", bd.getXrayResult ());
						request.setAttribute ("treatmentdatebd", String.valueOf (bd.getTreatmentInitiation ()));
						
						try{
							
							long mno = ssl.count ("monitoringresults", "where patientId='" + username + "'");
							if(mno != 0){
								String result = null;
							    request.setAttribute ("mondet", "yes");
							    MonitoringResults[] mrs = ssl.findMonitoringResultsByPatientId (username);
								for (MonitoringResults mr : mrs)
								{   
									if(result == null)
									  result = mr.getCurrentMonth () + " ";
									else
								      result = result + mr.getCurrentMonth () + " ";
									
									int mon = mr.getCurrentMonth ();
									request.setAttribute ("hwmd"+mon,mr.getHealthWorkerId ());
									request.setAttribute ("treatmentdatemd"+mon,String.valueOf (mr.getDateofTreatment ()));
									request.setAttribute ("baselinesmearmd"+mon, mr.getBaselineSmear ());
									request.setAttribute ("smearresult"+mon, mr.getSmearResult ());
									request.setAttribute ("patientfeel"+mon, mr.getPatientFeel ());
									request.setAttribute ("expsideeffect"+mon, mr.getExperienceSideEffects ());
									if(mr.getExperienceSideEffects ().equalsIgnoreCase ("YES"))
									   request.setAttribute ("sideeffect"+mon, mr.getPatientSideEffects ());
									request.setAttribute ("othersideeffect"+mon, mr.getOtherSideEffects ());
									request.setAttribute ("patientconsult"+mon, mr.getPatientConsult ());
									request.setAttribute ("healthfacility"+mon, mr.getHealthFacility ());
									request.setAttribute ("missmedication"+mon, mr.getMissMedication ());
									request.setAttribute ("durationmissmedication"+mon, mr.getDurationMissMedication ());
									request.setAttribute ("medicinewrapper"+mon, mr.getMedicineWrappers ());
									request.setAttribute ("doseconsumption"+mon, mr.getDoseConsumption ());
								}
							    request.setAttribute ("months", result);								
							}
						}catch(Exception e ){
							
							e.printStackTrace();
							request.setAttribute("errorattribute", "Error while finding forms.");
					        dispatcher=getServletContext().getRequestDispatcher(dest);
					        dispatcher.forward(request,response);
					        return;
						
						}
					}
				}
				catch (Exception e2)
				{
					e2.printStackTrace ();		
				}
			
				request.setAttribute("usersattribute", "Updated Successfully!");
	            dispatcher=getServletContext().getRequestDispatcher(dest);
	            dispatcher.forward(request,response);
	            return;
			}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorattribute", "Error while finding forms.");
	        dispatcher=getServletContext().getRequestDispatcher(dest);
	        dispatcher.forward(request,response);
	        return;
		}
		
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			  e.printStackTrace();
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			  e.printStackTrace();
		}	
		
	}

	private void doCSVReportsForOR(HttpServletRequest request,HttpServletResponse response) {

		try {
			
		String date = null;
		String dateSecond =  null;
		String reportType;
		
		date = request.getParameter("date");
		dateSecond = request.getParameter("dateSecond");
		reportType = request.getParameter ("form");
		
		if(reportType.equalsIgnoreCase ("1")){
			RequestDispatcher dispatcher;
			String forwardurl = "/CSVReports.jsp";
			request.setAttribute("Invalid", "Please select Report Type.");
			dispatcher=getServletContext().getRequestDispatcher(forwardurl);
		    dispatcher.forward(request, response);
		    return;
		}
		
		if(date == "" || dateSecond == ""){
			RequestDispatcher dispatcher;
			String forwardurl = "/CSVReports.jsp";
			request.setAttribute("Invalid", "Please fill in the Dates.");
			dispatcher=getServletContext().getRequestDispatcher(forwardurl);
		    dispatcher.forward(request, response);
		    return;
		}
		else{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 		
			try {
		 
				Date fromDate = formatter.parse(date);
				Date toDate = formatter.parse (dateSecond);
				if(fromDate.compareTo(toDate)>0){
					RequestDispatcher dispatcher;
					String forwardurl = "/CSVReports.jsp";
					request.setAttribute("Invalid", "Please fill in Valid Dates.");
					dispatcher=getServletContext().getRequestDispatcher(forwardurl);
				    dispatcher.forward(request, response);
				    return;
	        	}		 
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		createCSV(request, response);
		
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			  e.printStackTrace();
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			  e.printStackTrace();
		}		
		
	}

	public static void createCSV(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String date = request.getParameter("date");
		String dateSecond = request.getParameter("dateSecond");
		String reportType = request.getParameter ("form");
		ServerServiceImpl ssl = new ServerServiceImpl ();
		
        try{

        	String data[][] = null;
        	
        	if(reportType.equalsIgnoreCase ("2")){
        		response.setHeader("Content-Disposition", "attachment; filename=PatientDetailsExport_"+DateUtils.convertToString(new Date()).substring(0,10)+".zip"); 
        		String[] s={"patientdetails.PID" , "FirstName", "LastName", "Gender", "DOB", "AddressHouse", "AddressStreet", "AddressDistrict", "AddressFlat", "Phone1", "HomePhone", "MaritalStatus", "Education", "IncomeFamilyMember", "NominateVolunteer", "RelationshipFamily", "StartTreatment", "HealthWorkerID", "ENDTIMEFORM", "LocationName"};
        		data = ssl.getTableData("patientdetails, labmapping, location", s, "ENDTIMEFORM between '"+date+"%' and '"+dateSecond+" 23:59:59"+"' and " +
    				"patientdetails.HEALTHWORKERID = labmapping.PID and labmapping.LocationID = location.LocationID");
        	}else if(reportType.equalsIgnoreCase ("3")){
        		response.setHeader("Content-Disposition", "attachment; filename=BaselineDetailsExport_"+DateUtils.convertToString(new Date()).substring(0,10)+".zip"); 
        		String[] s={"PatientID", "BaselineSputum", "BaselineChest", "OtherXraySite", "XrayResult", "BaselineGeneXpert", "DrugSensitivity", "TypePatient", "PatientCategory", "weight", "Regimen", "FixedDose","Streptomycin", "HealthWorkerID", "EndTimeForm", "LocationName"};
        		data = ssl.getTableData("baselinedetails, location", s, "EndTimeForm between '"+date+"%' and '"+dateSecond+" 23:59:59"+"' and " +
    				"baselinedetails.LocationId = location.LocationId");
        	}else if(reportType.equalsIgnoreCase ("4")){
        		response.setHeader("Content-Disposition", "attachment; filename=MonitoringResultsExport_"+DateUtils.convertToString(new Date()).substring(0,10)+".zip"); 
        		String[] s={"PatientID", "DateTreatmentInitiation", "baselineSmear", "currentMonth", "smearResult", "patientFeel", "experienceSideEffects", "patientSideEffects", "otherSideEffects", "patientConsult", "healthFacility", "missMedication", "durationMissMedication", "medicineWrappers", "doseConsumption", "endTimeForm", "HealthWorkerID", "LocationName"};
        		data = ssl.getTableData("monitoringresults, labmapping, location", s, "EndTimeForm between '"+date+"%' and '"+dateSecond+" 23:59:59"+"' and " +
    				"monitoringresults.HealthWorkerID = labmapping.PID and labmapping.LocationID = location.LocationID group by monitoringresults.patientID , monitoringresults.currentMonth");
        	}else if(reportType.equalsIgnoreCase ("5")){
        		response.setHeader("Content-Disposition", "attachment; filename=SurveyExport_"+DateUtils.convertToString(new Date()).substring(0,10)+".zip");
        		String[] s={"surveys.pid", "satisfiedWithSystem", "unsatisfactoryAspectSystem", "systemImprovement", "satisfiedWithSmsReminder", "unsatisfactoryAspectSmsReminder", "reminderAdditionalEffort", "reminderTreatmentCompliance", "satisfiedServiceHealthFacility", "satisfiedCareTbSpeaclist", "TBTreatmentSystem", "healthWorkerID", "endTimeForm","LocationName"};
        		data = ssl.getTableData("surveys, labmapping, location", s, "EndTimeForm between '"+date+"%' and '"+dateSecond+" 23:59:59"+"' and " +
				"surveys.healthWorkerID = labmapping.PID and labmapping.LocationID = location.LocationID");
        	}
        	        	        	
    		response.setContentType("application/zip"); 
			response.setCharacterEncoding("UTF-8");
			ZipOutputStream zip = null;
			try {
				zip = new ZipOutputStream(response.getOutputStream());
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			CSVUtil.makeCsv(zip,data, reportType);
          
        }
        catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void doChangePatientMobileNo(HttpServletRequest request,HttpServletResponse response) {
		
		try{
			
			RequestDispatcher dispatcher;
			ssl = new ServerServiceImpl ();
			String upid = request.getParameter("upid");
			String upwd = request.getParameter("uphone");
			String adestination = "/ChangePatientMobileNo.jsp";
			
			if(upid.isEmpty())
			{
				request.setAttribute("updateuserexception", "Please enter Patient ID!");
	            dispatcher=getServletContext().getRequestDispatcher(adestination);
	            dispatcher.forward(request,response);
				return;
			}
			
			PatientDetails patientDetails = null;
			try
			{
				patientDetails = ssl.findPatientByPatientID(upid);			
			}
			catch (Exception e)
			{
				e.printStackTrace ();			
			}
			if(patientDetails == null)
			{
				request.setAttribute("updateuserexception", "This PatientID does not exist!");
	            dispatcher=getServletContext().getRequestDispatcher(adestination);
	            dispatcher.forward(request,response);
				return;			
			}
			else
			{
				String regex = "[0-9]+";
				if (!upwd.matches(regex)){
					request.setAttribute("updateuserexception", "Enter valid mobile Number!");
		            dispatcher=getServletContext().getRequestDispatcher(adestination);
		            dispatcher.forward(request,response);
					return;		
				}
				
				try
				{
				patientDetails.setPhone1 (upwd);
				ssl.updatePatientDetails(patientDetails);
				request.setAttribute("updateuserexception", "Contact Number Updated!");
	            dispatcher=getServletContext().getRequestDispatcher(adestination);
	            dispatcher.forward(request,response);
				return;
				}
				catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("updateuserexception", "Error Occured. Could not update patient contact number. Please try again!");
		            dispatcher=getServletContext().getRequestDispatcher(adestination);
		            dispatcher.forward(request,response);
					return;
				}
			}
			
	    } catch (ServletException e) {
		// TODO Auto-generated catch block
		  e.printStackTrace();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		  e.printStackTrace();
	    }
		
	}

	private void doChangeLocation(HttpServletRequest request,HttpServletResponse response) {
		
		try{
			
		RequestDispatcher dispatcher;
        HttpSession session=request.getSession();
        String dest="/changeLocation.jsp";
		String username = request.getParameter("uw");
		String currLocation = request.getParameter("currentLocation");
		String newLocation = request.getParameter("newLocation");
		String pid;
		String role;
		ssl = new ServerServiceImpl ();
		
		try
		{
			Users users = ssl.findUser (username);
			pid = users.getPid ();
			role = users.getRole ();
		}
		catch (Exception e)
		{
			e.printStackTrace ();
			session.setAttribute("usersattribute", "Cant find this user. Please try again!");
            dispatcher=getServletContext().getRequestDispatcher(dest);
            dispatcher.forward(request,response);
            return;
			
		}
		//find id for current location
		
		Location locationcurre =ssl.findLocationIDByName(currLocation);
		String locationIDCurr = locationcurre.getLocationId();
		
				
		//update current user in Labmapping to reflect blank/empty
		try{
		LabMapping lmapp = ssl.findMappingByPerson (pid, locationIDCurr);
		
		
		ssl.delete(lmapp);
		}
		catch (Exception e) {
		
			e.printStackTrace();
			session.setAttribute("usersattribute", "Current Location and User ID do not match!");
			dispatcher=getServletContext().getRequestDispatcher(dest);
            dispatcher.forward(request,response);
            return;
		}
		
		
		//find id for new location
		Location location = ssl.findLocationIDByName(newLocation);
		String newLocationName =  location.getLocationName();
		String locationID = location.getLocationId();

		
		//updating labmapping table by changing this user's current location to a new location
		LabMapping mapp = new LabMapping();
		LabMappingId lmi = new LabMappingId(locationID, pid);
		
		mapp.setId(lmi);
		mapp.setPidrole(role);
		
		try{
			
				ssl.saveLabMapping(mapp);
				session.setAttribute("usersattribute", "Updated Successfully!");
	            dispatcher=getServletContext().getRequestDispatcher(dest);
	            dispatcher.forward(request,response);
	            return;
				
			}
		catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private void doAddUser(HttpServletRequest request,HttpServletResponse response) {
		
		try {
		
		RequestDispatcher dispatcher;
		ssl = new ServerServiceImpl ();
		String apid = request.getParameter("apid");
		String afirstName = request.getParameter("afname");
		String alastName = request.getParameter("alname");
		String acurrentLocation = request.getParameter("acurrentLocation");
		String arole = request.getParameter("arole");
		String adestination = "/AddUser.jsp";
		
		if(apid.isEmpty())
		{
			request.setAttribute("adduserexception", "Please enter PID!");
            dispatcher=getServletContext().getRequestDispatcher(adestination);
            dispatcher.forward(request,response);
			return;
		}
		
		Users users = null;
		try
		{
			 users = ssl.findUser (apid);
			
		}
		catch (Exception e)
		{
			e.printStackTrace ();
			
			
		}
		if(users != null)
		{
			request.setAttribute("adduserexception", "This PID already exists. Please enter a different PID!");
            dispatcher=getServletContext().getRequestDispatcher(adestination);
            dispatcher.forward(request,response);
			return;
			
		}
		else
		{
			// adding a user
			try{
			users = new Users();
			users.setFirstName(afirstName.toUpperCase());
			users.setLastName(alastName.toUpperCase());
			users.setUserName(apid);
			users.setPid(apid);
			users.setStatus("ACTIVE");
			users.setPassword(apid);
			users.setRole(arole);
			ssl.saveUser(users);
			
			//finding id for new location
			Location location = ssl.findLocationIDByName(acurrentLocation);
			String newLocationName =  location.getLocationName();
			String locationID = location.getLocationId();
			
			LabMapping mapp = new LabMapping();
			LabMappingId lmi = new LabMappingId(locationID, apid);
			
			mapp.setId(lmi);
			mapp.setPidrole("LabTech");
			ssl.saveLabMapping(mapp);
			request.setAttribute("adduserexception", "User Saved!");
            dispatcher=getServletContext().getRequestDispatcher(adestination);
            dispatcher.forward(request,response);
			return;
			
			}
			catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("adduserexception", "Error Occured. Could not save user. Please try again!");
	            dispatcher=getServletContext().getRequestDispatcher(adestination);
	            dispatcher.forward(request,response);
				return;
				
			}
			
		}
		
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void doAddDocContactNumber(HttpServletRequest request, HttpServletResponse response) {
       
		RequestDispatcher dispatcher;
		ssl = new ServerServiceImpl ();
		String upid = request.getParameter("upid");
		String upwd = request.getParameter("uphone");
		String adestination = "/AddDocContactNumber.jsp";
		
		try {
		
		if(upid.isEmpty())
		{
			request.setAttribute("updateuserexception", "Please enter PID!");
            dispatcher=getServletContext().getRequestDispatcher(adestination);
            dispatcher.forward(request,response);
			
			return;
		}
		
		Users users = null;
		try
		{
			 users = ssl.findUser (upid);			
		}
		catch (Exception e)
		{
			e.printStackTrace ();			
		}
		if(users == null)
		{
			request.setAttribute("updateuserexception", "This PID does not exist!");
            dispatcher=getServletContext().getRequestDispatcher(adestination);
            dispatcher.forward(request,response);
			return;			
		}
		else
		{
			String role = users.getRole ();
			if (!role.equalsIgnoreCase ("Doctor")){
				request.setAttribute("updateuserexception", "This PID does not have Doctor role!");
	            dispatcher=getServletContext().getRequestDispatcher(adestination);
	            dispatcher.forward(request,response);
				return;	
			}
			
			String regex = "[0-9]+";
			if (!upwd.matches(regex)){
				request.setAttribute("updateuserexception", "Enter valid mobile Number!");
	            dispatcher=getServletContext().getRequestDispatcher(adestination);
	            dispatcher.forward(request,response);
				return;		
			}
			
			try
			{
			users.setPhoneNo (upwd);
			ssl.updateUser(users);
			request.setAttribute("updateuserexception", "User Updated!");
            dispatcher=getServletContext().getRequestDispatcher(adestination);
            dispatcher.forward(request,response);
			return;
			}
			catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("updateuserexception", "Error Occured. Could not update user. Please try again!");
	            dispatcher=getServletContext().getRequestDispatcher(adestination);
	            dispatcher.forward(request,response);
				return;
			}
		}

		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
