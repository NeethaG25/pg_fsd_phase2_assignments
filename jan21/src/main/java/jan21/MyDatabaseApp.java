package jan21;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class MyDatabaseApp {
	
	String qry;
	Connection dbCon;
	Statement theStatement;
	
	MyDatabaseApp(){

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
		
//		new MyDatabaseApp().getAllRecords();
//    	
//    	System.out.println("Please enter the ID:");
//    	
//    	int id = new Scanner(System.in).nextInt();
//    	
//    	new MyDatabaseApp().getLearnerDetailsById(id);
		
		System.out.println("Please enter the ID to be deleted");
    	
    	int id = new Scanner(System.in).nextInt();
		
		new MyDatabaseApp().deleteLearner(id);
		
	}
	
//  Get All Records from table:learners
  void getAllRecords() {
//  	Write the query to fetch all rows from table:learners
  	qry = "select * from learners";

  	try {

			
//			Execute the query
			ResultSet theResultSet = theStatement.executeQuery(qry);
			
//			Traverse through the results
			while(theResultSet.next()) {
				System.out.print("Name : " + theResultSet.getString("learner_name"));
				System.out.print(", ID : " + theResultSet.getInt("learnerid"));
				System.out.println(", Address : " + theResultSet.getString("learner_address"));
			}
			
		} catch (SQLException e) {
			System.out.println("Can't get a reference to Statement : " + e.getMessage());
		}
  }
  
  
 
//Get Learner details for a particular id
  void getLearnerDetailsById(int id) {
//  	Write the query to fetch details from the table:learners
  	qry = "select * from learners where learnerid = " + id;
  	

  	try {
//      	Execute the query
			ResultSet theResultSet = theStatement.executeQuery(qry);
			
			System.out.println("Details of learner for id : " + id);
			
//			Traverse through the results
			while(theResultSet.next()) {
				System.out.println("Name : " + theResultSet.getString("learner_name") + ", Address : " + theResultSet.getString("learner_address"));
			}
			
			
		} catch (SQLException e) {
			System.out.println("Can't execute the query : " + e.getMessage());
		}
  }
  
  // Add new learner to the table: learners
  void addNewLearner() {
	  //write the query to insert a new record
	  qry = "insert into learners(learner_name, learner_address) values ('EFG', 'LA, USA')";
	  
	  //Execute the query
	  try {
		theStatement.executeUpdate(qry);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("Insert was not successful "+e.getMessage());
	}
  }
  
  //update
  void updateLearner(int id) {
	  qry = "UPDATE `learners` SET `learner_name` = 'xyz' WHERE `learners`.`learnerid` = "+id;
	  
	  try {
		theStatement.executeUpdate(qry);
		System.out.println("Update was successful");
	} catch (SQLException e) {
		System.out.println("Update was not successful "+e.getMessage());
	}
  }

  //delete
  void deleteLearner(int id) {
	  qry = "DELETE FROM `learners` WHERE `learners`.`learnerid` = "+id;
	  
	  try {
		theStatement.executeUpdate(qry);
		System.out.println("Delete was successful");
	} catch (SQLException e) {
		System.out.println("Delete was not successful "+e.getMessage());
	}
  }
  
  
}
