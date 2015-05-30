/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package org.irdresearch.tbreach.mobile.util;

import java.util.Vector;

import javax.microedition.lcdui.ChoiceGroup;

public class StringUtil
{
	public static String replace(String oldStr, String newStr, String inString) {
	    int start = inString.indexOf(oldStr);
	    if (start == -1) {
	      return inString;
	    }
	    StringBuffer sb = new StringBuffer();
	    sb.append(inString.substring(0, start));
	    sb.append(newStr);
	    sb.append(inString.substring(start+oldStr.length()));
	    return sb.toString();
	  }
	
	
	public static String[] split (String string, char filter)
	{
		
		if (string == null)
			return null;
		if (string.equals (""))
			return new String[0];
		Vector vector = new Vector ();
		int pointer = 0;
		for (int i = 0; i < string.length (); i++)
			if (string.charAt (i) == filter)
			{
				vector.addElement (string.substring (pointer, i));
				pointer = i + 1;
			}
		vector.addElement (string.substring (pointer));
		String[] array = new String[vector.size ()];
		for (int i = 0; i < array.length; i++)
		{
			array[i] = vector.elementAt (i).toString ();
		}
		return array;
	}
	
	public static int firstOccurrenceOf (String string, char chr)
	{
		for(int i = 0; i < string.length(); i++){
			if(string.charAt( i ) == ' ')
				return i;			
		}
		
		return 0;
	}
	
	public static boolean isAlpha (String string)
	{
	
		for(int i = 0; i<string.length (); i++){
			if(!((string.charAt (i) >= 'A' && string.charAt (i) <= 'Z')||(string.charAt (i) >= 'a' && string.charAt (i) <= 'z')||string.charAt (i)==' '))
				return false;		
		}
		return true;
	}
	
	
	/**
	 * Returns the selected string values in an choicegroup separated by ':'
	 * 
	 * @param item
	 * @return
	 */
	public static String getStringMultipleSelectionFromChoiceGroup (ChoiceGroup choice)
	{
		boolean[] selectedIndices = new boolean[choice.size ()];

		choice.getSelectedFlags (selectedIndices);
		String string = "";
		for (int i = 0; i < choice.size (); i++)
		{
			if (selectedIndices[i])
			{
				string += choice.getString (i) + ",";
			}
		}
		return string;
	}
}
