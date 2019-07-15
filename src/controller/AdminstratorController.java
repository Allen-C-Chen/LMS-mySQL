package controller;

import java.util.ArrayList;
import java.util.Scanner;

import dao.AllService;
import model.Author;
import model.Book;
import model.BookLoans;
import model.Borrower;
import model.LibraryBranch;
import model.Publisher;

public class AdminstratorController {
	final static String INVALIDCHOICE = "Invalid choice, try again";
	static AllService allService = new AllService();
	
	static void runAdmin() {
	    Scanner scan = new Scanner( System.in );

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
		int choiceOfBookLoans =Integer.parseInt(scan.nextLine());
		BookLoans newBookLoans = newList.get(choiceOfBookLoans -1);
		int bookID = newBookLoans.getBook().getBookId();
		int branchID = newBookLoans.getLibraryBranch().getBranchID();
		int cardNo = newBookLoans.getBorrower().getCardNo();
		System.out.println("The Due date is ");
		System.out.println(newBookLoans.getDueDate());
		System.out.println("How many weeks would you like add");
		int numOfWeeks = Integer.parseInt(scan.nextLine());
		allService.getBookLoanService().adminOverRide(numOfWeeks+1, bookID, branchID, cardNo);

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
			//work on this later **************
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
			//int authorIDRemove =tempAuthorRemove.getAuthorId();		
			tempAuthorRemove.getAuthorId();	
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
