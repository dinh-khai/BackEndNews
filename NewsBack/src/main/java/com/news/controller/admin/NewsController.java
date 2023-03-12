package com.news.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.news.service.NewsService;
import com.news.service.UpLoadService;

@RestController
@RequestMapping("/api/news")
public class NewsController {
	@Autowired
	NewsService newService;
	@Autowired
	UpLoadService upload;
}
