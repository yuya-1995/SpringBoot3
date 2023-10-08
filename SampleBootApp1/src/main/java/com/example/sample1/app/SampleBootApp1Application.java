package com.example.sample1.app;

import org.springframework.boot.Banner.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;

import com.example.sample1.app.repositories.PersonRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SampleBootApp1Application implements CommandLineRunner {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SampleBootApp1Application.class);
		app.setBannerMode(Mode.OFF);
		app.run(args);
//		SpringApplication.run(SampleBootApp1Application.class, args);
	}

	@Override
	public void run(String[] args) {
		init();
		Person person = entityManager.find(Person.class, 1);
		System.out.println("+--------------------------------+");
		System.out.println(person.getName() + "," + person.getMail() + "," + person.getAge());
		System.out.println("+--------------------------------+");
		
	}
	
	public void init() {
		Person p1 = new Person();
		p1.setName("taro");
		p1.setAge(39);
		p1.setMail("tro@yamada");
		repository.saveAndFlush(p1);
	}
	

}
