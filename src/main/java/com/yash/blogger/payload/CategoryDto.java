package com.yash.blogger.payload;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto
{
	
	private int categoryId;
	private String catogeryTitle;
	private String catogeryDescription;

}
