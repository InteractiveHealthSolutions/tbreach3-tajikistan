/* Copyright(C) 2015 Interactive Health Solutions, Pvt. Ltd.

This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as
published by the Free Software Foundation; either version 3 of the License (GPLv3), or any later version.
This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program; if not, write to the Interactive Health Solutions, info@ihsinformatics.com
You can also access the license on the internet at the address: http://www.gnu.org/licenses/gpl-3.0.html

Interactive Health Solutions, hereby disclaims all copyright interest in this program written by the contributors. */
package org.irdresearch.tbreach.mobile.ui;


import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;

import org.irdresearch.tbreach.mobile.constants.ErrMsg;
import org.irdresearch.tbreach.mobile.constants.XmlStrings;
import org.irdresearch.tbreach.mobile.model.FormType;
import org.irdresearch.tbreach.mobile.util.DateTimeUtil;
import org.irdresearch.tbreach.mobile.util.StringUtil;

import java.util.Hashtable;

public class SurveyForm extends BaseTBReachForm implements CommandListener, ItemCommandListener, ItemStateListener{
	
	TextField participantID;
	TextField participantIDConfirm;
	TextField healthWorkerID;
	
	ChoiceGroup satisfiedWithSystem;
	TextField unsatisfactoryApsectsOfSystem;
	TextField systemImproved;
	ChoiceGroup satisfyWithReminderSystem;
	TextField unsatisfactoryApectsOfReminderSystem;
	ChoiceGroup smsSystemAdditionalEffort;
	ChoiceGroup smsSystemTreatmentCompliance;
	ChoiceGroup serviceByHealthFacility;
	ChoiceGroup careByTbSpecialist;
	ChoiceGroup tbTreatmentSystem;
	
	StringItem endOfForm;
	Command		cmdOK;
	Command		cmdBack;
	Item[]		formItems;
	TextField relationshipOther;
	private 	Hashtable queryData;
	
	
	

	public SurveyForm( String title , TBReachMainMIDlet tbrMidlet ) {
		super( title , tbrMidlet );
		
		participantID = null;
		participantIDConfirm = null;
		healthWorkerID = null;
		
		satisfiedWithSystem = null;
		unsatisfactoryApsectsOfSystem = null;
		systemImproved = null;
		satisfyWithReminderSystem = null;
		unsatisfactoryApectsOfReminderSystem = null;
		smsSystemAdditionalEffort = null;
		smsSystemTreatmentCompliance = null;
		serviceByHealthFacility = null;
		careByTbSpecialist = null;
		tbTreatmentSystem = null;
		endOfForm = null;
		
	}
	
	public Hashtable getQueryData ()
	{
		return queryData;
	}

	public void setQueryData (Hashtable queryData)
	{
		this.queryData = queryData;
	}
	
