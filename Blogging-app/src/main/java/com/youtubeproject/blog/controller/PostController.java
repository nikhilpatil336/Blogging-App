package com.youtubeproject.blog.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.youtubeproject.blog.config.AppConstants;
import com.youtubeproject.blog.entities.Post;
import com.youtubeproject.blog.payloads.ApiResponse;
import com.youtubeproject.blog.payloads.PostDTO;
import com.youtubeproject.blog.payloads.PostResponse;
import com.youtubeproject.blog.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;
	
	//create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO, 
			@PathVariable Integer userId,@PathVariable Integer categoryId)
	{
		PostDTO createdpostDTO = postService.createPost(postDTO, userId, categoryId);
		return new ResponseEntity<>(createdpostDTO, HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable Integer userId){
		List<PostDTO> posts = postService.getAllPostsOfUser(userId);
		return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK);
	}	
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable Integer categoryId){
		List<PostDTO> posts = postService.getAllPostsOfCategory(categoryId);
		return new ResponseEntity<List<PostDTO>>(posts, HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber
			, @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize
			, @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy
			, @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir){
		PostResponse allPosts = postService.getAllPosts(pageNumber-1, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(allPosts, HttpStatus.OK);
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> getSinglePosts(@PathVariable Integer postId){
		PostDTO post = postService.getSinglePost(postId);
		return new ResponseEntity<PostDTO>(post, HttpStatus.OK);
	}
	
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
		postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("post got deleted", true), HttpStatus.OK);
	}
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postDTO, @PathVariable Integer postId){
		PostDTO post = postService.updatePost(postDTO, postId);
		return new ResponseEntity<PostDTO>(post, HttpStatus.OK);
	}
	
	@GetMapping("/posts/{key_word}")
	public ResponseEntity<List<PostDTO>> searchPostByTitle(@RequestParam String key_word){
		List<PostDTO> postDTOs = postService.searchPost(key_word);
		return new ResponseEntity<List<PostDTO>>(postDTOs, HttpStatus.OK);
	}
}