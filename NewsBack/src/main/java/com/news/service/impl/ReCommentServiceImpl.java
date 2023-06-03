package com.news.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.dto.req.ReCommentDTOReq;
import com.news.dto.resp.ReCommentDTOResp;
import com.news.entity.ReComment;
import com.news.mapper.MapperDTO;
import com.news.mapper.MapperEntity;
import com.news.repos.ReCommentRepos;
import com.news.service.ReCommentService;

@Service
public class ReCommentServiceImpl implements ReCommentService{
	
	@Autowired
	ReCommentRepos reRepos;
	
	@Autowired
	MapperDTO mapper;
	
	@Autowired
	MapperEntity mapperEntity;

//	save recomment
	@Override
	public void saveReComemnt(ReCommentDTOReq dto) {
		ReComment re = mapperEntity.mapperReComment(dto);
		reRepos.save(re);
		return;
		
	}

//	get recomment by comment
	@Override
	public List<ReCommentDTOResp> getReCommentByComment(long id) {
		List<ReCommentDTOResp> list=new ArrayList<>();
		for(ReComment re:reRepos.findByCommentId(id)) {
			  ReCommentDTOResp dto=mapper.mapperReCommentDTO(re);
			  list.add(dto);
		}
		return list;
	}

}
