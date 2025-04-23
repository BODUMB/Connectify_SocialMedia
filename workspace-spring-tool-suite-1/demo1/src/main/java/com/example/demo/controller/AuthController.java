package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.JwtProvider;
import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.response.AuthResponse;
import com.example.demo.response.LoginRequest;
import com.example.demo.services.CustomUserDetailsService;
import com.example.demo.services.UserServices;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserServices userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomUserDetailsService customUserDetails;
	
	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody User user) throws Exception {
		
		User isExist = userRepository.findByEmail(user.getEmail());
		
		if(isExist != null) {
			throw new Exception("This email already used with another account");
		}
		
		User user1 = new User();
		user1.setEmail(user.getEmail());
		user1.setFirstName(user.getFirstName());
		user1.setLastName(user.getLastName());
		user1.setPassword(passwordEncoder.encode(user.getPassword()));
		
		User savedUser = userRepository.save(user1);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
		
		String token = JwtProvider.generateToken(authentication);
		
		AuthResponse res = new AuthResponse(token, "Register Successs");
		
		return res;
	}
	
	@PostMapping("/signin")
	public AuthResponse signin(@RequestBody LoginRequest loginRequest) {
		
		Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
		
		String token = JwtProvider.generateToken(authentication);
		
		AuthResponse res = new AuthResponse(token, "Login Success");
		
		return res;
	}

	private Authentication authenticate(String email, String password) {
		UserDetails userDetails = customUserDetails.loadUserByUsername(email);
		
		if(userDetails == null) {
			throw new BadCredentialsException("Invalid Username");
		}
		
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Password not matched");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}
	
}
