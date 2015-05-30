/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
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
