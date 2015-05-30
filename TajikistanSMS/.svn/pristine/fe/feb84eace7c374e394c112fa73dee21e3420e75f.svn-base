package org.irdresearch.tbreachtajikistan.utils;

import java.text.*;
import java.util.Calendar;
import java.util.Date;
import org.joda.time.DateTime;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateUtils {

	//public static final String DATE_FORMAT_
	private Date date;
	private static DateTime dt=new DateTime();
	private static DateTimeFormatter yyyy_MM_dd_HH_mm_ss = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
	private static final DateTimeFormatter dd_MM_yyyy = DateTimeFormat.forPattern("dd-MM-yyyy");
	
	/**Date Fromat Jun 24, 2010 6:16:34 PM	 *//*
	public static final DateFormat DATE_FORMAT_MON_dd_year_hh_mm_ss_AM = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
	*//**Date Format 6/24/10 6:16 PM *//*
	public static final DateFormat DATE_FORMAT_m_dd_yy_hh_mm_AM = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
	*//**Date Fromat Thursday, June 24, 2010 6:16:34 PM PKT *//*
	public static final DateFormat DATE_FORMAT_DAYOFWEEK_MONTH_dd_yyyy_hh_mm_ss_AM_UTC = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
	*//**Date Format June 24, 2010 6:16:34 PM PKT  *//*
	public static final DateFormat DATE_FORMAT_MONTH_dd_yyyy_hh_mm_ss_UTC = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
	
	*/public DateUtils()
	{
		
	}
	public static boolean datesEqual(Date date1,Date date2){
		SimpleDateFormat sd=new SimpleDateFormat("dd-MM-yyyy");
		String dat1=sd.format(date1);
		String dat2=sd.format(date2);
		
		if(dat1.compareTo(dat2)==0){
			return true;
		}
		return false;
	}
	public static boolean datesEqualUptoHour(Date date1,Date date2){
		Calendar dt1=Calendar.getInstance();
		dt1.setTime(date1);
		Calendar dt2=Calendar.getInstance();
		dt2.setTime(date2);
		
		if(dt1.get(Calendar.YEAR)==dt2.get(Calendar.YEAR)&&dt1.get(Calendar.MONTH)==dt2.get(Calendar.MONTH)
				&&dt1.get(Calendar.DAY_OF_MONTH)==dt2.get(Calendar.DAY_OF_MONTH)&&dt1.get(Calendar.HOUR)==dt2.get(Calendar.HOUR)){
			return true;
		}
		return false;
	}
	public static boolean validateDate(String date){
		try{
			dd_MM_yyyy.parseDateTime(date);
			return true;
		}
		catch (Exception e) {
			try{
				yyyy_MM_dd_HH_mm_ss.parseDateTime(date);
				return true;
			}catch (Exception e1) {
				return false;
			}
		}
	}
	
	public static long getDifferenceInDays (Date startDate, Date EndDate){
		long diff = EndDate.getTime() - startDate.getTime();
		long diffDays = diff / (24 * 60 * 60 * 1000);
		return diffDays;		
	}
	
	public static Date subtractNumOfDays(Date date,int daysToSubttract){
		DateTime dt=new DateTime(date);
		dt.minusDays(daysToSubttract);
		return dt.toDate();
	}
	public static int getDifferenceOfHours(Date date1,Date date2){
		int diff=(int) ((date1.getTime()-date2.getTime())/(60*60*1000));
		return diff;
	}
	public static int getDifferenceOfMinutes(Date date1,Date date2){
		int diff=(int) ((date1.getTime()-date2.getTime())/(60*1000));
		return diff;
	}
	public static Date getPastDate(int yearsbefore){
		DateTime dt=new DateTime();
		dt.minusYears(yearsbefore);
		return dt.toDate();
	}
	public static int getNumOfYears(Date pastDate){
		DateTime dt=new DateTime(pastDate);
		DateTime now=new DateTime();
		int n=now.getYear();
		int d=dt.getYear();
		int years=n-d;
		return years;
	}
	public static String truncateDate(Date date){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		Calendar daybgn=Calendar.getInstance();
		daybgn.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 0);
		DateTime dt=new DateTime(daybgn);
		return dt.toString(yyyy_MM_dd_HH_mm_ss);
	}
	public static String roundoffDate(Date date){
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		Calendar dayend=Calendar.getInstance();
		dayend.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 23, 59, 59);
		DateTime dt=new DateTime(dayend);
		return dt.toString(yyyy_MM_dd_HH_mm_ss);
	}
	public static Date convertToDate(String date){
		try{
			return dd_MM_yyyy.parseDateTime(date).toDate();
		}catch (Exception e) {
			return yyyy_MM_dd_HH_mm_ss.parseDateTime(date).toDate();
		}
	}
	public static String convertToString(Date date){
		DateTime dt=new DateTime(date);
		try{
			return dt.toString(yyyy_MM_dd_HH_mm_ss);
		}catch (Exception e) {
			return dt.toString(dd_MM_yyyy);
		}
	}
	public static void main(String[] args) {
		/* DateFormat dfMedium = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
		 DateFormat dfShort = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
		 DateFormat dfLarge = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
		 DateFormat dfLong = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);*/

		System.out.println("now dt:"+new Date());
		Calendar c=Calendar.getInstance();

		System.out.println("now c:"+c.getTime());
		DateFormat df=DateFormat.getDateTimeInstance();
df.format(new Date());
Calendar cal=Calendar.getInstance();
cal.set(1929, 3, 2);
DateTime dt=new DateTime(cal);

DateTimeFormatter fmt = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss");
dt.toString(fmt);

//fmt.parseDateTime("29-03-2010");
//fmt.parseDateTime("29Nov2010");
		/*System.out.println(dfMedium.format(new Date()));
		System.out.println(dfShort.format(new Date()));
		System.out.println(dfLarge.format(new Date()));
		System.out.println(dfLong.format(new Date()));*/
		System.out.println(dd_MM_yyyy.parseDateTime("29-06-1999").toDate());
		System.out.println(new Date());
		try {
			System.out.print(df.parse("8-8-8"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Date getDate() {
		DateTime dt=new DateTime();
		return dt.toDate();
	}
	
	
}
