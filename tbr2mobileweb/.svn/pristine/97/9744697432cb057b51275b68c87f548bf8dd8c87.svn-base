package org.irdresearch.tbreach2.server;


/*import org.irdresearch.smstarseel.context.TarseelContext;
import org.irdresearch.smstarseel.context.TarseelServices;
import org.irdresearch.smstarseel.data.OutboundMessage.PeriodType;
import org.irdresearch.smstarseel.data.OutboundMessage.Priority;*/
/*import org.irdresearch.tbreach2.shared.model.Encounter;
import org.irdresearch.tbreach2.shared.model.EncounterResults;
import org.irdresearch.tbreach2.shared.model.Feedback;*/
import java.util.Date;

import org.irdresearch.smstarseel.context.TarseelContext;
import org.irdresearch.smstarseel.context.TarseelServices;
import org.irdresearch.smstarseel.data.OutboundMessage.PeriodType;
import org.irdresearch.smstarseel.data.OutboundMessage.Priority;
import org.irdresearch.tbreach2.shared.model.GeneXpertResults;
import org.irdresearch.tbreach2.shared.model.PatientDetails;
import org.irdresearch.tbreach2.shared.model.Users;

/*import org.irdresearch.tbreach2.shared.model.Screening;
import org.irdresearch.tbreach2.shared.model.Sms;
import org.irdresearch.tbreach2.shared.model.SputumResults;*/


/**
 * @author owais.hussain@irdresearch.org
 *
 */
public class SMSUtil
{
	private static final String	status	= "PENDING";
	public static SMSUtil		util	= new SMSUtil();

	private String getMobileNumber(String PID)
	{
		if (PID.equals(""))
			return "";
		return HibernateUtil.util.selectObject("select Mobile from Contact where PID='" + PID + "'").toString();
	}

