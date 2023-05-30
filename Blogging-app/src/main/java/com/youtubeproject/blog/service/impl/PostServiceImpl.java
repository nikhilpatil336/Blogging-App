package com.youtubeproject.blog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.youtubeproject.blog.entities.Category;
import com.youtubeproject.blog.entities.Post;
import com.youtubeproject.blog.entities.User;
import com.youtubeproject.blog.exceptions.ResourceNotFoundException;
import com.youtubeproject.blog.payloads.PostDTO;
import com.youtubeproject.blog.payloads.PostResponse;
import com.youtubeproject.blog.repositories.CategoryRepository;
import com.youtubeproject.blog.repositories.PostRepository;
import com.youtubeproject.blog.repositories.UserRepository;
import com.youtubeproject.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId) {
		
		User user = userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", userId));
		
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", categoryId));
		
		Post post = modelMapper.map(postDTO, Post.class);
		
		post.setImageName("Default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost = postRepository.save(post);
		
		return modelMapper.map(newPost, PostDTO.class);
	}

	@Override
	public PostDTO updatePost(PostDTO postDTO, Integer postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", postId));
		
		post.setContent(postDTO.getContent());
		post.setTitle(postDTO.getTitle());
		post.setImageName(postDTO.getImageName());
		
		Post updatedPost = postRepository.save(post);
		
		return modelMapper.map(updatedPost, PostDTO.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", postId));
		
		postRepository.delete(post);
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		
		//If else condition with ternary operator
		Sort sort = sortDir.equals("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending(); 
		
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);	
		
		Page<Post> pageOfPosts = postRepository.findAll(p);
		
		List<Post> posts = pageOfPosts.getContent();
		
		List<PostDTO> postDTOs = posts.stream().map(post -> modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());
		
		
		PostResponse postResponse = new PostResponse();
		
		postResponse.setContent(postDTOs);
		postResponse.setPageNumber(pageOfPosts.getNumber());
		postResponse.setPageSize(pageOfPosts.getSize());
		postResponse.setTotalElements(pageOfPosts.getNumberOfElements());
		postResponse.setTotalPages(pageOfPosts.getTotalPages());
		postResponse.setLastPage(pageOfPosts.isLast());
		
		return postResponse;
	}

	@Override
	public List<PostDTO> getAllPostsOfUser(Integer userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", userId));

		List<Post> posts = postRepository.findByUser(user);
		
		List<PostDTO> postDTOs = posts.stream().map(post -> modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());
		
		return postDTOs;
	}

	@Override
	public List<PostDTO> getAllPostsOfCategory(Integer categoryId) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Caategory", categoryId));
		
		List<Post> posts = postRepository.findByCategory(category);
		
		List<PostDTO> postDTOs = posts.stream().map(post -> modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());
		
		return postDTOs;
	}

	
	@Override
	public PostDTO getSinglePost(Integer postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", postId));
		
		return modelMapper.map(post, PostDTO.class);		
	}

	@Override
	public List<PostDTO> searchPost(String keyword) {
		/*List<Post> posts = postRepository.searchByTitle("%" + keyword + "%");
		
		List<PostDTO> postDTOs = posts.stream().map(post -> modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());
		
		return postDTOs;*/
		
		return null;
	}

}
