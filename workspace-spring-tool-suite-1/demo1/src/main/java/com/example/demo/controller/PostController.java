package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Post;
import com.example.demo.models.User;
import com.example.demo.response.ApiResponse;
import com.example.demo.services.PostService;
import com.example.demo.services.UserServices;

@RestController
public class PostController {
	
	@Autowired
	PostService postService;
	
	@Autowired
	UserServices userService;
	
	@PostMapping("/api/posts")
	public ResponseEntity<Post> createPost(@RequestHeader("Authorization") String jwt, @RequestBody Post post) throws Exception{
		
		User reqUser = userService.findUserByJwt(jwt);
		
		Post createdPost = postService.createNewPost(post, reqUser.getId());
		return new ResponseEntity<>(createdPost, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId, @RequestHeader("Authorization") String jwt) throws Exception{
		
		User reqUser = userService.findUserByJwt(jwt);
		String message = postService.deletePost(postId, reqUser.getId());
		ApiResponse res = new ApiResponse(message, true);
		return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);
	}
	
	@GetMapping("/posts/{postid}")
	public ResponseEntity<Post> findPostByIdHandler(@PathVariable Integer postid) throws Exception{
		
		Post post = postService.findPostById(postid);
		return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/posts/user/{userid}")
	public ResponseEntity<List<Post>> findUsersPost(@PathVariable Integer userid){
		
		List<Post> posts = postService.findPostByUserId(userid);
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<Post>> findAllPost(){
		
		List<Post> posts = postService.findAllPost();
		return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
	}
	
	@PutMapping("/posts/save/{postId}")
	public ResponseEntity<Post> savedPostHandler(@PathVariable Integer postId, @RequestHeader("Authorization") String jwt) throws Exception{
		
		User reqUser = userService.findUserByJwt(jwt);
		
		Post post = postService.savedPost(postId, reqUser.getId());
		
		return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/posts/like/{postid}")
	public ResponseEntity<Post> likePostHandler(@PathVariable Integer postid, @RequestHeader("Authorization") String jwt) throws Exception{
		
		User reqUser = userService.findUserByJwt(jwt);
		
		Post post = postService.likePost(postid, reqUser.getId());
		
		return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
	}
	
	
}
