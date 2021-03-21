package com.chatground.model;

public class ChatGroundMessage {

	private String type;
	private String sender;
	private String message;
	private Integer onlineCounter;
	
	public ChatGroundMessage() {
		super();
	}
	
	public ChatGroundMessage(String type, String sender, String message, int onlineCounter) {
		this.type = type;
		this.sender = sender;
		this.message = message;
		this.onlineCounter = onlineCounter;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getOnlineCounter() {
		return onlineCounter;
	}

	public void setOnlineCounter(Integer onlineCounter) {
		this.onlineCounter = onlineCounter;
	}
	
	
	public static void main(String[] args) {

	}

}
