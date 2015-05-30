/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package org.irdresearch.tbreach2.shared.model;

import java.util.Date;

public class Surveys implements java.io.Serializable{
	
	private Integer				id;
	private String				pid;
	private String				satisfiedWithSystem;
	private String				unsatisfactoryAspectSystem;
	private String				systemImprovement;
	private String				satisfiedWithSmsReminder;
	private String				unsatisfactoryAspectSmsReminder;
	private String 				reminderAdditionalEffort;
	private String 				reminderTreatmentCompliance;
	private String				satisfiedServiceHealthFacility;
	private String				satisfiedCareTbSpeaclist;
	private String              tbTreatmentSystem;
	private String				healthWorkerID;
	private Date				startTimeForm;
	private Date				endTimeForm;

	
	public Surveys ()
	{
	}


	public String getPid() {
		return pid;
	}


	public void setPid(String pid) {
		this.pid = pid;
	}


	public String getSatisfiedWithSystem() {
		return satisfiedWithSystem;
	}


	public void setSatisfiedWithSystem(String satisfiedWithSystem) {
		this.satisfiedWithSystem = satisfiedWithSystem;
	}


	public String getUnsatisfactoryAspectSystem() {
		return unsatisfactoryAspectSystem;
	}


	public void setUnsatisfactoryAspectSystem(String unsatisfactoryAspectSystem) {
		this.unsatisfactoryAspectSystem = unsatisfactoryAspectSystem;
	}


	public String getSystemImprovement() {
		return systemImprovement;
	}


	public void setSystemImprovement(String systemImprovement) {
		this.systemImprovement = systemImprovement;
	}


	public String getSatisfiedWithSmsReminder() {
		return satisfiedWithSmsReminder;
	}


	public void setSatisfiedWithSmsReminder(String satisfiedWithSmsReminder) {
		this.satisfiedWithSmsReminder = satisfiedWithSmsReminder;
	}


	public String getUnsatisfactoryAspectSmsReminder() {
		return unsatisfactoryAspectSmsReminder;
	}


	public void setUnsatisfactoryAspectSmsReminder(
			String unsatisfactoryAspectSmsReminder) {
		this.unsatisfactoryAspectSmsReminder = unsatisfactoryAspectSmsReminder;
	}


	public String getReminderAdditionalEffort() {
		return reminderAdditionalEffort;
	}


	public void setReminderAdditionalEffort(String reminderAdditionalEffort) {
		this.reminderAdditionalEffort = reminderAdditionalEffort;
	}


	public String getReminderTreatmentCompliance() {
		return reminderTreatmentCompliance;
	}


	public void setReminderTreatmentCompliance(String reminderTreatmentCompliance) {
		this.reminderTreatmentCompliance = reminderTreatmentCompliance;
	}


	public String getSatisfiedServiceHealthFacility() {
		return satisfiedServiceHealthFacility;
	}


	public void setSatisfiedServiceHealthFacility(
			String satisfiedServiceHealthFacility) {
		this.satisfiedServiceHealthFacility = satisfiedServiceHealthFacility;
	}


	public String getSatisfiedCareTbSpeaclist() {
		return satisfiedCareTbSpeaclist;
	}


	public void setSatisfiedCareTbSpeaclist(String satisfiedCareTbSpeaclist) {
		this.satisfiedCareTbSpeaclist = satisfiedCareTbSpeaclist;
	}


	public String getHealthWorkerID() {
		return healthWorkerID;
	}


	public void setHealthWorkerID(String healthWorkerID) {
		this.healthWorkerID = healthWorkerID;
	}


	public Date getStartTimeForm() {
		return startTimeForm;
	}


	public void setStartTimeForm(Date startTimeForm) {
		this.startTimeForm = startTimeForm;
	}


	public Date getEndTimeForm() {
		return endTimeForm;
	}


	public void setEndTimeForm(Date endTimeForm) {
		this.endTimeForm = endTimeForm;
	}
	

	public String getTbTreatmentSystem() {
		return tbTreatmentSystem;
	}


	public void setTbTreatmentSystem(String tbTreatmentSystem) {
		this.tbTreatmentSystem = tbTreatmentSystem;
	}

}
