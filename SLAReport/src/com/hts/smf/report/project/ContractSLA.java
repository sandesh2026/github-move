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

public class ContractSLA implements ReportConstants {
	 private static final long serialVersionUID = 1L;	
	 List<String> wPriorityList = new ArrayList<String>();
	 Properties properties = null;
	 ArrayList<ResultBean> wResult = new  ArrayList<ResultBean>();
	 int holidaysCount = 0;
	 List<GregorianCalendar> holidays= new ArrayList<GregorianCalendar>();
	 Calendar sLAStartTime = new GregorianCalendar();
	 Calendar sLAEndTime = new GregorianCalendar();	
	 Calendar issueAssignedDate = new GregorianCalendar();
	 Calendar issueOnHoldDate = new GregorianCalendar();	 
	 Calendar issueWIPDate = new GregorianCalendar();
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
			Calendar c1=new GregorianCalendar();
			Calendar c2=new GregorianCalendar();
			SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-mm-dd");
			c1.setTime(sdf1.parse(wRInputs.getFromDate()));
			c2.setTime(sdf1.parse(wRInputs.getToDate()));
			long d=Math.abs(daysBetween(c1, c2));
			MYSQLConnection mySqlConnection= new MYSQLConnection();
			if(d>2)
			{
				conn = mySqlConnection.getConnection(true);
			}
			else
			{
				conn = mySqlConnection.getConnection();
			}
			
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
			    	//issue.setPriority(rs.getString(5));
			    	issue.setServiceName(rs.getString(5));
				    /*issue.setBusinessUnit(rs.getString(7));
				    issue.setLocation(rs.getString(8));
				    issue.setReasonForReopen(rs.getString(9));
				    issue.setAssignee(rs.getString(10));
				    issue.setReasonForReject(rs.getString(11));*/
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
		Calendar issueOpenDate = Calendar.getInstance();
		Calendar issueRejectedDate = new GregorianCalendar();
		
		
		Date changeStatusDate = new Date();		
		Date assignedDate = new Date();		
		Date wipDate[] =new Date[10];		
		Date resolvedDate = new Date();
		Date onHoldDate[] = new Date[10];	
		Date rejectedDate=null; //new Date();
		Date vendorIdentificationDate=null;//= new Date();
		Date bidCompNegoDate=null;//= new Date();
		Date vendorRegDate=null;//= new Date();
		Date clearanceDate=null;//= new Date();
		Date wtngImanyReqDate=null;//= new Date();
		Date termsheetRevDate=null;//= new Date();
		Date approveDate=null;//= new Date();
		Date firstDraftDate=null;//= new Date();
		Date reqReviewDate=null;//= new Date();
		Date procReviewDate=null;//= new Date();
		Date vendorReviewDate=null;//= new Date();
		Date vendorAcceptDate=null;//= new Date();
		Date CCFDate=null;//= new Date();
		Date execContractDate=null;//= new Date();
		Date bulSignDate=null;//= new Date();
		
		issueResolvedDate.set(1900, 01, 01, 0, 0);
		issueOnHoldDate.set(1900, 01, 01, 0, 0);		
		issueRejectedDate.set(1900, 01, 01, 0, 0);		
			
		Calendar dateNow = Calendar.getInstance();
	    //assignedDate[0]= dateNow.getTime();	   
	    resolvedDate= dateNow.getTime(); 
		
