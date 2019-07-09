package model;

public class BookCopies {
	//private int bookId; //Primary Key, Not Null
	//private int branchId; //Primary Key, Not Null
	private Book book;
	private LibraryBranch libraryBranch;
	private int noOfCopies;
	
	public BookCopies(Book book, LibraryBranch libraryBranch, int noOfCopies) {
		super();
		this.book = book;
		this.libraryBranch = libraryBranch;
		this.noOfCopies = noOfCopies;
	}
	
	public BookCopies() {
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
	public int getNoOfCopies() {
		return noOfCopies;
	}
	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}

	@Override
	public String toString() {
		return "bookCopies [book=" + book.getTitle() + ", libraryBranch=" + libraryBranch.getBranchName() + ", noOfCopies=" + noOfCopies + "]";
	}

}