	public void init()
	{
		
		healthWorkerID = new TextField( "Личный номер медработника" , tbrMidlet.getCurrentUserId() , 10 , TextField.UNEDITABLE);
		participantID = new TextField( "Реги�?трационный номер пациента" , "" , 8 , TextField.NUMERIC );
		participantIDConfirm = new TextField( "Реги�?трационный номер пациента (повторить)" , "" , 8 , TextField.NUMERIC );
		
		satisfiedWithSystem = new ChoiceGroup( "Вы довольны �?и�?темой хранени�? и приема медпрепаратов на дому?" , ChoiceGroup.POPUP );
		satisfiedWithSystem.append("Совершенно �?огла�?ен", null);
		satisfiedWithSystem.append("Cогла�?ен", null);
		satisfiedWithSystem.append("�?ичто", null);
		satisfiedWithSystem.append("�?е �?огла�?ен", null);
		satisfiedWithSystem.append("Совершенно не �?огла�?ен", null);
		
		unsatisfactoryApsectsOfSystem = new TextField( "Е�?ли нет, то почему?" , "" , 256  , TextField.ANY );
		unsatisfactoryApsectsOfSystem.setInitialInputMode("UCB_CYRILLIC"); 
		
		systemImproved = new TextField( "Как можно улучшить �?ту �?и�?тему?" , "" , 256  , TextField.ANY );
		systemImproved.setInitialInputMode("UCB_CYRILLIC"); 
		
		satisfyWithReminderSystem = new ChoiceGroup("Вы довольны �?и�?темой СМС напоминаний?" , ChoiceGroup.POPUP );
		satisfyWithReminderSystem.append("Совершенно �?огла�?ен", null);
		satisfyWithReminderSystem.append("Cогла�?ен", null);
		satisfyWithReminderSystem.append("�?ичто", null);
		satisfyWithReminderSystem.append("�?е �?огла�?ен", null);
		satisfyWithReminderSystem.append("Совершенно не �?огла�?ен", null);
		
		unsatisfactoryApectsOfReminderSystem = new TextField( "Е�?ли нет, то почему?" , "" , 256  , TextField.ANY );
		unsatisfactoryApectsOfReminderSystem.setInitialInputMode("UCB_CYRILLIC"); 
		
		smsSystemAdditionalEffort = new ChoiceGroup("Требует ли �?и�?тема СМС напоминаний от ва�? дополнительных у�?илий?" , ChoiceGroup.POPUP );
		smsSystemAdditionalEffort.append("Совершенно �?огла�?ен", null);
		smsSystemAdditionalEffort.append("Cогла�?ен", null);
		smsSystemAdditionalEffort.append("�?ичто", null);
		smsSystemAdditionalEffort.append("�?е �?огла�?ен", null);
		smsSystemAdditionalEffort.append("Совершенно не �?огла�?ен", null);
		
		smsSystemTreatmentCompliance = new ChoiceGroup("Помогает ли �?и�?тема СМС напоминаний �?облюдать режим лечени�??" , ChoiceGroup.POPUP );
		smsSystemTreatmentCompliance.append("Совершенно �?огла�?ен", null);
		smsSystemTreatmentCompliance.append("Cогла�?ен", null);
		smsSystemTreatmentCompliance.append("�?ичто", null);
		smsSystemTreatmentCompliance.append("�?е �?огла�?ен", null);
		smsSystemTreatmentCompliance.append("Совершенно не �?огла�?ен", null);
		
		serviceByHealthFacility = new ChoiceGroup("Как вы оцениваете каче�?тво у�?луг в медучреждении?" , ChoiceGroup.POPUP );
		serviceByHealthFacility.append("Совершенно �?огла�?ен", null);
		serviceByHealthFacility.append("Cогла�?ен", null);
		serviceByHealthFacility.append("�?ичто", null);
		serviceByHealthFacility.append("�?е �?огла�?ен", null);
		serviceByHealthFacility.append("Совершенно не �?огла�?ен", null);
		
		careByTbSpecialist = new ChoiceGroup("Как вы оцениваете каче�?тво работы �?пециали�?та по ТБ?" , ChoiceGroup.POPUP );
		careByTbSpecialist.append("Совершенно �?огла�?ен", null);
		careByTbSpecialist.append("Cогла�?ен", null);
		careByTbSpecialist.append("�?ичто", null);
		careByTbSpecialist.append("�?е �?огла�?ен", null);
		careByTbSpecialist.append("Совершенно не �?огла�?ен", null);
		
		tbTreatmentSystem = new ChoiceGroup("Ва�? у�?траивает �?и�?тема лечени�? ТБ пациентов в �?тране?" , ChoiceGroup.POPUP );
		tbTreatmentSystem.append("Совершенно �?огла�?ен", null);
		tbTreatmentSystem.append("Cогла�?ен", null);
		tbTreatmentSystem.append("�?ичто", null);
		tbTreatmentSystem.append("�?е �?огла�?ен", null);
		tbTreatmentSystem.append("Совершенно не �?огла�?ен", null);
		
		endOfForm = new StringItem( "Конец формы" , "" );
		
		cmdOK = new Command( "Сабт" , Command.OK , 1 );
		cmdBack = new Command( "Ба аввал" , Command.BACK , 2 );

		Item items[] = {healthWorkerID , participantID, participantIDConfirm , satisfiedWithSystem , unsatisfactoryApsectsOfSystem ,
		systemImproved, satisfyWithReminderSystem , unsatisfactoryApectsOfReminderSystem , smsSystemAdditionalEffort ,
		smsSystemTreatmentCompliance , serviceByHealthFacility , careByTbSpecialist , tbTreatmentSystem , endOfForm};
		formItems = items;
		addCommand( cmdOK );
		addCommand( cmdBack );

		startDate = DateTimeUtil.getDate();
		startTime = DateTimeUtil.getTime();
		initialShow();
		setCommandListener( this );
		setItemStateListener( this );
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

	public void itemStateChanged(Item i) {
	
	}
	

	private void initialShow() {
		
		for (int i = 0; i < formItems.length; i++)
			append( formItems[i] );
		
	}
	
	private boolean validate()
	{
		boolean result = true;
		String error = "И�?правьте в�?е ошибки: \n";
		if(participantID.getString().equals( "" ) || participantIDConfirm.getString().equals( "" ))
		{
			error = error + "Укажите номер пациента. \n";
			//tbrMidlet.showAlert ("Participant id(s) can't be empty.", null);
			result = false;
		}else
		
		if (!participantIDConfirm.getString ().equals (participantID.getString ())){
			error = error + "�?омера не �?овпадают. \n";
			//tbrMidlet.showAlert ("IDs do not match", null);
			result = false;
		}	
		
		return result;
	}

	public void commandAction(Command c , Item arg1) {
		
		System.out.println("INSIDE commandAction ITEM!");
		
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
				String uniqueID = (String) model.get( "uniqueID" );

				if (status.equals( XmlStrings.SUCCESS )) {
					System.out.println( "success" );
					
						deleteAll();
						removeCommand( cmdOK );
						removeCommand( cmdBack );
						init();
						tbrMidlet.showAlert("Данные �?охранены!" , null );


				}
				else if (status.equals( XmlStrings.ERROR )) {
					String msg =  (String) model.get( "msg" );
					String russianMsg = ErrMsg.getTajikErrorMessage( msg );
					tbrMidlet.showAlert(russianMsg , null );
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
		request = "type=" + FormType.PATIENT_SURVEY_FORM;
		request += "&parId=" + participantID.getString();
		request += "&startdate=" + startDate;
		request += "&stime=" + startTime;
		request += "&etime=" + endTime;
		request += "&satisfiedwithsystem=" + satisfiedWithSystem.getString( satisfiedWithSystem.getSelectedIndex() );
		request += "&unsatisfactoryaspectsystem=" + unsatisfactoryApsectsOfSystem.getString();
		request += "&systemimproved=" + systemImproved.getString();
		request += "&satisfiedremindersystem=" + satisfyWithReminderSystem.getString( satisfyWithReminderSystem.getSelectedIndex() );
		request += "&unsatisfactoryaspectremindersystem=" + unsatisfactoryApectsOfReminderSystem.getString();
		request += "&smssystemeffort=" + smsSystemAdditionalEffort.getString( smsSystemAdditionalEffort.getSelectedIndex() );
		request += "&smssystemcompliance=" + smsSystemTreatmentCompliance.getString( smsSystemTreatmentCompliance.getSelectedIndex() );
		request += "&servicehealthfacility=" + serviceByHealthFacility.getString( serviceByHealthFacility.getSelectedIndex() );
		request += "&caretbspecialist=" + careByTbSpecialist.getString( careByTbSpecialist.getSelectedIndex() );
		request += "&tbtreatmentsystem=" + tbTreatmentSystem.getString( tbTreatmentSystem.getSelectedIndex() );
		request += "&healthWorker=" + healthWorkerID.getString();
		return request;
		
	}

}
