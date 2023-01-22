package com.news.controller.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.news.dto.req.CommentDTOReq;
import com.news.service.CommentService;

@CrossOrigin
@RestController
@RequestMapping("/api/news/user/coment")
public class CommentController {
	
	@Autowired
	CommentService cmtService;
	
	/**
	 * Create new comment
	 * 
	 * @param commentDTOReq
	 */
	@PostMapping("/save")
	public void saveComment(@RequestBody CommentDTOReq commentDTOReq) {
		 cmtService.saveComment(commentDTOReq);
		 return;
	}
}
