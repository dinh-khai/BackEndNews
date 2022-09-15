package com.news.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.dto.CommentDTO;
import com.news.entity.ReComment;
import com.news.repos.CommentRepos;
import com.news.repos.ReCommentRepos;
import com.news.repos.UserRepos;
import com.news.service.ReCommentService;

@Service
public class ReCommentServiceImpl implements ReCommentService{
	
	@Autowired
	ReCommentRepos reRepos;
	
	@Autowired
	CommentRepos cmtRepos;
	
	@Autowired
	UserRepos uRepos;
	
	@Override
	public void saveReComemnt(CommentCreDTO dto) {
		ReComment re=new ReComment();
		re.setContent(dto.getDescription());
		re.setComment(cmtRepos.findById(dto.getId()).orElse(null));
		re.setUserCreator(uRepos.findById(dto.getUserName()).orElse(null));
		re.setCreatedTime(Calendar.getInstance().getTime());
		reRepos.save(re);
		return;	
	}

	@Override
	public void saveReComemnt(CommentDTO dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ReComment> getReCommentByComment(long id) {
		List<ReComment> list=reRepos.findByCommentId(id);
		return list;
	}

}
