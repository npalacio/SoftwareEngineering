package models;

//This is the class to model a book
public class Book {

	private String name;
	private String author;
	private String publisher;
	private String date;
	private int ISBN;
	
	public Book(String name, String author, String publisher, String date, int ISBN) {
		this.name = name;
		this.author = author;
		this.publisher = publisher;
		this.date = date;
		this.ISBN = ISBN;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getISBN() {
		return ISBN;
	}

	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}
	
	
}
