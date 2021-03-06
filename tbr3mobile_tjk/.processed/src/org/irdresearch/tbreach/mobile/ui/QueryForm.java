/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package org.irdresearch.tbreach.mobile.ui;

import java.util.Hashtable;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.TextField;

import org.irdresearch.tbreach.mobile.constants.ErrMsg;

public class QueryForm extends BaseTBReachForm implements CommandListener {

	Command			cmdOK;
	Command			cmdBack;

	TextField		idField;
	TextField		idConfirm;
	private String	formType;

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public QueryForm( String title , TBReachMainMIDlet tbrMidlet ,
			String formType ) {
		super( title , tbrMidlet );
		this.formType = formType;

		cmdOK = null;
		cmdBack = null;

		idField = null;
		idConfirm = null;
	}

	public void commandAction(Command c , Displayable d) {

		if (c == cmdOK) {
			if (validate()) {
				String request = createRequestPayload();
				Hashtable model = tbrMidlet.sendToServer( request );

				if (model != null) {

					if((String)model.get("status")!=null && ((String)model.get("status")).equals("error")) {
						
						tbrMidlet.showAlert((String)model.get("msg"), null);
					}

					else{
						if (formType.equals( "susconf" )) {
							// tbrMidlet.scf.setQueryData(model);
							tbrMidlet.scf
									.setPrevDisplayable( tbrMidlet.mainList );
							tbrMidlet.scf.setPatientId( idField.getString() );
							cleanUp();
							tbrMidlet.startTBReachForm( tbrMidlet.scf );
						}

						else if (formType.equals( "mtrfrm" )) {
							tbrMidlet.mtrfrm.setQueryData( model );
							tbrMidlet.mtrfrm
									.setPrevDisplayable( tbrMidlet.mainList );
							tbrMidlet.mtrfrm.setPatientId( idField.getString() );
							cleanUp();
							tbrMidlet.startTBReachForm( tbrMidlet.mtrfrm );
						}
						else if (formType.equals( "bslfrm" )) {
							tbrMidlet.bslfrm.setQueryData( model );
							tbrMidlet.bslfrm
									.setPrevDisplayable( tbrMidlet.mainList );
							tbrMidlet.bslfrm.setPatientId( idField.getString() );
							cleanUp();
							tbrMidlet.startTBReachForm( tbrMidlet.bslfrm );
						}
						else if (formType.equals( "pfupeff" )) {
							// tbrMidlet.spcf.setQueryData(model);
							tbrMidlet.pfupeff
									.setPrevDisplayable( tbrMidlet.mainList );
							tbrMidlet.pfupeff
									.setPatientId( idField.getString() );
							cleanUp();
							tbrMidlet.startTBReachForm( tbrMidlet.pfupeff );
						}
						else if (formType.equals( "pclvisf" )) {
							// tbrMidlet.spcf.setQueryData(model);
							tbrMidlet.pclvisf
									.setPrevDisplayable( tbrMidlet.mainList );
							tbrMidlet.pclvisf
									.setPatientId( idField.getString() );
							cleanUp();
							tbrMidlet.startTBReachForm( tbrMidlet.pclvisf );
						}
						else if (formType.equals( "clvisf" )) {
							// tbrMidlet.spcf.setQueryData(model);
							tbrMidlet.clvisf
									.setPrevDisplayable( tbrMidlet.mainList );
							tbrMidlet.clvisf.setPatientId( idField.getString() );
							cleanUp();
							tbrMidlet.startTBReachForm( tbrMidlet.clvisf );
						}
						else if (formType.equals( "spcoll" )) {
							// tbrMidlet.spcf.setQueryData(model);
							tbrMidlet.spcf
									.setPrevDisplayable( tbrMidlet.mainList );
							tbrMidlet.spcf.setPatientId( idField.getString() );
							cleanUp();
							tbrMidlet.startTBReachForm( tbrMidlet.spcf );
						}
						else if (formType.equals( "bastrt" )) {
							// tbrMidlet.btf.setQueryData(model);
							tbrMidlet.btf
									.setPrevDisplayable( tbrMidlet.mainList );
							tbrMidlet.btf.setPatientId( idField.getString() );
							cleanUp();
							tbrMidlet.startTBReachForm( tbrMidlet.btf );
						}
						else if (formType.equals( "fotrt" )) {
							// tbrMidlet.ftf.setQueryData(model);
							tbrMidlet.ftf
									.setPrevDisplayable( tbrMidlet.mainList );
							tbrMidlet.ftf.setPatientId( idField.getString() );
							cleanUp();
							tbrMidlet.startTBReachForm( tbrMidlet.ftf );
						}
						else if (formType.equals( "dradm" )) {
							// tbrMidlet.daf.setQueryData(model);
							tbrMidlet.daf
									.setPrevDisplayable( tbrMidlet.mainList );
							tbrMidlet.daf.setPatientId( idField.getString() );
							cleanUp();
							tbrMidlet.startTBReachForm( tbrMidlet.daf );
						}
						else if (formType.equals( "drdf" )) {
							// tbrMidlet.spcf.setQueryData(model);
							tbrMidlet.drdf
									.setPrevDisplayable( tbrMidlet.mainList );
							tbrMidlet.drdf.setPatientId( idField.getString() );
							cleanUp();
							tbrMidlet.startTBReachForm( tbrMidlet.drdf );
						}
						else if (formType.equals( "endfol" )) {
							// tbrMidlet.eff.setQueryData(model);
							tbrMidlet.eff
									.setPrevDisplayable( tbrMidlet.mainList );
							tbrMidlet.eff.setPatientId( idField.getString() );
							cleanUp();
							tbrMidlet.startTBReachForm( tbrMidlet.eff );
						}
						else if (formType.equals( "cdfinfo" )) {
							// tbrMidlet.cdf.setQueryData(model);
							tbrMidlet.cdf
									.setPrevDisplayable( tbrMidlet.mainList );
							tbrMidlet.cdf.setPatientId( idField.getString() );
							cleanUp();
							tbrMidlet.startTBReachForm( tbrMidlet.cdf );
						}
						else if (formType.equals( "pcfinfo" )) {
							// tbrMidlet.pcf.setQueryData(model);
							tbrMidlet.pcf
									.setPrevDisplayable( tbrMidlet.mainList );
							tbrMidlet.pcf.setPatientId( idField.getString() );
							cleanUp();
							tbrMidlet.startTBReachForm( tbrMidlet.pcf );
						}
						else if (formType.equals( "pcdfinfo" )) {
							// tbrMidlet.pcdf.setQueryData(model);
							tbrMidlet.pcdf
									.setPrevDisplayable( tbrMidlet.mainList );
							tbrMidlet.pcdf.setPatientId( idField.getString() );
							cleanUp();
							tbrMidlet.startTBReachForm( tbrMidlet.pcdf );
						}
						else if (formType.equals( "cspcoll" )) {
							// tbrMidlet.cspcf.setQueryData(model);
							tbrMidlet.cspcf
									.setPrevDisplayable( tbrMidlet.mainList );
							tbrMidlet.cspcf.setPatientId( idField.getString() );
							cleanUp();
							tbrMidlet.startTBReachForm( tbrMidlet.cspcf );
						}

						else if (formType.equals( "drugdisp" )) {
							// tbrMidlet.drdf.setQueryData(model);
							tbrMidlet.drdf
									.setPrevDisplayable( tbrMidlet.mainList );
							tbrMidlet.drdf.setPatientId( idField.getString() );
							cleanUp();
							tbrMidlet.startTBReachForm( tbrMidlet.drdf );
						}

						else if (formType.equals( "patverify" )) {
							// tbrMidlet.pvf.setQueryData(model);
							tbrMidlet.pvf
									.setPrevDisplayable( tbrMidlet.mainList );
							tbrMidlet.pvf.setPatientId( idField.getString() );
							cleanUp();
							tbrMidlet.startTBReachForm( tbrMidlet.pvf );
						}

						else if (formType.equals( "naafquery" )) {
							// tbrMidlet.naaf.setQueryData(model);
							tbrMidlet.naaf
									.setPrevDisplayable( tbrMidlet.mainList );
							tbrMidlet.naaf.setPatientId( idField.getString() );
							cleanUp();
							tbrMidlet.startTBReachForm( tbrMidlet.naaf );
						}
					}
				}
			}
		}

		else if (c == cmdBack) {
			deleteAll();
			removeCommand( cmdOK );
			removeCommand( cmdBack );
			tbrMidlet.setDisplay( prevDisplayable );
		}

	}

