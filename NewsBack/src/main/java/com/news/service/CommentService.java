package com.news.service;

import com.news.dto.PaginationDTO;
import com.news.dto.create.CommentCreateDTO;

public interface CommentService {
	
	void saveComment(CommentCreateDTO dto);
	
	PaginationDTO getCommentByNews(long id,int page);

}
