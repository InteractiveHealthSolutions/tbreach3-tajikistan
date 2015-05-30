/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
/**
 * Provides scheduling features (uses quartz scheduler)
 */

package org.irdresearch.tbreach2.server;

import java.util.Date;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;

public class SchedulerUtil implements Job
{
	private SchedulerFactory	schedulerFactory;
	private Scheduler			scheduler;
	private JobDetail			jobDetail;
	private Trigger				trigger;
	private String				jobName;
	private String				groupName;

	public SchedulerUtil ()
	{
		try
		{
			schedulerFactory = new StdSchedulerFactory ();
			scheduler = schedulerFactory.getScheduler ();
			trigger = TriggerUtils.makeMinutelyTrigger ();
			trigger.setStartTime (TriggerUtils.getEvenMinuteDate (new Date ()));
			trigger.setJobName (jobName);
			trigger.setGroup (groupName);
			scheduler.scheduleJob (jobDetail, trigger);
			startJobSchedule ();
		}
		catch (SchedulerException e)
		{
			e.printStackTrace ();
		}
	}

	public void startJobSchedule ()
	{
		try
		{
			if (scheduler.isShutdown ())
				scheduler.start ();
			else
				throw new SchedulerException (scheduler.getSchedulerName () + " is already running.");
		}
		catch (SchedulerException e)
		{
			e.printStackTrace ();
		}
	}

	public void shutdownJobSchedule ()
	{
		try
		{
			if (scheduler.isStarted ())
				scheduler.shutdown ();
			else
				throw new SchedulerException (scheduler.getSchedulerName () + " is not running.");
		}
		catch (SchedulerException e)
		{
			e.printStackTrace ();
		}
	}

	@Override
	public void execute (JobExecutionContext context) throws JobExecutionException
	{
		// Not implemented
	}
}
