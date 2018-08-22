package com.hts.smf.report.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import org.apache.poi.hssf.usermodel.*;

import java.io.*;

public class OTDAutomation {
	  Calendar cal = new GregorianCalendar();
	  	//public static final DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	  	public static final DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	  	public static final DateFormat monthFormat = new SimpleDateFormat("MMM");
	  	//public static final DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
	  	public static final DateFormat formatter = new SimpleDateFormat("E MM/dd/yyyy HH:mm:ss Z yyyy");
	  	final String URL = "jdbc:jtds:sqlserver://ie1aw0015.global.ds.honeywell.com:1433/";
		final String DBName = "JIRA_EMPTY";
		final String UserName = "Htsjira"; 
		final String Password = "J(!r@ht$";
		final String Driver = "net.sourceforge.jtds.jdbc.Driver";
		ArrayList<ResultBean> wResult = new  ArrayList<ResultBean>();
		List<String> BUDetails = new ArrayList<String>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		HSSFDataFormatter df = new HSSFDataFormatter();
		public void readData()
		{
			try{
				Class.forName(Driver).newInstance();
				con = DriverManager.getConnection(URL+DBName,UserName,Password);
				st = con.createStatement();
				List<IssuesBean> issuesList = new ArrayList<IssuesBean>();
				
				File file = new File("/home/ibestjira/OTD.xls");
				//File file = new File("C:\\Users\\e499378\\Desktop\\OTD1.xls");
	            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
	            HSSFWorkbook wb = new HSSFWorkbook(fs);
	            HSSFSheet sheet = wb.getSheetAt(0);
	            int rows = sheet.getPhysicalNumberOfRows();
	            Calendar DueDate = new GregorianCalendar();
	            Calendar HiredstartDate = new GregorianCalendar();
	            Calendar offerDate = new GregorianCalendar();
	            Calendar requestDate = new GregorianCalendar();
	            String CCGID = "";
	            int Year ;
	            
	            for(int i=1; i< rows - 1;i++)
	            {
	            
	            	
	            	IssuesBean issue = new IssuesBean();
	            	HSSFRow row = sheet.getRow(i);//Zero Based	            	
	            	//DueDate=RequestOpenDate(11)+90	   
	            	//System.out.println("RowCount:" + i + " ID"+ row.getCell(0).toString());
	            		if(row.getCell(11) != null && row.getCell(11).toString()!= null&&row.getCell(11).toString().length()>=1)
	            			{            				           			
		            			String str = sdf.format(row.getCell(11).getDateCellValue());	            			
		            			requestDate.setTime(row.getCell(11).getDateCellValue());
	            				
	            				DueDate.setTime(row.getCell(11).getDateCellValue());
	            				DueDate.add(Calendar.DAY_OF_MONTH, 90);            				           				
	            				DueDate.get(Calendar.YEAR);
	            				if(DueDate.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR))
	            				{
		            				issue.setScheduleStatus(1);
		            				issue.setDueYear(Integer.toString(DueDate.get(Calendar.YEAR)));
		            				issue.setDueDate(sdf.format(DueDate.getTime()));	            				
		            				issue.setDueMonth(monthFormat.format(DueDate.getTime()));
	            				}
	            			}
	            		else	
	            			//if(DueDate.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR))
	            			issue.setScheduleStatus(0);
	            		
	            		
	            		//Master If to check the due date is for current year
	            		if(DueDate.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)){
	            		
	            		//To capture the rfpNum
		            	if(row.getCell(0)!=null && row.getCell(0).toString()!=null)
		            	{
		            		String text = row.getCell(0).toString();
		            		if(text.length()>1 && text.indexOf(".")>0)
		            		issue.setRfpNum(text.substring(0,text.indexOf(".")));
		            		else 
		            			issue.setRfpNum(row.getCell(0).toString());
		            		
		            	}
		            	
		            	
		            	//To Capture the BU details
		            	if(row.getCell(5) != null && row.getCell(5).toString()!= null)
		            	{
		            		BUDetails.add(row.getCell(5).toString());
		            		issue.setBusinessUnit(row.getCell(5).toString());
		            	}
		            	
	            	
	            	//Pass/Fail
	            	//HiredStartDate=22
	            	//Status(Hired/Open)=21
	            	if(row.getCell(21)!=null && row.getCell(21).toString()!= null)
	            		if(row.getCell(21).toString().equalsIgnoreCase("open"))
	            			issue.setPassFailStatus(0);
	            			
	            		else
	            			
	            				if(row.getCell(22)!=null && row.getCell(22).toString() != null)
	            				{
	            					//HiredstartDate.setTime(new SimpleDateFormat("dd-MMM-yyyy").parse(row.getCell(22).toString()));
	            					HiredstartDate.setTime(row.getCell(22).getDateCellValue());
	            					issue.setTatJoin(Long.toString(calculateDateDiff(HiredstartDate,requestDate)));
	            					if(HiredstartDate.before(DueDate) || HiredstartDate.equals(DueDate))
	            						issue.setPassFailStatus(1);
	            					else
	            						issue.setPassFailStatus(0);
	            				}
	            	
	            	//OfferAcceptedDate=13
	            	if(row.getCell(13)!= null && row.getCell(13).toString()!= null)
	            	{
	            		offerDate.setTime(row.getCell(13).getDateCellValue());	            		            		
	            		issue.setTatOffer(Long.toString(calculateDateDiff(offerDate, requestDate)));
	            	}	
	            	//Account code=44
	            	if(row.getCell(44)!= null && row.getCell(44).toString()!= null)
	            	{
	            		//Remove the tilde character and query it to database to get the CCG Code.
	            		CCGID = row.getCell(44).toString().substring(4,14);
	            		issue.setCostCode(row.getCell(44).toString().substring(4,14));
	            		rs = st.executeQuery("SELECT distinct(CCG2) from dbo.CCGDATA where CCCode = "+"'"+CCGID+"'");
	            		while(rs.next())
	            			//CCGName = rs.getString(1);
	            			issue.setCcgName(rs.getString(1));
	            	}
	            	issuesList.add(issue);
	            		
	              }//End of master if.
	            }//End of row calculation
	            issueParse(issuesList);
	            
			}
			catch(Exception e){e.printStackTrace();}
		}
		
		private void issueParse(List<IssuesBean> issueList) throws ParseException
		{
			int pass_fail;
            int schedule;
            String TAT_Offer = "";
            String TAT_Join = "";
            String CCGID = "";
            String CCGName = "";
            String dueYear = "";
            String dueDate = "";
            String dueMonth = "";
            String businessUnit = "";
            String rfpNum="";
            int counter=0;
            
            Iterator<IssuesBean> iter = issueList.iterator();
            IssuesBean issue = null;
            while(iter.hasNext()){
    			issue =iter.next();
    			pass_fail = issue.getPassFailStatus();
    			schedule = issue.getScheduleStatus();
    			TAT_Offer = issue.getTatOffer();
    			TAT_Join = issue.getTatJoin();
    			CCGID = issue.getCostCode();
    			CCGName = issue.getCcgName();
    			dueYear = issue.getDueYear();
    			dueDate = issue.getDueDate();
    			dueMonth = issue.getDueMonth();
    			//System.out.println(dueMonth);
    			businessUnit = issue.getBusinessUnit();
    			//System.out.println(businessUnit);
    			rfpNum = issue.getRfpNum();
    			if(issue.getBusinessUnit().trim().equalsIgnoreCase("EAER")){counter++;}
    			//Pending action is to set the above to resultBean.
    			setResultsToBean(pass_fail,schedule,TAT_Offer,TAT_Join,CCGID,CCGName,dueYear,dueMonth,dueDate,businessUnit,rfpNum);
            }
            System.out.println("TotalCounter EAER "+counter);
		}
		
		//Set the data to resultBean
		private void setResultsToBean(int pass_fail,int schedule,String TAT_Offer,String TAT_Join,String CCGID,String CCGName,String dueYear,String dueMonth,String dueDate,String businessUnit
				,String rfpNum)
		{
			ResultBean resultBean = new ResultBean();
			resultBean.setPassFailStatus(pass_fail);
			resultBean.setScheduleStatus(schedule);
			resultBean.setTatOffer(TAT_Offer);
			resultBean.setTatJoin(TAT_Join);
			resultBean.setCostCode(CCGID);
			resultBean.setCcgName(CCGName);
			resultBean.setDueYear(dueYear);
			resultBean.setDueMonth(dueMonth);
			resultBean.setDueDate(dueDate);
			resultBean.setBusinessUnit(businessUnit);
			//System.out.println(businessUnit);
			resultBean.setRfpNum(rfpNum);
			wResult.add(resultBean);
		}
		
		public ArrayList<ResultBean> getResults(){
			return wResult;
		} 
		
		//The below method is to send the BU details to build up the Summary Reprot
		public List<String> getBUDetails()
		{
			return BUDetails;
		}
		//Calculating date difference
		private long calculateDateDiff(Calendar Time1, Calendar Time2 ) {
			long wTimeOne = Time1.getTimeInMillis();
			long wTimeTwo = Time2.getTimeInMillis();		
			long diff =0;
			//System.out.println(Time1.getTime());
			//System.out.println(Time2.getTime());
			if(wTimeOne > wTimeTwo)
				diff = wTimeOne - wTimeTwo;
			else
				diff = wTimeTwo - wTimeOne;
		    //long dSeconds = d / 1000;
		    //long dMinutes = diff / (60 * 1000);
		    //int dHour = (int)diff / (60 * 60 * 1000);
		    long dDays =(long) diff / (24 * 60 * 60 * 1000);
		    return dDays;
		}
}
