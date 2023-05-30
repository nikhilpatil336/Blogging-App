package com.youtubeproject.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.youtubeproject.blog.repositories.UserRepository;

@SpringBootTest
class BloggingAppApplicationTests {

	@Autowired
	private UserRepository userRepository;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void repoTest()
	{
		System.out.println(userRepository.getClass().getName());
		System.out.println(userRepository.getClass().getPackageName());
	}
}
