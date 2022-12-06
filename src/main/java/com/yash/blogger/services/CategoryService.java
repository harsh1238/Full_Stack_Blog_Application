package com.yash.blogger.services;

import java.util.List;

import com.yash.blogger.payload.CategoryDto;
import com.yash.blogger.repository.catergoryRepo;

import org.springframework.stereotype.Service;

@Service
public interface CategoryService 
{
	//create
	CategoryDto createcategoryDto(CategoryDto categoryDto);
	
	
	//Update
	CategoryDto updatedCategoryDto(CategoryDto categoryDto,Integer id);
	
	
	//Delete
	void deleteCategoryDto(Integer catId);
	
	//Getsinglewithid
	CategoryDto getCategory(Integer catId);
	
	//getalldetails
	List<CategoryDto> getAllDetails();

}
