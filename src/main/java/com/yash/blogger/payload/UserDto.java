package com.yash.blogger.payload;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	
		private int id;
		
		@NotEmpty
		@Size(min=3,message="usermust be minimum 3 size")
		private String name;
		
		@Email(message="Email address should be valid !")
		private String email;
		
		@NotEmpty
		@Size(min=3,max=8,message="Enter password correctly")
		private String password;
		
		
		private String about;

}
