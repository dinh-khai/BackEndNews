package com.news.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.news.entity.User;

public interface UserService {

	Optional<User> findById(String id);
	
	User getUser(String userName,String password);

	List<User> findAll();

	String save(String userName, String fullName, String password, String email,MultipartFile file,String forder,String defaultImage);
	void updateUser(String password,String userName);
	
	

}
