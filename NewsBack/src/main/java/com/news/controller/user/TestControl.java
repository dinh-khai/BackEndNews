package com.news.controller.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.news.dto.UserDto;
import com.news.repos.NewsRepos;
import com.news.service.TestService;
import com.news.service.UpLoadService;
 
@CrossOrigin
@RestController
@RequestMapping("/test")
public class TestControl {
	
	@Autowired
	TestService testService;
	
	@Autowired
	NewsRepos res;
	
	@Autowired
	ServletContext app;
	
	@Autowired
	UpLoadService up;
	
	@GetMapping("/news")
	public String getAllNews() throws IOException{
		String forder=new ClassPathResource("/static/image").getFile().getAbsolutePath();
		return forder;
	}
	
	@PostMapping(path="/upload")
	public void test(@RequestParam MultipartFile file) throws IOException {
		System.out.println(app.getContextPath());
//		System.out.println(up.upload(file,"user"));
	}
	
	@PostMapping(path="/user")
	public void getUser(@RequestBody UserDto user) {
		System.out.println(user.getFullName());
		return;
	}
	@GetMapping("/a")
	public void te(HttpServletRequest request) {
		System.out.println(request.getRequestURL());
		System.out.println(request.getProtocol());
		System.out.println(request.getServerPort());
		System.out.println(request.getServerName());
		return;
	}
}
