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

public class PatientRegistration extends BaseTBReachForm implements CommandListener, ItemCommandListener, ItemStateListener{
	
	TextField participantID;
	TextField participantIDConfirm;
	DateField startTreatment;
	TextField healthWorkerID;
	TextField homePhoneNo;
	TextField phoneNumber1;
	TextField addressStreet;
	TextField addressDistrict;
	TextField addressHouse;
	TextField addressFlat;
	TextField firstNameField;
	TextField lastNameField;
	DateField dobField;
	ChoiceGroup gender;
	ChoiceGroup nominateFamilyVolunteer;
	ChoiceGroup relationshipFamilyVolunteer;
	ChoiceGroup maritalStatus;
	ChoiceGroup education;
	ChoiceGroup incomeFamilyMember;
	StringItem endOfForm;
	Command		cmdOK;
	Command		cmdBack;
	Item[]		formItems;
	TextField relationshipOther;
	private 	Hashtable queryData;
	
	
	

	public PatientRegistration( String title , TBReachMainMIDlet tbrMidlet ) {
		super( title , tbrMidlet );
		
		participantID = null;
		participantIDConfirm = null;
		
		startTreatment = null;
		healthWorkerID = null;
		phoneNumber1 = null;
		addressStreet = null;
		addressDistrict = null;
		addressHouse = null;
		addressFlat = null;
		firstNameField = null;
		lastNameField = null;
		dobField = null;
		gender = null;
		nominateFamilyVolunteer = null;
		relationshipFamilyVolunteer = null;
		maritalStatus = null;
		education = null;
		incomeFamilyMember = null;
		cmdOK = null;
		cmdBack = null;
		relationshipOther = null;
		
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
		startTreatment = new DateField( "Дата начала лечени�?  (YYYY-MM-DD)" ,  DateField.DATE);
		
		homePhoneNo = new TextField( "�?омер телефона дл�? �?в�?зи" , "" , 25  , TextField.NUMERIC );
		phoneNumber1 = new TextField( "�?омер телефона дл�? СМС" , "" , 25  , TextField.NUMERIC );

		addressStreet = new TextField( "�?дре�?: улица" , "" , 25  , TextField.ANY );
		addressStreet.setInitialInputMode("UCB_CYRILLIC"); 
		addressDistrict = new TextField( "�?дре�?: микрорайон" , "" , 25 , TextField.ANY );
		addressDistrict.setInitialInputMode("UCB_CYRILLIC"); 
		addressHouse = new TextField( "�?дре�?: номер дома" , "" , 20 , TextField.ANY );
		addressHouse.setInitialInputMode("UCB_CYRILLIC");
		addressFlat = new TextField( "�?дре�?: номер квартиры" , "" , 20 , TextField.ANY );
		addressFlat.setInitialInputMode("UCB_CYRILLIC");
		
		firstNameField = new TextField("Им�?" , "" , 20 , TextField.ANY );
		firstNameField.setInitialInputMode("UCB_CYRILLIC");
		lastNameField = new TextField("Фамили�?" , "" , 20 , TextField.ANY );
		lastNameField.setInitialInputMode("UCB_CYRILLIC");
		dobField = new DateField( "Дата рождени�? (YYYY-MM-DD)" ,  DateField.DATE);
		
		gender = new ChoiceGroup( "Пол" , ChoiceGroup.POPUP );
		gender.append("Муж" , null);
		gender.append("Жен" , null);
		
		nominateFamilyVolunteer = new ChoiceGroup ("Имеет�?�? ли волонтер?",ChoiceGroup.POPUP);
		nominateFamilyVolunteer.append ("Да", null);
		nominateFamilyVolunteer.append ("�?ет", null);
		
		relationshipFamilyVolunteer = new ChoiceGroup( "Кем волонтер �?вл�?ет�?�? дл�? пациента?" , ChoiceGroup.POPUP );
		relationshipFamilyVolunteer.append( "Сын/дочь" , null );
		relationshipFamilyVolunteer.append( "Мать/отец" , null );
		relationshipFamilyVolunteer.append( "Брат/�?е�?тра" , null );
		relationshipFamilyVolunteer.append( "Супруг/�?упруга" , null );
		relationshipFamilyVolunteer.append( "Бабушка/дедушка" , null );
		relationshipFamilyVolunteer.append( "Внук/внучка" , null );
		relationshipFamilyVolunteer.append( "Тет�?/д�?д�?" , null );
		relationshipFamilyVolunteer.append( "Двоюродна�? �?е�?тра/брат" , null );
		relationshipFamilyVolunteer.append( "Другие" , null );
		
		relationshipOther = new TextField( "Укажите е�?ли другое." , "" , 25 , TextField.ANY);
		
		maritalStatus = new ChoiceGroup( "Семейное положение" , ChoiceGroup.POPUP );
		maritalStatus.append( "�?е женат/не замужем" ,null );
		maritalStatus.append( "Женат/замужем" ,null );
		maritalStatus.append( "Разведен/а" ,null );
		maritalStatus.append( "Живу раздельно" ,null );
		maritalStatus.append( "Вдовец/Вдова" ,null );
		maritalStatus.append( "�?ет ответа" ,null );
		
		education = new ChoiceGroup( "Образование" , ChoiceGroup.POPUP );
		education.append( "Дошкольное" ,null );
		education.append( "�?ачальна�? школа" ,null );
		education.append( "Средн�?�? школа" ,null );
		education.append( "Среднее �?пециальное" ,null );
		education.append( "Вы�?шее" ,null );
		education.append( "�?ет" ,null );
		
		incomeFamilyMember = new ChoiceGroup( "Доход на 1 члена �?емьи в ме�?�?ц" , ChoiceGroup.POPUP );
		incomeFamilyMember.append( "До 100 �?омони" ,null );
		incomeFamilyMember.append( "До 200 �?омони" ,null );
		incomeFamilyMember.append( "До 300 �?омони" ,null );
		incomeFamilyMember.append( "Более 400 �?омони" ,null );
		incomeFamilyMember.append( "нет ответа" ,null );
		
		endOfForm = new StringItem( "Конец формы" , "" );
		
		cmdOK = new Command( "Сабт" , Command.OK , 1 );
		cmdBack = new Command( "Ба аввал" , Command.BACK , 2 );

		Item items[] = {healthWorkerID , participantID, participantIDConfirm , startTreatment, homePhoneNo,  phoneNumber1 ,
		         addressStreet ,
				addressDistrict, addressHouse, addressFlat, firstNameField, lastNameField, dobField, gender, nominateFamilyVolunteer, relationshipFamilyVolunteer, relationshipOther,  maritalStatus, education, incomeFamilyMember , endOfForm  };
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
		int indexx = 8;
		
		
		if(i == relationshipFamilyVolunteer)
		{
			if(relationshipFamilyVolunteer.getSelectedIndex() == indexx)
			{
				show(relationshipOther);
			}
			else
			{
				hide(relationshipOther);
			}
		}
		
		/*if(i == nominateFamilyVolunteer)
		{
			if(nominateFamilyVolunteer.getSelectedIndex() == 0)
			{
				show(relationshipFamilyVolunteer);
			}
			else
			{
				hide(relationshipFamilyVolunteer);
				hide(relationshipOther);
			}
		}*/
	}
	

