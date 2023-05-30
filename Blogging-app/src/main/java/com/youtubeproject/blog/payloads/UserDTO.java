package com.youtubeproject.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
	
	private Integer userId;
	
	@NotEmpty
	@Size(min = 4, message = "Name should be of minimum of 4 characters")
	private String name;
	
	@Email
	private String email;
	
	@NotEmpty
	@Size(min = 3, max =10, message = "Password should be minimum of 3 characters and maximum of 10 characters")
	private String password;
	
	@NotEmpty
	private String about;
	
	private Set<RoleDTO> roles = new HashSet<>();
}
