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

	public static List<Book> getAvailableBooks(){
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		List<Book> availableBooks = new LinkedList<Book>();
		User user = null;
		int ownerID = 0;
		int year, ISBN = 0;
		String title, author, publisher = null;
		//boolean isAvailable;
		try{
			//STEP 3: Open a connection
			//System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);

			System.out.println("Connected to database...");

			//STEP 4: Execute a query
			System.out.println("Creating statement...");
			String sql = "SELECT OwnerID, Title, Author, Publisher, Year, ISBN, IsAvailable FROM Books WHERE IsAvailable = 1;";
			ResultSet rs, rs2 = null;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next())
			{
				//Get the book information
				ownerID = rs.getInt("OwnerID");
				year = rs.getInt("Year");
				ISBN = rs.getInt("ISBN");
				title = rs.getString("Title");
				author = rs.getString("Author");
				publisher = rs.getString("Publisher");
				
				
				//Using the ownerID create a user object that will be used to instantiate the book
				sql = "SELECT Username, Password FROM Users WHERE ID = ?;";
				ps2 = conn.prepareStatement(sql);
				ps2.setInt(1, ownerID);
				rs2 = ps2.executeQuery();
				while(rs2.next()){
					user = new User(rs2.getString("Username"), rs2.getString("Password"));
				}
				rs2.close();
				availableBooks.add(new Book(user, title, author, publisher, year, ISBN, true));
			}
			rs.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		disposePS(ps2);
		disposeConn(conn);
		return availableBooks;
	}

	private static void disposeConn(Connection conn){
		try {
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void disposePS(PreparedStatement ps){
		try {
			ps.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]){
		for(Book book : getAvailableBooks()){
			System.out.println("Book title: " + book.getTitle());
		}
	}
}