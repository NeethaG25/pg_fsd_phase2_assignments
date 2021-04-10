package com.simplylearn.neetha.flyaway.fly_away.admin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AirlineDao {
	
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
	
	//Insert new record using PreparedStatement
	public void addNewAirline(int flightId, Double amount, int seats) {
		
		preJdbcOperations();
		qry = "insert into airline_master(Flight_Id, Amount, Seats) values (?,?,?)";
		
		// get reference to the PreparedStatement
		try {
			PreparedStatement thePreparedStmt = dbCon.prepareStatement(qry);
			
			//set the values for the PreparedStatement
			thePreparedStmt.setInt(1, flightId);
			thePreparedStmt.setDouble(2, amount);
			thePreparedStmt.setInt(3, seats);
			
			if(thePreparedStmt.executeUpdate()>0) {
				System.out.println("Record added");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't get a reference to Statement : " + e.getMessage());
		}
		
	}
	
	//Update new record using PreparedStatement
	public void updateAirline(int flightId, Double amount, int seats) {
		
		preJdbcOperations();
		qry = "UPDATE airline_master SET Amount = ?, Seats = ? WHERE Flight_Id = ?";
		
		try {
			PreparedStatement thePreparedStmt = dbCon.prepareStatement(qry);
			
			//set the values for the PreparedStatement
			thePreparedStmt.setInt(3, flightId);
			thePreparedStmt.setDouble(1, amount);
			thePreparedStmt.setInt(2, seats);
			
			if(thePreparedStmt.executeUpdate()>0) {
				System.out.println("Record updated");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't get a reference to Statement : " + e.getMessage());
		}
		
	}
	
	//Update new record using PreparedStatement
	public void deleteAirline(int id) {
		
		preJdbcOperations();
		qry = "DELETE FROM `airline_master` WHERE `Flight_Id` = ?";
		
		try {
			PreparedStatement thePreparedStmt = dbCon.prepareStatement(qry);
			
			//set the values for the PreparedStatement
			thePreparedStmt.setInt(1, id);
							
			if(thePreparedStmt.executeUpdate()>0) {
				System.out.println("Record deleted");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't get a reference to Statement : " + e.getMessage());
		}
		
	}
	
	//Fetch All Learners
	public void getAllAirlines() {
		
		qry = "select * from airline_master";
		
		// get reference to the PreparedStatement
		try {
			PreparedStatement thePreparedStmt = dbCon.prepareStatement(qry);
			
			ResultSet theResultSet = thePreparedStmt.executeQuery();
			
			while(theResultSet.next()) {
				System.out.println("First Name = "+theResultSet.getInt("Flight_Id"));
				System.out.println("Last Name = "+theResultSet.getDouble("Amount"));
				System.out.println("Address = "+theResultSet.getInt("Seats"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't get a reference to Statement : " + e.getMessage());
		}

	}

}
