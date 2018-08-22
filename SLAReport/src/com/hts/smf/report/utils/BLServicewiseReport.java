package com.hts.smf.report.utils;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.hts.smf.report.beans.BLServicewiseReportBean;
import com.hts.smf.report.beans.ResultBean;


public class BLServicewiseReport implements ReportConstants {
	List<String> wPriorityList = new ArrayList<String>();
	public ArrayList<BLServicewiseReportBean> ServiceWiseSLAComplainnce(String projectName, List<String> wServices,ArrayList<ResultBean> resultLists) {
	   	ArrayList<BLServicewiseReportBean> serviceReprot = new ArrayList<BLServicewiseReportBean>();
    	List<String> wTicketTypes= getTicketTypes();
    	
    	for(String servicename : wServices){    		
    		for(String ticketTypes : wTicketTypes){    			
    			int servicewiseCount = 0;
        		int servicewiseResolvedCount = 0;
        		int rejectedTicktesCount = 0;
        		int openTicktesCount = 0;    		
        		long slaMetCount=0;
        		long slaNotMetCount =0;
        		long totalResolutionTime = 0;         		
        		double effortSpent =0;
        		double effortinJira =0;        		
    			BLServicewiseReportBean reportBean = new BLServicewiseReportBean();
	    		for(ResultBean resultBean : resultLists) {
	    			String wIssueServiceName = resultBean.getIssueServiceName();
	    			String wIssueTicketType = resultBean.getTicketType();
	    			String wIssueChangeStatus = resultBean.getIssueStatus();
	    			String wIssueStatus = resultBean.getResolutionSLA();
	    			double wEffortSpent = resultBean.getSAPEffort();
	    			double wEffortinJira = resultBean.getEstimatedEffort();
	    			long resolvedTime = resultBean.getTat();
	    			
					if(servicename.equals(wIssueServiceName)&& ticketTypes.equals(wIssueTicketType)){									
						if(wIssueChangeStatus.equals(WORKINPROGRESS) || wIssueChangeStatus.equals(ASSIGNED)){
							//do nothing
						} else {
						servicewiseCount = servicewiseCount + 1;
						if(wIssueChangeStatus.equals(RESOLVED) || wIssueChangeStatus.equals(CLOSED) ){
							servicewiseResolvedCount = servicewiseResolvedCount + 1;
							effortSpent = effortSpent + wEffortSpent;
							effortinJira = effortinJira + wEffortinJira;
						} else if(wIssueStatus.equals(REJECTED)){
							rejectedTicktesCount = rejectedTicktesCount + 1;
						} else{
							openTicktesCount = openTicktesCount + 1;
						}						
						if(wIssueStatus.equals(ReportConstants.SLA_MET)){
							slaMetCount = slaMetCount + 1;
							totalResolutionTime += resolvedTime;
						}else if(wIssueStatus.equals(ReportConstants.SLA_NOT_MET)){
							slaNotMetCount = slaNotMetCount + 1;
							totalResolutionTime += resolvedTime;
						}
						}
					}					
				}	    		
	    		
    		double avgEffort = 0;
    		double avgProductivity=0;
    		double avgTAT = 0;
    		
	    		if(totalResolutionTime > 0){
	    			//avgTAT = ((totalResolutionTime/servicewiseCount)/60/9);
	    			avgTAT = ((totalResolutionTime/60)/servicewiseCount);
	    			avgTAT = placeDecimals(avgTAT);	    			
	    			avgEffort =  effortSpent/servicewiseCount;	    			
	    			avgEffort = placeDecimals(avgEffort);	    			
	    			//avgProductivity = ((effortSpent/effortinJira)*100);
	    			avgProductivity =(((effortinJira-effortSpent)/effortinJira)*100);
	    			avgProductivity = placeDecimals(avgProductivity);	
	    		}
	    		if(servicewiseResolvedCount > 0){
		    		reportBean.setServices(servicename);
		    		reportBean.setTicketTypes(ticketTypes);	
		    		reportBean.setServiceTotal(servicewiseCount);
		    		reportBean.setResolved(servicewiseResolvedCount);
		    		reportBean.setAvgTAT(avgTAT);
		    		reportBean.setAvgEffort(avgEffort);
		    		reportBean.setAvgProductivity(avgProductivity);	    		
		    		reportBean.setNoOfCrossedSLA(slaNotMetCount); 
		    		serviceReprot.add(reportBean);
	    		}
    		}
    	}		
    	return serviceReprot;
    }
	
