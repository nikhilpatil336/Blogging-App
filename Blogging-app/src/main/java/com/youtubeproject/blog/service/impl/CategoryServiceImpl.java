package com.youtubeproject.blog.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.youtubeproject.blog.entities.Category;
import com.youtubeproject.blog.exceptions.ResourceNotFoundException;
import com.youtubeproject.blog.payloads.CategoryDTO;
import com.youtubeproject.blog.repositories.CategoryRepository;
import com.youtubeproject.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		Category category = modelMapper.map(categoryDTO, Category.class);
		Category addedCategory = categoryRepository.save(category);
		return modelMapper.map(addedCategory, CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> getAllCategroy() {
		List<Category> categories = categoryRepository.findAll();
		List<CategoryDTO> categoryDTOList = new ArrayList<>();

		categoryDTOList = categories.stream().map(categroy -> modelMapper.map(categroy, CategoryDTO.class))
				.collect(Collectors.toList());
		return categoryDTOList;
	}

	@Override
	public CategoryDTO getSingleCategroy(Integer categoryId) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", categoryId));

		return modelMapper.map(category, CategoryDTO.class);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", categoryId));

		category.setCatagoryTitle(categoryDTO.getCatagoryTitle());
		category.setCatagoryDescription(categoryDTO.getCatagoryDescription());
		
		Category updatedCategory = categoryRepository.save(category);
		
		return modelMapper.map(updatedCategory, CategoryDTO.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", categoryId));
		
		categoryRepository.delete(category);
	}

}
