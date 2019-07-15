package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Author;
import model.Book;
import model.LibraryBranch;

import java.util.ArrayList; // import the ArrayList class

public class LibraryBranchDao {
	private Connection conn = null;
	public LibraryBranch getLibraryBranchByID(int libraryBranchID) {
		// TODO Auto-generated method stub
		conn = JDBCDao.getConnection();
		LibraryBranch tempLibraryBranch = new LibraryBranch();
		ResultSet resultSet = null;
		String sql = "SELECT * FROM tbl_library_branch where branchId=?";
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			prepareStatement.setInt(1, libraryBranchID); 
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String address = resultSet.getString(3);
				tempLibraryBranch.setBranchID(id);
				tempLibraryBranch.setBranchName(name);
				tempLibraryBranch.setBranchAddress(address);
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
		return tempLibraryBranch;
	}
	
	public void addByNameAndAddress(String libraryBranchName, String libraryBranchAddress) {
		// TODO Auto-generated method stub
		conn = JDBCDao.getConnection();
		String sql = "INSERT INTO `lms`.`tbl_library_branch` (`branchName`, `branchAddress`) VALUES (?,?)";

		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			prepareStatement.setString(1, libraryBranchName);
			prepareStatement.setString(2, libraryBranchAddress);
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
		//return null;
	}

	public void removeByID(int libraryBranchID) {
		conn = JDBCDao.getConnection();
		String sql = "delete from tbl_library_branch where branchId = ?";
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			
			prepareStatement.setInt(1, libraryBranchID);
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
	public void updateLibraryBranchNameByID(int libraryBranchID, String newLibraryBranchName) {
		// TODO Auto-generated method stub
		conn = JDBCDao.getConnection();
		String sql = "update tbl_library_branch set branchName = ? "
				+ "where branchId = ?";
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			prepareStatement.setString(1, newLibraryBranchName);
			prepareStatement.setInt(2, libraryBranchID);
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
	public void updateLibraryBranchAddressByID(int libraryBranchID, String newLibraryBranchAddress) {
		// TODO Auto-generated method stub
		conn = JDBCDao.getConnection();
		String sql = "update tbl_library_branch set branchAddress = ? "
				+ "where branchId = ?";
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			prepareStatement.setString(1, newLibraryBranchAddress);
			prepareStatement.setInt(2, libraryBranchID);
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
	
	public ArrayList<Book> displayAllBooksAndAuthorInABranchID(int branchID) {
		conn = JDBCDao.getConnection();
	    ArrayList<Book> books = new ArrayList<Book>();

		ResultSet resultSet = null;
		String sql ="Select " + 
				"	book.bookId, book.title, authorName" + 
				" From  tbl_book_copies as copies" + 
				" Inner join" + 
				"	tbl_library_branch as branch on branch.branchId = copies.branchId" + 
				" Inner join" + 
				"	tbl_book as book on book.bookID = copies.bookId" + 
				" Inner join" + 
				"	tbl_author as author on author.authorId = book.authId" + 
				" where copies.branchId = ?;";
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			prepareStatement.setInt(1, branchID); 
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				Book newBook = new Book();
				Author newAuthor = new Author();

				int bookID = resultSet.getInt(1);
				String title = resultSet.getString(2);
				String author = resultSet.getString(3);
				newAuthor.setAuthorName(author);
				newBook.setAuthor(newAuthor);
				newBook.setTitle(title);
				newBook.setBookId(bookID);
				books.add(newBook);
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
		return books;
	}
	public ArrayList<LibraryBranch> displayAllLibraryBranchByNameAndAddress() {
		// TODO Auto-generated method stub
		conn = JDBCDao.getConnection();
	    ArrayList<LibraryBranch> libraryBranches = new ArrayList<LibraryBranch>();

		ResultSet resultSet = null;
		String sql = "SELECT * FROM tbl_library_branch;";
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				int branchID = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String address = resultSet.getString(3);
				LibraryBranch libraryBranch = new LibraryBranch();
				libraryBranch.setBranchID(branchID);
				libraryBranch.setBranchName(name);
				libraryBranch.setBranchAddress(address);
				libraryBranches.add(libraryBranch);
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
		return libraryBranches;
		
	}


}
