package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Comment;
import com.example.demo.services.CommentService;

@RestController
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@PostMapping("/comment/{userid}/{postid}")
	public ResponseEntity<Comment> createComment(@RequestBody Comment comment, @PathVariable Integer userid, @PathVariable Integer Postid) throws Exception{
		Comment createdComment = commentService.createComment(comment, userid, Postid);
		return new ResponseEntity<>(createdComment,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/comment/{commentId}")
	public ResponseEntity<Comment> getComment(@PathVariable Integer commentId) throws Exception{
		Comment comment1 = commentService.getComment(commentId);
		return new ResponseEntity<Comment>(comment1, HttpStatus.OK);
	}
	
	@GetMapping("/comment/{postid}")
	public ResponseEntity<List<Comment>> getAllComments(@PathVariable Integer postid){
		List<Comment> comments = commentService.getAllComments(postid);
		return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
		
	}
	
	@PutMapping("/comment/{commentid}/{userid}")
	public ResponseEntity<Comment> likedComment(@PathVariable Integer commentid, @PathVariable Integer userid) throws Exception{
		Comment comment = commentService.LikedComment(userid, commentid);
		return new ResponseEntity<Comment>(comment, HttpStatus.ACCEPTED);
	}
}
