package com.hts.smf.report.project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.hts.smf.report.beans.IssuesBean;
import com.hts.smf.report.beans.ReportInputs;
import com.hts.smf.report.beans.ResultBean;
import com.hts.smf.report.utils.MYSQLConnection;
import com.hts.smf.report.utils.ReportConstants;

public class TransportSLA implements ReportConstants {
	 private static final long serialVersionUID = 1L;	
	 List<String> wPriorityList = new ArrayList<String>();
	 Properties properties = null;
	 ArrayList<ResultBean> wResult = new  ArrayList<ResultBean>();
	 int holidaysCount = 0;
	 List<GregorianCalendar> holidays= new ArrayList<GregorianCalendar>();
	 Calendar sLAStartTime = new GregorianCalendar();
	 Calendar sLAEndTime = new GregorianCalendar();		 
	 ReportInputs wRInputs = new ReportInputs();
	 
	 public List<ResultBean> start(String wQuery, Properties props, List<GregorianCalendar> holidaysList, List<String> priorityList, ReportInputs inputs) {	 
		 Connection conn = null;
		 Statement statement = null;
		 ResultSet resultSet = null;
		 try {
			this.properties = props;
			this.wPriorityList = priorityList;
			this.wRInputs = inputs;
			this.holidays = holidaysList;
			MYSQLConnection mySqlConnection= new MYSQLConnection();					
			conn = mySqlConnection.getConnection();		
			statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
		              ResultSet.CONCUR_READ_ONLY);			
			resultSet = statement.executeQuery(wQuery);	
			getResults(resultSet);
			resultSet.close();
			statement.close();
			conn.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wResult; 
	}
	
