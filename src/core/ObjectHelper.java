package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ObjectHelper {

	private String userName = "root";
	private String password = "newPass";
	private String url = "jdbc:mysql://localhost:3306/veritabani";
	private static String driver = "com.mysql.jdbc.Driver";
	
	static {
		try {
			Class.forName(driver);
		} catch(ClassNotFoundException e ){
			e.printStackTrace();
		}
	}
	
	protected Connection getConnection() {
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(url, userName, password);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
}
