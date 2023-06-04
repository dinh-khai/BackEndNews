package com.news.controller;

import java.util.List;

import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.news.dto.resp.NewsDTOResp;
import com.news.dto.resp.PaginationDTOResp;
import com.news.service.CommentService;
import com.news.service.NewsService;

@RestController
@RequestMapping("/api/news/")
@CrossOrigin("*")
public class NewsController {
	@Autowired 
	NewsService newsService;
	@Autowired
	CommentService cmtService;
	@Autowired
	ServletContext context;
	
	@GetMapping("{id}")
	public ResponseEntity<NewsDTOResp> findById(@PathVariable long id) {
		return new ResponseEntity<NewsDTOResp>(newsService.findById(id),HttpStatus.OK);
	}
	
	@GetMapping("{id}/comments")
	public ResponseEntity<PaginationDTOResp> getCommentOfNews(@PathVariable long id, @RequestParam int page, @RequestParam int limit) {
	    return new ResponseEntity<PaginationDTOResp>(cmtService.getCommentByNews(id, page, limit), HttpStatus.OK);
	}
	
	@GetMapping
    public ResponseEntity<PaginationDTOResp> getNews(@RequestParam int page, @RequestParam int limit, @RequestParam(required = false) String sortType, @RequestParam(required = false) String sortBy) {
        return new ResponseEntity<PaginationDTOResp>(newsService.getListNews(page, limit, sortType, sortBy), HttpStatus.OK);
    }
	
	@GetMapping("featured")
	public ResponseEntity<List<NewsDTOResp>> getNewsByFetured(@RequestParam int page, @RequestParam int limit) {
	    return new ResponseEntity<List<NewsDTOResp>>(newsService.getListNewsByFeatured(page, limit), HttpStatus.OK);
	}
	
	@GetMapping("search")
    public ResponseEntity<PaginationDTOResp> search(@RequestParam String q, @RequestParam int page, @RequestParam int limit) {
        return new ResponseEntity<PaginationDTOResp>(newsService.search(q, page, limit), HttpStatus.OK);
    }
}
