package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//This class will model a connection to the database to store/retrieve information
public class Database {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://ec2-52-42-237-83.us-west-2.compute.amazonaws.com:3306/GroceryList";

	//  Database credentials
	static final String USER = "";
	static final String PASS = "";

	static public Connection getConnection()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			System.out.println("InstantiationException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			System.out.println("IllegalAccessException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return conn;
	}
}
