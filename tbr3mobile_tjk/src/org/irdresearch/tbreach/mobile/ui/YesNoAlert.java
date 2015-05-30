/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package org.irdresearch.tbreach.mobile.ui;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;

public class YesNoAlert extends Form implements CommandListener {
	private Command yesCommand, noCommand;
	TBReachMainMIDlet tbrMidlet;
	Displayable yesDisplayable;
	Displayable noDisplayable;
	
	public YesNoAlert(String title, String alertString, TBReachMainMIDlet tbrMidlet, Displayable yesDisplayable, Displayable noDisplayable) {
		super(title);
		this.tbrMidlet = tbrMidlet;
		this.yesDisplayable = yesDisplayable;
		this.noDisplayable = noDisplayable;
		
		yesCommand = new Command("Yes", Command.SCREEN, 1);
		noCommand = new Command("No", Command.SCREEN, 1);
		
		append(alertString);
		addCommand(yesCommand);
		addCommand(noCommand);
		setCommandListener(this);
		
	}

	public void commandAction(Command cmd, Displayable d) {
		// TODO Auto-generated method stub
		if (cmd == yesCommand) {
			tbrMidlet.setDisplay(yesDisplayable);
		} else if (cmd == noCommand) {
			tbrMidlet.setDisplay(noDisplayable);
		}
	}
	
}

