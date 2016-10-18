package database;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import models.Book;
import models.User;

public class DatabaseReader {
	
	
	public List<Book> getAvailableBooks(String columnToSortBy){
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		List<Book> availableBooks = new LinkedList<Book>();
		User user = null;
		int id = 0;
		int ownerID = 0;
		int year = 0;
		long ISBN = 0;
		String title, author, publisher = null;
		try{
			//Open a connection
			//System.out.println("Connecting to database...");
			conn = Database.getConnection();

			//System.out.println("Connected to database...");

			//Execute a query
			//System.out.println("Creating statement...");
			String sql = null;
			if(columnToSortBy != null && !columnToSortBy.isEmpty()) {
				sql = "SELECT ID, OwnerID, Title, Author, Publisher, Year, ISBN, IsAvailable FROM Books WHERE IsAvailable = 1 ORDER BY " 
						+ columnToSortBy + ";";
			} else {
				sql = "SELECT ID, OwnerID, Title, Author, Publisher, Year, ISBN, IsAvailable FROM Books WHERE IsAvailable = 1 ORDER BY Title;";
			}
			
			ResultSet rs, rs2 = null;
			ps = conn.prepareStatement(sql);

			System.out.println("Final query: " + ps);
			rs = ps.executeQuery();
			while(rs.next())
			{
				//Get the book information
				id = rs.getInt("ID");
				ownerID = rs.getInt("OwnerID");
				year = rs.getInt("Year");
				ISBN = rs.getLong("ISBN");
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
				availableBooks.add(new Book(id, user, title, author, publisher, year, ISBN, true));
			}
			rs.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		Database.disposePS(ps);
		Database.disposePS(ps2);
		Database.disposeConn(conn);
		return availableBooks;
	}
	
	public List<Book> getMyBooks(User owner, String columnToSortBy){
		Connection conn = null;
		PreparedStatement ps = null;
		List<Book> availableBooks = new LinkedList<Book>();
		int id = 0;
		User user = null;
		int ownerID = 0;
		int year = 0;
		long ISBN = 0;
		String title, author, publisher;
		boolean isAvailable;
		try{
			//Open a connection
			//System.out.println("Connecting to database...");
			conn = Database.getConnection();

			//System.out.println("Connected to database...");

			//Execute a query
			//System.out.println("Creating statement...");
			String sql = "SELECT ID FROM Users WHERE Username = ?;";
			ResultSet rs = null;
			ps = conn.prepareStatement(sql);
			ps.setString(1, owner.getName());
			rs = ps.executeQuery();
			while(rs.next())
			{
				//Get the ownerID that is used to get the books that belong to our user
				ownerID = rs.getInt("ID");
			}
			if(columnToSortBy != null && !columnToSortBy.isEmpty()) {
				sql = "SELECT ID, Title, Author, Publisher, Year, ISBN, IsAvailable FROM Books WHERE OwnerID = ? ORDER BY " 
						+ columnToSortBy + ";";
			} else {
				sql = "SELECT ID, Title, Author, Publisher, Year, ISBN, IsAvailable FROM Books WHERE OwnerID = ? ORDER BY Title;";
			}
			ps = conn.prepareStatement(sql);
			ps.setInt(1, ownerID);
			rs = ps.executeQuery();
			while(rs.next()){
				id = rs.getInt("Year");
				title = rs.getString("Title");
				author = rs.getString("Author");
				publisher = rs.getString("publisher");
				year = rs.getInt("Year");
				ISBN = rs.getLong("ISBN");
				isAvailable = rs.getBoolean("IsAvailable");
				
				availableBooks.add(new Book(id, user, title, author, publisher, year, ISBN, isAvailable));
			}
			rs.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		Database.disposePS(ps);
		Database.disposeConn(conn);
		return availableBooks;
	}
	
	//Main method for testing purposes
//	public static void main(String args[]){
//		User user = new User("npalacio", "fakePassword");
//		for(Book book : getMyBooks(user)){
//			System.out.println("Books owned by npalacio: " + book.getTitle());
//		}
//	}
}
