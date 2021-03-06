/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package org.irdresearch.tbreach2.shared.model;

import java.io.Serializable;
import java.util.Date;

public class PatientReminder implements  Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PatientDetails patientDetails;//make pat n rem composite to prevent duplications
	private Reminder reminder;
	private int timeHour;
	private int timeMinute;
	private int timeSecond;
	private int gapInHours;//can add day of mon month week and year for complte cron expreesion
	private int iterations;
	private Date lastUpdated;
	private String 	createdByUserId;
	private String 	createdByUserName;
	private Date 	createdDate;
	private String 	lastEditedByUserId;
	private String 	lastEditedByUserName;
	
	public PatientReminder(){
		
	}
	public PatientReminder(PatientDetails patientDetails,Reminder reminder) {
		this.patientDetails=patientDetails;
		this.reminder=reminder;
	}
	public PatientDetails getPatientDetails() {
		return patientDetails;
	}
	public Reminder getReminder() {
		return reminder;
	}
	public int getTimeHour() {
		return timeHour;
	}
	public int getTimeMinute() {
		return timeMinute;
	}
	public int getTimeSecond() {
		return timeSecond;
	}
	public int getGapInHours() {
		return gapInHours;
	}
	public int getIterations() {
		return iterations;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	
	public void setPatientDetails(PatientDetails patientDetails) {
		this.patientDetails=patientDetails;
	}
	public void setReminder(Reminder reminder) {
		this.reminder=reminder;
	}
	public void setTimeHour(int value) {
		timeHour=value;
	}
	public void setTimeMinute(int value) {
		timeMinute=value;
	}
	public void setTimeSecond(int value) {
		timeSecond=value;
	}
	public void setGapInHours(int value) {
		gapInHours=value;
	}
	public void setIterations(int value) {
		iterations=value;
	}
	public void setLastUpdated(Date date) {
		lastUpdated=date;
	}
	/**
	 * @param createdByUserId the createdByUserId to set
	 */
	public void setCreatedByUserId(String createdByUserId) {
		this.createdByUserId = createdByUserId;
	}
	/**
	 * @return the createdByUserId
	 */
	public String getCreatedByUserId() {
		return createdByUserId;
	}
	/**
	 * @param createdByUserName the createdByUserName to set
	 */
	public void setCreatedByUserName(String createdByUserName) {
		this.createdByUserName = createdByUserName;
	}
	/**
	 * @return the createdByUserName
	 */
	public String getCreatedByUserName() {
		return createdByUserName;
	}
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}
	
/*	public void setCreator(User creator){
		setCreatedByUserId(creator.getName());
		setCreatedByUserName(creator.getFullName());
		setCreatedDate(new Date());
	}*/
	/**
	 * @param lastEditedByUserId the lastEditedByUserId to set
	 */
	public void setLastEditedByUserId(String lastEditedByUserId) {
		this.lastEditedByUserId = lastEditedByUserId;
	}
	/**
	 * @return the lastEditedByUserId
	 */
	public String getLastEditedByUserId() {
		return lastEditedByUserId;
	}
	/**
	 * @param lastEditedByUserName the lastEditedByUserName to set
	 */
	public void setLastEditedByUserName(String lastEditedByUserName) {
		this.lastEditedByUserName = lastEditedByUserName;
	}
	/**
	 * @return the lastEditedByUserName
	 */
	public String getLastEditedByUserName() {
		return lastEditedByUserName;
	}
/*	public void setLastEditor(User editor){
		setLastEditedByUserId(editor.getName());
		setLastEditedByUserName(editor.getFullName());
		setLastUpdated(new Date());

	}*/

}
