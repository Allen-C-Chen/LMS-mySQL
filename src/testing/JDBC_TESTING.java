package testing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.Author;
import model.Book;
import model.Borrower;
import model.LibraryBranch;
import services.AuthorService;
import services.BookCopiesService;
import services.BookLoanService;
import services.BookService;
import services.BorrowerService;
import services.LibraryBranchService;
import services.PublisherService;

public class JDBC_TESTING {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//runAuthorTesting();
		//runBookTesting();
		//runPublisherTesting();
		//borrowerTesting();
		//runLibBranchTesting();
		//borrowerTesting();
		//runBookCopies();
		//runBookTesting();
		//bookLoanTest();
		PublisherService publisherService = new PublisherService();

		System.out.println(publisherService.getList());
	}
	static void bookLoanTest() {
		BookLoanService bookLoanService = new BookLoanService();
		bookLoanService.checkOutBook(2, 2, 2);
		//bookLoanService.checkInBook(2, 2, 2);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now().plusWeeks(4);
		String newCheckOut = dtf.format(now);
		
		//bookLoanService.adminOverRide(newCheckOut, 2, 2, 2);
	}
	static void runBookTesting() {
		System.out.println();
		BookService bookService = new BookService();
		System.out.println(bookService.getBookById(0).toString());
		
		System.out.println("Print all books");
		//bookService.printAllBooks();
		//bookService.addBook("book99", 1, 1);
		//bookService.updateBooks(33, "HI THERE");
		bookService.removeBookByID(33);
	}
	
	
	static void runBookCopies() {
		BookCopiesService bookCopiesService = new BookCopiesService();
		System.out.println(bookCopiesService.getBookCopiesByID(1,1).getNoOfCopies());
		//bookCopiesService.updateBookCopies(27, 1, 10);
		
	}
	static void borrowerTesting() {
		BorrowerService borrowerService = new BorrowerService();
		Borrower borrower = borrowerService.getBorrowerById(100);
		if(borrower == null) {
			System.out.println("Card not found");
		}
		else {
			System.out.println(borrower);
		}

		borrowerService.updateAll(99, "hi", "there", "Whats up");
	}
	static void runAuthorTesting() {
		AuthorService authorService = new AuthorService();		
		System.out.println("Add AUthor");
		authorService.addAuthorByName("TestAuthor");
		System.out.println("Get Author by ID");
		System.out.println(authorService.getAuthorById(1).toString());

		System.out.println("Update author by name");
		//authorService.updateByAuthorID(10, "lameAuthor");
		///]authorService.updateByAuthorID(10, "authorName10");

		System.out.println("Remove author by name");
		//authorService.removeAuthorByName("TestAuthor");
		//authorService.removeAuthorByAuthorID(15);
	}

	static void runPublisherTesting() {
		System.out.println();
		PublisherService publisherService = new PublisherService();
		System.out.println(publisherService.getPublisherById(0));
		//publisherService.addPublisher("hu", "address", "Pohne");
		publisherService.removePublisherByID(4);
		//publisherService.updatePublisherAllByID(4, "hi", "bob", "whats up");
	}
	
	static void runBorrowerTesting() {
		System.out.println();
		BorrowerService borrowerService = new BorrowerService();
		System.out.println(borrowerService.getBorrowerById(0));
		//borrowerService.addBorrower("hi", "There", "How ");
		borrowerService.deleteBorrowerByID(21);
		
	}
	static void runLibBranchTesting() {
		System.out.println();
		LibraryBranchService libraryBranchService = new LibraryBranchService();
		System.out.println("Add a library branch");
		//libraryBranchService.addLibraryBranch("testBranch", "testBranchAddress");
		System.out.println("HI");
		//System.out.println("get library branch");
		//libraryBranchService.getLibraryBranchById(4);
		
		//libraryBranchService.updateLibraryBranchNameByID(6, "BLAH BLAH");
		//libraryBranchService.updateLibraryBranchAddressByID(6, "BLAH BLAH ADDRESS");
		System.out.println("get library branch");
		System.out.println(libraryBranchService.getLibraryBranchById(4).getBranchName());;
		
		//libraryBranchService.removeAuthorByAuthorID(5);
		//libraryBranchService.removeAuthorByAuthorID(4);
		ArrayList<LibraryBranch> libraryBranches = libraryBranchService.displayAllLibraryBranchByNameAndAddress();
		for(int i = 0; i < libraryBranches.size(); i ++) {
			System.out.println(i+1 + ") " + libraryBranches.get(i).printNameAndAddress());
		}
		ArrayList<Book> books = libraryBranchService.displayAllBooksAndAuthorInABranchID(1);
		for(Book book : books) {
			System.out.println(book.printTitleAndAuthor());
		}
		

	}
}
