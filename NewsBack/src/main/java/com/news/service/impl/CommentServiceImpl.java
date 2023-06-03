package com.news.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.news.dto.req.CommentDTOReq;
import com.news.dto.resp.CommentDTOResp;
import com.news.dto.resp.PaginationDTOResp;
import com.news.entity.Comment;
import com.news.entity.News;
import com.news.entity.User;
import com.news.mapper.MapperDTO;
import com.news.mapper.MapperEntity;
import com.news.repos.CommentRepos;
import com.news.repos.NewsRepos;
import com.news.repos.UserRepos;
import com.news.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	CommentRepos cmtRepos;
	@Autowired
	UserRepos userRepos;
	@Autowired
	NewsRepos newsRepos;
	@Autowired
	MapperDTO mapper;
	
	@Autowired
	MapperEntity mapperEntity;

	@Override
	@Transactional   
	public void saveComment(CommentDTOReq dto) {
	    Comment cmt = new Comment();
        User user=userRepos.findById(dto.getUsername()).orElse(null);
        News news =newsRepos.findById(dto.getNewsId()).orElse(null);
        cmt.setContent(dto.getDescription());
        cmt.setCreatedTime(dto.getCreatedTime());
        cmt.setUserCreator(user);
        cmt.setNews(news);
        cmtRepos.save(cmt);
        return;
	}

	@Override
	public PaginationDTOResp getCommentByNews(long id, int page, int limit) {
		Pageable pageable=PageRequest.of(page, limit);
		Page<Comment> paging=cmtRepos.findAllByNewsId(id, pageable);
		List<CommentDTOResp> list=new ArrayList<>();
		for(Comment cmt:paging.getContent()) {
			CommentDTOResp dto=mapper.mapperCommentDTO(cmt);
			list.add(dto);
		}
		
		
		return new PaginationDTOResp(paging.getTotalPages(),paging.getNumber(),list,paging.isFirst(),paging.isLast());
	}


	
	
}
