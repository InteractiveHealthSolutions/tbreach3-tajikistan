package org.irdresearch.tbreachtajikistan.sms;

//import ird.xoutTB.reporting.ExceptionUtil;

import java.text.ParseException;
import java.util.Date;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;

import org.quartz.Scheduler;

public class RescheduleReminders {
	
	private CronTrigger reschedule_trigger;
	private JobDetail reschedule_job;
	private static Scheduler sched;
	public static String triggerName="rescheduleTrigger";
	public static String triggerGroup="reschedule";
	public static String jobName="reschedule_job";
	public static String jobGroup="reschedule";
	
	public RescheduleReminders(Scheduler schedu) throws ParseException{
		sched=schedu;
		reschedule_job=new JobDetail(jobName, jobGroup	,RescheduleRemindersJob.class);
		
		makeRescheduleTrigger();
		
		try{
			sched.addJob(reschedule_job, true);
		}catch (Exception e) {
				//LoggerUtil.logIt("exception while adding rescheduler reminder job:"+ExceptionUtil.getStackTrace(e));
		}
		
		try{
			sched.scheduleJob(reschedule_trigger);
		}catch (Exception e) {
			try{
				sched.deleteJob(jobName,jobGroup);
				sched.addJob(reschedule_job, true);
				sched.scheduleJob(reschedule_trigger);
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
		reschedule_trigger=new CronTrigger(triggerName,triggerGroup,jobName,jobGroup,getRecheduleTriggerExpression());
		reschedule_trigger.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_FIRE_ONCE_NOW);
		if(prev!=null){
			reschedule_trigger.setStartTime(prev);
		}
	}
	public static String getRecheduleTriggerExpression(){
		String cronExp;
		
	//TODO: time!
		try{
			String hour="23";
			String min="30";
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