	public ArrayList<BLServicewiseReportBean> ServiceWiseEarningReportSLAComplainnce(List<String> wServices, Properties properties, ArrayList<ResultBean> resultLists, ArrayList<ResultBean> ytdList) {
	   	ArrayList<BLServicewiseReportBean> earningServiceReprots = new ArrayList<BLServicewiseReportBean>();	   	
	   	wPriorityList = getPriorityList(properties);
	   	for(String servicename : wServices){
    		BLServicewiseReportBean reportBean = new BLServicewiseReportBean();  
    		String str[] = new String[1000];
    		int count = 0;
    		long wMileStoneValue = 0;
    		long wDateRangeMValue = 0;
    		for(ResultBean resultBean : ytdList) {
    			String wIssueServiceName = resultBean.getIssueServiceName();
    			if(servicename.equals(wIssueServiceName)){
    				String wIssueChangeStatus = resultBean.getIssueStatus();
					if(wIssueChangeStatus.equals(RESOLVED) || wIssueChangeStatus.equals(CLOSED)){
						str[count] = resultBean.getActualCompletionDate();	
						String mValue = resultBean.getMilestoneValue();
						long wMvalue = 0;
						if(mValue != null){
							if((mValue.equalsIgnoreCase("X")) || (mValue.equalsIgnoreCase("S")) || (mValue.equalsIgnoreCase("."))){
								//do nothing
							} else{
								wMvalue = Integer.parseInt(mValue);
							}
						}
						wMileStoneValue = wMileStoneValue + wMvalue;
						count++;
						String wKey= resultBean.getIssueKey();
						for(ResultBean results : resultLists) {
							String wIssueNo= results.getIssueKey();
							if(wIssueNo.equals(wKey)){
								String wRangeMValue = results.getMilestoneValue();
								long wRangeMvalue = 0;								
								if(wRangeMValue != null){
									if((wRangeMValue.equalsIgnoreCase("X")) || (wRangeMValue.equalsIgnoreCase("S")) || (wRangeMValue.equalsIgnoreCase("."))){
										//do nothing
									} else{
										wRangeMvalue = Integer.parseInt(wRangeMValue);
									}
								}																							
								wDateRangeMValue = wDateRangeMValue +wRangeMvalue;
							}
						}
					}
    			}	    			    		
    		}
	    	if(!servicename.equals("Empty")){	
		    	long earning= CalucalateEarnings(str, properties);	
	    		reportBean.setServices(servicename);
	    		long budgetAmt= Integer.parseInt((String) properties.get(servicename));
	    		reportBean.setAllotedBudget(budgetAmt);
	    		reportBean.setTargetEarnings(earning);
	    		reportBean.setTotalBudgetUtilization(wMileStoneValue);
	    		reportBean.setBudgetUtilizationforDateRange(wDateRangeMValue);
	    		earningServiceReprots.add(reportBean);
	    	}
    	}		
    	return earningServiceReprots;
    }
	
