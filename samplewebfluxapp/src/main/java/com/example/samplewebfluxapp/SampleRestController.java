package com.example.samplewebfluxapp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class SampleRestController {
	
	@Autowired
	PostRepository repository;
	
	@PostConstruct
	public void init() {
		Post p1 = new Post(1,1,"Hello","Hello Flux!");
		Post p2 = new Post(2,2,"Sample", "this is sample post");
		Post p3 = new Post(3,3,"ハロー", "これはサンプルです");
		repository.saveAndFlush(p1);
		repository.saveAndFlush(p2);
		repository.saveAndFlush(p3);
	}
	
	@RequestMapping("/")
	public String index() {
		return "hello flux (Mono)";
	}
	
	@RequestMapping("/flux")
	public Mono<String> flux() {
		return Mono.just("hello flux (Mono)");
	}
	
	@RequestMapping("/flux2")
	public Flux<String> flux2() {
		return Flux.just("Hello Flux.", "これはFluxのサンプルです。");
	}
	
	@RequestMapping("/post")
	public Mono<Post> post(){
		Post post = new Post(0,0,"dummy","dummy massage ...");
		return Mono.just(post);
	}
	
	@RequestMapping("/post/{id}")
	public Mono<Post> post(@PathVariable int id){
		Post post = repository.findById(id);
		return Mono.just(post);
	}
	
	@RequestMapping("/posts")
	public Flux<Object> posts(){
		List<Post> posts = repository.findAll();
		return Flux.fromArray(posts.toArray());
	}

}