		String isRejectedDate = null;
		String isAssignedDate = null;
		String isResolvedDate = null;
		String issueKey = null;
		String issueOpenDateandTime = null;
		String changeStatus = null;
		String issueStatusChangeTime = null;
		String serviceName = "";
		/*String rejectReason="";*/
		Iterator<IssuesBean> iter= issuesList.iterator();
		IssuesBean issue = null;
		while(iter.hasNext()){
		  issue =iter.next();		
		  changeStatus = issue.getChangeStatus();
		  issueStatusChangeTime = issue.getIssueStatusChangeTime();		
		  if(issueStatusChangeTime!=null)
		  {
		  issueStatusChangeTime= issueStatusChangeTime.substring(0,issueStatusChangeTime.length()-5);
		  issueStatusChangeTime=issueStatusChangeTime.concat(":00");
		  }
		 if(changeStatus != null || issueStatusChangeTime != null){
			 if(issueStatusChangeTime!="")
			 changeStatusDate = sdf.parse(issueStatusChangeTime); 
		 
		  if(changeStatus.equals(ASSIGNED))
			  assignedDate=changeStatusDate;
		  else if(changeStatus.equals(VENDOR_IDENTIFICATION))
			  vendorIdentificationDate=changeStatusDate;
		  else if(changeStatus.equals(BIDDING_COMPLIANCE_AND_NEGOTIATIONS))
			  bidCompNegoDate=changeStatusDate;
		  else if(changeStatus.equals(VENDOR_REGISTRATION_SCM))
			  vendorRegDate=changeStatusDate;
		  else if(changeStatus.equals(CLEARANCE))
			  clearanceDate=changeStatusDate;
		  else if(changeStatus.equals(WAITING_FOR_IMANY_REQUEST_REQUESTOR))
			  wtngImanyReqDate=changeStatusDate;
		  else if(changeStatus.equals(TERM_SHEET_REVIEW))
			  termsheetRevDate=changeStatusDate;
		  else if(changeStatus.equals(APPROVED))
			  approveDate=changeStatusDate;
		  else if(changeStatus.equals(FIRST_DRAFT_LEGAL))
			  firstDraftDate=changeStatusDate;
		  else if(changeStatus.equals(REQUESTOR_REVIEW))
			  reqReviewDate=changeStatusDate;
		  else if(changeStatus.equals(PROCUREMENT_REVIEW))
			  procReviewDate=changeStatusDate;
		  else if(changeStatus.equals(VENDOR_REVIEW))
			  vendorReviewDate=changeStatusDate;
		  else if(changeStatus.equals(VENDOR_ACCEPATANCE_LEGAL))
			  vendorAcceptDate=changeStatusDate;
		  else if(changeStatus.equals(CCF_LEGAL))
			  CCFDate=changeStatusDate;
		  else if(changeStatus.equals(EXECUTION_OF_CONTRACT))
			  execContractDate=changeStatusDate;
		  else if(changeStatus.equals(EXECUTED_BY_BUL))
			  bulSignDate=changeStatusDate;
		  else if(changeStatus.equals(REJECTED))
			  rejectedDate = changeStatusDate;
		 }
		}
		if(issue != null){
			issueOpenDateandTime = issue.getIssueOpenDateandTime();
			issueOpenDateandTime= issueOpenDateandTime.substring(0,issueOpenDateandTime.length()-5);
			issueOpenDateandTime=issueOpenDateandTime.concat(":00");			
			issueOpenDate.setTime(sdf.parse(issueOpenDateandTime));				
			issueKey = issue.getIssueNo();
			
			serviceName = issue.getServiceName();
			
			/*if(issue.getReasonForReject() != null){
				rejectReason=issue.getReasonForReject();
			}
			if(issue.getReasonForReopen() != null){
				reOpenReason = issue.getReasonForReopen();
			}		*/
		}
		long responseTimeInMinutes = 0;		
		long vendorIdentificationMin=0;
		long bidCompNegoMin=0;
		long vendorRegMin=0;
		long clearanceMin=0;
		long wtngImanyReqMin=0;
		long termsheetRevMin=0;
		long approveMin=0;
		long firstDraftMin=0;
		long reqReviewMin=0;
		long procReviewMin=0;
		//long vendorReviewMin=0;
		long vendorAcceptMin=0;
		long CCFMin=0;
		long execContractMin=0;
		long bulSignMin=0;
		/*long onHoldTimeinMinutes = 0;
		long resolvedTimeInMinutes = 0;*/		
		
				
		String vendorIdentificationSLA="";
		String bidCompNegoSLA="";
		String vendorRegSLA="";
		String clearanceSLA="";
		String wtngImanyReqSLA="";
		String termsheetRevSLA="";
		String approveSLA="";
		String firstDraftSLA="";
		String reqReviewSLA="";
		String procReviewSLA="";
		//String vendorReviewSLA="";
		String vendorAcceptSLA="";
		String CCFSLA="";
		String execContractSLA="";
		String bulSignSLA="";
		if(null==rejectedDate)
		{
			if(null!=vendorIdentificationDate)
			{
				vendorIdentificationMin=calTime(assignedDate,vendorIdentificationDate);
				vendorIdentificationSLA=resolvedSLA(vendorIdentificationMin,"vendoridentification");
			}
			if(null!=bidCompNegoDate)
			{
				if(null!=vendorIdentificationDate)
					bidCompNegoMin=calTime(vendorIdentificationDate,bidCompNegoDate);
				else
					bidCompNegoMin=calTime(assignedDate,bidCompNegoDate);
				bidCompNegoSLA=resolvedSLA(bidCompNegoMin,"biddingandnegotiations");
			}
			if(null!=vendorRegDate)
			{
				if(null!=bidCompNegoDate)
					vendorRegMin=calTime(bidCompNegoDate,vendorRegDate);
				else
					vendorRegMin=calTime(assignedDate,vendorRegDate);
				vendorRegSLA=resolvedSLA(vendorRegMin,"vendorregistration");
			}
			if(null!=clearanceDate)
			{
				if(null!=vendorRegDate)
					clearanceMin=calTime(vendorRegDate,clearanceDate);
				else
					clearanceMin=calTime(assignedDate,clearanceDate);
				clearanceSLA=resolvedSLA(clearanceMin,"clearance");
			}
			if(null!=wtngImanyReqDate)
			{
				if(null!=termsheetRevDate)
					wtngImanyReqMin=calTime(wtngImanyReqDate,termsheetRevDate);
				wtngImanyReqSLA=resolvedSLA(wtngImanyReqMin,"imanyrequest");
			}
			if(null!=approveDate)
			{
				approveMin=calTime(termsheetRevDate,approveDate);
				approveSLA=resolvedSLA(approveMin,"termsheetreview");
			}
			if(null!=firstDraftDate)
			{
				firstDraftMin=calTime(approveDate,firstDraftDate);
				firstDraftSLA=resolvedSLA(firstDraftMin,"firstdraft"+serviceName.replace(" ",""));
			}
			if(null!=reqReviewDate)
			{
				reqReviewMin=calTime(firstDraftDate,reqReviewDate);
				reqReviewSLA=resolvedSLA(reqReviewMin,"requestorreview");
			}
			if(null!=procReviewDate)
			{
				procReviewMin=calTime(reqReviewDate,procReviewDate);
				procReviewSLA=resolvedSLA(procReviewMin,"procurementreview");
			}
			if(null!=vendorAcceptDate)
			{
				if(null!=procReviewDate)
					vendorAcceptMin=calTime(procReviewDate,vendorAcceptDate);
				else if(null!=reqReviewDate)
					vendorAcceptMin=calTime(reqReviewDate,vendorAcceptDate);
				
				//vendorAcceptSLA=resolvedSLA(vendorAcceptMin,"vendoracceptance");
			}
			if(null!=CCFDate)
			{
				CCFMin=calTime(vendorAcceptDate,CCFDate);
				CCFSLA=resolvedSLA(CCFMin,"ccf");
			}
			if(null!=execContractDate)
			{
				if(null!=CCFDate)
					execContractMin=calTime(CCFDate,execContractDate);
				else
					execContractMin=calTime(vendorAcceptDate,execContractDate);
				execContractSLA=resolvedSLA(execContractMin,"executioncontract");
			}
			if(null!=bulSignDate)
			{
				bulSignMin=calTime(execContractDate,bulSignDate);
				bulSignSLA=resolvedSLA(bulSignMin,"signedbul");
			}
			setResultsToBean(issueKey, serviceName,vendorIdentificationMin,bidCompNegoMin,vendorRegMin,clearanceMin,wtngImanyReqMin,termsheetRevMin,approveMin,firstDraftMin,reqReviewMin,procReviewMin,vendorAcceptMin,CCFMin,execContractMin,bulSignMin,vendorIdentificationSLA,bidCompNegoSLA,vendorRegSLA,clearanceSLA,wtngImanyReqSLA,termsheetRevSLA,approveSLA,firstDraftSLA,reqReviewSLA,procReviewSLA,vendorAcceptSLA,CCFSLA,execContractSLA,bulSignSLA);
		}
		else
		{
			setResultsToBean(issueKey, serviceName,vendorIdentificationMin,bidCompNegoMin,vendorRegMin,clearanceMin,wtngImanyReqMin,termsheetRevMin,approveMin,firstDraftMin,reqReviewMin,procReviewMin,vendorAcceptMin,CCFMin,execContractMin,bulSignMin,vendorIdentificationSLA,bidCompNegoSLA,vendorRegSLA,clearanceSLA,wtngImanyReqSLA,termsheetRevSLA,approveSLA,firstDraftSLA,reqReviewSLA,procReviewSLA,vendorAcceptSLA,CCFSLA,execContractSLA,bulSignSLA);
		}	
	}	

	
	@SuppressWarnings("unchecked")
	private String resolvedSLA(long resolvedTime, String propPriority) {				
		String wLocation= wRInputs.getProject();
		String wTicketType = wRInputs.getTicketType();
		wLocation = wLocation.replace(" ", "");
		wTicketType = wTicketType.replace(" ", "");
		
		//double wExpectedResultion= (resolvedTime / 60);		
		for(Iterator wPri = wPriorityList.iterator(); wPri.hasNext();){
			String wKey = (String) wPri.next();			
			float wValue = Float.parseFloat((String) properties.get(wLocation+"_"+wTicketType+"_"+wKey));
			if(wKey.equals(propPriority)){
				wValue = wValue * 60;
				if(resolvedTime <= wValue){
					return SLA_MET;
				} 
			}
		}		
		return SLA_NOT_MET;	
	}
	
	private long calTime(Date startDate,Date endDate) {
		Calendar date1=Calendar.getInstance();
		Calendar date2=Calendar.getInstance();
		date1.setTime(startDate);
		date2.setTime(endDate);
		
		long assignToResolveinMinutes = 0;
		
			
		setSLAStartHour(date1);
		setSLAEndHour(date1);				
		date1 = checkSLATIME(date1);			
		date1 = checkHolidays(date1);
		
			setSLAStartHour(date2);
			setSLAEndHour(date2);				
			date2 = checkSLATIME(date2);			
			date2 = checkHolidays(date2);
			
			assignToResolveinMinutes = calucalteTimeInMinutes(date1, date2); 
		return assignToResolveinMinutes;
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
    private void setResultsToBean(String issueKey,String serviceName,long vendorIdentificationMin,long bidCompNegoMin,long vendorRegMin,long clearanceMin,long wtngImanyReqMin,long termsheetRevMin,long approveMin,long firstDraftMin,long reqReviewMin,long procReviewMin,long vendorAcceptMin,long CCFMin,long execContractMin,long bulSignMin,String vendorIdentificationSLA,String bidCompNegoSLA,String vendorRegSLA,String clearanceSLA,String wtngImanyReqSLA,String termsheetRevSLA,String approveSLA,String firstDraftSLA,String reqReviewSLA,String procReviewSLA,String vendorAcceptSLA,String CCFSLA,String execContractSLA,String bulSignSLA)
/*	private void setResultsToBean(String issueName, String priorityName,
			String serviceNames, String businessUnit, String location, String assignee,String reOpenReason,String rejectReason, String issueStatus, String openDate, String assignDate,
			String resolveDate, long responseInMinutes, long resolveInMinutes, long onHoldInMinutes,
			String responseSLAMsg, String resolutionSLAMsg)*/ {
		
		ResultBean resultBean= new ResultBean();
		resultBean.setIssueKey(issueKey);
		resultBean.setIssueServiceName(serviceName);
		resultBean.setVendorIdentificationMin(vendorIdentificationMin);
		resultBean.setBidCompNegoMin(bidCompNegoMin);
		resultBean.setVendorRegMin(vendorRegMin);
		resultBean.setClearanceMin(clearanceMin);
		resultBean.setWtngImanyReqMin(wtngImanyReqMin);
		resultBean.setTermsheetRevMin(termsheetRevMin);
		resultBean.setApproveMin(approveMin);
		resultBean.setFirstDraftMin(firstDraftMin);
		resultBean.setReqReviewMin(reqReviewMin);
		resultBean.setProcReviewMin(procReviewMin);
		resultBean.setVendorAcceptMin(vendorAcceptMin);
		resultBean.setCCFMin(CCFMin);
		resultBean.setExecContractMin(execContractMin);
		resultBean.setBulSignMin(bulSignMin);
		
		resultBean.setVendorIdentificationSLA(vendorIdentificationSLA);
		resultBean.setBidCompNegoSLA(bidCompNegoSLA);
		resultBean.setVendorRegSLA(vendorRegSLA);
		resultBean.setClearanceSLA(clearanceSLA);
		resultBean.setWtngImanyReqSLA(wtngImanyReqSLA);
		resultBean.setTermsheetRevSLA(termsheetRevSLA);
		resultBean.setApproveSLA(approveSLA);
		resultBean.setFirstDraftSLA(firstDraftSLA);
		resultBean.setReqReviewSLA(reqReviewSLA);
		resultBean.setProcReviewSLA(procReviewSLA);
		resultBean.setVendorAcceptSLA(vendorAcceptSLA);
		resultBean.setCCFSLA(CCFSLA);
		resultBean.setExecContractSLA(execContractSLA);
		resultBean.setBulSignSLA(bulSignSLA);
				
				
		
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
