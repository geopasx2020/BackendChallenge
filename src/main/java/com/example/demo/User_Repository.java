package com.example.demo;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface User_Repository extends JpaRepository<User,Integer>{


	
List<User>findByEmailAndPassword(String email,String Password);


Optional<User> findByEmail(String email);


User findUserByEmail(String email);




}
