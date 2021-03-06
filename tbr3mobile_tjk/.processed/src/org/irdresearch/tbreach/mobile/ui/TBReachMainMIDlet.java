/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package org.irdresearch.tbreach.mobile.ui;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import java.util.Hashtable;

import org.irdresearch.tbreach.mobile.constants.Tbr2Version;
import org.irdresearch.tbreach.mobile.model.Settings;
import org.irdresearch.tbreach.mobile.model.MessageEntry;

import org.irdresearch.tbreach.mobile.util.HttpSender;

public class TBReachMainMIDlet extends MIDlet {
	
	public MainList						mainList;
	private LoginForm					loginForm;
	public SuspectIDForm				sif;
	public SuspectConfirmationForm		scf;
	public SuspectVerificationForm		svf;
	public PatientGPSForm				pgf;
	public RefusalForm					rf;
	public SputumCollectionForm			spcf;
	public PatientInfoForm				pif;
	public PatientTBHistoryForm			ptbf;
	public BaselineTreatmentForm		btf;
	public FollowupTreatmentForm		ftf;
	public DrugAdministrationForm		daf;
	public DrugDispensationForm			drdf;
	public EndFollowupForm				eff;
	public MRNumberForm					maf;
	private HttpSender					hs;
	public QueryForm					qf;
	public SearchForm					sf;
	public SputumQueryForm				sqf;
	public MRIDSearchForm				lsf;
	public DFR							dfr;
	public ClinicalDiagnosisForm		cdf;
	public PaedConfirmationForm			pcf;
	public ContactTracingNewSuspect		ctns;
	public PaedClinicalDiagnosis		pcdf;
	public ContactSputumCollectionForm	cspcf;
	public PatientFollowupEffortForm	pfupeff;
	public PaedClinicalVisitForm		pclvisf;
	public ClinicalVisitForm			clvisf;
	public DOTSNumberAssignmentForm		dnaf;
	public AddressUpdateForm			auf;
	public PatientVerificationForm		pvf;
	public NoActiveFollowupForm			naaf;
	private Display						display;
	private Settings					settings;
	public SputumResultsForm			srf;
	public PatientRegistration			preg;
	public BaselineForm					bslfrm;
	public MonitoringForm				mtrfrm;
	public SurveyForm					surfrm;

	private String						currentUser;
	private String						currentUserId;
	private int							currentRole;

	public String getCurrentUserId() {
		return currentUserId;
	}

	public void setCurrentUserId(String currentUserId) {
		this.currentUserId = currentUserId;
	}

	public String getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}

	public int getCurrentRole() {
		return currentRole;
	}

	public void setCurrentRole(int currentRole) {
		this.currentRole = currentRole;
	}
	
	public TBReachMainMIDlet() {
		mainList = new MainList("?????????? ??????????", this);
		loginForm = new LoginForm("Login", this);
		settings  = new Settings();
		sif = new SuspectIDForm("?????????????? ?????????????????? ???????? ??????????", this);
		preg = new PatientRegistration("?????????????????????????? ????????????????", this);
		bslfrm = new BaselineForm("?????????????????? ????????????", this);
		mtrfrm = new MonitoringForm("????????????????????", this);
		surfrm = new SurveyForm("???????????? ????????????????", this);
		scf = new SuspectConfirmationForm("Confirm TB Suspect", this);
		svf = new SuspectVerificationForm("Verify TB Suspect", this);
		if(isGPSAvailable())
			pgf = new PatientGPSForm("Patient GPS",this);
		pif = new PatientInfoForm("Patient Information", this);
		ptbf = new PatientTBHistoryForm("Patient TB History", this);
		rf = new RefusalForm("Refusal Form", this);
		spcf = new SputumCollectionForm("Sputum Collection", this);
		srf = new SputumResultsForm("Sputum Result", this);
		hs = new HttpSender();
		//hs.setTbrMidlet(this);
		qf = new QueryForm("Query Server", this, null);
		btf = new BaselineTreatmentForm("Baseline Treatment", this);
		ftf = new FollowupTreatmentForm("Follow-up Treatment", this);
		daf = new DrugAdministrationForm("Drug Administration",this);
		drdf = new DrugDispensationForm( "Drug Dispensation" , this );
		eff = new EndFollowupForm("End Followup", this);
		maf = new MRNumberForm("Assign MR Number", this);
		sf = new SearchForm("Search", this);
		sqf = new SputumQueryForm("Search", this, null);
		lsf = new MRIDSearchForm("Search", this, null);
		dfr = new DFR("Daily Field Report", this);
		cdf = new ClinicalDiagnosisForm("Adult Clinical Diagnosis", this);
		pcf = new PaedConfirmationForm("Paed Confirmation", this);
		ctns = new ContactTracingNewSuspect("New Contact Tracing Suspect", this);
		pcdf = new PaedClinicalDiagnosis("Paed Clinical Diagnosis", this);
		cspcf = new ContactSputumCollectionForm("Contact Sputum Collection", this);
		pfupeff = new PatientFollowupEffortForm("Patient FUP Effort", this);
		pclvisf = new PaedClinicalVisitForm("Peads Clinical Visit", this);
		clvisf = new ClinicalVisitForm("Clinical Visit", this);
		dnaf = new DOTSNumberAssignmentForm("Assign DOTS Number", this);
		if(isGPSAvailable())
			auf = new AddressUpdateForm("Address Update",this);
		pvf = new PatientVerificationForm("Patient Verification", this);
		naaf = new NoActiveFollowupForm("No Active Followup", this);

	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		System.out.println("entering dapp");
		mainList = null;
		loginForm = null;
		System.out.println("leaving dapp");
	}

	protected void pauseApp() {

	}

	protected void startApp() throws MIDletStateChangeException {
		System.out.println("starting app");
		if(display==null)
			display = Display.getDisplay(this);
		
		loginForm.init();
		
		setDisplay(loginForm);
		
		//showAlert("BOO", null);
	}
	

	public Display getDisplay()
	{
		return display;
	}

	public void showAlert(String msg, String imgName) {
		javax.microedition.lcdui.Alert alert;
		alert = new javax.microedition.lcdui.Alert("ALERT!");
		alert.setString(msg);
		alert.setTimeout(Alert.FOREVER);
		
		setDisplay(alert);
	}
	
	public void startMainMenu(int userType) {
		settings.setActiveUserType(userType);
		mainList.init();
		setDisplay(mainList);
	}
	
	public void startTBReachForm(BaseTBReachForm form) {
		form.init();
		setDisplay(form);
	}
	
	public void setDisplay(javax.microedition.lcdui.Displayable d) {
		display.setCurrent(d);		
	}
	
	public Settings getSettings() {
		return settings;
	}
	
	public Hashtable sendToServer(String payload) {
		payload += "&appver=" + Tbr2Version.VERSION;
	
		try {
		
		//public MessageEntry(String url, String postParams, boolean waitForResponse, int requestMethod, boolean secure) {
		MessageEntry ms = new MessageEntry(settings.getBaseURL(), payload, true, MessageEntry.REQUEST_METHOD_HTTP_POST, false);
		//showAlert(settings.getBaseURL(),null);
		System.out.println("doing POST");
		hs.doPost(settings.getBaseURL(), ms);
		}
		
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return hs.model;
	}
	
	public static boolean isGPSAvailable() {
		boolean classFound = false;
        try {
            // this will throw an exception if JSR-179 is missing
            Class.forName("javax.microedition.location.Location");
            classFound = true;
        } catch (Exception e) {
        	// GPS is not available
        }
        return classFound;
	}
	
}
