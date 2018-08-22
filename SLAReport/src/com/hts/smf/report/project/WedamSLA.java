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
import java.text.SimpleDateFormat;
import com.hts.smf.report.beans.IssuesBean;
import com.hts.smf.report.beans.ReportInputs;
import com.hts.smf.report.beans.ResultBean;
import com.hts.smf.report.utils.MYSQLConnection;
import com.hts.smf.report.utils.ReportConstants;
import java.sql.ResultSetMetaData;

public class WedamSLA implements ReportConstants {
	 private static final long serialVersionUID = 1L;	
	 List<String> wPriorityList = new ArrayList<String>();
	 Properties properties = null;
	 ArrayList<ResultBean> wResult = new  ArrayList<ResultBean>();
	 int holidaysCount = 0;
	 int numberOfColumns = 0;
	 List<GregorianCalendar> holidays= new ArrayList<GregorianCalendar>();
	 Calendar sLAStartTime = new GregorianCalendar();
	 Calendar sLAEndTime = new GregorianCalendar();	
	 Calendar issueAssignedDate = new GregorianCalendar();
	 Calendar issueOnHoldDate = new GregorianCalendar();	 
	 Calendar issueWIPDate = new GregorianCalendar();
	 Calendar issueResolvedDate = new GregorianCalendar();	
	 //Added by rohit for Operations report
	 Calendar issueWIFDate = new GregorianCalendar();
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
			//statement.setFetchSize(1000);
			//statement = (PreparedStatement) conn.prepareStatement(wQuery);			
			resultSet= statement.executeQuery(wQuery);	
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
			  rs.setFetchSize(100);
			  while(rs.next()){
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
				    issue.setReasonForReopen(rs.getString(9));
				    issue.setAssignee(rs.getString(10));
				    issue.setReasonForReject(rs.getString(11));
				    issue.setUnits(rs.getString(12));
				    issue.setExpectedResolutionTime(rs.getString(13));
				    issue.setSummary(rs.getString(14));
				    
				    issue.setReporter(rs.getString(15));
				    issue.setCostCenterGroupOne(rs.getString(16));
				    issue.setCostCenterGroupTwo(rs.getString(17));
				    ResultSetMetaData rsMetaData = rs.getMetaData();
				    numberOfColumns = rsMetaData.getColumnCount();
				    
				    if(numberOfColumns == 18)
				    {
				    	
				    	issue.setSubServiceName(rs.getString(18));  
				    }
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
		int wipIndex=0;
		int wifIndex = 0;
		int onHoldIndex=0;
		int resolvedIndex = 0;
		Calendar issueOpenDate = Calendar.getInstance();
		Calendar issueRejectedDate = new GregorianCalendar();	
		
		Date changeStatusDate = new Date();		
		Date assignedDate[] = new Date[10];		
		Date wipDate[] =new Date[10];	
		Date wifDate[] = new Date[10];
		Date resolvedDate = new Date();
		Date onHoldDate[] = new Date[10];	
		Date resolvedDates[] = new Date[10];
		Date rejectedDate= new Date();
		
		issueResolvedDate.set(1900, 01, 01, 0, 0);
		issueOnHoldDate.set(1900, 01, 01, 0, 0);		
		issueRejectedDate.set(1900, 01, 01, 0, 0);		
			
		Calendar dateNow = Calendar.getInstance();
	    assignedDate[0]= dateNow.getTime();	   
	    resolvedDate= dateNow.getTime(); 
		
		String isRejectedDate = null;
		String isAssignedDate = null;
		String isResolvedDate = null;
		String issueKey = null;
		String issueOpenDateandTime = null;
		String changeStatus = null;
		String issueStatusChangeTime = null;
		String priority = "";	
		String serviceName = "";
		String subServiceName = "";
		String businessUnit = "";
		String location = "";
		String reOpenReason ="";
		String assignee="";
		String rejectReason="";
		String units="";
		String expResTime="";
		String summary = "";
		String reporter = "";
		String ccg1="";
		String ccg2="";
		Iterator<IssuesBean> iter= issuesList.iterator();
		IssuesBean issue = null;
		while(iter.hasNext()){
		  issue =iter.next();	
		  changeStatus = issue.getChangeStatus();
		  issueStatusChangeTime = issue.getIssueStatusChangeTime();		
		  if(issueStatusChangeTime!=null)
		  {
		  issueStatusChangeTime= issueStatusChangeTime.substring(0,issueStatusChangeTime.lastIndexOf(":"));
		  issueStatusChangeTime=issueStatusChangeTime.concat(":00");
		  }
		 if(changeStatus != null || issueStatusChangeTime != null){
			 if(issueStatusChangeTime!=""&&issueStatusChangeTime!=null)
			 changeStatusDate = sdf.parse(issueStatusChangeTime); 
		 
		  if(changeStatus.equals(ASSIGNED)){
    		if(assignIndex == 0) {
    			assignedDate[assignIndex] = changeStatusDate;
    			assignIndex = assignIndex + 1;
    			isAssignedDate = issueStatusChangeTime;
    		} 
		  }else if(changeStatus.equals(WORKINPROGRESS)){
	    		if(wipIndex != 0){
	    			if(wipDate[wipIndex - 1].equals(changeStatusDate) == false){
	    				wipDate[wipIndex] = changeStatusDate;
	    				wipIndex = wipIndex + 1;
	    			}
	    		} else if(wipIndex == 0){
	    				wipDate[wipIndex] = changeStatusDate;
	    				wipIndex = wipIndex + 1;
	    		}    		
	    	}
		//Code needs to be added here for Waiting For Internal Function
		  else if(changeStatus.equals(WAITING_INTERNAL_FUNCTION))
		  {
			  if(wifIndex != 0)
			  {
				  //Check whether it is the same date or not.
				  if(wifDate[wifIndex - 1].equals(changeStatusDate) == false)
				  {
					  wifDate[wifIndex] = changeStatusDate;
					  wifIndex = wifIndex + 1;
				  }
				  
			  }
			  else if(wifIndex == 0)
			  {
				  wifDate[wifIndex] = changeStatusDate;
				  wifIndex = wifIndex + 1;
			  }
			  
	    	} else if(changeStatus.equals(RESOLVED)){
	    		resolvedDate = changeStatusDate;
	    		resolvedDates[resolvedIndex] = resolvedDate;
	    		resolvedIndex = resolvedIndex + 1;
	    		isResolvedDate = issueStatusChangeTime;
	    	} else if(changeStatus.equals(ONHOLD)){
  				if(onHoldIndex != 0){
  					if(onHoldDate[onHoldIndex - 1].equals(changeStatusDate) == false){
  						onHoldDate[onHoldIndex] = changeStatusDate;
  						onHoldIndex = onHoldIndex + 1;
  					}
  				} else if(onHoldIndex == 0){
  					onHoldDate[onHoldIndex] = changeStatusDate;
  					onHoldIndex = onHoldIndex + 1;
  				}
	    	} else if(changeStatus.equals(REJECTED)){
	    		rejectedDate = changeStatusDate;
	    		issueRejectedDate.setTime(rejectedDate);
	    	}
		 }
		}
		if(issue != null){
			issueOpenDateandTime = issue.getIssueOpenDateandTime();
			if(issueStatusChangeTime != null)
			issueOpenDateandTime= issueOpenDateandTime.substring(0,issueStatusChangeTime.lastIndexOf(":"));
			issueOpenDateandTime=issueOpenDateandTime.concat(":00");			
			issueOpenDate.setTime(sdf.parse(issueOpenDateandTime));				
			issueKey = issue.getIssueNo();
			priority = issue.getPriority();	
			serviceName = issue.getServiceName();
			businessUnit = issue.getBusinessUnit();
			location = issue.getLocation();
			assignee=issue.getAssignee();
			units = issue.getUnits();
			subServiceName = issue.getSubServiceName();
			expResTime = issue.getExpectedResolutionTime();
			summary = issue.getSummary();
			reporter = issue.getReporter();
			ccg1= issue.getCostCenterGroupOne();
			ccg2 = issue.getCostCenterGroupTwo();
			if(issue.getReasonForReject() != null){
				rejectReason=issue.getReasonForReject();
			}
			if(issue.getReasonForReopen() != null){
				reOpenReason = issue.getReasonForReopen();
			}		
		}
		long responseTimeInMinutes = 0;		
		long onHoldTimeinMinutes = 0;
		long wifTimeinMinutes = 0;
		long resolvedTimeInMinutes = 0;		
		//Added by rohit
		long totalTimeInMinutes = 0;
		if((issueRejectedDate.get(Calendar.YEAR) == 1900) &&(issueRejectedDate.get(Calendar.MONTH) == 01)) {
			if(assignIndex > 0){					
				responseTimeInMinutes = calResponseTime(issueOpenDate,assignedDate, assignIndex);				
			}
			if(assignIndex > 0 && wipIndex > 0){				
				if(onHoldIndex > 0 &&  resolvedIndex > 0) {
					onHoldTimeinMinutes = calOnHoldTime(onHoldDate,wipDate, onHoldIndex, wipIndex, resolvedIndex);
				}
				//Code needs to be added here.
				if(wifIndex > 0 && resolvedIndex > 0)
				{
					wifTimeinMinutes = calWIFTime(wifIndex,wipIndex,onHoldIndex,resolvedIndex,wifDate,wipDate,onHoldDate,resolvedDates);
				}
				
			}
			if(assignIndex > 0 && wipIndex > 0 && resolvedIndex > 0) {
				resolvedTimeInMinutes = calResolvedTime(issueAssignedDate, onHoldDate, wipDate, resolvedDate, wipIndex,onHoldIndex);					
				resolvedTimeInMinutes  = responseTimeInMinutes + resolvedTimeInMinutes;
				totalTimeInMinutes = resolvedTimeInMinutes + onHoldTimeinMinutes;
				String responseSLAMsg = resposeSLA(responseTimeInMinutes);
				String resolutionSLAMsg = resolvedSLA(resolvedTimeInMinutes, priority,units,expResTime);
				//String resolutionSLAMsg = resolvedSLA(resolvedTimeInMinutes, priority);
				
				setResultsToBean(issueKey,priority, serviceName,subServiceName,summary,reporter, businessUnit,location, assignee,reOpenReason,rejectReason, changeStatus, issueOpenDateandTime, isAssignedDate, isResolvedDate,
						responseTimeInMinutes, resolvedTimeInMinutes, onHoldTimeinMinutes,wifTimeinMinutes,totalTimeInMinutes,ccg1,ccg2,
						responseSLAMsg, resolutionSLAMsg);	
			} else if(assignIndex > 0 && wipIndex == 0 && resolvedIndex == 0){
				
				String responseSLAMsg = resposeSLA(responseTimeInMinutes);
				
				setResultsToBean(issueKey,priority, serviceName,subServiceName,summary,reporter, businessUnit, location, assignee,reOpenReason,rejectReason, changeStatus, issueOpenDateandTime, isAssignedDate, "",
						responseTimeInMinutes, resolvedTimeInMinutes, onHoldTimeinMinutes,wifTimeinMinutes,totalTimeInMinutes,ccg1,ccg2,
						responseSLAMsg, OPEN);
			} else if(assignIndex > 0 && onHoldIndex > 0 && resolvedIndex == 0){
				String responseSLAMsg = resposeSLA(responseTimeInMinutes);
				setResultsToBean(issueKey,priority, serviceName,subServiceName,summary,reporter, businessUnit, location, assignee,reOpenReason,rejectReason, changeStatus, issueOpenDateandTime, isAssignedDate, "",
						responseTimeInMinutes, resolvedTimeInMinutes, onHoldTimeinMinutes,wifTimeinMinutes,totalTimeInMinutes,ccg1,ccg2,
						responseSLAMsg, "Open");				
			} else  if(assignIndex > 0 && wipIndex > 0 && resolvedIndex == 0){
				String responseSLAMsg = resposeSLA(responseTimeInMinutes);
				
				setResultsToBean(issueKey,priority, serviceName,subServiceName,summary,reporter, businessUnit, location, assignee,reOpenReason,rejectReason, changeStatus, issueOpenDateandTime, isAssignedDate, "",
						responseTimeInMinutes, resolvedTimeInMinutes, onHoldTimeinMinutes,wifTimeinMinutes,totalTimeInMinutes,ccg1,ccg2,
						responseSLAMsg, "Open");
			}
		} else {
			if(issueRejectedDate.get(Calendar.YEAR) != 1900){
			isRejectedDate = issueRejectedDate.get(Calendar.YEAR)+"-"+ strMonths[issueRejectedDate.get(Calendar.MONTH)] +"-" +issueRejectedDate.get(Calendar.DAY_OF_MONTH)+"  "+issueRejectedDate.get(Calendar.HOUR)+":" + issueRejectedDate.get(Calendar.MINUTE);
			
			setResultsToBean(issueKey,priority, serviceName,subServiceName,summary,reporter, businessUnit, location, assignee,reOpenReason,rejectReason, changeStatus, issueOpenDateandTime, REJECTED+":"+isRejectedDate, REJECTED,
					responseTimeInMinutes, resolvedTimeInMinutes, onHoldTimeinMinutes,wifTimeinMinutes,totalTimeInMinutes,ccg1,ccg2,
					REJECTED, REJECTED);
			}
		}		
	}	

	
	@SuppressWarnings("unchecked")
	private String resolvedSLA(long resolvedTime, String propPriority,String units,String expResTime) {
		int expValue = 0;
		String wProject= wRInputs.getProject();
		String wTicketType = wRInputs.getTicketType();
		wProject = wProject.replace(" ", "");
		wTicketType = wTicketType.replace(" ", "");
		if(propPriority == null){
			propPriority = "Cat1";			
		} else{
			propPriority = propPriority.replace(" ", "");
		}
	//	if(propPriority.equals("Cat3")){
			//if((wProject.equalsIgnoreCase("Operations"))&& wTicketType.equals(OFFICE_RELATED)){
			
			if(((wProject.equalsIgnoreCase("Operations"))&& (wTicketType.equals(OFFICE_RELATED)))||((wProject.equalsIgnoreCase("Operations"))&& (wTicketType.equals(OPERATIONS_ADMIN)))||((wProject.equalsIgnoreCase("Operations"))&& (wTicketType.equals(OPERATIONS_WORKSPACE)))||((wProject.equalsIgnoreCase("Operations"))&& (wTicketType.equals(OPERATIONS_FACILITY)))||((wProject.equalsIgnoreCase("Operations"))&& (wTicketType.equals(OPERATIONS_SECURITY)))){
					//(wProject.equals(WEDAM))&& wTicketType.equals(OFFICE_RELATED)){--Rohit Modified
				if(expResTime != null)
				{	//expResTime.replace(" ", "");			
					if(expResTime.matches(".*[a-zA-Z].*"))
					{
						return "";
					}
					else
					{
						//System.out.println(expResTime);
						expValue = Integer.parseInt(expResTime.replace(".", " ").replace(" ", ""));
						if(units != null)
						{
							if(units.equalsIgnoreCase("Day"))
							expValue *= 540;
							else if(units.equalsIgnoreCase("Hour"))
							expValue *= 60;
							/*else if(units.equalsIgnoreCase("Minute"))
							expValue = 60;*/
						}
						
					}
				}
				
				if(resolvedTime > expValue)
				   return SLA_NOT_MET;
				else
					return SLA_MET;
				
			}
		//}
		/*if(propPriority.equals("Cat6")){
			if((wProject.equals("WEDAM"))&& wTicketType.equals(HSE_SERVICES)){
				return "";
			}
		}*/
		if(propPriority.equals("Cat6")){
			if((wProject.equalsIgnoreCase("Operations"))&& wTicketType.equals(HSE_SERVICES)){
				if(expResTime != null)
				{
					if(expResTime.matches(".*[a-zA-Z].*"))
					{
						return "";
					}
					else
					{
						expValue = Integer.parseInt(expResTime.replace(".", " ").replace(" ", ""));
						if(units != null)
						{
							if(units.equalsIgnoreCase("Day"))
							expValue *= 480;
							else if(units.equalsIgnoreCase("Hour"))
							expValue *= 60;
							/*else if(units.equalsIgnoreCase("Minutes"))
							expValue = 60;*/
						}
					}
				}
				if(resolvedTime > expValue)
				   return SLA_NOT_MET;
				else
					return SLA_MET;
			}
		}
		if(propPriority.equals("Cat4")){
			if((wProject.equalsIgnoreCase("Operations"))&& wTicketType.equals(LAB_SERVICES)){
				if(expResTime != null)
				{
					
					if(expResTime.matches(".*[a-zA-Z].*"))
					{
						return "";
					}
					else
					{
						expValue = Integer.parseInt(expResTime.replace(".", " ").replace(" ", ""));
						if(units != null)
						{
							if(units.equalsIgnoreCase("Day"))
							expValue *= 480;
							else if(units.equalsIgnoreCase("Hour"))
							expValue *= 60;
							/*else if(units.equalsIgnoreCase("Minutes"))
							expValue = 60;*/
						}
					}
				}
				
				if(resolvedTime > expValue)
				   return SLA_NOT_MET;
				else
					return SLA_MET;
			
			}
		}
		/*if(propPriority.equals("Cat4")){
			if((wProject.equals("WEDAM"))&& wTicketType.equals(LAB_SERVICES)){
				return "";
			}
		}*/
		//double wExpectedResultion= (resolvedTime / 60);		
		for(Iterator wPri = wPriorityList.iterator(); wPri.hasNext();){
			String wKey = (String) wPri.next();			
			float wValue = Float.parseFloat((String) properties.get(wProject+"_"+wTicketType+"_"+wKey));
			if(wKey.equals(propPriority)){
				wValue = wValue * 60;
				if(resolvedTime <= wValue){
					return SLA_MET;
				} 
			}
		}		
		return SLA_NOT_MET;	
	}
	private long calResolvedTime(Calendar issueAssignedDate2,
			Date[] onHoldDate, Date[] wipDate, Date resolvedDate, int wipIndex,
			int onHoldIndex) {
		int onHold =0;
		int wip = 0;
		long assignToFirstOnHoldinMinutes = 0;
		long assignToResolveinMinutes = 0;
		if(onHoldIndex > 0){
			while(onHold < onHoldIndex){
				issueOnHoldDate.setTime(onHoldDate[onHold]);				
				setSLAStartHour(issueOnHoldDate);
				setSLAEndHour(issueOnHoldDate);
				issueOnHoldDate = checkSLATIME(issueOnHoldDate);				
				issueOnHoldDate = checkHolidays(issueOnHoldDate);
			 
			if(wip > 0){
				issueWIPDate.setTime(wipDate[wip]);
				setSLAStartHour(issueWIPDate);
				setSLAEndHour(issueWIPDate);
				issueWIPDate = checkSLATIME(issueWIPDate);		
				issueWIPDate = checkHolidays(issueWIPDate);
				if(issueWIPDate.before(issueOnHoldDate)){
					assignToFirstOnHoldinMinutes += calucalteTimeInMinutes(issueWIPDate, issueOnHoldDate);
				}
			}  else {
				
				assignToFirstOnHoldinMinutes += calucalteTimeInMinutes(issueAssignedDate, issueOnHoldDate);
			}
			wip++;
			onHold ++;
			} 
			
			issueWIPDate.setTime(wipDate[wipIndex - 1]);
			setSLAStartHour(issueWIPDate);
			setSLAEndHour(issueWIPDate);
			issueWIPDate = checkSLATIME(issueWIPDate);		
			issueWIPDate = checkHolidays(issueWIPDate);
							
			issueResolvedDate.setTime(resolvedDate);				
			setSLAStartHour(issueResolvedDate);
			setSLAEndHour(issueResolvedDate);				
			issueResolvedDate = checkSLATIME(issueResolvedDate);			
			issueResolvedDate = checkHolidays(issueResolvedDate);				
			
			assignToFirstOnHoldinMinutes += calucalteTimeInMinutes(issueWIPDate, issueResolvedDate);
										
		} else {
			issueResolvedDate.setTime(resolvedDate);
			setSLAStartHour(issueResolvedDate);
			setSLAEndHour(issueResolvedDate);				
			issueResolvedDate = checkSLATIME(issueResolvedDate);			
			issueResolvedDate = checkHolidays(issueResolvedDate);
			
			assignToResolveinMinutes = calucalteTimeInMinutes(issueAssignedDate, issueResolvedDate); 
			
		}		
		assignToResolveinMinutes = assignToResolveinMinutes + assignToFirstOnHoldinMinutes;	
		return assignToResolveinMinutes;
	}

