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
import java.util.HashSet;
import java.util.Set;


public class Reminder implements  Serializable{
	private static final long serialVersionUID = 4618341382482591564L;

	private int reminderId;
	private String name;
	private String description;
	private String category;
	//private String text;
	/*private int iterations;
	private Time startTime;
	private Time endTime;
	private Time gap;*/
	private Date lastUpdated;
	private Set<ReminderText> reminderText=new HashSet<ReminderText>();
	//private Set<Patient> patient;
	private String 	createdByUserId;
	private String 	createdByUserName;
	private Date 	createdDate;
	private String 	lastEditedByUserId;
	private String 	lastEditedByUserName;	
	public Reminder() {
		
	}
	public Reminder(String Name) {
		//patient=new HashSet<Patient>();
		this.name=Name;
	}
	
	public int getReminderId() {
		return reminderId;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public String getCategory() {
		return category;
	}
	/*public String getText(){
		return text;
	}*/
	public Set<ReminderText> getReminderText() {
		return reminderText;
	}
	/*public int  getIterations() {
		return iterations;
	}
	public Time getStartTime() {
		return startTime;
	}
	public Time getEndTime(){
		return endTime;
	}
	public Time getGap() {
		return gap;
	}*/
	public Date getLastUpdated() {
		return lastUpdated;
	}
	/*public Set<Patient> getPatient(){
		return patient;
	}*/
	void setReminderId(int rId) {
		reminderId=rId;
	}
	public void setName(String string){
		name=string.toUpperCase();
	}
	public void setDescription(String string){
		description=string;
	}
	public void setCategory(String string){
		category=string;
	}
	/*public void setText(String string){
		text=string;
	}*/
	public  void setReminderText(Set<ReminderText> remText) {
		reminderText=remText;
	}
	/*public void setIterations(int iterations){
		this.iterations=iterations;
	}
	public void setStartTime(Time time){
		startTime=time;
	}
	public void setEndTime(Time time){
		endTime=time;
	}
	public void setGap(Time time){
		gap=time;
	}*/
	public void setLastUpdated(Date date){
		lastUpdated=date;
	}
	/*public void setPatient(Set<Patient> patients) {
		patient=patients;
	}*/
	public boolean isSameReminder(Reminder reminder){
		if(reminder !=null && this.name.compareTo(reminder.getName())==0){
			return true;
		}
		return false;
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
		createdByUserId=creator.getName();
		createdByUserName=creator.getFullName();
		createdDate=new Date();
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
		lastEditedByUserId=editor.getName();
		lastEditedByUserName=editor.getFullName();
	}*/
}
