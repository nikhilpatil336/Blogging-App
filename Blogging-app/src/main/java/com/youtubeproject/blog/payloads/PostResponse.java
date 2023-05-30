package com.youtubeproject.blog.payloads;

import java.util.List;

import org.springframework.data.domain.Page;

import com.youtubeproject.blog.entities.Post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Setter
@Getter
public class PostResponse {

	private List<PostDTO> content;
	private int pageNumber;
	private int pageSize;
	private int totalElements;
	private int totalPages;
	private boolean lastPage;
}
