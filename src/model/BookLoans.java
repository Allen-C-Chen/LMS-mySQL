package model;

public class BookLoans {
	//private int bookId; //Primary Key, Not Null
	//private int branchId; //Primary Key, Not Null
	//private int cardNo; //Primary Key, Not Null
	//dateOut
	//dueDate
	private Book book;
	private LibraryBranch libraryBranch;
	private Borrower borrower;
	String dateOut;
	String dueDate;
	public BookLoans(Book book, LibraryBranch libraryBranch, Borrower borrower, String dateOut, String dueDate) {
		super();
		this.book = book;
		this.libraryBranch = libraryBranch;
		this.borrower = borrower;
		this.dateOut = dateOut;
		this.dueDate = dueDate;
	}
	public BookLoans() {
		// TODO Auto-generated constructor stub
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public LibraryBranch getLibraryBranch() {
		return libraryBranch;
	}
	public void setLibraryBranch(LibraryBranch libraryBranch) {
		this.libraryBranch = libraryBranch;
	}
	public Borrower getBorrower() {
		return borrower;
	}
	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}
	public String getDateOut() {
		return dateOut;
	}
	public void setDateOut(String dateOut) {
		this.dateOut = dateOut;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	@Override
	public String toString() {
		return "BookLoans [book=" + book.getBookId() + ", libraryBranch=" + libraryBranch.getBranchID() + ", borrower=" + borrower.getCardNo() + ", dateOut="
				+ dateOut + ", dueDate=" + dueDate + "]";
	}
	
	
}
