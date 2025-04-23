package com.example.demo.services;

import java.util.List;

import com.example.demo.models.User;

public interface UserServices {
	
	public User registerUser(User user);
	
	public User findUserById(Integer userId) throws Exception;
	
	public User findUserByEmail(String email);
	
	public User followUser(Integer userId1, Integer userId2) throws Exception;
	
	public User updateUser(User user, Integer userId) throws Exception;
	
	public List<User> searchUser(String query);
	
	public User findUserByJwt(String jwt);
}
