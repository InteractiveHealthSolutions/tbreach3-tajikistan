/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package org.irdresearch.tbreach2.shared.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class ReminderHistory implements Serializable{
	private static final long serialVersionUID = -6332273676653695876L;

	public enum REMINDER_STATUS{
		SENT,
		NOT_SENT,
		LOGGED,
		PENDING,
		UNKNOWN
	}
	private long recordNum;
	private String cellNo;
	private Date sentDate;
	private Time sentTime;
	private Reminder reminder;
	private PatientDetails patientDetails;
	private int reminderNumForTheDay;
	private String reminderStatus;
	private String reminderText;
	private String uniqueID;
	public String getUniqueID() {
		return uniqueID;
	}
	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}
	public long getRecordNum() {
		return recordNum;
	}
	public String getCellNo()
	{
		return cellNo;
	}
	public Date getSentDate(){
		return sentDate;
	}
	public Time getSentTime(){
		return sentTime;
	}
	public Reminder getReminder(){
		return reminder;
	}
	public PatientDetails getpatientDetails(){
		return patientDetails;
	}
	public int getReminderNumForTheDay()
	{
		return reminderNumForTheDay;
	}
	public String getReminderStatus()
	{
		return reminderStatus;
	}
	public void setRecordNum(long recordNum){
		this.recordNum=recordNum;
	}
	public void setCellNo(String string)
	{
		cellNo=string;
	}
	public void setSentDate(Date date){
		sentDate=date;
	}
	public void setSentTime(Time time){
		sentTime=time;
	}
	public void setReminder(Reminder rem){
		reminder=rem;
	}
	public void setPatientDetails(PatientDetails patientDetails){
		this.patientDetails=patientDetails;
	}
	public void setReminderNumForTheDay(int reminderNum)
	{
		reminderNumForTheDay=reminderNum;
	}
	void setReminderStatus(String string)
	{
			reminderStatus=string;
	}
	public void setStatusOfReminder(REMINDER_STATUS status){
		reminderStatus=status.toString();
	}
/*	@Override
	public String toString() {
		StringBuilder sb=new  StringBuilder();
		
		sb.append("\nCell num:"+getCellNo());
		sb.append("\nSent Date:"+getSentDate());
		sb.append("\nSent Time:"+getSentTime());
		sb.append("\nReminder id:"+getReminder().getReminderId());
		sb.append("\nReminder Text:"+getReminderText());
		sb.append("\nReminder Status:"+getReminderStatus());
		sb.append("\npatientRecordNumber:"+getPatient().getPatientRecordNumber());
		sb.append("\nPatient ID:"+getPatient().getPatientId());
		sb.append("\nUniqueID:"+getUniqueID());
	return sb.toString();
	}*/
	/**
	 * @param reminderText the reminderText to set
	 */
	public void setReminderText(String reminderText) {
		this.reminderText = reminderText;
	}
	/**
	 * @return the reminderText
	 */
	public String getReminderText() {
		return reminderText;
	}
}
