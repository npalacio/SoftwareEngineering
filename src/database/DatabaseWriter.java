package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.Book;
import models.Purchase;
import models.Trade;
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
			//System.out.println("Done");
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		Database.disposePS(ps);
		Database.disposeConn(conn);
		return true;
	}
	
	//Method to insert a trade into the database
	public boolean addTrade(Trade trade){
		Connection conn = null;
		PreparedStatement ps = null;
		int senderID = 0;
		String senderUsername = trade.getSender().getName();
		int receiverID = 0;
		String receiverUsername = trade.getRecipient().getName();
		int senderBookID = trade.getSenderBook().getId();
		int receiverBookID = trade.getRecipientBook().getId();
		boolean accepted = trade.isAccepted();
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
			//Get sender user id and receiver user id
			ps.setString(1, senderUsername);
			rs = ps.executeQuery();
			if(rs.next()) {
				senderID = rs.getInt("ID");
			} else {
				//System.out.println("No OwnerID returned in addTrade, exiting method");
				return false;
			}
			ps = conn.prepareStatement(sql);
			ps.setString(1, receiverUsername);
			rs = ps.executeQuery();
			if(rs.next()) {
				receiverID = rs.getInt("ID");
			} else {
				//System.out.println("No OwnerID returned in addTrade, exiting method");
				return false;
			}
			sql = "INSERT INTO Trades (SenderID, ReceiverID, SenderBookID, ReceiverBookID, Accepted) VALUES (?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, senderID);
			ps.setInt(2, receiverID);
			ps.setInt(3, senderBookID);
			ps.setInt(4, receiverBookID);
			ps.setBoolean(5, accepted);
			ps.executeUpdate();
			rs.close();
			//System.out.println("Done");
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		Database.disposePS(ps);
		Database.disposeConn(conn);
		return true;
	}
	
	public boolean removeTradeById(int Id) {
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = Database.getConnection();
			String sql = "DELETE FROM Trades WHERE ID = ?";
			ps = conn.prepareStatement(sql);
			//Get sender user id and receiver user id
			ps.setInt(1, Id);
			ps.executeUpdate();
//			System.out.println("Done");
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		Database.disposePS(ps);
		Database.disposeConn(conn);
		return true;
	}
	
	//Add method to update trade to accepted or not
	//If a trade is accepted or rejected we are going to remove it from the database no matter what
	//For now we will not do anything other than remove the trade, in the future we should implement a way to notify the
	//person who proposed the trade that it was accepted/rejected
	public boolean respondToTrade(Trade trade){
		Connection conn = null;
		PreparedStatement ps = null;
		int senderID = 0;
		String senderUsername = trade.getSender().getName();
		int receiverID = 0;
		String receiverUsername = trade.getRecipient().getName();
		int senderBookID = trade.getSenderBook().getId();
		int receiverBookID = trade.getRecipientBook().getId();
		try{
			conn = Database.getConnection();
			String sql = "SELECT ID FROM Users WHERE Username = ?";
			ResultSet rs = null;
			ps = conn.prepareStatement(sql);
			//Get sender user id and receiver user id
			ps.setString(1, senderUsername);
			rs = ps.executeQuery();
			if(rs.next()) {
				senderID = rs.getInt("ID");
			} else {
				System.out.println("No OwnerID returned in addTrade, exiting method");
				return false;
			}
			ps = conn.prepareStatement(sql);
			ps.setString(1, receiverUsername);
			rs = ps.executeQuery();
			if(rs.next()) {
				receiverID = rs.getInt("ID");
			} else {
				System.out.println("No OwnerID returned in addTrade, exiting method");
				return false;
			}
			sql = "DELETE FROM Trades WHERE SenderID = ? AND ReceiverID = ? AND SenderBookID = ? AND ReceiverBookID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, senderID);
			ps.setInt(2, receiverID);
			ps.setInt(3, senderBookID);
			ps.setInt(4, receiverBookID);
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
	
	//Method to insert a trade into the database
	public boolean addUser(User user){
		Connection conn = null;
		PreparedStatement ps = null;
		String username = user.getName();
		String password = user.getPassword();
		try{
			conn = Database.getConnection();
			String sql = "INSERT INTO Users (Username, Password) VALUES (?, ?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.executeUpdate();
			//System.out.println("Done");
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		Database.disposePS(ps);
		Database.disposeConn(conn);
		return true;
	}
	
	//Main method for testing purposes
//	public static void main(String args[]){
//		User user1 = new User("jacob", "smith");
//		User user2 = new User("person", "human");
//		DatabaseReader dbr = new DatabaseReader();
//		for(Book book : getMyBooks(user)){
//			System.out.println("Books owned by npalacio: " + book.getTitle());
//		}
//		Book book = new Book(0, user, "New Book", "Smith, William", "New Publisher", 1990, 8934736984723L, true);
//		Book senderBook = dbr.findBook(5);
//		System.out.println("Sender book = " + senderBook.getTitle());
//		Book receiverBook = dbr.findBook(8);
//		System.out.println("Receiver book = " + receiverBook.getTitle());
//		Trade trade = new Trade(user1, user2, senderBook, receiverBook);
		//addUser(user2);
		//respondToTrade(trade);
		//deleteBook(book);
		//addBook(book);
//	}
}