	/*public void sendGenericSMSAlert(String targetNumber, String messageText)
	{
		Sms sms = new Sms(targetNumber, messageText, new Date(), null, status, null, null);
		if (!sms.getTargetNumber().equals(""))
			HibernateUtil.util.save(sms);
	}

	*//**
	 * Send alerts to Monitor on Baseline Treatment
	 * @param encounter
	 */
	/*public void sendAlertsOnBaselineSubmission(Encounter encounter)
	{
		String targetNumber = "";
		String messageText = "";
		// Send alert to Monitor
		String chwId = HibernateUtil.util.selectObject("select CHWID from Patient where PatientID='" + encounter.getId().getPid1() + "'").toString();
		String monitorId = HibernateUtil.util.selectObject("select MonitorID from Worker where WorkerID='" + chwId + "'").toString();
		targetNumber = getMobileNumber(monitorId);
		messageText = "Dear Monitor! Baseline Treatment form for Patient " + encounter.getId().getPid1() + " has been filled by "
				+ encounter.getId().getPid2();
		sendGenericSMSAlert(targetNumber, messageText);
	}

	*//**
	 * Send alerts to CHW and Monitor if TB is Diagnosed
	 * @param encounter
	 *//*
	public void sendAlertsOnClinicalDiagnosis(Encounter encounter)
	{
		String targetNumber = "";
		String messageText = "";
		EncounterResults results = (EncounterResults) HibernateUtil.util.findObject("from EncounterResults where EncounterID = "
				+ encounter.getId().getEncounterId() + "AND PID1='" + encounter.getId().getPid1() + "' AND PID2= '" + encounter.getId().getPid2()
				+ "' AND Element='DIAGNOSIS'");
		if (!results.getValue().contains("OTHER"))
		{
			// Send alert to CHW
			String chwID = HibernateUtil.util.selectObject("select CHWID from Patient where PatientID='" + encounter.getId().getPid1() + "'")
					.toString();
			targetNumber = getMobileNumber(chwID);
			messageText = "Dear Health Worker! A confirmed TB Patient has been identified through Clinical Diagnosis. ID assigned: "
					+ encounter.getId().getPid1() + results.getValue();
			sendGenericSMSAlert(targetNumber, messageText);
			// Send alert to Monitor
			String monitorID = HibernateUtil.util.selectObject("select MonitorID from Worker where WorkerID='" + chwID + "'").toString();
			targetNumber = getMobileNumber(monitorID);
			messageText = "Dear Monitor! A confirmed TB Patient has been identified through Clinical Diagnosis. ID assigned: "
					+ encounter.getId().getPid1() + results.getValue();
			sendGenericSMSAlert(targetNumber, messageText);
		}
	}

	*//**
	 * Send alerts to CHW and Monitor on End of Follow-up with outcome "Treatment Completed" or "Cured"
	 * @param encounter
	 *//*
	public void sendAlertsOnEndOfFollowup(Encounter encounter)
	{
		String targetNumber = "";
		String messageText = "";
		EncounterResults results = (EncounterResults) HibernateUtil.util.findObject("from EncounterResults where EncounterID = "
				+ encounter.getId().getEncounterId() + "AND PID1='" + encounter.getId().getPid1() + "' AND PID2= '" + encounter.getId().getPid2()
				+ "' AND Element='REASON'");
		if (results.getValue().equals("CURED") || results.getValue().equals("TX COMPLETED"))
		{
			// Send alert to CHW
			String chwId = HibernateUtil.util.selectObject("select CHWID from Patient where PatientID='" + encounter.getId().getPid1() + "'")
					.toString();
			targetNumber = getMobileNumber(chwId);
			messageText = "Dear Health Worker! Follow-ups on Patient " + encounter.getId().getPid1() + " have been completed with final outcome "
					+ results.getValue();
			sendGenericSMSAlert(targetNumber, messageText);
			// Send alert to Monitor
			String monitorId = HibernateUtil.util.selectObject("select MonitorID from Worker where WorkerID='" + chwId + "'").toString();
			targetNumber = getMobileNumber(monitorId);
			messageText = "Dear Monitor! Follow-ups on Patient " + encounter.getId().getPid1() + " have been completed with final outcome "
					+ results.getValue();
			sendGenericSMSAlert(targetNumber, messageText);
		}
	}

	*//**
	 * Send alerts to Technical correspondents on feedback
	 * @param feedback
	 *//*
	public void sendAlertsOnFeedback(Feedback feedback)
	{
		if (feedback.getFeedbackType().equalsIgnoreCase("Error/Bug"))
		{
			sendGenericSMSAlert("03453174270", feedback.toString());
		}
	}

	*//**
	 * Send alerts to CHW and Monitor on Gene Xpert Results
	 * @param results
	 *//*
	public void sendAlertsOnGXPResults(GeneXpertResults results)
	{
		String targetNumber = "";
		String messageText = "";
		if (results.getIsPositive())
		{
			// Send alert to CHW
			String chwId = HibernateUtil.util.selectObject("select CHWID from Patient where PatientID='" + results.getPatientId() + "'").toString();
			targetNumber = getMobileNumber(chwId);
			messageText = "Dear Health Worker! Gene Xpert Result for ID " + results.getPatientId() + " was found Positive.";
			sendGenericSMSAlert(targetNumber, messageText);
			// Send alert to Monitor
			String monitorId = HibernateUtil.util.selectObject("select MonitorID from Worker where WorkerID='" + chwId + "'").toString();
			targetNumber = getMobileNumber(monitorId);
			messageText = "Dear Monitor! Gene Xpert Result for ID " + results.getPatientId() + " was found Positive.";
			sendGenericSMSAlert(targetNumber, messageText);
		}
	}

	*//**
	 * Send alerts to CHW and Monitor on TB Suspect find
	 * @param screening
	 *//*
	public void sendAlertsOnScreening(Screening screening)
	{
		String targetNumber = "";
		String messageText = "";
		if (screening.getSuspect())
		{
			// Send alert to CHW
			targetNumber = getMobileNumber(screening.getChwid());
			messageText = "Dear Health Worker! A suspect has been identified. ID assigned: " + screening.getPatientId();
			sendGenericSMSAlert(targetNumber, messageText);
			// Send alert to respective Monitor
			String monitorId = HibernateUtil.util.selectObject("select MonitorID from Worker where WorkerID='" + screening.getChwid() + "'")
					.toString();
			targetNumber = getMobileNumber(monitorId);
			messageText = "Dear Monitor! A suspect has been identified by " + screening.getChwid() + ". ID assigned: " + screening.getPatientId();
			sendGenericSMSAlert(targetNumber, messageText);
		}
	}

	*//**
	 * Send alerts to CHW and Monitor on Sputum Results.
	 * @param results
	 *//*
	public void sendAlertsOnSmearResults(SputumResults results)
	{
		String targetNumber = "";
		String messageText = "";
		if (results.getMonth() != 0 || results.getSmearResult() == null)
			return;
		if (results.getSmearResult().equals("1+") || results.getSmearResult().equals("2+") || results.getSmearResult().equals("3+")
				|| results.getSmearResult().equals("1-9AFB"))
		{
			// Send alert to CHW
			String chwId = HibernateUtil.util.selectObject("select CHWID from Patient where PatientID='" + results.getPatientId() + "'").toString();
			targetNumber = getMobileNumber(chwId);
			messageText = "Dear Health Worker! Suspect " + results.getPatientId() + " was found Smear Positive case.";
			sendGenericSMSAlert(targetNumber, messageText);
			// Send alert to Monitor
			String monitorId = HibernateUtil.util.selectObject("select MonitorID from Worker where WorkerID='" + chwId + "'").toString();
			targetNumber = getMobileNumber(monitorId);
			messageText = "Dear Monitor! Suspect " + results.getPatientId() + " was found Smear Positive case.";
			sendGenericSMSAlert(targetNumber, messageText);
			// If and only if an Indus Patient is found positive then send this additional text message to 03468227801
			String gpId = HibernateUtil.util.selectObject("select GPID from Patient where PatientID='" + results.getPatientId() + "'").toString();
			if (gpId.equals("G-INDUS-00"))
			{
				targetNumber = "03468227801";
				messageText = "Dear Monitor! Suspect " + results.getPatientId() + " was found Smear Positive case.";
				sendGenericSMSAlert(targetNumber, messageText);
			}
		}
	}*/

