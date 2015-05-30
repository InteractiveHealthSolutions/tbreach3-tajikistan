/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package org.irdresearch.tbreachtajikistan.sms;

import ird.xoutTB.context.ServiceContext;
import ird.xoutTB.db.entity.Patient;
import org.irdresearch.tbreach2.shared.model.PatientReminder;
import org.irdresearch.tbreach2.shared.model.PatientResponse;
import org.irdresearch.tbreach2.shared.model.ReminderHistory;
import org.irdresearch.tbreach2.shared.model.Reminder;
import org.irdresearch.tbreach2.shared.model.ReminderHistory.REMINDER_STATUS;
import org.irdresearch.tbreach2.shared.model.ReminderText;

import org.irdresearch.tbreach2.shared.model.PatientDetails;
import org.irdresearch.tbreachtajikistan.utils.DateUtils;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.irdresearch.smstarseel.context.TarseelContext;
import org.irdresearch.smstarseel.context.TarseelServices;
import org.irdresearch.smstarseel.data.OutboundMessage.PeriodType;
import org.irdresearch.smstarseel.data.OutboundMessage.Priority;
import org.irdresearch.smstarseel.data.Project;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SendReminderJob implements org.quartz.StatefulJob {

	private ServiceContext sc = new ServiceContext();
	private ReminderHistory rh = new ReminderHistory();
	private int remNum = 0;
	private JobExecutionContext jctx;
	private int reminderSystemJobIndex = -1;
	private int reminderNumberfortheDAY;

	// private ScheduledReminders sch = null;
	List<ReminderHistory> reminderCount = new ArrayList<ReminderHistory>();
	List<PatientResponse> patienaresponse = new ArrayList<PatientResponse>();
	List<PatientReminder> patreminders = new ArrayList<PatientReminder>();
	List<ReminderText> reminderText = new ArrayList<ReminderText>();
	
	
	char realdifference;
	HibernateUtil util = new HibernateUtil();
	@Override
	public void execute(JobExecutionContext jctx) throws JobExecutionException {
		System.out.println(new Date());
		int validity_Period = 2;
/*		Patient p = (Patient) jctx.getJobDetail().getJobDataMap()
				.get("patient");
		Reminder r = (Reminder) jctx.getJobDetail().getJobDataMap()
				.get("reminder");
		
		String projectUniID = null;*/
		Reminder r = (Reminder) jctx.getJobDetail().getJobDataMap().get("reminder");
		String REMTXT = null;
		int medicineReminderType;
		PatientDetails pd = (PatientDetails) jctx.getJobDetail().getJobDataMap().get("patient");
		String projectUniID = null;
		/*String REMTXT = "";*/
		this.jctx = jctx;
	//	reminderSystemJobIndex = currentJctxJobIndex(pd);
		try {
			//Integer p_id = p.getPatientRecordNumber();
			
			reminderText = util.getReminderText (1);
			
			Integer p_id = Integer.parseInt(pd.getPid());
			// chk for response wid respect to patient's reminder time
			//Time firstResponseHour = null;
			String firstResponseHour= null;
			/*patienaresponse = sc.getResponseService().getReminderNumber(p_id, new Date(), new Date());*/
			patienaresponse = util.getResponses(p_id, new Date(), new Date());
			reminderCount = util.getReminderNumber(p_id, new Date(), new Date());
			patreminders = util.gettimeofReminder(p_id);
			PatientReminder prreminder = patreminders.get(0);
			// ReminderHistory reminderhist=reminderCount.get(0);
			// Time firstReminderSentHOUR=reminderhist.getSentTime();

			String pTime = prreminder.getTimeHour() + ":"
					+ prreminder.getTimeMinute() + ":"
					+ prreminder.getTimeSecond();// when it goes daily, 10:30
			
			//get the daily time of reminders, we have fixed for Tajikistan, morning 9:05 am
			/*String pTime = "09:05:00";*/
			
			
			if (!patienaresponse.isEmpty()) {
				PatientResponse responses = patienaresponse.get(0);
				//firstResponseHour = responses.getRecieveTime();// first patient
				firstResponseHour=responses.getRecieveDate().getHours()+":"+responses.getRecieveDate().getMinutes()+":"+responses.getRecieveDate().getSeconds();												// response for
																// that day
			}
			System.out.println(pTime + "senthour.........." + " bbr "+ firstResponseHour);
			DateFormat df = new SimpleDateFormat("HH:mm:ss");
			df.setTimeZone(TimeZone.getDefault());
			Date responseHourDate = null;
			// Date date1 = df.parse(firstResponseHour.toString());//12:30
			if(firstResponseHour!=null){
				responseHourDate=df.parse(firstResponseHour);
			}
			Date date2 = df.parse(pTime);
			Long time = date2.getTime();
			time -= (2 * 60 * 60 * 1000);
			Date d2 = new Date(time);// trigger time-2=10:30-2=8:30
			if (reminderCount.size() < 3) {//9:00 12:21
				if (patienaresponse.isEmpty() || responseHourDate.before(d2))// response
																				// before
																				// benchmark
				{

					reminderNumberfortheDAY = reminderCount.size();
					
					if(reminderNumberfortheDAY == 0)
					{
						medicineReminderType = 0;
						//REMTXT = pickupRandomReminderText(r, medicineReminderType);
						ReminderText rt = reminderText.get (medicineReminderType);
						REMTXT = rt.getText ();
					}
					else
					{
						medicineReminderType = 1;
						//REMTXT = pickupRandomReminderText(r, medicineReminderType);
						ReminderText rt = reminderText.get (medicineReminderType);
						REMTXT = rt.getText ();
					}
					
					//System.out.println(reminderNumberfortheDAY+ " this is the reminder COunt");
					//System.out.println(REMTXT + "REminder text");
					reminderNumberfortheDAY++;
					//System.out.println(reminderNumberfortheDAY+ " reminder increment");
					TarseelServices ts = TarseelContext.getServices();

					Project pID = ts.getDeviceService().findProjectById(1);
					//PatientReminder pr = new PatientReminder();

					Calendar cal = Calendar.getInstance();
					cal.set(Calendar.HOUR_OF_DAY, prreminder.getTimeHour());
					cal.set(Calendar.MINUTE, prreminder.getTimeMinute());
					cal.set(Calendar.SECOND, prreminder.getTimeSecond());
/*					int hour = 9;
					int minute = 5;
					int second = 0;
					
					Calendar cal = Calendar.getInstance();
					cal.set(Calendar.HOUR_OF_DAY, hour);
					cal.set(Calendar.MINUTE, minute);
					cal.set(Calendar.SECOND, second);
*/					
					
					/*Phone Number cant be null, either fill in Phone 1 or Phone 2
					 * 
					 * 
					 *
					 */
					if(pd.getPhone1()!=null || !pd.getPhone1().equalsIgnoreCase(""))
					{
						projectUniID = ts.getSmsService().createNewOutboundSms(pd.getPhone1(), REMTXT, new Date(),Priority.HIGH, validity_Period, PeriodType.HOUR,pID.getProjectId(), Integer.toString(p_id));
						rh.setCellNo(pd.getPhone1());
						rh.setPatientDetails(pd);
						rh.setReminder(r);
						rh.setReminderNumForTheDay(reminderNumberfortheDAY);
						rh.setReminderText(REMTXT);
						rh.setStatusOfReminder(REMINDER_STATUS.LOGGED);
						rh.setSentDate(DateUtils.getDate());
						rh.setSentTime(new Time(Calendar.getInstance().getTimeInMillis()));
						rh.setUniqueID(projectUniID);
	
					}
 
					else
					{
						projectUniID = ts.getSmsService().createNewOutboundSms(pd.getPhone2(), REMTXT, new Date(),Priority.HIGH, validity_Period, PeriodType.HOUR,pID.getProjectId(), Integer.toString(p_id));
						rh.setCellNo(pd.getPhone2());
						rh.setPatientDetails(pd);
						rh.setReminder(r);
						rh.setReminderNumForTheDay(reminderNumberfortheDAY);
						rh.setReminderText(REMTXT);
						rh.setStatusOfReminder(REMINDER_STATUS.LOGGED);
						rh.setSentDate(DateUtils.getDate());
						rh.setSentTime(new Time(Calendar.getInstance().getTimeInMillis()));
						rh.setUniqueID(projectUniID);
					}
					
					
					try {
						ts.commitTransaction();
						util.addReminderHistory(rh);
						
						//System.out.println("Reminder Sent..............");
					} catch (Exception e) {
/*						xoutFiles
								.UnsavedReminder("---------------------------------------------"
										+ "\nException message:"
										+ e.getMessage()
										+ "\n"
										+ rh.toString()
										+ "\n---------------------------------------------------");

						LoggerUtil
								.logIt("Reminder History Record was not saved.. "
										+ ExceptionUtil.getStackTrace(e));
						String message = "Reminder History Record was not saved.. \nCheck if database connections are working."
								+ "\nRem Num:"
								+ rh.getReminderNumForTheDay()
								+ "\nPatient:"
								+ rh.getPatient().getPatientId()
								+ "\nReminder:"
								+ rh.getReminder().getName()
								+ "\nOn Date:"
								+ rh.getSentDate()
								+ "\n\nCheck log and unsent reminder history record for complete details.";
						ReminderSystem
								.emailErrorReportToAdmin(
										"Reminder History Record was not saved in database",
										message);*/
					}
					finally{
						ts.closeSession();
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}// remNum is the current reminder number in the db
		finally {
			//sc.closeSession();
		}
	}

	/*
	 * OutboundMessage msg; msg=new OutboundMessage(p.getCellNoLatest(), "");
	 * 
	 * try{ msg.addText(REMTXT); }catch (Exception e) { msg=new
	 * OutboundMessage(p.getCellNoLatest(),"Please take your daily medicine"); }
	 * //can remove this synchronized block and use the tarseel api to call
	 * OutboundMessage synchronized (this) { try {
	 * System.out.println("CURRENTLY EXECUTING JOBS::"
	 * +jctx.getScheduler().getCurrentlyExecutingJobs().size());
	 * LoggerUtil.logIt
	 * ("CURRENTLY EXECUTING JOBS::"+jctx.getScheduler().getCurrentlyExecutingJobs
	 * ().size()); } catch (SchedulerException e) { System.out.println(
	 * "error while getting currently executing jobs from scheduler:"
	 * +e.getMessage()); }
	 * 
	 * boolean msgSent=false;
	 * 
	 * msgSent=SendMessage.send(msg,3,50000);
	 * 
	 * if(!msgSent){ xoutFiles.unsentReminder(msg.toString()); }
	 * 
	 * REMINDER_STATUS status;
	 * status=(msgSent)?REMINDER_STATUS.SENT:REMINDER_STATUS.NOT_SENT; // TODO
	 * what to do if msg was not sent save RH record with not delivered flag or
	 * not save at all. ///////////////////////////////////////////////////
	 * saveReminderHistoryRecord(p, r, remNum, REMTXT,status);
	 * ////////////////////////////////////////////////// try{
	 * jctx.getJobDetail().getJobDataMap().remove("rem_num");
	 * jctx.getJobDetail().getJobDataMap().put("rem_num",remNum+1); }catch
	 * (Exception e) { System.out.println("error:"+e.getMessage()); } }
	 */

	private String pickupRandomReminderText(Reminder reminder, int reminderType) {
		String text = "";
		List<ReminderText> rt = new ArrayList<ReminderText>();
		rt.addAll(reminder.getReminderText());

		try {
			int num = reminderType;
			text = rt.get(num).getText();
		} catch (Exception e) {
			text = "Дорухои таъиншударо кабул кунед ва тарики СМСи (ха) та�?дик намоед";

		}
		return text;
	}

	/*
	 * private boolean determineAndHandleJobExecutionStatus(Patient p,Reminder
	 * r){ boolean executeJob=true; boolean systemStable=true;
	 * 
	 * try{ ReminderSystem.getReminderServiceContext().openSession();
	 * sch=ReminderSystem
	 * .getReminderServiceContext().getScheduledTaskService().getScheduledReminder
	 * (p.getPatientRecordNumber(), r.getReminderId()); }catch (Exception e) {
	 * systemStable=false;
	 * 
	 * System.out.println(new Date()+
	 * "A serious error occurred while retrieving scheduled task data from database. check log for details."
	 * );
	 * LoggerUtil.logIt("JOB:"+jctx.getJobDetail().getFullName()+", TRIGGER:"+
	 * jctx.getTrigger().getFullName()+
	 * ":A serious error occurred while retrieving scheduled task data from database.\n"
	 * +ExceptionUtil.getStackTrace(e)); }finally{
	 * ReminderSystem.getReminderServiceContext().closeSession(); }
	 * 
	 * if(systemStable){
	 * if(ReminderSystem.getScheduledReminders().get(reminderSystemJobIndex
	 * ).SYSTEM_FOUND_UNSTABLE_FOR_LAST_RUN){
	 * sch.setResponseRecieved(ReminderSystem
	 * .getScheduledReminders().get(reminderSystemJobIndex).RESPONSE_RECIEVED);
	 * sch.setResponseDate(ReminderSystem.getScheduledReminders().get(
	 * reminderSystemJobIndex).RESPONSE_DATE);
	 * sch.setReminderSent(jctx.getPreviousFireTime());
	 * sch.setReminderNum(jctx.getJobDetail
	 * ().getJobDataMap().getInt("rem_num")); }
	 * 
	 * executeJob=determineExecutionStatusUsingDatabase();
	 * 
	 * try{ ReminderSystem.getReminderServiceContext().openSession();
	 * ReminderSystem
	 * .getReminderServiceContext().getScheduledTaskService().updateScheduledTask
	 * (sch); ReminderSystem.getReminderServiceContext().commitTransaction();
	 * }catch (Exception e) {
	 * System.out.println("error while updating scheduled task :"
	 * +e.getMessage());
	 * LoggerUtil.logIt("error while updating scheduled task :"
	 * +ExceptionUtil.getStackTrace(e)); }finally{
	 * ReminderSystem.getReminderServiceContext().closeSession(); } }else{
	 * ReminderSystem.getScheduledReminders().get(reminderSystemJobIndex).
	 * SYSTEM_FOUND_UNSTABLE_FOR_LAST_RUN=true;
	 * executeJob=determineExecutionStatusUsingJctx(); } return executeJob; }
	 */
	/*
	 * private boolean determineExecutionStatusUsingDatabase(){ boolean
	 * executeJob;
	 * 
	 * if(DateUtils.datesEqual(sch.getReminderSent(), DateUtils.getDate())){
	 * if(sch.getResponseRecieved()){ executeJob=false;
	 * 
	 * ReminderSystem.getScheduledReminders().get(reminderSystemJobIndex).
	 * RESPONSE_RECIEVED=true;
	 * ReminderSystem.getScheduledReminders().get(reminderSystemJobIndex
	 * ).RESPONSE_DATE=sch.getResponseDate(); }else{
	 * remNum=sch.getReminderNum(); sch.setReminderNum(remNum+1);
	 * sch.setReminderSent(DateUtils.getDate()); executeJob=true; } }else{ int
	 * dif=DateUtils.getDifferenceOfHours(sch.getResponseDate(), new Date());
	 * 
	 * if(dif<=0&&dif>=-2){ sch.setReminderSent(new Date()); executeJob=false;
	 * 
	 * ReminderSystem.getScheduledReminders().get(reminderSystemJobIndex).
	 * RESPONSE_RECIEVED=false;
	 * ReminderSystem.getScheduledReminders().get(reminderSystemJobIndex
	 * ).RESPONSE_DATE=sch.getResponseDate(); }else{ remNum=1;
	 * sch.setReminderNum(remNum+1); sch.setReminderSent(DateUtils.getDate());
	 * sch.setResponseRecieved(false); sch.setResponseDate(new Date(75,1,12));
	 * executeJob=true;
	 * 
	 * ReminderSystem.getScheduledReminders().get(reminderSystemJobIndex).
	 * RESPONSE_RECIEVED=false;
	 * ReminderSystem.getScheduledReminders().get(reminderSystemJobIndex
	 * ).RESPONSE_DATE=sch.getResponseDate();
	 * ReminderSystem.getScheduledReminders
	 * ().get(reminderSystemJobIndex).NUM_OF_RESPONSES_TODAY=0; } } return
	 * executeJob; }
	 */
	private boolean determineExecutionStatusUsingJctx() {
		Calendar prev = Calendar.getInstance();
		Calendar now = Calendar.getInstance();
		boolean executeJob;

		try {
			prev.setTime(jctx.getPreviousFireTime());
			if (DateUtils.datesEqual(now.getTime(), prev.getTime())) {
				remNum = jctx.getJobDetail().getJobDataMap().getInt("rem_num");

				if (ReminderSystem.getScheduledReminders().get(
						reminderSystemJobIndex).RESPONSE_RECIEVED) {
					executeJob = false;
				} else {
					executeJob = true;
				}
			} else {
				remNum = 1;
				ReminderSystem.getScheduledReminders().get(
						reminderSystemJobIndex).RESPONSE_RECIEVED = false;
				executeJob = true;
			}
		} catch (Exception ee) {
			remNum = 1;
			ReminderSystem.getScheduledReminders().get(reminderSystemJobIndex).RESPONSE_RECIEVED = false;
			executeJob = true;
		}
		return executeJob;
	}

	private int currentJctxJobIndex(Patient p) {
		for (int i = 0; i < ReminderSystem.getScheduledReminders().size(); i++) {
			if (ReminderSystem.getScheduledReminders().get(i).getPatientId()
					.compareTo(p.getPatientId()) == 0) {
				reminderSystemJobIndex = i;
				return reminderSystemJobIndex;
			}
		}
		return -1;
	}
	/*
	 * private void saveReminderHistoryRecord(Patient p,Reminder r,int
	 * remNum,String REMTXT,REMINDER_STATUS status){
	 * rh.setCellNo(p.getCellNoLatest()); rh.setPatient(p); rh.setReminder(r);
	 * rh.setReminderNumForTheDay(remNum); rh.setReminderText(REMTXT);
	 * rh.setStatusOfReminder(status); rh.setSentDate(DateUtils.getDate());
	 * rh.setSentTime(new Time(Calendar.getInstance().getTimeInMillis()));
	 * 
	 * try{ sc.openSession(); sc.getReminderService().addReminderHistory(rh);
	 * sc.commitTransaction(); }catch (Exception e) {
	 * xoutFiles.UnsavedReminder("---------------------------------------------"
	 * + "\nException message:" +e.getMessage()+ "\n" +rh.toString()+
	 * "\n---------------------------------------------------" );
	 * 
	 * LoggerUtil.logIt("Reminder History Record was not saved.. "+ExceptionUtil.
	 * getStackTrace(e)); String message=
	 * "Reminder History Record was not saved.. \nCheck if database connections are working."
	 * + "\nRem Num:"+rh.getReminderNumForTheDay()+
	 * "\nPatient:"+rh.getPatient().getPatientId()+
	 * "\nReminder:"+rh.getReminder().getName()+ "\nOn Date:"+rh.getSentDate()+
	 * "\n\nCheck log and unsent reminder history record for complete details.";
	 * ReminderSystem
	 * .emailErrorReportToAdmin("Reminder History Record was not saved in database"
	 * , message); }finally{ sc.closeSession(); } }
	 */
}