	private void initialShow() {
		
		for (int i = 0; i < formItems.length; i++)
			append( formItems[i] );
		
		hide( relationshipOther );
		hide (nominateFamilyVolunteer);
		
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
		
		/* if(addressHouse.getString().equals( "" ) || addressDistrict.getString().equals( "" ) || addressFlat.getString().equals( "" ) || addressStreet.getString().equals( "" )){
			error = error + "Укажите полный адре�?. \n";
			//tbrMidlet.showAlert ("IDs do not match", null);
			result = false;
		}*/
		
		if (startTreatment.getDate() == null){
			error = error + "Укажите дату начала лечени�?. \n";
			//tbrMidlet.showAlert ("Please fill start treatment Date", null);
			result = false;
		}else if (DateTimeUtil.isDateInFuture( startTreatment.getDate() )){
			error = error + "Ошибка даты начала лечени�? \n";
			//tbrMidlet.showAlert ("Please fill valid date!", null);
			result = false;
		}
		
		if(homePhoneNo.getString().equals( "" ) || phoneNumber1.getString().equals( "" ))
		{
			error = error + "Укажите номер телефона \n";
			//tbrMidlet.showAlert ("Please fill in phone number", null);
			result = false;			
		}
		
		if(firstNameField.getString().equals("") || lastNameField.getString().equals(""))
		{
			error = error + "Укажите им�? \n";
			//tbrMidlet.showAlert ("Please fill in complete name", null);
			result = false;			
		}
			/*if((!StringUtil.isAlpha( firstNameField.getString())) && (!StringUtil.isAlpha( firstNameField.getString()))){
				tbrMidlet.showAlert ("Please fill in correct name", null);
				result = false;				
			}*/
		
		if(dobField.getDate() == null)
		{
			error = error + "Укажите дату рождени�? \n";
			//tbrMidlet.showAlert ("Please fill in date of Birth", null);
			result = false;			
		}else if (DateTimeUtil.isDateInFuture( dobField.getDate() )){
			error = error + "Ошибка даты рождени�? \n";
			//tbrMidlet.showAlert ("Please fill valid date!", null);
			result = false;
		}
		
		if(!result){
			tbrMidlet.showAlert (error, null);
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
		request = "type=" + FormType.PATIENT_REGISTRATION;
		request += "&parId=" + participantID.getString();
		request += "&sdate=" + DateTimeUtil.getDate( startTreatment.getDate() );
		request += "&startdate=" + startDate;
		request += "&stime=" + startTime;
		request += "&etime=" + endTime;
		request += "&homephonenumber=" + homePhoneNo.getString();
		request += "&phone1=" + phoneNumber1.getString();
		request += "&street=" + addressStreet.getString();
		request += "&district=" + addressDistrict.getString();
		request += "&house=" + addressHouse.getString();
		request += "&flat=" + addressFlat.getString();
		request += "&firstname=" + firstNameField.getString();
		request += "&lastname=" + lastNameField.getString();
		request += "&dob=" + DateTimeUtil.getDate( dobField.getDate() );
		request += "&gender=" + gender.getString( gender.getSelectedIndex() );
		request += "&nominatevolunteer=" + nominateFamilyVolunteer.getString( nominateFamilyVolunteer.getSelectedIndex() );
		request += "&relationship=" + relationshipFamilyVolunteer.getString( relationshipFamilyVolunteer.getSelectedIndex() );
		request += "&relationother=" + relationshipOther.getString();
		request += "&maritalstatus=" + maritalStatus.getString( maritalStatus.getSelectedIndex() );
		request += "&education=" + education.getString( education.getSelectedIndex() );
		request += "&incomeFamilyMember=" + incomeFamilyMember.getString( incomeFamilyMember.getSelectedIndex() );
		request += "&healthWorker=" + healthWorkerID.getString();
		
		return request;
		
	}

}
