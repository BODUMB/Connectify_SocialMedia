package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {
	
	@GetMapping
	public String HomeControllerHandler() {
		return "This is home Controller for my Endpoint";
	}
	
	@GetMapping("/home")
	public String HomeControllerHandler2() {
		return "This is Second(2) home Controller for my Endpoint";
	}
	
	@GetMapping("/home2")
	public String HomeControllerHandler3() {
		return "Click to Upload";
	}
	
}
