/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
/**
 * Provides Authentication funcionality
 */

package org.irdresearch.tbreach2.server;

/**
 * @author owais.hussain@irdresearch.org
 * 
 */
public class UserAuthentication
{
	public UserAuthentication ()
	{
		// Not implemented
	}

	public static boolean userExsists (String userName)
	{
		try
		{
			return HibernateUtil.util.count ("select count(*) from Users where UserName = '" + userName.toUpperCase () + "'") > 0;
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
		return false;
	}

	public static boolean validatePassword (String userName, String password)
	{
		try
		{
			// Users user = (Users)
			// HibernateUtil.util.findObject("from Users where UserName = '" +
			// userName.toUpperCase() + "'");
			// if(MDHashUtil.match(password, user.getPassword()))
			String user = HibernateUtil.util.selectObject ("select Password from users where UserName = '" + userName.toUpperCase () + "'").toString ();
			if (MDHashUtil.match (password, user))
				return true;
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
		return false;
	}

/*	public static boolean validateSecretAnswer (String userName, String secretAnswer)
	{
		try
		{
			Users user = (Users) HibernateUtil.util.findObject ("from Users where UserName = '" + userName.toUpperCase () + "'");
			if (MDHashUtil.match (secretAnswer, user.getSecretAnswer ()))
				return true;
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
		return false;
	}*/
}
