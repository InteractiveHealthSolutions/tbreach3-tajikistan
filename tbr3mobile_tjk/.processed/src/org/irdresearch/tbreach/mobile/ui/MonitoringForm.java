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

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;

import org.irdresearch.tbreach.mobile.constants.XmlStrings;
import org.irdresearch.tbreach.mobile.model.FormType;
import org.irdresearch.tbreach.mobile.util.DateTimeUtil;
import org.irdresearch.tbreach.mobile.util.StringUtil;

public class MonitoringForm extends BaseTBReachForm implements CommandListener, ItemCommandListener, ItemStateListener{
	
	String month2 = null;
	String month3 = null;
	String month4 = null;
	String month5 = null;
	String month6 = null;
	String month7 = null;
	String month8 = null;
	String month9 = null;
	int experiencedSideEffectIndex;
	
	TextField participantID;
	TextField startTreatment;
//	TextField healthWorkerID;
	TextField baselineSmearResult;
	TextField patientCategory;
	ChoiceGroup smearResult;	
	
//	StringItem monthSmear2;
//	StringItem monthSmear3;
//	StringItem monthSmear4;
//	StringItem monthSmear5;
//	StringItem monthSmear6;
//	StringItem monthSmear7;
//	StringItem monthSmear8;
//	StringItem monthSmear9;
	
	TextField monthSmear2;
	TextField monthSmear3;
	TextField monthSmear4;
	TextField monthSmear5;
	TextField monthSmear6;
	TextField monthSmear7;
	TextField monthSmear8;
	TextField monthSmear9;
	TextField   monthTreatment;
	ChoiceGroup patientFeel;
	ChoiceGroup experiencedSideEffect;
	ChoiceGroup patientSideEffects;
	TextField otherSideEffects;
	ChoiceGroup patientConsult;
	ChoiceGroup healthFacility;
	ChoiceGroup missMedication;
	ChoiceGroup durationMissMedication;
	ChoiceGroup medicineWrappers;
	ChoiceGroup doseConsumption;
	String patientId;
	StringItem endOfForm;
	int length;
	
	Command		cmdOK;
	Command		cmdBack;
	Item[]		formItems;
	private Hashtable	queryData;
	

	public MonitoringForm( String title , TBReachMainMIDlet tbrMidlet ) {
		super( title , tbrMidlet );
	}
	
	private void show(Item item) {
		int i = indexOf( item );
		delete( i );
		insert( i , item );
	}
	
	private int indexOf(Item item) {
		for (int i = 0; i < formItems.length; i++)
			if (formItems[i] == item)
				return i;
		return -1;
	}


	private void hide(Item item) {
		int i = indexOf( item );
		delete( i );
		StringItem tmp = new StringItem( "" , "" );
		insert( i , tmp );
	}
	
	public Hashtable getQueryData() {
		return queryData;
	}

	public void setQueryData(Hashtable queryData) {
		this.queryData = queryData;
	}
	
