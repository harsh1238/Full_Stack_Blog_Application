package com.yash.blogger.payload;

import java.sql.Date;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Postdto {

	private int postId;
	
	private String title;
	
	private String content;
	
	private String imagename;
	
	private UserDto user;
	
	private CategoryDto category;
	
	

}
