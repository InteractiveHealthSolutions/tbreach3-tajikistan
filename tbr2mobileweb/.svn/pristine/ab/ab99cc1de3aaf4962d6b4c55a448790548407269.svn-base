package org.irdresearch.tbreach2.shared.model;

import java.sql.Time;
import java.util.Date;

public class PatientResponse {

	public enum RESPONSE_TYPES{
		DIRTY,
		VERIFIED,
		M_CALL
	}
	private long recordNum;
	private String cellNo;
	private Date recieveDate;
	private Time recieveTime;
	private String responseText;
	private String responseType=PatientResponse.RESPONSE_TYPES.VERIFIED.toString();
	private int reminderId;
	private String reminderText;//not to be used yet
	private PatientDetails patientDetails;
	private String uniqueID;
	
	public long getRecordNum()
	{
		return recordNum;
	}
	public String getCellNo() {
		return cellNo;
	}
	public Date getRecieveDate(){
		return recieveDate;
	}
	public Time getRecieveTime() {
		return recieveTime;
	}
	public String getResponseText() {
		return responseText;
	}
	public String getResponseType() {
		return responseType;
	}
	public int getReminderId() {
		return reminderId;
	}
	public String getReminderText() {
		return reminderText;
	}
	public PatientDetails getPatientDetails(){
		return patientDetails;
	}
	public void  setRecordNum(long rec) {
		recordNum=rec;
	}
	public void setCellNo(String string) {
		cellNo=string;
	}
	public void setRecieveDate(Date date){
		recieveDate=date;
	}
	public void  setRecieveTime(Time time) {
		recieveTime=time;
	}
	public void setResponseText(String string) {
			responseText=string;
	}
	void setResponseType(String string) {
		responseType=string;
	}
	public void setPatientResponseType(RESPONSE_TYPES resp) {
		responseType=resp.toString();
	}
	public void setReminderId(int rId){
		reminderId=rId;
	}
	public void setReminderText(String string){
			reminderText=string;
	}
	public void setPatientDetails(PatientDetails patDetails){
		patientDetails=patDetails;
	}
/*	@Override
	public String toString() {
		StringBuilder sb=new  StringBuilder();
		
		sb.append("Cell num:"+getCellNo());
		sb.append("\nRecieve Date:"+getRecieveDate());
		sb.append("\nRecieve Time:"+getRecieveTime());
		sb.append("\nResponse Text:"+getResponseText());
		sb.append("\nResponse Type:"+getResponseType());
		sb.append("\npatientRecordNumber:"+getPatient().getPatientRecordNumber());
		sb.append("\nPatient ID:"+getPatient().getPatientId());
	return sb.toString();
	}*/
	public String getUniqueID() {
		return uniqueID;
	}
	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}
}
