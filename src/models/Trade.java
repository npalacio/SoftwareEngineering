package models;

//This class will model a 'trade' object that will be created when someone proposes a trade
public class Trade {
	
	private int id;
	private User sender;
	private User recipient;
	private Book senderBook;
	private Book recipientBook;
	private boolean accepted;
	
	public Trade(int id, User s, User r, Book sBook, Book rBook){
		this.sender = s;
		this.recipient = r;
		this.senderBook = sBook;
		this.recipientBook = rBook;
		this.id = id;
	}
	
	public Trade(User s, User r, Book sBook, Book rBook){
		this.sender = s;
		this.recipient = r;
		this.senderBook = sBook;
		this.recipientBook = rBook;
	}
	
	//Id is a read-only property so that it stay in sync with the database
	public int getId(){
		return this.id;
	}
	
	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getRecipient() {
		return recipient;
	}

	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}

	public Book getSenderBook() {
		return senderBook;
	}

	public void setSenderBook(Book senderBook) {
		this.senderBook = senderBook;
	}

	public Book getRecipientBook() {
		return recipientBook;
	}

	public void setRecipientBook(Book recipientBook) {
		this.recipientBook = recipientBook;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}


}
