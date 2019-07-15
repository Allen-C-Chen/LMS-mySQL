package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCDao {
	public static Connection getConnection() {
		Connection conn = null;
		PreparedStatement prepareStatement = null; //deete
		ResultSet resultSet = null; //delete
		try {
			// Legacy
			// Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/LMS?useSSL=false&autoReconnect=true&serverTimezone=UTC", "root", "root");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Legacy -Catch
		// } catch (ClassNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		finally { // if we use try with resources approach we do not need the finally block and
					// resources will automatically get closed
			try {
				if (resultSet != null) {
					resultSet.close();
				}
				if (prepareStatement != null) {
					prepareStatement.close();

				}
				if (conn == null) {
					conn.close();

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conn;
	}
}
