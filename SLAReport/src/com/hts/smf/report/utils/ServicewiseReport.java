package com.hts.smf.report.utils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.hts.smf.report.beans.ResultBean;
import com.hts.smf.report.beans.ServicewiseReportBean;


public class ServicewiseReport implements ReportConstants {
		
	public ArrayList<ServicewiseReportBean> ServiceWiseSLAComplainnce(String projectName, List<String> wServices, List<String> wPriorities, ArrayList<ResultBean> resultLists) {
		int count =0;	
		//Added by rohit
		int hydLocCount = 0;
		int BngLocCount = 0;
		int MadLocCount = 0;
		int ServiceRequestCount = 0;
		int IncidentCount = 0;
		//end
    	ArrayList<ServicewiseReportBean> serviceReprot = new ArrayList<ServicewiseReportBean>();	
    	
    	for(String servicename : wServices){
    		int servicewiseCount = 0;
    		int servicewiseResolvedCount = 0;
    		int rejectedTicktesCount = 0;
    		int openTicktesCount = 0;    		
    		long slaMetCount=0;
    		long slaNotMetCount =0;
    		long responseSLAMetCount=0;
    		long responseSLANotMetCount =0;
    		long totalResolutionTime = 0;
    		long totalResponseTime=0;
    		//Added by rohit
    		hydLocCount = 0;
			BngLocCount = 0;
			MadLocCount = 0;
			ServiceRequestCount = 0;
			IncidentCount = 0;
			//end 
    		ServicewiseReportBean reportBean = new ServicewiseReportBean();
    		for(String priority : wPriorities){
    			count = 0;  
    			
    			
	    		for (ResultBean resultBean : resultLists) {
	    			String wIssueServiceName = resultBean.getIssueServiceName();
	    			String wIssuePriority = resultBean.getIssuePriority();
	    			
	    			String wIssueChangeStatus = resultBean.getIssueStatus();
	    			String wIssueStatus = resultBean.getResolutionSLA();
	    			String wResponseIssueStatus=resultBean.getResponseSLA();
	    			long resolvedTime = resultBean.getResolutionTime();
	    			long responseTime =resultBean.getResponseTime();
	    			//Added by rohit to provide location count
	    			String location = resultBean.getIssueLocation();
	    			if(wIssuePriority == null){
	    				if(wIssueServiceName!=null)
	    				if(wIssueServiceName.equalsIgnoreCase("HSE Issues"))
	    					wIssuePriority = "P3";
	    				else
	    				wIssuePriority = "Cat1";
	    			} else{
	    				wIssuePriority = wIssuePriority.replace(" ", "");
	    			}
					if(priority.equalsIgnoreCase(wIssuePriority)){
						if(wIssueServiceName == null){
							//System.out.println("IssueId:" + resultBean.getIssueKey());
							wIssueServiceName = "Empty";
						}
						if(servicename.equals(wIssueServiceName)){			
							count =  count + 1;
							if(wIssueChangeStatus.equals(RESOLVED) || wIssueChangeStatus.equals(CLOSED) ){
								servicewiseResolvedCount = servicewiseResolvedCount + 1;								
							}else if(wIssueStatus.equals(REJECTED)){
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
							if(wResponseIssueStatus!=null)
							{
								if(wResponseIssueStatus.equals(ReportConstants.SLA_MET)){
									responseSLAMetCount = responseSLAMetCount + 1;
									totalResponseTime += responseTime;
								}else if(wResponseIssueStatus.equals(ReportConstants.SLA_NOT_MET)){
									responseSLANotMetCount = responseSLANotMetCount + 1;
									totalResponseTime += responseTime;
								}
							}
							if(location != null)
							{
								if(location.contains("Bangalore"))BngLocCount = BngLocCount + 1;
							    if(location.contains("Madurai"))MadLocCount = MadLocCount + 1;
							    if(location.contains("Hyderabad"))hydLocCount = hydLocCount + 1;
							}
						} 
					}					
				} 
	    		servicewiseCount = servicewiseCount + count;
	    		reportBean.setServices(servicename);
	    		
	    		if(priority.equals("Cat1")){
	    			reportBean.setCat1(count);
	    			ServiceRequestCount += count;
	    		} else if(priority.equals("Cat2")){
	    			reportBean.setCat2(count);
	    			ServiceRequestCount += count;
	    		} else if(priority.equals("Cat3")){
	    			reportBean.setCat3(count);
	    			ServiceRequestCount += count;
	    		} else if(priority.equals("Cat4")){
	    			reportBean.setCat4(count);
	    			ServiceRequestCount += count;
	    		} else if(priority.equals("Cat5")){
	    			reportBean.setCat5(count);
	    			ServiceRequestCount += count;
	    		} else if(priority.equals("Cat6")){
	    			reportBean.setCat6(count);
	    			ServiceRequestCount += count;
	    		} else if(priority.equals("Cat7")){
	    			reportBean.setCat7(count);
	    			ServiceRequestCount += count;
	    		} else if(priority.equals("Cat8")){
	    			reportBean.setCat8(count);
	    			ServiceRequestCount += count;
	    		} else if(priority.equals("Cat9")){
	    			reportBean.setCat9(count);
	    			ServiceRequestCount += count;
	    		} else if(priority.equals("Cat10")){
	    			reportBean.setCat10(count);
	    			ServiceRequestCount += count;
	    		} else if(priority.equals("Cat11")){
	    			reportBean.setCat11(count);
	    			ServiceRequestCount += count;
	    		}  else if(priority.equals("Cat12")){
	    			reportBean.setCat12(count);
	    			ServiceRequestCount += count;
	    		} else if(priority.equals("NonStandard")){
	    			reportBean.setNonStandard(count);
	    		} else if(priority.equals("P1")){
	    			reportBean.setP1(count);
	    			IncidentCount += count;
	    		} else if(priority.equals("P2")){
	    			reportBean.setP2(count);
	    			IncidentCount += count;
	    		} else if(priority.equals("P3")){
	    			reportBean.setP3(count);
	    			IncidentCount += count;
	    		}
	    	
	    	}
    		long slaPercent = 0;
    		double avgResolution = 0;
    		long responseSLAPercent = 0;
    		double avgResponse = 0;
    		if(slaMetCount > 0){
    			slaPercent = ((slaMetCount * 100) / (slaMetCount + slaNotMetCount));
    			avgResolution = totalResolutionTime / (slaMetCount + slaNotMetCount);
    			avgResolution = (avgResolution/60);
    			int decimalPlaces = 2;
    			BigDecimal bd = new BigDecimal(avgResolution);
    			bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
    			avgResolution = bd.doubleValue();
    		}
    		if(responseSLAMetCount > 0){
    			responseSLAPercent = ((responseSLAMetCount * 100) / (responseSLAMetCount + responseSLANotMetCount));
    			avgResponse = totalResponseTime / (responseSLAMetCount + responseSLANotMetCount);
    			avgResponse = (avgResponse/60);
    			int decimalPlaces = 2;
    			BigDecimal bd = new BigDecimal(avgResponse);
    			bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
    			avgResponse = bd.doubleValue();
    		}
    		reportBean.setServiceTotal(servicewiseCount);
    		reportBean.setResolved(servicewiseResolvedCount);
    		reportBean.setRejected(rejectedTicktesCount);
    		reportBean.setOpen(openTicktesCount);
    		reportBean.setSlaComplaince(slaPercent);
    		reportBean.setAvgResolutionTime(avgResolution);
    		reportBean.setAvgResponseTime(avgResponse);
    		reportBean.setSlaReponseComplaince(responseSLAPercent);
    		//Added by rohit
    		reportBean.setServiceRequestTotal(ServiceRequestCount);
    		reportBean.setIncidentTotal(IncidentCount);
    		reportBean.setHyderabad(hydLocCount);
    		reportBean.setBangalore(BngLocCount);
    		reportBean.setMadurai(MadLocCount);
    		
    		//End here.
    		serviceReprot.add(reportBean);
    	}		
    	return serviceReprot;
    }  
	
	//Added by rohit
	public ArrayList<ServicewiseReportBean> ServiceWiseSLAComplaiance(String projectName, List<String> wServices, List<String> wPriorities, ArrayList<ResultBean> resultLists) {
		int count =0;		
    	
		ArrayList<ServicewiseReportBean> serviceReprot = new ArrayList<ServicewiseReportBean>();	
    	List<String> services = new ArrayList<String>();
    	/*
    	services.add("ACCOUNT ADMIN SUPP");
    	services.add("BLACKBERRY SUPP");
    	services.add("CONFIG MGMT SUPP");
    	services.add("DESKTOP SUPP");
    	services.add("ENGG APPS SUPP");
    	services.add("ENTERPRISE APPS SUPP");
    	services.add("HARDWARE SUPP");
    	services.add("INFO SECURITY");
    	services.add("MISC SERVICES");
    	services.add("NETWORK SUPP");
    	services.add("NEW HIRE SUPP");
    	services.add("NON WINDOWS SUPP");
    	services.add("OS SERVICES");
    	services.add("PRINTING SUPP");
    	services.add("PROJECT SHARE SUPP");
    	services.add("SERVICE DESK");
    	services.add("VIDEO CONF SUPP");
    	services.add("VM SUPP");
    	*/
    	
    	/*services.add("Infrastructure Event");
    	services.add("Infrastructure Restoration");
    	services.add("User Service Request");
    	services.add("User Service Restoration");
    	*/
    	services.add("AP HTS-GBL-ACCOUNT ADMIN SUPP");
    	services.add("AP HTS-GBL-BLACKBERRY SUPP");
    	services.add("AP HTS-GBL-CONFIG MGMT SUPP");
    	services.add("AP HTS-GBL-ENGG APPS SUPP");
    	services.add("AP HTS-GBL-ENTERPRISE APPS SUPP");
    	services.add("AP HTS-GBL-HARDWARE SUPP");
    	services.add("AP HTS-GBL-INFO SECURITY");
    	services.add("AP HTS-GBL-L2 DESKTOP SUPP");
    	services.add("AP HTS-GBL-MISC SERVICES");
    	services.add("AP HTS-GBL-NETWORK SUPP");
    	services.add("AP HTS-GBL-NEW HIRE SUPP");
    	services.add("AP HTS-GBL-NON WINDOWS SUPP");
    	services.add("AP HTS-GBL-OS SERVICES");
    	services.add("AP HTS-GBL-PRINTING SUPP");
    	services.add("AP HTS-GBL-PROJECT SHARE SUPP");
    	services.add("AP HTS-GBL-SERVICE DESK");
    	services.add("AP HTS-GBL-VIDEO CONF SUPP");
    	services.add("AP HTS-GBL-VM SUPP");
    	services.add("AP IE11-GBL-DESKTOP SUPP");
    	services.add("AP IE1A-GBL-DESKTOP SUPP");
    	services.add("AP IE1F-GBL-DESKTOP SUPP");
    	services.add("AP IE22-GBL-DESKTOP SUPP");

    	
    	List<String> Priorities = new ArrayList<String>();
    	Priorities.add("S0");
    	Priorities.add("S1");
    	Priorities.add("S2");
    	Priorities.add("S3");
    	Priorities.add("S4");
    	//Priorities.add("OTTP");
    	
    	long slaMetCountService = 0;
    	long slaNotMetCountService = 0;
    	long slaPercentService = 0;
    	
    	for(String servicename : services){
    		int servicewiseCount = 0;
    		int servicewiseResolvedCount = 0;
    		
    		int openTicktesCount = 0;    		
    		long slaMetCount=0;
    		long slaNotMetCount =0;
    		long totalResolutionTime = 0;
    		ServicewiseReportBean reportBean = new ServicewiseReportBean();
    		long totalPriorityResolutionTime;
    		long totalSLAMetCount;
    		long totalSLANotMetCount;
    		for(String priority : Priorities){
    			count = 0;   
    			totalPriorityResolutionTime = 0;
    			totalSLAMetCount = 0;
    			totalSLANotMetCount = 0;
    			
	    		for (ResultBean resultBean : resultLists) {
	    			String wIssueServiceName = resultBean.getIssueServiceName();
	    			String wIssuePriority = resultBean.getIssuePriority();
	    			String wIssueChangeStatus = resultBean.getIssueStatus();
	    			String wIssueStatus = resultBean.getResolutionSLA();
	    			long resolvedTime = resultBean.getResolutionTime();
	    			
	    			if(wIssuePriority == null){
	    				if(wIssueServiceName!=null)
	    				if(wIssueServiceName.equalsIgnoreCase("HSE Issues"))
	    					wIssuePriority = "P3";
	    				else
	    				wIssuePriority = "Cat1";
	    			} else{
	    				wIssuePriority = wIssuePriority.replace(" ", "");
	    			}
					if(priority.equals(wIssuePriority)){
						if(wIssueServiceName == null){
							//System.out.println("IssueId:" + resultBean.getIssueKey());
							wIssueServiceName = "Empty";
						}
						if(servicename.equals(wIssueServiceName)){			
							count =  count + 1;
							if(wIssueChangeStatus.equals(RESOLVED)|| wIssueChangeStatus.equals(CLOSED)){
								servicewiseResolvedCount = servicewiseResolvedCount + 1;								
							} else{
								openTicktesCount = openTicktesCount + 1;
							}
							
							if(wIssueStatus.equals(ReportConstants.SLA_MET)){
								slaMetCount = slaMetCount + 1;								
								totalResolutionTime += resolvedTime;
								totalPriorityResolutionTime += resolvedTime;
								totalSLAMetCount = totalSLAMetCount + 1;
							}else if(wIssueStatus.equals(ReportConstants.SLA_NOT_MET)){
								slaNotMetCount = slaNotMetCount + 1;
								totalResolutionTime += resolvedTime;
								totalPriorityResolutionTime += resolvedTime;
								totalSLANotMetCount = totalSLANotMetCount + 1;
							}
							
						} 
					}					
				} 
	    		servicewiseCount = servicewiseCount + count;
	    		reportBean.setServices(servicename);
	    		
	    		//Calculation for slaPercent for individual priorities
	    		long slaPercent = 0;
	    		double avgResolution = 0;
	    		
	    		
	    		
	    		
	    		//particular service->particular priority->all tickets
	    		/*
	    		if(slaMetCount > 0){
	    			slaPercent = ((slaMetCount * 100) / (slaMetCount + slaNotMetCount));
	    			avgResolution = totalResolutionTime / (slaMetCount + slaNotMetCount);
	    			avgResolution = (avgResolution / 60);
	    			
	    			int decimalPlaces = 2;
	    			BigDecimal bd = new BigDecimal(avgResolution);
	    			bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
	    			avgResolution = bd.doubleValue();
	    		}	
	    		System.out.println(avgResolution);
	    		/*
	    		System.out.println("totalSLAMetCount:" + totalSLAMetCount);
	    		System.out.println("totalSLANotMetCount" + totalSLANotMetCount);
	    		System.out.println("totalProrityWiseResolutionTime" + totalPriorityResolutionTime);
	    		
	    		*/
	    		System.out.println(totalPriorityResolutionTime);
	    		System.out.println("slaMetCount:" + totalSLAMetCount +"slaNotMetCount:"+ totalSLANotMetCount);
	    		if(totalSLAMetCount > 0){
	    			slaPercent = ((totalSLAMetCount * 100) / (totalSLAMetCount + totalSLANotMetCount));
	    			avgResolution = totalPriorityResolutionTime / (totalSLAMetCount + totalSLANotMetCount);
	    			//avgResolution = totalResolutionTime / (slaMetCount + slaNotMetCount);
	    			avgResolution = (avgResolution / 60);
	    			
	    			int decimalPlaces = 2;
	    			BigDecimal bd = new BigDecimal(avgResolution);
	    			bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
	    			avgResolution = bd.doubleValue();
	    		}
	    		
	    		if(priority.equalsIgnoreCase("S0")){
	    			reportBean.setS0(count);
	    			reportBean.setAvgResolutionTimeS0(avgResolution);
	    		}else if(priority.equalsIgnoreCase("S1")){
	    			reportBean.setS1(count);
	    			reportBean.setAvgResolutionTimeS1(avgResolution);
	    		}else if(priority.equalsIgnoreCase("S2")){
	    			reportBean.setS2(count);
	    			reportBean.setAvgResolutionTimeS2(avgResolution);
	    		}else if(priority.equalsIgnoreCase("S3")){
	    			reportBean.setS3(count);
	    			reportBean.setAvgResolutionTimeS3(avgResolution);
	    		}else if(priority.equalsIgnoreCase("S4")){
	    			reportBean.setS4(count);
	    			reportBean.setAvgResolutionTimeS4(avgResolution);
	    		}
	    		
	    		
	    	
	    	
	    	}
    		//particular service -> all priorities -> all tickets
    		if(servicename.equalsIgnoreCase("AP IE1F-GBL-DESKTOP SUPP"))
    			System.out.println("AP IE1F-GBL-DESKTOP SUPP");
    		long slaPercent = 0;
    		double avgResolution = 0;
    		if(slaMetCount > 0){
    			slaPercent = ((slaMetCount * 100) / (slaMetCount + slaNotMetCount));
    			avgResolution = totalResolutionTime / (slaMetCount + slaNotMetCount);
    			avgResolution = (avgResolution / 60);
    			int decimalPlaces = 2;
    			BigDecimal bd = new BigDecimal(avgResolution);
    			bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
    			avgResolution = bd.doubleValue();
    			
    			
    		}
    		reportBean.setServiceTotal(servicewiseCount);
    		reportBean.setResolved(servicewiseResolvedCount);
    		reportBean.setSlaComplaince(slaPercent);
    		reportBean.setAvgResolutionTime(avgResolution);
    		serviceReprot.add(reportBean);
    		
    		slaMetCountService += slaMetCount;
    		slaNotMetCountService += slaNotMetCount;
    		
    	}	
    	
		
		if(slaMetCountService > 0){
			slaPercentService = ((slaMetCountService * 100) / (slaMetCountService + slaNotMetCountService));		
		}
    	System.out.println("slamEt" + slaMetCountService);
    	System.out.println("slaNotmEt" + slaNotMetCountService);
		System.out.println(slaPercentService);
    	return serviceReprot;
    	
    }  
	public ArrayList<ServicewiseReportBean> BuWiseSLACompliance(String projectName, List<String> wBUDetails,ArrayList<ResultBean> resultLists)
	{
		ArrayList<ServicewiseReportBean> BUReport = new ArrayList<ServicewiseReportBean>();	
    	List<String> BUDetails = new ArrayList<String>();
    	
    	//Populate the monthly details within the below list
    	List<String> wMonths = new ArrayList<String>();
    	wMonths.add("Jan");
    	wMonths.add("Feb");
    	wMonths.add("Mar");
    	wMonths.add("Apr");
    	wMonths.add("May");
    	wMonths.add("Jun");
    	wMonths.add("Jul");
    	wMonths.add("Aug");
    	wMonths.add("Sep");
    	wMonths.add("Oct");
    	wMonths.add("Nov");
    	wMonths.add("Dec");
    	
    	//wBUDetails.add("GrandTotal");
    	//Configuring data
    	//BUDetails.add("ACS");BUDetails.add("AERO");BUDetails.add("Core Engg");BUDetails.add("ITSS");BUDetails.add("Support");BUDetails.add("Turbo");BUDetails.add("GrandTotal");
    	BUDetails.add("EACS");
    	BUDetails.add("EAER");
    	BUDetails.add("ITSS");
    	BUDetails.add("GrandTotal");
    	
    	int cumPassFail = 0;
    	int cumSchedule = 0;
    	//double grandTotal = 0;
    	long grandTotal = 0;
    	String grandTotalPerc = "";
    	int debuggerEAER = 0;
    	int debuggerTotal = 0;
    	//For each BU, iterate the monthly details
    	//for(ResultBean resultBean : resultLists)
		//System.out.println(resultBean.getDueMonth());
    		
		
    	for(String BU : BUDetails)
    	{
    		ServicewiseReportBean reportBean = new ServicewiseReportBean();
    		int totalSchedule = 0;
    		//double totalPercentage = 0;
    		long totalPercentage = 0;
    		String totalPer = "";
    		int totalPassFail = 0;
    		reportBean.setBusinessUnit(BU);
    		
    		for(String mon: wMonths)
    		{
    			//Needs to be wiped off for each iteration
    			int monthlySchedule = 0;
    			int passFail = 0;
    			//double monthlyPercentage = 0;
    			long monthlyPercentage = 0;
    			String comPer = "";
    			
    			//For All BU's RFP Values
    			int overallBUSchedule = 0;
    			int overallBUPassFail = 0;
    			//double overallBUPercentage = 0;
    			long overallBUPercentage = 0;
    			String overallBUPerc = "";
    			for(ResultBean resultBean : resultLists)
    			{
        			//System.out.println(resultBean.getRfpNum());
        			//if(resultBean.getRfpNum().equalsIgnoreCase("171425"))
        			//	System.out.println("HOLD ON!!");
    				//Incorporate the logic of RFP status
    				if(resultBean.getBusinessUnit().trim().equalsIgnoreCase(BU))
    				{
    					//For each month calculate the RFP value
    					
    					
		    			String month = resultBean.getDueMonth();
		    			
		    			/*if(resultBean.getBusinessUnit().trim().equalsIgnoreCase("EAER"))
		    			if(month.equalsIgnoreCase(mon))
		    				if(month.equalsIgnoreCase("Mar")){
		    					System.out.println("REQ ID "+resultBean.getRfpNum());
		    					debuggerEAER+=resultBean.getScheduleStatus();
		    					debuggerTotal+=resultBean.getPassFailStatus();
		    					}*/
		    			if(month.equalsIgnoreCase(mon))
		    			{
		    				//Iterate through the Captured ResultBean
		    				monthlySchedule += resultBean.getScheduleStatus();
		    				passFail += resultBean.getPassFailStatus();
		    				
		    			}
		    			totalSchedule += resultBean.getScheduleStatus();
		    			totalPassFail += resultBean.getPassFailStatus();
    				}
    				//String month = resultBean.getDueDate();
    				String month = resultBean.getDueMonth();
	    			if(month.equalsIgnoreCase(mon))
	    			{
	    				overallBUSchedule += resultBean.getScheduleStatus();
	    				overallBUPassFail += resultBean.getPassFailStatus();
	    				
	    				
	    			}
	    			
    			}//End of ResultBean
    			/*if(BU.equalsIgnoreCase("EAER"))
    			if(mon.equalsIgnoreCase("Mar"))
    			{System.out.println("Pas/Fail "+debuggerEAER);
    			System.out.println("Schedule "+debuggerTotal);
    				}*/
    			if(monthlySchedule != 0){
    			monthlyPercentage = Math.round((double)(passFail * 100)/monthlySchedule);
    			comPer = String.valueOf(monthlyPercentage)+ "%";
    			}
    			else
    				comPer = String.valueOf(0)+"%";
    			
    			if(overallBUSchedule != 0){
    				//System.out.println((double)(overallBUPassFail * 100)/overallBUSchedule);
    			overallBUPercentage = Math.round((double)(overallBUPassFail * 100)/overallBUSchedule);
    			overallBUPerc = String.valueOf(overallBUPercentage) + "%";
    			
    			}
    			else
    				overallBUPerc = String.valueOf(0)+"%";
    			
    			if(!(BU.equalsIgnoreCase("GrandTotal"))){
    					
    			if(mon.equalsIgnoreCase("Jan"))
    				reportBean.setJanPercentage(comPer);
    			else if(mon.equalsIgnoreCase("Feb"))
    				reportBean.setFebPercentage(comPer);
    			else if(mon.equalsIgnoreCase("Mar"))
    				reportBean.setMarPercentage(comPer);
    			else if(mon.equalsIgnoreCase("Apr"))
    				reportBean.setAprPercentage(comPer);
    			else if(mon.equalsIgnoreCase("May"))
    				reportBean.setMayPercentage(comPer);
    			else if(mon.equalsIgnoreCase("Jun"))
    				reportBean.setJunPercentage(comPer);
    			else if(mon.equalsIgnoreCase("Jul"))
    				reportBean.setJulPercentage(comPer);
    			else if(mon.equalsIgnoreCase("Aug"))
    				reportBean.setAugPercentage(comPer);
    			else if(mon.equalsIgnoreCase("Sep"))
    				reportBean.setSepPercentage(comPer);
    			else if(mon.equalsIgnoreCase("Oct"))
    				reportBean.setOctPercentage(comPer);
    			else if(mon.equalsIgnoreCase("Nov"))
    				reportBean.setNovPercentage(comPer);
    			else if(mon.equalsIgnoreCase("Dec"))
    				reportBean.setDecPercentage(comPer);
    			}
    			else
    			{
    				
    				if(mon.equalsIgnoreCase("Jan"))
        				reportBean.setJanPercentage(overallBUPerc);
        			else if(mon.equalsIgnoreCase("Feb"))
        				reportBean.setFebPercentage(overallBUPerc);
        			else if(mon.equalsIgnoreCase("Mar"))
        				reportBean.setMarPercentage(overallBUPerc);
        			else if(mon.equalsIgnoreCase("Apr"))
        				reportBean.setAprPercentage(overallBUPerc);
        			else if(mon.equalsIgnoreCase("May"))
        				reportBean.setMayPercentage(overallBUPerc);
        			else if(mon.equalsIgnoreCase("Jun"))
        				reportBean.setJunPercentage(overallBUPerc);
        			else if(mon.equalsIgnoreCase("Jul"))
        				reportBean.setJulPercentage(overallBUPerc);
        			else if(mon.equalsIgnoreCase("Aug"))
        				reportBean.setAugPercentage(overallBUPerc);
        			else if(mon.equalsIgnoreCase("Sep"))
        				reportBean.setSepPercentage(overallBUPerc);
        			else if(mon.equalsIgnoreCase("Oct"))
        				reportBean.setOctPercentage(overallBUPerc);
        			else if(mon.equalsIgnoreCase("Nov"))
        				reportBean.setNovPercentage(overallBUPerc);
        			else if(mon.equalsIgnoreCase("Dec"))
        				reportBean.setDecPercentage(overallBUPerc);
    			}
    		}//End of Month Iteration
    		
    		if(totalSchedule != 0){
    			
    		totalPercentage = Math.round((double)(totalPassFail * 100)/totalSchedule);
    		totalPer = String.valueOf(totalPercentage)+ "%";
    		}
    		else
    			totalPer = String.valueOf(0)+"%";
    		
    		if(BU.equalsIgnoreCase("GrandTotal"))
    		 { 
    			for(ResultBean resultBean:resultLists)
    			{
    				cumPassFail += resultBean.getPassFailStatus();
    				cumSchedule += resultBean.getScheduleStatus();
    			}
    			//System.out.println("PASS/FAIL:"+cumPassFail);
    			//System.out.println("SCHEDULE:"+cumSchedule);
    			if(cumSchedule != 0)
    			grandTotal = Math.round((double)(cumPassFail * 100)/cumSchedule);
    			grandTotalPerc = String.valueOf(grandTotal)+"%";
    			totalPer = grandTotalPerc;
    			//System.out.println("TotalPerc"+totalPer);
    		}
    		reportBean.setTotalYearPercentage(totalPer);
    		BUReport.add(reportBean);
    	}//End of BU Iteration
    	
    	return BUReport;
	}
}