package menu;

public class MenuImpl implements Menu {

	public void displayMainMenu() {
		System.out.println ( "\t\t\t\t\tMain Menu" );
	    System.out.println ( "***********************************************************************************************" );
	    System.out.println ( "\nOption 1 - Please Select Option 1 to enter new learner details" );
	    System.out.println ( "Option 2 - Please select Option 2 to display all learners with their details" );
	    System.out.println ( "Option 3 - Please select Option 3 to search for a learner by name or address" );
	    System.out.println ( "Option 4 - Please select Option 4 to update learner details");
	    System.out.println ( "Option 5 - Please select Option 5 to delete learner details");
	    System.out.println ( "Option 6 - Please select Option 6 to sort learner details");
	    System.out.println ( "Option 7 - Please select Option 7 to Exit");
	    System.out.print ( "Selection: " );
		
	}
	
	public void displaySubMenu1() {
		System.out.println ( "***********************************************************************************************" );
	    System.out.println ( "\t\t\t\tOption 2 : Sub Menu" );
	    System.out.println ( "***********************************************************************************************" );
		System.out.println ( "\nOption 1 - Please Select Option a search a Learner by First Name" );
	    System.out.println ( "Option 2 - Please select Option b to search a Learner by Address" );
	    System.out.print ( "Selection: " );
		
	}

	public void displaySubMenu2() {
		System.out.println ( "***********************************************************************************************" );
	    System.out.println ( "\t\t\t\tOption 2 : Sub Menu" );
	    System.out.println ( "***********************************************************************************************" );
		System.out.println ( "\nOption 1 - Please Select Option a to sort by First Name" );
	    System.out.println ( "Option 2 - Please select Option b to sort by Last Name" );
	    System.out.println ( "Option 3 - Please select Option c to sort by Address" );
	    System.out.println ( "Option 4 - Please select Option d to sort by Id" );
	    System.out.print ( "Selection: " );
		
	}
	

}