	private long calOnHoldTime(Date[] onHoldDate, Date[] wipDate,
			int onHoldIndex, int wipIndex, int resolvedIndex) {
		int onHold = 0;
		int wip =1;	
		long onHoldTimeinMinutes =0;
		issueOnHoldDate.setTime(onHoldDate[onHold]);				
		issueWIPDate.setTime(wipDate[0]);
		if(issueOnHoldDate.before(issueWIPDate)){
			setSLAStartHour(issueOnHoldDate);
			setSLAEndHour(issueOnHoldDate);
			issueOnHoldDate = checkSLATIME(issueOnHoldDate);				
			issueOnHoldDate = checkHolidays(issueOnHoldDate);
			setSLAStartHour(issueWIPDate);
			setSLAEndHour(issueWIPDate);
			issueWIPDate = checkSLATIME(issueWIPDate);		
			issueWIPDate = checkHolidays(issueWIPDate);
			return onHoldTimeinMinutes += calucalteTimeInMinutes(issueOnHoldDate, issueWIPDate);
		}				
		while(onHold < onHoldIndex){
			issueOnHoldDate.setTime(onHoldDate[onHold]);
			
			setSLAStartHour(issueOnHoldDate);
			setSLAEndHour(issueOnHoldDate);
			issueOnHoldDate = checkSLATIME(issueOnHoldDate);				
			issueOnHoldDate = checkHolidays(issueOnHoldDate);		
			
			if(wipIndex >= wip){
				if(wipDate[wip]!=null)
				issueWIPDate.setTime(wipDate[wip]);
				if(resolvedIndex > 1){
					while(wip < wipIndex){						
						issueWIPDate.setTime(wipDate[wip]);
						if(issueWIPDate.before(issueOnHoldDate)){
							wip ++;
						} else{
							break;
						}
					}
				}
				setSLAStartHour(issueWIPDate);
				setSLAEndHour(issueWIPDate);
				issueWIPDate = checkSLATIME(issueWIPDate);		
				issueWIPDate = checkHolidays(issueWIPDate);
			
			}
			
			onHoldTimeinMinutes += calucalteTimeInMinutes(issueOnHoldDate, issueWIPDate);
											
			wip++;				
			onHold++;				
		}
		return onHoldTimeinMinutes;
	}

