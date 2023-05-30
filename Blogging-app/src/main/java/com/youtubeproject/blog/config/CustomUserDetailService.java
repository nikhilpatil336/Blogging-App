package com.youtubeproject.blog.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.youtubeproject.blog.entities.User;
import com.youtubeproject.blog.exceptions.ResourceNotFoundException;
import com.youtubeproject.blog.repositories.UserRepository;

public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User users = userRepository.findByname(username).orElseThrow(()-> new ResourceNotFoundException(username, 0));
		return users;
	}

}