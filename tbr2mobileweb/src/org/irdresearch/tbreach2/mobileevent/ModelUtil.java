/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */

package org.irdresearch.tbreach2.mobileevent;

import org.irdresearch.tbreach2.server.ServerServiceImpl;
import org.irdresearch.tbreach2.shared.model.Encounter;
import org.irdresearch.tbreach2.shared.model.EncounterResults;
import org.irdresearch.tbreach2.shared.model.EncounterResultsId;

public class ModelUtil
{

	public static EncounterResults createEncounterResult (Encounter e, String element, String value)
	{
		EncounterResultsId erId = new EncounterResultsId (e.getId ().getEncounterId (), e.getId ().getPid1 (), e.getId ().getPid2 (), element);
		EncounterResults er = new EncounterResults (erId, value);

		return er;
	}

	public static Boolean isValidGPID (String gpid)
	{
		Boolean result = false;

		ServerServiceImpl ssl = new ServerServiceImpl ();
		try
		{
			result = ssl.exists ("GP", "where GPID='" + gpid.toUpperCase () + "'");
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace ();
			return null;
		}

		return result;
	}

	public static Boolean isValidCHWID (String chwid)
	{
		Boolean result = false;

		ServerServiceImpl ssl = new ServerServiceImpl ();
		try
		{
			result = ssl.exists ("Worker", "where WorkerID='" + chwid.toUpperCase () + "'");
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace ();
			return null;
		}

		return result;
	}
}
