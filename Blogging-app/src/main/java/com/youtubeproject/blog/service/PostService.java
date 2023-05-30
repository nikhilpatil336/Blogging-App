package com.youtubeproject.blog.service;

import java.util.List;

import com.youtubeproject.blog.payloads.PostDTO;
import com.youtubeproject.blog.payloads.PostResponse;

public interface PostService {

	//create
	PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId);
	
	//update
	PostDTO updatePost(PostDTO postDTO, Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
	//get all 
	PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	//get all by users
	List<PostDTO> getAllPostsOfUser(Integer userId);
	
	//get all by category
	List<PostDTO> getAllPostsOfCategory(Integer categoryId);
	
	// get by id
	PostDTO getSinglePost(Integer postId);	
	
	//Search post
	List<PostDTO> searchPost(String keyword);
}
