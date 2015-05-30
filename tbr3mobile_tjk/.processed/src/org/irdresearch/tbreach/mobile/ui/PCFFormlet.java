/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package org.irdresearch.tbreach.mobile.ui;

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;

public class PCFFormlet {

	ChoiceGroup relationshipGroup;
	TextField otherField;
	
	ChoiceGroup tbFormGroup;
	ChoiceGroup tbTypeGroup;
	ChoiceGroup ssPositiveGroup;
	
	public PCFFormlet() {
		relationshipGroup = null;
		otherField = null;
		
		tbFormGroup = null;
		tbTypeGroup = null;
		ssPositiveGroup = null;
	}
	
	public void init() {
		relationshipGroup = new ChoiceGroup("Relationship to Suspect", ChoiceGroup.POPUP);
		relationshipGroup.append("Mother",null);
		relationshipGroup.append("Father ",null);
		relationshipGroup.append("Brother",null);
		relationshipGroup.append("Sister",null);
		relationshipGroup.append("Grandfather",null);
		relationshipGroup.append("Grandmother",null);
		relationshipGroup.append("Uncle",null);
		relationshipGroup.append("Aunt ",null);
		relationshipGroup.append("Other",null);
		relationshipGroup.append("Don't Know", null);
		
		otherField = new TextField("Specify Other:","",50,TextField.ANY);
		otherField.setConstraints(TextField.UNEDITABLE);
		
		tbFormGroup = new ChoiceGroup("Form of TB", ChoiceGroup.POPUP);
		tbFormGroup.append("Susceptible TB", null);
		tbFormGroup.append("MDR-TB", null);
		tbFormGroup.append("PDR-TB", null);
		tbFormGroup.append("Mono-resistant", null);
		tbFormGroup.append("Don't Know", null);
		tbFormGroup.append("Refused", null);
		
		tbTypeGroup = new ChoiceGroup("Type of TB", ChoiceGroup.POPUP);
		tbTypeGroup.append("Pulmonary",null);
		tbTypeGroup.append("Extra-Pulmonary",null);
		tbTypeGroup.append("Don't Know", null);
		tbTypeGroup.append("Refused",null);
		
		ssPositiveGroup = new ChoiceGroup("Sputum Smear +ve?", ChoiceGroup.POPUP);
		ssPositiveGroup.append("Yes",null);
		ssPositiveGroup.append("No",null);
		ssPositiveGroup.append("Don't Know", null);
		ssPositiveGroup.append("Refused",null);
		
	}
	
	public void addToForm(BaseTBReachForm f, int pos, int num) {
		f.insert(pos, new StringItem("Family Member #" + num, null));
		f.insert(pos+1,relationshipGroup);
		f.insert(pos+2,otherField);
		f.insert(pos+3,tbFormGroup);
		f.insert(pos+4,tbTypeGroup);
		f.insert(pos+5,ssPositiveGroup);
	}
	
}