	private long calResponseTime(Calendar wIssueOpenDate, Date[] assignedDate,
			int assignIndex) {
		setSLAStartHour(wIssueOpenDate);	
		setSLAEndHour(wIssueOpenDate);		
		wIssueOpenDate = checkSLATIME(wIssueOpenDate);
		wIssueOpenDate = checkHolidays(wIssueOpenDate);
		
		int assign=0;			
		while(assign < assignIndex){
			issueAssignedDate.setTime(assignedDate[assign]);
			setSLAStartHour(issueAssignedDate);
			setSLAEndHour(issueAssignedDate);	
			issueAssignedDate = checkSLATIME(issueAssignedDate);				
			issueAssignedDate = checkHolidays(issueAssignedDate);				
			assign++;
		}			
		long responseTimeInMin = calucalteTimeInMinutes(wIssueOpenDate, issueAssignedDate);									
		
		return responseTimeInMin;
	}

	//Added the below method by rohit to calculate waiting for internal function time
	private long calWIFTime(int wifIndex,int wipIndex,int onHoldIndex,int resolvedIndex,Date[] wifDate,Date[] wipDate,Date[] onHoldDate,Date[] resolvedDates)
	{
		long WIFTimeInMinutes = 0;
		int wif = 0;
		int wip = 0;
		int onHold = 0;
		int flag = 0;
		int resolved = 0;
		
		while(wif < wifIndex)
		{
			wip = 0;
			issueWIFDate.setTime(wifDate[wif]);
			if(wipIndex > 0)
			while(wip < wipIndex)
			{
				resolved = 0;
				onHold = 0;
				flag =0;
				issueWIPDate.setTime(wipDate[wip]);
				if(issueWIFDate.before(issueWIPDate))
				{
					if(onHoldIndex > 0)
					while(onHold < onHoldIndex)
					{
						issueOnHoldDate.setTime(onHoldDate[onHold]);
						if(issueOnHoldDate.before(issueWIPDate) && issueOnHoldDate.after(issueWIFDate))
						{
							//don't calculate the delay time here for that particular WIPDate.
							flag = 1;
							//continue;
						}
						
						onHold ++;
					}
					if(resolvedIndex > 0)
					while(resolved < resolvedIndex)
					{
						issueResolvedDate.setTime(resolvedDates[resolved]);
						if(issueResolvedDate.before(issueWIPDate) && issueResolvedDate.after(issueWIFDate))
						{
							flag = 1;
						}
						resolved ++;
					}
					if(!(flag == 1))
					{
						//Calculate delay time between issueWIFDate and issueWIPDate and break the loop here
						setSLAStartHour(issueWIFDate);
						setSLAEndHour(issueWIFDate);
						issueWIFDate = checkSLATIME(issueWIFDate);				
						issueWIFDate = checkHolidays(issueWIFDate);
						setSLAStartHour(issueWIPDate);
						setSLAEndHour(issueWIPDate);
						issueWIPDate = checkSLATIME(issueWIPDate);		
						issueWIPDate = checkHolidays(issueWIPDate);
						WIFTimeInMinutes += calucalteTimeInMinutes(issueWIFDate, issueWIPDate);
						break;
					}
				}
					
					wip++;
				}
			onHold = 0;
			if(onHoldIndex > 0)
			while(onHold < onHoldIndex)
			{
				flag = 0;
				wip = 0;
				issueOnHoldDate.setTime(onHoldDate[onHold]);
				if(issueWIFDate.before(issueOnHoldDate))
				{
					if(wipIndex > 0)
					while(wip < wipIndex)
					{
						issueWIPDate.setTime(wipDate[wip]);
						if(issueWIPDate.before(issueOnHoldDate) && issueWIPDate.after(issueWIFDate))
						{
							//don't calculate the delay time here for that particular onHoldDate
							flag = 1;
							
						}
						wip ++;
					}
					if(!(flag == 1))
					{
						//Calculate the delay time between issueWIFDate and issueOnHoldDate and break the loop here
						setSLAStartHour(issueWIFDate);
						setSLAEndHour(issueWIFDate);
						issueWIFDate = checkSLATIME(issueWIFDate);				
						issueWIFDate = checkHolidays(issueWIFDate);
						setSLAStartHour(issueOnHoldDate);
						setSLAEndHour(issueOnHoldDate);
						issueOnHoldDate = checkSLATIME(issueOnHoldDate);		
						issueOnHoldDate = checkHolidays(issueOnHoldDate);
						WIFTimeInMinutes += calucalteTimeInMinutes(issueWIFDate, issueOnHoldDate);
						break;
					}
				}
				
				onHold ++;
				}
			
			resolved = 0;
			while(resolved < resolvedIndex)
			{
			issueResolvedDate.setTime(resolvedDates[resolved]);
			if(resolvedIndex > 0 && issueWIFDate.before(issueResolvedDate))
			{
				flag = 0;
				wip = 0;
				onHold = 0;
			while(wip < wipIndex)
			{
				
				issueWIPDate.setTime(wipDate[wip]);
				if(issueWIPDate.before(issueResolvedDate) && issueWIPDate.after(issueWIFDate))
				{
					//don't calculate delay for this kind of scenarios
					flag = 1;
					
				}
				
					wip ++;
					
			}
			while(onHold < onHoldIndex)
			{
				issueOnHoldDate.setTime(onHoldDate[onHold]);
				if(issueOnHoldDate.before(issueResolvedDate) && issueOnHoldDate.after(issueWIFDate))
				{
					//don't calculate the delay time here for that particular WIPDate.
					flag = 1;
					//continue;
				}
				
				onHold ++;
			}
			if(!(flag == 1))
			{
				//Calculate the delay time here between issueWIFDate and issueResolvedDate 
				setSLAStartHour(issueWIFDate);
				setSLAEndHour(issueWIFDate);
				issueWIFDate = checkSLATIME(issueWIFDate);				
				issueWIFDate = checkHolidays(issueWIFDate);
				setSLAStartHour(issueResolvedDate);
				setSLAEndHour(issueResolvedDate);
				issueResolvedDate = checkSLATIME(issueResolvedDate);		
				issueResolvedDate = checkHolidays(issueResolvedDate);
				WIFTimeInMinutes += calucalteTimeInMinutes(issueWIFDate,issueResolvedDate);
			}		
			}
			resolved ++;
			}
			wif ++;
		
		}
		return WIFTimeInMinutes;
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
		for (Calendar calendar : holidays) {							
			if(issueDate.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) && issueDate.get(Calendar.MONTH)== calendar.get(Calendar.MONTH) 
					&& issueDate.get(Calendar.DAY_OF_MONTH)== calendar.get(Calendar.DAY_OF_MONTH)){
				issueDate.add(Calendar.DAY_OF_MONTH, 1);
				issueDate.set(issueDate.get(Calendar.YEAR),issueDate.get(Calendar.MONTH), issueDate.get(Calendar.DAY_OF_MONTH), SLASTARTHOUR, SLASTARTMIN);				
			}			
		}
		return issueDate;
	}

	private void setResultsToBean(String issueName, String priorityName,
			String serviceNames,String subServiceName,String summary,String reporter, String businessUnit, String location, String assignee,String reOpenReason,String rejectReason, String issueStatus, String openDate, String assignDate,
			String resolveDate, long responseInMinutes, long resolveInMinutes, long onHoldInMinutes,long wifTimeinMinutes, long totalTimeInMinutes,String ccg1,String ccg2,
			String responseSLAMsg, String resolutionSLAMsg) {
		
		ResultBean resultBean= new ResultBean();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs= null;		
		String assigneeeid=assignee;
		if(assignee != null){
			if(assignee.length() > 0){
				try {
					Class.forName(Driver).newInstance();
					conn = DriverManager.getConnection(URL+DBName,UserName,Password);				
					if(conn != null){
						stmt= conn.createStatement();
						rs = stmt.executeQuery("SELECT display_name from dbo.cwd_user where user_name= "+"'"+assignee+"'");	
						
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
		resultBean.setIssueReOpenReason(reOpenReason);
		resultBean.setIssueStatus(issueStatus);
		resultBean.setIssueOpenDate(openDate);
		resultBean.setIssueAssignedDate(assignDate);
		resultBean.setIssueResolvedDate(resolveDate);
		resultBean.setResponseTime(responseInMinutes);
		resultBean.setResolutionTime(resolveInMinutes);
		resultBean.setOnHoldTime(onHoldInMinutes);
		resultBean.setWifTimeinMinutes(wifTimeinMinutes);
		//Added by rohit
		resultBean.setTotalTimeInMinutes(totalTimeInMinutes);
		resultBean.setResponseSLA(responseSLAMsg);
		resultBean.setResolutionSLA(resolutionSLAMsg);
		resultBean.setIssueAssignee(assignee);
		resultBean.setIssueAssigneeEID(assigneeeid);
		resultBean.setIssueRejectReason(rejectReason);
		resultBean.setSubServiceName(subServiceName);
		resultBean.setSummary(summary);
		resultBean.setReporter(reporter);
		resultBean.setCostCenterGroupOne(ccg1);
		resultBean.setCostCenterGroupTwo(ccg2);
		wResult.add(resultBean);		
	}
	
	public ArrayList<ResultBean> getResults(){
		return wResult;
	}
	
	private long calucalteDateDiff(Calendar Time1, Calendar Time2 ) {
		long wTimeOne = Time1.getTimeInMillis();
		long wTimeTwo = Time2.getTimeInMillis();		
		long diff = wTimeOne - wTimeTwo;
	    //long dSeconds = d / 1000;
	    long dMinutes = diff / (60 * 1000);
	    //long dHour = d / (60 * 60 * 1000);
	    //long dDays = d / (24 * 60 * 60 * 1000);
	    return dMinutes;
	}
	
	private String resposeSLA(long responseTimeInMinutes) {
		if(responseTimeInMinutes <= 30){
			return SLA_MET;
		}
		return SLA_NOT_MET;
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
			if(days<1)
				days=1;
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