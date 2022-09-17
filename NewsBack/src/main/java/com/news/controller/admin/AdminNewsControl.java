package com.news.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.news.dto.MoreDescriptionDto;
import com.news.dto.create.NewsCreateDTO;
import com.news.entity.News;
import com.news.service.MoreDescriptionService;
import com.news.service.NewsService;

@CrossOrigin
@RestController
@RequestMapping("/api/news/admin")
public class AdminNewsControl {
	@Autowired
	NewsService newsService;
	
	@Autowired
	MoreDescriptionService desService;
	
	@PostMapping(value="/save",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public News save(HttpServletRequest request,@RequestPart NewsCreateDTO dto,@RequestPart MultipartFile file) {
		return newsService.saveNews(dto,file,request);
		
	}
	
	@PostMapping(value="/saveMoreDes")
	public void saveMoreDes(@RequestBody(required = false) MoreDescriptionDto des){
		desService.save(des);
		return;
	}

	@PutMapping(value="/update",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public void update(HttpServletRequest request,@RequestPart NewsCreateDTO dto,@RequestPart MultipartFile file,@RequestPart long id) {
		newsService.updateNews(id, dto, file, request);
		return;
	}
	
	@DeleteMapping("/delete")
	public void delete(@RequestParam long id) {
		newsService.deleteNews(id);
		return;
	}
}
