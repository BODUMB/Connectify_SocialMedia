package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Comment;
import com.example.demo.models.Post;
import com.example.demo.models.User;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;

@Service
public class CommentServiceImplementation implements CommentService{
	
	@Autowired
	UserServices userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	PostService postService;
	
	@Autowired
	CommentRepository commentRepository;

	@Override
	public Comment createComment(Comment comment, Integer userid, Integer postId) throws Exception {
		
		Post post = postService.findPostById(postId);
		User user = userService.findUserById(userid);
		
		Comment newComment = new Comment();
		newComment.setId(comment.getId());
		newComment.setMessage(comment.getMessage());
		newComment.setPost(post);
		newComment.setUser(user);
		return newComment;
	}

	@Override
	public Comment getComment(Integer commentId) throws Exception {
		Optional<Comment> newCommment = commentRepository.findById(commentId);
		
		if(newCommment.isEmpty()) {
			throw new Exception("Comment not found with Id "+commentId);
		}
		return newCommment.get();
	}

	@Override
	public List<Comment> getAllComments(Integer postId) {
		
		return commentRepository.findAll();
	}

	@Override
	public Comment LikedComment(Integer userId, Integer CommentId) throws Exception {
		Comment newComment = getComment(CommentId);
		User user = userService.findUserById(userId);
		
		if(newComment.getLiked().contains(user)) {
			newComment.getLiked().remove(user);
		}
		else {
			newComment.getLiked().add(user);
		}
;		return commentRepository.save(newComment);
	}
	
	
}
