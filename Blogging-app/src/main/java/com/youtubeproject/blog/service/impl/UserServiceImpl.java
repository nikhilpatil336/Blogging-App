package com.youtubeproject.blog.service.impl;

import java.util.ArrayList;
import java.util.Base64.Encoder;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.youtubeproject.blog.config.AppConstants;
import com.youtubeproject.blog.entities.Role;
import com.youtubeproject.blog.entities.User;
import com.youtubeproject.blog.exceptions.ResourceNotFoundException;
import com.youtubeproject.blog.payloads.UserDTO;
import com.youtubeproject.blog.repositories.RoleRepository;
import com.youtubeproject.blog.repositories.UserRepository;
import com.youtubeproject.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private  PasswordEncoder passwordEncoder;
	
	@Override
	public UserDTO createUser(UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		User savedUser = userRepository.save(user);
		return modelMapper.map(savedUser, UserDTO.class);
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO, Integer userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", userId));
		
		//User user = userRepository.findById(userId)
				//		.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
				
		//updating the data from parameter userDTO into user variable
		//user = modelMapper.map(userDTO, User.class);
		
		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		user.setAbout(userDTO.getAbout());
		
		//Saving the updated user variable in repo
		User updatedUser = userRepository.save(user);
		
		//returning the UserDTO object
		return modelMapper.map(updatedUser, UserDTO.class);
	}

	@Override
	public UserDTO getUserById(Integer userId) {
		//User user = userRepository.findById(userId)
		//		.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		
		User user = userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", userId));
		
		//returning userDTO object
		return modelMapper.map(user, UserDTO.class);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> users = userRepository.findAll();
		
		List<UserDTO> userDTOs = new ArrayList<>();
		
		//this is my code
		/*users.stream().forEach(x -> {
			userDTOs.add(modelMapper.map(x, UserDTO.class));
		});*/
		
		
		//youtube code
		userDTOs = users.stream().map(user -> modelMapper.map(user, UserDTO.class))
				.collect(Collectors.toList());
		
		return userDTOs;
	}

	@Override
	public void deleteUser(Integer userId) {
		
		User user = userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", userId));
		
		userRepository.delete(user);		
	}

	@Override
	public UserDTO registerUser(UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
		
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		
		Role role = roleRepository.findById(AppConstants.NORMAL_ROLE_ID).get();
		
		user.getRoles().add(role);
		
		User newUser = userRepository.save(user);
		
		return modelMapper.map(newUser, UserDTO.class);
	}
}