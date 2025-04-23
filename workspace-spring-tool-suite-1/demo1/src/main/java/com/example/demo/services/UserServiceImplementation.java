package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.config.JwtProvider;
import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserServices{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User registerUser(User user) {
		
		User user1 = new User();
		user1.setEmail(user.getEmail());
		user1.setFirstName(user.getFirstName());
		user1.setLastName(user.getLastName());
		user1.setPassword(user.getPassword());
		user1.setId(user.getId()); 
		
		User savedUser = userRepository.save(user1);
		
		return savedUser;
	}

	@Override
	public User findUserById(Integer userId) throws Exception {
        Optional<User> user2 = userRepository.findById(userId);
		
		if(user2.isPresent()) return user2.get();
		
		throw new Exception("user does not exist with userId: "+userId);
	}

	@Override
	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(email);
		return user;
	}

	@Override
	public User followUser(Integer requserId, Integer userId2) throws Exception {
		// TODO Auto-generated method stub
		User reqUser = findUserById(requserId);
		
		User user2 = findUserById(userId2);
		
		user2.getFollowers().add(reqUser.getId());
		reqUser.getFollowings().add(user2.getId());
		
		userRepository.save(reqUser);
		userRepository.save(user2);
		return reqUser;
	}

	@Override
	public User updateUser(User user, Integer userId) throws Exception {
		// TODO Auto-generated method stub
		Optional<User> user1 = userRepository.findById(userId);
		
		if(user1.isEmpty()) {
			throw new Exception("user does not exist with ID: "+userId);
		}
		
		User oldUser = user1.get();
		
		if(user.getFirstName() != null) {
			oldUser.setFirstName(user.getFirstName());
		}
		
		if(user.getLastName() != null) {
			oldUser.setLastName(user.getLastName());
		}
		
		if(user.getEmail() != null) {
			oldUser.setEmail(user.getEmail());
		}
		
  		if(user.getPassword() != null) {
			oldUser.setPassword(user.getPassword());
		}
  		
  		if(user.getGender() != null) {
  			oldUser.setGender(user.getGender());
  		}
  		
  		User updatedUser = userRepository.save(oldUser);
		
		return updatedUser;
	}

	@Override
	public List<User> searchUser(String query) {
		
		return userRepository.searchUser(query);
	}

	@Override
	public User findUserByJwt(String jwt) {
		
		String email = JwtProvider.getEmailFromJwtToken(jwt);
		
		User user = userRepository.findByEmail(email);
		return user;
	}
	
}
