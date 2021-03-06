/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package org.irdresearch.tbreach2.mobileevent;

import java.util.Vector;

public class FieldTextMaps
{
	static final Vector ADVERTISING_FREE_TESTS_OPTIONS_MAP;
	static {
		ADVERTISING_FREE_TESTS_OPTIONS_MAP = new Vector();

		ADVERTISING_FREE_TESTS_OPTIONS_MAP.addElement(new ElementValueMap("HOW_FOUND_OUT_1","PAMPHLETS", "Pamphlets"));
		ADVERTISING_FREE_TESTS_OPTIONS_MAP.addElement(new ElementValueMap("HOW_FOUND_OUT_2","HEALTH WORKER SCREENING", "Health worker screening"));
		ADVERTISING_FREE_TESTS_OPTIONS_MAP.addElement(new ElementValueMap("HOW_FOUND_OUT_3","DOCTOR REFERRED WITHOUT PAD", "Doctor sent no pad"));
		ADVERTISING_FREE_TESTS_OPTIONS_MAP.addElement(new ElementValueMap("HOW_FOUND_OUT_4","AN AQUAINTANCE TOLD", "Janne wale ne bataya"));
		ADVERTISING_FREE_TESTS_OPTIONS_MAP.addElement(new ElementValueMap("HOW_FOUND_OUT_5","SOMEONE ELSE IN LAB", "Lab men koi aur"));
		ADVERTISING_FREE_TESTS_OPTIONS_MAP.addElement(new ElementValueMap("HOW_FOUND_OUT_6","DOCTOR IN LAB", "Lab doctor ne bataya"));
		ADVERTISING_FREE_TESTS_OPTIONS_MAP.addElement(new ElementValueMap("HOW_FOUND_OUT_7","POSTER AT CLINIC", "Poster at clinic"));
		ADVERTISING_FREE_TESTS_OPTIONS_MAP.addElement(new ElementValueMap("HOW_FOUND_OUT_8","MOSQUE ANNOUNCEMENT", "Masjid men ailan"));
		ADVERTISING_FREE_TESTS_OPTIONS_MAP.addElement(new ElementValueMap("HOW_FOUND_OUT_9","DOCTOR REFERRED WITH PAD", "Parchi for free test"));
		ADVERTISING_FREE_TESTS_OPTIONS_MAP.addElement(new ElementValueMap("HOW_FOUND_OUT_10","BANNER ON STREET", "Banner on street"));
		ADVERTISING_FREE_TESTS_OPTIONS_MAP.addElement(new ElementValueMap("HOW_FOUND_OUT_11","CABLE AD", "Cable Ad"));
		ADVERTISING_FREE_TESTS_OPTIONS_MAP.addElement(new ElementValueMap("HOW_FOUND_OUT_12","STANDEE IN LAB", "Standee in lab"));
		ADVERTISING_FREE_TESTS_OPTIONS_MAP.addElement(new ElementValueMap("HOW_FOUND_OUT_13","BANNER IN LAB", "Banner in lab"));
		ADVERTISING_FREE_TESTS_OPTIONS_MAP.addElement(new ElementValueMap("HOW_FOUND_OUT_14","RADIO AD", "Radio Ad"));
	}
	public static ElementValueMap getByElementName(String elementName){
		for (int i = 0; i < ADVERTISING_FREE_TESTS_OPTIONS_MAP.size(); i++)
		{
			ElementValueMap value = (ElementValueMap) ADVERTISING_FREE_TESTS_OPTIONS_MAP.elementAt(i);
			if(value.getElementName().toLowerCase().equals(elementName.trim().toLowerCase())){
				return value;
			}
		}
		return null;
	}
	
	public static ElementValueMap getByElementValue(String elementValue){
		for (int i = 0; i < ADVERTISING_FREE_TESTS_OPTIONS_MAP.size(); i++)
		{
			ElementValueMap value = (ElementValueMap) ADVERTISING_FREE_TESTS_OPTIONS_MAP.elementAt(i);
			if(value.getElementValue().toLowerCase().equals(elementValue.trim().toLowerCase())){
				return value;
			}
		}
		return null;
	}
	
	public static ElementValueMap getByDisplayText(String displayText){
		for (int i = 0; i < ADVERTISING_FREE_TESTS_OPTIONS_MAP.size(); i++)
		{
			ElementValueMap value = (ElementValueMap) ADVERTISING_FREE_TESTS_OPTIONS_MAP.elementAt(i);
			if(value.getDisplayText().toLowerCase().equals(displayText.trim().toLowerCase())){
				return value;
			}
		}
		return null;
	}
	public static class ElementValueMap{
		
		private final String elementName;
		private final String elementValue;
		private final String displayText;
		
		public ElementValueMap(String elementName,String elementValue, String displayText)
		{
			this.elementName = elementName;
			this.elementValue = elementValue;
			this.displayText = displayText;
		}

		public String getElementName()
		{
			return elementName;
		}

		public String getElementValue()
		{
			return elementValue;
		}

		public String getDisplayText()
		{
			return displayText;
		}
	}
}
