package models;

import java.math.BigInteger;

//This is the class to model a book
public class Book {

	private User owner;
	private String title;
	private String author;
	private String publisher;
	private int year;
	private long ISBN;
	private boolean isAvailable;
	
	public Book(User owner, String title, String author, String publisher, int year, long isbn, boolean isAvailable) {
		this.owner = owner;
		this.title = title;
		this.author= author;
		this.publisher = publisher;
		this.year = year;
		this.ISBN = isbn;
		this.isAvailable = isAvailable;
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
	
	
}
