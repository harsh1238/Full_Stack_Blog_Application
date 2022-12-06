package com.yash.blogger.services;

import java.util.List;

import com.yash.blogger.payload.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto user );
	UserDto updateUser(UserDto user,Integer UserId);
	void deleteUser(Integer userid);
	List<UserDto> getAllUserById();
	UserDto getUserbyId(Integer userid);

}
