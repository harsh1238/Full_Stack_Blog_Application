package com.yash.blogger.services.impl;


import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import javax.persistence.PostRemove;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.yash.blogger.entity.Category;
import com.yash.blogger.entity.Post;
import com.yash.blogger.entity.User;
import com.yash.blogger.exceptions.ResourceNotFoundExeption;
import com.yash.blogger.payload.PostResponse;
import com.yash.blogger.payload.Postdto;
import com.yash.blogger.repository.Posts;
import com.yash.blogger.repository.UserRepo;
import com.yash.blogger.repository.catergoryRepo;
import com.yash.blogger.services.PostService;
import java.sql.Date;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private Posts postrepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private catergoryRepo catergoryRepo;
	
	@Override
	public Postdto createPost(Postdto postdto,Integer userId, Integer categoryId) {
		// TODO Auto-generated method stub
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundExeption("User", "User id", userId));
		Category category=this.catergoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundExeption("category", "category id", categoryId));
		
		Post post=this.modelMapper.map(postdto, Post.class);
		
		post.setImagename("default.png");
		LocalDateTime time= LocalDateTime.now();
		post.setAddedDate(time);
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost=this.postrepo.save(post);
		
		
		return this.modelMapper.map(newPost, Postdto.class);
	}

	@Override
	public Postdto updatePostdto(Postdto postdto, Integer id) {
		// TODO Auto-generated method stub
		Post post= this.postrepo.findById(id).orElseThrow(() -> new ResourceNotFoundExeption("Post", "Post id", id));
		
		post.setImagename(postdto.getImagename());
		post.setTitle(postdto.getTitle());
		post.setContent(postdto.getContent());
		
		Post updatedPost = this.postrepo.save(post);
		return this.modelMapper.map(updatedPost, Postdto.class);
	}

	@Override
	public void deletePost(Integer id) {
		// TODO Auto-generated method stub
		Post post = this.postrepo.findById(id).orElseThrow(() -> new ResourceNotFoundExeption("post", "post id", id));
		this.postrepo.delete(post);
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) 
	{
		
		//Sort sort  = (sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
		Sort sort=null;
		
		if(sortDir.equalsIgnoreCase("asc"))
		{
			sort = Sort.by(sortBy).ascending();
		}
		else
		{
			sort = Sort.by(sortBy).descending();
		}
		
		Pageable p =  PageRequest.of(pageNumber, pageSize,sort);
		
		Page<Post> pagePost = this.postrepo.findAll(p);
		List<Post> allPosts = pagePost.getContent();
		List<Postdto> allPostdtoPosts = allPosts.stream().map((post)->this.modelMapper.map(post, Postdto.class)).collect(Collectors.toList());
		
		PostResponse postresponse = new PostResponse();
		
		postresponse.setContent(allPostdtoPosts);
		postresponse.setPageNumber(pagePost.getNumber());
		postresponse.setPageSize(pagePost.getSize());
		postresponse.setTotalElements(pagePost.getTotalElements());
		postresponse.setTotalPages(pagePost.getTotalPages());
		postresponse.setLastPage(pagePost.isLast());
		return postresponse;
	}

	@Override
	public Postdto getPostById(Integer id) {
		// TODO Auto-generated method stub
		Post postdetails=this.postrepo.findById(id).orElseThrow(()->new ResourceNotFoundExeption("Post", "Post id", id));
		return this.modelMapper.map(postdetails,Postdto.class);
	}

	@Override
	public List<Postdto> getPostByUserId(Integer UserId) {
		// TODO Auto-generated method stub
		User user=this.userRepo.findById(UserId).orElseThrow(()->new ResourceNotFoundExeption("User", "User id", UserId));
		
		
		List<Post> userposts=this.postrepo.findByUser(user);
		
		List<Postdto> latestpostdto = userposts.stream().map((post)->this.modelMapper.map(post, Postdto.class)).collect(Collectors.toList());
		return latestpostdto;
	}

	@Override
	public List<Postdto> getPostByCategoryId(Integer CategoryId) {
		// TODO Auto-generated method stub
		
		Category catId = this.catergoryRepo.findById(CategoryId).orElseThrow(()->new ResourceNotFoundExeption("Category","Category ID",CategoryId));
		
		List<Post> catPost = this.postrepo.findByCategory(catId);
		
		List<Postdto> catPostDto = catPost.stream().map(post->this.modelMapper.map(post, Postdto.class)).collect(Collectors.toList());
		return catPostDto;
	}

	@Override
	public List<Postdto> serachPost(String keyword)
	{
		List<Post> post=this.postrepo.findByTitle("%"+keyword+"%");
		List<Postdto> postdtos =post.stream().map((posts)-> this.modelMapper.map(posts, Postdto.class)).collect(Collectors.toList());
		return postdtos;
	}

}
