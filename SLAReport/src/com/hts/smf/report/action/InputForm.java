package com.hts.smf.report.action;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import com.hts.smf.report.beans.BLServicewiseReportBean;
import com.hts.smf.report.beans.ReportInputs;
import com.hts.smf.report.beans.ResultBean;
import com.hts.smf.report.beans.ServicewiseReportBean;
import com.hts.smf.report.utils.ReportConstants;
import com.hts.smf.report.utils.ResultAction;
import com.opensymphony.xwork2.ActionSupport;


public class InputForm extends ActionSupport {   
	private static final long serialVersionUID = 1L;
	private String lst;	
	private String ticketType;
	private String location;
	private String fromDate;
	private String toDate; 
	private ArrayList<ResultBean> resultList;
	private ArrayList<ResultBean> ytdResultList;
	private ArrayList<ServicewiseReportBean> servicewiseData;
	private ArrayList<BLServicewiseReportBean> blServicewiseData;
	private ArrayList<BLServicewiseReportBean> wipResultList;
	private ArrayList<BLServicewiseReportBean> resResultList;
	private ArrayList<BLServicewiseReportBean> openTicketsServiceWiseData;
	private ArrayList<BLServicewiseReportBean> earningServiceWiseData;
	
	public InputForm() {
    }

    @Override
	public String execute() {
    	String MESSAGESUCCESS = executeSLA();    	
        return MESSAGESUCCESS;
    }

