package com.news.controller.user;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.news.dto.CommentDto;
import com.news.entity.Comment;
import com.news.entity.ReComment;
import com.news.service.CommentService;
import com.news.service.ReCommentService;

@CrossOrigin
@RestController
@RequestMapping("/news")
public class CommentController {
	
	@Autowired
	CommentService cmtService;
	
	@Autowired
	ReCommentService reService;
	
	@GetMapping("/comment/all")
	public List<Comment> getAllComments(){
		return cmtService.findAll();
	}
	
	@GetMapping("/comment/allReComment/{id}")
	public Set<ReComment> getAllReCommentByIdCmt(@PathVariable long id) throws Exception{
		try {
			return cmtService.findById(id).get().getListReComment();
		} catch (Exception e) {
			throw new Exception();
		}
	}
	
	@PostMapping("/comment/saveComment")
	public void saveComment(@RequestBody CommentDto dto) {
		 cmtService.saveComment(dto);
		 return;
	}
	
	@PostMapping("/comment/saveReComment")
	public void saveReComment(@RequestBody CommentDto dto) {
		 reService.saveReComemnt(dto);
		 return;
	}
}
