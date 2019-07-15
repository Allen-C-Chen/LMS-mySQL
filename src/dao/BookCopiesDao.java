package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Book;
import model.BookCopies;
import model.LibraryBranch;

public class BookCopiesDao {

	private Connection conn = null;
	public void updateBookCopies(int bookID, int branchID,int numOfCOpies) {
		conn = JDBCDao.getConnection();
		String sql = "update tbl_book_copies set noOfCopies = ? "
				+" WHERE (`bookId` = ?) and (`branchId` = ?)";
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			prepareStatement.setInt(1, numOfCOpies);
			prepareStatement.setInt(2, bookID);
			prepareStatement.setInt(3, branchID);
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
	public BookCopies getBookCopiesByID(int bookID, int branchID) { //not returning anything
		conn = JDBCDao.getConnection();

		BookCopies tempBookCopies = new BookCopies();
		Book book = new Book();
		LibraryBranch branch = new LibraryBranch();
		ResultSet resultSet = null;
		String sql = "SELECT * FROM tbl_book_copies where bookId=? AND branchId = ?";
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			prepareStatement.setInt(1, bookID); 
			prepareStatement.setInt(2, branchID); 
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				int newBookID = resultSet.getInt(1);
				int newBranchID = resultSet.getInt(2);
				int NumOfCopies = resultSet.getInt(3);
				book.setBookId(newBookID);
				branch.setBranchID(newBranchID);
				tempBookCopies.setBook(book);
				tempBookCopies.setLibraryBranch(branch);
				tempBookCopies.setNoOfCopies(NumOfCopies);
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
		return tempBookCopies;	
		}

}
