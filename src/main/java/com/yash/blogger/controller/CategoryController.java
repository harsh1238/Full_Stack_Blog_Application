package com.yash.blogger.controller;

import java.util.List;

import com.yash.blogger.payload.ApiResponse;
import com.yash.blogger.payload.CategoryDto;
import com.yash.blogger.repository.catergoryRepo;
import com.yash.blogger.services.CategoryService;

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
@RequestMapping("/api/categories")
public class CategoryController {

	
	@Autowired
	private CategoryService categoryService;
	
	//create categories
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto)
	{
		CategoryDto categoryDto2=this.categoryService.createcategoryDto(categoryDto);
		return new ResponseEntity<CategoryDto>(categoryDto2, HttpStatus.CREATED);
	}
	
	
	//edit categories
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> editCategory(@RequestBody CategoryDto categoryDto, @PathVariable Integer catId )
	{
		CategoryDto updatedCategory=this.categoryService.updatedCategoryDto(categoryDto, catId);
		return  ResponseEntity.ok(updatedCategory);
	} 
	
	//delete categories
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory( @PathVariable Integer catId )
	{
		this.categoryService.deleteCategoryDto(catId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully",true),HttpStatus.OK );
	}
	
	
	//get single category
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategoryDetail( @PathVariable Integer catId )
	{
		CategoryDto getCategory = this.categoryService.getCategory(catId);
		
		return new ResponseEntity<CategoryDto>(getCategory,HttpStatus.OK);
	}
	//get all categories
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategoryDetails( )
	{

		return ResponseEntity.ok( this.categoryService.getAllDetails());
	}
}
