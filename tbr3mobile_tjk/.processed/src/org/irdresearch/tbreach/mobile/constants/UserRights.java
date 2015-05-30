/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
/**
 * This class represents User Roles and their respective rights on the forms
 */

package org.irdresearch.tbreach.mobile.constants;

/**
 * @author Owais
 * 
 */
public final class UserRights
{
	private static int[]	adminRights			= {MenuItem.MENU_SUSPECT_ID};
	private static int[]	cehwRights			= {};
	private static int[]	chwRights			= {MenuItem.MENU_SUSPECT_ID};
	private static int[]	glwRights			= {};
	private static int[]	gpRights			= {};
	private static int[]	supervisorRights	= {};
	private static int[]	laboratoryRights	= {};
	private static int[]	tswRights			= {};

	public static boolean getUserRights (int userType, int menuItem)
	{
		boolean b = false;
		int[] toUse = {};
		switch (userType)
		{
			case UserType.USER_TYPE_ADMIN :
				toUse = adminRights;
				break;
			/*case UserType.CEHW :
				toUse = cehwRights;
				break;*/
			case UserType.USER_TYPE_CHW :
				toUse = chwRights;
				break;
			/*case UserType.GLW :
				toUse = glwRights;
				break;
			case UserType.GP :
				toUse = gpRights;
				break;
			case UserType.LABORATORY :
				toUse = laboratoryRights;
				break;
			case UserType.SUPERVISOR :
				toUse = supervisorRights;
				break;
			case UserType.TSW :
				toUse = tswRights;
				break;*/
			default :
				break;
		}
		for (int i = 0; i < toUse.length; i++)
			if (menuItem == toUse[i])
				b = true;
		return b;
	}
}
