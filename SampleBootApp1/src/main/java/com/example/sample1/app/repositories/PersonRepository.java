package com.example.sample1.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.sample1.app.Person;
	
@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
	
	@Query("SELECT d FROM Person d ORDER BY d.name")
	List<Person> findAllOrderByName();
	
	public Optional<Person> findById(long name);
	
}

