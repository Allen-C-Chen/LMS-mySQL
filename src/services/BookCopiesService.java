package services;

import dao.BookCopiesDao;
import model.BookCopies;

public class BookCopiesService {
	BookCopiesDao bookCopiesDao = new BookCopiesDao();

	public BookCopies getBookCopiesByID(int bookID, int branchID) {
		// TODO Auto-generated method stub
		return bookCopiesDao.getBookCopiesByID(bookID, branchID);
	}
	public void updateBookCopies(int bookID, int branchID,int numOfCOpies) {
		bookCopiesDao.updateBookCopies(bookID, branchID, numOfCOpies);
	}

}
