package com.news.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.news.dto.req.CommentDTOReq;
import com.news.dto.resp.CommentDTOResp;
import com.news.dto.resp.PaginationDTOResp;
import com.news.entity.Comment;
import com.news.mapper.MapperDTO;
import com.news.mapper.MapperEntity;
import com.news.repos.CommentRepos;
import com.news.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	CommentRepos cmtRepos;
	@Autowired
	MapperDTO mapper;
	
	@Autowired
	MapperEntity mapperEntity;

	@Override
	public void saveComment(CommentDTOReq dto) {
		Comment cmt=mapperEntity.mapperComment(dto);
		cmtRepos.save(cmt);
		return;
	}

	@Override
	public PaginationDTOResp getCommentByNews(long id, int page) {
		Pageable pageable=PageRequest.of(page, PaginationDTOResp.size);
		Page<Comment> paging=cmtRepos.findAllByNewsId(id, pageable);
		List<CommentDTOResp> list=new ArrayList<>();
		for(Comment cmt:paging.getContent()) {
			CommentDTOResp dto=mapper.mapperCommentDTO(cmt);
			list.add(dto);
		}
		
		return new PaginationDTOResp(paging.getTotalPages(),paging.getNumber(),list,paging.isFirst(),paging.isLast());
	}


	
	
}
