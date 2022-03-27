package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Comment_Repository extends JpaRepository<Comment,Integer>{
List<Comment>findCommentByPost(Post post);
Comment findByPostAndId(Post post,int id);


}
