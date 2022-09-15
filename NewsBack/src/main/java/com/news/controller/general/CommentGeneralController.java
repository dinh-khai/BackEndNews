package com.news.controller.general;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.news.dto.PaginationDTO;
import com.news.service.CommentService;

@RequestMapping("/api/news/genral/comment")
@RestController
public class CommentGeneralController {
	@Autowired
	CommentService cmtService;
	
	@GetMapping("/{newsId}/{page}")
	public PaginationDTO getCommentByNews(@PathVariable Map<String, String> comments) {
		long id=Long.parseLong(comments.get("newsId"));
		int page=Integer.parseInt(comments.getOrDefault("page", "0"));
		return cmtService.getCommentByNews(id, page);
	}
}
