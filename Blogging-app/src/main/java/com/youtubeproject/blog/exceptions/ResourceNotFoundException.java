package com.youtubeproject.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
	
	String resourceName;
	//String fieldName;
	long fieldValue;
	
	
	/*public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
		//super(String.format("%s not found with %s : %l", resourceName, fieldName, fieldValue));
		// this is the code taugth in youtube video
		
		super(resourceName + " not found with " + fieldName + " : " + fieldValue);
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}*/
	
	/*public ResourceNotFoundException(String resourceName, Integer fieldValue) {
		
		//super(resourceName + " with ID " + fieldValue + " not found");
		super(String.format("%s with ID %i not found", resourceName, fieldValue));
	}*/


	public ResourceNotFoundException(String resourceName2, Integer fieldId) {
		//super(String.format("%s with ID %i not found", resourceName2, fieldId));
		super(resourceName2 + " with ID " + fieldId + " not found");
	}

	/*public ResourceNotFoundException(String resourceName, long fieldValue) {
		super(resourceName + " with ID " + fieldValue + " not found");
		this.resourceName = resourceName;
		this.fieldValue = fieldValue;
	}*/
}
