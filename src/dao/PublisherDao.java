package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Book;
import model.Publisher;

public class PublisherDao {
	Connection conn = null;
	
	public Publisher getPublisherByID(int publisherID) {
		// TODO Auto-generated method stub
		//PreparedStatement prepareStatement = null;
		Connection conn = JDBCDao.getConnection();

		Publisher tempPublisher = new Publisher();
		ResultSet resultSet = null;

		String sql = "SELECT * FROM tbl_publisher where publisherId=?";
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			prepareStatement.setInt(1, publisherID); 
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				System.out.println("Publisher name is :: " + resultSet.getString("publisherName"));
				tempPublisher.setPublisherId(resultSet.getInt(1));
				tempPublisher.setPublisherName(resultSet.getString(2));
				tempPublisher.setPublisherAddress(resultSet.getString(3));
				tempPublisher.setPublisherPhone(resultSet.getString(4));
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
		return tempPublisher;	
	}
	public void removeByID(int publisherID) {
		conn = JDBCDao.getConnection();

		String sql = "delete from tbl_publisher where publisherId = ?";
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			
			prepareStatement.setInt(1, publisherID);
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
	public void addPublisher(String publisherName, String publisherAddress, String publisherPhone) { //do upidate later
		// TODO Auto-generated method stub
		conn = JDBCDao.getConnection();

		ResultSet resultSet = null;
		String sql = "INSERT INTO `lms`.`tbl_publisher` (`publisherName`, `publisherAddress`, `publisherPhone`) VALUES (?, ?, ?);";
		
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			prepareStatement.setString(1, publisherName);
			prepareStatement.setString(2, publisherAddress);
			prepareStatement.setString(3, publisherPhone);

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
	public void updateAll(int publisherID, String publisherName, String publisherAddress, String publisherPhone) {
		// TODO Auto-generated method stub
		//UPDATE `lms`.`tbl_publisher` SET `publisherName` = ?, `publisherAddress` = ?, `publisherPhone` = ?  WHERE (`publisherId` = ?);
		Connection conn = JDBCDao.getConnection();
		String sql = "UPDATE `lms`.`tbl_publisher` SET `publisherName` = ?, `publisherAddress` = ?, `publisherPhone` = ?  WHERE (`publisherId` = ?);";
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			prepareStatement.setString(1, publisherName);
			prepareStatement.setString(2, publisherAddress);
			prepareStatement.setString(3, publisherPhone);

			prepareStatement.setInt(4, publisherID);
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
	public ArrayList<Publisher> getPubList(){
		Connection conn = JDBCDao.getConnection();
	    ArrayList<Publisher> publishers = new ArrayList<Publisher>();
		ResultSet resultSet = null;
		String sql = "SELECT * FROM lms.tbl_publisher;";
		Publisher newPub = new Publisher();
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				int pubID = resultSet.getInt(1);
				String pubName= resultSet.getString(2);
				String pubAdddress= resultSet.getString(3);
				String pubPhone= resultSet.getString(4);

				newPub.setPublisherId(pubID);
				newPub.setPublisherName(pubName);
				newPub.setPublisherAddress(pubAdddress);
				newPub.setPublisherPhone(pubPhone);
				publishers.add(newPub);
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
		return publishers;
	}
}
