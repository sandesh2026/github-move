package com.hts.smf.report.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

import com.hts.smf.report.beans.ReportInputs;
import com.hts.smf.report.beans.ResultBean;
import com.hts.smf.report.project.AdminFacilitiesSLA;
import com.hts.smf.report.project.BusninessLegalSLA;
import com.hts.smf.report.project.HSESLA;
import com.hts.smf.report.project.ISCMSLA;
import com.hts.smf.report.project.LogisticsSLA;
import com.hts.smf.report.project.PKMSLA;
import com.hts.smf.report.project.TransportSLA;
import com.hts.smf.report.project.TravelSLA;
import com.hts.smf.report.project.WedamSLA;
import com.hts.smf.report.project.ContractSLA;
import com.hts.smf.report.project.ISOPSSLA;
import com.hts.smf.report.project.OTDAutomation;


public class ResultAction implements ReportConstants {		
	private static final String Time_In_Min = " 23:59:59";
	private List<GregorianCalendar> holidaysList;
	private Properties properties = null;
	List<String> wPriorityList = new ArrayList<String>();
	List<String> listBU = new ArrayList<String>();
	public ArrayList<ResultBean> loadData(ReportInputs rInputs, InputStream inputStream) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		String projectName = rInputs.getProject();
		//Added by rohit
		if(projectName.equals("ISOPS"))
		{
			InputUtils utils1 = new InputUtils();
			properties = utils1.loadProps(inputStream);
			wPriorityList.add("S0");
			wPriorityList.add("S1");
			wPriorityList.add("S2");
			wPriorityList.add("S3");
			wPriorityList.add("S4");
			holidaysList = utils1.wHTSHolidayList(properties, rInputs);		
			projectName = projectName.replace(" ", "");
			 if(projectName.equals("ISOPS")){
				ISOPSSLA slaCalc = new ISOPSSLA();
				slaCalc.start(properties, holidaysList, wPriorityList, rInputs);
			return slaCalc.getResults();
		}
		}
		else if(projectName.equals("OTD"))
		{
			//Set the below to all the months
			wPriorityList.add("Jan");
			wPriorityList.add("Feb");
			wPriorityList.add("Mar");
			wPriorityList.add("Apr");
			wPriorityList.add("May");
			wPriorityList.add("Jun");
			wPriorityList.add("Jul");
			wPriorityList.add("Aug");
			wPriorityList.add("Sep");
			wPriorityList.add("Oct");
			wPriorityList.add("Nov");
			wPriorityList.add("Dec");
			OTDAutomation otd = new OTDAutomation();
			otd.readData();
			listBU = otd.getBUDetails();
			return otd.getResults();
		}
		else
		{
			String ticketType = rInputs.getTicketType();
			String wRequestQuery = selectQuery(rInputs);
			InputUtils utils = new InputUtils();
			properties = utils.loadProps(inputStream);
			wPriorityList = utils.getPriorityList(properties, projectName, ticketType);
			holidaysList = utils.wHTSHolidayList(properties, rInputs);				
		projectName = projectName.replace(" ", "");
		if(projectName.equals(BANGALORE_ADMIN) || projectName.equals(BANGALORE_FACILITIES) 
				|| projectName.equals(HYDERABAD_OPERATIONS) || projectName.equals(MADURAI_OPERATIONS)){
				   AdminFacilitiesSLA slaCalc = new AdminFacilitiesSLA();
				   slaCalc.start(wRequestQuery, properties, holidaysList, wPriorityList, rInputs);
			return slaCalc.getResults();
		} else if(projectName.equals(LOGISTICS)){
				   LogisticsSLA slaCalc = new LogisticsSLA();
				   slaCalc.start(wRequestQuery, properties, holidaysList, wPriorityList, rInputs);
			return slaCalc.getResults();
		} else if(projectName.equals(PKM)){
				   PKMSLA slaCalc= new PKMSLA();
				   slaCalc.start(wRequestQuery, properties, holidaysList, wPriorityList, rInputs);
			return slaCalc.getResults();
		} else if(projectName.equals(TRANSPORT)) {
				   TransportSLA slaCalc= new TransportSLA();
				   slaCalc.start(wRequestQuery, properties, holidaysList, wPriorityList, rInputs);
			return slaCalc.getResults();
		} else if(projectName.equals(HSE)){
					HSESLA slaCalc = new HSESLA();
					slaCalc.start(wRequestQuery, properties, holidaysList, wPriorityList, rInputs);
			return slaCalc.getResults();
		} else if(projectName.equals(ISCM)){
				ISCMSLA slaCalc = new ISCMSLA();
				slaCalc.start(wRequestQuery, properties, holidaysList, wPriorityList, rInputs);
			 return slaCalc.getResults();
		} else if(projectName.equals(TRAVEL)){
				TravelSLA slaCalc = new TravelSLA();
				slaCalc.start(wRequestQuery, properties, holidaysList, wPriorityList, rInputs);
			return slaCalc.getResults();
		} else if(projectName.equals(BUSINESS_LEGAL)){
			BusninessLegalSLA slaCalc = new BusninessLegalSLA();
			slaCalc.start(wRequestQuery, properties, holidaysList, wPriorityList, rInputs);			
		return slaCalc.getResults();
		}else if(projectName.equalsIgnoreCase("Operations")){ 
		//else if(projectName.equals(WEDAM)){--Rohit Modified it
			WedamSLA slaCalc = new WedamSLA();
			slaCalc.start(wRequestQuery, properties, holidaysList, wPriorityList, rInputs);			
		return slaCalc.getResults();
		} else if(projectName.equals(CONTRACT)){
			ContractSLA slaCalc = new ContractSLA();
			slaCalc.start(wRequestQuery, properties, holidaysList, wPriorityList, rInputs);			
		return slaCalc.getResults();
		}
		}
	return null;
	}
	
	public ArrayList<com.hts.smf.report.beans.ServicewiseReportBean> loadServicewiseData(ReportInputs inputs, ArrayList<ResultBean> wResults) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		
		String projectName = inputs.getProject();
		if(projectName.equalsIgnoreCase("ISOPS"))
		{
			List<String> wServices = new ArrayList<String>();
			wServices.add("User Service Restoration");
			wServices.add("Infrastructure Restoration");
			wServices.add("User Service Request");
			ServicewiseReport servicewiseReport = new ServicewiseReport();
			return servicewiseReport.ServiceWiseSLAComplaiance(projectName, wServices, wPriorityList, wResults);		
		}
		else if(projectName.equalsIgnoreCase("OTD"))
		{
			//List<String> wServices = new ArrayList<String>();
			ServicewiseReport servicewiseReport = new ServicewiseReport();
			return servicewiseReport.BuWiseSLACompliance(projectName, listBU, wResults);	
		}
		else
		{
			List<String> wServices = getServiceNames(inputs);
			ServicewiseReport servicewiseReport = new ServicewiseReport();			
			return servicewiseReport.ServiceWiseSLAComplainnce(projectName, wServices, wPriorityList, wResults);
		}
				
	}
	
	public ArrayList<com.hts.smf.report.beans.BLServicewiseReportBean> loadBLServicewiseData(ReportInputs inputs, ArrayList<ResultBean> wResults) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		List<String> wServices = getServiceNames(inputs);
		String projectName = inputs.getProject();
		BLServicewiseReport servicewiseReport = new BLServicewiseReport();
		
		return servicewiseReport.ServiceWiseSLAComplainnce(projectName, wServices, wResults);		
	}
	
	public ArrayList<com.hts.smf.report.beans.BLServicewiseReportBean> loadBLEarningReportServicewiseData(ReportInputs inputs, ArrayList<ResultBean> wResults, ArrayList<ResultBean> ytdList) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		List<String> wServices = getServiceNames(inputs);
		BLServicewiseReport earningServicewiseReport = new BLServicewiseReport();
		
		return earningServicewiseReport.ServiceWiseEarningReportSLAComplainnce(wServices, properties, wResults, ytdList);		
	}	
	
	public ArrayList<com.hts.smf.report.beans.BLServicewiseReportBean> loadResolvedBLServicewiseData(ArrayList<ResultBean> wResults) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		BLServicewiseReport resServicewiseReport = new BLServicewiseReport();		
		return resServicewiseReport.ServiceWiseResolvedSLAComplainnce(wResults);
		
	}	
	
	public ArrayList<com.hts.smf.report.beans.BLServicewiseReportBean> loadWIPBLServicewiseData(ArrayList<ResultBean> wResults) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		BLServicewiseReport servicewiseReports = new BLServicewiseReport();		
		return servicewiseReports.ServiceWiseWIPSLAComplainnce(wResults);		
	}	
	
	public ArrayList<com.hts.smf.report.beans.BLServicewiseReportBean> loadWIPRemHrsBLServicewiseData(ReportInputs inputs, ArrayList<ResultBean> wResults) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		List<String> wServices = getServiceNames(inputs);
		BLServicewiseReport servicewiseReport = new BLServicewiseReport();		
		return servicewiseReport.ServiceWiseRemainingHrs(wServices, wResults);		
	}	
	
	public String selectQuery(ReportInputs wInputs){
		String wQuery="";
		String wProjectName  = wInputs.getProject().replace(" ", "");
		String wTicketType = wInputs.getTicketType().replace(" ", "");
		/*if(wProjectName.equals(BANGALORE_ADMIN) && wTicketType.equals(SERVICE_REQUEST)){
			wQuery = BangaloreAdminQuery;
			wQuery = wQuery.replace("<fromDate>", wInputs.getFromDate());
			wQuery = wQuery.replace("<toDate>", wInputs.getToDate() +Time_In_Min);
		} else if(wProjectName.equals(BANGALORE_FACILITIES) && wTicketType.equals(SERVICE_REQUEST)){
			wQuery = BangaloreFacilitiesQuery;
			wQuery = wQuery.replace("<fromDate>", wInputs.getFromDate());
			wQuery = wQuery.replace("<toDate>", wInputs.getToDate()+Time_In_Min);
		} else if(wProjectName.equals(HYDERABAD_OPERATIONS) && wTicketType.equals(ADMIN)){
			wQuery = Hyderbad_Admin_ServiceRequest;
			wQuery = wQuery.replace("<fromDate>", wInputs.getFromDate());
			wQuery = wQuery.replace("<toDate>", wInputs.getToDate() +Time_In_Min);
		} else if(wProjectName.equals(HYDERABAD_OPERATIONS) && wTicketType.equals(FACILITIES)){
			wQuery = Hyderbad_Facilities_ServiceRequest;
			wQuery = wQuery.replace("<fromDate>", wInputs.getFromDate());
			wQuery = wQuery.replace("<toDate>", wInputs.getToDate()+Time_In_Min);
		} else if(wProjectName.equals(MADURAI_OPERATIONS) && wTicketType.equals(ADMIN)){
			wQuery = Madurai_Admin_ServiceRequest;
			wQuery = wQuery.replace("<fromDate>", wInputs.getFromDate());
			wQuery = wQuery.replace("<toDate>", wInputs.getToDate() +Time_In_Min);
		} else if(wProjectName.equals(MADURAI_OPERATIONS) && wTicketType.equals(FACILITIES)){
			wQuery = Madurai_Facilities_ServiceRequest;
			wQuery = wQuery.replace("<fromDate>", wInputs.getFromDate());
			wQuery = wQuery.replace("<toDate>", wInputs.getToDate()+Time_In_Min);
		} else */if(wProjectName.equals(LOGISTICS) && wTicketType.equals(TRANSFER_OF_MATERIALS)){
			wQuery = Logistics_Transfer_Of_Materials;
			wQuery = wQuery.replace("<fromDate>", wInputs.getFromDate());
			wQuery = wQuery.replace("<toDate>", wInputs.getToDate() +Time_In_Min);
		} else if(wProjectName.equals(LOGISTICS) && wTicketType.equals(EXPORT_OF_METERIAL)){
			wQuery = Logistics_Export_Of_Meterial;
			wQuery = wQuery.replace("<fromDate>", wInputs.getFromDate());
			wQuery = wQuery.replace("<toDate>", wInputs.getToDate() +Time_In_Min);
		} else if(wProjectName.equals(LOGISTICS) && wTicketType.equals(CUSTOMS_CLEARANCE_AND_DISTRIBUTION)){
			wQuery = Logistics_Customs_Clearance_And_Distribution;
			wQuery = wQuery.replace("<fromDate>", wInputs.getFromDate());
			wQuery = wQuery.replace("<toDate>", wInputs.getToDate() +Time_In_Min);
		} else if(wProjectName.equals(LOGISTICS) && wTicketType.equals(KITTING_OF_BILL_OF_MATERIAL)){
			wQuery = Logistics_Kitting_Of_Bill_Of_Material;
			wQuery = wQuery.replace("<fromDate>", wInputs.getFromDate());
			wQuery = wQuery.replace("<toDate>", wInputs.getToDate() +Time_In_Min);
		} else if(wProjectName.equals(LOGISTICS) && wTicketType.equals(EXPORT_OF_MATERIAL_IT_RELATED)){
			wQuery = Logistics_Export_Of_Material_IT_Related;
			wQuery = wQuery.replace("<fromDate>", wInputs.getFromDate());
			wQuery = wQuery.replace("<toDate>", wInputs.getToDate() +Time_In_Min);
		} else if(wProjectName.equals(LOGISTICS) && wTicketType.equals(TRANSFER_OF_MATERIALS_IT_RELATED)){
			wQuery = Logistics_Transfer_Of_Materials_IT_Related;
			wQuery = wQuery.replace("<fromDate>", wInputs.getFromDate());
			wQuery = wQuery.replace("<toDate>", wInputs.getToDate() +Time_In_Min);
		} else if(wProjectName.equals(TRANSPORT) && wTicketType.equals(LATE_NIGHT_BOOKING)){
			wQuery = Transport_Late_Night_Booking;
			wQuery = wQuery.replace("<fromDate>", wInputs.getFromDate());
			wQuery = wQuery.replace("<toDate>", wInputs.getToDate() +Time_In_Min);
		} /*else if(wProjectName.equals(TRANSPORT) && wTicketType.equals(ROUTE_CARD)){
			wQuery = Transport_RouteCard;
			wQuery = wQuery.replace("<fromDate>", wInputs.getFromDate());
			wQuery = wQuery.replace("<toDate>", wInputs.getToDate() +Time_In_Min);
		} else if(wProjectName.equals(TRANSPORT) && wTicketType.equals(CHANGE_ROUTE)){
			wQuery = Transport_Change_Route;
			wQuery = wQuery.replace("<fromDate>", wInputs.getFromDate());
			wQuery = wQuery.replace("<toDate>", wInputs.getToDate() +Time_In_Min);
		} else if(wProjectName.equals(TRANSPORT) && wTicketType.equals(LOCAL_OUTSTATION_TRANSPORT)){
			wQuery = Transport_Local_Outstation_Transport;
			wQuery = wQuery.replace("<fromDate>", wInputs.getFromDate());
			wQuery = wQuery.replace("<toDate>", wInputs.getToDate() +Time_In_Min);
		} else if(wProjectName.equals(TRANSPORT) && wTicketType.equals(QUERY)){
			wQuery = Transport_Query;
			wQuery = wQuery.replace("<fromDate>", wInputs.getFromDate());
			wQuery = wQuery.replace("<toDate>", wInputs.getToDate() +Time_In_Min);
		}*/ else if(wProjectName.equals(PKM) && wTicketType.equals(SERVICE_REQUEST)){
			wQuery =PKM_ServiceRequest;
			wQuery = wQuery.replace("<fromDate>", wInputs.getFromDate());
			wQuery = wQuery.replace("<toDate>", wInputs.getToDate() +Time_In_Min);
		} else if(wProjectName.equals(PKM) && wTicketType.equals(QUERY)){
			wQuery = PKM_Query;
			wQuery = wQuery.replace("<fromDate>", wInputs.getFromDate());
			wQuery = wQuery.replace("<toDate>", wInputs.getToDate() +Time_In_Min);
		} else if(wProjectName.equals(PKM) && wTicketType.equals(PROJECT_SPECIFIC_TRAINING_REQUEST_EXTERNAL_TRAINING)){
			wQuery = PKM_External_Training;
			wQuery = wQuery.replace("<fromDate>", wInputs.getFromDate());
			wQuery = wQuery.replace("<toDate>", wInputs.getToDate() +Time_In_Min);
		} else if(wProjectName.equals(PKM) && wTicketType.equals(PROJECT_SPECIFIC_TRAINING_REQUEST_INTERNAL_TRAINING)){
			wQuery = PKM_Internal_Training;
			wQuery = wQuery.replace("<fromDate>", wInputs.getFromDate());
			wQuery = wQuery.replace("<toDate>", wInputs.getToDate() +Time_In_Min);
		} /*else if(wProjectName.equals(HSE) && wTicketType.equals(SERVICE_REQUEST)){
			wQuery = HSE_ServiceRequest;
			wQuery = wQuery.replace("<fromDate>", wInputs.getFromDate());
			wQuery = wQuery.replace("<toDate>", wInputs.getToDate() +Time_In_Min);
		}  else if(wProjectName.equals(HSE) && wTicketType.equals(WOKK_PERMIT)){
			wQuery = HSE_WorkPermit;
			wQuery = wQuery.replace("<fromDate>", wInputs.getFromDate());
			wQuery = wQuery.replace("<toDate>", wInputs.getToDate() +Time_In_Min);
		}  else if(wProjectName.equals(HSE) && wTicketType.equals(HSE_ISSUES)){
			wQuery = HSE_HSEIssues;
			wQuery = wQuery.replace("<fromDate>", wInputs.getFromDate());
			wQuery = wQuery.replace("<toDate>", wInputs.getToDate() +Time_In_Min);
		} */ else if(wProjectName.equals(ISCM) && wTicketType.equals(SERVICE_REQUEST)){
			wQuery = ISCM_ServiceRequest;
			wQuery = wQuery.replace("<fromDate>", wInputs.getFromDate());
			wQuery = wQuery.replace("<toDate>", wInputs.getToDate() +Time_In_Min);
		}  else if(wProjectName.equals(TRAVEL) && wTicketType.equals(QUERY)){
			wQuery = Travel_Query;
			wQuery = wQuery.replace("<fromDate>", wInputs.getFromDate());
			wQuery = wQuery.replace("<toDate>", wInputs.getToDate() +Time_In_Min);
		}	/*else if(wProjectName.equals(BUSINESS_LEGAL)) {
			wQuery = BusinessLegal_Request;
			wQuery = wQuery.replace("<fromDate>", wInputs.getFromDate());
			wQuery = wQuery.replace("<toDate>", wInputs.getToDate() +Time_In_Min);
		}*/
		else if(wProjectName.equalsIgnoreCase("Operations") && wTicketType.equals(OFFICE_RELATED)){
			//else if(wProjectName.equals(WEDAM) && wTicketType.equals(OFFICE_RELATED)){--Rohit Modified
			wQuery = WedamOfficerelatedQuery;
			wQuery = wQuery.replace("<fromDate>", wInputs.getFromDate());
			wQuery = wQuery.replace("<toDate>", wInputs.getToDate() +Time_In_Min);
		}
		else if(wProjectName.equalsIgnoreCase("Operations") && wTicketType.equals(OPERATIONS_SECURITY)){
			//else if(wProjectName.equals(WEDAM) && wTicketType.equals(OFFICE_RELATED)){--Rohit Modified
			wQuery = WedamOperationSecurityrelatedQuery;
			wQuery = wQuery.replace("<fromDate>", wInputs.getFromDate());
			wQuery = wQuery.replace("<toDate>", wInputs.getToDate() +Time_In_Min);
		}else if(wProjectName.equalsIgnoreCase("Operations") && wTicketType.equals(OPERATIONS_ADMIN)){
			//else if(wProjectName.equals(WEDAM) && wTicketType.equals(OFFICE_RELATED)){--Rohit Modified
			wQuery = WedamOperationAdminrelatedQuery;
			wQuery = wQuery.replace("<fromDate>", wInputs.getFromDate());
			wQuery = wQuery.replace("<toDate>", wInputs.getToDate() +Time_In_Min);
	}else if(wProjectName.equalsIgnoreCase("Operations") && wTicketType.equals(OPERATIONS_WORKSPACE)){
		//else if(wProjectName.equals(WEDAM) && wTicketType.equals(OFFICE_RELATED)){--Rohit Modified
		wQuery = WedamOperationWorkspacerelatedQuery;
		wQuery = wQuery.replace("<fromDate>", wInputs.getFromDate());
		wQuery = wQuery.replace("<toDate>", wInputs.getToDate() +Time_In_Min);
	}else if(wProjectName.equalsIgnoreCase("Operations") && wTicketType.equals(OPERATIONS_FACILITY)){
		//else if(wProjectName.equals(WEDAM) && wTicketType.equals(OFFICE_RELATED)){--Rohit Modified
		wQuery = WedamOperationFacilityrelatedQuery;
		wQuery = wQuery.replace("<fromDate>", wInputs.getFromDate());
		wQuery = wQuery.replace("<toDate>", wInputs.getToDate() +Time_In_Min);
	}else if(wProjectName.equalsIgnoreCase("Operations")  && wTicketType.equals(HSE_SERVICES)){
			wQuery = WedamHSEServiceQuery;
			wQuery = wQuery.replace("<fromDate>", wInputs.getFromDate());
			wQuery = wQuery.replace("<toDate>", wInputs.getToDate() +Time_In_Min);
		}	else if(wProjectName.equalsIgnoreCase("Operations")  && wTicketType.equals(LAB_SERVICES)){
			wQuery = WedamLabServiceQuery;
			wQuery = wQuery.replace("<fromDate>", wInputs.getFromDate());
			wQuery = wQuery.replace("<toDate>", wInputs.getToDate() +Time_In_Min);
		}	else if(wProjectName.equals(CONTRACT) && wTicketType.equals(COMMERCIAL)){
			wQuery = ContractCommercialQuery;
			wQuery = wQuery.replace("<fromDate>", wInputs.getFromDate());
			wQuery = wQuery.replace("<toDate>", wInputs.getToDate() +Time_In_Min);
		}	else if(wProjectName.equals(CONTRACT) && wTicketType.equals(NON_COMMERCIAL)){
			wQuery = ContractNonCommercialQuery;
			wQuery = wQuery.replace("<fromDate>", wInputs.getFromDate());
			wQuery = wQuery.replace("<toDate>", wInputs.getToDate() +Time_In_Min);
		}	
		return wQuery;		
	}
	
	
	public List<String> getServiceNames(ReportInputs wInputs) throws InstantiationException, IllegalAccessException, ClassNotFoundException {		 
		 List<String> services = new ArrayList<String>();		 
		 Statement statement = null;
		 ResultSet rSet = null;
		 try {
			MYSQLConnection MySQLConn = new MYSQLConnection();
	        Connection con = MySQLConn.getConnection();
		 if(con != null){
			statement = con.createStatement();
			String wServiceNameSqlQuery = "";
			String wProjectName  = wInputs.getProject().replace(" ", "");
			String wTicketType = wInputs.getTicketType().replace(" ", "");
			/*if(wProjectName.equals(BANGALORE_ADMIN) && wTicketType.equals(SERVICE_REQUEST)){
				wServiceNameSqlQuery = BangaloreAdminServiceNameQuery;
			} else if(wProjectName.equals(BANGALORE_FACILITIES)&& wTicketType.equals(SERVICE_REQUEST)){
				wServiceNameSqlQuery = BangaloreFacilitiesServiceNameQuery;
			} else if(wProjectName.equals(HYDERABAD_OPERATIONS)&& wTicketType.equals(ADMIN)){
				wServiceNameSqlQuery = Hyderbad_Admin_ServiceNameQuery;
			} else if(wProjectName.equals(HYDERABAD_OPERATIONS)&& wTicketType.equals(FACILITIES)){
				wServiceNameSqlQuery = Hyderbad_Facilities_ServiceNameQuery;
			} else if(wProjectName.equals(MADURAI_OPERATIONS)&& wTicketType.equals(ADMIN)){
				wServiceNameSqlQuery = Madurai_Admin_ServiceNameQuery;
			} else if(wProjectName.equals(MADURAI_OPERATIONS)&& wTicketType.equals(FACILITIES)){
				wServiceNameSqlQuery = Madurai_Facilities_ServiceNameQuery;
			} else */ if(wProjectName.equals(LOGISTICS)&& wTicketType.equals(TRANSFER_OF_MATERIALS)){
				wServiceNameSqlQuery = Logistics_Transfer_Of_Materials_ServiceNameQuery;
			} else if(wProjectName.equals(LOGISTICS)&& wTicketType.equals(EXPORT_OF_METERIAL)){
				wServiceNameSqlQuery = Logistics_Export_Of_Meterial_ServiceNameQuery;
			} else if(wProjectName.equals(LOGISTICS)&& wTicketType.equals(TRANSFER_OF_MATERIALS_IT_RELATED)){
				wServiceNameSqlQuery = Logistics_Transfer_Of_Materials_IT_Related_ServiceNameQuery;
			} else if(wProjectName.equals(LOGISTICS)&& wTicketType.equals(EXPORT_OF_MATERIAL_IT_RELATED)){
				wServiceNameSqlQuery = Logistics_Export_Of_Material_IT_Related_ServiceNameQuery;
			} else if(wProjectName.equals(LOGISTICS)&& wTicketType.equals(CUSTOMS_CLEARANCE_AND_DISTRIBUTION)){
				wServiceNameSqlQuery = Logistics_Customs_Clearance_And_Distribution_ServiceNameQuery;
			} else if(wProjectName.equals(LOGISTICS)&& wTicketType.equals(KITTING_OF_BILL_OF_MATERIAL)){
				wServiceNameSqlQuery = Logistics_Kitting_Of_Bill_Of_Material_ServiceNameQuery;
			} /*else if(wProjectName.equals(TRANSPORT)&& wTicketType.equals(ROUTE_CARD)){
				wServiceNameSqlQuery = Transport_RouteCard_Services;
			} else if(wProjectName.equals(TRANSPORT)&& wTicketType.equals(CHANGE_ROUTE)){
				wServiceNameSqlQuery = Transport_Change_Route_Services;
			} else if(wProjectName.equals(TRANSPORT)&& wTicketType.equals(LOCAL_OUTSTATION_TRANSPORT)){
				wServiceNameSqlQuery = Transport_Local_Outstation_Transport_Services;
			} else if(wProjectName.equals(TRANSPORT)&& wTicketType.equals(QUERY)){
				wServiceNameSqlQuery = Transport_Query_Services;
			} else if(wProjectName.equals(TRANSPORT)&& wTicketType.equals(LATE_NIGHT_BOOKING)){
				wServiceNameSqlQuery = Transport_Late_Night_Booking_Services;
			}*/else if(wProjectName.equals(PKM)&& wTicketType.equals(SERVICE_REQUEST)){
				wServiceNameSqlQuery = PKM_ServiceRequest_Srevices;
			} else if(wProjectName.equals(PKM)&& wTicketType.equals(QUERY)){
				wServiceNameSqlQuery = PKM_Query_Srevices;
			} else if(wProjectName.equals(PKM)&& wTicketType.equals(PROJECT_SPECIFIC_TRAINING_REQUEST_EXTERNAL_TRAINING)){
				wServiceNameSqlQuery = PKM_External_Training_Srevices;
			} else if(wProjectName.equals(PKM)&& wTicketType.equals(PROJECT_SPECIFIC_TRAINING_REQUEST_INTERNAL_TRAINING)){
				wServiceNameSqlQuery = PKM_Internal_Training_Srevices;
			} /*else if(wProjectName.equals(HSE) && wTicketType.equals(SERVICE_REQUEST)){
				wServiceNameSqlQuery = HSE_ServiceRequest_Services;
			} else if(wProjectName.equals(HSE) && wTicketType.equals(WOKK_PERMIT)) {
				wServiceNameSqlQuery = HSE_WorkPermit_Services;
			} else if(wProjectName.equals(HSE) && wTicketType.equals(HSE_ISSUES)) {
				wServiceNameSqlQuery = HSE_HSEIssues_Services;
			}*/ else if(wProjectName.equals(ISCM) && wTicketType.equals(SERVICE_REQUEST)){
				wServiceNameSqlQuery = ISCM_Services;
			} else if(wProjectName.equals(TRAVEL) && wTicketType.equals(QUERY)){
				
				wServiceNameSqlQuery = Travel_Query_Services;
			} /*else if(wProjectName.equals(BUSINESS_LEGAL)){
				wServiceNameSqlQuery =BusinessLeagl_Services;
			}*/else if(wProjectName.equalsIgnoreCase("Operations")&& wTicketType.equals(OFFICE_RELATED)){ 
			//else if(wProjectName.equals(WEDAM)&& wTicketType.equals(OFFICE_RELATED)){--Rohit Modified it
				wServiceNameSqlQuery = WedamOfficerelatedServicesQuery;
			}else if(wProjectName.equalsIgnoreCase("Operations")&& wTicketType.equals(OPERATIONS_ADMIN)){ 
			//else if(wProjectName.equals(WEDAM)&& wTicketType.equals(OFFICE_RELATED)){--Rohit Modified it
				wServiceNameSqlQuery = WedamOperationAdminrelatedServicesQuery;
			}else if(wProjectName.equalsIgnoreCase("Operations")&& wTicketType.equals(OPERATIONS_WORKSPACE)){ 
			//else if(wProjectName.equals(WEDAM)&& wTicketType.equals(OFFICE_RELATED)){--Rohit Modified it
				wServiceNameSqlQuery = WedamOperationWorkspacerelatedServicesQuery;
			}else if(wProjectName.equalsIgnoreCase("Operations")&& wTicketType.equals(OPERATIONS_FACILITY)){ 
			//else if(wProjectName.equals(WEDAM)&& wTicketType.equals(OFFICE_RELATED)){--Rohit Modified it
				wServiceNameSqlQuery = WedamOperationFacilityrelatedQueryServicesQuery;
			}else if(wProjectName.equalsIgnoreCase("Operations")&& wTicketType.equals(OPERATIONS_SECURITY)){ 
			//else if(wProjectName.equals(WEDAM)&& wTicketType.equals(OFFICE_RELATED)){--Rohit Modified it
				wServiceNameSqlQuery = WedamOperationSecurityrelatedServicesQuery;
			}
			else if(wProjectName.equalsIgnoreCase("Operations")&& wTicketType.equals(HSE_SERVICES)){
				wServiceNameSqlQuery = WedamHSEServiceServicesQuery;
			} else if(wProjectName.equalsIgnoreCase("Operations")&& wTicketType.equals(LAB_SERVICES)){
				wServiceNameSqlQuery = WedamLabServiceServicesQuery;
			} else if(wProjectName.equals(CONTRACT)&& wTicketType.equals(COMMERCIAL)){
				wServiceNameSqlQuery = ContractCommercialServicesQuery;
			} else if(wProjectName.equals(CONTRACT)&& wTicketType.equals(NON_COMMERCIAL)){
				wServiceNameSqlQuery = ContractNonCommercialServicesQuery;
			}
			 rSet = statement.executeQuery(wServiceNameSqlQuery);
			while(rSet.next()){
				if(rSet.getString(1).equals("Please Select") || rSet.getString(1).equals("Select Service")){
					//do nothing
				} else if(!rSet.getString(1).contains("Please select..")){
					services.add(rSet.getString(1));
				}
			 }			
			//services.add("Empty");
			rSet.close();
			statement.close();
			con.close();
		 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Collections.sort(services);
		return services;
	 }

	public ArrayList<ResultBean> loadYTDData(ReportInputs inputs) {
		BusninessLegalSLA slaCalc = new BusninessLegalSLA();
		String year= inputs.getFromDate();
		year = year.substring(0, 4);
		inputs.setFromDate(year+"-01-01");
		inputs.setToDate(year+"-12-31");
		String wRequestQuery = selectQuery(inputs);
		slaCalc.start(wRequestQuery, properties, holidaysList, wPriorityList, inputs);		
		return slaCalc.getResults();
	}
 }
