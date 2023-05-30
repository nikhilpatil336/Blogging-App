package com.youtubeproject.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.NotEmpty;

import com.youtubeproject.blog.entities.Category;
import com.youtubeproject.blog.entities.Comment;
import com.youtubeproject.blog.entities.User;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

	private Integer postId;
	
	@NotEmpty
	@Size(min = 4, message = "title should be of minimum 4 length")
	private String title;
	
	@NotEmpty
	@Size(min = 4, message = "title should be of minimum 4 length")
	private String content;
	
	
	//this data we will set in our service instead of taking from the user
	private String imageName;
	
	private Date addedDate;
	
	private UserDTO user;
	
	private CategoryDTO category;
	
	private Set<CommentDTO> comments = new HashSet<>();
}
