package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Comment;

public interface CommentService {
	
	Comment createComment(Comment comment, Integer userid, Integer postId) throws Exception;
	
	Comment getComment(Integer commentId) throws Exception;
	
	List<Comment> getAllComments(Integer postId);
	
	Comment LikedComment(Integer userId, Integer CommentId) throws Exception;
}
