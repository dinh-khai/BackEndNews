package com.news.controller.general;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.news.dto.resp.ReCommentDTOResp;
import com.news.service.ReCommentService;

@RestController
@RequestMapping("/api/news/general/reComment")
public class ReCommentGeneralController {
	@Autowired
	ReCommentService reService;
	
	@GetMapping("/allByComment/{cmtId}")
	public List<ReCommentDTOResp> getReCommentByComment(@PathVariable long cmtId){
		return reService.getReCommentByComment(cmtId);
	}
}
