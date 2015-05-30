/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
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
