package com.youtubeproject.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.youtubeproject.blog.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
