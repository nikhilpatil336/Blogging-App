package com.youtubeproject.blog.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youtubeproject.blog.entities.Comment;
import com.youtubeproject.blog.entities.Post;
import com.youtubeproject.blog.exceptions.ResourceNotFoundException;
import com.youtubeproject.blog.payloads.CommentDTO;
import com.youtubeproject.blog.repositories.CommentRepository;
import com.youtubeproject.blog.repositories.PostRepository;
import com.youtubeproject.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDTO createComment(CommentDTO commentDTO, Integer postId) {
		
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", postId));
		
		Comment comment = modelMapper.map(commentDTO, Comment.class);
		
		comment.setPost(post);
		
		Comment savedComment = commentRepository.save(comment);
		
		return modelMapper.map(savedComment, CommentDTO.class);
	}

	@Override
	public void deleteComment(Integer CommentId) {
		
		 Comment comment = commentRepository.findById(CommentId)
				.orElseThrow(() -> new ResourceNotFoundException("comment", CommentId));
		
		 commentRepository.delete(comment);
	}
	
	

}
