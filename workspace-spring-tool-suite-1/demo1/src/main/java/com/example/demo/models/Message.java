package com.example.demo.models;

public class Message {
	private String message;
	private Integer id;
	
	public Message() {
		// TODO Auto-generated constructor stub
	}

	
	public Message(String message, Integer id) {
		super();
		this.message = message;
		this.id = id;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
