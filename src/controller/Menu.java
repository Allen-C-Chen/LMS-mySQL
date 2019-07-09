package controller;

import java.util.ArrayList;
import java.util.Scanner;

import dao.AllService;
import model.Author;
import model.Book;
import model.BookCopies;
import model.BookLoans;
import model.Borrower;
import model.LibraryBranch;
import model.Publisher;
import services.BorrowerService;
import services.LibraryBranchService;

public class Menu {
	//main Strings
	static final String GCITMENU = "1) Librarian \n2) Administrator \n3) Borrower \n4) Quit";
	
	//Librarian strings
	//lib1
	//final static String LIB1 = "1) Enter Branch you manage \nQuit to previous";
	
	//final static String LIB3 = "1) Update the details of the Library \n2)Add copies of Book to the Branch \n3) Quit to previous";
	final static String INVALIDCHOICE = "Invalid choice, try again";
	static AllService allService = new AllService();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    Scanner scan = new Scanner( System.in );
		boolean run = true;
		String mainMenuChoice = "";
		while(run) {
			System.out.println(GCITMENU);
			mainMenuChoice = scan.nextLine();
			switch(mainMenuChoice) {
			case "1": //Librarian
				runLibrarianLib1();
				break;
			case "2": //admin
				//runLibrary_Branch();
				runAdmin();
				break;
			case "3":
				runBorrower();
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
	private static void runLibrarianLib1() {
		String librarianLib1Choice = "";
		String librarianLib2Choice = "";
		String librarianLib3Choice = "";

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
	private static void runLibraryLib2() {
		System.out.println("Here is a list of the locations and branches we have");
		allService.getLibraryBranchService().displayAllLibraryBranchByNameAndAddress();

	}
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

		
	    String lib3ChoiceUpdate = "";
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
		
		System.out.println("Existing number of copies: "+ bookCopies.getNoOfCopies());//if none zhow zero
		
		System.out.println("Enter new number of copies");
		int newCopies = Integer.parseInt(scan.nextLine());
		allService.getBookCopiesService().updateBookCopies(bookID, branchID, newCopies);
		//ask for new number
		//take user back to Lib3
	}
	//borrower functions
	private static void runBorrower() {
		BorrowerService borrowerService = new BorrowerService();
	    Scanner scan = new Scanner( System.in );
		System.out.println("Please enter your borrower card number");
		//String cardNo = Integer.toString(scan.int());
		//int cardNumber = Integer.parseInt(cardNo);
		int cardNumber = Integer.parseInt(scan.nextLine());;
		Borrower tempBorrower = new Borrower();
		while(tempBorrower.getCardNo() == 0) {
			tempBorrower = borrowerService.checkID(cardNumber);
			if(tempBorrower.getCardNo() == 0) {
				System.out.println("please try again");
				cardNumber = Integer.parseInt(scan.nextLine());
				tempBorrower = borrowerService.checkID(cardNumber);
			}
		}
		System.out.println("that is a valid number");
		runBorrowerBORR1(cardNumber);
	}
	private static void runBorrowerBORR1(int cardNo) {
	    Scanner scan = new Scanner( System.in );
		System.out.println("1) Check out a book"
				+ "\n2) Return A book"
				+ "\n3) Quit to previous");
		String borrowerChoice = scan.nextLine();
		switch(borrowerChoice) {
		case("1"):
			runBorrowerCheckOutBook(cardNo);
		break;
		case("2"):
			runBorrowerReturnBook(cardNo);
		break;
		case("3"):
			System.out.println("you choose to quit");
		break;
		default:
			System.out.println("Invalid choice");
			break;
		}
	}
	private static void runBorrowerCheckOutBook(int cardNo) {
	    Scanner scan = new Scanner( System.in );
		System.out.println("Here is a list of the locations and branches we have");
		ArrayList<LibraryBranch> libArray = allService.getLibraryBranchService().displayAllLibraryBranchByNameAndAddress();
		for(int i = 0; i < libArray.size(); i ++) {
			System.out.println(i+1 + ") " + libArray.get(i).printNameAndAddress());
		}
		
		System.out.println("Pick the Branch you want to check out from");
		//display all
		System.out.println("Please choose the number" );
		int number = Integer.parseInt(scan.nextLine());
		LibraryBranch libraryBranch = libArray.get(number-1);
		int branchID = libraryBranch.getBranchID(); //get the branch ID
		//from the list, display all books in that lib
				
		ArrayList<Book> books = allService.getLibraryBranchService().displayAllBooksAndAuthorInABranchID(branchID);
		for(int i = 0; i < books.size(); i ++) {
			System.out.println(i+1 + ") " + books.get(i).printTitleAndAuthor());
		}
		
		//String stringChoiceOfBook = Integer.toString(scan.int());
		int choiceOfBOok = Integer.parseInt(scan.nextLine());
		Book tempBook = books.get(choiceOfBOok -1);

		int bookID = tempBook.getBookId(); //get the book ID
		allService.getBookLoanService().checkOutBook(branchID, bookID, cardNo);
		//exiting takes me to BORR1
	}
	private static void runBorrowerReturnBook(int cardNo) {
	    Scanner scan = new Scanner( System.in );

		System.out.println("Here are the books onLoan");
		ArrayList<BookLoans> bookLoanss = allService.getBookLoanService().getAllListBybyCardNo(cardNo);
		for(int i = 0; i < bookLoanss.size(); i ++) {
			System.out.println(i+1 + ") " + bookLoanss.get(i).toString());
		}
		System.out.println("Please choose the number" );
		int number = Integer.parseInt(scan.nextLine());
		BookLoans bookLoans = bookLoanss.get(number-1);
		int branchID = bookLoans.getLibraryBranch().getBranchID();
		int bookID = bookLoans.getBook().getBookId();
		allService.getBookLoanService().checkInBook(branchID, bookID, cardNo);
	}
	private static void runAdmin() {
	    Scanner scan = new Scanner( System.in );

		boolean run = true;
		System.out.println(""
				+ "1) Add/Update/Delete Book and Author\r\n" + 
				"2) Add/Update/Delete Publishers \r\n" + 
				"3) Add/Update/Delete Library Branches\r\n" + 
				"4) Add/Update/Delete Borrowers\r\n" + 
				"5) Over-ride Due Date for a Book Loan\n");
		String adminChoice = scan.nextLine();
		switch(adminChoice) {
		case("1"):
			adminBookAuthorChoice();
			break;
		case("2"):
			adminAddUpdateDeletePublisher();

			break;
		case("3"):
			adminAddUpdateDeleteLibraryBranch();
			break;
		case("4"):
			adminAddUpdateDeleteBorrowers();
			break;
		case("5"): //check out date need to do
			overRideCheckOut();
			break;
		
		}
	
	}
	private static void overRideCheckOut() {
		// TODO Auto-generated method stub
	    Scanner scan = new Scanner( System.in );

		ArrayList<BookLoans>  newList = allService.getBookLoanService().getAllList();
		for(int i = 0; i < newList.size(); i ++) {
			System.out.println(i + ") " + newList.get(i));
		}
		System.out.println("Please Choose a number");
		int choiceOfBookLoans = Integer.parseInt(scan.nextLine());
		BookLoans newBookLoans = newList.get(choiceOfBookLoans -1);
		int bookID = newBookLoans.getBook().getBookId();
		int branchID = newBookLoans.getLibraryBranch().getBranchID();
		int cardNo = newBookLoans.getBorrower().getCardNo();
		System.out.println("The Due date is ");
		System.out.println(newBookLoans.getDueDate());
		System.out.println("How many weeks would you like to extend it by");
		int numOfWeeks = Integer.parseInt(scan.nextLine());
		allService.getBookLoanService().adminOverRide(numOfWeeks, bookID, branchID, cardNo);
		//display all books checked out
		//use function
		//win
		
	}
	public static void adminBookAuthorChoice() {
	    Scanner scan = new Scanner( System.in );
	    System.out.println("Please choose book or author");
	    System.out.println("\n1) book");
	    System.out.println("\n2) author");
	    String choice = scan.nextLine();
	   switch(choice) {
		case("1"): //book
			adminAddUpdateDeleteBook();			
			break;
		case("2"): //author
			adminAddUpdateDeletePublisherAuthor();
			break;
	   }
	    //add book (bookID, title, authorID, PubID) // title, author, pub   
	}
	
	public static void adminAddUpdateDeleteBook() { //1
	    Scanner scan = new Scanner( System.in );
		System.out.println("You choose Book");
		System.out.println("Please choose:" 
				+ "\n1) Add"
				+ "\n2) Update" 
				+ "\n3) Delete");
		String crudChoice = scan.nextLine();
		switch(crudChoice) {
		case("1"):
			System.out.println("What is the name of the Book you are adding");
			String bookName = scan.nextLine();
			System.out.println("What is the name of the Author you are adding");
			String authorName = scan.nextLine();
			System.out.println("What is the name of the Publisher you are adding");
			String publisherName = scan.nextLine();
			//work on this later
			break;
		case("2"):
			ArrayList<Book>  newList = allService.getBookService().getListOfBooks();
			for(int i = 0; i < newList.size(); i ++) {
				System.out.println(i + ") " + newList.get(i));
			}
			System.out.println("Please Choose a number");
			int choiceOfBook = Integer.parseInt(scan.nextLine());
			Book newBook = newList.get(choiceOfBook -1);
			int bookID = newBook.getBookId();
			
			System.out.println("What is the new name of the Book");
			String newBookTitle = scan.nextLine();
			allService.getBookService().updateBooks(bookID, newBookTitle); //check
			break;
		case("3"):
			System.out.println("What is the name of the Book you are removing");
			ArrayList<Book>  newListRemove = allService.getBookService().getListOfBooks();
			for(int i = 0; i < newListRemove.size(); i ++) {
				System.out.println(i + ") " + newListRemove.get(i));
			}
			System.out.println("Please Choose a number");
			int choiceOfBookRemove = Integer.parseInt(scan.nextLine());
			Book newBookRemove = newListRemove.get(choiceOfBookRemove -1);
			int bookIDRemove = newBookRemove.getBookId();
			allService.getBookService().removeBookByID(bookIDRemove);
			break;
		}
	}
	public static void adminAddUpdateDeletePublisherAuthor() { //1
	    Scanner scan = new Scanner( System.in );
		System.out.println("You choose Author");
		System.out.println("Please choose:" 
				+ "\n1) Add"
				+ "\n2) Update" 
				+ "\n3) Delete");
		String crudChoice = scan.nextLine();
		switch(crudChoice) {
		case("1"):
			System.out.println("What is the name of the Author you are adding");
			String name = scan.nextLine();
			allService.getAuthorService().addAuthorByName(name);
			break;
		case("2"): //update
			ArrayList<Author>  newList = allService.getAuthorService().getAllAuthors();
			for(int i = 0; i < newList.size(); i ++) {
				System.out.println(i + ") " + newList.get(i).getAuthorName());
			}
			System.out.println("Please choose a number for author name");
			int choiceOfAuthor = Integer.parseInt(scan.nextLine());
			Author tempAuthor = newList.get(choiceOfAuthor -1);
			int authorID =tempAuthor.getAuthorId();
			System.out.println("What is the new name of the Author");
			String newName = scan.nextLine();
			allService.getAuthorService().updateByAuthorID(authorID, newName);
			//new name stuff
			break;
		case("3"):
			System.out.println("What is the name of the Author you are removing");
			ArrayList<Author>  removeNewList = allService.getAuthorService().getAllAuthors();
			for(int i = 0; i < removeNewList.size(); i ++) {
				System.out.println(i + ") " + removeNewList.get(i).getAuthorName());
			}
			System.out.println("Please choose a number for author name");
			int choiceOfAuthorRemove = Integer.parseInt(scan.nextLine());
			Author tempAuthorRemove = removeNewList.get(choiceOfAuthorRemove -1);
			int authorIDRemove =tempAuthorRemove.getAuthorId();		
			break;
		}
	}
	
	public static void adminAddUpdateDeletePublisher() { //2
	    Scanner scan = new Scanner( System.in );
		System.out.println("You choose Publisher");
		
		System.out.println("Please choose:" 
				+ "\n1) Add"
				+ "\n2) Update" 
				+ "\n3) Delete");
		String crudChoice = scan.nextLine();
		switch(crudChoice) {
		case("1"): //add
			System.out.println("What is the name of the publisher you are adding");
			String name = scan.nextLine();
			System.out.println("What is the address of the publisher you are adding");
			String address = scan.nextLine();
			System.out.println("What is the phone of the publisher you are adding");
			String phone = scan.nextLine();
			allService.getPublisherService().addPublisher(name, address, phone);
			break;
		case("2"): //update
			System.out.println("What is the name of the publisher you are updating");
			ArrayList<Publisher>  newList = allService.getPublisherService().getList();
			for(int i = 0; i < newList.size(); i ++) {
				System.out.println(i + ") " + newList.get(i));
			}
			int choiceOfPublisher = Integer.parseInt(scan.nextLine());
			Publisher tempPublisher = newList.get(choiceOfPublisher -1);
			int publisherID = tempPublisher.getPublisherId();
			System.out.println("What is the new name of the publisher you are adding");
			String nameUpdate = scan.nextLine();
			System.out.println("What is the new address of the publisher you are adding");
			String addressUpdate = scan.nextLine();
			System.out.println("What is the new phone of the publisher you are adding");
			String phoneUpdate = scan.nextLine();
			allService.getPublisherService().updatePublisherAllByID(publisherID, nameUpdate, addressUpdate, phoneUpdate);
			break;
			case("3"):
			System.out.println("What is the name of the pubisher you are removing");
			ArrayList<Publisher>  removeNewList = allService.getPublisherService().getList();
			for(int i = 0; i < removeNewList.size(); i ++) {
				System.out.println(i + ") " + removeNewList.get(i));
			}
			int choiceOfPublisherRemove = Integer.parseInt(scan.nextLine());
			Publisher tempPublisherRemov = removeNewList.get(choiceOfPublisherRemove -1);
			int publisherIDRemove = tempPublisherRemov.getPublisherId();
			allService.getPublisherService().removePublisherByID(publisherIDRemove);
			break;
		}
	}
	public static void adminAddUpdateDeleteLibraryBranch() { //3
	    Scanner scan = new Scanner( System.in );
		System.out.println("You choose Library Branch");
		System.out.println("Please choose:" 
				+ "\n1) Add"
				+ "\n2) Update" 
				+ "\n3) Delete");
		String crudChoice = scan.nextLine();
		switch(crudChoice) {
		case("1"): //add
			System.out.println("What is the name of the Library Branch you are adding");
			String nameAdd = scan.nextLine();
			System.out.println("what is the address of the branch you are adding");
			String addressAdd = scan.nextLine();
			allService.getLibraryBranchService().addLibraryBranch(nameAdd, addressAdd);
			break;
		case("2"): //update
			ArrayList<LibraryBranch>  newList = allService.getLibraryBranchService().displayAllLibraryBranchByNameAndAddress();
			for(int i = 0; i < newList.size(); i ++) {
				System.out.println(i + ") " + newList.get(i));
			}
			System.out.println("please choose a number");
			int choiceOfLibBranch = Integer.parseInt(scan.nextLine());
			LibraryBranch libraryBranch = newList.get(choiceOfLibBranch -1);
			int libraryBranchID = libraryBranch.getBranchID();

			System.out.println("What is the new name of the Library Branch");
			String nameUpdate = scan.nextLine();
			System.out.println("What is the new address");
			String addressUpdate = scan.nextLine();
			allService.getLibraryBranchService().updateLibraryBranchAddressByID(libraryBranchID, addressUpdate);
			allService.getLibraryBranchService().updateLibraryBranchNameByID(libraryBranchID, nameUpdate);
			break;
		case("3"): //delete
			System.out.println("What is the name of the Library Branch you are removing");
			ArrayList<LibraryBranch>  removeList = allService.getLibraryBranchService().displayAllLibraryBranchByNameAndAddress();
			for(int i = 0; i < removeList.size(); i ++) {
				System.out.println(i + ") " + removeList.get(i));
			}
			System.out.println("please choose a number");
			int choiceOfLibBranchRemove = Integer.parseInt(scan.nextLine());
			LibraryBranch libraryBranchRemove = removeList.get(choiceOfLibBranchRemove -1);
			int libraryBranchIDRemove = libraryBranchRemove.getBranchID();
			allService.getLibraryBranchService().removeLibraryID(libraryBranchIDRemove);
			break;
		}
	}
	
	public static void adminAddUpdateDeleteBorrowers() { //4
	    Scanner scan = new Scanner( System.in );
		System.out.println("You choose Borrowers");
		System.out.println("Please choose:" 
				+ "\n1) Add"
				+ "\n2) Update" 
				+ "\n2) Delete");
		String crudChoice = scan.nextLine();
		switch(crudChoice) {
		case("1"):
			System.out.println("What is the name of the Borrowers you are adding");
			String name = scan.nextLine();
			System.out.println("What is the address of the Borrowers you are adding");
			String address = scan.nextLine();
			System.out.println("What is the phone number of the Borrowers you are adding");
			String phoneNumber = scan.nextLine();
			allService.getBorrowerService().addBorrower(name, address, phoneNumber);
			break;
		case("2"):
			ArrayList<Borrower>  newList = allService.getBorrowerService().getAllList();
			for(int i = 0; i < newList.size(); i ++) {
				System.out.println(i + ") " + newList.get(i));
			}
			System.out.println("please choose a number");
			int choiceOfBorrower = Integer.parseInt(scan.nextLine());
			Borrower borrower = newList.get(choiceOfBorrower -1);
			int borrowerID = borrower.getCardNo();
			System.out.println("What is the new name of the Borrowers");
			String borrowerName = scan.nextLine();
			System.out.println("What is the new address of the Borrowers");
			String borrowerAddress = scan.nextLine();
			System.out.println("What is the new phone of the Borrowers");
			String borrowerPhone = scan.nextLine();
			allService.getBorrowerService().updateAll(borrowerID, borrowerName, borrowerAddress, borrowerPhone);
			break;
		case("3"):
			System.out.println("What is the name of the Borrowers you are removing");
			ArrayList<Borrower>  newListRemove = allService.getBorrowerService().getAllList();
			for(int i = 0; i < newListRemove.size(); i ++) {
				System.out.println(i + ") " + newListRemove.get(i));
			}
			System.out.println("please choose a number");
			int choiceOfBorrowerRemove = Integer.parseInt(scan.nextLine());
			Borrower borrowerRemove = newListRemove.get(choiceOfBorrowerRemove -1);
			int borrowerIDRemove = borrowerRemove.getCardNo();
			allService.getBorrowerService().deleteBorrowerByID(borrowerIDRemove);
			break;
		}
	}
}
