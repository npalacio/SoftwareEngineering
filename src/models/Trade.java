package models;

//This class will model a 'trade' object that will be created when someone proposes a trade
public class Trade {
	
	private Book book;
	private String seller;
	private double price;
	
	public Trade(Book book, String seller, double price) {
		this.book = book;
		this.seller = seller;
		this.price = price;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
