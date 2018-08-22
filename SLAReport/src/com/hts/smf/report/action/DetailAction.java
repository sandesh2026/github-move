package com.hts.smf.report.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hts.smf.report.utils.MYSQLConnection;
import com.opensymphony.xwork2.ActionSupport;

public class DetailAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String lst;
	private List<String> lstList = null;
	
	public String execute() throws Exception {	
	if (getLst() != null && !getLst().equals("")) {
		populateDetail(getLst());
		return SUCCESS;
		} else {
		return SUCCESS;
		}
	}
	
	private void populateDetail(String id) {
		lstList = new ArrayList<String>();		
		MYSQLConnection conn = new MYSQLConnection();
        ResultSet rs = null;
        Statement stmt = null;
        Connection con = conn.getConnection();
        try {
			stmt=	con.createStatement();
			rs = stmt.executeQuery("SELECT pname FROM JIRA_EMPTY.dbo.issuetype WHERE id IN(SELECT issuetype FROM JIRA_EMPTY.dbo.issuetypescreenschemeentity WHERE scheme= (SELECT sink_node_id FROM JIRA_EMPTY.dbo.nodeassociation WHERE sink_node_entity='IssueTypeScreenScheme' AND source_node_id=(SELECT id FROM JIRA_EMPTY.dbo.project WHERE pname like '" + id+"'))and issuetype != 84)");
			//rs = stmt.executeQuery("SELECT pname FROM dbo.issuetype WHERE id IN(SELECT issuetype FROM dbo.issuetypescreenschemeentity WHERE scheme= (SELECT sink_node_id FROM dbo.nodeassociation WHERE sink_node_entity='IssueTypeScreenScheme' AND source_node_id=(SELECT id FROM dbo.project WHERE pname like '" + id+"'))and issuetype != 84)");
			while(rs.next()){
				if(!rs.getString(1).equalsIgnoreCase("Other Service Request"))
				lstList.add(rs.getString(1));
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}      
	}
	
	public List<String> getLstList() {
		return lstList;
	}
	
	public void setLstList(List<String> lstList) {
		this.lstList = lstList;
	}
	
	public String getLst() {
		return lst;
	}
	
	public void setLst(String lst) {
		this.lst= lst;
	}
}