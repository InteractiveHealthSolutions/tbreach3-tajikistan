/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package org.irdresearch.tbreachtajikistan.sms;

import org.irdresearch.tbreachtajikistan.utils.LoggerUtil;
import org.irdresearch.tbreachtajikistan.utils.Utils;


import java.text.ParseException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;

public class RescheduleRemindersJob implements Job{

	static void reScheduleReminders() throws ParseException{
		try
		{
			ReminderSystem.rescheduleReminders();
		}
		catch (SchedulerException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void execute(JobExecutionContext jctx) throws JobExecutionException {
		try {
			LoggerUtil.logIt(Utils.getJVMInfo());
		//	LoggerUtil.logIt(Utils.getSMSServerInfo(ReminderSystem.getServer()));
			LoggerUtil.logIt("\n.....RESCHEDULING REMINDERS .....");
			
			reScheduleReminders();
		} catch (Exception e) {
			//LoggerUtil.logIt("An error occurred while rescheduling reminders. \nstack trace is: "+ExceptionUtil.getStackTrace(e));
			//ReminderSystem.emailErrorReportToAdmin("Error occurred while rescheduling reminders", "An error occurred while rescheduling reminders. \nError message is: "+e.getMessage());
		}
	}
}
