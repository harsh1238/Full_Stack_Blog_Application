package com.yash.blogger.controller;

import java.util.List;

import com.yash.blogger.config.AppConstants;
import com.yash.blogger.entity.Category;
import com.yash.blogger.entity.Post;
import com.yash.blogger.entity.User;
import com.yash.blogger.payload.ApiResponse;
import com.yash.blogger.payload.PostResponse;
import com.yash.blogger.payload.Postdto;
import com.yash.blogger.services.PostService;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PostController 
{
	
	@Autowired
	private PostService postService;
	
	//Create post for the user and category
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<Postdto> createPostApi(@RequestBody Postdto postdto,@PathVariable Integer userId,@PathVariable Integer categoryId)
	{
		
		Postdto createpost = this.postService.createPost(postdto, userId, categoryId);
		return new ResponseEntity<Postdto>(createpost,HttpStatus.CREATED);
		
	}

	//get all post details by userID
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<Postdto>> getPostByUserId(@PathVariable Integer userId)
	{
		List<Postdto> getPostByUserId = this.postService.getPostByUserId(userId);
		return new ResponseEntity<List<Postdto>>(getPostByUserId,HttpStatus.OK);
		
	}
	
	//get Post details as per the category 
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<Postdto>> getPostByCategory(@PathVariable Integer categoryId)
	{	
		List<Postdto> postByCatId = this.postService.getPostByCategoryId(categoryId);
		return new ResponseEntity<List<Postdto>>(postByCatId,HttpStatus.OK);
	}
	
	//get post details by POSTID
	@GetMapping("/post/{postId}")
	public ResponseEntity<Postdto> getPostById(@PathVariable Integer postId)
	{
		Postdto postDetails=this.postService.getPostById(postId);
		return new ResponseEntity<Postdto>(postDetails,HttpStatus.OK);
	}
	
	
	//get all posts
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value="pageNumber",defaultValue=AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
		@RequestParam(value="pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
		@RequestParam(value="sortby",defaultValue = AppConstants.SORT_BY,required = false) String  sortBy,
		@RequestParam(value="sortdir",defaultValue = AppConstants.SORT_DIR,required = false) String  sortDir)
	{
		PostResponse allPosts = this.postService.getAllPosts(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(allPosts,HttpStatus.OK);
	}
	
	//Delete post by POSTID
	@DeleteMapping("/post/{postId}")
	public ResponseEntity<ApiResponse> deletePostById(@PathVariable Integer postId)
	{
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post has been deletd successfully", false),HttpStatus.OK);
	}
		
	//Update post by POSTID
	@PutMapping("/post/{postId}")
	public ResponseEntity<Postdto> updatePosyById(@PathVariable Integer postId, @RequestBody Postdto postdto)
	{
		Postdto updatedPost = this.postService.updatePostdto(postdto, postId);
		return new ResponseEntity<Postdto>(updatedPost,HttpStatus.OK);
	}
	
	
	//search by title
	@GetMapping("/post/search/{keywords}")
	public ResponseEntity<List<Postdto>> searchByTitle(@PathVariable("keywords") String keyword)
	{
		List<Postdto> result = this.postService.serachPost(keyword);
		return new ResponseEntity<List<Postdto>>(result,HttpStatus.OK);
	}
 }
