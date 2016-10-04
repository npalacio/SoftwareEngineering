package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import models.Book;
import models.User;

//This class will model a connection to the database to store/retrieve information
public class Database {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://ec2-52-42-237-83.us-west-2.compute.amazonaws.com:3306/BookExchange";

	//  Database credentials
	static final String USER = "npalacio";
	static final String PASS = "books";

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

	public static void disposeConn(Connection conn){
		if(conn != null){
			try {
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Attempted to close null connection!");
		}
	}

	public static void disposePS(PreparedStatement ps){
		if(ps != null){
			try {
				ps.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Attempted to close null prepared statement!");
		}
	}
}