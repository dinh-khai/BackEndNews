package com.news.service.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.dto.CommentDto;
import com.news.entity.Comment;
import com.news.repos.CommentRepos;
import com.news.repos.NewsRepos;
import com.news.repos.UserRepos;
import com.news.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	CommentRepos cmtRepos;
	
	@Autowired
	NewsRepos newsRepos;
	
	@Autowired
	UserRepos uRepos;

	@Override
	public List<Comment> findAll() {
		return cmtRepos.findAll();
	}

	@Override
	public Optional<Comment> findById(Long id) {
		return cmtRepos.findById(id);
	}

	@Override
	public void saveComment(CommentDto dto) {
		Comment comment=new Comment();
		comment.setUserCreator(uRepos.findById(dto.getUserName()).orElse(null));
		comment.setContent(dto.getDescription());
		comment.setNews(newsRepos.findById(dto.getId()).orElse(null));
		comment.setCreatedTime(Calendar.getInstance().getTime());
		cmtRepos.save(comment);
		return;
	}
	
	
}
