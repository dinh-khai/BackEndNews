package com.news.service;

import java.util.List;


import com.news.dto.ReCommentDTO;
import com.news.dto.create.ReCommentCreateDTO;

public interface ReCommentService {
	void saveReComemnt(ReCommentCreateDTO dto);
	List<ReCommentDTO> getReCommentByComment(long id);
}
