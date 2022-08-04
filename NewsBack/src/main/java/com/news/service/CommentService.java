package com.news.service;

import java.util.List;
import java.util.Optional;

import com.news.dto.CommentDto;
import com.news.entity.Comment;

public interface CommentService {

	Optional<Comment> findById(Long id);

	List<Comment> findAll();
	
	void saveComment(CommentDto dto);

}
