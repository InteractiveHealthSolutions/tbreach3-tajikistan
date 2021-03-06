/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package org.irdresearch.tbreachtajikistan.sms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*import ird.xoutTB.context.Context;
import ird.xoutTB.context.ServiceContext;*/
import org.irdresearch.tbreach2.shared.model.PatientDetails;
import org.irdresearch.tbreach2.shared.model.PatientReminder;
import org.irdresearch.tbreach2.shared.model.PatientResponse;
import org.irdresearch.tbreach2.shared.model.PatientResponse.RESPONSE_TYPES;

import org.irdresearch.smstarseel.context.TarseelContext;
import org.irdresearch.smstarseel.context.TarseelServices;
import org.irdresearch.smstarseel.data.CallLog;
import org.irdresearch.smstarseel.data.InboundMessage;
import org.irdresearch.smstarseel.data.InboundMessage.InboundStatus;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/*import ird.xoutTB.response.verification.VerifyResponse;*/
import org.irdresearch.tbreachtajikistan.utils.DateUtils;
/*import ird.xoutTB.utils.xoutFiles;*/
public class StatusCheckJob implements Job {
	
	int responseCount;
	int callResponseCount;
	@Override
	public void execute(JobExecutionContext jctx) throws JobExecutionException {
		/*ServiceContext sc=new ServiceContext();*/
		int p_id=0;
		int projectid=1;
		HibernateUtil util = new HibernateUtil();
		Boolean smsstats=null;
		List<InboundMessage> inboundCount=new ArrayList<InboundMessage>();
		List<InboundMessage> inboundmessage=new ArrayList<InboundMessage>();
		/*List<CallLog> calllogCount=new ArrayList<CallLog>();*/
		TarseelServices ts = TarseelContext.getServices();
		/*List<CallLog> calllog = new ArrayList<CallLog>();*/
		List<PatientReminder> pReminder=new ArrayList<PatientReminder>();
		int validity_Period=2;
		//u r finding all the messages for a particular id, if 100000 message then it is
		//going to loop through all the messages, so just loop through Inbounstaus.unread, same for call
		try{
			//System.out.println("Getting till here............");
			inboundmessage=ts.getSmsService().findInbound(null, null, InboundStatus.UNREAD, null, null, null, null, false);
			
			/*xoutFiles.recievedResponse(inboundmessage.toString());*/
			
			for(InboundMessage im:inboundmessage)
			{
				String daybgn=DateUtils.truncateDate(new Date());
				String dayend=DateUtils.roundoffDate(new Date());
				Date datesmal=DateUtils.convertToDate(daybgn);
	        	Date daybg=DateUtils.convertToDate(dayend);
				inboundCount=ts.getSmsService().findInbound(datesmal, daybg, null,null, im.getOriginator(), null, null, false);
				
				//System.out.println(inboundCount.size()+"......... "+im.getOriginator());
				im.setStatus(InboundStatus.READ);
				//System.out.println(im.getStatus()+"This is the Inbound status!!....");
				
				ts.getSmsService().updateInbound(im);
				ts.commitTransaction();
				if(!im.getRecieveDate().before(new Date()) || (im.getRecieveDate().after(datesmal) && im.getRecieveDate().before(daybg))){
				//Patient p=VerifyResponse.verifyResponseNumber(im.getOriginator());
				String phoneNumber = im.getOriginator();
				String verifiedNumber = phoneNumber.substring(4, phoneNumber.length());
				PatientDetails p = util.getPatientHavingCellNumber(verifiedNumber);	
				if(p!=null){
				pReminder=util.gettimeofReminder(Integer.parseInt(p.getPid()));
				PatientReminder pre=pReminder.get(0);
				p_id=pre.getReminder().getReminderId();
				
				boolean b=alertResponse(inboundCount);
				
					if(isValidResponse(im)){
						
						//System.out.println(im.getOriginator()+"......HreI IS......."+inboundCount);
						
						
						
						
						
						//System.out.println(p.getPatientRecordNumber()+"HHHHHHHHHHHHHHH.........."+p_id);
						PatientResponse pr=new PatientResponse();
						
						pr.setCellNo(im.getOriginator());
						pr.setRecieveDate(im.getRecieveDate());
						pr.setResponseText(im.getText());
						 
						pr.setPatientResponseType(RESPONSE_TYPES.VERIFIED);
						
						
						pr.setReminderId(pre.getReminder().getReminderId());
						pr.setPatientDetails(p);
						pr.setUniqueID(im.getReferenceNumber());
						util.addResponseRecord(pr);
						
						
/*						if(b){
						String txt="";
						if(p_id==1)
						{
						try {
							txt = "Your response has been received";
						} catch (Exception e) {
							e.printStackTrace();
						}
						}

						ts.getSmsService().createNewOutboundSms(im.getOriginator(), txt, new Date(), Priority.MEDIUM, validity_Period, PeriodType.HOUR, 1, null);
						ts.commitTransaction();
						}*/
					}else{
						//get boolean for Dirty SMS
						
						PatientResponse pr=new PatientResponse();
						
						pr.setCellNo(im.getOriginator());
						pr.setRecieveDate(im.getRecieveDate());
						pr.setResponseText(im.getText());
						 
						pr.setPatientResponseType(RESPONSE_TYPES.DIRTY);
						
						
						pr.setReminderId(p_id);
						pr.setPatientDetails(p);
						pr.setUniqueID(im.getReferenceNumber());
						util.addResponseRecord(pr);
						/*sc.commitTransaction();*/
						
/*						if(b){
						String txt="";
						if(p_id==1){
							try{
								txt="The response text is too long";
							}catch (Exception e) {
								e.printStackTrace();
							}
						}
						else{
							try{
								txt=Context.getIRSetting("plain.urdu-invalid-response-text","invalid response");
							}catch (Exception e) {
								e.printStackTrace();
							}
						}
							ts.getSmsService().createNewOutboundSms(im.getOriginator(), txt, new Date(), Priority.MEDIUM, validity_Period, PeriodType.HOUR, 1, null);
							ts.commitTransaction();
					}*/						
					}
				}
			}
				

				
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		
	}
	
	public boolean alertResponseCall(List<CallLog> callCount){
		int call_responses_today=0;
		callResponseCount= callCount.size();
		//System.out.println(callResponseCount+" This is the call count today");
		call_responses_today=callResponseCount;
		//System.out.println(call_responses_today+" Number of responses today of this number.......");
		if(call_responses_today>3)
		{
			return false;
		}
		return true;
	}
	
	public boolean alertResponse(List<InboundMessage> ibm){
		int responses_today = 0;
		responseCount=ibm.size();
		//System.out.println(responseCount+" Count");
		responses_today=responseCount;
		//System.out.println(responses_today+" Number of responses today of this number.......");
		
		if(responses_today>3)
		{
			return false;
		}
		
		return true;
		
		
	}
	public static boolean isValidResponse(InboundMessage msg){
		if(msg.getText()==null
				|| (msg.getText().trim()).compareTo("")==0 
				|| msg.getText().length()>MAX_RESPONSE_LENGTH){
			return false;
		}
		return true;
	}
	private static int MAX_CELL_NUMBER_MATCH_LENGTH=10;
	
	private static int MAX_RESPONSE_LENGTH=150;
}
