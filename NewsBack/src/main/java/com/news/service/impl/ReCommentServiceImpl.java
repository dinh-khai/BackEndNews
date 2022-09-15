package com.news.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.news.dto.ReCommentDTO;
import com.news.dto.create.ReCommentCreateDTO;
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
	public void saveReComemnt(ReCommentCreateDTO dto) {
		ReComment re=mapperEntity.mapperReComment(dto);
		reRepos.save(re);
		return;
		
	}

//	get recomment by comment
	@Override
	public List<ReCommentDTO> getReCommentByComment(long id) {
		List<ReCommentDTO> list=new ArrayList<>();
		for(ReComment re:reRepos.findByCommentId(id)) {
			  ReCommentDTO dto=mapper.mapperReCommentDTO(re);
			  list.add(dto);
		}
		return list;
	}

}
