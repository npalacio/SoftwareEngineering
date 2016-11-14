package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import models.Book;
import models.Purchase;
import models.Trade;
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
		double price = 0;
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
				sql = "SELECT ID, OwnerID, Title, Author, Publisher, Year, ISBN, Price, IsAvailable FROM Books WHERE IsAvailable = 1 ORDER BY " 
						+ columnToSortBy + ";";
			} else {
				sql = "SELECT ID, OwnerID, Title, Author, Publisher, Year, ISBN, Price, IsAvailable FROM Books WHERE IsAvailable = 1 ORDER BY Title;";
			}
			
			ResultSet rs, rs2 = null;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next())
			{
				//Get the book information
				id = rs.getInt("ID");
				ownerID = rs.getInt("OwnerID");
				year = rs.getInt("Year");
				ISBN = rs.getLong("ISBN");
				price = rs.getDouble("Price");
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
				availableBooks.add(new Book(id, user, title, author, publisher, year, ISBN, price, true));
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
	
	public Book findBook(int id) {
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		Book book = null;
		User user = null;
		int ownerID = 0;
		int year = 0;
		long ISBN = 0;
		double price = 0;
		String title, author, publisher = null;
		try {
			conn = Database.getConnection();
			String sql = "SELECT OwnerID, Title, Author, Publisher, Year, ISBN, Price, IsAvailable FROM Books WHERE ID = " + id + ";";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			ownerID = rs.getInt("OwnerID");
			year = rs.getInt("Year");
			ISBN = rs.getLong("ISBN");
			price = rs.getDouble("Price");
			title = rs.getString("Title");
			author = rs.getString("Author");
			publisher = rs.getString("Publisher");
			
			
			sql = "SELECT Username, Password FROM Users WHERE ID = " + ownerID + ";";
			ps2 = conn.prepareStatement(sql);
			ResultSet rs2 = ps2.executeQuery();
			rs2.next();
			user = new User(rs2.getString("Username"), rs2.getString("Password"));
			rs2.close();
			rs.close();
			
			book = new Book(id, user, title, author, publisher, year, ISBN, price, true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Database.disposePS(ps);
		Database.disposePS(ps2);
		Database.disposeConn(conn);
		return book;
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
		double price = 0;
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
				sql = "SELECT ID, Title, Author, Publisher, Year, ISBN, Price, IsAvailable FROM Books WHERE OwnerID = ? ORDER BY " 
						+ columnToSortBy + ";";
			} else {
				sql = "SELECT ID, Title, Author, Publisher, Year, ISBN, Price, IsAvailable FROM Books WHERE OwnerID = ? ORDER BY Title;";
			}
			ps = conn.prepareStatement(sql);
			ps.setInt(1, ownerID);
			rs = ps.executeQuery();
			while(rs.next()){
				id = rs.getInt("ID");
				title = rs.getString("Title");
				author = rs.getString("Author");
				publisher = rs.getString("publisher");
				year = rs.getInt("Year");
				ISBN = rs.getLong("ISBN");
				price = rs.getDouble("Price");
				isAvailable = rs.getBoolean("IsAvailable");
				availableBooks.add(new Book(id, user, title, author, publisher, year, ISBN, price, isAvailable));
			}
			rs.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		Database.disposePS(ps);
		Database.disposeConn(conn);
		return availableBooks;
	}
	
	public boolean isValidUser(User user){
		Connection conn = null;
		PreparedStatement ps = null;
		String username = user.getName();
		String password = user.getPassword();
		String validatedUsername = null;
		String validatedPassword = null;
		try{
			conn = Database.getConnection();
			//BINARY keyword because case-sensitive comparison only on Password comparison
			String sql = "SELECT Username, Password FROM Users WHERE BINARY Password = ? AND Username = ?;";
			ResultSet rs = null;
			ps = conn.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, username);
			rs = ps.executeQuery();
			while(rs.next()){
				validatedUsername = rs.getString("Username");
				validatedPassword = rs.getString("Password");
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		Database.disposePS(ps);
		Database.disposeConn(conn);
		//If the query returned any value, it is a valid user
		return validatedUsername != null && validatedPassword != null;
	}
	
	public Trade findTrade(int Id){
		Connection conn = null;
		Connection conn2 = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		Trade trade = null;
		Book senderBook = null;
		Book receiverBook = null;
		User sender = null;
		User receiver = null;
		String receiverUsername = null;
		String receiverPassword = null;
		String senderUsername = null;
		String senderPassword = null;
		int userID = 0;
		int senderID = 0;
		int receiverID = 0;
		int senderBookID = 0;
		int receiverBookID = 0;
		try{
			conn = Database.getConnection();
			String sql = "SELECT ID, SenderID, ReceiverID, SenderBookID, ReceiverBookID FROM Trades WHERE ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, Id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Id = rs.getInt("ID");
				senderID = rs.getInt("SenderID");
				senderBookID = rs.getInt("SenderBookID");
				receiverID = rs.getInt("ReceiverID");
				receiverBookID = rs.getInt("ReceiverBookID");
				if(senderBookID != 0 && receiverBookID != 0){
					senderBook = findBook(senderBookID);
					receiverBook = findBook(receiverBookID);
					if(senderID != 0) {
						conn2 = Database.getConnection();
						sql = "SELECT Username, Password FROM Users WHERE ID = ?;";
						ps2 = conn2.prepareStatement(sql);
						ps2.setInt(1, senderID);
						ResultSet rs2 = ps2.executeQuery();
						while(rs2.next()){
							senderUsername = rs2.getString("Username");
							senderPassword = rs2.getString("Password");
						}
						if(senderUsername != null && senderPassword != null){
							sender = new User(senderUsername, senderPassword);
						} else {
							System.out.println("No sending user returned in getTradesById, returning current list of trades");
							break;
							//return trades;
						}
						rs2.close();
					} else {
						System.out.println("No senderID user returned in getTradesById, returning current list of trades");
						break;
						//return trades;
					} 
					if(receiverID != 0) {
						conn2 = Database.getConnection();
						sql = "SELECT Username, Password FROM Users WHERE ID = ?;";
						ps2 = conn2.prepareStatement(sql);
						ps2.setInt(1, receiverID);
						ResultSet rs2 = ps2.executeQuery();
						while(rs2.next()){
							receiverUsername = rs2.getString("Username");
							receiverPassword = rs2.getString("Password");
						}
						if(receiverUsername != null && receiverPassword != null){
							receiver = new User(receiverUsername, receiverPassword);
						} else {
							System.out.println("No receiving user returned in getTradesById, returning current list of trades");
							break;
							//return trades;
						}
					} else {
						System.out.println("No receiverID user returned in getTradesById, returning current list of trades");
						break;
						//return trades;
					}if(senderBook != null && receiverBook != null) {
						trade = new Trade(Id, sender, receiver, senderBook, receiverBook);
					} else {
						System.out.println("No book returned in get trades by receiver, returning current list of trades");
						break;
						//return trades;
					}
				}
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		Database.disposePS(ps2);
		Database.disposeConn(conn2);
		Database.disposePS(ps);
		Database.disposeConn(conn);
		return trade;
	}

	
	public List<Trade> getTradesByReceiver(User receiver){
		Connection conn = null;
		Connection conn2 = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		List<Trade> trades = new LinkedList<Trade>();
		Book senderBook = null;
		Book receiverBook = null;
		User sender = null;
		String username = receiver.getName();
		String password = receiver.getPassword();
		String senderUsername = null;
		String senderPassword = null;
		int Id = 0;
		int userID = 0;
		int senderID = 0;
		int senderBookID = 0;
		int receiverBookID = 0;
		try{
			conn = Database.getConnection();
			//BINARY keyword because case-sensitive comparison only on Password comparison
			String sql = "SELECT ID FROM Users WHERE BINARY Password = ? AND Username = ?;";
			ResultSet rs = null;
			ResultSet rs2 = null;
			ps = conn.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, username);
			rs = ps.executeQuery();
			while(rs.next()){
				userID = rs.getInt("ID");
			}
			if(userID == 0) {
				System.out.println("No userID returned in getTradesByReceiver, returning empty list");
				return trades;
			}
			sql = "SELECT ID, SenderID, SenderBookID, ReceiverBookID FROM Trades WHERE ReceiverID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userID);
			rs = ps.executeQuery();
			while(rs.next()){
				Id = rs.getInt("ID");
				senderID = rs.getInt("SenderID");
				senderBookID = rs.getInt("SenderBookID");
				receiverBookID = rs.getInt("ReceiverBookID");
				if(senderBookID != 0 && receiverBookID != 0){
					senderBook = findBook(senderBookID);
					receiverBook = findBook(receiverBookID);
					if(senderID != 0) {
						conn2 = Database.getConnection();
						sql = "SELECT Username, Password FROM Users WHERE ID = ?;";
						ps2 = conn2.prepareStatement(sql);
						ps2.setInt(1, senderID);
						rs2 = ps2.executeQuery();
						while(rs2.next()){
							senderUsername = rs2.getString("Username");
							senderPassword = rs2.getString("Password");
						}
						if(senderUsername != null && senderPassword != null){
							sender = new User(senderUsername, senderPassword);
						} else {
							System.out.println("No sending user returned in getTradesByReceiver, returning current list of trades");
							break;
							//return trades;
						}
					} else {
						System.out.println("No senderID user returned in getTradesByReceiver, returning current list of trades");
						break;
						//return trades;
					} if(senderBook != null && receiverBook != null) {
						trades.add(new Trade(Id, sender, receiver, senderBook, receiverBook));
					} else {
						System.out.println("No book returned in get trades by receiver, returning current list of trades");
						break;
						//return trades;
					}
				}
			}
			rs2.close();
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		Database.disposePS(ps2);
		Database.disposeConn(conn2);
		Database.disposePS(ps);
		Database.disposeConn(conn);
		return trades;
	}
	
	public List<Trade> getTradesBySender(User sender){
		Connection conn = null;
		Connection conn2 = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		List<Trade> trades = new LinkedList<Trade>();
		Book senderBook = null;
		Book receiverBook = null;
		User receiver = null;
		String username = sender.getName();
		String password = sender.getPassword();
		String receiverUsername = null;
		String receiverPassword = null;
		int Id = 0;
		int userID = 0;
		int receiverID = 0;
		int senderBookID = 0;
		int receiverBookID = 0;
		try{
			conn = Database.getConnection();
			//BINARY keyword because case-sensitive comparison only on Password comparison
			String sql = "SELECT ID FROM Users WHERE BINARY Password = ? AND Username = ?;";
			ResultSet rs = null;
			ResultSet rs2 = null;
			ps = conn.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, username);
			rs = ps.executeQuery();
			while(rs.next()){
				userID = rs.getInt("ID");
			}
			if(userID == 0) {
				System.out.println("No userID returned in getTradesBySender, returning empty list");
				return trades;
			}
			sql = "SELECT ID, ReceiverID, SenderBookID, ReceiverBookID FROM Trades WHERE SenderID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userID);
			rs = ps.executeQuery();
			while(rs.next()){
				Id = rs.getInt("ID");
				receiverID = rs.getInt("ReceiverID");
				senderBookID = rs.getInt("SenderBookID");
				receiverBookID = rs.getInt("ReceiverBookID");
				if(senderBookID != 0 && receiverBookID != 0){
					senderBook = findBook(senderBookID);
					receiverBook = findBook(receiverBookID);
					if(receiverID != 0) {
						conn2 = Database.getConnection();
						sql = "SELECT Username, Password FROM Users WHERE ID = ?;";
						ps2 = conn2.prepareStatement(sql);
						ps2.setInt(1, receiverID);
						rs2 = ps2.executeQuery();
						while(rs2.next()){
							receiverUsername = rs2.getString("Username");
							receiverPassword = rs2.getString("Password");
						}
						if(receiverUsername != null && receiverPassword != null){
							receiver = new User(receiverUsername, receiverPassword);
						} else {
							System.out.println("No sending user returned in getTradesBySender, returning current list of trades");
							break;
							//return trades;
						}
					} else {
						System.out.println("No senderID user returned in getTradesBySender, returning current list of trades");
						break;
						//return trades;
					} if(senderBook != null && receiverBook != null) {
						trades.add(new Trade(Id, sender, receiver, senderBook, receiverBook));
					} else {
						System.out.println("No book returned in getTradesBySender, returning current list of trades");
						break;
						//return trades;
					}
				}
			}
			rs2.close();
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		Database.disposePS(ps2);
		Database.disposeConn(conn2);
		Database.disposePS(ps);
		Database.disposeConn(conn);
		return trades;
	}
	
	//Main method for testing purposes
	public static void main(String args[]){
//		User user = new User("jstein", "stein");
//		List<Trade> trades = DatabaseReader.getTradesByReceiver(user);
//		for(Trade trade : trades){
//			System.out.println("Trade from " + trade.getSender().getName() + " to " + trade.getRecipient().getName());
//			System.out.println("\tSBook: " + trade.getSenderBook().getTitle() + ", RBook: " + trade.getRecipientBook().getTitle());
//		}
//		for(Book book : getMyBooks(user)){
//			System.out.println("Books owned by npalacio: " + book.getTitle());
//		}
//		System.out.println(isValidUser(user));
//		Trade trade = findTrade(1);
//		System.out.println("Sender: " + trade.getSender().getName());
//		System.out.println("Receiver: " + trade.getRecipient().getName());
//		System.out.println("SenderBook: " + trade.getSenderBook().getTitle());
//		System.out.println("ReceiverBook: " + trade.getRecipientBook().getTitle());
	}
}
