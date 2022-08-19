package com.news.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.news.dto.MoreDescriptionDto;
import com.news.entity.News;
import com.news.service.MoreDescriptionService;
import com.news.service.NewsService;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminNewsControl {
	@Autowired
	NewsService newsService;
	
	@Autowired
	MoreDescriptionService desService;
	
	@PostMapping("/news/save")
	public News save(HttpServletRequest request,@RequestParam String title,@RequestParam String description,
			@RequestParam int cateId,@RequestParam int classifyId,
			@RequestParam boolean featured,@RequestParam(required = false) MultipartFile file) {
		return newsService.saveNews(title, description, cateId, classifyId, featured, file,request.getServerName(),request.getServerPort());
		
	}
	
	@PostMapping(value="/news/saveMoreDes")
	public void saveMoreDes(@RequestBody(required = false) MoreDescriptionDto des){
		desService.save(des);
		return;
	}

	@PutMapping("/news/update")
	public void update(HttpServletRequest request,@RequestParam long id, @RequestParam String title,@RequestParam String description,
			@RequestParam int cateId,@RequestParam int classifyId,
			@RequestParam boolean featured,@RequestParam MultipartFile file) {
		newsService.updateNews(id,title, description, cateId, classifyId, featured, file,request.getServerName(),request.getServerPort());
		return;
	}
	
	@DeleteMapping("/news/delete")
	public void delete(@RequestParam long id) {
		newsService.deleteNews(id);
		return;
	}
}
