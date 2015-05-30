package org.irdresearch.tbreachtajikistan.sms;

import java.text.ParseException;
import java.util.Date;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;

public class MonthlyPatientReminder
{
 
	private CronTrigger monthlyreminder_trigger;
	private JobDetail monthlyreminder_job;
	private static Scheduler sched;
	public static String triggerName="monthlyReminderJobTrigger";
	public static String triggerGroup="monthlyReminderJob";
	public static String jobName="MonthlyReminder_job";
	public static String jobGroup="monthlyreminder";
	
	public MonthlyPatientReminder(Scheduler schedu) throws ParseException{
		sched=schedu;
		monthlyreminder_job=new JobDetail(jobName, jobGroup	,MonthlyPatientReminderJob.class);
		
		makeRescheduleTrigger();
		
		try{
			sched.addJob(monthlyreminder_job, true);
		}catch (Exception e) {
				//LoggerUtil.logIt("exception while adding rescheduler reminder job:"+ExceptionUtil.getStackTrace(e));
		}
		
		try{
			sched.scheduleJob(monthlyreminder_trigger);
		}catch (Exception e) {
			try{
				sched.deleteJob(jobName,jobGroup);
				sched.addJob(monthlyreminder_job, true);
				sched.scheduleJob(monthlyreminder_trigger);
			}catch (Exception ee) {
				//System.out.println("Error while updating trigger"+ee.getMessage());
				//LoggerUtil.logIt("Error while updating trigger"+ExceptionUtil.getStackTrace(ee));
			}
		}
	}
	
	private void makeRescheduleTrigger() throws ParseException{
		//trigger fire at 6:5:0 am daily
		Date prev=null;
		
		try {//if one previously exits then start time equal to it
			prev=sched.getTrigger(triggerName, triggerGroup).getPreviousFireTime();
		} catch (Exception e) {
		}
		monthlyreminder_trigger=new CronTrigger(triggerName,triggerGroup,jobName,jobGroup,getRecheduleTriggerExpression());
		monthlyreminder_trigger.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_FIRE_ONCE_NOW);
		if(prev!=null){
			monthlyreminder_trigger.setStartTime(prev);
		}
	}
	public static String getRecheduleTriggerExpression(){
		String cronExp;
		
	//TODO: time!
		try{
			String hour="9";
			String min="0";
			String sec="0";
			/*
			if(!Utils.isNumberBetween(hour, 0, 23) ){
				hour="4";
			}
			if(!Utils.isNumberBetween(min, 0, 59)){
				min="5";
			}
			if(!Utils.isNumberBetween(sec, 0, 59)){
				sec="0";
			}*/
			cronExp=sec+" "+min+" "+hour+ " * * ?";
		}catch (Exception e) {
			cronExp="0"+" "+"5"+" "+"5"+ " * * ?";
		}
		return cronExp;
	}
	
}
