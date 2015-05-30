/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package org.irdresearch.tbreach2.server;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.set.CompositeSet.SetMutator;
import org.irdresearch.tbreach2.mobileevent.XmlUtil;
import org.irdresearch.tbreach2.shared.model.BaselineDetails;
import org.irdresearch.tbreach2.shared.model.LabMapping;
import org.irdresearch.tbreach2.shared.model.LabMappingId;
import org.irdresearch.tbreach2.shared.model.Location;
import org.irdresearch.tbreach2.shared.model.MonitoringResults;
import org.irdresearch.tbreach2.shared.model.PatientDetails;
import org.irdresearch.tbreach2.shared.model.Screening;
import org.irdresearch.tbreach2.shared.model.Users;

/**
 * Servlet implementation class changeLocationServlet
 */
public class editServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServerServiceImpl ssl;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ssl = new ServerServiceImpl ();
		RequestDispatcher dispatcher;
        String dest="/EditForm.jsp";
		String username = request.getParameter("upid");
		String formName = request.getParameter("frm");
		
		boolean flag = false;
		
		// Fields from registration form, that needs to validated.
		String fn = request.getParameter ("firstname");
		String ln = request.getParameter ("lastname");
		String dob = request.getParameter ("dob");
		String dTreatment = request.getParameter ("dateTreatment");
		String phone1 = request.getParameter ("phone1");
		String homephone = request.getParameter ("homephone");
		String addhouse = request.getParameter ("addhouse");
		String adddistrict = request.getParameter ("adddistrict");
		String addflat = request.getParameter ("addflat");
		String addstreet = request.getParameter ("addstreet");
		String relationship = request.getParameter ("relationshipOption");
		
		// Fields from baseline form, that needs to validated.
		String weightbd = request.getParameter ("weightbd");
		String xrayresult = request.getParameter ("xrayresult");
		String stretopmycin = request.getParameter ("streptomycin");
		
		// Fields from monitoring form, that needs to validated.
		String oneSideEffect = request.getParameter ("option1");
		String twoSideEffect = request.getParameter ("option2");
		String threeSideEffect = request.getParameter ("option3");
		String fourSideEffect = request.getParameter ("option4");
		String fiveSideEffect = request.getParameter ("option5");
		String sixSideEffect = request.getParameter ("option6");
		String otherSideEffect = request.getParameter ("othersideeffect");
		String oneFacility = request.getParameter ("facility1");
		String twoFacility = request.getParameter ("facility2");
		String threeFacility = request.getParameter ("facility3");
		String fourFacility = request.getParameter ("facility4");
		String expSideEffect = request.getParameter ("expsideeffect");
		String doseconsumption = request.getParameter("doseconsumption");
		System.out.println ("doseconsumption:"+doseconsumption);
		Date dateOfBirth = null;
		Date dateTreatment = null;
		
		// If monitoring form is edited - Check for validations
		if(formName.equalsIgnoreCase ("monform")){
			if(expSideEffect.equals ("1")){
				if(oneSideEffect == null && twoSideEffect == null && threeSideEffect == null && fourSideEffect == null && fiveSideEffect == null && sixSideEffect == null){
					String error = "Select atleast one option";
					request.setAttribute ("sideEffecterr", error);
					flag = true;
				}
				if(sixSideEffect != null){
					if(otherSideEffect == null || otherSideEffect == ""){
						String error = "Please specify other";
						request.setAttribute ("othersideEffecterr", error);
						flag = true;	
					}
				}
				if(oneFacility == null && twoFacility == null && threeFacility == null && fourFacility == null){
					String error = "Select atleast one option";
					request.setAttribute ("facilityerr", error);
					flag = true;
				}
			}
		}
		
		// If baseline form is edited - Check for validations
		if(formName.equalsIgnoreCase ("baseform")){
			String regex = "[0-9]+";
			if(!weightbd.matches (regex)){
				String error = "Invalid Weight";
				request.setAttribute ("weighterr",error);
				flag = true;
			}
			else{
				int weight = Integer.parseInt(weightbd);
				if(weight < 1 || weight > 300) {
					String error = "Invalid Weight";
					request.setAttribute ("weighterr",error);
					flag = true;
				}
			}
			if(stretopmycin != null){
				if(stretopmycin.equals ("---")){
					String error = "Select valid option ";
					request.setAttribute ("streerr",error);
					flag = true;
				}
			}
		}
		
		// If registration form is edited - Check for validations
		if(formName.equalsIgnoreCase ("regform")){
			
			if(relationship.equals ("1")){
				String other = request.getParameter ("other");
				if(other.equals ("") || other == null){
					String error = "Specify other.";
					request.setAttribute ("othererr",error);
					flag = true;
				}
			}
			if(fn == null || fn.equals ("")){
				String error = "First Name can't be empty.";
				request.setAttribute ("fnerr",error);
				flag = true;
			}
			if(ln == null || ln.equals ("")){
				String error = "Last Name can't be empty.";
				request.setAttribute ("lnerr", error);
				flag = true;
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
			if(dob == null || dob.equals ("")){
				String error = "Date of Birth can't be empty";
				request.setAttribute ("doberr", error);
				flag = true;
			}else{
				try
				{
				    dateOfBirth = formatter.parse(dob);
				    Date date = new Date();
					
					if(dateOfBirth.after(date)){
						String error = "Date of Birth is invalid";
						request.setAttribute ("doberr", error);
						flag = true;
					}
				}
				catch (ParseException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
					String error = "Date of Birth is invalid";
					request.setAttribute ("doberr", error);
					flag = true;
				}	
			}
			if(dTreatment == null || dTreatment.equals ("")){
				String error = "Treatment Inititaion date can't be empty";
				request.setAttribute ("treatdateerr", error);
				flag = true;
			}
			else{
				try{	
					dateTreatment = formatter.parse (dTreatment);
	                Date date = new Date();
					
					if(dateTreatment.after(date)){
						String error = "Treatment Initiation Date is invalid";
						request.setAttribute ("treatdateerr", error);
						flag = true;
					}
				   }
				catch (ParseException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
					String error = "Treatment Initiation date is invalid";
					request.setAttribute ("treatdateerr", error);
					flag = true;
				}
			}
			String regex = "[0-9]+";
			if(phone1 == null || phone1.equals ("")){
				String error = "Mobile Number can't be empty";
				request.setAttribute ("phone1err", error);
				flag = true;
			}else{
				if(!phone1.matches (regex)){
					String error = "Mobile number is invalid";
					request.setAttribute ("phone1err", error);
					flag = true;
				}
			}
			if(homephone == null || homephone.equals("")){
				String error = "Home Phone Number can't be empty";
				request.setAttribute ("homephoneerr", error);
				flag = true;
			}else{
				if(!homephone.matches (regex)){
					String error = "Home phone nuumber is invalid";
					request.setAttribute ("homephoneerr", error);
					flag = true;
				}
			}
			if(addhouse == null || addhouse.equals ("")){
				String error = "Address can't be empty";
				request.setAttribute ("addhouseerr", error);
				flag = true;
			}
			
		}
		
		 // In case of error
			if(flag){
				
		   // When registration form is edited - return back all fields that were filled 
			if(formName.equalsIgnoreCase ("regform")){
				PatientDetails p1 = null;
				p1 = ssl.findPatientByPatientID (username);
				request.setAttribute ("upid", username);
			    request.setAttribute ("patreg", "yes");
			    request.setAttribute("addhouse", addhouse);
			    request.setAttribute("addstreet", addstreet);
			    request.setAttribute("adddistrict", adddistrict);
			    request.setAttribute("addflat", addflat);
			    request.setAttribute("phone1", phone1);
			    request.setAttribute("homephone", homephone);
			    request.setAttribute("hw", p1.getHealthWorkerID ());
			    request.setAttribute("realtionshipfamily", request.getParameter ("relationshipOption"));
			    request.setAttribute("maritalstatus", request.getParameter ("MartialOption"));
			    request.setAttribute("education", request.getParameter ("EducationOption"));
			    request.setAttribute("incomefamily", request.getParameter ("FamilyOption"));
			    request.setAttribute("gender", request.getParameter ("genderOption"));
			    request.setAttribute("firstname", fn);
			    request.setAttribute("lastname", ln);
			    request.setAttribute("dob", request.getParameter ("dob"));
			    request.setAttribute ("dateTreatment", request.getParameter ("dateTreatment"));
			    request.setAttribute("regmsg", "Fix all error(s) before updating the form.");
			}else{
				// else return from the database
				PatientDetails pd = null;
				pd = ssl.findPatientByPatientID (username);
				request.setAttribute ("upid", username);
				request.setAttribute ("patreg", "yes");
				request.setAttribute("addhouse", pd.getAddressHouse ());
				request.setAttribute("addstreet", pd.getAddressStreet ());
				request.setAttribute("adddistrict", pd.getAddressDistrict ());
				request.setAttribute("addflat", pd.getAddressFlat ());
				request.setAttribute("phone1", pd.getPhone1 ());
				request.setAttribute("homephone", pd.getHomePhone ());
				request.setAttribute("hw", pd.getHealthWorkerID ());
				request.setAttribute("realtionshipfamily", pd.getRelationshipFamily ());
				request.setAttribute("maritalstatus", pd.getMaritalStatus ());
				request.setAttribute("education", pd.getEducation ());
				request.setAttribute("incomefamily", pd.getIncomeFamilyMember ());
				request.setAttribute("gender", pd.getGender ());
				request.setAttribute("firstname", pd.getFirstName ());
				request.setAttribute("lastname", pd.getlastName ());
				request.setAttribute("dob", String.valueOf (pd.getdob ()));
				request.setAttribute ("dateTreatment",String.valueOf(pd.getStartTreatment ()));
			}
			// When baseline form is edited - return back all fields that were filled 
			if(formName.equalsIgnoreCase ("baseform")){
				BaselineDetails bd = ssl.findBaselineByPatientID (username);
				request.setAttribute ("basdet", "yes");
				request.setAttribute ("hwbd", bd.getChwId ());
				request.setAttribute ("baselinesputumbd", request.getParameter ("baselinesputum"));
				request.setAttribute ("baselinechestbd", request.getParameter ("baselinechest"));
				request.setAttribute ("baselinegenexpertbd", request.getParameter ("genexpertresult"));
				request.setAttribute ("drugsensitivity", request.getParameter ("genexpertdrugresult"));
				request.setAttribute ("catpatientbd",  request.getParameter ("catpatientbd"));
				request.setAttribute ("typepatientbd", request.getParameter ("patienttype"));
				request.setAttribute ("weightbd", request.getParameter ("weightbd"));
				request.setAttribute ("regimenbd", request.getParameter ("regimenbd"));
				request.setAttribute ("fixeddosebd", request.getParameter ("fixeddose"));
				String strepto = request.getParameter ("streptomycin");
				if(!(strepto == null || strepto == ""))
				request.setAttribute ("streptomycinbd", strepto);
				request.setAttribute ("otherxraysitebd", request.getParameter ("anyotherxray"));
				request.setAttribute ("xrayresultbd", request.getParameter ("xrayresult"));
				request.setAttribute ("treatmentdatebd", String.valueOf (bd.getTreatmentInitiation ()));
				request.setAttribute("basemsg", "Fix all error(s) before updating the form.");
			}
			else{ // else return from the database
				long dno = 0;
				try
				{
					dno = ssl.count ("baselinedetails", "where patientId='" + username + "'");
				}
				catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (dno != 0){
					BaselineDetails bd = ssl.findBaselineByPatientID (username);
					request.setAttribute ("basdet", "yes");
					request.setAttribute ("hwbd", bd.getChwId ());
					request.setAttribute ("baselinesputumbd", bd.getBaselineSputum ());
					request.setAttribute ("baselinechestbd", bd.getBaselineChest ());
					request.setAttribute ("baselinegenexpertbd", bd.getBaselineGeneXpert ());
					request.setAttribute ("drugsensitivity", bd.getGeneXpertDrugSensitivity ());
					request.setAttribute ("baselinegenexpertbd", bd.getBaselineGeneXpert ());
					request.setAttribute ("catpatientbd", bd.getPatientCategory ());
					request.setAttribute ("typepatientbd", bd.getTypePatient ());
					request.setAttribute ("weightbd", bd.getWeight ());
					request.setAttribute ("regimenbd", bd.getRegimen ());
					request.setAttribute ("fixeddosebd", bd.getFixedDose ());
					if(!(bd.getStrepto() == null || bd.getStrepto() == ""))
					  request.setAttribute ("streptomycinbd", bd.getStrepto ());
					request.setAttribute ("otherxraysitebd", bd.getOtherXraySite ());
					request.setAttribute ("xrayresultbd", bd.getXrayResult ());
					request.setAttribute ("treatmentdatebd", String.valueOf (bd.getTreatmentInitiation ()));
				}
			}
		
            if(formName.equalsIgnoreCase ("monform")){ // When monitoring form is edited - return back all fields that were filled 
				 // Seems No error checks needed in monitoring form?
            	// You were kidding right -_-
            	request.setAttribute("monthcurr",request.getParameter ("Month_list"));
				request.setAttribute ("hwmdcurr",request.getParameter ("formSubmit"));
				request.setAttribute ("baselinesmearmdcurr", request.getParameter ("basesmear"));
				request.setAttribute ("smearresultcurr", request.getParameter ("smearresult"));
				request.setAttribute ("patientfeelcurr", request.getParameter ("patientfeel"));
				if(request.getParameter ("expsideeffect").equals ("1")){
				   request.setAttribute ("expsideeffectcurr","1");
				   if (oneSideEffect != null) 
					   request.setAttribute ("oneSideEffect",oneSideEffect);
				   if (twoSideEffect != null) 
					   request.setAttribute ("twoSideEffect",twoSideEffect);
				   if (threeSideEffect != null) 
					   request.setAttribute ("threeSideEffect",threeSideEffect);
				   if (fourSideEffect != null) 
					   request.setAttribute ("fourSideEffect",fourSideEffect);
				   if (fiveSideEffect != null) 
					   request.setAttribute ("fiveSideEffect",fiveSideEffect);
				   if (sixSideEffect != null) 
					   request.setAttribute ("sixSideEffect",sixSideEffect);
				}
				else request.setAttribute ("expsideeffectcurr","2");
				request.setAttribute ("othersideeffectcurr", request.getParameter ("othersideeffect"));
				if(request.getParameter ("patientconsult").equals("1")){
				   request.setAttribute ("patientconsultcurr", "1");
				   if (oneFacility != null) 
					   request.setAttribute ("oneFacility",oneFacility);
				   if (twoFacility != null) 
					   request.setAttribute ("twoFacility",twoFacility);
				   if (threeFacility != null) 
					   request.setAttribute ("threeFacility",threeFacility);
				   if (fourFacility != null) 
					   request.setAttribute ("fourFacility",fourFacility);
				} else request.setAttribute ("patientconsultcurr", "2");
				if(request.getParameter ("missmedication").equals("1")){
					request.setAttribute ("missmedicationcurr", "1");
					request.setAttribute ("durationmissmedicationcurr", request.getParameter ("durationmissmedication"));
				}
				else request.setAttribute ("missmedicationcurr", "2");
				if(request.getParameter ("medicinewrapper").equals("1"))
					request.setAttribute ("medicinewrappercurr", "1");
				else {
					 request.setAttribute ("medicinewrappercurr", "2");
				     request.setAttribute ("doseconsumptioncurr", request.getParameter("doseconsumption"));
				}
				
				request.setAttribute("monmsg", "Fix all error(s) before updating the form.");
            	String result = null;
				request.setAttribute ("mondet", "yes");
				MonitoringResults[] mrs = ssl.findMonitoringResultsByPatientId (username);
				for (MonitoringResults mr : mrs){   
				     if(result == null)
						result = mr.getCurrentMonth () + " ";
					else
						result = result + mr.getCurrentMonth () + " ";
										
					int mon = mr.getCurrentMonth ();
					request.setAttribute ("hwmd"+mon,mr.getHealthWorkerId ());
					request.setAttribute ("treatmentdatemd"+mon,String.valueOf (mr.getDateofTreatment ()));
					request.setAttribute ("baselinesmearmd"+mon, mr.getBaselineSmear ());
					request.setAttribute ("smearresult"+mon, mr.getSmearResult ());
					request.setAttribute ("patientfeel"+mon, mr.getPatientFeel ());
					request.setAttribute ("expsideeffect"+mon, mr.getExperienceSideEffects ());
					if(mr.getExperienceSideEffects ().equalsIgnoreCase ("YES"))
					   request.setAttribute ("sideeffect"+mon, mr.getPatientSideEffects ());
					request.setAttribute ("othersideeffect"+mon, mr.getOtherSideEffects ());
					request.setAttribute ("patientconsult"+mon, mr.getPatientConsult ());
					request.setAttribute ("healthfacility"+mon, mr.getHealthFacility ());
					request.setAttribute ("missmedication"+mon, mr.getMissMedication ());
					request.setAttribute ("durationmissmedication"+mon, mr.getDurationMissMedication ());
					request.setAttribute ("medicinewrapper"+mon, mr.getMedicineWrappers ());
					request.setAttribute ("doseconsumption"+mon, mr.getDoseConsumption ());
					}
				request.setAttribute ("months", result);
			}
            else{	// return all from the database
            	long mno = 0;
				try
				{
					mno = ssl.count ("monitoringresults", "where patientId='" + username + "'");
				}
				catch (Exception e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(mno != 0){
				String result = null;
				request.setAttribute ("mondet", "yes");
				MonitoringResults[] mrs = ssl.findMonitoringResultsByPatientId (username);
				for (MonitoringResults mr : mrs){   
				     if(result == null)
						result = mr.getCurrentMonth () + " ";
					else
						result = result + mr.getCurrentMonth () + " ";
										
					int mon = mr.getCurrentMonth ();
					request.setAttribute ("hwmd"+mon,mr.getHealthWorkerId ());
					request.setAttribute ("treatmentdatemd"+mon,String.valueOf (mr.getDateofTreatment ()));
					request.setAttribute ("baselinesmearmd"+mon, mr.getBaselineSmear ());
					request.setAttribute ("smearresult"+mon, mr.getSmearResult ());
					request.setAttribute ("patientfeel"+mon, mr.getPatientFeel ());
					request.setAttribute ("expsideeffect"+mon, mr.getExperienceSideEffects ());
					if(mr.getExperienceSideEffects ().equalsIgnoreCase ("YES"))
					   request.setAttribute ("sideeffect"+mon, mr.getPatientSideEffects ());
					request.setAttribute ("othersideeffect"+mon, mr.getOtherSideEffects ());
					request.setAttribute ("patientconsult"+mon, mr.getPatientConsult ());
					request.setAttribute ("healthfacility"+mon, mr.getHealthFacility ());
					request.setAttribute ("missmedication"+mon, mr.getMissMedication ());
					request.setAttribute ("durationmissmedication"+mon, mr.getDurationMissMedication ());
					request.setAttribute ("medicinewrapper"+mon, mr.getMedicineWrappers ());
					request.setAttribute ("doseconsumption"+mon, mr.getDoseConsumption ());
					}
				request.setAttribute ("months", result);
                 }
               }
				
		        dispatcher=getServletContext().getRequestDispatcher(dest);
		        dispatcher.forward(request,response);
		        return;
			}
			
			// In case of no Error - save the form in the database.
			if(formName.equalsIgnoreCase ("regform")){
			    PatientDetails p1 = null;
				p1 = ssl.findPatientByPatientID (username);
				
				p1.setFirstName (fn);
				p1.setLastName (ln); 
				p1.setGender (request.getParameter ("genderOption"));
				p1.setAddressHouse (addhouse);
				p1.setAddressStreet (addstreet);
				p1.setAddressFlat (addflat);
				p1.setAddressDistrict (adddistrict);
				p1.setPhone1 (phone1);
				p1.setHomePhone (homephone);
				p1.setdob (dateOfBirth);
				p1.setStartTreatment (dateTreatment);
				if (relationship.equals ("1"))
					p1.setRelationshipFamily (request.getParameter ("other"));
				else
					p1.setRelationshipFamily (relationship);
				p1.setMaritalStatus (request.getParameter ("MartialOption"));
				p1.setEducation (request.getParameter ("EducationOption"));
				p1.setIncomeFamilyMember (request.getParameter ("FamilyOption"));
			    ssl.update (p1);
			    request.setAttribute("regmsg", "Form updated Successfully.");
			}
			else if(formName.equalsIgnoreCase ("baseform")){
				BaselineDetails b1 = ssl.findBaselineByPatientID (username);
				
				b1.setWeight (weightbd);
				b1.setBaselineSputum (request.getParameter ("baselinesputum"));
				b1.setBaselineChest (request.getParameter ("baselinechest"));
				b1.setBaselineGeneXpert (request.getParameter ("genexpertresult"));
				b1.setGeneXpertDrugSensitivity (request.getParameter ("genexpertdrugresult"));
				b1.setPatientCategory (request.getParameter ("catpatientbd"));
				b1.setTypePatient (request.getParameter ("patienttype"));
				b1.setWeight (request.getParameter ("weightbd"));
				b1.setRegimen (request.getParameter ("regimenbd"));
				b1.setFixedDose (request.getParameter ("fixeddose"));
				String strepto = request.getParameter ("streptomycin");
				if(!(strepto == null || strepto == ""))
				   b1.setStrepto (strepto);
				else
					b1.setStrepto(null);
				b1.setOtherXraySite (request.getParameter ("anyotherxray"));
				b1.setXrayResult (request.getParameter ("xrayresult"));
				ssl.update (b1);
			    request.setAttribute("basemsg", "Form updated Successfully.");
			    
			} 
			else if(formName.equalsIgnoreCase ("monform")){
				String month = request.getParameter ("Month_list");
				MonitoringResults mr = ssl.findMonitoringResultsByPatientIDAndMonth (username, month);
				mr.setSmearResult(request.getParameter ("smearresult"));
				mr.setPatientFeel (request.getParameter ("patientfeel"));
				if(request.getParameter ("expsideeffect").equals ("1")){
					mr.setExperienceSideEffects ("YES");
					String sideEffects = ( (oneSideEffect ==null) ? "" : oneSideEffect+",") + ( (twoSideEffect ==null) ? "" : twoSideEffect+",") + ( (threeSideEffect ==null) ? "" : threeSideEffect+",") +
					( (fourSideEffect ==null) ? "" : fourSideEffect+",") + ( (fiveSideEffect ==null) ? "" : fiveSideEffect+",") + ( (sixSideEffect ==null) ? "" : otherSideEffect+",");
	                sideEffects = sideEffects.substring (0,sideEffects.length ()-1);
	                mr.setPatientSideEffects (sideEffects);
	                if(request.getParameter ("patientconsult").equals ("1")){
	                	mr.setPatientConsult ("YES");
	                	String healthFacility = ( (oneFacility ==null) ? "" : oneFacility+",") + ( (twoFacility ==null) ? "" : twoFacility+",") + ( (threeFacility ==null) ? "" : threeFacility+",") + ( (fourFacility ==null) ? "" : fourFacility+",");
	                	healthFacility = healthFacility.substring (0,healthFacility.length ()-1);
	                	mr.setHealthFacility (healthFacility);
	                }  
				}
				else {
					mr.setExperienceSideEffects ("NO");
					mr.setPatientSideEffects (null);
					mr.setOtherSideEffects (null);
					mr.setPatientConsult (null);
					mr.setHealthFacility (null);
				}
				 if(request.getParameter ("missmedication").equals ("1")){
					 mr.setMissMedication ("YES");
					 mr.setDurationMissMedication (request.getParameter ("durationmissmedication"));
				 } else {
					 mr.setMissMedication ("NO");
					 mr.setDurationMissMedication (null);
				 }
				 if(request.getParameter ("medicinewrapper").equals ("1")){
					 mr.setMedicineWrappers ("YES");
					 mr.setDoseConsumption (null);
				 } else {
					 mr.setMedicineWrappers ("NO");
					 mr.setDoseConsumption (request.getParameter ("doseconsumption"));
				 }
				 
				 ssl.update (mr);
				 request.setAttribute("monmsg", "Form updated Successfully.");
			}
		
	     // Return all the forms updated fields to client side.
		Screening screening = null;
		try
		{
			screening = ssl.findScreeningByPatientID(username);
		}
		catch (Exception e)
		{
			request.setAttribute("errorattribute", "Error while finding patient");
	        dispatcher=getServletContext().getRequestDispatcher(dest);
	        dispatcher.forward(request,response);
	        return;
		}
				
		if (screening == null)
		{
			request.setAttribute("errorattribute", "Patient doesn't exists!");
	        dispatcher=getServletContext().getRequestDispatcher(dest);
	        dispatcher.forward(request,response);
	        return;
		}
		
		
		try
		{
			long pno = ssl.count ("patientdetails", "where pid='" + username + "'" );
			if (pno == 0){
				request.setAttribute("errorattribute", "OR forms for the patient are not filled!");
		        dispatcher=getServletContext().getRequestDispatcher(dest);
		        dispatcher.forward(request,response);
		        return;	
			}		  	
		}
		catch (Exception e3)
		{
			// TODO Auto-generated catch block
			e3.printStackTrace();
			request.setAttribute("errorattribute", "Error while finding forms.");
	        dispatcher=getServletContext().getRequestDispatcher(dest);
	        dispatcher.forward(request,response);
	        return;
		} 	
		
		PatientDetails pd = null;
		pd = ssl.findPatientByPatientID (username);
		
		
		
		try{
			    request.setAttribute ("upid", username);
			    request.setAttribute ("patreg", "yes");
			    request.setAttribute("addhouse", pd.getAddressHouse ());
			    request.setAttribute("addstreet", pd.getAddressStreet ());
			    request.setAttribute("adddistrict", pd.getAddressDistrict ());
			    request.setAttribute("addflat", pd.getAddressFlat ());
			    request.setAttribute("phone1", pd.getPhone1 ());
			    request.setAttribute("homephone", pd.getHomePhone ());
			    request.setAttribute("hw", pd.getHealthWorkerID ());
			    request.setAttribute("realtionshipfamily", pd.getRelationshipFamily ());
			    request.setAttribute("maritalstatus", pd.getMaritalStatus ());
			    request.setAttribute("education", pd.getEducation ());
			    request.setAttribute("incomefamily", pd.getIncomeFamilyMember ());
			    request.setAttribute("gender", pd.getGender ());
			    request.setAttribute("firstname", pd.getFirstName ());
			    request.setAttribute("lastname", pd.getlastName ());
			    request.setAttribute("dob", String.valueOf (pd.getdob ()));
			    request.setAttribute ("dateTreatment",String.valueOf(pd.getStartTreatment ()));
			    
			    try{
					
					long dno =ssl.count ("baselinedetails", "where patientId='" + username + "'");
					if (dno != 0){
						BaselineDetails bd = ssl.findBaselineByPatientID (username);
						request.setAttribute ("basdet", "yes");
						request.setAttribute ("hwbd", bd.getChwId ());
						request.setAttribute ("baselinesputumbd", bd.getBaselineSputum ());
						request.setAttribute ("baselinechestbd", bd.getBaselineChest ());
						request.setAttribute ("baselinegenexpertbd", bd.getBaselineGeneXpert ());
						request.setAttribute ("drugsensitivity", bd.getGeneXpertDrugSensitivity ());
						request.setAttribute ("baselinegenexpertbd", bd.getBaselineGeneXpert ());
						request.setAttribute ("catpatientbd", bd.getPatientCategory ());
						request.setAttribute ("typepatientbd", bd.getTypePatient ());
						request.setAttribute ("weightbd", bd.getWeight ());
						request.setAttribute ("regimenbd", bd.getRegimen ());
						request.setAttribute ("fixeddosebd", bd.getFixedDose ());
						if(!(bd.getStrepto() == null || bd.getStrepto() == ""))
						  request.setAttribute ("streptomycinbd", bd.getStrepto ());
						request.setAttribute ("otherxraysitebd", bd.getOtherXraySite ());
						request.setAttribute ("xrayresultbd", bd.getXrayResult ());
						request.setAttribute ("treatmentdatebd", String.valueOf (bd.getTreatmentInitiation ()));
						
						try{
							
							long mno = ssl.count ("monitoringresults", "where patientId='" + username + "'");
							if(mno != 0){
								String result = null;
							    request.setAttribute ("mondet", "yes");
							    MonitoringResults[] mrs = ssl.findMonitoringResultsByPatientId (username);
								for (MonitoringResults mr : mrs)
								{   
									if(result == null)
									  result = mr.getCurrentMonth () + " ";
									else
								      result = result + mr.getCurrentMonth () + " ";
									
									int mon = mr.getCurrentMonth ();
									request.setAttribute ("hwmd"+mon,mr.getHealthWorkerId ());
									request.setAttribute ("treatmentdatemd"+mon,String.valueOf (mr.getDateofTreatment ()));
									request.setAttribute ("baselinesmearmd"+mon, mr.getBaselineSmear ());
									request.setAttribute ("smearresult"+mon, mr.getSmearResult ());
									request.setAttribute ("patientfeel"+mon, mr.getPatientFeel ());
									request.setAttribute ("expsideeffect"+mon, mr.getExperienceSideEffects ());
									if(mr.getExperienceSideEffects ().equalsIgnoreCase ("YES"))
									   request.setAttribute ("sideeffect"+mon, mr.getPatientSideEffects ());
									request.setAttribute ("othersideeffect"+mon, mr.getOtherSideEffects ());
									request.setAttribute ("patientconsult"+mon, mr.getPatientConsult ());
									request.setAttribute ("healthfacility"+mon, mr.getHealthFacility ());
									request.setAttribute ("missmedication"+mon, mr.getMissMedication ());
									request.setAttribute ("durationmissmedication"+mon, mr.getDurationMissMedication ());
									request.setAttribute ("medicinewrapper"+mon, mr.getMedicineWrappers ());
									request.setAttribute ("doseconsumption"+mon, mr.getDoseConsumption ());
								}
							    request.setAttribute ("months", result);								
							}
						}catch(Exception e ){
							
							e.printStackTrace();
							request.setAttribute("errorattribute", "Error while finding forms.");
					        dispatcher=getServletContext().getRequestDispatcher(dest);
					        dispatcher.forward(request,response);
					        return;
						
						}
					}
				}
				catch (Exception e2)
				{
					e2.printStackTrace ();		
				}
			
				request.setAttribute("usersattribute", "Updated Successfully!");
	            dispatcher=getServletContext().getRequestDispatcher(dest);
	            dispatcher.forward(request,response);
	            return;
			}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorattribute", "Error while finding forms.");
	        dispatcher=getServletContext().getRequestDispatcher(dest);
	        dispatcher.forward(request,response);
	        return;
		}
		
	}

}
