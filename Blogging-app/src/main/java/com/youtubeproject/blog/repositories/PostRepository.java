package com.youtubeproject.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.youtubeproject.blog.entities.Category;
import com.youtubeproject.blog.entities.Post;
import com.youtubeproject.blog.entities.User;

public interface PostRepository extends JpaRepository<Post, Integer>{

	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	//List<Post> findTitleContaining(String title);
	
	/*@Query("select p form Post p where p.title like :key")
	List<Post> searchByTitle(@Param("key") String title);*/
}
