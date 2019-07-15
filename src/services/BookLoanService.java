package services;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import dao.BookLoanDao;
import model.BookCopies;
import model.BookLoans;

public class BookLoanService {
	BookLoanDao bookLoanDao = new BookLoanDao();
	BookCopiesService bookCopiesServices = new BookCopiesService();

	public void adminOverRide(int numOfWeeks, int bookID, int branchId,  int cardNo) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String checkOutDate = dtf.format(now.plusWeeks(numOfWeeks));
		bookLoanDao.updateCheckInDate(checkOutDate, branchId, bookID, cardNo );
	}
	public void checkOutBook(int branchId, int bookID, int cardNo) {
		//book copies --;
		BookCopies bookCopies = new BookCopies();
		bookCopies = bookCopiesServices.getBookCopiesByID( branchId, bookID);
		int newCopies = bookCopies.getNoOfCopies()-1;
		bookCopiesServices.updateBookCopies(bookID, branchId, newCopies); //REMOVING 1 from THE Book Copies
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String checkInDate = dtf.format(now);		
		String checkOutDate = dtf.format(now.plusWeeks(1));		
		bookLoanDao.addBookLoan(bookID,branchId, cardNo, checkInDate, checkOutDate);
		System.out.println("You have checked out this book! It is due date is " + checkOutDate);

	}
	public void checkInBook(int branchIDd, int bookID, int cardNo) {
		//book loans --;
		bookLoanDao.removeByID(bookID, branchIDd, cardNo);
		BookCopies bookCopies = new BookCopies();
		bookCopies = bookCopiesServices.getBookCopiesByID( branchIDd, bookID);
		int newCopies = bookCopies.getNoOfCopies()+1;
		bookCopiesServices.updateBookCopies(bookID, branchIDd, newCopies);
		//book copies ++;
	}
	public BookLoans getBookLoansByID(int bookID, int branchID, int cardNo) {
		return bookLoanDao.getBookLoansByID(bookID, branchID, cardNo);
	}
	public ArrayList<BookLoans> getAllList(){
		return bookLoanDao.getAllBooks();
	}
	public ArrayList<BookLoans> getAllListBybyCardNo(int cardNo){
		return bookLoanDao.getAllBooksByCardNo(cardNo);
	}

}
