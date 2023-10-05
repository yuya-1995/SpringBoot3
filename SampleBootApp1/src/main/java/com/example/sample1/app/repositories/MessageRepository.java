package com.example.sample1.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sample1.app.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
	public Optional<Message> findById(long id);
}
