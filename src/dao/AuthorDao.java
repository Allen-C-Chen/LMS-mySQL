package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Author;
import model.Book;

public class AuthorDao {

	private Connection conn = null;
	public ArrayList<Author> geAllAuthor(){
		conn = JDBCDao.getConnection();
		ArrayList<Author> authors = new ArrayList();

		ResultSet resultSet = null;
		String sql = "SELECT * FROM tbl_author;";
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				Author tempAuthor = new Author();

				tempAuthor.setAuthorId(resultSet.getInt(1));
				tempAuthor.setAuthorName(resultSet.getString(2));
				authors.add(tempAuthor);
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
		return authors;
	}
	public Author getAuthorByID(int authorID) {
		// TODO Auto-generated method stub
		//PreparedStatement prepareStatement = null;
		conn = JDBCDao.getConnection();

		Author tempAuthor = new Author();
		ResultSet resultSet = null;
		String sql = "SELECT * FROM tbl_author where authorId=?";
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			prepareStatement.setInt(1, authorID); 
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				System.out.println("author name is :: " + resultSet.getString("authorName"));
				tempAuthor.setAuthorId(resultSet.getInt(1));
				tempAuthor.setAuthorName(resultSet.getString(2));
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
		return tempAuthor;
	}

	public void addByName(String authorName) {
		// TODO Auto-generated method stub
		conn = JDBCDao.getConnection();
		ResultSet resultSet = null;
		String sql = "INSERT INTO `lms`.`tbl_author` (`authorName`) VALUES (?)";
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			prepareStatement.setString(1, authorName);
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
	public String removeByName(String authorName) {
		// TODO Auto-generated method stub
		conn = JDBCDao.getConnection();

		String sql = "delete from tbl_author where authorName = ?";
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			prepareStatement.setString(1, authorName);
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

		return authorName;
	}

	public void removeByID(int authorID) {
		conn = JDBCDao.getConnection();

		String sql = "delete from tbl_author where authorId = ?";
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			
			prepareStatement.setInt(1, authorID);
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
	public void updateByAuthorID(int authorID, String newAuthorName) {
		// TODO Auto-generated method stub
		Connection conn = JDBCDao.getConnection();
		String sql = "update tbl_author set authorName = ? "
				+ "where authorId = ?";
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			prepareStatement.setString(1, newAuthorName);
			prepareStatement.setInt(2, authorID);
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
	
}
