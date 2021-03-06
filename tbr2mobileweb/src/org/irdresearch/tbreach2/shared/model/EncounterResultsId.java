/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */

package org.irdresearch.tbreach2.shared.model;

// Generated Aug 28, 2011 3:52:51 PM by Hibernate Tools 3.4.0.Beta1

/**
 * EncounterresultsId generated by hbm2java
 */
public class EncounterResultsId implements java.io.Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 5685175923674201339L;
	private int					encounterId;
	private String				pid1;
	private String				pid2;
	private String				element;

	public EncounterResultsId ()
	{
	}

	public EncounterResultsId (int encounterId, String pid1, String pid2, String element)
	{
		this.encounterId = encounterId;
		this.pid1 = pid1;
		this.pid2 = pid2;
		this.element = element;
	}

	public int getEncounterId ()
	{
		return this.encounterId;
	}

	public void setEncounterId (int encounterId)
	{
		this.encounterId = encounterId;
	}

	public String getPid1 ()
	{
		return this.pid1;
	}

	public void setPid1 (String pid1)
	{
		this.pid1 = pid1;
	}

	public String getPid2 ()
	{
		return this.pid2;
	}

	public void setPid2 (String pid2)
	{
		this.pid2 = pid2;
	}

	public String getElement ()
	{
		return this.element;
	}

	public void setElement (String element)
	{
		this.element = element;
	}

	public boolean equals (Object other)
	{
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EncounterResultsId))
			return false;
		EncounterResultsId castOther = (EncounterResultsId) other;

		return (this.getEncounterId () == castOther.getEncounterId ())
				&& ((this.getPid1 () == castOther.getPid1 ()) || (this.getPid1 () != null && castOther.getPid1 () != null && this.getPid1 ().equals (castOther.getPid1 ())))
				&& ((this.getPid2 () == castOther.getPid2 ()) || (this.getPid2 () != null && castOther.getPid2 () != null && this.getPid2 ().equals (castOther.getPid2 ())))
				&& ((this.getElement () == castOther.getElement ()) || (this.getElement () != null && castOther.getElement () != null && this.getElement ().equals (castOther.getElement ())));
	}

	public int hashCode ()
	{
		int result = 17;

		result = 37 * result + this.getEncounterId ();
		result = 37 * result + (getPid1 () == null ? 0 : this.getPid1 ().hashCode ());
		result = 37 * result + (getPid2 () == null ? 0 : this.getPid2 ().hashCode ());
		result = 37 * result + (getElement () == null ? 0 : this.getElement ().hashCode ());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString ()
	{
		return encounterId + ", " + pid1 + ", " + pid2 + ", " + element;
	}

}
