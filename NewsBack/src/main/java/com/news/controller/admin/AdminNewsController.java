package com.news.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.news.dto.req.NewsDTOReq;
import com.news.service.NewsService;

@RestController
@RequestMapping("/api/news/")
@CrossOrigin("*")
public class AdminNewsController {
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private NewsService newsService;
	@Autowired
	ObjectMapper jsonMapper;
	
	@PostMapping(value = "",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<String> save(@RequestParam String dto , @RequestPart(required = false) MultipartFile file) throws JsonMappingException, JsonProcessingException{
	    NewsDTOReq req = jsonMapper.readValue(dto, NewsDTOReq.class);
	    newsService.saveNews(req, file, request);
		return new ResponseEntity<String>("insert success!!!",HttpStatus.OK);
	}
	
}
