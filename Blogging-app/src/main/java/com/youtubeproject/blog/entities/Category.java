package com.youtubeproject.blog.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "catagories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Integer catagoryId;
	
	@Column(name = "Title", nullable = false, length = 100)
	private String catagoryTitle;
	
	@Column(name = "Description", nullable = false, length = 100)
	private String catagoryDescription;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	private List<Post> posts = new ArrayList<>(); 
}
