package com.hts.smf.report.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MYSQLConnection implements ReportConstants {
	Connection conn = null;	
	public Connection getConnection(){		
		if(conn == null){
			try {
				Class.forName(Driver).newInstance();
				conn = DriverManager.getConnection(URL+DBName,UserName,Password);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		return 	conn;
	}
	public Connection getConnection(boolean flag){		
		if(conn == null){
			try {
				Class.forName(Driver).newInstance();
				conn = DriverManager.getConnection(URL1+DBName1,UserName1,Password1);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		return 	conn;
	}
}