package com.hts.smf.report.project;

import java.math.BigDecimal;
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

public class BusninessLegalSLA implements ReportConstants {
	 private static final long serialVersionUID = 1L;	
	 List<String> wPriorityList = new ArrayList<String>();
	 Properties properties = null;
	 ArrayList<ResultBean> wResult = new  ArrayList<ResultBean>();
	 ArrayList<ResultBean> wResultWIP = new ArrayList<ResultBean>();
	 int holidaysCount = 0;
	 List<GregorianCalendar> holidays= new ArrayList<GregorianCalendar>();
	 Calendar sLAStartTime = new GregorianCalendar();
	 Calendar sLAEndTime = new GregorianCalendar();	
	 Calendar issueAssignedDate = new GregorianCalendar();
	 Calendar issueOnHoldDate = new GregorianCalendar();	 
	 Calendar issueWIPDate = new GregorianCalendar();
	 Calendar issueResolvedDate = new GregorianCalendar();	
	 ReportInputs wRInputs = new ReportInputs();
	 String wProject = null;	 
	 public List<ResultBean> start(String wQuery, Properties props, List<GregorianCalendar> holidaysList, List<String> priorityList, ReportInputs inputs) {	 
		 Connection conn = null;
		 Statement statement = null;
		 ResultSet resultSet = null;
		 try {
			this.properties = props;
			this.wPriorityList = priorityList;
			this.wRInputs = inputs;
			this.holidays = holidaysList;
			wProject = wRInputs.getProject();
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
			IssuesBean issue= new IssuesBean();
	    	issueNo = rs.getString(1);			    	
	    	issue.setIssueNo(rs.getString(1));
	    	issue.setIssueOpenDateandTime(rs.getString(2));
	    	issue.setChangeStatus(rs.getString(3));
	    	issue.setIssueStatusChangeTime(rs.getString(4));
	    	issue.setTicketType(rs.getString(5));
	    	issue.setServiceName(rs.getString(6));
		    issue.setTitle(rs.getString(7));
		    issue.setHldTicketNo(rs.getString(8));
		    issue.setEstimatedEffort(rs.getDouble(9));
		    issue.setProjectReceiptDate(rs.getString(10));
		    issue.setEffectiveProjectRecDate(rs.getString(11));
		    issue.setSAPEffort(rs.getDouble(12));
		    issue.setActualCompletionDate(rs.getString(13));
		    issue.setMilestoneValue(rs.getString(14));
		    issue.setRemainingEffort(rs.getDouble(15));
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
		int onHoldIndex=0;
		int resolvedIndex = 0;
		Calendar issueOpenDate = new GregorianCalendar();
		Calendar issueRejectedDate = new GregorianCalendar();
		Calendar wProjectReceiptDate = new GregorianCalendar();
		Calendar wActualCompletionDate = new GregorianCalendar();
		
		Date openDate = new Date();
		Date changeStatusDate = new Date();		
		Date assignedDate[] = new Date[10];		
		Date wipDate[] =new Date[10];		
		Date onHoldDate[] = new Date[10];	
		Date rejectedDate= new Date();	
		Date pReciptDate = new Date();
		Date aCompletionDate = new Date();
		
		issueResolvedDate.set(1900, 01, 01, 0, 0);
		issueOnHoldDate.set(1900, 01, 01, 0, 0);		
		issueRejectedDate.set(1900, 01, 01, 0, 0);	
			    
		String issueKey = null;
		String issueOpenDateandTime = null;
		String changeStatus = null;
		String issueStatusChangeTime = null;
		
		String ticketType = "";	
		String serviceName = "";
		String title = "";
		String hldticketno = "";		
		double estimatedEffort = 0;
		String projectReceiptDate="";
		String effectiveProjectRecDate="";
		double sapEffort = 0;
		String actualCompletionDate="";
		String wMilestonevalue="";
		double remEffort = 0;
		Iterator<IssuesBean> iter= issuesList.iterator();
		IssuesBean issue = null;
		
		while(iter.hasNext()){
		  issue =iter.next();						
		  changeStatus = issue.getChangeStatus();
		  issueStatusChangeTime = issue.getIssueStatusChangeTime();		  
		 if(changeStatus != null || issueStatusChangeTime != null){
			 changeStatusDate = sdf.parse(issueStatusChangeTime); 		 
		  if(changeStatus.equals(ASSIGNED)){
			 if(assignIndex != 0){
    			if(assignedDate[assignIndex - 1].equals(changeStatusDate) == false){
    				assignedDate[assignIndex] = changeStatusDate;
    				assignIndex = assignIndex + 1;
    			}
			  }else if(assignIndex == 0) {
    			assignedDate[assignIndex] = changeStatusDate;
    			assignIndex = assignIndex + 1;    			
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
	    	} else if(changeStatus.equals(RESOLVED)){
	    		resolvedIndex = resolvedIndex + 1;
	    	} else if(changeStatus.equals(ONHOLD)){
  				if(onHoldIndex != 0){
  					if(onHoldDate[onHoldIndex - 1].equals(changeStatusDate) == false){
  						onHoldIndex = onHoldIndex + 1;
  					}
  				} else if(onHoldIndex == 0){
  					onHoldIndex = onHoldIndex + 1;
  				}
	    	} else if(changeStatus.equals(REJECTED)){
	    		rejectedDate = changeStatusDate;
	    		issueRejectedDate.setTime(rejectedDate);
	    	}
		 }
		}	
		
		if(issue != null) {
			issueOpenDateandTime = issue.getIssueOpenDateandTime();
			openDate = sdf.parse(issueOpenDateandTime);
			issueOpenDate.setTime(openDate);	
			issueKey = issue.getIssueNo();			
			ticketType= issue.getTicketType();	
			serviceName = issue.getServiceName();
			title = issue.getTitle();
			hldticketno = issue.getHldTicketNo();
			estimatedEffort = issue.getEstimatedEffort();
			projectReceiptDate = issue.getProjectReceiptDate();
			effectiveProjectRecDate = issue.getEffectiveProjectRecDate();
			sapEffort = issue.getSAPEffort();
			actualCompletionDate = issue.getActualCompletionDate();		
			wMilestonevalue = issue.getMilestoneValue();
			remEffort = issue.getRemainingEffort();
		}	
		
		//CPI Report
		if(assignIndex > 0 && wipIndex > 0 && resolvedIndex > 0) {
			pReciptDate = sdf.parse(projectReceiptDate);
			wProjectReceiptDate.setTime(pReciptDate);
			aCompletionDate = sdf.parse(actualCompletionDate);
			wActualCompletionDate.setTime(aCompletionDate);
			double costPerformanceIndex = sapEffort/estimatedEffort;
			costPerformanceIndex = getDoubleValue(costPerformanceIndex);
			long wTAT = CalucalateTAT(wActualCompletionDate,wProjectReceiptDate);			
			String wSLA= calucalateSLA(wTAT, ticketType, serviceName);	
			//wTAT = wTAT/60/9;		
			setResultsToBean(issueKey,serviceName,ticketType,hldticketno,title,changeStatus,
					actualCompletionDate,estimatedEffort,sapEffort,0,wMilestonevalue,costPerformanceIndex,
					wTAT, wSLA);			
		} else if(assignIndex > 0 && wipIndex > 0 && resolvedIndex == 0){
			wActualCompletionDate.setTime(new Date());
			pReciptDate = sdf.parse(effectiveProjectRecDate);
			wProjectReceiptDate.setTime(pReciptDate);
			long wTAT = CalucalateTAT(wActualCompletionDate,wProjectReceiptDate);
			String wSLA= calucalateCrossedSLA(wTAT, ticketType, serviceName);
			double costPerformanceIndex =(sapEffort + remEffort)/estimatedEffort;	
			costPerformanceIndex = getDoubleValue(costPerformanceIndex);
			wTAT = wTAT/60/9;			
			setResultsToBean(issueKey, serviceName, ticketType, hldticketno, title, changeStatus, "",
					estimatedEffort,sapEffort, remEffort, "", costPerformanceIndex, wTAT, wSLA);	
		} else if(assignIndex > 0 && wipIndex == 0 && resolvedIndex == 0){
			wActualCompletionDate.setTime(new Date());
			pReciptDate = sdf.parse(effectiveProjectRecDate);
			wProjectReceiptDate.setTime(pReciptDate);
			long wTAT = CalucalateTAT(wActualCompletionDate,wProjectReceiptDate);
			String wSLA= calucalateCrossedSLA(wTAT, ticketType, serviceName);
			double costPerformanceIndex =(sapEffort + remEffort)/estimatedEffort;		
			costPerformanceIndex = getDoubleValue(costPerformanceIndex);
			wTAT = wTAT/60/9;					
			setResultsToBean(issueKey, serviceName, ticketType, hldticketno, title, changeStatus, "",
					estimatedEffort,sapEffort, remEffort, "", costPerformanceIndex, wTAT, wSLA);	
		}
	}

	/**
	 * @param costPerformanceIndex
	 * @return
	 */
	private double getDoubleValue(double costPerformanceIndex) {
		int decimalPlaces = 2;
		BigDecimal bd = new BigDecimal(costPerformanceIndex);
		bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
		costPerformanceIndex = bd.doubleValue();
		return costPerformanceIndex;
	}	
	
	private void setResultsToBean(String issueKey, String serviceName,
			String ticketType, String hldticketno, String title,
			String changeStatus, String actualCompletionDate, double estimatedEffort, double actualEffort,
			double remainingdays, String milestonevalue, double costPerformanceIndex, long tat, String sla) {		
		ResultBean rBean= new ResultBean();
		rBean.setIssueKey(issueKey);
		rBean.setIssueServiceName(serviceName);
		rBean.setTicketType(ticketType);
		rBean.setHldTicketNo(hldticketno);
		rBean.setTitle(title);
		rBean.setIssueStatus(changeStatus);
		rBean.setActualCompletionDate(actualCompletionDate);
		rBean.setEstimatedEffort(estimatedEffort);
		rBean.setSAPEffort(actualEffort);
		rBean.setMilestoneValue(milestonevalue);
		rBean.setCostPerformanceIndex(costPerformanceIndex);
		rBean.setRemainingDays(remainingdays);
		rBean.setTat(tat);		
		rBean.setResolutionSLA(sla);
		wResult.add(rBean);	
	}

	private long CalucalateTAT(Calendar actualCompletionDate,
			Calendar projectReceiptDate) {
		
		setSLAStartHour(actualCompletionDate);	
		setSLAEndHour(actualCompletionDate);		
		actualCompletionDate = checkSLATIME(actualCompletionDate);
		actualCompletionDate = checkHolidays(actualCompletionDate);
		
		setSLAStartHour(projectReceiptDate);	
		setSLAEndHour(projectReceiptDate);		
		projectReceiptDate = checkSLATIME(projectReceiptDate);
		projectReceiptDate = checkHolidays(projectReceiptDate);
		
		long TATTimeInMin = calucalteTimeInMinutes(projectReceiptDate, actualCompletionDate);	
		
		return TATTimeInMin;
	}

	private String calucalateCrossedSLA(long resolvedTime, String ticketType, String serviceName) {				
		wProject = wProject.replace(" ", "");	
		ticketType = ticketType.replace(" ", "");
		System.out.println(wProject);
		System.out.println(ticketType);
		System.out.println(serviceName);
		int wValue = Integer.parseInt((String) properties.get(wProject+"_"+ticketType+"_"+serviceName));
		if(wValue == 0){
			return "";
		}
		wValue = wValue * 9 * 60;
		if(resolvedTime <= wValue){
			return "";
		}			
		return "SLA Crossed";		
	}

	private String calucalateSLA(long resolvedTime, String ticketType, String serviceName) {				
		wProject = wProject.replace(" ", "");	
		ticketType = ticketType.replace(" ", "");
		int wValue = Integer.parseInt((String) properties.get(wProject+"_"+ticketType+"_"+serviceName));
		if(wValue == 0){
			return SLA_MET;
		}
		wValue = wValue * 9 * 60;
		if(resolvedTime <= wValue){
			return SLA_MET;
		}			
		return SLA_NOT_MET;		
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
	
	public ArrayList<ResultBean> getResults(){
		return wResult;
	}
	
	public ArrayList<ResultBean> getWIPResults(){
		return wResultWIP;
	}
	
	
	private long calucalteDateDiff(Calendar Time1, Calendar Time2 ) {
		long wTimeOne = Time1.getTimeInMillis();
		long wTimeTwo = Time2.getTimeInMillis();		
		long diff = wTimeOne - wTimeTwo;
	    long dMinutes = diff / (60 * 1000);
	    return dMinutes;
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
}
