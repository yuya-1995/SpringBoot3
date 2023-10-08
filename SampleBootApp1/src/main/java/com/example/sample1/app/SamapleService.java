package com.example.sample1.app;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.sample1.app.repositories.PostRepository;

@Service
public class SamapleService {
	
	private String baceUrl = "https://jsonplaceholder.typicode.com/posts";
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	PostRepository repository;
	
	public Post[] getAllPosts() {
		return restTemplate.getForObject(baceUrl, Post[].class);
	}
	
	public Post getPost(int id) {
		return restTemplate.getForObject(baceUrl + "/" + id, Post.class);
	}

//	public Post getPost() {
//		return new Post(0,0,"Dummy","this is sample.");
//	}
	
	public Object[] getLocalPosts() {
		return repository.findAll().toArray();
	}
	
	public Post getAndSavePost(int id) {
		Post post = restTemplate.getForObject(baceUrl + "/" + id, Post.class);
		repository.save(post);
		return post;
	}
	
}
