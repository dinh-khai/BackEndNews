package com.news.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.news.dto.resp.NewsDTOResp;
import com.news.entity.News;
import com.news.exception.MyException;
import com.news.service.NewsService;

@RestController
@RequestMapping("/api/news/")
@CrossOrigin("*")
public class NewsController {
	@Autowired 
	NewsService newsService;
	@Autowired
	ServletContext context;
	
	@GetMapping("")
	public ResponseEntity<List<NewsDTOResp>>  findAll(){
		return new ResponseEntity<List<NewsDTOResp>>(newsService.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<NewsDTOResp> findById(@PathVariable long id) {
		return new ResponseEntity<NewsDTOResp>(newsService.findById(id),HttpStatus.OK);
	}
	
	@GetMapping(value = "/image")
	public String getImage(HttpServletResponse response) throws IOException {
////	    InputStream in = context.getResourceAsStream("F://Image/IMG_20221230_183913.jpg");
//	    InputStream in = new FileInputStream(new File("F://Image/IMG_20221230_183913.jpg"));
//	    IOUtils.copy(in, response.getOutputStream());
	    File file = new File("/");
	    return file.getAbsolutePath();
	}
}