	/**
	 * Send alerts to CHW and Monitor on X-Ray Results
	 * @param results
	 */
	/*public void sendAlertsOnXRayResults(XrayResults results)
	{
		String targetNumber = "";
		String messageText = "";
		if (results.getXrayResults().equals("SUGGESTIVE OF TB") || results.getXrayResults().equals("SUSPICIOUS OF TB"))
		{
			// Send alert to CHW
			String chwId = HibernateUtil.util.selectObject("select CHWID from Patient where PatientID='" + results.getId().getPatientId() + "'")
					.toString();
			targetNumber = getMobileNumber(chwId);
			messageText = "Dear Health Worker! X-Ray results of Suspect " + results.getId().getPatientId() + " were found "
					+ results.getXrayResults();
			sendGenericSMSAlert(targetNumber, messageText);
			// Send alert to Monitor
			String monitorId = HibernateUtil.util.selectObject("select MonitorID from Worker where WorkerID='" + chwId + "'").toString();
			targetNumber = getMobileNumber(monitorId);
			messageText = "Dear Monitor! X-Ray results of Suspect " + results.getId().getPatientId() + " were found " + results.getXrayResults();
			sendGenericSMSAlert(targetNumber, messageText);
		}
	}*/
	
	public void sendAlertsOnAutoGXPResults(GeneXpertResults results, boolean isPositive)
	{
		TarseelServices services = TarseelContext.getServices ();
		ServerServiceImpl ssl = new ServerServiceImpl();
		
		String patientNumber = null;
		String tbSpecalistNumber = null;
		String patientMessage = null;
		String tbSpecalistMessage = null;
		String messageHeader = null;
		
		try{
	  	
			String pid = results.getPatientId();
			messageHeader = "**Automated GeneXpert Result Message for Patient " + results.getPatientId() + "**\n\n";
			
			try {
				long pno = ssl.count ("patientdetails", "where pid='" + pid + "'" );
				if (pno != 0){
				    PatientDetails pd = ssl.findPatientByPatientID(pid);
					
					patientNumber = pd.getPhone1();
					
					patientMessage = messageHeader +
					"Your result is ready. Please pick up from the lab at your earliest convenience";
					
					services.getSmsService ().createNewOutboundSms (patientNumber, patientMessage, new Date (), Priority.HIGHEST, 24, PeriodType.DAY, 1, null);
					
					if(isPositive){
						String healthWorkerId = pd.getHealthWorkerID();
						Users user = new Users ();
						user = ssl.findUser(healthWorkerId);
						tbSpecalistNumber = user.getPhoneNo ();
						
						tbSpecalistMessage = messageHeader +
						"Result: " + results.getGeneXpertResult() + ( (results.getMtbBurden()==null) ? "" : "\nMTB Burden: " + results.getMtbBurden()) + (( results.getDrugResistance()==null) ? "" : "\nRif Resistance: " + results.getDrugResistance());
						
						services.getSmsService ().createNewOutboundSms (tbSpecalistNumber, tbSpecalistMessage, new Date (), Priority.HIGHEST, 24, PeriodType.DAY, 1, null);
					}
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		finally{
			services.commitTransaction();
			services.closeSession();
		}
	}
}
