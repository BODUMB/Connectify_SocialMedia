package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UserServices;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class UserController {
	
	@Autowired 
	UserRepository userRepository;
	
	@Autowired
	UserServices userService;
	
//	@PostMapping("users/post")
//	public User createUser(@RequestBody User user) {
//		
//		User savedUser = userService.registerUser(user);
//		
//		return savedUser;
//	}
	
	@GetMapping("/api/users")
	public List<User> getUsers() {
		
		List<User> users = userRepository.findAll();
		return users;
	}
	
	@GetMapping("/api/users/{userId}")
	public User getUserById(@PathVariable("userId") Integer id) throws Exception{
		User user = userService.findUserById(id);
		return user;
		
	}
	
//	@GetMapping("/users/{userEmail}")
//	public User getUserByEmail(@PathVariable("userEmail") String Email){
//		User fuser = userService.findUserByEmail(Email);
//		return fuser;
//		
//	}
	
	
	@PutMapping("/api/users")
	public User updateUser(@RequestHeader("Authorization") String jwt, @RequestBody User user) throws Exception {
		
		User reqUser = userService.findUserByJwt(jwt);
		
		User updatedUser = userService.updateUser(user, reqUser.getId());
		
		return updatedUser;
	}
	
	@PutMapping("/api/users/{id2}")
	public User followUserHandler(@RequestHeader("Authorization") String jwt, @PathVariable Integer id2) throws Exception {
		
		User reqUser = userService.findUserByJwt(jwt);
		
		
		User user = userService.followUser(reqUser.getId(), id2);
		return user;
	}
	
	@DeleteMapping("users/{userid}")
	public String deleteUser(@PathVariable Integer userid) throws Exception {
		
		Optional<User> user = userRepository.findById(userid);
		
		if(user.isEmpty()) {
			throw new Exception("user does not exist with ID: "+userid);
		}
		
		userRepository.delete(user.get());
		 
		return "User Deleted Successfully "+userid;
	}
	
	@GetMapping("api/users/search")
	public List<User> searchUser(@RequestParam("query") String query){
		
		List<User> users = userService.searchUser(query);
		return users;
	}
	
	@GetMapping("api/users/profile")
	public User getUserFromToken(@RequestHeader("Authorization") String jwt) {
		
//		System.out.println("JWT -- "+jwt);
		
		User user = userService.findUserByJwt(jwt);
		
		user.setPassword(null);
		
		return user;
	}
}
