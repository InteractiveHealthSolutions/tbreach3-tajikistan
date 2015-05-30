/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package org.irdresearch.tbreach.mobile.ui;

import java.util.Hashtable;

import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;

import org.irdresearch.tbreach.mobile.constants.XmlStrings;
import org.irdresearch.tbreach.mobile.model.FormType;
import org.irdresearch.tbreach.mobile.util.DateTimeUtil;

public class BaselineForm extends BaseTBReachForm implements CommandListener, ItemCommandListener, ItemStateListener{
	
	TextField participantID;
	TextField oldStartTreatment;
	//DateField startTreatment;
	//TextField healthWorkerID;
	ChoiceGroup baselineSputum;
	ChoiceGroup baselineChest;
	ChoiceGroup otherXraySite;
	ChoiceGroup resultXray;
	ChoiceGroup baselineGeneXpert;
	ChoiceGroup geneXpertDrugSensitivity;
	ChoiceGroup typePatient;
	ChoiceGroup patientCategory;
	TextField weight;
	ChoiceGroup regimen;
	ChoiceGroup fixedDose;
	ChoiceGroup strepto;
	StringItem endOfForm;
	Command		cmdOK;
	Command		cmdBack;
	Item[]		formItems;
	private Hashtable	queryData;
	String patientId;
	

