package controller;

import java.util.ArrayList;
import java.util.stream.IntStream;

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
		System.out.println("Please enter your borrower card number");
		int cardNumber = InputHelper.checkInput(0,99999);
		Borrower tempBorrower = new Borrower();
		while(tempBorrower.getCardNo() == 0) {
			tempBorrower = borrowerService.checkID(cardNumber);
			if(tempBorrower.getCardNo() == 0) {
				System.out.println("please try again");
				cardNumber = InputHelper.checkInput(0,999999);
				tempBorrower = borrowerService.checkID(cardNumber);
			}
		}
		System.out.println("that is a valid number");
		runBorrowerBORR1(cardNumber);
	}
	private static void runBorrowerBORR1(int cardNo) {
		System.out.println("1) Check out a book"
				+ "\n2) Return A book"
				+ "\n3) Quit to previous");
		String borrowerChoice = InputHelper.userString();
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

		System.out.println("Here is a list of the locations and branches we have");
		ArrayList<LibraryBranch> libArray = allService.getLibraryBranchService().displayAllLibraryBranchByNameAndAddress();
		IntStream.range(0, libArray.size())
		.forEach(index -> System.out.println(index + " -> " + libArray.get(index)));
		System.out.println("Pick the Branch you want to check out from");
		//display all
		System.out.println("Please choose the number" );
		int number = InputHelper.checkInput(0,libArray.size()-1);
		LibraryBranch libraryBranch = libArray.get(number);
		int branchID = libraryBranch.getBranchID(); //get the branch ID
		//from the list, display all books in that lib
		ArrayList<Book> books = allService.getLibraryBranchService().displayAllBooksAndAuthorInABranchID(branchID);
		IntStream.range(0, books.size())
		.forEach(index -> System.out.println(index + " -> " + books.get(index)));
		
		//String stringChoiceOfBook = Integer.toString(scan.int());
		int choiceOfBOok = InputHelper.checkInput(0,books.size()-1);
		Book tempBook = books.get(choiceOfBOok );
		int bookID = tempBook.getBookId(); //get the book ID
		allService.getBookLoanService().checkOutBook(branchID, bookID, cardNo);
		//exiting takes me to BORR1
	}
	private static void runBorrowerReturnBook(int cardNo) {

		System.out.println("Here are the books onLoan");
		ArrayList<BookLoans> bookLoanss = allService.getBookLoanService().getAllListBybyCardNo(cardNo);

		IntStream.range(0, bookLoanss.size())
		.forEach(index -> System.out.println(index + " -> " + bookLoanss.get(index)));
		
		System.out.println("Please choose the number" );
		int number = InputHelper.checkInput(0,bookLoanss.size()-1);
		BookLoans bookLoans = bookLoanss.get(number);
		int branchID = bookLoans.getLibraryBranch().getBranchID();
		int bookID = bookLoans.getBook().getBookId();
		allService.getBookLoanService().checkInBook(branchID, bookID, cardNo);
	}
}
