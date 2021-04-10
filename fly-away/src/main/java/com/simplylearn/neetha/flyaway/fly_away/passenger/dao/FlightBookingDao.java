package com.simplylearn.neetha.flyaway.fly_away.passenger.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class FlightBookingDao {
	
	String qry;
	Connection dbCon;
	Statement theStatement;
	
	void preJdbcOperations(){

    	try {
//    		Define the URL to connect
    		String urlToConnect = "jdbc:mysql://localhost:3306/flyaway_schema";
    		
//    		Define the username for db to connect
    		String dbUserName = "root";
    		
//    		Define the password
    		String dbUserPassword = "";
    		
//    		Define the driver for the database
    		String mySqlDriver = "com.mysql.cj.jdbc.Driver";
    		
    		
//        	Load the Driver
			Class.forName(mySqlDriver);
			
//			Try to establish the connection
			dbCon = DriverManager.getConnection(urlToConnect, dbUserName, dbUserPassword);
			
//        	Get a reference to the Statement
			theStatement = dbCon.createStatement();
			
//			System.out.println("Successfully connected to the database...");
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("Can't load the Driver : " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Can't connect to DB : " + e.getMessage());
		} 
	}

}
