package com.news.controller.user;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.news.dto.resp.UserDTOResp;
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
	
	@GetMapping(path="/upload")
	public String test() throws IOException {
		return app.getContextPath();
//		System.out.println(up.upload(file,"user"));
	}
	
	@PostMapping(path="/user")
	public void getUser(@RequestBody UserDTOResp user) {
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
	
	@PostMapping("/ckeditor")
	public void ck(@RequestBody String a) {
		System.out.println(a);
	}
}
