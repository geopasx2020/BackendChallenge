package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Post_Repository extends JpaRepository<Post,Integer>{


List<Post> findAllByOrderByCreatedDateDesc();
List<Post> findByUser(User user);
Post findByUserAndId(User user,int id);










}
