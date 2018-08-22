package com.hts.smf.report.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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

public class TravelSLA implements ReportConstants {
	 private static final long serialVersionUID = 1L;	
	 List<String> wPriorityList = new ArrayList<String>();
	 Properties properties = null;
	 ArrayList<ResultBean> wResult = new  ArrayList<ResultBean>();
	 int holidaysCount = 0;
	 List<GregorianCalendar> holidays= new ArrayList<GregorianCalendar>();
	 Calendar sLAStartTime = new GregorianCalendar();
	 Calendar sLAEndTime = new GregorianCalendar();	
	 Calendar issueOnHoldDate = new GregorianCalendar();	 
	 Calendar issueResolvedDate = new GregorianCalendar();
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
					issue.setAssignee(rs.getString(9));
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
		int onHoldIndex=0;
		int resolvedIndex = 0;
		Calendar issueOpenDate = new GregorianCalendar();
		
		Date openDate = new Date();
		Date changeStatusDate = new Date();		
		Date resolvedDate = new Date();
		Date onHoldDate = new Date();	
		
		issueResolvedDate.set(1900, 01, 01, 0, 0);
		issueOnHoldDate.set(1900, 01, 01, 0, 0);		
			
		Calendar dateNow = Calendar.getInstance();
	    resolvedDate= dateNow.getTime(); 
		String isResolvedDate = null;
		String issueKey = null;
		String issueOpenDateandTime = null;
		String changeStatus = null;
		String issueStatusChangeTime = null;
		String priority = "";	
		String serviceName = "";
		String businessUnit = "";
		String assignee ="";
		String location = "";				
		Iterator<IssuesBean> iter= issuesList.iterator();
		IssuesBean issue = null;
		while(iter.hasNext()){
		  issue =iter.next();			   
		  changeStatus = issue.getChangeStatus();
		  issueStatusChangeTime = issue.getIssueStatusChangeTime();
		
		 if(changeStatus != null || issueStatusChangeTime != null){
			 changeStatusDate = sdf.parse(issueStatusChangeTime); 
		 
		 if(changeStatus.equals(RESOLVED)){
	    		resolvedDate = changeStatusDate;
	    		resolvedIndex = resolvedIndex + 1;
	    		isResolvedDate = issueStatusChangeTime;
	     } else if(changeStatus.equals(ONHOLD)){
  					onHoldDate = changeStatusDate;
  					onHoldIndex = onHoldIndex + 1; 				
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
			assignee=issue.getAssignee();
		}
		
		long onHoldTimeinMinutes = 0;
		long resolvedTimeInMinutes = 0;
		if(onHoldIndex > 0 &&  resolvedIndex > 0) {
			issueResolvedDate.setTime(resolvedDate);
			issueOnHoldDate.setTime(onHoldDate);
			onHoldTimeinMinutes = calOnHoldTime(issueOnHoldDate, issueResolvedDate);			
		}
		if(resolvedIndex > 0) {
			issueResolvedDate.setTime(resolvedDate);			
			resolvedTimeInMinutes = calResolvedTime(issueOpenDate, resolvedDate);
			resolvedTimeInMinutes = resolvedTimeInMinutes - onHoldTimeinMinutes;
			String resolutionSLAMsg = resolvedSLA(resolvedTimeInMinutes, priority);
			setResultsToBean(issueKey,assignee,priority, serviceName, businessUnit, location, changeStatus, issueOpenDateandTime,isResolvedDate,
						resolvedTimeInMinutes, onHoldTimeinMinutes, resolutionSLAMsg);				
		} else {
			setResultsToBean(issueKey,assignee,OPEN, serviceName, businessUnit, location, OPEN, issueOpenDateandTime,OPEN,
					resolvedTimeInMinutes, onHoldTimeinMinutes, OPEN);	
		}
	}
	
	private long calResolvedTime(Calendar issueOpenedDate,Date resolvedDate) {
		long assignToResolveinMinutes = 0;		
		issueResolvedDate.setTime(resolvedDate);		
		setSLAStartHour(issueOpenedDate);
		setSLAEndHour(issueOpenedDate);				
		issueOpenedDate = checkSLATIME(issueOpenedDate);			
		issueOpenedDate = checkHolidays(issueOpenedDate);		
		setSLAStartHour(issueResolvedDate);
		setSLAEndHour(issueResolvedDate);				
		issueResolvedDate = checkSLATIME(issueResolvedDate);			
		issueResolvedDate = checkHolidays(issueResolvedDate);		
		assignToResolveinMinutes = calucalteTimeInMinutes(issueOpenedDate, issueResolvedDate);	
		return assignToResolveinMinutes;
	}
	
	private long calOnHoldTime(Calendar wOnholdStartDate, Calendar resolvedDate) {
		setSLAStartHour(wOnholdStartDate);	
		setSLAEndHour(wOnholdStartDate);		
		wOnholdStartDate = checkSLATIME(wOnholdStartDate);
		wOnholdStartDate = checkHolidays(wOnholdStartDate);				
		long onHoldTimeInMin = calucalteTimeInMinutes(wOnholdStartDate, wOnholdStartDate);	
		
		return onHoldTimeInMin;
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
	
	private void setSLAStartHour(Calendar slaStartHour) {
		sLAStartTime.set(slaStartHour.get(Calendar.YEAR),slaStartHour.get(Calendar.MONTH), slaStartHour.get(Calendar.DAY_OF_MONTH), SLASTARTHOUR, SLASTARTMIN);
	}
	
	private void setSLAEndHour(Calendar slaEndHour) {
		sLAEndTime.set(slaEndHour.get(Calendar.YEAR),slaEndHour.get(Calendar.MONTH), slaEndHour.get(Calendar.DAY_OF_MONTH), SLAENDHOUR, SLAENDMIN);
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
		for (Calendar calendar : holidays) {							
			if(issueDate.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) && issueDate.get(Calendar.MONTH)== calendar.get(Calendar.MONTH) 
					&& issueDate.get(Calendar.DAY_OF_MONTH)== calendar.get(Calendar.DAY_OF_MONTH)){
				issueDate.add(Calendar.DAY_OF_MONTH, 1);
				issueDate.set(issueDate.get(Calendar.YEAR),issueDate.get(Calendar.MONTH), issueDate.get(Calendar.DAY_OF_MONTH), SLASTARTHOUR, SLASTARTMIN);				
			}			
		}
		return issueDate;
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
	
	private void setResultsToBean(String issueName, String assignee,String priorityName, String serviceNames, 
			String businessUnit, String location, String issueStatus, String openDate,
			String resolveDate, long resolveInMinutes, long onHoldInMinutes,
			String resolutionSLAMsg) {		
		String URL ="jdbc:mysql://localhost:3306/";
		String DBName ="crowddb";
		ResultBean resultBean= new ResultBean();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs= null;		
		if(assignee != null){
			if(assignee.length() > 0){
				try {
					Class.forName(Driver).newInstance();
					conn = DriverManager.getConnection(URL+DBName,"stem","");				
					if(conn != null){
						stmt= conn.createStatement();
						rs = stmt.executeQuery("SELECT ATTRIBUTEVALUES.VALUE AS VALUE FROM ATTRIBUTEVALUES,"+
								"ATTRIBUTES WHERE ATTRIBUTES.ID = ATTRIBUTEVALUES.ATTRIBUTEVALUEID AND REMOTEPRINCIPALNAME = "+
								"'"+assignee+"' AND ATTRIBUTES.ATTRIBUTE ='displayName';");	
						
						while(rs.next()){					
							assignee = rs.getString(1);
						}					
					}				
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						rs.close();
						stmt.close();
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
				}
			}
		}
		
		resultBean.setIssueKey(issueName);
		resultBean.setIssuePriority(priorityName);
		resultBean.setIssueServiceName(serviceNames);
		resultBean.setIssueBusinessUnit(businessUnit);
		resultBean.setIssueLocation(location);
		resultBean.setIssueStatus(issueStatus);
		resultBean.setIssueOpenDate(openDate);
		resultBean.setIssueAssignee(assignee);
		resultBean.setIssueResolvedDate(resolveDate);
		resultBean.setResolutionTime(resolveInMinutes);
		resultBean.setOnHoldTime(onHoldInMinutes);
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
}
