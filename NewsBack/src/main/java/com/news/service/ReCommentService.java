package com.news.service;

import java.util.List;

import com.news.dto.CommentDTO;
import com.news.entity.ReComment;

public interface ReCommentService {
	void saveReComemnt(CommentDTO dto);
	List<ReComment> getReCommentByComment(long id);
}
