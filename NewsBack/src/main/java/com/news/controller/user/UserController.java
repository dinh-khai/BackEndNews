package com.news.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.news.dto.UserDTO;
import com.news.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/api/news/user")
public class UserController {
	@Autowired
	UserService uService;
	
//	@GetMapping("/infoUser")
//	public UserDTO getUser(@RequestParam String userName) {
//		
//	}
//	
}