	 private void getResults(ResultSet rs) {
		   try {		
			  List<IssuesBean> issuesList = new ArrayList<IssuesBean>();
			  String issueNo= null;	      
			  while(rs.next()){
				  String ticketType = wRInputs.getTicketType();
				  ticketType = ticketType.replace(" ", "");
		    	    IssuesBean issue= new IssuesBean();
			    	issueNo = rs.getString(1);
			    	issue.setIssueNo(rs.getString(1));
			    	issue.setIssueOpenDateandTime(rs.getString(2));
			    	issue.setChangeStatus(rs.getString(3));
			    	issue.setIssueStatusChangeTime(rs.getString(4));
			    	issue.setPriority(rs.getString(5));
			    	issue.setServiceName(rs.getString(6));
					issue.setBusinessUnit(rs.getString(7));
					issue.setLocation(rs.getString(8));			    	
				    issuesList.add(issue); 
			    	
			    	if(rs.next()){
					   String nextIssue = rs.getString(1);
					   if(issueNo.equals(nextIssue)){
						   rs.previous();					 
					   } else{
						   calucalateSLA(issuesList);					  
						   issuesList.clear();
						   rs.previous();
					   }
					 } else {
						 calucalateSLA(issuesList);
					 }
		      }	
			  System.out.println(CONNECTION_CLOSED);
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
	}	
	
	private void calucalateSLA(List<IssuesBean> issuesList) throws ParseException {			
		int assignIndex=0;
		int onHoldIndex=0;
		int resolvedIndex = 0;
		Calendar issueOpenDate = new GregorianCalendar();
		Calendar issueAssigndDate = new GregorianCalendar();
		Calendar issueRejectedDate = new GregorianCalendar();
		Calendar issueOnHoldDate = new GregorianCalendar();		
		Calendar issueResolvedDate = new GregorianCalendar();
		
		Date openDate = new Date();
		Date changeStatusDate = new Date();		
		issueAssigndDate.set(1900, 01, 01, 0, 0);
		issueResolvedDate.set(1900, 01, 01, 0, 0);
		issueOnHoldDate.set(1900, 01, 01, 0, 0);		
		issueRejectedDate.set(1900, 01, 01, 0, 0);		
			
	    String isRejectedDate = null;
		String isResolvedDate = null;
		String issueKey = null;
		String issueOpenDateandTime = null;
		String changeStatus = null;
		String issueStatusChangeTime = null;
		String priority = "";	
		String serviceName = "";
		String businessUnit = "";
		String location = "";				
		Iterator<IssuesBean> iter= issuesList.iterator();
		IssuesBean issue = null;
		while(iter.hasNext()){
		  issue =iter.next();			   
		  changeStatus = issue.getChangeStatus();
		  issueStatusChangeTime = issue.getIssueStatusChangeTime();
		
		 if(changeStatus != null || issueStatusChangeTime != null){
			 changeStatusDate = sdf.parse(issueStatusChangeTime); 
		 
		  if(changeStatus.equals(ASSIGNED)){
    		if(assignIndex == 0) {
    			issueAssigndDate.setTime(changeStatusDate);
    			assignIndex = assignIndex + 1;
    		} 
		  } else if(changeStatus.equals(RESOLVED)){
			  issueResolvedDate.setTime(changeStatusDate);
	    		resolvedIndex = resolvedIndex + 1;
	    		isResolvedDate = issueStatusChangeTime;
	    	} else if(changeStatus.equals(ONHOLD)){
  				if(onHoldIndex == 0){
  					issueOnHoldDate.setTime(changeStatusDate);
  					onHoldIndex = onHoldIndex + 1;
  				}
	    	} else if(changeStatus.equals(REJECTED)){
	    		issueRejectedDate.setTime(changeStatusDate);
	    	}
		 }
		}		
		if(issue != null){
			issueOpenDateandTime = issue.getIssueOpenDateandTime();
			openDate = sdf.parse(issueOpenDateandTime);
			issueOpenDate.setTime(openDate);	
			issueKey = issue.getIssueNo();
			priority = issue.getPriority();	
			serviceName = issue.getServiceName();
			businessUnit = issue.getBusinessUnit();
			location = issue.getLocation();
		}
		
		long responseTimeInMinutes = 0;		
		long onHoldTimeinMinutes = 0;
		long resolvedTimeInMinutes = 0;	
		if((issueRejectedDate.get(Calendar.YEAR) == 1900) &&(issueRejectedDate.get(Calendar.MONTH) == 01)) {
			if(wRInputs.getTicketType().replace(" ", "").equals(LATE_NIGHT_BOOKING)){
				if(resolvedIndex > 0){
					resolvedTimeInMinutes = calucalteDateDiff(issueResolvedDate, issueOpenDate);
					String resolutionSLAMsg = resolvedSLA(resolvedTimeInMinutes, priority);
					setResultsToBean(issueKey,priority,serviceName, businessUnit, location, changeStatus, issueOpenDateandTime,isResolvedDate,
								responseTimeInMinutes, resolvedTimeInMinutes, onHoldTimeinMinutes,"", resolutionSLAMsg);
				}
			} else {
				if(assignIndex > 0){
					responseTimeInMinutes = calTimeinMin(issueOpenDate,issueAssigndDate);				
				}
				
				if(assignIndex > 0 && onHoldIndex > 0 && resolvedIndex > 0){				
					onHoldTimeinMinutes = calTimeinMin(issueOnHoldDate,issueResolvedDate);
				}
				if(assignIndex > 0 && resolvedIndex > 0){				
					resolvedTimeInMinutes = calTimeinMin(issueAssigndDate, issueResolvedDate);
					resolvedTimeInMinutes = responseTimeInMinutes + resolvedTimeInMinutes - onHoldTimeinMinutes;
					String responseSLAMsg = resposeSLA(responseTimeInMinutes);
					String resolutionSLAMsg = resolvedSLA(resolvedTimeInMinutes, priority);					
					setResultsToBean(issueKey,priority,serviceName, businessUnit, location, changeStatus, issueOpenDateandTime,isResolvedDate,
							responseTimeInMinutes, resolvedTimeInMinutes, onHoldTimeinMinutes,responseSLAMsg, resolutionSLAMsg);

				} else {
					setResultsToBean(issueKey,priority,serviceName, businessUnit, location, changeStatus, issueOpenDateandTime,isResolvedDate,
							responseTimeInMinutes, resolvedTimeInMinutes, onHoldTimeinMinutes,OPEN, OPEN);

				}
			}
		} else{
			isRejectedDate = issueRejectedDate.get(Calendar.YEAR)+"-"+ strMonths[issueRejectedDate.get(Calendar.MONTH)] +"-" +issueRejectedDate.get(Calendar.DAY_OF_MONTH)+"  "+issueRejectedDate.get(Calendar.HOUR)+":" + issueRejectedDate.get(Calendar.MINUTE);				
			setResultsToBean(issueKey,priority, serviceName, businessUnit, location, changeStatus, issueOpenDateandTime, REJECTED+":"+isRejectedDate, 
					responseTimeInMinutes, resolvedTimeInMinutes, onHoldTimeinMinutes,REJECTED, REJECTED);
		}				
	}	
	
	private String resposeSLA(long responseTimeInMinutes) {
		if(responseTimeInMinutes <= 30){
			return SLA_MET;
		}
		return SLA_NOT_MET;
	}
	private long calTimeinMin(Calendar issueAssigndDate,
			Calendar issueResolvedDate) {		
		setSLAStartHour(issueAssigndDate);	
		setSLAEndHour(issueAssigndDate);		
		issueAssigndDate = checkSLATIME(issueAssigndDate);
		issueAssigndDate = checkHolidays(issueAssigndDate);
		
		setSLAStartHour(issueResolvedDate);	
		setSLAEndHour(issueResolvedDate);		
		issueResolvedDate = checkSLATIME(issueResolvedDate);
		issueResolvedDate = checkHolidays(issueResolvedDate);
		
		long timeInMin = calucalteTimeInMinutes(issueAssigndDate, issueResolvedDate);		
		return timeInMin;
	}
	
	@SuppressWarnings("unchecked")
	private String resolvedSLA(long resolvedTime, String propPriority) {				
		String wLocation= wRInputs.getProject();
		String wTicketType = wRInputs.getTicketType();
		wLocation = wLocation.replace(" ", "");
		wTicketType = wTicketType.replace(" ", "");
		if(propPriority == null){
			propPriority = "Cat1";
		}	
		
		if(propPriority.equals("Cat3")){
			return SLA_MET;
		}
		for(Iterator wPri = wPriorityList.iterator(); wPri.hasNext();){
			String wKey = (String) wPri.next();			
			int wValue = Integer.parseInt((String) properties.get(wLocation+"_"+wTicketType+"_"+wKey));
			if(wKey.contains(propPriority)){
				wValue = wValue * 60;
				if(resolvedTime <= wValue){
					return SLA_MET;
				} 
			}
		}		
		return SLA_NOT_MET;	
	}
	
	private void setResultsToBean(String issueName, String priorityName, String serviceNames, 
			String businessUnit, String location, String issueStatus, String openDate,
			String resolveDate, long responseInMinutes, long resolveInMinutes, long onHoldInMinutes,
			String responseSLAMSG, String resolutionSLAMsg) {		
		ResultBean resultBean= new ResultBean();
		resultBean.setIssueKey(issueName);
		resultBean.setIssuePriority(priorityName);
		resultBean.setIssueServiceName(serviceNames);
		resultBean.setIssueBusinessUnit(businessUnit);
		resultBean.setIssueLocation(location);
		resultBean.setIssueStatus(issueStatus);
		resultBean.setIssueOpenDate(openDate);
		resultBean.setIssueResolvedDate(resolveDate);
		resultBean.setResponseTime(responseInMinutes);
		resultBean.setResolutionTime(resolveInMinutes);
		resultBean.setOnHoldTime(onHoldInMinutes);
		resultBean.setResponseSLA(responseSLAMSG);
		resultBean.setResolutionSLA(resolutionSLAMsg);
		wResult.add(resultBean);		
	}
	
	public ArrayList<ResultBean> getResults(){
		return wResult;
	}
	
	private long calucalteDateDiff(Calendar Time1, Calendar Time2 ) {
		long wTimeOne = Time1.getTimeInMillis();
		long wTimeTwo = Time2.getTimeInMillis();		
		long diff = wTimeOne - wTimeTwo;
	    long dMinutes = diff / (60 * 1000);
	    return dMinutes;
	}
	
	private Calendar checkSLATIME(Calendar issueDate) {
		if(issueDate.getTime().before(sLAStartTime.getTime())){
			issueDate.set(issueDate.get(Calendar.YEAR),issueDate.get(Calendar.MONTH), issueDate.get(Calendar.DAY_OF_MONTH), SLASTARTHOUR, SLASTARTMIN);
		} else if(issueDate.getTime().after(sLAEndTime.getTime())){
			issueDate.add(Calendar.DAY_OF_MONTH, 1);
			issueDate.set(issueDate.get(Calendar.YEAR),issueDate.get(Calendar.MONTH), issueDate.get(Calendar.DAY_OF_MONTH), SLASTARTHOUR, SLASTARTMIN);
		}
		return issueDate;
	}
	private void setSLAStartHour(Calendar slaStartHour) {
		sLAStartTime.set(slaStartHour.get(Calendar.YEAR),slaStartHour.get(Calendar.MONTH), slaStartHour.get(Calendar.DAY_OF_MONTH), SLASTARTHOUR, SLASTARTMIN);
	}
	
	private void setSLAEndHour(Calendar slaEndHour) {
		sLAEndTime.set(slaEndHour.get(Calendar.YEAR),slaEndHour.get(Calendar.MONTH), slaEndHour.get(Calendar.DAY_OF_MONTH), SLAENDHOUR, SLAENDMIN);
	}

	
	private Calendar checkHolidays(Calendar issueDate) {
		holidaysCount = 0;		
		for (Calendar calendar : holidays) {							
			if(issueDate.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) && issueDate.get(Calendar.MONTH)== calendar.get(Calendar.MONTH) 
					&& issueDate.get(Calendar.DAY_OF_MONTH)== calendar.get(Calendar.DAY_OF_MONTH)){
				issueDate.add(Calendar.DAY_OF_MONTH, 1);
				issueDate.set(issueDate.get(Calendar.YEAR),issueDate.get(Calendar.MONTH), issueDate.get(Calendar.DAY_OF_MONTH), SLASTARTHOUR, SLASTARTMIN);				
			}			
		}	
		
		if(issueDate.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
			issueDate.add(Calendar.DAY_OF_MONTH, 1);
			issueDate.set(issueDate.get(Calendar.YEAR),issueDate.get(Calendar.MONTH), issueDate.get(Calendar.DAY_OF_MONTH), SLASTARTHOUR, SLASTARTMIN);			
		} 
		
		if(issueDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			issueDate.add(Calendar.DAY_OF_MONTH, 1);
			issueDate.set(issueDate.get(Calendar.YEAR),issueDate.get(Calendar.MONTH), issueDate.get(Calendar.DAY_OF_MONTH), SLASTARTHOUR, SLASTARTMIN);
		}
		
		return issueDate;
	}
	
