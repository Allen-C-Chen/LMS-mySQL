package model;

public class Book {
	private int bookId; //Primary Key, Not Null
	private String title; //Not Null
	private Author author;
	private Publisher publisher;
//	private String authorName;
	

	public Book() {
		// TODO Auto-generated constructor stub
	}

	public Book(int bookId, String title, Author author, Publisher publisher) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public String printTitleAndAuthor() {
		return title + ", " + author.getAuthorName();
	}
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", author=" + author + ", publisher=" + publisher + "]";
	}

	
}
