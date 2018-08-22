package com.hts.smf.report.action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;

public class ListingAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private List<String> lstList1 = null;
	private String usr;

	public String getUsr() {
		return usr;
	}

	public void setUsr(String usr) {
		this.usr = usr;
	}

	public String execute() throws Exception {
		
		//setUsr(encryptString(usr));
		populateDetail();
		return SUCCESS;
	}
	public static String encryptString(String str)
	   {
		  String key = "Encrypt"; 
	      StringBuffer sb = new StringBuffer (str);

	      int lenStr = str.length();
	      int lenKey = key.length();
		   
	      for ( int i = 0, j = 0; i < lenStr; i++, j++ ) 
	      {
	         if ( j >= lenKey ) j = 0;  
	         sb.setCharAt(i, (char)(str.charAt(i) ^ key.charAt(j))); 
	      }

	      return sb.toString();
	   }
	private void populateDetail() {
	
		lstList1 = new ArrayList<String>();
		/*if(usr.equalsIgnoreCase("e426834")||usr.equalsIgnoreCase("e483826")||usr.equalsIgnoreCase("e413201")||usr.equalsIgnoreCase("e182390")||usr.equalsIgnoreCase("e447484")||usr.equalsIgnoreCase("e195246"))
		{
			//lstList1.add("Bangalore Admin");
		    //lstList1.add("Bangalore Facilities");
		    //lstList1.add("Hyderabad Operations");
		    //lstList1.add("Madurai Operations");
		    lstList1.add("WEDAM");
		}
		else if(usr.equalsIgnoreCase("e401933"))
		{
			lstList1.add("Business Legal");
		}
		else if(usr.equalsIgnoreCase("e515907")||usr.equalsIgnoreCase("e371955"))
		{
			lstList1.add("EXIM");
		}
		else if(usr.equalsIgnoreCase("e297370"))
		{
			lstList1.add("PKM");
		}
		else if(usr.equalsIgnoreCase("e401891")||usr.equalsIgnoreCase("e419626"))
		{
			//lstList1.add("HSE");
		}
		else if(usr.equalsIgnoreCase("e331523")||usr.equalsIgnoreCase("e308846"))
		{
			lstList1.add("ISCM");
		}
		else if(usr.equalsIgnoreCase("e451233"))
		{
			lstList1.add("Travel (International & Domestic Travel)");
		}
		else if(usr.equalsIgnoreCase("e481557"))
		{
			lstList1.add("Learning & Development Services");
		}
		else
		{*/
		    /*lstList1.add("Bangalore Admin");
		    lstList1.add("Bangalore Facilities");*/
		    //lstList1.add("Business Legal");
		    /*lstList1.add("Hyderabad Operations");
		    lstList1.add("Madurai Operations");*/
		    lstList1.add("EXIM");
		    lstList1.add("PKM");
		    /*lstList1.add("Transport");
		    lstList1.add("HSE");*/
		    lstList1.add("ISCM");
		    //lstList1.add("Travel (International & Domestic Travel)");
		    lstList1.add("Operations");
		    //Rohit added here on Jan 1st 2013
		    //lstList1.add("OPERATIONS");
		    lstList1.add("ISOPS");
		    lstList1.add("OTD");
		    lstList1.add("Inspection");
		    //lstList1.add("Contract Management");
		//}
	    /* MYSQLConnection conn = new MYSQLConnection();
        ResultSet rs = null;
        Statement stmt = null;
        Connection con = conn.getConnection();
        try {
			stmt=	con.createStatement();
			rs = stmt.executeQuery("SELECT pname FROM project ORDER BY pname");			
			while(rs.next()){
				String pname = rs.getString(1);
				if(pname.equals("Bangalore Admin")){
					lstList1.add(pname);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			con.close();
		}*/
	}
	
	public List<String> getlstList1() {
		return lstList1;
	}
	
	public void setlstList1(List<String> lstList1) {
		this.lstList1 = lstList1;
	}
}