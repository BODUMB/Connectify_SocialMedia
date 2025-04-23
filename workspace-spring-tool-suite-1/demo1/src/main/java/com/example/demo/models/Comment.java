package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String message;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Post post;
	@OneToMany
	private List<User> liked = new ArrayList<>();
	
	public Comment() {
		// TODO Auto-generated constructor stub
	}


	public Comment(Integer id, String message, User user, Post post, List<User> liked) {
		super();
		this.id = id;
		this.message = message;
		this.user = user;
		this.post = post;
		this.liked = liked;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Post getPost() {
		return post;
	}


	public void setPost(Post post) {
		this.post = post;
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

	public List<User> getLiked() {
		return liked;
	}

	public void setLiked(List<User> liked) {
		this.liked = liked;
	}
	
	
}
