package jan21;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsingUpdatablePreparedStmt {

	String qry;
	Connection dbCon;
	Statement theStatement;
	
	UsingUpdatablePreparedStmt(){

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
		new UsingUpdatablePreparedStmt().fetchAndUpdate();
	}
	
	void fetchAndUpdate() {
		qry = "select * from learners";
		
		try {
			PreparedStatement myPreparedStmt = dbCon.prepareStatement(qry, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			ResultSet myResultSet = myPreparedStmt.executeQuery();
			
			while(myResultSet.next()) {
				if(myResultSet.getInt("learnerid") == 4) {
					//update the current record
					myResultSet.updateString("learner_name", myResultSet.getString("learner_name") + " Priya");
					
					//commit changes
					myResultSet.updateRow();
					
					System.out.println("Row updated");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't get a reference to Statement : " + e.getMessage());
		}
	}

}
