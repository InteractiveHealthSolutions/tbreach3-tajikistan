/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package test;

import ird.xoutTB.emailer.exception.EmailException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import javax.mail.MessagingException;
import org.irdresearch.smstarseel.context.TarseelContext;
import org.irdresearch.smstarseel.context.TarseelServices;
import org.irdresearch.smstarseel.data.Project;
import org.irdresearch.smstarseel.data.OutboundMessage.PeriodType;
import org.irdresearch.smstarseel.data.OutboundMessage.Priority;
import org.irdresearch.tbreach2.shared.model.BaselineDetails;
import org.irdresearch.tbreach2.shared.model.PatientDetails;
import org.irdresearch.tbreach2.shared.model.PatientResponse;
import org.irdresearch.tbreach2.shared.model.Reminder;
import org.irdresearch.tbreach2.shared.model.ReminderHistory;
import org.irdresearch.tbreach2.shared.model.ReminderText;
import org.irdresearch.tbreach2.shared.model.Users;
import org.irdresearch.tbreach2.shared.model.ReminderHistory.REMINDER_STATUS;
import org.irdresearch.tbreachtajikistan.sms.DoctorsReminderJob;
import org.irdresearch.tbreachtajikistan.sms.HibernateUtil;
import org.irdresearch.tbreachtajikistan.utils.DateUtils;
import org.irdresearch.tbreachtajikistan.utils.EmailEngine;
import org.quartz.JobExecutionException;
import com.csvreader.CsvWriter;

public class test
{
	
	public static void main(String[] args) {
		
		File file = new File("C:\\Application Data\\XpertSMS\\TBReach.properties");
		
		if(file.exists() && !file.isDirectory())
			System.out.println("false");
		System.out.println("true");
		
		
		
	}
		/*String message = "The list of patient who haven't responded in from 3 days or more: \n";
		message = message + "Patient ID - Phone Number \n";
		HibernateUtil util = new HibernateUtil();
		List<PatientResponse> patientresponse = new ArrayList<PatientResponse>();
		List<PatientDetails> patientList = new ArrayList<PatientDetails>();
		List<PatientDetails> flaggedpatients = new ArrayList<PatientDetails>();

		
		patientList = util.getAllActivePatientsFromPatientDetails ();
		
		for(int i = 0; i<patientList.size (); i++){
			
			PatientDetails pd = new PatientDetails();
			pd = patientList.get (i);
			int p_id = Integer.parseInt (pd.getPid ());
			
			int x = -2;
			Calendar cal = GregorianCalendar.getInstance();
			cal.add( Calendar.DAY_OF_YEAR, x);
			Date threeDaysAgo = cal.getTime();
			patientresponse = util.getResponses(p_id, threeDaysAgo, new Date());
			
			if(patientresponse.isEmpty ()){
				Date patientRegistered = pd.getStartTimeForm ();
				long days = DateUtils.getDifferenceInDays (patientRegistered, new Date());
				if(days>=3) {
					
					flaggedpatients.add (patientList.get (i));
					message = message + pd.getPid () + " - " + pd.getPhone1 () + "\n";
				}
				
			}*/
			
		}
		
//TODO: SENT MAIL with patient id's in flaggedpatients!
		
		
		


