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
		participantID = new TextField( "Регистрационный номер пациента" , "" , 8 , TextField.NUMERIC );
		participantIDConfirm = new TextField( "Регистрационный номер пациента (повторить)" , "" , 8 , TextField.NUMERIC );
		
		satisfiedWithSystem = new ChoiceGroup( "Вы довольны системой хранения и приема медпрепаратов на дому?" , ChoiceGroup.POPUP );
		satisfiedWithSystem.append("Совершенно согласен", null);
		satisfiedWithSystem.append("Cогласен", null);
		satisfiedWithSystem.append("Ничто", null);
		satisfiedWithSystem.append("Не согласен", null);
		satisfiedWithSystem.append("Совершенно не согласен", null);
		
		unsatisfactoryApsectsOfSystem = new TextField( "Если нет, то почему?" , "" , 256  , TextField.ANY );
		unsatisfactoryApsectsOfSystem.setInitialInputMode("UCB_CYRILLIC"); 
		
		systemImproved = new TextField( "Как можно улучшить эту систему?" , "" , 256  , TextField.ANY );
		systemImproved.setInitialInputMode("UCB_CYRILLIC"); 
		
		satisfyWithReminderSystem = new ChoiceGroup("Вы довольны системой СМС напоминаний?" , ChoiceGroup.POPUP );
		satisfyWithReminderSystem.append("Совершенно согласен", null);
		satisfyWithReminderSystem.append("Cогласен", null);
		satisfyWithReminderSystem.append("Ничто", null);
		satisfyWithReminderSystem.append("Не согласен", null);
		satisfyWithReminderSystem.append("Совершенно не согласен", null);
		
		unsatisfactoryApectsOfReminderSystem = new TextField( "Если нет, то почему?" , "" , 256  , TextField.ANY );
		unsatisfactoryApectsOfReminderSystem.setInitialInputMode("UCB_CYRILLIC"); 
		
		smsSystemAdditionalEffort = new ChoiceGroup("Требует ли система СМС напоминаний от вас дополнительных усилий?" , ChoiceGroup.POPUP );
		smsSystemAdditionalEffort.append("Совершенно согласен", null);
		smsSystemAdditionalEffort.append("Cогласен", null);
		smsSystemAdditionalEffort.append("Ничто", null);
		smsSystemAdditionalEffort.append("Не согласен", null);
		smsSystemAdditionalEffort.append("Совершенно не согласен", null);
		
		smsSystemTreatmentCompliance = new ChoiceGroup("Помогает ли система СМС напоминаний соблюдать режим лечения?" , ChoiceGroup.POPUP );
		smsSystemTreatmentCompliance.append("Совершенно согласен", null);
		smsSystemTreatmentCompliance.append("Cогласен", null);
		smsSystemTreatmentCompliance.append("Ничто", null);
		smsSystemTreatmentCompliance.append("Не согласен", null);
		smsSystemTreatmentCompliance.append("Совершенно не согласен", null);
		
		serviceByHealthFacility = new ChoiceGroup("Как вы оцениваете качество услуг в медучреждении?" , ChoiceGroup.POPUP );
		serviceByHealthFacility.append("Совершенно согласен", null);
		serviceByHealthFacility.append("Cогласен", null);
		serviceByHealthFacility.append("Ничто", null);
		serviceByHealthFacility.append("Не согласен", null);
		serviceByHealthFacility.append("Совершенно не согласен", null);
		
		careByTbSpecialist = new ChoiceGroup("Как вы оцениваете качество работы специалиста по ТБ?" , ChoiceGroup.POPUP );
		careByTbSpecialist.append("Совершенно согласен", null);
		careByTbSpecialist.append("Cогласен", null);
		careByTbSpecialist.append("Ничто", null);
		careByTbSpecialist.append("Не согласен", null);
		careByTbSpecialist.append("Совершенно не согласен", null);
		
		tbTreatmentSystem = new ChoiceGroup("Вас устраивает система лечения ТБ пациентов в стране?" , ChoiceGroup.POPUP );
		tbTreatmentSystem.append("Совершенно согласен", null);
		tbTreatmentSystem.append("Cогласен", null);
		tbTreatmentSystem.append("Ничто", null);
		tbTreatmentSystem.append("Не согласен", null);
		tbTreatmentSystem.append("Совершенно не согласен", null);
		
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
		String error = "Исправьте все ошибки: \n";
		if(participantID.getString().equals( "" ) || participantIDConfirm.getString().equals( "" ))
		{
			error = error + "Укажите номер пациента. \n";
			//tbrMidlet.showAlert ("Participant id(s) can't be empty.", null);
			result = false;
		}else
		
		if (!participantIDConfirm.getString ().equals (participantID.getString ())){
			error = error + "Номера не совпадают. \n";
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
						tbrMidlet.showAlert("Данные сохранены!" , null );


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
