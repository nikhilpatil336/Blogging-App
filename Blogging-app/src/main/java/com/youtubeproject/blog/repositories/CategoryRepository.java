package com.youtubeproject.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.youtubeproject.blog.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
