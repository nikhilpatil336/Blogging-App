package com.youtubeproject.blog;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.youtubeproject.blog.config.AppConstants;
import com.youtubeproject.blog.entities.Role;
import com.youtubeproject.blog.repositories.RoleRepository;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
public class BloggingAppApplication {

	@Autowired
	private RoleRepository roleRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BloggingAppApplication.class, args);
		System.out.println("Ready");
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	public void run(String... args) throws Exception
	{
		try {
			Role admin_Role = new Role();
			admin_Role.setId(AppConstants.ADMIN_ROLE_ID);
			admin_Role.setName("ROLE_ADMIN");
			
			roleRepository.save(admin_Role);
			
			Role normal_Role = new Role();
			normal_Role.setId(AppConstants.ADMIN_ROLE_ID);
			normal_Role.setName("ROLE_NORMAL");
			
			roleRepository.save(normal_Role);
			
			/*List<Role> roles_At_Start = new ArrayList<>();
			
			roles_At_Start.add(admin_Role);
			roles_At_Start.add(normal_Role);
			
			roleRepository.saveAll(roles_At_Start);*/
			
		}catch (Exception e) {
			System.out.println(e);
		}
	}
}
