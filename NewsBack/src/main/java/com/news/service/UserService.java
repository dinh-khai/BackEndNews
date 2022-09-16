package com.news.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.news.dto.UserDTO;
import com.news.entity.User;

public interface UserService {

	UserDTO findById(HttpServletRequest request);
	
	User getUser(String userName,String password);

	List<User> findAll();

	String save(String userName, String fullName, String password, String email
				,MultipartFile file,String serverName,int port);
	void updateUser(String password,String userName);
	
	

}
