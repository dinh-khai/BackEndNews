package com.news.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.news.dto.ChangePass;
import com.news.entity.User;
import com.news.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/news")
public class UserController {
	@Autowired
	UserService uService;

	@PostMapping("/register")
	public String saveUser(HttpServletRequest request, @RequestParam String userName, @RequestParam String fullName,
			@RequestParam String password, @RequestParam String email,
			@RequestParam(required = false) MultipartFile file) {
		return uService.save(userName, fullName, password, email, file
								,request.getServerName(), request.getServerPort());
	}

	@PostMapping("/login")
	public User login(@RequestParam String userName, @RequestParam String password) throws Exception {
		return uService.getUser(userName, password);
	}

	@GetMapping("/user/all")
	public List<User> all() {
		return uService.findAll();
	}

	@PutMapping("/user/changePass")
	public void changePass(@RequestBody ChangePass changePass) {
		uService.updateUser(changePass.getPassword(), changePass.getUserName());
		return;
	}

}