	private long calucalteTimeInMinutes(Calendar issueStartDate, Calendar issueChangeStatusDate) {
		long days = daysBetween(issueStartDate,issueChangeStatusDate);
		long diffInMinutes = 0;
		if(days >= 1){
			Date d= new Date();
			Calendar wStartTimeOne= new GregorianCalendar();
			Calendar wStartTimeTwo =new GregorianCalendar();
			d = issueStartDate.getTime();
			wStartTimeOne.setTime(d);
			wStartTimeTwo.setTime(d);
			//wStartTimeOne = issueStartDate;	
			wStartTimeOne.set(wStartTimeOne.get(Calendar.YEAR), wStartTimeOne.get(Calendar.MONTH), wStartTimeOne.get(Calendar.DAY_OF_MONTH), SLAENDHOUR, SLAENDMIN);			
			diffInMinutes = diffInMinutes + calucalteDateDiff(wStartTimeOne, wStartTimeTwo);
			wStartTimeTwo.add(Calendar.DAY_OF_MONTH, (int)days);				
			wStartTimeTwo.set(wStartTimeTwo.get(Calendar.YEAR),wStartTimeTwo.get(Calendar.MONTH), wStartTimeTwo.get(Calendar.DAY_OF_MONTH), SLASTARTHOUR, SLASTARTMIN);
			diffInMinutes = diffInMinutes + calucalteDateDiff(issueChangeStatusDate, wStartTimeTwo);				
			days = days - holidaysCount;
			diffInMinutes = diffInMinutes +((60*9) * (days-1));
		} else {
			diffInMinutes = calucalteDateDiff(issueChangeStatusDate, issueStartDate);
		}	
		return diffInMinutes;
	}
	
	private long daysBetween(Calendar startDate, Calendar endDate) {  
	   Calendar date = (Calendar) startDate.clone();
	   date.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH),date.get(Calendar.DAY_OF_MONTH), SLAENDHOUR, SLAENDMIN);
	   long daysBetween = 0;  
	   holidaysCount = 0;
	   while (date.before(endDate)) {  
	       date.add(Calendar.DAY_OF_MONTH, 1);  
	       if(date.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
	    	   holidaysCount = holidaysCount + 1; 
	       } 
	       if(date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
	    	   holidaysCount = holidaysCount + 1;  
	       }
	       for (Calendar calendar : holidays) {
				if(date.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) && date.get(Calendar.MONTH)== calendar.get(Calendar.MONTH) && date.get(Calendar.DAY_OF_MONTH)== calendar.get(Calendar.DAY_OF_MONTH)){
					 holidaysCount = holidaysCount + 1;				
				}			
			}	
	    daysBetween++;	       
	   }	   
	   return daysBetween;  
	}  
}