package com.news.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.news.entity.NewsClassification;
import com.news.service.ClassifyService;

@CrossOrigin
@RestController
@RequestMapping("/news")
public class ClassifyController {
	@Autowired
	ClassifyService service;
	
	@GetMapping("/classify/all")
	public List<NewsClassification> getAll(){
		return service.findAll();
	}
}
