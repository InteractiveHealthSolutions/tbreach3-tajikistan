/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package org.irdresearch.tbreachtajikistan.sms;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.irdresearch.smstarseel.context.TarseelContext;
import org.irdresearch.smstarseel.context.TarseelServices;
import org.irdresearch.smstarseel.data.Project;
import org.irdresearch.smstarseel.data.OutboundMessage.PeriodType;
import org.irdresearch.smstarseel.data.OutboundMessage.Priority;
import org.irdresearch.tbreach2.shared.model.BaselineDetails;
import org.irdresearch.tbreach2.shared.model.PatientDetails;
import org.irdresearch.tbreach2.shared.model.Reminder;
import org.irdresearch.tbreach2.shared.model.ReminderHistory;
import org.irdresearch.tbreach2.shared.model.ReminderText;
import org.irdresearch.tbreach2.shared.model.Users;
import org.irdresearch.tbreach2.shared.model.ReminderHistory.REMINDER_STATUS;
import org.irdresearch.tbreachtajikistan.utils.DateUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class DoctorsReminderJob implements Job
{


	@Override
	public void execute (JobExecutionContext arg0) throws JobExecutionException
	{
		HibernateUtil util = new HibernateUtil();
		List<BaselineDetails> patientList = new ArrayList<BaselineDetails>();
		patientList = util.getAllPatientsFromBaselineDetails();
		List<ReminderText> reminderText = new ArrayList<ReminderText>();
		reminderText = util.getReminderText (3);
		
		Reminder rem = new Reminder();
		rem = util.getReminder(3);
		
		for(int i = 0; i<patientList.size (); i++){
			
			BaselineDetails pr = new BaselineDetails();
			pr = patientList.get (i);
			int p_id = Integer.parseInt (pr.getPatientId ());
			
			Date treatmentStarted = pr.getTreatmentInitiation ();
			long days = DateUtils.getDifferenceInDays (treatmentStarted, new Date());
			
			Boolean notificationSent = false;
			String reminderMsg = null;
			String projectUniID = null;
			int validity_Period = 2;
			ReminderHistory rh = new ReminderHistory();
			int reminder_count = 1;
			PatientDetails pd = util.getPatientDetail (p_id);
			String active = pd.getActive();
			
			if(active.equals("1")){
				if (days%24 == 0){
					notificationSent = true;
					ReminderText rt = reminderText.get (0);
					reminderMsg = rt.getText () +" " + pd.getFirstName () + " "+ pd.getlastName () + " (" + p_id + ")";
				}
				else if (days%26 == 0){
					notificationSent = true;
					ReminderText rt = reminderText.get (1);
					reminderMsg = rt.getText () +" " + pd.getFirstName () + " "+ pd.getlastName () +" (" + p_id + ")";
					reminder_count = 2;
				}
				if (notificationSent == true) {
					
					Users user = new Users ();
					user = util.getUser (pr.getChwId ());
					
					TarseelServices ts = TarseelContext.getServices();
					Project pID = ts.getDeviceService().findProjectById(1);
					Calendar cal = Calendar.getInstance();
					cal.set(Calendar.HOUR_OF_DAY, 9);
					cal.set(Calendar.MINUTE, 30);
					projectUniID = ts.getSmsService().createNewOutboundSms(user.getPhoneNo (), reminderMsg, new Date(),Priority.HIGH, validity_Period, PeriodType.HOUR,pID.getProjectId(), pr.getChwId ());
					
					rh.setCellNo(user.getPhoneNo ());
					rh.setPatientDetails(pd);
					rh.setReminder(rem);
					rh.setReminderNumForTheDay(reminder_count);
					rh.setReminderText(reminderMsg);
					rh.setStatusOfReminder(REMINDER_STATUS.LOGGED);
					rh.setSentDate(DateUtils.getDate());
					rh.setSentTime(new Time(Calendar.getInstance().getTimeInMillis()));
					rh.setUniqueID(projectUniID);
					
					ts.commitTransaction();
					ts.closeSession();
				}	
			}
		}
		
	}

}
