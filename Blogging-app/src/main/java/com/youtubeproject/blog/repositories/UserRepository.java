package com.youtubeproject.blog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.youtubeproject.blog.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByname(String name);
}
