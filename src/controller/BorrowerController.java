package controller;

import java.util.ArrayList;
import java.util.Scanner;

import dao.AllService;
import model.Book;
import model.BookLoans;
import model.Borrower;
import model.LibraryBranch;
import services.BorrowerService;

public class BorrowerController {
	final static String INVALIDCHOICE = "Invalid choice, try again";
	static AllService allService = new AllService();

	static void runBorrower() {
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
}
