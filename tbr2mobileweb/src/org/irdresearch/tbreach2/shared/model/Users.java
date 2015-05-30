/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */

package org.irdresearch.tbreach2.shared.model;

import java.io.Serializable;

public class Users implements Serializable
{

	/**
	 * 
	 */
	private String				pid;
	private String				userName;
	private String				password;
	private String				role;
	private String				status;
	private String 				user_id;
	private String				firstName;
	private String 				lastName;
	private String				phoneNo;

	public Users ()
	{

	}

	public Users (String pid, String userName, String password, String role, String status, String user_id, String firstName, String lastName, String phoneNo)
	{
		this.pid = pid;
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.status = status;
		this.user_id = user_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNo = phoneNo;
	}

	public String getPhoneNo() {
		return phoneNo;
	}
	
	public void setPhoneNo(String phoneNo){
		this.phoneNo = phoneNo;
	}
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPid ()
	{
		return pid;
	}

	public void setPid (String pid)
	{
		this.pid = pid;
	}

	public String getUserName ()
	{
		return userName;
	}

	public void setUserName (String userName)
	{
		this.userName = userName;
	}

	public String getPassword ()
	{
		return password;
	}

	public void setPassword (String password)
	{
		this.password = password;
	}

	public String getRole ()
	{
		return role;
	}

	public void setRole (String role)
	{
		this.role = role;
	}

	public String getStatus ()
	{
		return status;
	}

	public void setStatus (String status)
	{
		this.status = status;
	}

}
