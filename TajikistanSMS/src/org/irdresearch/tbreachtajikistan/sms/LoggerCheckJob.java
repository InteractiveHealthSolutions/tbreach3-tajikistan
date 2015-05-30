/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package org.irdresearch.tbreachtajikistan.sms;

import java.util.ArrayList;
import java.util.List;

import org.irdresearch.tbreach2.shared.model.ReminderHistory;
import org.irdresearch.tbreach2.shared.model.ReminderHistory.REMINDER_STATUS;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import org.irdresearch.smstarseel.context.TarseelContext;
import org.irdresearch.smstarseel.context.TarseelServices;
import org.irdresearch.smstarseel.data.OutboundMessage;
import org.irdresearch.smstarseel.data.OutboundMessage.OutboundStatus;
public class LoggerCheckJob implements Job {

	@Override
	public void execute(JobExecutionContext jctx) throws JobExecutionException {
		
		//for checking, reminder sent or not
		OutboundMessage obm;
		String uniqueID;
		TarseelServices ts = TarseelContext.getServices();
		List<ReminderHistory> reminderloggerstatus=new ArrayList<ReminderHistory>();
		HibernateUtil util = new HibernateUtil();
		/*ServiceContext sc=new ServiceContext();*/
		try{
			
		reminderloggerstatus=util.getStatusByReferenceNumber();
		
		for(ReminderHistory rm:reminderloggerstatus){
			if(rm.getReminderStatus().equals("LOGGED")&&rm.getUniqueID()!=null)
			{	
				uniqueID=rm.getUniqueID();
				//System.out.println(uniqueID+"..........");
				obm=ts.getSmsService().findOutboundMessageByReferenceNumber(uniqueID, true);
				if(obm!=null){
				//System.out.println(obm.getReferenceNumber()+"RAF"+obm.getStatus().toString());
				if(!obm.getStatus().equals(OutboundStatus.PENDING) && (!obm.getStatus().equals(OutboundStatus.UNKNOWN)))
				{
					if(obm.getStatus().equals(OutboundStatus.SENT))
					{	
						rm.setStatusOfReminder(REMINDER_STATUS.SENT);
						//System.out.println(rm.getReminderStatus()+"...............................................");
						
						
							util.update(rm);
							/*sc.commitTransaction();*/

					}
					else if(obm.getStatus().equals(OutboundStatus.FAILED))
					{
						rm.setStatusOfReminder(REMINDER_STATUS.NOT_SENT);
						util.update(rm);
						/*sc.commitTransaction();*/
					}
					
					else if(obm.getStatus().equals(OutboundStatus.MISSED))
					{
						rm.setStatusOfReminder(REMINDER_STATUS.NOT_SENT);
						util.update(rm);
						/*sc.commitTransaction();*/
					}
				}
				
				}


			}
			
		}
			
		}
		catch (Exception e) {
			e.printStackTrace();
/*			xoutFiles.UnsavedReminder("---------------------------------------------" +
					"\nException message:" +e.getMessage()+
					"\n" +
					"\n---------------------------------------------------"
			);*/
			
			
		}finally{
			ts.closeSession();
			/*sc.closeSession();*/
		}
		

}
}
