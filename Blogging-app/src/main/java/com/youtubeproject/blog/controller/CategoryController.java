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
import org.springframework.web.bind.annotation.RestController;

import com.youtubeproject.blog.payloads.ApiResponse;
import com.youtubeproject.blog.payloads.CategoryDTO;
import com.youtubeproject.blog.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	
	//create
	@PostMapping()
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO)
	{
		CategoryDTO createdCategoryDTO = categoryService.createCategory(categoryDTO);
		return new ResponseEntity<CategoryDTO>(createdCategoryDTO, HttpStatus.CREATED);
	}
	
	//update
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Integer categoryId)
	{
		CategoryDTO updatedCategoryDTO = categoryService.updateCategory(categoryDTO, categoryId);
		return new ResponseEntity<CategoryDTO>(updatedCategoryDTO, HttpStatus.OK);
	}
	
	//delete
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId)
	{
		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category with ID: " + categoryId + " deleted successfully", true), HttpStatus.OK);
	}
	
	//get
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> getSingleCategory(@PathVariable Integer categoryId)
	{
		CategoryDTO singleCategoryDTO = categoryService.getSingleCategroy(categoryId);
		return new ResponseEntity<CategoryDTO>(singleCategoryDTO, HttpStatus.OK);
	}
	
	//getall
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> getAllCategories()
	{
		return ResponseEntity.ok(categoryService.getAllCategroy());
	}
}
