/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package org.irdresearch.tbreach.mobile.ui;

import java.util.Hashtable;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;

public class SearchForm extends BaseTBReachForm implements CommandListener {
	
	Command cmdBack;
	
	private Hashtable queryData;
	private String patientId;
	
	public SearchForm(String title,TBReachMainMIDlet tbrMidlet) {
		super(title, tbrMidlet);
	
	}
	
	public void init() {
		String data = "";
		
		if(queryData!=null) {
			if((String)queryData.get("data")==null) {
			data += "Patient ID: " + (String)queryData.get("pid");
			data += "\nMR Number: " + (String)queryData.get("mr");
			data += "\nFirst Name: " + (String)queryData.get("fname");
			data += "\nFather/Husband Name: " + (String)queryData.get("fatname");
			data += "\nGender: " + (String)queryData.get("gender");
			data += "\nDate of Birth: " + (String)queryData.get("dob");
			data += "\nAge: " + (String)queryData.get("age");
			data += "\nMarital Status: " + (String)queryData.get("mstatus");
			data += "\nNIC: " + (String)queryData.get("nic");
			data += "\nPhone: " + (String)queryData.get("phone");
			data += "\nHouse No: " + (String)queryData.get("housenum");
			data += "\nStreet: " + (String)queryData.get("street");
			data += "\nSector: " + (String)queryData.get("sector");
			data += "\nColony: " + (String)queryData.get("colony"); 
			data += "\nTown: " + (String)queryData.get("town"); 
			data += "\nUC: " + (String)queryData.get("uc"); 
			data += "\nLandmark: " + (String)queryData.get("landmark"); 
			data += "\nAdults in HH: " + (String)queryData.get("numadults");
			data += "\nChildren in HH:" + (String)queryData.get("numchildren");
			data += "\nState: " + (String)queryData.get("state");
			
			}
			
			else {
				data += (String)queryData.get("data");
			}
			
			append(data);
			
			cmdBack = new Command("Home", Command.BACK, 1);
			addCommand(cmdBack);
			
			setCommandListener(this);
		}
	}
	
	public Hashtable getQueryData() {
		return queryData;
	}

	public void setQueryData(Hashtable queryData) {
		this.queryData = queryData;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public void commandAction(Command c, Displayable d) {
		if(c==cmdBack) {
			deleteAll();
			removeCommand(cmdBack);
			tbrMidlet.setDisplay(prevDisplayable);
		}
	}

}
