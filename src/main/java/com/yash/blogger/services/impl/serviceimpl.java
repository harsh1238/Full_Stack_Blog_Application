package com.yash.blogger.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.yash.blogger.exceptions.*;
import com.yash.blogger.entity.Category;
import com.yash.blogger.entity.User;
import com.yash.blogger.payload.UserDto;
import com.yash.blogger.repository.UserRepo;
import com.yash.blogger.services.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class serviceimpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelmapper;

	@Override
	public UserDto createUser(UserDto userdto) {
		
		// creating new user
		User user=this.dtotoUser(userdto);
		
		User saveduser=this.userRepo.save(user);
		
		return this.usertoDto(saveduser);
	}

	@Override
	public UserDto updateUser(UserDto userdto, Integer userId) {
		// updating user detail
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundExeption("user","id",userId));
		
		user.setName(userdto.getName());
		user.setEmail(userdto.getEmail());
		user.setAbout(userdto.getAbout());
		user.setPassword(userdto.getPassword());
		
		User updatedUser = this.userRepo.save(user);
		UserDto userDto1=this.usertoDto(updatedUser);
		
		
		return userDto1;
	}

	@Override
	public void deleteUser(Integer userid) { 
		// deleting that particular user
		
		this.userRepo.deleteById(userid);

	}

	@Override
	public List<UserDto> getAllUserById() {
		// will fetch all  user properties
		List<User> users=this.userRepo.findAll();
		
		List<UserDto> userdto = users.stream().map(user->this.usertoDto(user)).collect(Collectors.toList());
		
		return userdto;
	}

	@Override
	public UserDto getUserbyId(Integer userId) {
		//  we will get single id user details.
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundExeption("user","id",userId));
		
		return this.usertoDto(user);
	}

	
	private User dtotoUser(UserDto userdto)
	{
		User user=this.modelmapper.map(userdto, User.class);
//		user.setId(userdto.getId());
//		user.setEmail(userdto.getEmail());
//		user.setAbout(userdto.getAbout());
//		user.setName(userdto.getName());
//		user.setPassword(userdto.getPassword());
		return user;
	}
	
	private UserDto usertoDto(User user)
	{
		 UserDto userdto = this.modelmapper.map(user, UserDto.class);
		 
//		 userdto.setId(user.getId());
//		 userdto.setName(user.getName());
//		 userdto.setEmail(user.getEmail());
//		 userdto.setPassword(user.getPassword());
//		 userdto.setAbout(user.getAbout());
		 
		 return userdto;
	}
}
