package controller;

import java.util.ArrayList;
import java.util.stream.IntStream;

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
		System.out.println(""
				+ "1) Add/Update/Delete Book and Author\r\n" + 
				"2) Add/Update/Delete Publishers \r\n" + 
				"3) Add/Update/Delete Library Branches\r\n" + 
				"4) Add/Update/Delete Borrowers\r\n" + 
				"5) Over-ride Due Date for a Book Loan\n");
		String adminChoice = InputHelper.userString();
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
		ArrayList<BookLoans>  newList = allService.getBookLoanService().getAllList();
		IntStream.range(0, newList.size())
		.forEach(index -> System.out.println(index + " -> " + newList.get(index)));

		System.out.println("Please Choose a number");
		int choiceOfBookLoans =InputHelper.checkInput(0,newList.size()-1);
		BookLoans newBookLoans = newList.get(choiceOfBookLoans);
		int bookID = newBookLoans.getBook().getBookId();
		int branchID = newBookLoans.getLibraryBranch().getBranchID();
		int cardNo = newBookLoans.getBorrower().getCardNo();
		System.out.println("The Due date is ");
		System.out.println(newBookLoans.getDueDate());
		System.out.println("How many weeks would you like add");
		int numOfWeeks = InputHelper.checkInput(0,99);
		allService.getBookLoanService().adminOverRide(numOfWeeks+1, bookID, branchID, cardNo);

	}
	public static void adminBookAuthorChoice() {
	    System.out.println("Please choose book or author");
	    System.out.println("\n1) book");
	    System.out.println("\n2) author");
	    String choice = InputHelper.userString();
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
		System.out.println("You choose Book");
		System.out.println("Please choose:" 
				+ "\n1) Add"
				+ "\n2) Update" 
				+ "\n3) Delete");
		String crudChoice = InputHelper.userString();
		switch(crudChoice) {
		case("1"): //add
			//first get book title

			System.out.println("What is the name of the Book you are adding");
			String bookName = InputHelper.userString();
			//work on this later **************
			//can not add a book without an author in the data base , same with publisher
			//second display all authors
			ArrayList<Author> authors = allService.getAuthorService().getAllAuthors();
			IntStream.range(0, authors.size())
			.forEach(index -> System.out.println(index + " -> " + authors.get(index)));
			
			System.out.println("Please choose an author from the list");
			int choiceOfAuthor =InputHelper.checkInput(0,authors.size()-1);
			Author newAuthor = authors.get(choiceOfAuthor); //no errro detection
			//third display all publishers
			ArrayList<Publisher> publishers = allService.getPublisherService().getList();

			IntStream.range(0, publishers.size())
			.forEach(index -> System.out.println(index + " -> " + publishers.get(index)));
			
			System.out.println("Please choose an publisher from the list");
			int choiceOfPublisher =InputHelper.checkInput(0,publishers.size()-1);
			Publisher newPublisher = publishers.get(choiceOfPublisher);
			
			allService.getBookService().addBook(bookName, newAuthor.getAuthorId(), newPublisher.getPublisherId());
			break;
		case("2"): //update
			ArrayList<Book>  newList = allService.getBookService().getListOfBooks();
//			for(int i = 0; i < newList.size(); i ++) {
//				System.out.println(i + ") " + newList.get(i));
//			}
			IntStream.range(0, newList.size())
			.forEach(index -> System.out.println(index + " -> " + newList.get(index)));
			
			System.out.println("Please Choose a number");
			int choiceOfBook = InputHelper.checkInput(0,newList.size()-1);
			Book newBook = newList.get(choiceOfBook);
			int bookID = newBook.getBookId();
			
			System.out.println("What is the new name of the Book");
			String newBookTitle = InputHelper.userString();
			allService.getBookService().updateBooks(bookID, newBookTitle); //check
			break;
		case("3"): //delete
			System.out.println("What is the name of the Book you are removing");
			ArrayList<Book>  newListRemove = allService.getBookService().getListOfBooks();

			IntStream.range(0, newListRemove.size())
			.forEach(index -> System.out.println(index + " -> " + newListRemove.get(index)));
			
			System.out.println("Please Choose a number");
			int choiceOfBookRemove = InputHelper.checkInput(0,newListRemove.size()-1);
			Book newBookRemove = newListRemove.get(choiceOfBookRemove);
			int bookIDRemove = newBookRemove.getBookId();
			allService.getBookService().removeBookByID(bookIDRemove);
			break;
		}
	}
	public static void adminAddUpdateDeletePublisherAuthor() { //1
		System.out.println("You choose Author");
		System.out.println("Please choose:" 
				+ "\n1) Add"
				+ "\n2) Update" 
				+ "\n3) Delete");
		String crudChoice = InputHelper.userString();
		switch(crudChoice) {
		case("1"):
			System.out.println("What is the name of the Author you are adding");
			String name = InputHelper.userString();
			allService.getAuthorService().addAuthorByName(name);
			break;
		case("2"): //update
			ArrayList<Author>  newList = allService.getAuthorService().getAllAuthors();

			IntStream.range(0, newList.size())
			.forEach(index -> System.out.println(index + " -> " + newList.get(index)));
			
			System.out.println("Please choose a number for author name");
			int choiceOfAuthor = InputHelper.checkInput(0,newList.size()-1);
			Author tempAuthor = newList.get(choiceOfAuthor);
			int authorID =tempAuthor.getAuthorId();
			System.out.println("What is the new name of the Author");
			String newName = InputHelper.userString();
			allService.getAuthorService().updateByAuthorID(authorID, newName);
			//new name stuff
			break;
		case("3"):
			System.out.println("What is the name of the Author you are removing");
			ArrayList<Author>  removeNewList = allService.getAuthorService().getAllAuthors();

			IntStream.range(0, removeNewList.size())
			.forEach(index -> System.out.println(index + " -> " + removeNewList.get(index)));
			
			System.out.println("Please choose a number for author name");
			int choiceOfAuthorRemove = InputHelper.checkInput(0,removeNewList.size()-1);
			Author tempAuthorRemove = removeNewList.get(choiceOfAuthorRemove);
			//int authorIDRemove =tempAuthorRemove.getAuthorId();		
			tempAuthorRemove.getAuthorId();	
			break;
		}
	}
	
	public static void adminAddUpdateDeletePublisher() { //2
		System.out.println("You choose Publisher");
		
		System.out.println("Please choose:" 
				+ "\n1) Add"
				+ "\n2) Update" 
				+ "\n3) Delete");
		String crudChoice = InputHelper.userString();
		switch(crudChoice) {
		case("1"): //add
			System.out.println("What is the name of the publisher you are adding");
			String name = InputHelper.userString();
			System.out.println("What is the address of the publisher you are adding");
			String address = InputHelper.userString();
			System.out.println("What is the phone of the publisher you are adding");
			String phone = InputHelper.userString();
			allService.getPublisherService().addPublisher(name, address, phone);
			break;
		case("2"): //update
			System.out.println("What is the name of the publisher you are updating");
			ArrayList<Publisher>  newList = allService.getPublisherService().getList();

			IntStream.range(0, newList.size())
			.forEach(index -> System.out.println(index + " -> " + newList.get(index)));
			
			int choiceOfPublisher = InputHelper.checkInput(0,newList.size()-1);
			Publisher tempPublisher = newList.get(choiceOfPublisher);
			int publisherID = tempPublisher.getPublisherId();
			System.out.println("What is the new name of the publisher you are adding");
			String nameUpdate = InputHelper.userString();
			System.out.println("What is the new address of the publisher you are adding");
			String addressUpdate = InputHelper.userString();
			System.out.println("What is the new phone of the publisher you are adding");
			String phoneUpdate = InputHelper.userString();
			allService.getPublisherService().updatePublisherAllByID(publisherID, nameUpdate, addressUpdate, phoneUpdate);
			break;
		case("3"):
			System.out.println("What is the name of the pubisher you are removing");
			ArrayList<Publisher>  removeNewList = allService.getPublisherService().getList();

			IntStream.range(0, removeNewList.size())
			.forEach(index -> System.out.println(index + " -> " + removeNewList.get(index)));
			
			int choiceOfPublisherRemove = InputHelper.checkInput(0,removeNewList.size()-1);
			Publisher tempPublisherRemov = removeNewList.get(choiceOfPublisherRemove);
			int publisherIDRemove = tempPublisherRemov.getPublisherId();
			allService.getPublisherService().removePublisherByID(publisherIDRemove);
			break;
		}
	}
	public static void adminAddUpdateDeleteLibraryBranch() { //3
		System.out.println("You choose Library Branch");
		System.out.println("Please choose:" 
				+ "\n1) Add"
				+ "\n2) Update" 
				+ "\n3) Delete");
		String crudChoice = InputHelper.userString();
		switch(crudChoice) {
		case("1"): //add
			System.out.println("What is the name of the Library Branch you are adding");
			String nameAdd = InputHelper.userString();
			System.out.println("what is the address of the branch you are adding");
			String addressAdd = InputHelper.userString();
			allService.getLibraryBranchService().addLibraryBranch(nameAdd, addressAdd);
			break;
		case("2"): //update
			ArrayList<LibraryBranch>  newList = allService.getLibraryBranchService().displayAllLibraryBranchByNameAndAddress();

			IntStream.range(0, newList.size())
			.forEach(index -> System.out.println(index + " -> " + newList.get(index)));
			
			System.out.println("please choose a number");
			int choiceOfLibBranch = InputHelper.checkInput(0,newList.size()-1);
			LibraryBranch libraryBranch = newList.get(choiceOfLibBranch);
			int libraryBranchID = libraryBranch.getBranchID();

			System.out.println("What is the new name of the Library Branch");
			String nameUpdate = InputHelper.userString();
			System.out.println("What is the new address");
			String addressUpdate = InputHelper.userString();
			allService.getLibraryBranchService().updateLibraryBranchAddressByID(libraryBranchID, addressUpdate);
			allService.getLibraryBranchService().updateLibraryBranchNameByID(libraryBranchID, nameUpdate);
			break;
		case("3"): //delete
			System.out.println("What is the name of the Library Branch you are removing");
			ArrayList<LibraryBranch>  removeList = allService.getLibraryBranchService().displayAllLibraryBranchByNameAndAddress();

			IntStream.range(0, removeList.size())
			.forEach(index -> System.out.println(index + " -> " + removeList.get(index)));
			
			System.out.println("please choose a number");
			int choiceOfLibBranchRemove = InputHelper.checkInput(0,removeList.size()-1);
			LibraryBranch libraryBranchRemove = removeList.get(choiceOfLibBranchRemove);
			int libraryBranchIDRemove = libraryBranchRemove.getBranchID();
			allService.getLibraryBranchService().removeLibraryID(libraryBranchIDRemove);
			break;
		}
	}
	
	public static void adminAddUpdateDeleteBorrowers() { //4
		System.out.println("You choose Borrowers");
		System.out.println("Please choose:" 
				+ "\n1) Add"
				+ "\n2) Update" 
				+ "\n2) Delete");
		String crudChoice = InputHelper.userString();
		switch(crudChoice) {
		case("1"):
			System.out.println("What is the name of the Borrowers you are adding");
			String name = InputHelper.userString();
			System.out.println("What is the address of the Borrowers you are adding");
			String address = InputHelper.userString();
			System.out.println("What is the phone number of the Borrowers you are adding");
			String phoneNumber = InputHelper.userString();
			allService.getBorrowerService().addBorrower(name, address, phoneNumber);
			break;
		case("2"):
			ArrayList<Borrower>  newList = allService.getBorrowerService().getAllList();

			IntStream.range(0, newList.size())
			.forEach(index -> System.out.println(index + " -> " + newList.get(index)));
			
			System.out.println("please choose a number");
			int choiceOfBorrower = InputHelper.checkInput(0,newList.size()-1);
			Borrower borrower = newList.get(choiceOfBorrower);
			int borrowerID = borrower.getCardNo();
			System.out.println("What is the new name of the Borrowers");
			String borrowerName = InputHelper.userString();
			System.out.println("What is the new address of the Borrowers");
			String borrowerAddress = InputHelper.userString();
			System.out.println("What is the new phone of the Borrowers");
			String borrowerPhone = InputHelper.userString();
			allService.getBorrowerService().updateAll(borrowerID, borrowerName, borrowerAddress, borrowerPhone);
			break;
		case("3"):
			System.out.println("What is the name of the Borrowers you are removing");
			ArrayList<Borrower>  newListRemove = allService.getBorrowerService().getAllList();

			IntStream.range(0, newListRemove.size())
			.forEach(index -> System.out.println(index + " -> " + newListRemove.get(index)));
			
			System.out.println("please choose a number");
			int choiceOfBorrowerRemove = InputHelper.checkInput(0,newListRemove.size()-1);
			Borrower borrowerRemove = newListRemove.get(choiceOfBorrowerRemove);
			int borrowerIDRemove = borrowerRemove.getCardNo();
			allService.getBorrowerService().deleteBorrowerByID(borrowerIDRemove);
			break;
		}
	}
}
