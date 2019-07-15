package controller;

import java.util.ArrayList;
import dao.AllService;
import model.Book;
import model.BookCopies;
import model.LibraryBranch;

public class LibrarianController {
	final static String INVALIDCHOICE = "Invalid choice, try again";
	static AllService allService = new AllService();

	static void runLibrarianLib1() {
		String librarianLib1Choice = "";
		System.out.println("1) Enter Branch you manage \n2)Quit to previous"); //printing it out
		librarianLib1Choice = InputHelper.userString();
		switch(librarianLib1Choice) {
		case "1":
			System.out.println("What is the name of the branch you manage");
			//runLibraryLib2();
			System.out.println("Here is a list of the locations and branches we have");
			ArrayList<LibraryBranch> libArray = allService.getLibraryBranchService().displayAllLibraryBranchByNameAndAddress();
			for(int i = 0; i < libArray.size(); i ++) {
				System.out.println(i + ") " + libArray.get(i).printNameAndAddress());
			}

			System.out.println("Please select the number of the branch");
			int num = InputHelper.checkInput(0,libArray.size()-1);
			runLibraryLib3(libArray.get(num).getBranchName(), libArray.get(num).getBranchID() );
			break;
		case "2":
			System.out.println("You choose to return back to main menu");
			return;
		default:
			System.out.println(INVALIDCHOICE);
		}

		
	}
//	private static void runLibraryLib2() {
//		System.out.println("Here is a list of the locations and branches we have");
//		allService.getLibraryBranchService().displayAllLibraryBranchByNameAndAddress();
//
//	}
	private static void runLibraryLib3(String branchName, int branchID) {

		System.out.println("1) Update the details of the Library"
				+ "\n2) Add copies of Book to the Branch \n3) Quit to previous");
		String Lib3Choice = InputHelper.userString();
		switch(Lib3Choice ) {
		case("1"):
			runLibraryLib3Update(branchName, branchID);
		break;
		case("2"):
			runLibraryLib3AddCopies(branchID);
		break;
		case("3"):
			System.out.println("You choose to quit");
		break;
		default:
			System.out.println("Invalid Choice");
		}
	}
	private static void runLibraryLib3Update(String LibraryName, int branchID) {
	    System.out.println("You have chosen to update the branch with branchID " + branchID + " and Branch Name "+ LibraryName);
	    System.out.println("Please enter new branch name or enter N/A for no change");
		String newName = InputHelper.userString();
	    //ask user for name
		if(!newName.equals("N/A")) {
			allService.getLibraryBranchService().updateLibraryBranchNameByID(branchID, newName);
		}
	    System.out.println("Please enter new branch address or enter N/A for no change");
	    //ask user for new branch address 
		String newAddress = InputHelper.userString();
		if(!newAddress.equals("N/A")) {
			allService.getLibraryBranchService().updateLibraryBranchAddressByID(branchID, newAddress);
		}
	    System.out.println("it has been succesfully updated");
	}
	
	private static void runLibraryLib3AddCopies(int branchID) {
		System.out.println("Pick the Book you want to add copies of, to your branch" );
		ArrayList<Book> libArray = allService.getLibraryBranchService().displayAllBooksAndAuthorInABranchID(branchID);
		for(int i = 0; i < libArray.size(); i ++) {
			System.out.println(i + ") " + libArray.get(i).printTitleAndAuthor());
		}
		int choiceOfBook = InputHelper.checkInput(0,libArray.size()-1);
		Book tempBook = libArray.get(choiceOfBook );
		int bookID = tempBook.getBookId();
		BookCopies bookCopies = allService.getBookCopiesService().getBookCopiesByID(bookID, branchID);
		
		System.out.println("Existing number of copies: "+ bookCopies.getNoOfCopies());//if none show zero
		
		System.out.println("Enter new number of copies");
		int newCopies = InputHelper.checkInput(0,999);
		allService.getBookCopiesService().updateBookCopies(bookID, branchID, newCopies);

	}
}
