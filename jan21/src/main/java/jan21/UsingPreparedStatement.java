package jan21;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UsingPreparedStatement {
	
	String qry;
	Connection dbCon;
	Statement theStatement;
	
	UsingPreparedStatement(){

    	try {
//    		Define the URL to connect
    		String urlToConnect = "jdbc:mysql://localhost:3306/simplilearn_march_21";
    		
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

	public static void main(String[] args) {
		Scanner newScanner = new Scanner(System.in);
//		System.out.println("Please enter the id of the record to be updated");
//		int id = newScanner.nextInt();
//		System.out.println("Please enter the name");
//		newScanner.nextLine();
//		String learnerName = newScanner.nextLine();
//		
//		new UsingPreparedStatement().updateLearner(id, learnerName);
		
		
		System.out.println("Please enter the id of the record to be updated");
		int id = newScanner.nextInt();
		new UsingPreparedStatement().deleteLearner(id);
		newScanner.close();
		
	}
	
	//Insert new record using PreparedStatement
	void addNewLearner(String LearnerName, String LearnerAddress) {
		
		qry = "insert into learners(learner_name, learner_address) values (?,?)";
		
		// get reference to the PreparedStatement
		try {
			PreparedStatement thePreparedStmt = dbCon.prepareStatement(qry);
			
			//set the values for the PreparedStatement
			thePreparedStmt.setString(1, LearnerName);
			thePreparedStmt.setString(2, LearnerAddress);
			
			if(thePreparedStmt.executeUpdate()>0) {
				System.out.println("Record added");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't get a reference to Statement : " + e.getMessage());
		}
		
	}
	
	//Fetch all details
	void getAllRecords(int id) {
		
		qry = "select * from learners where learnerid = ?";
		
		// get reference to the PreparedStatement
		try {
			PreparedStatement thePreparedStmt = dbCon.prepareStatement(qry);
			
			//set the values for the PreparedStatement
			thePreparedStmt.setInt(1, id);
			
			ResultSet theResultSet = thePreparedStmt.executeQuery();
			
			while(theResultSet.next()) {
				System.out.println("Name = "+theResultSet.getString("learner_name"));
				System.out.println("Address = "+theResultSet.getString("learner_address"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't get a reference to Statement : " + e.getMessage());
		}

	}
	
	//update an existing record
	void updateLearner(int id, String learnerName) {
		
		
		qry = "UPDATE `learners` SET `learner_name` = ? WHERE `learners`.`learnerid` = ?";
		
		try {
			PreparedStatement thePreparedStmt = dbCon.prepareStatement(qry);
			
			//set the values for the PreparedStatement
			thePreparedStmt.setInt(2, id);
			thePreparedStmt.setString(1, learnerName);
			
			if(thePreparedStmt.executeUpdate()>0) {
				System.out.println("Record updated");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't get a reference to Statement : " + e.getMessage());
		}
		
		
	}
	
	// delete record
void deleteLearner(int id) {
		
		
		qry = "DELETE FROM `learners` WHERE `learners`.`learnerid` = ?";
		
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

}
