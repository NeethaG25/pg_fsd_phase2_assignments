package demo;

import java.util.Scanner;

import jdbc_operations.JdbcOperations;
import menu.Menu;
import menu.MenuImpl;

public class Welcome {
	
public static void main(String[] args) {
		

		Menu menu = new MenuImpl();	
		JdbcOperations myJdbc = new JdbcOperations();
		
		int mainMenuChoice, subMenuChoice1, subMenuChoice2; 
		int id;
		String firstName;
		String lastName;
		String address;
		
		Scanner in = new Scanner ( System.in );
		menu.displayMainMenu();
		mainMenuChoice = in.nextInt();	
		
		while (mainMenuChoice < 1 || mainMenuChoice > 7) {
	        System.out.print("\nError! Incorrect choice.\n");
	        menu.displayMainMenu();
	        mainMenuChoice = in.nextInt();
	    }
		
		do {

			switch (mainMenuChoice) {
		      case 1:
		        System.out.println ( "You picked option 1" );
		        System.out.println ( "Please enter the Learner's First Name" );
		        in.nextLine();
		        firstName = in.nextLine();
		        System.out.println ( "Please enter the Learner's Last Name" );
		        lastName = in.nextLine();
		        System.out.println ( "Please enter the Learner's Address" );
		        address = in.nextLine();
		        myJdbc.addNewLearner(firstName, lastName, address);

		        menu.displayMainMenu();
		        mainMenuChoice = in.nextInt();
		        break;
		      case 2:
			        System.out.println ( "You picked option 2" );
			        System.out.println ( "List of all the Learners" );
			        myJdbc.getAllRecords();
			        
			        menu.displayMainMenu();
			        mainMenuChoice = in.nextInt();
			        break;
		      case 3:
			        System.out.println ( "You picked option 3" );
			        menu.displaySubMenu1();
			        in.nextLine();
			        subMenuChoice1 = in.nextInt();
			        switch (subMenuChoice1) {
			        case 1:
			        	System.out.println("Please enter the First Name of the Learner");
			        	in.nextLine();
			        	firstName = in.nextLine();
			        	myJdbc.getRecordbyFirstName(firstName);
			        	break;
			        case 2:
			        	System.out.println("Please enter the Address of the Learner");
			        	in.nextLine();
			        	address = in.nextLine();
			        	myJdbc.getRecordbyAddress(address);
			        	break;
			        	
			        }
			        
			        menu.displayMainMenu();
			        mainMenuChoice = in.nextInt();
			        break;
		      case 4:
			        System.out.println ( "You picked option 4" );
			        System.out.println ( "Please enter the Learner's Id to update the Learners details" );
			        in.nextLine();
			        id = in.nextInt();
			        System.out.println ( "Please enter the Learner's First Name" );
			        in.nextLine();
			        firstName = in.nextLine();
			        System.out.println ( "Please enter the Learner's Last Name" );
			        lastName = in.nextLine();
			        System.out.println ( "Please enter the Learner's Address" );
			        address = in.nextLine();
			        myJdbc.updateLearner(id, firstName, lastName, address);

			        menu.displayMainMenu();
			        mainMenuChoice = in.nextInt();
			        break;
		      case 5:
			        System.out.println ( "You picked option 5" );
			        System.out.println ( "Please enter the Learner's Id to Delete the Learners details" );
			        in.nextLine();
			        id = in.nextInt();
			        
			        myJdbc.deleteLearner(id);

			        menu.displayMainMenu();
			        mainMenuChoice = in.nextInt();
			        break;
		      case 6:
			        System.out.println ( "You picked option 6" );
			        menu.displaySubMenu2();
			        in.nextLine();
			        subMenuChoice2 = in.nextInt();
			        switch (subMenuChoice2) {
			        case 1:

			        	System.out.println("List of Learners sorted by First Name");
			        	myJdbc.sortAllRecords("learner_first_name");
			        	break;
			        case 2:
			        	System.out.println("List of Learners sorted by Last Name");
			        	myJdbc.sortAllRecords("learner_last_name");
			        	break;
			        case 3:
			        	System.out.println("List of Learners sorted by Address");
			        	myJdbc.sortAllRecords("learner_address_name");
			        	break;
			        case 4:
			        	System.out.println("List of Learners sorted by Learner Id");
			        	myJdbc.sortAllRecords("learnerid");
			        	break;
			        	
			        }
			        
			        menu.displayMainMenu();
			        mainMenuChoice = in.nextInt();
			        break;
		      case 7:
		        System.out.println ( "You picked option 7, exiting the Application" );
		        System.exit(0);
		        break;
		      default:
		        System.err.println ( "Unrecognized option" );
		        break;
		    }
		} while (mainMenuChoice != 0);
		
		in.close();

	}

}
