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
