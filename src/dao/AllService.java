package dao;

import services.AuthorService;
import services.BookCopiesService;
import services.BookLoanService;
import services.BookService;
import services.BorrowerService;
import services.LibraryBranchService;
import services.PublisherService;

public class AllService {
	BookService bookService;
	AuthorService authorService;
	BorrowerService borrowerService;
	LibraryBranchService libraryBranchService;
	BookCopiesService bookCopiesService;
	PublisherService publisherService;
	BookLoanService bookLoanService;
	public AllService() {
		bookService = new BookService();
		authorService = new AuthorService();		
		publisherService = new PublisherService();
		borrowerService = new BorrowerService();
		libraryBranchService = new LibraryBranchService();
		bookCopiesService = new BookCopiesService();
		bookLoanService = new BookLoanService();	}

	public BookLoanService getBookLoanService() {
		return bookLoanService;
	}

	public void setBookLoanService(BookLoanService bookLoanService) {
		this.bookLoanService = bookLoanService;
	}

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public AuthorService getAuthorService() {
		return authorService;
	}

	public void setAuthorService(AuthorService authorService) {
		this.authorService = authorService;
	}

	public BorrowerService getBorrowerService() {
		return borrowerService;
	}

	public void setBorrowerService(BorrowerService borrowerService) {
		this.borrowerService = borrowerService;
	}

	public LibraryBranchService getLibraryBranchService() {
		return libraryBranchService;
	}

	public void setLibraryBranchService(LibraryBranchService libraryBranchService) {
		this.libraryBranchService = libraryBranchService;
	}

	public BookCopiesService getBookCopiesService() {
		return bookCopiesService;
	}

	public void setBookCopiesService(BookCopiesService bookCopiesService) {
		this.bookCopiesService = bookCopiesService;
	}

	public PublisherService getPublisherService() {
		return publisherService;
	}

	public void setPublisherService(PublisherService publisherService) {
		this.publisherService = publisherService;
	}
	
}
