package com.news.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.news.dto.req.NewsDTOReq;
import com.news.service.NewsService;

@RestController
@RequestMapping("/api/news")
public class AdminNewsController {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private NewsService newsService;
	
	@PostMapping(value = "",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<String> save(@RequestPart NewsDTOReq dto , @RequestPart MultipartFile file){
		newsService.saveNews(dto, file, request);
		return new ResponseEntity<String>("insert success!!!",HttpStatus.OK);
	}
	
}
