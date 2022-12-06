package com.yash.blogger.services;

import java.util.List;

import com.yash.blogger.entity.Category;
import com.yash.blogger.entity.Post;
import com.yash.blogger.entity.User;
import com.yash.blogger.payload.PostResponse;
import com.yash.blogger.payload.Postdto;

public interface PostService {
	
	//create
	Postdto createPost(Postdto postdto,Integer userID,Integer categoryId);
	
	//Update
	Postdto updatePostdto(Postdto postdto, Integer id);
	
	//delete
	void deletePost(Integer idInteger);
	
	//getAllPost
	PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	//getspecific post
	Postdto getPostById(Integer id);
	
	//get post for Perticular User
	List<Postdto> getPostByUserId(Integer UserId);
	
	//get Post as per category
	List<Postdto> getPostByCategoryId(Integer CategoryId);
	
	//srch Post by keyord
	List<Postdto> serachPost(String keyword);
	}
