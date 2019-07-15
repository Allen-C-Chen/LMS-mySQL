package services;


import java.util.ArrayList;

import dao.AuthorDao;
import dao.BookDao;
import model.Book;

public class BookService {
	private BookDao bookDao = new BookDao();
	private AuthorDao authorDao = new AuthorDao();

	public void addBook(String bookName, int authorID, int publisherID) {
		bookDao.add(bookName, authorID, publisherID);
	}
	public void addBook(String bookName, String authorName, String publisherName) {
		authorDao.addByName(authorName);
		addBook(bookName,1,1);
	}
	public Book getBookById(int bookID) {
		return bookDao.getBookByID(bookID);
	}
	public void removeBookByID(int bookID) {
		bookDao.removeByID(bookID);
	}
	public void updateBooks(int bookID, String newBookTitle) {
		bookDao.updateByBookID(bookID, newBookTitle);
	}
	public ArrayList<Book> getListOfBooks(){
		return bookDao.getAllBooks();
	}
	public void printAllBooks() {
		// TODO Auto-generated method stub
		bookDao.printAllBooks();
	}
	

}
