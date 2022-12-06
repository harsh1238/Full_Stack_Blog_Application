package com.yash.blogger.services.impl;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.stream.Collectors;

import com.yash.blogger.entity.Category;
import com.yash.blogger.exceptions.ResourceNotFoundExeption;
import com.yash.blogger.payload.CategoryDto;
import com.yash.blogger.repository.catergoryRepo;
import com.yash.blogger.services.CategoryService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceimpl  implements CategoryService {

	@Autowired
	private catergoryRepo catergoryRepo;
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public CategoryDto createcategoryDto(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		Category createdDto = this.modelMapper.map(categoryDto, Category.class);
		
		Category resp=this.catergoryRepo.save(createdDto);
		
		return this.modelMapper.map(resp, CategoryDto.class);
	}

	@Override
	public CategoryDto updatedCategoryDto(CategoryDto categoryDto, Integer id) {
		// TODO Auto-generated method stub
		Category catid=this.catergoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundExeption("user","id",id));
		
		catid.setCatogeryDescription(categoryDto.getCatogeryDescription());
		catid.setCatogeryTitle(categoryDto.getCatogeryTitle());
		
		
		return this.modelMapper.map(catid, CategoryDto.class);
	}

	@Override
	public void deleteCategoryDto(Integer catId) {
		// TODO Auto-generated method stub
		Category id = this.catergoryRepo.findById(catId).orElseThrow(()->new ResourceNotFoundExeption("user","id",catId));
		this.catergoryRepo.delete(id);
	}

	@Override
	public CategoryDto getCategory(Integer catId) {
		// TODO Auto-generated method stub
		Category id = this.catergoryRepo.findById(catId).orElseThrow(()->new ResourceNotFoundExeption("user","id",catId));
		return this.modelMapper.map(id, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllDetails() {
		// TODO Auto-generated method stub
		List<Category>  categories=this.catergoryRepo.findAll();
		List<CategoryDto>  categoryDto=categories.stream().map((updatedcat)-> this.modelMapper.map(updatedcat, CategoryDto.class)).collect(Collectors.toList());
		return categoryDto;
	}

}
