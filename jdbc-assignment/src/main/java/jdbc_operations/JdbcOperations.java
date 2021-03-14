package jdbc_operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.LearnerDto;

public class JdbcOperations {
	String qry;
	Connection dbCon;
	Statement theStatement;
	
	void preJdbcOperations(){

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
	
	//Insert new record using PreparedStatement
		public void addNewLearner(String learnerFirstName, String learnerLastName, String learnerAddress) {
			
			preJdbcOperations();
			qry = "insert into learners_new(learner_first_name, learner_last_name, learner_address) values (?,?,?)";
			
			// get reference to the PreparedStatement
			try {
				PreparedStatement thePreparedStmt = dbCon.prepareStatement(qry);
				
				//set the values for the PreparedStatement
				thePreparedStmt.setString(1, learnerFirstName);
				thePreparedStmt.setString(2, learnerLastName);
				thePreparedStmt.setString(3, learnerAddress);
				
				if(thePreparedStmt.executeUpdate()>0) {
					System.out.println("Record added");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Can't get a reference to Statement : " + e.getMessage());
			}
			
		}
		
		//Update new record using PreparedStatement
		public void updateLearner(int id, String learnerFirstName, String learnerLastName, String learnerAddress) {
			
			preJdbcOperations();
			qry = "UPDATE learners_new SET learner_first_name = ?, learner_last_name = ?, learner_address = ? WHERE learnerid = ?";
			
			try {
				PreparedStatement thePreparedStmt = dbCon.prepareStatement(qry);
				
				//set the values for the PreparedStatement
				thePreparedStmt.setInt(4, id);
				thePreparedStmt.setString(1, learnerFirstName);
				thePreparedStmt.setString(2, learnerLastName);
				thePreparedStmt.setString(3, learnerAddress);
				
				if(thePreparedStmt.executeUpdate()>0) {
					System.out.println("Record updated");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Can't get a reference to Statement : " + e.getMessage());
			}
			
		}
		
		//Update new record using PreparedStatement
		public void deleteLearner(int id) {
			
			preJdbcOperations();
			qry = "DELETE FROM `learners_new` WHERE `learners_new`.`learnerid` = ?";
			
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
		public void getAllRecords() {
			
			qry = "select * from learners_new";
			
			// get reference to the PreparedStatement
			try {
				PreparedStatement thePreparedStmt = dbCon.prepareStatement(qry);
				
				ResultSet theResultSet = thePreparedStmt.executeQuery();
				
				while(theResultSet.next()) {
					System.out.println("First Name = "+theResultSet.getString("learner_first_name"));
					System.out.println("Last Name = "+theResultSet.getString("learner_last_name"));
					System.out.println("Address = "+theResultSet.getString("learner_address"));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Can't get a reference to Statement : " + e.getMessage());
			}

		}
		
		//Fetch Learner details by First Name
		public void getRecordbyFirstName(String firstName) {
			
			System.out.println("First Name "+firstName);
			
			qry = "select * from `learners_new` WHERE `learners_new`.`learner_first_name` = ?";
			
			// get reference to the PreparedStatement
			try {
				PreparedStatement thePreparedStmt = dbCon.prepareStatement(qry);
				
				//set the values for the PreparedStatement
				thePreparedStmt.setString(1, firstName);
				
				ResultSet theResultSet = thePreparedStmt.executeQuery();
				
				if(theResultSet.getFetchSize()>0) {
					while(theResultSet.next()) {
						System.out.println("Learner Id = "+theResultSet.getInt("learnerid"));
						System.out.println("First Name = "+theResultSet.getString("learner_first_name"));
						System.out.println("Last Name = "+theResultSet.getString("learner_last_name"));
						System.out.println("Address = "+theResultSet.getString("learner_address"));
					}
				} else {
					System.out.println("There is no Learner with this First Name");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Can't get a reference to Statement : " + e.getMessage());
			}

		}
		
		//Fetch Learner details by First Name
		public void getRecordbyAddress(String address) {
			
			qry = "select * from learners_new where learner_address = ?";
			
			// get reference to the PreparedStatement
			try {
				PreparedStatement thePreparedStmt = dbCon.prepareStatement(qry);
				
				//set the values for the PreparedStatement
				thePreparedStmt.setString(1, address);
				
				ResultSet theResultSet = thePreparedStmt.executeQuery();
				
				if(theResultSet.getFetchSize()>0) {
					while(theResultSet.next()) {
						System.out.println("Learner Id = "+theResultSet.getInt("learnerid"));
						System.out.println("First Name = "+theResultSet.getString("learner_first_name"));
						System.out.println("Last Name = "+theResultSet.getString("learner_last_name"));
						System.out.println("Address = "+theResultSet.getString("learner_address"));
					}
				} else {
					System.out.println("There is no Learner with this Address");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Can't get a reference to Statement : " + e.getMessage());
			}

		}
		
		//Sort All Learners
		public void sortAllRecords(String str) {
			
			qry = "SELECT * FROM learners_new ORDER BY "+str+" ASC";
			
			// get reference to the PreparedStatement
			try {
				PreparedStatement thePreparedStmt = dbCon.prepareStatement(qry);
				
				ResultSet theResultSet = thePreparedStmt.executeQuery();
				
				while(theResultSet.next()) {
					System.out.println("Learner Id = "+theResultSet.getInt("learnerid"));
					System.out.println("First Name = "+theResultSet.getString("learner_first_name"));
					System.out.println("Last Name = "+theResultSet.getString("learner_last_name"));
					System.out.println("Address = "+theResultSet.getString("learner_address"));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Can't get a reference to Statement : " + e.getMessage());
			}

		}
		
		//Sort All Learners
		public void sortAllRecordsByLearnerId(int id) {
			
			qry = "SELECT * FROM learners_new ORDER BY "+id+" ASC";
			
			// get reference to the PreparedStatement
			try {
				PreparedStatement thePreparedStmt = dbCon.prepareStatement(qry);
				
				ResultSet theResultSet = thePreparedStmt.executeQuery();
				
				while(theResultSet.next()) {
					System.out.println("Learner Id = "+theResultSet.getInt("learnerid"));
					System.out.println("First Name = "+theResultSet.getString("learner_first_name"));
					System.out.println("Last Name = "+theResultSet.getString("learner_last_name"));
					System.out.println("Address = "+theResultSet.getString("learner_address"));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Can't get a reference to Statement : " + e.getMessage());
			}

		}

}
