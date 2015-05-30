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
		participantID = new TextField( "Регистрационный номер пациента" , (String)queryData.get("pid") , 8 , TextField.UNEDITABLE );
		oldStartTreatment = new TextField("Дата начала лечения" , (String)queryData.get("treatmentinitate"), 10, TextField.UNEDITABLE);
		//startTreatment = new DateField( "новый: Дата начала лечения (если изменилось)" ,  DateField.DATE);
		//startTreatment.setDate( null );
		
		weight = new TextField( "Вес пациента (кг)" , "" , 3  , TextField.NUMERIC );
		
		baselineSputum = new ChoiceGroup( "Микроскопия мазка (диагностика)" , ChoiceGroup.POPUP );
		baselineSputum.append( "Нет" , null );
		baselineSputum.append( "Отр" , null );
		baselineSputum.append( "1-9 КУБ" , null );
		baselineSputum.append( "1+" , null );
		baselineSputum.append( "2+" , null );
		baselineSputum.append( "3+" , null );
		
		baselineChest = new ChoiceGroup( "Рентгенография легких (диагностика)" , ChoiceGroup.POPUP );
		baselineChest.append( "Нет" , null );
		baselineChest.append( "Нормальный" , null );
		baselineChest.append( "Подозрение на ТБ" , null );
		baselineChest.append( "Большое подозрение на ТБ" , null );
		baselineChest.append( "Деструктивность (обусловлена ТБ)" , null );
		baselineChest.append( "Деструктивность (обусловлена не ТБ)" , null );
		
		otherXraySite = new ChoiceGroup("Локализация ТБ", ChoiceGroup.POPUP);
		otherXraySite.append( "Легкие" , null );
		otherXraySite.append( "Таз" , null );
		otherXraySite.append( "Кости" , null );
		otherXraySite.append( "Другие" , null );
		
		resultXray = new ChoiceGroup("Рентгенография органов (диагностика)", ChoiceGroup.POPUP);
		resultXray.append( "Нет" , null );
		resultXray.append( "Нормальный" , null );
		resultXray.append( "Подозрение на ТБ" , null );
		resultXray.append( "Большое подозрение на ТБ" , null );
		resultXray.append( "Деструктивность (обусловлена ТБ)" , null );
		resultXray.append( "Деструктивность (обусловлена не ТБ)" , null );
		
		//TODO: Should we remove extra option? 
		
		baselineGeneXpert = new ChoiceGroup( "Результаты GeneXpert" , ChoiceGroup.POPUP );
		baselineGeneXpert.append( "Нет" , null );
		baselineGeneXpert.append( "МБТ -" , null );
		baselineGeneXpert.append( "МБТ +" , null );
		//baselineGeneXpert.append( "Номаълум" , null );
		//baselineGeneXpert.append( "Хато" , null );
		
		geneXpertDrugSensitivity = new ChoiceGroup( "Чувствительность к Rif" , ChoiceGroup.POPUP );
		geneXpertDrugSensitivity.append( "Нет" , null );
		geneXpertDrugSensitivity.append( "МБТ Rif +" , null );
		geneXpertDrugSensitivity.append( "МБТ Rif -" , null );
		//geneXpertDrugSensitivity.append( "Номаълум" , null );
		//geneXpertDrugSensitivity.append( "Хато" , null );
		//geneXpertDrugSensitivity.append( "Нест" , null );
		
		typePatient = new ChoiceGroup( "Тип пациента" , ChoiceGroup.POPUP );
		typePatient.append( "Новый случай" , null );
		typePatient.append( "Рецидив" , null );
		typePatient.append( "Переведен из" , null );
		typePatient.append( "После отрыва" , null );
		typePatient.append( "После неудачи лечения" , null );
		typePatient.append( "Другие" , null );
		
		patientCategory = new ChoiceGroup( "Категория лечения пациента" , ChoiceGroup.POPUP );
		patientCategory.append( "Категория 1" , null );
		patientCategory.append( "Категория 2" , null );
		patientCategory.append( "Категория 3" , null );
		
		regimen = new ChoiceGroup( "Режим лечения" , ChoiceGroup.POPUP );
		regimen.append( "RHZE" , null );
		regimen.append( "RHZES" , null );
		
		fixedDose = new ChoiceGroup( "Доза назначенного препарата " , ChoiceGroup.POPUP );
		fixedDose.append( "1" , null );
		fixedDose.append( "1.5" , null );
		fixedDose.append( "2" , null );
		fixedDose.append( "3" , null );
		fixedDose.append( "4" , null );
		fixedDose.append( "5" , null );
		
		strepto = new ChoiceGroup( "Доза стрептомицина" , ChoiceGroup.POPUP );
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
						tbrMidlet.showAlert("Данные сохранены!" , null );


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
			if(patientCategory.getString( patientCategory.getSelectedIndex() ).equals( "Категория 1" ))
			{
				regimen.setSelectedIndex( 0 , true);
				
			}
			
			else if(patientCategory.getString( patientCategory.getSelectedIndex() ).equals( "Категория 2" ))
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
			tbrMidlet.showAlert ("Укажите вес пациента", null);
			result = false;
		}
		return result;
	}

	public void commandAction(Command c , Item item) {
		
		
	}


}
