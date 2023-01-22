package com.news.service;

import java.util.List;

import com.news.dto.req.ReCommentDTOReq;
import com.news.dto.resp.ReCommentDTOResp;

public interface ReCommentService {
	void saveReComemnt(ReCommentDTOReq dto);
	List<ReCommentDTOResp> getReCommentByComment(long id);
}
