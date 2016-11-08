package models;

import java.math.BigInteger;

//This is the class to model a book
public class Book {

	private int Id;
	private User owner;
	private String title;
	private String author;
	private String publisher;
	private int year;
	private long ISBN;
	private double price;
	private boolean isAvailable;
	
	public Book(int id, User owner, String title, String author, String publisher, int year, long isbn, double price, boolean isAvailable) {
		this.Id = id;
		this.owner = owner;
		this.title = title;
		this.author= author;
		this.publisher = publisher;
		this.year = year;
		this.ISBN = isbn;
		this.price = price;
		this.isAvailable = isAvailable;
	}

	//A separate constructor used when adding a book, we do not populate the ID field since this will be populated
	//automatically when we put it in the database
	public Book(User owner, String title, String author, String publisher, int year, long isbn, boolean isAvailable) {
		this.owner = owner;
		this.title = title;
		this.author= author;
		this.publisher = publisher;
		this.year = year;
		this.ISBN = isbn;
		this.isAvailable = isAvailable;
	}
	
	//This property should be read-only in the code so that it stays in sync with the database primary key of ID
	public int getId() {
		return Id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getYear() {
		return year;
	}

	public void setyear(int year) {
		this.year = year;
	}

	public long getISBN() {
		return ISBN;
	}

	public void setISBN(long iSBN) {
		ISBN = iSBN;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}
