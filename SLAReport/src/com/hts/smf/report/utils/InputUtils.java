package com.hts.smf.report.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

import com.hts.smf.report.beans.ReportInputs;

public class InputUtils implements ReportConstants {

	private Properties properties = null;

	public Properties loadProps(InputStream inputStream){
		 if(properties == null){
			 properties = new Properties();			   
		 }		 		
		 try {
			properties.load(inputStream);						
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	 }
	
	public List<GregorianCalendar> wHTSHolidayList(Properties wProperties, ReportInputs inputs) {
		String wLocation = "";
		if(inputs.getProject().equalsIgnoreCase("EXIM")){
			wLocation ="EXIM";
		} else{
			wLocation = inputs.getLocation().replace(" ", "");
		}
		int wYear = Integer.parseInt(inputs.getFromDate().substring(0, 4));
		List<GregorianCalendar> holidays = new ArrayList<GregorianCalendar>();		
		String holidayList = (String) wProperties.get(wLocation+"_"+HOLIDAYS_LIST+"_"+wYear);		
		StringTokenizer str = new StringTokenizer(holidayList, "$$");
		int d=0;
		while (str.hasMoreElements()) {
			String element = (String) str.nextElement();
			int wMonth = Integer.parseInt(element.split("/")[0]);
			int wDay = Integer.parseInt(element.split("/")[1]);
			holidays.add(new GregorianCalendar(wYear, wMonth-1, wDay));
			d++;
		}		
		return holidays;
		
		/*List<GregorianCalendar> holidays = new ArrayList<GregorianCalendar>();
		String yearList= (String) wProperties.get(YEAR);
		List<Integer> stYear = new ArrayList<Integer>();
		if(stYear != null){
			StringTokenizer yrStr = new StringTokenizer(yearList, "$$");		
			while(yrStr.hasMoreElements()){
				stYear.add(Integer.parseInt((String) yrStr.nextElement()));			 
			}		
		}
		for(int y=0; y < stYear.size(); y++){	
			int wYear = stYear.get(y);
			String holidayList = (String) wProperties.get(wLocation+"_"+HOLIDAYS_LIST+"_"+wYear);			
			if(holidayList != null){
				StringTokenizer str = new StringTokenizer(holidayList, "$$");			
				while (str.hasMoreElements()) {
					String element = (String) str.nextElement();
					int wMonth = Integer.parseInt(element.split("/")[0]);
					int wDay = Integer.parseInt(element.split("/")[1]);
					holidays.add(new GregorianCalendar(wYear, wMonth-1, wDay));				
				}	
			}
		}
		return holidays;*/
	}
	
	public List<String> getPriorityList(Properties wProperties, String projectName, String ticketType) {		
		projectName = projectName.replace(" ", "");
		ticketType = ticketType.replace(" ", "");
		List<String> wPriorityList = new ArrayList<String>();
		SortedSet<Object> sortedSet = new TreeSet<Object>(wProperties.keySet());		
		for(Iterator<Object> iter = sortedSet.iterator(); iter.hasNext();){
			String element= (String) iter.next();		
			String ele[]=element.split("_");
			if(ele[0].equalsIgnoreCase(projectName)&&ele[1].equalsIgnoreCase(ticketType))
			{
				wPriorityList.add(ele[2]);	
			}
		}
		Collections.sort(wPriorityList);
		return wPriorityList;
	}
}