	public BaselineForm( String title , TBReachMainMIDlet tbrMidlet ) {
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
	
	public void init()
	{
		//healthWorkerID = new TextField( "Личный номер медработника" , tbrMidlet.getCurrentUserId() , 10 , TextField.UNEDITABLE);
		participantID = new TextField( "Реги�?трационный номер пациента" , (String)queryData.get("pid") , 8 , TextField.UNEDITABLE );
		oldStartTreatment = new TextField("Дата начала лечени�?" , (String)queryData.get("treatmentinitate"), 10, TextField.UNEDITABLE);
		//startTreatment = new DateField( "новый: Дата начала лечени�? (е�?ли изменило�?ь)" ,  DateField.DATE);
		//startTreatment.setDate( null );
		
		weight = new TextField( "Ве�? пациента (кг)" , "" , 3  , TextField.NUMERIC );
		
		baselineSputum = new ChoiceGroup( "Микро�?копи�? мазка (диагно�?тика)" , ChoiceGroup.POPUP );
		baselineSputum.append( "�?ет" , null );
		baselineSputum.append( "Отр" , null );
		baselineSputum.append( "1-9 КУБ" , null );
		baselineSputum.append( "1+" , null );
		baselineSputum.append( "2+" , null );
		baselineSputum.append( "3+" , null );
		
		baselineChest = new ChoiceGroup( "Рентгенографи�? легких (диагно�?тика)" , ChoiceGroup.POPUP );
		baselineChest.append( "�?ет" , null );
		baselineChest.append( "�?ормальный" , null );
		baselineChest.append( "Подозрение на ТБ" , null );
		baselineChest.append( "Большое подозрение на ТБ" , null );
		baselineChest.append( "Де�?труктивно�?ть (обу�?ловлена ТБ)" , null );
		baselineChest.append( "Де�?труктивно�?ть (обу�?ловлена не ТБ)" , null );
		
		otherXraySite = new ChoiceGroup("Локализаци�? ТБ", ChoiceGroup.POPUP);
		otherXraySite.append( "Легкие" , null );
		otherXraySite.append( "Таз" , null );
		otherXraySite.append( "Ко�?ти" , null );
		otherXraySite.append( "Другие" , null );
		
		resultXray = new ChoiceGroup("Рентгенографи�? органов (диагно�?тика)", ChoiceGroup.POPUP);
		resultXray.append( "�?ет" , null );
		resultXray.append( "�?ормальный" , null );
		resultXray.append( "Подозрение на ТБ" , null );
		resultXray.append( "Большое подозрение на ТБ" , null );
		resultXray.append( "Де�?труктивно�?ть (обу�?ловлена ТБ)" , null );
		resultXray.append( "Де�?труктивно�?ть (обу�?ловлена не ТБ)" , null );
		
		//TODO: Should we remove extra option? 
		
		baselineGeneXpert = new ChoiceGroup( "Результаты GeneXpert" , ChoiceGroup.POPUP );
		baselineGeneXpert.append( "�?ет" , null );
		baselineGeneXpert.append( "МБТ -" , null );
		baselineGeneXpert.append( "МБТ +" , null );
		//baselineGeneXpert.append( "�?омаълум" , null );
		//baselineGeneXpert.append( "Хато" , null );
		
		geneXpertDrugSensitivity = new ChoiceGroup( "Чув�?твительно�?ть к Rif" , ChoiceGroup.POPUP );
		geneXpertDrugSensitivity.append( "�?ет" , null );
		geneXpertDrugSensitivity.append( "МБТ Rif +" , null );
		geneXpertDrugSensitivity.append( "МБТ Rif -" , null );
		//geneXpertDrugSensitivity.append( "�?омаълум" , null );
		//geneXpertDrugSensitivity.append( "Хато" , null );
		//geneXpertDrugSensitivity.append( "�?е�?т" , null );
		
		typePatient = new ChoiceGroup( "Тип пациента" , ChoiceGroup.POPUP );
		typePatient.append( "�?овый �?лучай" , null );
		typePatient.append( "Рецидив" , null );
		typePatient.append( "Переведен из" , null );
		typePatient.append( "По�?ле отрыва" , null );
		typePatient.append( "По�?ле неудачи лечени�?" , null );
		typePatient.append( "Другие" , null );
		
		patientCategory = new ChoiceGroup( "Категори�? лечени�? пациента" , ChoiceGroup.POPUP );
		patientCategory.append( "Категори�? 1" , null );
		patientCategory.append( "Категори�? 2" , null );
		patientCategory.append( "Категори�? 3" , null );
		
		regimen = new ChoiceGroup( "Режим лечени�?" , ChoiceGroup.POPUP );
		regimen.append( "RHZE" , null );
		regimen.append( "RHZES" , null );
		
		fixedDose = new ChoiceGroup( "Доза назначенного препарата " , ChoiceGroup.POPUP );
		fixedDose.append( "1" , null );
		fixedDose.append( "1.5" , null );
		fixedDose.append( "2" , null );
		fixedDose.append( "3" , null );
		fixedDose.append( "4" , null );
		fixedDose.append( "5" , null );
		
		strepto = new ChoiceGroup( "Доза �?трептомицина" , ChoiceGroup.POPUP );
		strepto.append( "250" , null );
		strepto.append( "500" , null );
		strepto.append( "750" , null );
		
		endOfForm = new StringItem( "Конец формы" , "" );
		
		cmdOK = new Command( "Сабт" , Command.OK , 1 );
		cmdBack = new Command( "Ба аввал" , Command.BACK , 2 );

		Item items[] = {/*healthWorkerID ,*/ participantID, oldStartTreatment , weight ,
				baselineSputum , baselineChest , otherXraySite, resultXray, baselineGeneXpert ,
				geneXpertDrugSensitivity, typePatient, patientCategory, regimen, fixedDose,  strepto ,endOfForm };
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
		request = "type=" + FormType.PATIENT_BASELINE_FORM;
		request += "&parId=" + participantID.getString();
		request += "&weight=" + weight.getString();
		/*Date d = startTreatment.getDate();
		if (d != null)
			request += "&sdate=" + DateTimeUtil.getDate( startTreatment.getDate() );*/
		request += "&startdate=" + startDate;
		request += "&stime=" + startTime;
		request += "&etime=" + endTime;
		request += "&basesputum=" + baselineSputum.getString(baselineSputum.getSelectedIndex());
		request += "&basechest=" + baselineChest.getString(baselineChest.getSelectedIndex());
		request += "&otherxraysite=" + otherXraySite.getString(otherXraySite.getSelectedIndex());
		request += "&resultxray=" + resultXray.getString(resultXray.getSelectedIndex());
		if (baselineGeneXpert.getSelectedIndex() == 1)
			request += "&basegenexpert=" + "MTB Negative";
		else if (baselineGeneXpert.getSelectedIndex() == 2)
			request += "&basegenexpert=" + "MTB Positive";
		else request += "&basegenexpert=" + baselineGeneXpert.getString(baselineGeneXpert.getSelectedIndex());
		if (geneXpertDrugSensitivity.getSelectedIndex() == 1)
			request += "&drugsensitive=" + "MTB Rif Positive";
		else if (geneXpertDrugSensitivity.getSelectedIndex() == 2)
			request += "&drugsensitive=" + "MTB Rif Negative";
		else request += "&drugsensitive=" + geneXpertDrugSensitivity.getString(geneXpertDrugSensitivity.getSelectedIndex());
		request += "&typepatient=" + typePatient.getString(typePatient.getSelectedIndex());
		request += "&patientcategory=" + patientCategory.getString(patientCategory.getSelectedIndex());
		request += "&regimen=" + regimen.getString(regimen.getSelectedIndex());
		request += "&fixedDose=" + fixedDose.getString(fixedDose.getSelectedIndex());
		if(regimen.getString(regimen.getSelectedIndex()).equals("RHZES")){
		request += "&strepto=" + strepto.getString( strepto.getSelectedIndex() );}
		request += "&healthWorker=" + tbrMidlet.getCurrentUserId();
		
		return request;
		
	}

	public void setQueryData(Hashtable queryData) {
		this.queryData = queryData;
	}
	
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	private void initialShow() {
		
		for (int i = 0; i < formItems.length; i++)
			append( formItems[i] );
		
		hide( strepto );
			
	}


	public void itemStateChanged(Item i) {
			
		if(i == regimen)
		{
			if(regimen.getString( regimen.getSelectedIndex() ).equals( "RHZES" ))
			{
				show(strepto);
			}
			else
			{
				hide(strepto);
			}
		}
		
		if(i == patientCategory)
		{
			if(patientCategory.getString( patientCategory.getSelectedIndex() ).equals( "Категори�? 1" ))
			{
				regimen.setSelectedIndex( 0 , true);
				
			}
			
			else if(patientCategory.getString( patientCategory.getSelectedIndex() ).equals( "Категори�? 2" ))
			{
				regimen.setSelectedIndex( 1 , true);
			}
		}
		
		
	}
	
	private boolean validate()
	{
		boolean result = true;
		
		if(weight.getString().equals( "" ))
		{
			tbrMidlet.showAlert ("Укажите ве�? пациента", null);
			result = false;
		}
		return result;
	}

	public void commandAction(Command c , Item item) {
		
		
	}


}
