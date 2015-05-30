/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */

package org.irdresearch.tbreach2.shared.model;

// Generated Aug 28, 2011 3:01:59 PM by Hibernate Tools 3.4.0.Beta1

import java.util.Date;

/**
 * LogData generated by hbm2java
 */
public class Log_Data implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -4415955699426173663L;
	private Long				logNo;
	private String				userId;
	private Date				dateLogged;
	private String				logType;
	private String				entity;
	private String				currentValue;

	public Log_Data ()
	{
	}

	public Log_Data (Date dateLogged)
	{
		this.dateLogged = dateLogged;
	}

	public Log_Data (String userId, Date dateLogged, String logType, String entity, String currentValue)
	{
		this.userId = userId;
		this.dateLogged = dateLogged;
		this.logType = logType;
		this.entity = entity;
		this.currentValue = currentValue;
	}

	public Long getLogNo ()
	{
		return this.logNo;
	}

	public void setLogNo (Long logNo)
	{
		this.logNo = logNo;
	}

	public String getUserId ()
	{
		return this.userId;
	}

	public void setUserId (String userId)
	{
		this.userId = userId;
	}

	public Date getDateLogged ()
	{
		return this.dateLogged;
	}

	public void setDateLogged (Date dateLogged)
	{
		this.dateLogged = dateLogged;
	}

	public String getLogType ()
	{
		return this.logType;
	}

	public void setLogType (String logType)
	{
		this.logType = logType;
	}

	public String getEntity ()
	{
		return this.entity;
	}

	public void setEntity (String entity)
	{
		this.entity = entity;
	}

	public String getCurrentValue ()
	{
		return this.currentValue;
	}

	public void setCurrentValue (String currentValue)
	{
		this.currentValue = currentValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString ()
	{
		return logNo + ", " + userId + ", " + dateLogged + ", " + logType + ", " + entity + ", " + currentValue;
	}

}