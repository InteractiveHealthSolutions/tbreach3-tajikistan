/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package org.irdresearch.tbreach2.shared.model;

import java.util.Date;

public class PatientDetails implements java.io.Serializable{
	
	private String				pid;
	private String				addressHouse;
	private String				addressStreet;
	private String				addressDistrict;
	private String				addressFlat;
	private String				gender;
	private String 				firstName;
	private String 				lastName;
	private Date				dob;
	private String				nominateVolunteer;
	private String				relationshipFamily;
	private String				maritalStatus;
	private String				education;
	private String				incomeFamilyMember;
	private String				homePhone;
	private String				phone1;
	private String				phoneOwner1;
	private String				phone2;
	private String				phoneOwner2;
	private Date				startTreatment;
	private String				healthWorkerID;
	private Date				startTimeForm;
	private Date				endTimeForm;
	private String              active;
	private Date				inactiveDate;

	
	public PatientDetails ()
	{
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	
	public Date getdob() {
		return dob;
	}

	public void setdob(Date dateOfBirth) {
		this.dob = dateOfBirth;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getlastName() {
		return lastName;
	}
	
	public void setNominateVolunteer(String nominateVolunteer) {
		this.nominateVolunteer = nominateVolunteer;
	}
	
	public String getNominateVolunteer() {
		return nominateVolunteer;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getStartTreatment() {
		return startTreatment;
	}


	public void setStartTreatment(Date startTreatment) {
		this.startTreatment = startTreatment;
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


	public String getPhone1() {
		return phone1;
	}


	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}


	public String getPhoneOwner1() {
		return phoneOwner1;
	}


	public void setPhoneOwner1(String phoneOwner1) {
		this.phoneOwner1 = phoneOwner1;
	}


	public String getPhone2() {
		return phone2;
	}


	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}


	public String getPhoneOwner2() {
		return phoneOwner2;
	}


	public void setPhoneOwner2(String phoneOwner2) {
		this.phoneOwner2 = phoneOwner2;
	}


	public String getPid() {
		return pid;
	}


	public void setPid(String pid) {
		this.pid = pid;
	}


	public String getAddressHouse() {
		return addressHouse;
	}


	public void setAddressHouse(String addressHouse) {
		this.addressHouse = addressHouse;
	}


	public String getAddressStreet() {
		return addressStreet;
	}


	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}


	public String getAddressDistrict() {
		return addressDistrict;
	}


	public void setAddressDistrict(String addressDistrict) {
		this.addressDistrict = addressDistrict;
	}


	public String getAddressFlat() {
		return addressFlat;
	}


	public void setAddressFlat(String addressFlat) {
		this.addressFlat = addressFlat;
	}


	public String getRelationshipFamily() {
		return relationshipFamily;
	}


	public void setRelationshipFamily(String relationshipFamily) {
		this.relationshipFamily = relationshipFamily;
	}


	public String getMaritalStatus() {
		return maritalStatus;
	}


	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}


	public String getEducation() {
		return education;
	}


	public void setEducation(String education) {
		this.education = education;
	}


	public String getIncomeFamilyMember() {
		return incomeFamilyMember;
	}


	public void setIncomeFamilyMember(String incomeFamilyMember) {
		this.incomeFamilyMember = incomeFamilyMember;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getActive() {
		return active;
	}

	public void setInactiveDate(Date inactiveDate) {
		this.inactiveDate = inactiveDate;
	}

	public Date getInactiveDate() {
		return inactiveDate;
	}

}
