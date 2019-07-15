package controller;

import java.util.ArrayList;
import java.util.Scanner;

import dao.AllService;
import model.Book;
import model.BookCopies;
import model.LibraryBranch;

public class LibrarianController {
	final static String INVALIDCHOICE = "Invalid choice, try again";
	static AllService allService = new AllService();

	static void runLibrarianLib1() {
		String librarianLib1Choice = "";
	    Scanner scan = new Scanner( System.in );

		System.out.println("1) Enter Branch you manage \n2)Quit to previous"); //printing it out
		librarianLib1Choice = scan.nextLine();
		switch(librarianLib1Choice) {
		case "1":
			System.out.println("What is the name of the branch you manage");
			//runLibraryLib2();
			System.out.println("Here is a list of the locations and branches we have");
			ArrayList<LibraryBranch> libArray = allService.getLibraryBranchService().displayAllLibraryBranchByNameAndAddress();
			for(int i = 0; i < libArray.size(); i ++) {
				System.out.println(i+1 + ") " + libArray.get(i).printNameAndAddress());
			}

			System.out.println("Please select the number of the branch");
			int num = Integer.parseInt(scan.nextLine());
			//System.out.println("HI THERE" + libArray.get(num-1));

			runLibraryLib3(libArray.get(num-1).getBranchName(), libArray.get(num-1).getBranchID() );
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
	    Scanner scan = new Scanner( System.in );

		System.out.println("1) Update the details of the Library +"
				+ "\n2)Add copies of Book to the Branch \n3) Quit to previous");
		String Lib3Choice = scan.nextLine();
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
	    Scanner scan = new Scanner( System.in );
	    System.out.println("You have chosen to update the branch with branchID " + branchID + " and Branch Name "+ LibraryName);
	    System.out.println("Please enter new branch name or enter N/A for no change");
		String newName = scan.nextLine();
	    //ask user for name
		if(!newName.equals("N/A")) {
			allService.getLibraryBranchService().updateLibraryBranchNameByID(branchID, newName);
		}
	    System.out.println("Please enter new branch address or enter N/A for no change");
	    //ask user for new branch address 
		String newAddress = scan.nextLine();
		if(!newAddress.equals("N/A")) {
			allService.getLibraryBranchService().updateLibraryBranchAddressByID(branchID, newAddress);
		}
	    System.out.println("it has been succesfully updated");
	}
	
	private static void runLibraryLib3AddCopies(int branchID) {
	    Scanner scan = new Scanner( System.in );
		System.out.println("Pick the Book you want to add copies of, to your branch" );
		ArrayList<Book> libArray = allService.getLibraryBranchService().displayAllBooksAndAuthorInABranchID(branchID);
		for(int i = 0; i < libArray.size(); i ++) {
			System.out.println(i+1 + ") " + libArray.get(i).printTitleAndAuthor());
		}
		int choiceOfBook = Integer.parseInt(scan.nextLine());
		Book tempBook = libArray.get(choiceOfBook -1);
		int bookID = tempBook.getBookId();
		BookCopies bookCopies = allService.getBookCopiesService().getBookCopiesByID(bookID, branchID);
		
		System.out.println("Existing number of copies: "+ bookCopies.getNoOfCopies());//if none show zero
		
		System.out.println("Enter new number of copies");
		int newCopies = Integer.parseInt(scan.nextLine());
		allService.getBookCopiesService().updateBookCopies(bookID, branchID, newCopies);

	}
}
