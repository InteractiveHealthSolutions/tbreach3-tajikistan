package org.irdresearch.tbreachtajikistan.sms;

import java.text.ParseException;
import java.util.Date;

import org.irdresearch.tbreach2.shared.model.PatientReminder;

import org.irdresearch.tbreach2.shared.model.PatientDetails;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;

public class ScheduledJob {

	public enum ResponseType{
		CALL,
		SMS,
		NOT_AVAILABLE
	}
	public boolean RESPONSE_RECIEVED=false;
	public Date RESPONSE_DATE=new Date(75,1,12);
	public int NUM_OF_RESPONSES_TODAY=0;
	public boolean SYSTEM_FOUND_UNSTABLE_FOR_LAST_RUN=false;
	public ResponseType RESPONSE_TYPE=ResponseType.NOT_AVAILABLE;
	private CronTrigger reminder_trigger;
	private CronTrigger reminder_trigger_logger;
	private JobDetail reminder_job;
	private PatientDetails patient_details;
	private String trigname;
	private String triggp;
	private String jobname;
	private String jobgp;
	private String patientId;
	private int reminderId;
	private String trigname2="loggerTrigger";
	private JobDetail loggerjobdetail;
	private PatientReminder patient_reminder;
	
	
	public JobDetail getLoggerjobdetail() {
		return loggerjobdetail;
	}
	public void setLoggerjobdetail(JobDetail loggerjobdetail) {
		this.loggerjobdetail = loggerjobdetail;
	}
	public String getTrigname2() {
		return trigname2;
	}
	public void setTrigname2(String trigname2) {
		this.trigname2 = trigname2;
	}
	public CronTrigger getReminder_trigger_logger() {
		return reminder_trigger_logger;
	}
	public void setReminder_trigger_logger(CronTrigger reminder_trigger_logger) {
		this.reminder_trigger_logger = reminder_trigger_logger;
	}
	
	public int getReminderId(){
		return reminderId;
	}
	public String getPatientId(){
		return patientId;
	}
	public CronTrigger getTrigger(){
		return reminder_trigger; 
	}
	public JobDetail getJob(){
		return reminder_job;
	}
	public String getTriggerName(){
		return trigname; 
	}
	public String getTriggerGroup(){
		return triggp; 
	}
	public String getJobName(){
		return jobname; 
	}
	public String getJobGroup(){
		return jobgp; 
	}
	public ScheduledJob(){}
	public ScheduledJob(PatientReminder pr) throws ParseException{
		this.patient_reminder=pr;
		patientId=patient_reminder.getPatientDetails().getPid();
		reminderId=patient_reminder.getReminder().getReminderId();
		trigname="rem_trig_"+patient_reminder.getPatientDetails().getPid()+"_"+
							+patient_reminder.getReminder().getReminderId();
		triggp="reminder";
		jobname="rem_job_"+patient_reminder.getPatientDetails().getPid()+"_"+
						  +patient_reminder.getReminder().getReminderId();
		jobgp="reminder";
		
/*		patientId=patient_details.getPid();
		
		trigname="rem_trig_"+patient_details.getPid();
		triggp="reminder";
		jobname="rem_job_"+patient_details.getPid();
		jobgp="reminder";*/
		
		makeJobTrigger();
		makeJob();
		
	}
	
	private void makeJobTrigger() throws ParseException{
		String c=getCronExpression();
		reminder_trigger=new CronTrigger(trigname, triggp, jobname, jobgp, c);
		reminder_trigger.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_FIRE_ONCE_NOW);
		
		
	}

	public String getCronExpression(){
		String c=Integer.toString(patient_reminder.getTimeSecond());
		c=c+" "+Integer.toString(patient_reminder.getTimeMinute());
		c=c+" "+Integer.toString(patient_reminder.getTimeHour());//5 6 9
		int a=patient_reminder.getTimeHour()+patient_reminder.getGapInHours();
		
		//a=9+2=11 2is gap in hr
		for (int i = 1; i < patient_reminder.getIterations(); i++) {
			c=c+","+Integer.toString(a);//9,11  i1 i2 9,11,13 i3 not exe
			a=a+patient_reminder.getGapInHours();
		}
		
		c=c+" "+"? * MON-SAT";
		return c;
		
		/*String c=Integer.toString(patient_reminder.getTimeSecond());
		c=c+" "+Integer.toString(patient_reminder.getTimeMinute());//5 6 9
		int a=patient_reminder.getTimeMinute()+patient_reminder.getGapInHours();
		
		//a=9+2=11 2is gap in hr
		for (int i = 1; i < patient_reminder.getIterations(); i++) {
			c=c+","+Integer.toString(a);//9,11  i1 i2 9,11,13 i3 not exe
			a=a+patient_reminder.getGapInHours();
		}
		c=c+" "+Integer.toString(patient_reminder.getTimeHour());

		c=c+" "+"* * ?";
		return c;*/
	}
	
	private void makeJob(){
		int num=1;
		reminder_job=new JobDetail(jobname, jobgp, SendReminderJob.class);
		reminder_job.getJobDataMap().put("patient", patient_reminder.getPatientDetails());
		reminder_job.getJobDataMap().put("reminder", patient_reminder.getReminder());
		reminder_job.getJobDataMap().put("rem_num", num);
		
	}

}
