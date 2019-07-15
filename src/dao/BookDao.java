package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Author;
import model.Book;

public class BookDao {
	private Connection conn = null;
	
	public void updateByBookID(int bookID, String newBookTitle) {
		// TODO Auto-generated method stub
		conn = JDBCDao.getConnection();
		String sql = "UPDATE `lms`.`tbl_book` SET `title` = ? WHERE (`bookId` = ?);"; 
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			prepareStatement.setString(1, newBookTitle);
			prepareStatement.setInt(2, bookID);
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
	
	public void removeByID(int bookID) {
		conn = JDBCDao.getConnection();

		String sql = "DELETE FROM `lms`.`tbl_book` WHERE (`bookId` = ?);";
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			
			prepareStatement.setInt(1, bookID);
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
	public Book getBookByID(int bookID) {
		// TODO Auto-generated method stub
		conn = JDBCDao.getConnection();
		Book tempBook = new Book();
		ResultSet resultSet = null;
		String sql = "SELECT * FROM tbl_book where bookId=?";
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			prepareStatement.setInt(1, bookID); 
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				System.out.println("book name is :: " + resultSet.getString("title"));
				tempBook.setBookId(resultSet.getInt(1));
				tempBook.setTitle(resultSet.getString(2));
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
		return tempBook;
	}

	public void printAllBooks() {
		// TODO Auto-generated method stub
		conn = JDBCDao.getConnection();
		Book tempBook = new Book();
		ResultSet resultSet = null;

		String sql = "SELECT * FROM tbl_book";
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				tempBook.setBookId(resultSet.getInt(1));
				tempBook.setTitle(resultSet.getString(2));
				tempBook.getAuthor().setAuthorId(resultSet.getInt(3));
				//tempBook.setPubId(resultSet.getInt(4));
				System.out.println(tempBook.toString());
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

	}

	public void add(String bookName, int authorID, int publisherID) {
		// TODO Auto-generated method stub
		conn = JDBCDao.getConnection();
		String sql = "INSERT INTO `lms`.`tbl_book` (`title`, `authId`, `pubId`) VALUES (?, ?, ?)";
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			prepareStatement.setString(1, bookName);
			prepareStatement.setInt(2, authorID);
			prepareStatement.setInt(3, publisherID);
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
	
	public ArrayList<Book> getAllBooksLambda(){
		//ResultSet resultSet = null;
		ArrayList<Book> books = new ArrayList<Book>();
		try(Connection conn = JDBCDao.getConnection()){
			String sql = "SELECT * FROM tbl_book";
			try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;

	}
	public ArrayList<Book> getAllBooks() {
		// TODO Auto-generated method stub
		conn = JDBCDao.getConnection();
		ResultSet resultSet = null;
		ArrayList<Book> books = new ArrayList<Book>();
		String sql = "SELECT * FROM tbl_book";
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				Book tempBook = new Book();
				tempBook.setBookId(resultSet.getInt(1));
				tempBook.setTitle(resultSet.getString(2));
				Author newAuthor = new Author();
				newAuthor.setAuthorId(resultSet.getInt(3));
				tempBook.setAuthor(newAuthor);
				//tempBook.setPubId(resultSet.getInt(4)); 
				//System.out.println(tempBook.toString());
				books.add(tempBook);
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
}
