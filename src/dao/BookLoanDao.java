package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Book;
import model.BookLoans;
import model.Borrower;
import model.LibraryBranch;

public class BookLoanDao {
	private Connection conn = null;
/**
 * 
 * @param bookID
 * @param branchID
 * @param cardNo
 * @return
 */

	public BookLoans getBookLoansByID(int bookID, int branchID, int cardNo) {
		// TODO Auto-generated method stub
		//PreparedStatement prepareStatement = null;
		conn = JDBCDao.getConnection();

		BookLoans tempBookLoans = new BookLoans();
		ResultSet resultSet = null;
		String sql = "SELECT * FROM tbl_book_loans where bookId=? AND branchId = ? AND cardNo = ?";

		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			prepareStatement.setInt(1, bookID); 
			prepareStatement.setInt(2, branchID); 
			prepareStatement.setInt(3, cardNo); 
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				String checkInDate = resultSet.getString(4);
				String dueDate = resultSet.getString(5);
				Book newBook = new Book();
				newBook.setBookId(bookID);
				LibraryBranch newLibraryBranch = new LibraryBranch();
				newLibraryBranch.setBranchID(branchID);
				Borrower newBorrower = new Borrower();
				newBorrower.setCardNo(cardNo);
				tempBookLoans.setBook(newBook);
				tempBookLoans.setLibraryBranch(newLibraryBranch);
				tempBookLoans.setBorrower(newBorrower);
				tempBookLoans.setDateOut(checkInDate);
				tempBookLoans.setDueDate(dueDate);
				//esultSet.getInt(1));
				//tempAuthor.setAuthorName(resultSet.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tempBookLoans;
	}
	


	public void removeByID(int bookID, int branchId, int cardNo) {
		conn = JDBCDao.getConnection();

		String sql = "DELETE FROM `lms`.`tbl_book_loans` WHERE (`bookId` = ?) and (`branchId` = ?) and (`cardNo` = ?)";
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			prepareStatement.setInt(1, bookID);
			prepareStatement.setInt(2, branchId);
			prepareStatement.setInt(3, cardNo);
			//execute
			prepareStatement.execute();
		} catch (SQLException e) { 	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	public void addBookLoan(int bookID, int branchId, int cardNo, String checkInDate, String checkOutDate) {
		// TODO Auto-generated method stub
		conn = JDBCDao.getConnection();
		String sql = "INSERT INTO `lms`.`tbl_book_loans` (`bookId`, `branchId`, `cardNo`, `dateOut`, `dueDate`) " 
		+ " VALUES (?, ?, ?, ?,?);";
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			prepareStatement.setInt(1, bookID);
			prepareStatement.setInt(2, branchId);
			prepareStatement.setInt(3, cardNo);
			prepareStatement.setString(4, checkInDate);
			prepareStatement.setString(5, checkOutDate);

			prepareStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}



	public void updateCheckInDate(String newDate, int bookID, int branchId, int cardNo) {
		// TODO Auto-generated method stub
		conn = JDBCDao.getConnection();
		String sql = "UPDATE `lms`.`tbl_book_loans` SET `dueDate` = ?" + 
		" WHERE (`bookId` = ?) and (`branchId` = ?) and (`cardNo` = ?);";
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			prepareStatement.setString(1, newDate);
			prepareStatement.setInt(2, branchId);
			prepareStatement.setInt(3, bookID);
			prepareStatement.setInt(4, cardNo);

			//execute
			prepareStatement.executeUpdate();
			
			
		} catch (SQLException e) { 	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public ArrayList<BookLoans> getAllBooks() {
		// TODO Auto-generated method stub
		conn = JDBCDao.getConnection();
		ResultSet resultSet = null;
		ArrayList<BookLoans> BookLoansData = new ArrayList<BookLoans>();
		String sql = "SELECT * FROM tbl_book_loans;";
		
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				BookLoans newBookLoans = new BookLoans();
				Book newBook = new Book();
				newBook.setBookId(resultSet.getInt(1));
				LibraryBranch newLibraryBranch = new LibraryBranch();
				newLibraryBranch.setBranchID(resultSet.getInt(2));
				Borrower newBorrower = new Borrower();
				newBorrower.setCardNo(resultSet.getInt(3));
				
				newBookLoans.setBook(newBook);
				newBookLoans.setLibraryBranch(newLibraryBranch);
				newBookLoans.setBorrower(newBorrower);
				
				newBookLoans.setDateOut(resultSet.getString(4));
				newBookLoans.setDueDate(resultSet.getString(5));
				//tempBook.setPubId(resultSet.getInt(4));
				//System.out.println(tempBook.toString());
				BookLoansData.add(newBookLoans);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return BookLoansData;
	}		
	public ArrayList<BookLoans> getAllBooksByCardNo( int cardNo) {
		// TODO Auto-generated method stub
		conn = JDBCDao.getConnection();
		ResultSet resultSet = null;
		ArrayList<BookLoans> BookLoansData = new ArrayList<BookLoans>();
		String sql = "SELECT * FROM tbl_book_loans where cardNo =?;";

		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			prepareStatement.setInt(1, cardNo);

			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				BookLoans newBookLoans = new BookLoans();
				Book newBook = new Book();
				newBook.setBookId(resultSet.getInt(1));

				LibraryBranch newLibraryBranch = new LibraryBranch();
				newLibraryBranch.setBranchID(resultSet.getInt(2));
				Borrower newBorrower = new Borrower();
				newBorrower.setCardNo(resultSet.getInt(3));
				
				newBookLoans.setBook(newBook);
				newBookLoans.setLibraryBranch(newLibraryBranch);
				newBookLoans.setBorrower(newBorrower);
				
				newBookLoans.setDateOut(resultSet.getString(4));
				newBookLoans.setDueDate(resultSet.getString(5));
				BookLoansData.add(newBookLoans);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return BookLoansData;
	}	
	
}
