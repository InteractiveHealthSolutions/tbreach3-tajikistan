/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package org.irdresearch.tbreach.mobile.ui;

import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;

public class BaseTBReachForm extends Form {

	protected TBReachMainMIDlet tbrMidlet;
	protected Displayable prevDisplayable;
	protected String startDate;
	protected String startTime;
	protected String endTime;
		
	public Displayable getPrevDisplayable() {
		return prevDisplayable;
	}

	public void setPrevDisplayable(Displayable prevDisplayable) {
		this.prevDisplayable = prevDisplayable;
	}

	public BaseTBReachForm(String title, TBReachMainMIDlet tbrMidlet) {
		super(title);
		this.tbrMidlet = tbrMidlet;
		
	}
	
	public void init () {
		
	}
	
	
}
