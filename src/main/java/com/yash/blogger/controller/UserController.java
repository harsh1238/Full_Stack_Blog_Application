package com.yash.blogger.controller;

import java.util.List;

import javax.validation.Valid;

import com.yash.blogger.payload.ApiResponse;
import com.yash.blogger.payload.UserDto;
import com.yash.blogger.services.UserService;

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


@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//POST-API create user
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
	{
		UserDto user=this.userService.createUser(userDto);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	
	//PUT-API update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userdto,@PathVariable("userId") Integer id )
	{
		UserDto updatedUser=this.userService.updateUser(userdto, id);
		return  ResponseEntity.ok(updatedUser);
	}
	//DELETE-API delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer id){ 
		this.userService.deleteUser(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully",true),HttpStatus.OK );
		
	}
	
	
	//GET-API get single  user
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer userId)
	{
		
		return ResponseEntity.ok(this.userService.getUserbyId(userId));
	}
	
	//GET-API get List of  users
		@GetMapping("/")
		public ResponseEntity<List<UserDto>> getAllUsers()
		{
			
			return ResponseEntity.ok(this.userService.getAllUserById());
		}


}
