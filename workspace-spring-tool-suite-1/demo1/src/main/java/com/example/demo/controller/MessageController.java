package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Message;

@RestController
public class MessageController {
	
	@GetMapping("/messages")
	public List<Message> getMessage(){
		 List<Message> messages = new ArrayList<>();
		 
		 Message m1 = new Message("Behind you !!!",1);
		 
		 Message m2 = new Message("Be Careful what you wish for...",2);
		 
		 messages.add(m1);
		 messages.add(m2);
		 return messages;
	}
	
	@GetMapping("/messages/{messageid}")
	public Message findMessage(@PathVariable Integer messageid) {
		Message m1 = new Message("Is this is the power of GOD ????!!!!",1);
		m1.setId(messageid);
		
		return m1;
	}
	
	@PostMapping("/messages/post")
	public Message postMessage(@RequestBody Message m) {
		Message m1 = new Message();
		m1.setId(m.getId());
		m1.setMessage(m.getMessage());
		
		return m1;
	}
	
	@PutMapping("messages/edit")
	public Message editMessage(@RequestBody Message m) {
		Message m1 = new Message();
		m1.setId(m.getId());
		m1.setMessage(m.getMessage());
		
		if(m1.getId() != null) {
			m1.setId(m.getId());
		}
		
		if(m1.getMessage() != null) {
			m1.setMessage(m.getMessage());
		}
		
		return m1;
	}
	
	@DeleteMapping("messages/delete/{messageid}")
	public String deleteMessage(@PathVariable Integer messageid) {
		
		return "Message Deleted Successfully. ID: "+messageid;
	}
}