	private long CalucalateEarnings(String[] str, Properties properties) {		
		Date wCompletionDate= new Date();
		Date wLastCompletionDate = new Date();
		Calendar calDate = new GregorianCalendar();
		calDate.set(1900, 0, 0, 0, 0, 0);
		wLastCompletionDate = calDate.getTime();
		for (int i = 0; i < str.length; i++) {
			try {
				if(str[i] == null){
					break;
				}
				wCompletionDate = sdf.parse(str[i]);
				if(i == 0){
					wLastCompletionDate  = wCompletionDate;			
				}else if(wCompletionDate.after(wLastCompletionDate)){
					wLastCompletionDate  = wCompletionDate;					
				}					
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		String year= ((String) properties.get("Year"));
		DateFormat sdfs = new SimpleDateFormat("yyyy-MMM-dd");
		Calendar cal2 = new GregorianCalendar();
		cal2.set(1900, 0, 0, 0, 0, 0);
		Date wActualDateRange = new Date();
		wActualDateRange = cal2.getTime();
		for(Iterator<String> wPri = wPriorityList.iterator(); wPri.hasNext();){
			String wKey = (String) wPri.next();
			String wDate = wKey.split("_")[1];
			String month = wDate.substring(0, 3);
			String day = wDate.substring(3, wDate.length());
			String date =year;			
			date= date.concat("-");
			date= date.concat(month);
			date= date.concat("-");
			date= date.concat(day);
			Date wTempDate = new Date();
			Date rangeDate = new Date();			
			try {
				wTempDate = sdfs.parse(date);
				if(wTempDate.before(wLastCompletionDate)){
					rangeDate = wTempDate;
					if(rangeDate.after(wActualDateRange)){						
						wActualDateRange = rangeDate;						
					}
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}		
		}
		long earningsCount =0;
		double earnings = Integer.parseInt((String) properties.get("PerDayEarning"));
		year= ((String) properties.get("Year"));
		for(Iterator<String> wPris = wPriorityList.iterator(); wPris.hasNext();){
			String wKey = (String) wPris.next();
			String wDate = wKey.split("_")[1];
			String month = wDate.substring(0, 3);
			String day = wDate.substring(3, wDate.length());
			String date =year;			
			date= date.concat("-");
			date= date.concat(month);
			date= date.concat("-");
			date= date.concat(day);
			Date wTempDate = new Date();			
			try {
				wTempDate = sdfs.parse(date);
				if(wTempDate.before(wActualDateRange)){
					int wEar = Integer.parseInt((String) properties.get("BillingCalendar_"+wKey));	
					earningsCount = (long) (earningsCount + (earnings * wEar));					
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}	
		}		
		return earningsCount;		
	}
	
	public List<String> getPriorityList(Properties wProperties) {		
		List<String> priorityList = new ArrayList<String>();
		SortedSet<Object> sortedSet = new TreeSet<Object>(wProperties.keySet());		
		String str="BillingCalendar";
		for(Iterator<Object> iter = sortedSet.iterator(); iter.hasNext();){
			String element= (String) iter.next();			
			if(element.startsWith(str)){
				int length = str.length() + 1; 
				priorityList.add(element.substring(length));				
			}
		}
		Collections.sort(priorityList);
		return priorityList;
	}

	public ArrayList<BLServicewiseReportBean> ServiceWiseRemainingHrs(List<String> wServices, ArrayList<ResultBean> resultLists) {
	   	ArrayList<BLServicewiseReportBean> remHrsserviceReprots = new ArrayList<BLServicewiseReportBean>();
	   	List<String> wTicketTypes= getTicketTypes();    	
    	for(String servicename : wServices){
    		for(String ticketTypes : wTicketTypes){
    			int servicewiseCount = 0;
    			long remaingHrs = 0;
    			BLServicewiseReportBean reportBean = new BLServicewiseReportBean();    			
	    		for(ResultBean resultBean : resultLists) {
	    			String wIssueServiceName = resultBean.getIssueServiceName();
	    			String wIssueTicketType = resultBean.getTicketType();	    			
	    			if(servicename.equals(wIssueServiceName)&& ticketTypes.equals(wIssueTicketType)){
	    				String wIssueChangeStatus = resultBean.getIssueStatus();
						if(wIssueChangeStatus.equals(WORKINPROGRESS) || wIssueChangeStatus.equals(ASSIGNED)){
							servicewiseCount = servicewiseCount + 1;
							remaingHrs += resultBean.getRemainingDays();
						}
	    			}
	    		}
	    		if(servicewiseCount > 0){
		    		//remaingHrs = remaingHrs * 9;
		    		reportBean.setServices(servicename);
		    		reportBean.setTicketTypes(ticketTypes);
		    		reportBean.setOpen(servicewiseCount);
		    		reportBean.setRemainingDays(remaingHrs);
		    		remHrsserviceReprots.add(reportBean);
	    		}
    		}
    	}		
    	return remHrsserviceReprots;
    }
	
	public ArrayList<BLServicewiseReportBean> ServiceWiseResolvedSLAComplainnce(ArrayList<ResultBean> resultLists) {
	   	ArrayList<BLServicewiseReportBean> resServiceReprots = new ArrayList<BLServicewiseReportBean>();    	
    		for(ResultBean resultBean : resultLists) {	    			
    			String wIssueChangeStatus = resultBean.getIssueStatus();
				if(wIssueChangeStatus.equals(RESOLVED) || wIssueChangeStatus.equals(CLOSED)){
					BLServicewiseReportBean reportBean = new BLServicewiseReportBean();
					reportBean.setServices(resultBean.getIssueServiceName());
		    		reportBean.setTicketTypes(resultBean.getTicketType());
		    		reportBean.setHldTicketNo(resultBean.getHldTicketNo());
		    		reportBean.setTitle(resultBean.getTitle());
		    		reportBean.setIssueStatus(wIssueChangeStatus);
		    		reportBean.setActualCompletionDate(resultBean.getActualCompletionDate());
		    		reportBean.setEstimatedEffort(resultBean.getEstimatedEffort());
		    		reportBean.setSapEffort(resultBean.getSAPEffort());
		    		reportBean.setRemainingDays(resultBean.getRemainingDays());
		    		reportBean.setCostPerformanceIndex(resultBean.getCostPerformanceIndex());
		    		reportBean.setTat(resultBean.getTat());	
		    		reportBean.setSla(resultBean.getResolutionSLA());
		    		resServiceReprots.add(reportBean);
		    	}
    	}		
    	return resServiceReprots;
    }
	
	public ArrayList<BLServicewiseReportBean> ServiceWiseWIPSLAComplainnce(ArrayList<ResultBean> resultLists) {
	   	ArrayList<BLServicewiseReportBean> serviceReprots = new ArrayList<BLServicewiseReportBean>();    	
    		for(ResultBean resultBean : resultLists) {	    			
    			String wIssueChangeStatus = resultBean.getIssueStatus();
				if(wIssueChangeStatus.equals(WORKINPROGRESS) || wIssueChangeStatus.equals(ASSIGNED)){
					BLServicewiseReportBean reportBean = new BLServicewiseReportBean();
					reportBean.setServices(resultBean.getIssueServiceName());
		    		reportBean.setTicketTypes(resultBean.getTicketType());
		    		reportBean.setHldTicketNo(resultBean.getHldTicketNo());
		    		reportBean.setTitle(resultBean.getTitle());
		    		reportBean.setIssueStatus(wIssueChangeStatus);
		    		reportBean.setEstimatedEffort(resultBean.getEstimatedEffort());
		    		reportBean.setSapEffort(resultBean.getSAPEffort());
		    		reportBean.setRemainingDays(resultBean.getRemainingDays());
		    		reportBean.setCostPerformanceIndex(resultBean.getCostPerformanceIndex());
		    		reportBean.setTat(resultBean.getTat());	
		    		reportBean.setSla(resultBean.getResolutionSLA());
		    		serviceReprots.add(reportBean);
    		}
    	}		
    	return serviceReprots;
    }
	
	

	/**
	 * @param double value
	 * @return
	 */
	private double placeDecimals(double value) {
		int decimalPlaces = 2;
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
		value = bd.doubleValue();
		return value;
	}

	private List<String> getTicketTypes() {
		Connection conn  = null;
		Statement statement = null;
		ResultSet resultSet = null;		
		List<String> tTypes= new ArrayList<String>();	
		/*try {
			MYSQLConnection mySqlConnection= new MYSQLConnection();					
			conn = mySqlConnection.getConnection();	
			statement = conn.createStatement();
			resultSet = statement.executeQuery(BusinessLegal_Ticketypes);
			while (resultSet.next()){
				tTypes.add(resultSet.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	*/		
		return tTypes;
	}  
}