package models;

public class Purchase {

	private Book book;
	private User seller;
	private User buyer;
	private double price;
	
	public Purchase(Book book, User buyer, User seller, double price) {
		this.book = book;
		this.seller = seller;
		this.buyer = buyer;
		this.price = price;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}
	
	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
