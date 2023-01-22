package com.news.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.news.dto.req.ReCommentDTOReq;
import com.news.service.ReCommentService;

@RestController
@RequestMapping("/api/news/user/reComment")
public class ReCommentController {
	@Autowired
	ReCommentService reService;
	
	/**
	 * Create new recomment
	 * 
	 * @param reCommentDTOReq
	 */
	@PostMapping("/save")
	public void saveReComment(@RequestBody ReCommentDTOReq reCommentDTOReq) {
		reService.saveReComemnt(reCommentDTOReq);
		return;
	}
}
