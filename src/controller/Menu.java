package controller;

import dao.AllService;

public class Menu {
	//main Strings
	static final String GCITMENU = "1) Librarian \n2) Administrator \n3) Borrower \n4) Quit";
	final static String INVALIDCHOICE = "Invalid choice, try again";
	static AllService allService = new AllService();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean run = true;
		String mainMenuChoice = "";
		while(run) {
			System.out.println(GCITMENU);
			mainMenuChoice = InputHelper.userString();
			switch(mainMenuChoice) {
			case "1": //Librarian
				LibrarianController.runLibrarianLib1();
				break;
			case "2": //admin
				AdminstratorController.runAdmin();
				break;
			case "3":
				BorrowerController.runBorrower();
				break;
			case "4":
				run = false;
				System.out.println("You choose quit");
				break;
			default:
				System.out.println(INVALIDCHOICE);
			}
		}
	}
}