	public void init()
	{
		
		int i = 0;
		Item items[] = new Item[30];
		
		monthsInString();
		
//		healthWorkerID = new TextField( "Личный номер медработника" , tbrMidlet.getCurrentUserId() , 10 , TextField.UNEDITABLE);
//		items[i] = healthWorkerID; i++;
		
		participantID = new TextField( "Реги�?трационный номер пациента" , (String)queryData.get("pid") , 8 , TextField.UNEDITABLE );
		items[i] = participantID; i++; 
		
		startTreatment = new TextField( "Дата начала лечени�?" , (String)queryData.get("treatmentinitate"), 10, TextField.UNEDITABLE);
		items[i] = startTreatment; i++;
		
		String baslineSmearResultR = (String)queryData.get("baselinesmear");
		if (baslineSmearResultR.equals("NONE")|| baslineSmearResultR.equals("none")|| baslineSmearResultR.equals("None"))
			baselineSmearResult = new TextField( "Микро�?копи�? мазка (диагно�?тика)" , "�?ет" , 10  , TextField.UNEDITABLE );
		if (baslineSmearResultR.equals("NEGATIVE")|| baslineSmearResultR.equals("negative")|| baslineSmearResultR.equals("Negative"))
			baselineSmearResult = new TextField( "Микро�?копи�? мазка (диагно�?тика)" , "Отр" , 10  , TextField.UNEDITABLE );
		else if (baslineSmearResultR.equals("1-9 AFB"))
			baselineSmearResult = new TextField( "Микро�?копи�? мазка (диагно�?тика)" , "1-9 КУБ" , 10  , TextField.UNEDITABLE );
		else
			baselineSmearResult = new TextField( "Микро�?копи�? мазка (диагно�?тика)" , baslineSmearResultR + "+" , 10  , TextField.UNEDITABLE );
		items[i] = baselineSmearResult; i++;
		
		if (month2 != null){
			monthSmear2 = new TextField( "Микро�?копи�? мазка (2 ме�?.)" , month2,10, TextField.UNEDITABLE);
			items[i] = monthSmear2; i++;
		}
		
		if (month3 != null){
			monthSmear3 = new TextField( "Микро�?копи�? мазка (3 ме�?.)" , month3,10, TextField.UNEDITABLE);
			items[i] = monthSmear3; i++;
		}
		
		if (month4 != null){
			monthSmear4 = new TextField( "Микро�?копи�? мазка (4 ме�?.)" , month4,10, TextField.UNEDITABLE);
			items[i] = monthSmear4; i++;
		}
		
		if (month5 != null){
			monthSmear5 = new TextField( "Микро�?копи�? мазка (5 ме�?.)" , month5,10, TextField.UNEDITABLE);
			items[i] = monthSmear5; i++;
		}
		
		if (month6 != null){
			monthSmear6 = new TextField( "Микро�?копи�? мазка (6 ме�?.)" , month6,10, TextField.UNEDITABLE);
			items[i] = monthSmear6; i++;
		}
		
		if (month7 != null ){
			monthSmear7 = new TextField( "Микро�?копи�? мазка (7 ме�?.)" , month7,10, TextField.UNEDITABLE);
			items[i] = monthSmear7; i++;
		}
		
		if (month8 != null ){
			monthSmear8 = new TextField( "Микро�?копи�? мазка (8 ме�?.)" , month8,10, TextField.UNEDITABLE);
			items[i] = monthSmear8; i++;
		}
		
		if (month9 != null ){
			monthSmear9 = new TextField( "Микро�?копи�? мазка (9 ме�?.)" , month9,10, TextField.UNEDITABLE);
			items[i] = monthSmear9; i++;
		}
		
		int month = DateTimeUtil.calculateMonthOfTreatment( (String)queryData.get("treatmentinitate") , new Date() );
		monthTreatment = new TextField( "Ме�?�?ц лечени�?" , month+"" , 2  , TextField.NUMERIC );
		items[i] = monthTreatment; i++;
		
		smearResult = new ChoiceGroup( "Микро�?копи�? мазка" , ChoiceGroup.POPUP );
        smearResult.append("�?ет" , null);
		smearResult.append( "Отр" , null );
		smearResult.append( "1-9 КУБ" , null );
		smearResult.append( "1+" , null );
		smearResult.append( "2+" , null );
		smearResult.append( "3+" , null );
		items[i] = smearResult; i++;
	
		
		patientFeel = new ChoiceGroup( "Самочув�?твие" , ChoiceGroup.POPUP );
		patientFeel.append( "Улучшение" , null );
		patientFeel.append( "Ухудшение" , null );
		patientFeel.append( "Без изменений" , null );
		patientFeel.append( "�?е знаю" , null );
		items[i] = patientFeel; i++;
		
		System.out.println("experiencedSideEffect "+i);
		experiencedSideEffect = new ChoiceGroup( "Были побочные дей�?тви�??" , ChoiceGroup.POPUP );
		experiencedSideEffect.append( "�?ет" , null );
		experiencedSideEffect.append( "Да" , null );
		items[i] = experiencedSideEffect; experiencedSideEffectIndex = i; i++;
	
		patientSideEffects = new ChoiceGroup( "Какие побочные дей�?тви�??" , ChoiceGroup.MULTIPLE );
		patientSideEffects.append( "Головна�? боль" , null );
		patientSideEffects.append( "Рвота" , null );
		patientSideEffects.append( "Боль в животе" , null );
		patientSideEffects.append( "Боль в �?у�?тавах" , null );
		patientSideEffects.append( "Ухудшение зрени�?" , null );
		patientSideEffects.append("Другие",null);
		//items[i] = patientSideEffects; i++;
		
		otherSideEffects = new TextField( "Укажите е�?ли другое." , "" , 25 , TextField.ANY);
		
		patientConsult = new ChoiceGroup( "Получали кон�?ультацию при побочных дей�?тви�?х?" , ChoiceGroup.POPUP );
		patientConsult.append( "�?ет" , null );
		patientConsult.append( "Да" , null );
		//items[i] = patientConsult; i++;
		
		healthFacility = new ChoiceGroup( "Где получали кон�?ультацию при побочных дей�?тви�?х?" , ChoiceGroup.MULTIPLE);
		healthFacility.append( "Центр здоровь�?" , null );
		healthFacility.append( "ТБ центр" , null );
		healthFacility.append( "Го�?.больница" , null );
		healthFacility.append( "Ча�?тна�? больница" , null );
		//items[i] = healthFacility; i++;
		
		System.out.println("missMedication "+i);
		missMedication = new ChoiceGroup( "Пропу�?кали ли прием препаратов в прошлом ме�?�?це?" , ChoiceGroup.POPUP );
		missMedication.append( "�?ет" , null );
		missMedication.append( "Да" , null );
		items[i] = missMedication; i++;
		
		durationMissMedication = new ChoiceGroup( "Сколько раз пропу�?кали прием препаратов?" , ChoiceGroup.POPUP );
		durationMissMedication.append( "один раз" , null );
		durationMissMedication.append( "два раза" , null );
		durationMissMedication.append( "три раза" , null );
		durationMissMedication.append( "неделю" , null );
		durationMissMedication.append( "более недели" , null );
		durationMissMedication.append( "не помню" , null );
		//items[i] = durationMissMedication; i++;
		
		System.out.println("medicineWrappers "+i);
		medicineWrappers = new ChoiceGroup( "Упаковка препаратов за прошедший ме�?�?ц пу�?та�??" , ChoiceGroup.POPUP );
		medicineWrappers.append( "Да" , null );
		medicineWrappers.append( "�?ет" , null );
		items[i] = medicineWrappers; i++;
		
		doseConsumption = new ChoiceGroup( "Сколько доз не было прин�?то?" , ChoiceGroup.POPUP );
		doseConsumption.append( "1" , null );
		doseConsumption.append( "2" , null );
		doseConsumption.append( "3" , null );
		doseConsumption.append( "4" , null );
		doseConsumption.append( "5" , null );
		doseConsumption.append( ">5" , null );
		doseConsumption.append( ">10" , null );
		doseConsumption.append( ">15" , null );
		//items[i] = doseConsumption; i++;
		
		endOfForm = new StringItem( "Конец формы" , "" );
		items[i] = endOfForm; i++;
		
		length = i;
		cmdOK = new Command( "Сабт" , Command.OK , 1 );
		cmdBack = new Command( "Ба аввал" , Command.BACK , 2 );

		/*Item items[] = {healthWorkerID , participantID, startTreatment , baselineSmearResult , 
				monthSmear2 , monthSmear3 , monthSmear4 , monthSmear5, monthSmear6, monthSmear7, monthSmear8, monthSmear9, monthTreatment, smearResult,
				 patientFeel, experiencedSideEffect, patientSideEffects, patientConsult,  healthFacility, missMedication, durationMissMedication, medicineWrappers
				,doseConsumption};*/
		
		formItems = items;
		addCommand( cmdOK );
		addCommand( cmdBack );

		startDate = DateTimeUtil.getDate();
		startTime = DateTimeUtil.getTime();
		initialShow();
		setCommandListener( this );
		setItemStateListener( this );
		
	}
	
	
	public void commandAction(Command c , Displayable d) {
		
		if (c == cmdOK) {
			if(validate()){
			
			endTime = DateTimeUtil.getTime();
			String request = createRequestPayload();
			System.out.println( request );
			Hashtable model = tbrMidlet.sendToServer( request );
			
			if (model != null) {
				String status = (String) model.get( "status" );
				

				if (status.equals( XmlStrings.SUCCESS )) {
					System.out.println( "success" );
					
						deleteAll();
						removeCommand( cmdOK );
						removeCommand( cmdBack );
						init();
						tbrMidlet.showAlert("Данные �?охранены!" , null );


				}
				else if (status.equals( XmlStrings.ERROR )) {
					System.out.println( (String) model.get( "msg" ) );
					tbrMidlet.showAlert(
							"ERROR: " + (String) model.get( "msg" ) , null );
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
	
	public String createRequestPayload()
	{
		String request = "";
		request = "type=" + FormType.PATIENT_MONITORING_FORM;
		request += "&parId=" + participantID.getString();
		request += "&startTreatment=" + startTreatment.getString();
		request += "&healthWorkerID=" + tbrMidlet.getCurrentUserId();
		request += "&monthTreatment=" + monthTreatment.getString();
		request += "&baselineSmearResult=" + baselineSmearResult.getString();
		/*request += "&monthSmear2=" + monthSmear2.getString(monthSmear2.getSelectedIndex());
		request += "&monthSmear3=" + monthSmear3.getString(monthSmear3.getSelectedIndex());
		request += "&monthSmear4=" + monthSmear4.getString(monthSmear4.getSelectedIndex());
		request += "&monthSmear5=" + monthSmear5.getString(monthSmear5.getSelectedIndex());
		request += "&monthSmear6=" + monthSmear6.getString(monthSmear6.getSelectedIndex());
		request += "&monthSmear7=" + monthSmear7.getString(monthSmear7.getSelectedIndex());
		request += "&monthSmear6Cat2=" + monthSmear6Cat2.getString(monthSmear6Cat2.getSelectedIndex());*/
		request += "&smearResult=" + smearResult.getString(smearResult.getSelectedIndex());
		request += "&startdate=" + startDate;
		request += "&stime=" + startTime;
		request += "&etime=" + endTime;
		request += "&patientFeel=" + patientFeel.getString(patientFeel.getSelectedIndex());
		request += "&experiencedSideEffect=" + experiencedSideEffect.getString(experiencedSideEffect.getSelectedIndex());
		if(experiencedSideEffect.getSelectedIndex() == 1){
			String patientSideEffect = StringUtil.getStringMultipleSelectionFromChoiceGroup( patientSideEffects );
			System.out.println(patientSideEffect);
			request += "&patientSideEffects=" + patientSideEffect;
			if(patientSideEffects.isSelected( 5 )){
				request +="&otherSideEffects=" + otherSideEffects.getString();}
		}	
		
		request += "&patientConsult=" + patientConsult.getString(patientConsult.getSelectedIndex());
		
		if(patientConsult.getSelectedIndex() == 1){
		String healthFac = StringUtil.getStringMultipleSelectionFromChoiceGroup( healthFacility );
		request += "&healthFacility=" + healthFac;}
		
		request += "&missMedication=" + missMedication.getString(missMedication.getSelectedIndex());
		if(missMedication.getSelectedIndex() == 1){
		request += "&durationMissMedication=" + durationMissMedication.getString(durationMissMedication.getSelectedIndex());}
		
		request += "&medicineWrappers=" + medicineWrappers.getString(medicineWrappers.getSelectedIndex());
		if(medicineWrappers.getSelectedIndex() == 1){
		request += "&doseConsumption=" + doseConsumption.getString( doseConsumption.getSelectedIndex() );}
			
		return request;		
	}
	
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	
	
	private void initialShow() {
		
		for (int j = 0; j < length; j++)
			append( formItems[j] );
		
		//hide(patientSideEffects);
		//hide(patientConsult);
		//hide(healthFacility);
		//hide(durationMissMedication);
		//hide(doseConsumption);
		/*if(month2 == null)
			hide(monthSmear2);
		if(month3 == null)
			hide(monthSmear3);
		if(month4 == null)
			hide(monthSmear4);
		if(month5 == null)
			hide(monthSmear5);
		if(month6 == null)
			hide(monthSmear6);
		if(month7 == null)
			hide(monthSmear7);
		if(month8 == null)
			hide(monthSmear8);
		if(month9 == null)
			hide(monthSmear9);*/
		
/*
		
		String previousMR = (String)queryData.get("previousMR");
		if (previousMR == "no"){
			String[] previousMResults=StringUtil.split( previousMR , '/' );
			
			for (int counter=0; counter<previousMResults.length-1; counter++)
			{
				int j = StringUtil.firstOccurrenceOf( previousMResults[counter] , ' ' );
				int len = previousMResults[counter].length();
				String month = previousMResults[counter].substring( 0 , j);
				int monthNumber = Integer.parseInt( month );
				String result = previousMResults[counter].substring( j+1 , len );
				String resultInR = null;	
				
				if( result.equals( "NEGATIVE" ))
					resultInR = "Отр";
				else if (result.equals( "1-9 AFB" ))
					resultInR = "1-9 КУБ";
				else
					resultInR = result;
				
				switch (monthNumber) {
		            case 2:  monthSmear2.setString( resultInR );
		            		 show(monthSmear2);
		                     break;
		            case 3:  monthSmear3.setString( resultInR );
		            		 show(monthSmear3);
		                     break;
		            case 4:  monthSmear4.setString( resultInR );
		            		 show(monthSmear4);
		                     break;
		            case 5:  monthSmear5.setString( resultInR );
		            		 show(monthSmear5);
		                     break;
		            case 6:  monthSmear6.setString( resultInR );
		            		 show(monthSmear6);
		                     break;
		            case 7:  monthSmear7.setString( resultInR );
		            		 show(monthSmear7);
		                     break;
		            case 8:  monthSmear8.setString( resultInR );
		            		 show(monthSmear8);
	                		 break;
		            case 9:  monthSmear9.setString( resultInR );
		            		 show(monthSmear9);
	                		 break;
		        }	
			}
		
	   }*/
				
	}


	public void itemStateChanged(Item i) {
		
		// deleting & inserting items again! mobile in Tajikistan seems to go through hidden fields too -_-
		
		if(i == experiencedSideEffect)
		{
			if(experiencedSideEffect.getSelectedIndex() == 1)   
			{
				deleteFromTo(experiencedSideEffect, endOfForm);
				int index = getItemIndex(experiencedSideEffect , this ); index++;
				insert( index , patientSideEffects  ); index++;
				if (patientSideEffects.isSelected( 5 )){
					insert (index,otherSideEffects); index++;
				}
				insert( index , patientConsult); index++;
				if(patientConsult.getSelectedIndex() == 1){
					insert (index , healthFacility); index++;
				}
				insert (index , missMedication); index++;
				if(missMedication.getSelectedIndex() == 1){
					insert (index , durationMissMedication); index++;
				}
				insert (index, medicineWrappers); index++;
				if(medicineWrappers.getSelectedIndex() == 1){
					insert (index , doseConsumption); index++;
				}
				insert (index,endOfForm);
			}
			else
			{
				deleteFromTo(experiencedSideEffect, endOfForm);
				int index = getItemIndex(experiencedSideEffect , this ); index++;
				insert (index , missMedication); index++;
				if(missMedication.getSelectedIndex() == 1){
					insert (index , durationMissMedication); index++;
				}
				insert (index, medicineWrappers); index++;
				if(medicineWrappers.getSelectedIndex() == 1){
					insert (index , doseConsumption); index++;
				}
				insert (index,endOfForm);
			}
		}
		
		if(i == patientSideEffects){
			
			if(patientSideEffects.isSelected( 5 ))   
			{
				deleteFromTo(patientSideEffects, endOfForm);
				int index = getItemIndex(patientSideEffects , this ); index++;
				insert (index,otherSideEffects); index++;
				insert( index , patientConsult); index++;
				if(patientConsult.getSelectedIndex() == 1){
					insert (index , healthFacility); index++;
				}
				insert (index , missMedication); index++;
				if(missMedication.getSelectedIndex() == 1){
					insert (index , durationMissMedication); index++;
				}
				insert (index, medicineWrappers); index++;
				if(medicineWrappers.getSelectedIndex() == 1){
					insert (index , doseConsumption); index++;
				}
				insert (index,endOfForm);			
			}else if(!patientSideEffects.isSelected( 5 )){
				deleteFromTo(patientSideEffects, endOfForm);
				int index = getItemIndex(patientSideEffects , this ); index++;
				insert( index , patientConsult); index++;
				if(patientConsult.getSelectedIndex() == 1){
					insert (index , healthFacility); index++;
				}
				insert (index , missMedication); index++;
				if(missMedication.getSelectedIndex() == 1){
					insert (index , durationMissMedication); index++;
				}
				insert (index, medicineWrappers); index++;
				if(medicineWrappers.getSelectedIndex() == 1){
					insert (index , doseConsumption); index++;
				}
				insert (index,endOfForm);
			}
			
		}
		
		if(i == patientConsult)
		{
			if(patientConsult.getSelectedIndex() == 1)
			{
				deleteFromTo(patientConsult, endOfForm);
				int index = getItemIndex(patientConsult , this ); index++;
				insert (index , healthFacility); index++;
				insert (index , missMedication); index++;
				if(missMedication.getSelectedIndex() == 1){
					insert (index , durationMissMedication); index++;
				}
				insert (index, medicineWrappers); index++;
				if(medicineWrappers.getSelectedIndex() == 1){
					insert (index , doseConsumption); index++;
				}
				insert (index,endOfForm);
			}
			
			else
			{
				deleteFromTo(patientConsult, endOfForm);
				int index = getItemIndex(patientConsult , this ); index++;
				insert (index , missMedication); index++;
				if(missMedication.getSelectedIndex() == 1){
					insert (index , durationMissMedication); index++;
				}
				insert (index, medicineWrappers); index++;
				if(medicineWrappers.getSelectedIndex() == 1){
					insert (index , doseConsumption); index++;
				}
				insert (index,endOfForm);
			}
		}
		
		if(i == missMedication)
		{
			if(missMedication.getSelectedIndex() == 1)
			{
				deleteFromTo(missMedication, endOfForm);
				int index = getItemIndex(missMedication , this ); index++;
				insert (index , durationMissMedication); index++;
				insert (index, medicineWrappers); index++;
				if(medicineWrappers.getSelectedIndex() == 1){
					insert (index , doseConsumption); index++;
				}
				insert (index,endOfForm);				
			}
			
			else
			{
				deleteFromTo(missMedication, endOfForm);
				int index = getItemIndex(missMedication , this ); index++;
				insert (index, medicineWrappers); index++;
				if(medicineWrappers.getSelectedIndex() == 1){
					insert (index , doseConsumption); index++;
				}
				insert (index,endOfForm);
			}
		}
		
		if(i == medicineWrappers)
		{
			if( medicineWrappers.getSelectedIndex() == 1)
			{
				deleteFromTo(medicineWrappers, endOfForm);
				int index = getItemIndex(medicineWrappers , this ); index++;
				if(medicineWrappers.getSelectedIndex() == 1){
					insert (index , doseConsumption); index++;
				}
				insert (index,endOfForm);
			}
			
			else
			{
				deleteFromTo(medicineWrappers, endOfForm);
				int index = getItemIndex(medicineWrappers , this ); index++;
				insert (index,endOfForm);
			}
		}
		
		
	}
	
	private boolean validate()
	{
		boolean result = true;
				
		if(monthTreatment.getString().equals( "" ))
		{
			tbrMidlet.showAlert ("Укажите ме�?�?ц лечени�?", null);
			result = false;
		}
		
		else
		{
			int month = Integer.parseInt( monthTreatment.getString() );
	        if(month == 0 || month > 9){
	        	tbrMidlet.showAlert ("Ошибка ме�?�?ца начала лечени�?", null);
	        	result = false;
	        }
		}
		
		if(patientSideEffects.isSelected( 5 )){
			if (otherSideEffects.getString().equals( "" )){
				tbrMidlet.showAlert ("Укажите другие побочные �?ффекты", null);
				result = false;
			}
		}
		
		if(experiencedSideEffect.getSelectedIndex() == 1)
		{
			boolean status = false;
			for(int i = 0; i<=5; i++){
				if(patientSideEffects.isSelected( i )){
					status = true;
					break;
				}					
			}
			if (!status){
				tbrMidlet.showAlert ("Укажите побочные �?ффекты", null);
				result = false;
			}			
		}
		
		if(patientConsult.getSelectedIndex() == 1)
		{
			boolean status = false;
			for(int i = 0; i<4; i++){
				if(healthFacility.isSelected( i )){
					status = true;
					break;
				}					
			}
			if (!status){
				tbrMidlet.showAlert ("Укажите медучреждение", null);
				result = false;
			}			
		}
		
		return result;
	}

	public void commandAction(Command c , Item item) {
		
		
	}
	
	
	private void monthsInString(){
		
		String previousMR = (String)queryData.get("previousMR");
		System.out.println(previousMR);
		boolean chk = previousMR.equals( "no" );
		if (!chk){
			String[] previousMResults=StringUtil.split( previousMR , '/' );
			
			for (int counter=0; counter<previousMResults.length-1; counter++)
			{
				int j = StringUtil.firstOccurrenceOf( previousMResults[counter] , ' ' );
				int len = previousMResults[counter].length();
				String month = previousMResults[counter].substring( 0 , j);
				int monthNumber = Integer.parseInt( month );
				String result = previousMResults[counter].substring( j+1 , len );
				String resultInR = null;	
				
				if( result.equals("NEGATIVE")|| result.equals("negative")|| result.equals("Negative"))
					resultInR = "Отр";
				else if (result.equalsIgnoreCase( "1-9 AFB" ))
					resultInR = "1-9 КУБ";
				else if (result.equalsIgnoreCase( "NONE" ))
					resultInR = "�?ет";
				else	
					resultInR = result+"+";
				
				switch (monthNumber) {
		            case 2:  month2 = resultInR;
		                     break;
		            case 3:  month3 = resultInR;
		                     break;
		            case 4:  month4 = resultInR;
		                     break;
		            case 5:  month5 = resultInR;
		                     break;
		            case 6:  month6 = resultInR;
		                     break;
		            case 7:  month7 = resultInR;
		                     break;
		            case 8:  month8 = resultInR;
	                		 break;
		            case 9:  month9 = resultInR;
	                		 break;
		        }	
			}
		
	   }
		
	}
	
	private int getItemIndex(Item item, Form form) {
	    for(int i = 0, size = form.size(); i < size; i++) {
	    	if(form.get(i).equals(item)) {
	    		return i;
	    	}
	    }
	    return -1;
	}
	
	private void deleteFromTo (Item from, Item to){
		
		int startIndex = getItemIndex(from, this);
		int endIndex = getItemIndex(to, this);
		System.out.println("start:"+startIndex+"end:"+endIndex);
		
		int diff = endIndex - startIndex;
		
		for(int i = diff; i>0; i--){
			delete(startIndex+i);
		}
		
	}
	
}
