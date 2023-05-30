package com.youtubeproject.blog.service;

import com.youtubeproject.blog.payloads.CommentDTO;

public interface CommentService {

	CommentDTO createComment(CommentDTO commentDTO, Integer postId);
	
	void deleteComment(Integer CommentId);
}
