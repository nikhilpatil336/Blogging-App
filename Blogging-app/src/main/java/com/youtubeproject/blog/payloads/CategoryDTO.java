package com.youtubeproject.blog.payloads;

import org.hibernate.validator.constraints.NotEmpty;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {
	
	private Integer categoryId;
	
	@NotEmpty
	@Size(min = 4, max = 100, message = "The title of the catagory should be between 4 an 100 characters")
	private String catagoryTitle;

	@NotEmpty
	@Size(min = 4, max = 100, message = "The description of the catagory should be between 4 an 100 characters")
	private String catagoryDescription;

}