	/**
	 * @throws FileNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	private String executeSLA() {
		String wSuccessMsg="success";
		ReportInputs rInputs= new ReportInputs();
        rInputs.setProject(getLst());        
        rInputs.setTicketType(getTicketType());
        rInputs.setLocation(getLocation());
        rInputs.setFromDate(getFromDate());
        rInputs.setToDate(getToDate());
        ResultAction action = new ResultAction();		
		try {			
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(ReportConstants.INPUT_PROPERTIES);
			//InputStream inputStream = new FileInputStream(ReportConstants.INPUT_PROPERTIES);
			resultList = new ArrayList<ResultBean>();
			resultList.addAll(action.loadData(rInputs, inputStream));			
			servicewiseData= new ArrayList<ServicewiseReportBean>();
			blServicewiseData = new ArrayList<BLServicewiseReportBean>();
			openTicketsServiceWiseData = new ArrayList<BLServicewiseReportBean>();
			if(getLst().replace(" ", "").equals(ReportConstants.BUSINESS_LEGAL)){				
				wipResultList = new ArrayList<BLServicewiseReportBean>();
				resResultList = new ArrayList<BLServicewiseReportBean>();				
				resResultList.addAll(action.loadResolvedBLServicewiseData(getResultList()));
				wipResultList.addAll(action.loadWIPBLServicewiseData(getResultList()));  
				blServicewiseData.addAll(action.loadBLServicewiseData(rInputs, getResultList()));
				openTicketsServiceWiseData.addAll(action.loadWIPRemHrsBLServicewiseData(rInputs, getResultList()));
				
				ytdResultList = new ArrayList<ResultBean>();				
				ytdResultList.addAll(action.loadYTDData(rInputs));			
				earningServiceWiseData = new ArrayList<BLServicewiseReportBean>();
				earningServiceWiseData.addAll(action.loadBLEarningReportServicewiseData(rInputs,getResultList(), getYtdResultList()));
			} else{				
				 servicewiseData.addAll(action.loadServicewiseData(rInputs, getResultList()));
			}
			 if(getLst().equals(ReportConstants.LOGISTICS)){
				 wSuccessMsg = ReportConstants.LOGISTICS;
			 } else if(getLst().equals(ReportConstants.TRANSPORT)){ 
				 wSuccessMsg = ReportConstants.TRANSPORT;
			 }else if(getLst().equals(ReportConstants.PKM)) {
				 wSuccessMsg = ReportConstants.PKM;
			 } else if(getLst().equals(ReportConstants.HSE)){
				 wSuccessMsg = ReportConstants.HSE;
			 } else if(getLst().equals(ReportConstants.ISCM)){
				 wSuccessMsg = ReportConstants.ISCM;
			 } else if(getLst().equals(ReportConstants.TRAVEL)){
				 wSuccessMsg = ReportConstants.TRAVEL;
			 }else if(getLst().replace(" ","").equals(ReportConstants.BUSINESS_LEGAL)){
				 wSuccessMsg = ReportConstants.BUSINESS_LEGAL;
			 }else if(getLst().equalsIgnoreCase("Operations")){
			 //else if(getLst().equals(ReportConstants.WEDAM)) Rohit Modified it{
				 wSuccessMsg = ReportConstants.WEDAM;
			 //Rohit added on Jan 1st 2013
		     
			 }
			 else if(getLst().equals("OPERATIONS"))
			 {
				 wSuccessMsg = ReportConstants.WEDAM;
			 }
			 else if(getLst().replace(" ","").equals(ReportConstants.CONTRACT)){
				 wSuccessMsg = ReportConstants.CONTRACT;
			 }else if(getLst().replace(" ","").equals("ISOPS")){
				 wSuccessMsg = "ISOPS";
			 }
			 else if(getLst().replace(" ","").equalsIgnoreCase("OTD")){
				 wSuccessMsg = "OTD";
			 }
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return wSuccessMsg;
	}

    @Override
	public void validate() {
        if (getFromDate().length() == 0) {
            addFieldError("fromDate", "FromDate is required");
        }
        if (getToDate().length() == 0) {
            addFieldError("toDate", getText("ToDate is required"));
        }
    }    
  
	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public ArrayList<ResultBean> getResultList() {
		return resultList;
	}

	public void setResultList(ArrayList<ResultBean> resultList) {
		this.resultList = resultList;
	}

	public ArrayList<ServicewiseReportBean> getServicewiseData() {
		return servicewiseData;
	}

	public void setServicewiseData(ArrayList<ServicewiseReportBean> servicewiseData) {
		this.servicewiseData = servicewiseData;
	}

	public ArrayList<BLServicewiseReportBean> getBlServicewiseData() {
		return blServicewiseData;
	}

	public void setBlServicewiseData(
			ArrayList<BLServicewiseReportBean> blServicewiseData) {
		this.blServicewiseData = blServicewiseData;
	}

	public ArrayList<BLServicewiseReportBean> getWipResultList() {
		return wipResultList;
	}

	public void setWipResultList(ArrayList<BLServicewiseReportBean> wipResultList) {
		this.wipResultList = wipResultList;
	}

	public ArrayList<BLServicewiseReportBean> getResResultList() {
		return resResultList;
	}

	public void setResResultList(ArrayList<BLServicewiseReportBean> resResultList) {
		this.resResultList = resResultList;
	}

	public ArrayList<BLServicewiseReportBean> getOpenTicketsServiceWiseData() {
		return openTicketsServiceWiseData;
	}

	public void setOpenTicketsServiceWiseData(
			ArrayList<BLServicewiseReportBean> openTicketsServiceWiseData) {
		this.openTicketsServiceWiseData = openTicketsServiceWiseData;
	}

	public ArrayList<BLServicewiseReportBean> getEarningServiceWiseData() {
		return earningServiceWiseData;
	}

	public void setEarningServiceWiseData(
			ArrayList<BLServicewiseReportBean> earningServiceWiseData) {
		this.earningServiceWiseData = earningServiceWiseData;
	}

	public ArrayList<ResultBean> getYtdResultList() {
		return ytdResultList;
	}

	public void setYtdResultList(ArrayList<ResultBean> ytdResultList) {
		this.ytdResultList = ytdResultList;
	}

	public String getLst() {
		return lst;
	}

	public void setLst(String lst) {
		this.lst = lst;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}
}