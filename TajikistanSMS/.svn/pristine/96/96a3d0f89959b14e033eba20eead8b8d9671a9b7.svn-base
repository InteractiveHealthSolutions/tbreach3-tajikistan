package org.irdresearch.tbreachtajikistan.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class LoggerUtil {
	static SimpleDateFormat sd=new SimpleDateFormat("EEE-dd-MMM-yyyy HH:mm:ss");
	static Logger log=Logger.getLogger("rollingFile");
	
	static Logger getSimpleLogger(){
		return log;
	}
	public static void logIt(String o){
		getSimpleLogger().error("\n"+sd.format(new Date())+":"+o);
	}
}
