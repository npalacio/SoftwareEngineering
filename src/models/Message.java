package models;

public class Message {
	private int Id;
	private User receiver;
	private String message;
	
	public Message(User receiver, String message){
		this.receiver = receiver;
		this.message = message;
	}
	
	public Message(int Id, User receiver, String message){
		this.Id = Id;
		this.receiver = receiver;
		this.message = message;
	}

	public int getId() {
		return Id;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
