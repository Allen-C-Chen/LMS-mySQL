package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Borrower;


public class BorrowerDao {
	private Connection conn = null;
	public void addBorrower(String borrowerName, String borrowerAddress, String borrowerPhone) {
		conn = JDBCDao.getConnection();
		String sql = "INSERT INTO `lms`.`tbl_borrower` (`name`, `address`, `phone`) VALUES (?, ?, ?);";
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			prepareStatement.setString(1, borrowerName);
			prepareStatement.setString(2, borrowerAddress);
			prepareStatement.setString(3, borrowerPhone);

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
	public void removeByID(int borrowerID) {
		conn = JDBCDao.getConnection();
		String sql = "delete from tbl_borrower where cardNo = ?";
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			prepareStatement.setInt(1, borrowerID);
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
	public Borrower getBorrowerByID(int borrowerID) {
		// TODO Auto-generated method stub
		conn = JDBCDao.getConnection();
		//PreparedStatement prepareStatement = null;
		Borrower tempBorrower = new Borrower();
		ResultSet resultSet = null;
		String sql = "SELECT * FROM tbl_borrower where cardNo=?";
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			prepareStatement.setInt(1, borrowerID); 
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				tempBorrower.setCardNo(resultSet.getInt(1));
				tempBorrower.setName(resultSet.getString(2));
				tempBorrower.setAddress(resultSet.getString(3));
				tempBorrower.setPhone(resultSet.getString(4));
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
		return tempBorrower;
	}
	public void updateAll(int borrowerID, String borrowerName, String borrowerAddress, String borrowerPhone) {
		// TODO Auto-generated method stub
		conn = JDBCDao.getConnection();
		System.out.println("HIH");
		String sql = "UPDATE `lms`.`tbl_borrower` SET `name` = ?, `address` = ?, `phone` = ? WHERE (`cardNo` = ?);";
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			prepareStatement.setString(1, borrowerName);
			prepareStatement.setString(2, borrowerAddress);
			prepareStatement.setString(3, borrowerPhone);
			prepareStatement.setInt(4, borrowerID);
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
	public ArrayList<Borrower> getAllList() {
		// TODO Auto-generated method stub
		conn = JDBCDao.getConnection();
		ResultSet resultSet = null;
		ArrayList<Borrower> borrowers = new ArrayList<Borrower>();
		String sql = "SELECT * FROM tbl_borrower;";
		try(PreparedStatement prepareStatement = conn.prepareStatement(sql)){
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()) {
				Borrower newBorrower = new Borrower();
				newBorrower.setCardNo(resultSet.getInt(1));
				newBorrower.setName(resultSet.getString(2));
				newBorrower.setAddress(resultSet.getString(3));
				newBorrower.setAddress(resultSet.getString(4));
				borrowers.add(newBorrower);
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
		return borrowers;
		
	}


}
