package com.news.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	private NewsService newsService;
	@Autowired
	ObjectMapper jsonMapper;
	
	@PostMapping(value = "",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<String> save(@RequestParam String dto , @RequestPart(required = false) MultipartFile file) throws JsonMappingException, JsonProcessingException{
	    NewsDTOReq req = jsonMapper.readValue(dto, NewsDTOReq.class);
	    newsService.saveNews(req, file);
		return new ResponseEntity<String>("insert success!!!",HttpStatus.CREATED);
	}
	
	@PutMapping(value = "",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> update(@RequestParam long id, @RequestParam String dto , @RequestPart(required = false) MultipartFile file) throws JsonMappingException, JsonProcessingException{
        NewsDTOReq req = jsonMapper.readValue(dto, NewsDTOReq.class);
        newsService.updateNews(id,req, file);
        return new ResponseEntity<String>("update success!!!",HttpStatus.OK);
    }
	
	@DeleteMapping(value = "")
    public ResponseEntity<String> delete(@RequestParam long id) throws JsonMappingException, JsonProcessingException{
        newsService.deleteNews(id);
        return new ResponseEntity<String>("delete success!!!",HttpStatus.OK);
    }
}
