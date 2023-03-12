package com.news.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.news.dto.resp.NewsDTOResp;
import com.news.entity.News;
import com.news.service.NewsService;

@RestController
@RequestMapping("/api/news")
public class NewsController {
	@Autowired 
	NewsService newsService;
	
	@GetMapping("")
	public List<News> findAll(){
		return newsService.findAll();
	}
	
	@GetMapping("/{id}")
	public NewsDTOResp findById(@PathVariable long id) {
		return newsService.findById(id);
	}
}
