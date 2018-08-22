package com.hts.smf.report.project;


import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.SortedSet;
import java.util.TreeSet;
import java.text.SimpleDateFormat;
import com.hts.smf.report.beans.IssuesBean;
import com.hts.smf.report.beans.ReportInputs;
import com.hts.smf.report.beans.ResultBean;
import com.hts.smf.report.utils.ReportConstants;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import java.io.*;

public class ISOPSSLA implements ReportConstants {
	 private static final long serialVersionUID = 1L;	
	 public static final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
	 
	 public List<ResultBean> start(Properties props, List<GregorianCalendar> holidaysList, List<String> priorityList, ReportInputs inputs) {	 
		
		 try {
			this.properties = props;
			this.wPriorityList = priorityList;
			this.wRInputs = inputs;
			this.holidays = holidaysList;
			ReadData();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wResult; 
	}
	
	 private void ReadData() {
		   try {		
			  List<IssuesBean> issuesList = new ArrayList<IssuesBean>();
			  File file = new File("/home/SLAReport/Resolved.xls");
			  //File file = new File("C:\\Users\\e499378\\Desktop\\Resolved.xls");//Resolved_19to25Feb Resolved_05to11Feb.xls
			 // File file = new File("C:\\Users\\e499378\\Desktop\\Resolved_Feb2012.xls");
	            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
	            HSSFWorkbook wb = new HSSFWorkbook(fs);
	            HSSFSheet sheet = wb.getSheetAt(0);
	            int rows = sheet.getPhysicalNumberOfRows();
	           	            
	            for(int i=1; i<rows; i++)
	            {            	
	            	HSSFRow row = sheet.getRow(i);
	            	
	            	
	            	//if(i==151)
	            	//	System.out.println("Breakpoint");
	            	System.out.println(i);
	            	if(row.getCell(18).toString().equalsIgnoreCase("cancelled"))
	            	{
	            		continue;
	            	}
	            	else
	            	{
	            	
	            	
	            	
	            		IssuesBean issue= new IssuesBean();
	            		
	            		if(row.getCell(1).toString() != null)
	            			issue.setIncidentType(row.getCell(1).toString());
	            		else
	            			issue.setIncidentType("Empty");
	            		if(row.getCell(2)!= null && row.getCell(2).toString() != null)
	            			issue.setNotes(row.getCell(2).toString());
	            		else
	            			issue.setNotes("Empty");
	            		if(row.getCell(4) != null && row.getCell(4).toString() != null)
	            			issue.setSummary(row.getCell(4).toString());
	            		else
	            			issue.setSummary("Empty");
	            		if(row.getCell(5) != null &&row.getCell(5).toString() != null)
	            			issue.setFirstName(row.getCell(5).toString());
	            		else
	            			issue.setFirstName("Empty");
	            		if(row.getCell(6) != null &&row.getCell(6).toString() != null)
	            			issue.setSubmitterName(row.getCell(6).toString());
	            		else
	            			issue.setSubmitterName("Empty");
	            		if(row.getCell(7) != null &&row.getCell(7).toString() != null)
	            			issue.setProductCategorizationTier1(row.getCell(7).toString());
	            		else
	            			issue.setProductCategorizationTier1("Empty");
	            		if(row.getCell(8) != null &&row.getCell(8).toString() != null)
	            			issue.setProductCategorizationTier2(row.getCell(8).toString());
	            		else
	            			issue.setProductCategorizationTier2("Empty");
	            		if(row.getCell(9) != null &&row.getCell(9).toString() != null)
	            			issue.setProductCategorizationTier3(row.getCell(9).toString());
	            		else
	            			issue.setProductCategorizationTier3("Empty");
	            		if(row.getCell(10) != null &&row.getCell(10).toString() != null)
	            			issue.setProductName(row.getCell(10).toString());
	            		else
	            			issue.setProductName("Empty");
	            		if(row.getCell(11) != null &&row.getCell(11).toString() != null)
	            			issue.setSite(row.getCell(11).toString());
	            		else
	            			issue.setSite("Empty");
	            		if(row.getCell(12) != null &&row.getCell(12).toString() != null)
	            			issue.setImpact(row.getCell(12).toString());
	            		else
	            			issue.setImpact("Empty");
	            		//if(row.getCell(13).toString() != null)
	            			//issue.setPriority(row.getCell(13).toString());
	            		//else
	            			//issue.setPriority("Empty");
	            		if(row.getCell(14) != null &&row.getCell(14).toString() != null)
	            			issue.setUrgency(row.getCell(14).toString());
	            		else
	            			issue.setUrgency("Empty");
	            		if(row.getCell(15) != null &&row.getCell(15).toString() != null)
	            			issue.setLastResolvedDate(row.getCell(15).toString());
	            		else
	            			issue.setLastResolvedDate("Empty");
	            		if(row.getCell(16) != null &&row.getCell(16).toString() != null)
	            			issue.setLastModifiedDate(row.getCell(16).toString());
	            		else
	            			issue.setLastModifiedDate("Empty");
	            		if(row.getCell(17) != null &&row.getCell(17).toString() != null)
	            			issue.setLastModifiedBy(row.getCell(17).toString());
	            		else
	            			issue.setLastModifiedBy("Empty");
	            		if(row.getCell(18) != null &&row.getCell(18).toString() != null)
	            			issue.setStatus(row.getCell(18).toString());
	            		else
	            			issue.setStatus("Empty");
	            		if(row.getCell(19) != null &&row.getCell(19).toString() != null)
	            			issue.setReportedSource(row.getCell(19).toString());
	            		else
	            			issue.setReportedSource("Empty");
	            		if(row.getCell(20) != null &&row.getCell(20).toString() != null)
	            			issue.setOwnerGroup(row.getCell(20).toString());
	            		else
	            			issue.setOwnerGroup("Empty");
	            		if(row.getCell(21) != null &&row.getCell(21).toString() != null)
	            			issue.setOwner(row.getCell(21).toString());
	            		else
	            			issue.setOwner("Empty");
	            		if(row.getCell(22) != null &&row.getCell(22).toString() != null)
	            			issue.setAssignedGroup(row.getCell(22).toString());
	            		else
	            			issue.setAssignedGroup("Empty");
	            		if(row.getCell(23) != null &&row.getCell(23).toString() != null)
	            			issue.setAssignee(row.getCell(23).toString());
	            		else
	            			issue.setAssignee("Empty");
	            		if(row.getCell(24) != null &&row.getCell(24).toString() != null)
	            			issue.setResolution(row.getCell(24).toString());
	            		else
	            			issue.setResolution("Empty");
	            		if(row.getCell(25) != null &&row.getCell(25).toString() != null)
	            			issue.setResolution(row.getCell(25).toString());
	            		else
	            			issue.setResolution("Empty");
	            		if(row.getCell(26) != null &&row.getCell(26).toString() != null)
	            			issue.setReopenedDate(row.getCell(26).toString());
	            		else
	            			issue.setReopenedDate("Empty");
	            		if(row.getCell(27) != null &&row.getCell(27).toString() != null)
	            			issue.setReportedDate(row.getCell(27).toString());
	            		else
	            			issue.setReportedDate("Empty");
	            		
	            		
	            		
	            		
	            		
	            		if(row.getCell(22).toString() != null)
	            		issue.setServiceName(row.getCell(22).toString());
	            		else
	            			issue.setServiceName("Empty");
	            		if(row.getCell(0).toString() != null)
	            	     	issue.setIssueNo(row.getCell(0).toString());
	            		else
	            			issue.setIssueNo("Empty");
	            		if(row.getCell(3).toString() != null)
	            			issue.setEid(row.getCell(3).toString());
	            		
	            		if(row.getCell(12).toString().equalsIgnoreCase("3-Moderate/Limited"))
	            		{
	            		if(row.getCell(14).toString().equalsIgnoreCase("4-Low"))
	            			issue.setPriority("S4");
	            		else if(row.getCell(14).toString().equalsIgnoreCase("2-High"))
	            				issue.setPriority("S1");
	            		else if(row.getCell(14).toString().equalsIgnoreCase("3-Medium"))
	            				issue.setPriority("S2");
	            		else if(row.getCell(14).toString().equalsIgnoreCase("1-Critical"))
	            				issue.setPriority("S0");
	            		}
	            		else if(row.getCell(12).toString().equalsIgnoreCase("4-Minor/Localized"))
	            		{
	            		if(row.getCell(14).toString().equalsIgnoreCase("4-Low"))
	            				issue.setPriority("OTTP");
	            		else if(row.getCell(14).toString().equalsIgnoreCase("2-High"))
	            				issue.setPriority("S1");
	            		else if(row.getCell(14).toString().equalsIgnoreCase("3-Medium"))
	            				issue.setPriority("S3");
	            		else if(row.getCell(14).toString().equalsIgnoreCase("1-Critical"))
	            				issue.setPriority("S0");
	            		}
	            		
	            		else if(row.getCell(12).toString().equalsIgnoreCase("2-Significant/Large"))
	            		{
	            		if(row.getCell(14).toString().equalsIgnoreCase("4-Low"))
	            				issue.setPriority("S4");
	            			
	            		else if(row.getCell(14).toString().equalsIgnoreCase("2-High"))
	            				issue.setPriority("S1");
	            		else if(row.getCell(14).toString().equalsIgnoreCase("3-Medium"))
	            				issue.setPriority("S2");
	            		else if(row.getCell(14).toString().equalsIgnoreCase("1-Critical"))
	            				issue.setPriority("S0");
	            		}
	            		else if(row.getCell(12).toString().equalsIgnoreCase("1-Extensive/Widespread"))
	            		{
	            		if(row.getCell(14).toString().equalsIgnoreCase("4-Low"))
	            			issue.setPriority("S4");
	            		else if(row.getCell(14).toString().equalsIgnoreCase("2-High"))
	            				issue.setPriority("S1");
	            		else if(row.getCell(14).toString().equalsIgnoreCase("3-Medium"))
	            				issue.setPriority("S2");
	            		else if(row.getCell(14).toString().equalsIgnoreCase("1-Critical"))
	            				issue.setPriority("S0");
	            		}
	            	
	            		            		
	            		
	            	issue.setStatus(row.getCell(18).toString());
	            	issue.setIssueOpenDateandTime(sdf.format(row.getCell(26).getDateCellValue()).toString());
	            	issue.setIssueStatusChangeTime(sdf.format(row.getCell(15).getDateCellValue()).toString());
	            	
	            	if(row.getCell(23) != null)
	            	 {
	            		
	            		if(row.getCell(23).toString() != null)
	            		{
	            			issue.setAssignee(row.getCell(23).toString());
	            			
	            		}
	            	 }
	            	
	            	else
	            	{
	            		issue.setAssignee("Empty");
	            	}
	            	issuesList.add(issue); 
	            	
	            }
	            }
	            
	            calucalateSLA(issuesList);
	              
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
	}	
	
	private void calucalateSLA(List<IssuesBean> issuesList) throws ParseException {			
		int wipIndex=0;
		int onHoldIndex=0;
		Calendar issueOpenDate = Calendar.getInstance();
		Calendar issueRejectedDate = new GregorianCalendar();	
		long resolvedTimeInMinutes = 0;
		Date changeStatusDate = new Date();		
		Date assignedDate[] = new Date[10];		
		Date wipDate[] =new Date[10];		
		Date resolvedDate = new Date();
		Date onHoldDate[] = new Date[10];	
				
		issueResolvedDate.set(1900, 01, 01, 0, 0);
		issueOnHoldDate.set(1900, 01, 01, 0, 0);		
		issueRejectedDate.set(1900, 01, 01, 0, 0);		
			
		Calendar dateNow = Calendar.getInstance();
	    assignedDate[0]= dateNow.getTime();	   
	    resolvedDate= dateNow.getTime(); 
		
		
		String isResolvedDate = null;
		String issueKey = null;
		String issueOpenDateandTime = null;
		String changeStatus = null;
		String issueStatusChangeTime = null;
		String priority = "";	
		String serviceName = "";
		
		String assignee="";
		String eid="";
		//Added by rohit 
		String incidentID = "";
		String incidentType= "";
		 String notes= "";
		 String corporateID="";
		 String summary="";
		 String firstName="";
		 String submitterName="";
		 String productCategorizationTier1="";
		 String productCategorizationTier2="";
		 String productCategorizationTier3="";
		 String productName="";
		 String site="";
		 String impact="";
		 String urgency="";
		 String lastResolvedDate="";
		 String lastModifiedDate="";
		 String lastModifiedBy="";
		 String status="";
		 String reportedSource="";
		 String ownerGroup="";
		 String owner="";
		 String assignedGroup="";
		 String resolution="";
		 String reopenedDate="";
		 String reportedDate="";
		
		
		
		Iterator<IssuesBean> iter= issuesList.iterator();
		IssuesBean issue = null;
		while(iter.hasNext()){
			issue =iter.next();		
		  changeStatus = issue.getStatus();
		  issueStatusChangeTime = issue.getIssueStatusChangeTime();		
		  if(issueStatusChangeTime!=null)
		  {
		  issueStatusChangeTime= issueStatusChangeTime.substring(0,issueStatusChangeTime.lastIndexOf(":"));
		  issueStatusChangeTime=issueStatusChangeTime.concat(":00");
		  }
		 if(changeStatus != null || issueStatusChangeTime != null){
			 if(issueStatusChangeTime!=""&&issueStatusChangeTime!=null)
			 changeStatusDate = sdf.parse(issueStatusChangeTime); 
		 
		  if(changeStatus.equals(RESOLVED)|| changeStatus.equalsIgnoreCase("Closed")){
	    		resolvedDate = changeStatusDate;
	    		isResolvedDate = issueStatusChangeTime;
	    	} 
		 }
		
		
			issueOpenDateandTime = issue.getIssueOpenDateandTime();
			if(issueOpenDateandTime != null){
			issueOpenDateandTime= issueOpenDateandTime.substring(0,issueOpenDateandTime.lastIndexOf(":"));
			issueOpenDateandTime=issueOpenDateandTime.concat(":00");
			}
			 if(changeStatus != null || issueOpenDateandTime != null)
			 {
				 if(issueOpenDateandTime!=""&&issueOpenDateandTime!=null)
					 issueOpenDate.setTime(sdf.parse(issueOpenDateandTime));	
			 }
			issueKey = issue.getIssueNo();
			priority = issue.getPriority();	
			serviceName = issue.getServiceName();
			assignee=issue.getAssignee();
			resolvedDate = sdf.parse(issue.getIssueStatusChangeTime());
			eid = issue.getEid();
			
			//Added by rohit
			 incidentID = issue.getIncidentID();
			 incidentType = issue.getIncidentType();
			  notes = issue.getNotes();
			  corporateID = issue.getCorporateID();
			  summary = issue.getSummary();
			  firstName = issue.getFirstName();
			  submitterName = issue.getSubmitterName();
			  productCategorizationTier1 = issue.getProductCategorizationTier1();
			  productCategorizationTier2 = issue.getProductCategorizationTier2();
			  productCategorizationTier3 = issue.getProductCategorizationTier3();
			  productName = issue.getProductName();
			  site = issue.getSite();
			  impact = issue.getImpact();
			  urgency = issue.getUrgency();
			  lastResolvedDate = issue.getLastResolvedDate();
			  lastModifiedDate = issue.getLastModifiedDate();
			  lastModifiedBy = issue.getLastModifiedBy();
			  status = issue.getStatus();
			  reportedSource = issue.getReportedSource();
			  ownerGroup = issue.getOwnerGroup();
			  owner = issue.getOwner();
			  assignedGroup = issue.getAssignedGroup();
			  resolution = issue.getResolution();
			  reopenedDate = issue.getReopenedDate();
			  reportedDate = issue.getReportedDate();
			
			
			
			
			issueAssignedDate.setTime(sdf.parse(issue.getIssueOpenDateandTime()));
    		setSLAStartHour(issueAssignedDate);
    		setSLAEndHour(issueAssignedDate);	
    		issueAssignedDate = checkSLATIME(issueAssignedDate);				
    		issueAssignedDate = checkHolidays(issueAssignedDate);
        	
		
		resolvedTimeInMinutes = calResolvedTime(issueAssignedDate, onHoldDate, wipDate, resolvedDate, wipIndex,onHoldIndex);								
		String resolutionSLAMsg = resolvedSLA(resolvedTimeInMinutes, priority);		
		//setResultsToBean(issueKey,priority, serviceName,assignee,changeStatus,issueOpenDateandTime,isResolvedDate,resolvedTimeInMinutes,resolutionSLAMsg,eid);
		setResultsToBean(issueKey,priority, serviceName,assignee,changeStatus,issueOpenDateandTime,isResolvedDate,
				 resolvedTimeInMinutes,resolutionSLAMsg,eid,
				  
				  incidentID,
		 incidentType,
		  notes,
		  corporateID,
		  summary,
		  firstName,
		  submitterName,
		  productCategorizationTier1,
		  productCategorizationTier2,
		  productCategorizationTier3,
		  productName,
		  site,
		  impact,
		  urgency,
		  lastResolvedDate,
		  lastModifiedDate,
		  lastModifiedBy,
		  status,
		  reportedSource,
		  ownerGroup,
		  owner,
		  assignedGroup,
		  resolution,
		  reopenedDate,
		  reportedDate);	
			
		}
				
	}	

	
	@SuppressWarnings("unchecked")
	private String resolvedSLA(long resolvedTime, String propPriority) {				
		
		
		
		int expValue = 0;	
		
		if(propPriority.equalsIgnoreCase("S0"))
			expValue = 120;
			//expValue = 60;
		else if(propPriority.equalsIgnoreCase("S1"))
			 //expValue = 180;
			//expValue = 120;
			expValue = 240;
		else if(propPriority.equalsIgnoreCase("S2"))
			//expValue = 360;
			expValue = 480;
		else if(propPriority.equalsIgnoreCase("S3"))
			//expValue = 720;
			expValue = 960;
		else if(propPriority.equalsIgnoreCase("S4"))
			expValue = 4320;
		else if(propPriority.equalsIgnoreCase("OTTP"))
			return "";
		
		
		if(resolvedTime <= expValue)
			return SLA_MET;
		else 
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

	
	private String getServiceNames(String serviceName)
	{
		
		String element[] = serviceName.split("-");
		return element[2];
	}
				 	
	
	private void setResultsToBean(String issueName, String priorityName,
			String serviceNames, String assignee, String issueStatus, String assignDate,
			String resolveDate,long resolveInMinutes, String resolutionSLAMsg,String eid,
			
			String incidentID,
			String incidentType,
			String notes,
			String corporateID,
			String summary,
			String  firstName,
			String submitterName,
			String  productCategorizationTier1,
			String productCategorizationTier2,
			String productCategorizationTier3,
			String  productName,
			String  site,
			String impact,
			String  urgency,
			String  lastResolvedDate,
			String  lastModifiedDate,
			String lastModifiedBy,
			String status,
			String reportedSource,
			String ownerGroup,
			String owner,
			String assignedGroup,
			String resolution,
			String reopenedDate,
			String  reportedDate
	) {
		
		ResultBean resultBean= new ResultBean();
		resultBean.setIssueAssignee(assignee);
		resultBean.setIssueKey(issueName);
		resultBean.setIssuePriority(priorityName);
		resultBean.setIssueServiceName(serviceNames);		
		resultBean.setIssueStatus(issueStatus);
		resultBean.setIssueAssignedDate(assignDate);
		resultBean.setIssueResolvedDate(resolveDate);
		resultBean.setResolutionTime(resolveInMinutes);
		resultBean.setResolutionSLA(resolutionSLAMsg);
		//resultBean.setIssueAssignee(assignee);
		resultBean.setEid(eid);
		
		//Added by rohit on 5/31/2012
		resultBean.setIncidentID(incidentID);
		resultBean.setIncidentType(incidentType);
		resultBean.setNotes(notes);
		resultBean.setCorporateID(corporateID);
		resultBean.setSummary(summary);
		resultBean.setFirstName(firstName);
		resultBean.setSubmitterName(submitterName);
		resultBean.setProductCategorizationTier1(productCategorizationTier1);
		resultBean.setProductCategorizationTier2(productCategorizationTier2);
		resultBean.setProductCategorizationTier3(productCategorizationTier3);
		resultBean.setProductName(productName);
		resultBean.setSite(site);
		resultBean.setImpact(impact);
		//resultBean.setPriority();
		resultBean.setUrgency(urgency);
		resultBean.setLastResolvedDate(lastResolvedDate);
		resultBean.setLastModifiedDate(lastModifiedDate);
		resultBean.setLastModifiedBy(lastModifiedBy);
		resultBean.setStatus(status);
		resultBean.setReportedSource(reportedSource);
		resultBean.setOwnerGroup(ownerGroup);
		resultBean.setOwner(owner);
		resultBean.setAssignedGroup(assignedGroup);	
		resultBean.setResolution(resolution);
		resultBean.setReopenedDate(reopenedDate);
		resultBean.setReportedDate(reportedDate);
		
		wResult.add(resultBean);		
	}
	
	public ArrayList<ResultBean> getResults(){
		return wResult;
	}
	
	private long calucalteDateDiff(Calendar Time1, Calendar Time2 ) {
		long wTimeOne = Time1.getTimeInMillis();
		long wTimeTwo = Time2.getTimeInMillis();		
		
		System.out.println(Time1.getTime());
		System.out.println(Time2.getTime());
		
		long diff = wTimeOne - wTimeTwo;
	    //long dSeconds = d / 1000;
	    long dMinutes = diff / (60 * 1000);
	    //long dHour = d / (60 * 60 * 1000);
	    //long dDays = d / (24 * 60 * 60 * 1000);
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
		 		 
		   System.out.println(endDate.getTime());
		   System.out.println(startDate.getTime());
		   System.out.println(date.getTime());
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
