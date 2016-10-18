package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.Book;
import models.User;

public class DatabaseWriter {
	
	//Delete book from BookID field in a book object
	public boolean deleteBook(int bookId){
		Connection conn = null;
		PreparedStatement ps = null;
		//String title = book.getTitle();
		//String author = book.getAuthor();
		try{
			//Delete references to this book that is in other tables (Trades/Purchases)
			conn = Database.getConnection();
			String sql = "DELETE FROM Trades WHERE SenderBookID = ? OR ReceiverBookID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bookId);
			ps.setInt(2, bookId);
			ps.executeUpdate();
			sql = "DELETE FROM Purchases WHERE BookID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bookId);
			ps.executeUpdate();
			sql = "DELETE FROM Books WHERE ID = ?;";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bookId);
			ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		Database.disposePS(ps);
		Database.disposeConn(conn);
		return true;
	}
	
	//returns boolean indicating success/failure
	public boolean addBook(Book book){
		Connection conn = null;
		PreparedStatement ps = null;
		int ownerID = 0;
		String title = book.getTitle();
		String author = book.getAuthor();
		String publisher = book.getPublisher();
		int year = book.getYear();
		long isbn = book.getISBN();
		boolean isAvailable = book.isAvailable();
		try{
			//Open a connection
			//System.out.println("Connecting to database...");
			conn = Database.getConnection();

			//System.out.println("Connected to database...");

			//Execute a query
			//System.out.println("Creating statement...");
			String sql = "SELECT ID FROM Users WHERE Username = ?";
			ResultSet rs = null;
			ps = conn.prepareStatement(sql);
			ps.setString(1, book.getOwner().getName());
			rs = ps.executeQuery();
			rs.next();
			ownerID = rs.getInt("ID");
			if(ownerID == 0){
				System.out.println("No OwnerID returned in deleteBook, exiting method");
				return false;
			}
			sql = "INSERT INTO Books (OwnerID, Title, Author, Publisher, Year, ISBN, IsAvailable) VALUES (?, ?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, ownerID);
			ps.setString(2, title);
			ps.setString(3, author);
			ps.setString(4, publisher);
			ps.setInt(5, year);
			ps.setLong(6, isbn);
			ps.setBoolean(7, isAvailable);
			ps.executeUpdate();
			rs.close();
			System.out.println("Done");
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		Database.disposePS(ps);
		Database.disposeConn(conn);
		return true;
	}
	
	
	//Main method for testing purposes
	public static void main(String args[]){
		User user = new User("npalacio", "fakePassword");
//		for(Book book : getMyBooks(user)){
//			System.out.println("Books owned by npalacio: " + book.getTitle());
//		}
		Book book = new Book(0, user, "New Book", "Smith, William", "New Publisher", 1990, 8934736984723L, true);
		//deleteBook(book);
		//addBook(book);
	}
}
