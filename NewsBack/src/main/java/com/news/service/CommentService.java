package com.news.service;

import com.news.dto.req.CommentDTOReq;
import com.news.dto.resp.PaginationDTOResp;

public interface CommentService {
	
	void saveComment(CommentDTOReq dto);
	
	PaginationDTOResp getCommentByNews(long id,int page, int limit);

}
