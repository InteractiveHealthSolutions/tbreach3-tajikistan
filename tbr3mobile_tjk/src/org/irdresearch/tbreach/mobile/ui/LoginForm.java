/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package org.irdresearch.tbreach.mobile.ui;

import java.util.Date;
import java.util.Hashtable;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDletStateChangeException;

import org.irdresearch.tbreach.mobile.constants.ErrMsg;
import org.irdresearch.tbreach.mobile.constants.UserType;
import org.irdresearch.tbreach.mobile.util.DateTimeUtil;


public class LoginForm extends BaseTBReachForm implements CommandListener {
	
	//private TBReachMainMIDlet tbrMidlet;
	private TextField username;
	private TextField password;
	private Command cmdOK;
	private Command cmdExit;

	public LoginForm(String title, TBReachMainMIDlet tbrMidlet) {
		super(title, tbrMidlet);
		
		username = new TextField("Username", "", 50, TextField.NUMERIC);
		password = new TextField("Password", "", 50, TextField.PASSWORD | TextField.NUMERIC | TextField.SENSITIVE);
		cmdOK = new Command("Ха", Command.OK, 1);
		cmdExit = new Command("Баромад", Command.BACK, 0);
		
	}
	
	public boolean validate ()
	{
		if (username == null || username.getString ().trim ().length () == 0)
		{
			tbrMidlet.showAlert ("Enter Username", null);
			return false;
		}
		else if (password == null || password.getString ().trim ().length () == 0)
		{
			tbrMidlet.showAlert ("Enter Password", null);
			return false;
		}
		return true;
	}

	public void commandAction(Command c, Displayable d) {
		System.out.println("entering commandaction");
		if (c == cmdOK) {
			
			if(validate())
			{
			
			//BEGIN DUMMY LOGIN CODE
/*			if(username.getString()!=null && username.getString().toUpperCase().equals("G-DUMMY-01")) {
				if(password.getString()!=null && password.getString().equals("g-dummy-01")) {
					tbrMidlet.setCurrentUser(username.getString().toUpperCase());
					tbrMidlet.setCurrentUserId(username.getString().toUpperCase());
					tbrMidlet.setCurrentRole( UserType.USER_TYPE_GP);
					tbrMidlet.startMainMenu(UserType.USER_TYPE_GP);
				}
				else {
					tbrMidlet.showAlert("Invalid Username or Password! Please try again!", null);
				}
			}
			else if(username.getString()!=null && username.getString().toUpperCase().equals("C-DUMMY-01")) {
				if(password.getString()!=null && password.getString().equals("c-dummy-01")) {
					tbrMidlet.setCurrentUser(username.getString().toUpperCase());
					tbrMidlet.setCurrentUserId(username.getString().toUpperCase());
					tbrMidlet.setCurrentRole( UserType.USER_TYPE_CHW);
					tbrMidlet.startMainMenu(UserType.USER_TYPE_CHW);
				}
				else {
					tbrMidlet.showAlert("Invalid Username or Password! Please try again!", null);
				}
			}
			else if(username.getString()!=null && username.getString().toUpperCase().equals("M-DUMMY-01")) {
				if(password.getString()!=null && password.getString().equals("m-dummy-01")) {
					tbrMidlet.setCurrentUser(username.getString().toUpperCase());
					tbrMidlet.setCurrentUserId(username.getString().toUpperCase());
					tbrMidlet.setCurrentRole( UserType.USER_TYPE_MONITOR);
					tbrMidlet.startMainMenu(UserType.USER_TYPE_MONITOR);
				}
				else {
					tbrMidlet.showAlert("Invalid Username or Password! Please try again!", null);
				}
			}
			else if(username.getString()!=null && username.getString().toUpperCase().equals("ADMIN")) {
				if(password.getString()!=null && password.getString().equals("admin")) {
					tbrMidlet.setCurrentUser(username.getString().toUpperCase());
					tbrMidlet.setCurrentUserId(username.getString().toUpperCase());
					tbrMidlet.setCurrentRole( UserType.USER_TYPE_ADMIN);
					tbrMidlet.startMainMenu(UserType.USER_TYPE_ADMIN);
				}
				else {
					tbrMidlet.showAlert("Invalid Username or Password! Please try again!", null);
				}
			}
			else {
				tbrMidlet.showAlert("Invalid Username or Password! Please try again!", null);
			}*/
			
			//END DUMMY LOGIN CODE
			
			//BEGIN COMMENTED ACTUAL LOGIN CODE
			String request = createRequestPayload();
			Hashtable model = tbrMidlet.sendToServer(request);
			if (model != null) {

				if ((String) model.get("status") != null && ((String) model.get("status")).equals("error")) {
					tbrMidlet.showAlert((String) model.get("msg"), null);				
				}

				else {

					String role = (String) model.get("role");
					String uid = (String)model.get("uid");
					String user = username.getString();
					System.out.println(uid);
					
					if (role.equals("LABTECH")) {
						tbrMidlet.setCurrentUser(user);
						tbrMidlet.setCurrentUserId(uid);
						tbrMidlet.startMainMenu(UserType.USER_TYPE_CHW);
					}

				 	else if (role.equals("CHW")) {
						tbrMidlet.setCurrentUser(user);
						tbrMidlet.setCurrentUserId(uid);
						tbrMidlet.startMainMenu(UserType.USER_TYPE_CHW);

					} 

					else if (role.equals("MONITOR")) {
						tbrMidlet.setCurrentUser(user);
						tbrMidlet.setCurrentUserId(uid);
						tbrMidlet.startMainMenu(UserType.USER_TYPE_MONITOR);
					}

					else if (role.equals("ADMIN")) {
						tbrMidlet.setCurrentUser(user);
						tbrMidlet.setCurrentUserId(uid);
						tbrMidlet.startMainMenu(UserType.USER_TYPE_ADMIN);
					}
					else if (role.equals("DOCTOR")) {
						/*tbrMidlet.setCurrentUser(user);
						tbrMidlet.setCurrentUserId(uid);
						tbrMidlet.startMainMenu(UserType.USER_TYPE_DOCTOR);*/
				}
				}
			}

			else
				tbrMidlet.showAlert(ErrMsg.LOGIN_ERROR, null);
			//END COMMENTED ACTUAL LOGIN CODE 
			 
			 
			}
		}
		
		else if(c==cmdExit) {
			System.out.println("entering handler");
			deleteAll();
			
			try {
				tbrMidlet.destroyApp(false);
				tbrMidlet.notifyDestroyed();
				
			}
			catch(MIDletStateChangeException e) {
				System.out.println("BAAAA->" + e.toString());
			}
			System.out.println("leaving handler");
			
		}
			
	}
	
    public void init() {
		setCommandListener(this);

    	addCommand(cmdOK);
    	addCommand(cmdExit);
    	append(username);
    	append(password);
    }
    
    private String createRequestPayload() {
    	
    	String request = null;
    	
    	String usernameString = username.getString();
    	String passwordString = password.getString();
    	
    	request = "type=lg&";
    	request += "username=" + usernameString + "&";
    	request += "phoneTime=" + DateTimeUtil.getDateTime (new Date()) + "&";
    	request += "password=" + passwordString;
    	
    	return request;
    }

}
