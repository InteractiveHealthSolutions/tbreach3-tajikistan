/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package org.irdresearch.tbreach.mobile.ui;


import java.util.Hashtable;

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.TextField;

import org.irdresearch.tbreach.mobile.constants.ErrMsg;

public class MRIDSearchForm extends BaseTBReachForm implements CommandListener,ItemStateListener {

	Command cmdOK;
	Command cmdBack;
	
	TextField idField;
	TextField mrField;
	ChoiceGroup idType;
	
	private String formType;
	
	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public MRIDSearchForm(String title, TBReachMainMIDlet tbrMidlet, String formType) {
		super(title, tbrMidlet);
		this.formType = formType;
		
		cmdOK = null;
		cmdBack = null;
		
		idField = null;
		mrField = null;
		idType = null;
	}
	
	public void commandAction(Command c, Displayable d) {

		if (c == cmdOK) {
			if (validate()) {
				String request = createRequestPayload();
				Hashtable model = tbrMidlet.sendToServer(request);

				if (model != null) {
					
					if((String)model.get("status")!=null && ((String)model.get("status")).equals("error")) {
						tbrMidlet.showAlert((String)model.get("msg"), null);
					}
					
					else {
						tbrMidlet.sf.setQueryData(model);
						tbrMidlet.sf.setPrevDisplayable(tbrMidlet.mainList);
						tbrMidlet.sf.setPatientId(null);
						cleanUp();
						tbrMidlet.startTBReachForm(tbrMidlet.sf);
					}		
				}
			}
		}
		
		else if(c==cmdBack) {
			deleteAll();
			removeCommand(cmdOK);
			removeCommand(cmdBack);
			tbrMidlet.setDisplay(prevDisplayable);
		}

	}
	
	private String createRequestPayload() {
    	
    	String request = null;
    	String id = "P" + idField.getString();
    	String mr = mrField.getString();
    	String type = idType.getString(idType.getSelectedIndex());
    	
    	request = "type=fq&qtype=" + formType;
    	request += "&un=" + tbrMidlet.getCurrentUser();
    	request += "&id=" + id;
    	request += "&mr=" + mr;
    	request += "&itype=" + type;
    	
    	return request;
    }
	
	
	public void init() {
		
		idField = new TextField("ID", "", 10, TextField.NUMERIC);
		mrField = new TextField("MR", "", 11, TextField.NUMERIC);
		mrField.setConstraints(TextField.UNEDITABLE);
		idType = new ChoiceGroup("Search using",ChoiceGroup.POPUP);
		idType.append("Patient ID",null);
		idType.append("MR", null);
		cmdOK = new Command("Submit", Command.OK, 0);
		cmdBack = new Command("Home", Command.BACK, 1);
		
		
		append(idType);
		append(idField);
		append(mrField);
		addCommand(cmdOK);
		addCommand(cmdBack);
		
		setCommandListener(this);
		setItemStateListener(this);
	}
	
	public boolean validate() {
		boolean result = true;
		int index = idType.getSelectedIndex();
		if(idType.getSelectedIndex()==0 && (idField.getString()==null || idField.getString().length()==0) ) {
			tbrMidlet.showAlert(ErrMsg.ID_MISSING,null);
			result = false;
		}

		else if (idType.getSelectedIndex()==1 && (mrField.getString()==null || mrField.getString().length()==0) ) {
			tbrMidlet.showAlert(ErrMsg.MR_MISSING,null);
			result = false;
		}

		
		return result;
	}
	
	public void cleanUp() {
		deleteAll();
		removeCommand(cmdOK);
		removeCommand(cmdBack);
	}
	
	public void itemStateChanged(Item i) {
		if(i==idType) {
			int index = idType.getSelectedIndex();
			
			if(index==0) {
				mrField.setString("");
				mrField.setConstraints(TextField.UNEDITABLE);
				idField.setConstraints(TextField.NUMERIC);
			}
			
			if(index==1) {
				idField.setString("");
				idField.setConstraints(TextField.UNEDITABLE);
				mrField.setConstraints(TextField.NUMERIC);
			}
		}
	}
	
}
