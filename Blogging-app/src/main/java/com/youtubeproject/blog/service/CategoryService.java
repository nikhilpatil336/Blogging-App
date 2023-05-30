package com.youtubeproject.blog.service;

import java.util.List;

import com.youtubeproject.blog.payloads.CategoryDTO;

public interface CategoryService {
	
	//create
	public CategoryDTO createCategory(CategoryDTO categoryDTO);
	
	//getall
	public List<CategoryDTO> getAllCategroy();
	
	//get one
	public CategoryDTO getSingleCategroy(Integer id);
	
	//update
	public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer id);
	
	//delete
	public void deleteCategory(Integer id);
}
