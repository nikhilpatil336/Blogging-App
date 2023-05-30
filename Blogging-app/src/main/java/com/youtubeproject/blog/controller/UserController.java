package com.youtubeproject.blog.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.youtubeproject.blog.payloads.ApiResponse;
import com.youtubeproject.blog.payloads.UserDTO;
import com.youtubeproject.blog.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
		UserDTO createUserDTO = userService.createUser(userDTO);
		return new ResponseEntity<>(createUserDTO, HttpStatus.CREATED);
	}
	
	@PutMapping("/{userId}")
	//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable Integer userId) {
		UserDTO updatedUserDTO = userService.updateUser(userDTO, userId);
		return ResponseEntity.ok(updatedUserDTO);
	}
	
	@DeleteMapping("/{userId}")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId)
	{
		userService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User with ID: " + userId + " deleted successfully", true), HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<UserDTO> getSingleUser(@PathVariable Integer userId)
	{
		/*UserDTO singleUser = userService.getUserById(userid);
		return new ResponseEntity<UserDTO>(singleUser, HttpStatusCode.valueOf(201));*/
		
		//return RseponseEntity<>(singleUser, HttpStatus.OK);
		
		return ResponseEntity.ok(userService.getUserById(userId));
	}
	
	@GetMapping
	//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<UserDTO>> getAllUsers()
	{
		//List<UserDTO> users = userService.getAllUsers();
		//return new ResponseEntity<List<UserDTO>>(users, HttpStatusCode.valueOf(201));
		
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO)
	{
		UserDTO registerUser = userService.registerUser(userDTO);
		
		return new ResponseEntity<>(registerUser, HttpStatus.CREATED);
	}
}
	