package com.yash.blogger.repository;



import java.util.List;

import com.yash.blogger.entity.Category;
import com.yash.blogger.entity.Post;
import com.yash.blogger.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Posts extends JpaRepository<Post, Integer>
{
	
	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	@Query("select p from Post p where p.title Like :key")
	List<Post> findByTitle(@Param("key") String title);

}