	private String createRequestPayload() {

		String request = null;
		String id = idField.getString();

		request = "type=qf&qtype=" + formType;
		request += "&un=" + tbrMidlet.getCurrentUser();
		request += "&pid=" + id;

		return request;
	}

	public void init() {

		idField = new TextField( "???????????????????????????????? ?????????? ????????????????" , "" , 8 ,
				TextField.NUMERIC );
		idConfirm = new TextField( "???????????????????????????????? ?????????? ???????????????? (??????????????????)" , "" , 8 ,
				TextField.NUMERIC );
		cmdOK = new Command( "????????" , Command.OK , 0 );
		cmdBack = new Command( "???? ??????????" , Command.BACK , 1 );

		append( idField );
		append( idConfirm );
		addCommand( cmdOK );
		addCommand( cmdBack );

		setCommandListener( this );
	}

	public boolean validate() {
		boolean result = true;

		int check = -1;
		if (idField.getString() == null || idField.getString().length() == 0) {
			tbrMidlet.showAlert( ErrMsg.ID_MISSING , null );
			result = false;
		}
		/*
		 * else if((check
		 * =IdValidateUtil.validateId(idField.getString()))!=IdValidateUtil
		 * .ID_VALID) { if(check==IdValidateUtil.BAD_ID_LENGTH) {
		 * tbrMidlet.showAlert(ErrMsg.INVALID_ID_LENGTH,null); }
		 * 
		 * else if(check==IdValidateUtil.BAD_GP_NUMBER) {
		 * tbrMidlet.showAlert(ErrMsg.INVALID_GP_NUMBER_IN_ID,null); }
		 * 
		 * else if(check==IdValidateUtil.BAD_YEAR) {
		 * tbrMidlet.showAlert(ErrMsg.INVALID_YEAR_IN_ID,null); }
		 * 
		 * result = false; }
		 */
		else if (!idField.getString().equals( idConfirm.getString() )) {
			tbrMidlet.showAlert( ErrMsg.ID_MISMATCH , null );
			result = false;
		}

		return result;
	}

	public void cleanUp() {
		deleteAll();
		removeCommand( cmdOK );
		removeCommand( cmdBack );
	}
}
